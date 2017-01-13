package com.gqhmt.tyzf.common.frame.buffer;

import com.gqhmt.tyzf.common.frame.exception.FrameException;

/**
 * Created by zhou on 2016/10/27.
 * Description:本地缓存收发接口
 */
public interface IMsgReceiveSend {

    /**
     * 获取缓存实例
     * @return
     * @throws FrameException
     */
    MsgBuffer getMsgBuffer()throws FrameException;

}
