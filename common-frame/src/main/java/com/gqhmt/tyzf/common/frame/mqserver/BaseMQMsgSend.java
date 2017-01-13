package com.gqhmt.tyzf.common.frame.mqserver;

import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.buffer.AbstractMsgReceiveSend;
import com.gqhmt.tyzf.common.frame.buffer.MsgBuffer;
import com.gqhmt.tyzf.common.frame.common.*;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.MsgConstants;
import com.gqhmt.tyzf.common.frame.message.ServiceRequest;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;
import com.gqhmt.tyzf.common.frame.message.MsgObject;
import com.mysql.jdbc.log.LogUtils;

import java.util.Hashtable;

/**
 * Created by zhou on 2016/10/20.
 * Description: MQ发送类
 */
public abstract class BaseMQMsgSend extends AbstractMultiThread implements IConfigurable {
    /** log前缀 */
    protected String LOG_PREFIX = "BaseMQMsgSend";
    /** 缓存 */
    protected MsgBuffer sendBuffer = null;
    /** 类名 */
    protected String prefix = null;
    /** mq发送 */
    protected AmqSender amqSender;
/** 属性字段*/
    protected Hashtable<String, String> props ;
    /** 内置锁 */
    private Object obj = new Object();

    /**
     * 无参构造
     * @throws FrameException
     */
    public BaseMQMsgSend() throws FrameException {
        boolean ret = this.init();
        if (ret) {
            LogUtil.getInstance().debug(LOG_PREFIX + " daemon init complete");
        } else {
            LogUtil.getInstance().debug(LOG_PREFIX + " daemon init failed");
            throw new FrameException(FrameConstans.MQ_MSG_SEND_INSTANCE_INIT_ERR);
        }
    }

    /**
     * 有参构造（ConfigManager启动时调用此构造）
     * @param prefix
     * @throws FrameException
     */
    public BaseMQMsgSend(String prefix) throws FrameException {
        this.prefix = prefix;
        boolean ret = this.init();
        if (ret) {
            LogUtil.getInstance().debug(LOG_PREFIX + " daemon init complete");
        } else {
            LogUtil.getInstance().debug(LOG_PREFIX + " daemon init failed");
            throw new FrameException(FrameConstans.MQ_MSG_SEND_INSTANCE_INIT_ERR);
        }
    }

    private boolean init() {
        isDaemon = true;
        boolean ret = false;
        try {
            props = this.initCfgData();
            String sendQueueName = (String) ConfigManager.getInstance().get(props.get(MQConstants.AMQ_SENDQUEUENAME));
            if(sendQueueName != null && sendQueueName.length() > 0) {
                LogUtil.getInstance().debug(LOG_PREFIX +" sendQueueName:"+ sendQueueName);
                amqSender = new AmqSender(null, sendQueueName);
            }
            String bufferName = (String) ConfigManager.getInstance().get(props.get(MQConstants.AMQ_SEND_BUFFER))
                    + Constants.KEY_SPLIT+ Constants.KEY_SUFFIX_INSTANCE;
            this.sendBuffer = AbstractMsgReceiveSend.getMsgBufferInstance(bufferName);// 此处要注意。。。
            ret = true;
        } catch (Exception e) {
            LogUtil.getInstance().error(LOG_PREFIX + " daemon 响应分派守护线程初始化错误：" + e.getMessage());
            LogUtil.getInstance().error(e);
            return false;
        }
        return ret;
    }

    @Override
    protected void stop() {
    }

    /**
     * 初始化配置参数；由派生类实现
     */
    protected abstract Hashtable<String, String> initCfgData() throws FrameException;

