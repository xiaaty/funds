package com.gqhmt.tyzf.common.frame.buffer;

import com.gqhmt.tyzf.common.frame.config.ConfigManager;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

/**
 * Created by zhou on 2016/10/27.
 * Description: 系统缓存对象
 */
public class ESBMsgBuffer extends AbstractMsgReceiveSend {

    /**
     * 有参构造
     * @param prefix config里的标签名称
     * @throws FrameException
     */
    public ESBMsgBuffer(String prefix) throws FrameException {
        super(prefix);
    }

    @Override
    protected boolean init() throws FrameException{
        try {
            //设置实例的名称
            LOG_PREFIX = "TYZFMsgBuffer";

            //根据参数初始化buffer
            Object obj = (String) ConfigManager.getInstance().get(prefix + ".BufferSize");
            if (obj != null) {
                int size = Integer.parseInt((String)obj);
                //初始化buffer
                buffer=new MsgBuffer(size);
            }else{
                buffer=new MsgBuffer();
            }
            return true;
        } catch (FrameException ex) {
            LogUtil.getInstance().error(ex);
        } catch (Exception ex) {
            LogUtil.getInstance().error(ex);
        }
        return false;
    }

}
