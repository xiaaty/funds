package com.gqhmt.tyzf.common.frame.mqserver;

import com.gqhmt.tyzf.common.frame.amq.AmqReceiver;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.tyzf.common.frame.buffer.AbstractMsgReceiveSend;
import com.gqhmt.tyzf.common.frame.buffer.MsgBuffer;
import com.gqhmt.tyzf.common.frame.common.*;
import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.ServiceRequest;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Hashtable;

/**
 * Created by zhou on 2016/10/20.
 * Description:MQ接收类
 */
public abstract class BaseMQMsgReceive extends AbstractMultiThread implements IConfigurable {

    protected String LOG_PREFIX = "BaseMQMsgReceive";
    /** mq收发 */
    private AmqSendAndReceive amqReceiver;

    /** 本地缓存 */
    protected MsgBuffer receiveBuffer = null;

    /** 类名 */
    protected String prefix = null;

    /** 属性字段 */
    protected Hashtable<String, String> props ;

    /** 内置锁 */
    private Object obj = new Object();

    /**
     * 无参构造
     * @throws FrameException
     */
    public BaseMQMsgReceive() throws FrameException {
        boolean ret = this.init();
        if (ret)
            LogUtil.getInstance().debug(LOG_PREFIX + "initiation is successful!");
        else {
            LogUtil.getInstance().error(LOG_PREFIX + " initiation is failed!");
            throw new FrameException(FrameConstans.MQ_MSG_RECEIVE_INSTANCE_INIT_ERR);
        }
    }

    /**
     * 有参构造（ConfigManager启动时调用此构造）
     * @param prefix
     * @throws FrameException
     */
    public BaseMQMsgReceive(String prefix) throws FrameException {
        this.prefix = prefix;
        boolean ret = this.init();
        if (ret)
            LogUtil.getInstance().debug(LOG_PREFIX + " initiation is successful!");
        else {
            LogUtil.getInstance().error(LOG_PREFIX + " initiation is failed!");
            throw new FrameException(FrameConstans.MQ_MSG_RECEIVE_INSTANCE_INIT_ERR);
        }
    }

    /**
     * 初始化MQ接收对象和本地缓存对象
     * @return
     */
    protected boolean init() {
        isDaemon = true;
        boolean ret = false;
        try {
            props=initCfgData();
            //加载接收MQ的队列名称
            String receiveQueueName = (String) ConfigManager.getInstance().get(props.get(MQConstants.AMQ_RECEIVEQUEUENAME));
            //实例化MQ接收对象
            if(receiveQueueName != null && receiveQueueName.trim().length() > 0) {
                LogUtil.getInstance().debug(LOG_PREFIX +" receiveQueueName:"+ receiveQueueName);
                amqReceiver = new AmqReceiver(receiveQueueName);
            }
            //加载本地缓存名称
            String bufferName= (String)ConfigManager.getInstance().get(props.get(MQConstants.AMQ_RECEIVE_BUFFER));
            //实例化本地缓存对象
            this.receiveBuffer = AbstractMsgReceiveSend.getMsgBufferInstance(bufferName+ Constants.KEY_SPLIT+Constants.KEY_SUFFIX_INSTANCE);
            ret = true;
        } catch (Exception e) {
            LogUtil.getInstance().error(LOG_PREFIX + " 请求分派守护线程初始化错误：" + e.getMessage());
            LogUtil.getInstance().error(e);
            return false;
        }
        return ret;
    }

    @Override
    protected void stop() {
    }

    /**
     * 初始化配置参数，由派生类实现
     */
    protected abstract Hashtable<String,String> initCfgData() throws FrameException;

    @Override
    public void run() {
        // 设置为运行状态
        setStatus(AbstractMultiThread.RUN);
        while (this.getStatus() == AbstractMultiThread.RUN) {
            Message jmsMsg = null;
            try {
                jmsMsg = amqReceiver.receiveMessage();
            } catch (Exception ex) {
                LogUtil.getInstance().info(LOG_PREFIX +" getNextMessage  Interrupted !",ex);
                continue;
            }
            try {
                if (jmsMsg == null) {
                    continue;
                }
                if(!(jmsMsg instanceof TextMessage)) {
                    LogUtil.getInstance().debug("Receive Message from Queue is not instanceof TextMessage.");
                    continue;
                }
                String msgStr = ((TextMessage)jmsMsg).getText();
                //无论同步异步均需获取并设置请求对象的correlationId.
                String correlationId = jmsMsg.getJMSCorrelationID();
                if(correlationId == null || correlationId.trim().length()<= 0) {
                    correlationId = jmsMsg.getJMSMessageID();
                }
                System.out.println("JMS CorrelationId is : " + correlationId);
                ServiceRequest servReq = new ServiceRequest();
                servReq.setCorrelationId(correlationId);
                LogUtil.getInstance().debug(LOG_PREFIX + " receive msg success");
                servReq.setRequestMsg(msgStr);

                receiveBuffer.put(servReq);
                LogUtil.getInstance().info(LOG_PREFIX + " receiveBuffer Size reach " + receiveBuffer.getNum());
            } catch (JMSException e) {
                LogUtil.getInstance().error(e);
            } catch (FrameException e) {
                LogUtil.getInstance().error(e);
            }
        }
    }

    /**************** IConfigurable实现方法 *****************/

    @Override
    public void refresh() throws FrameException {
        synchronized(obj){
            LogUtil.getInstance().debug(LOG_PREFIX + " refresh begin");

            if(this.receiveBuffer != null){
                this.receiveBuffer = null;
            }

            boolean ret = this.init();
            if (ret)
                LogUtil.getInstance().debug(LOG_PREFIX + " refresh end");
            else {
                LogUtil.getInstance().error(LOG_PREFIX + " 请求分派守护线程刷新错误");
                throw new FrameException(FrameConstans.MQ_MSG_RECEIVE_INSTANCE_REFRESH_ERR);
            }
        }
    }

    @Override
    public IStatus checkStatus() throws FrameException {
        LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus begin");
        try {
            if (getStatus() == AbstractMultiThread.RUN) {
                LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus end");
                return new BasicStatus(true, LOG_PREFIX + ServerConConstants.CHECK_MQ_RECEIVE_RUN_SUCCESS + ","
                        + ServerConConstants.CHECK_MQ_SUCCESS);
            } else if (getStatus() == AbstractMultiThread.SHOULD_STOP) {
                LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_RECEIVE_STOPPING_ERROR);
            } else {
                LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_RECEIVE_STOPPED_ERROR);
            }
        }catch(Exception e){
            LogUtil.getInstance().debug(LOG_PREFIX,e);
            if (getStatus() == AbstractMultiThread.RUN) {
                LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_ERROR);
            } else if (getStatus() == AbstractMultiThread.SHOULD_STOP) {
                LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus end");
                return new BasicStatus(false, LOG_PREFIX+ServerConConstants.CHECK_MQ_ERROR + ","
                        + LOG_PREFIX+ServerConConstants.CHECK_MQ_RECEIVE_STOPPING_ERROR);
            } else {
                LogUtil.getInstance().debug(LOG_PREFIX + " checkStatus end");
                return new BasicStatus(false, LOG_PREFIX + ServerConConstants.CHECK_MQ_ERROR + ","
                        + LOG_PREFIX + ServerConConstants.CHECK_MQ_RECEIVE_STOPPED_ERROR);
            }
        }
    }

    @Override
    public void compensate() throws FrameException {}

    @Override
    public void release() {}

}