    @Override
    public void run(){
        // 设置为运行状态
        setStatus(AbstractMultiThread.RUN);
        LogUtil.getInstance().info(LOG_PREFIX + " begin to run");
        ServiceRequest servResp = null;
        String msg = null;
        while (getStatus() == AbstractMultiThread.RUN) {
            try {
                LogUtil.getInstance().info(LOG_PREFIX + " daemon sendBuffer Size reach " + sendBuffer.getNum());
                if(amqSender!=null){
                    try {
                        servResp = (ServiceRequest) this.sendBuffer.get();
                    }catch(Exception e) {
                        System.out.println("msg : " + e.getMessage());
                        LogUtil.getInstance().info(LOG_PREFIX,e);
                        setStatus(AbstractMultiThread.SHOULD_STOP);
                        continue;
                    }
                    if (servResp == null) {
                        continue;
                    }
                    MsgObject mo=servResp.getMo();
                    if (mo == null||mo.getMessageSendBody()==null) {
                        continue;
                    }
                    msg=mo.getMessageSendBody();
                    LogUtil.getInstance().debug("Send Message to Queue = " + msg.length());
                    LogUtil.getInstance().debug(" Send msgBody: \n" + msg);
                    try {
                        //处理单笔同步交易发送
                        if(MsgConstants.ACTUAL_TRADE.equalsIgnoreCase(mo.getRequestHeaderParameter(MsgConstants.IS_ACTUAL))){
                            //如果是同步消息，则设置消息的correlationId.
                            amqSender.sendSimpleSynchronizedMsg(msg,servResp.getCorrelationId());
                        }else{
                            amqSender.sendMsg(msg);
                        }
                    } catch(AmqException e) {
                        LogUtil.getInstance().debug("--Send Message failed!",e);
                    }
                    LogUtil.getInstance().debug("--Send Message success!");
                }
            } catch (Exception ex) {
                LogUtil.getInstance().error(ex,(Object)msg);
            }
        }
        LogUtil.getInstance().info(LOG_PREFIX + " daemon stopped");
        if (null != stopReason) {
            LogUtil.getInstance().info(LOG_PREFIX + " daemon stop reason is: " + stopReason);
        }
        //线程结束
        setStatus(AbstractMultiThread.STOPPED);
    }

    /**************** IConfigurable实现方法 *****************/

    @Override
    public void release() {}

    @Override
    public void refresh() throws FrameException {
        synchronized (obj) {
            LogUtil.getInstance().debug(LOG_PREFIX + " daemon refresh begin");
            if (this.sendBuffer != null) {
                this.sendBuffer = null;
            }

            boolean ret = this.init();
            if (ret) {
                LogUtil.getInstance().debug(LOG_PREFIX + " daemon refresh end");
            }
        }
    }

    @Override
    public IStatus checkStatus() throws FrameException {
        LogUtil.getInstance().debug(LOG_PREFIX + " daemon checkStatus begin");
        try {
            if (getStatus() == AbstractMultiThread.RUN) {
                LogUtil.getInstance().debug(LOG_PREFIX + " daemon checkStatus end");
                return new BasicStatus(true, LOG_PREFIX + ServerConConstants.CHECK_MQ_SEND_RUN_SUCCESS + "," + LOG_PREFIX
                        + ServerConConstants.CHECK_MQ_SUCCESS);
            } else if (getStatus() == AbstractMultiThread.SHOULD_STOP) {
                LogUtil.getInstance().debug(LOG_PREFIX + " daemon checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_SEND_STOPPING_ERROR);
            } else {
                LogUtil.getInstance().debug(LOG_PREFIX + " daemon checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_SEND_STOPPED_ERROR);
            }
        } catch (Exception e) {
            LogUtil.getInstance().error(LOG_PREFIX,e);
            if (getStatus() == AbstractMultiThread.RUN) {
                LogUtil.getInstance().debug(LOG_PREFIX + " daemon checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_ERROR);
            } else if (getStatus() == AbstractMultiThread.SHOULD_STOP) {
                LogUtil.getInstance().debug(LOG_PREFIX + " daemon checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_ERROR + "," + LOG_PREFIX
                        + ServerConConstants.CHECK_MQ_SEND_STOPPING_ERROR);
            } else {
                LogUtil.getInstance().debug(LOG_PREFIX + " daemon checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_ERROR + "," + LOG_PREFIX
                        + ServerConConstants.CHECK_MQ_SEND_STOPPED_ERROR);
            }
        }
    }

    @Override
    public void compensate() throws FrameException {}

}
