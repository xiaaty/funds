package com.gqhmt.tyzf.common.frame.buffer;

import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

/**
 * Created by zhou on 2016/10/24.
 * Description:系统缓存抽象类
 */
public abstract class AbstractMsgReceiveSend implements IMsgReceiveSend {

    //日志前缀
    protected String LOG_PREFIX = "AbstractMsgReceiveSend";

    //本地缓存对象
    protected MsgBuffer buffer = null;

    //在配置文件中的标签名称
    protected String prefix = null;

    /**
     * Constructor
     * @throws FrameException
     */
    public AbstractMsgReceiveSend(String prefix) throws FrameException {
        this.prefix = prefix;
        boolean ret = this.init();
        if (ret)
            LogUtil.getInstance().debug(LOG_PREFIX + " init complete");
        else {
            LogUtil.getInstance().debug(LOG_PREFIX +" init failed");
            throw new FrameException(FrameConstans.MSG_BUFFER_INIT_ERR +"-init failed");
        }
    }

    /**
     * Constructor
     * @throws FrameException
     */
    public AbstractMsgReceiveSend() throws FrameException {
        boolean ret = this.init();
        if (ret)
            LogUtil.getInstance().debug(LOG_PREFIX + " init complete");
        else {
            LogUtil.getInstance().debug(LOG_PREFIX +" init failed");
            throw new FrameException(FrameConstans.MSG_BUFFER_INIT_ERR +"-init failed");
        }
    }

    /**
     * 初始化方法，由子类实现
     * @return
     * @throws FrameException
     */
    protected abstract boolean init()throws FrameException;

    /**
     * 获取缓存对象
     * @return
     * @throws FrameException
     */
    @Override
    public MsgBuffer getMsgBuffer() throws FrameException {
        if(buffer==null){
            LogUtil.getInstance().error(LOG_PREFIX +" buffer is null");
            throw new FrameException(FrameConstans.MSG_BUFFER_INIT_ERR +"-buffer is null");
        }
        return buffer;
    }

    /**
     * 根据传入的实例名获取实例
     * @param instanceName
     * @return
     * @throws FrameException
     */
    public static MsgBuffer getMsgBufferInstance(String instanceName)throws FrameException {
        IMsgReceiveSend msgBuffer = (IMsgReceiveSend) ConfigManager.getInstance().get(instanceName);
        if (msgBuffer == null) {
            LogUtil.getInstance().error(instanceName+"获取失败");
            throw new FrameException(FrameConstans.MSG_BUFFER_ERR +"-"+instanceName+"获取失败");
        } else
            return msgBuffer.getMsgBuffer();
    }

}
