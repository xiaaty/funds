package com.gqhmt.tyzf.common.frame.buffer;

import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.IServiceObject;

/**
 * Created by zhou on 2016/10/24.
 * Description:本地缓存接口
 */
public interface IMsgBuffer {

    /**
     * 获取消息
     * @return
     * @throws FrameException
     * @throws InterruptedException
     */
    IServiceObject get() throws FrameException, InterruptedException;

    /**
     * 为本地缓存添加消息
     * @param obj
     * @throws FrameException
     */
    void put(IServiceObject obj) throws FrameException;

    /**
     * 获取缓存中消息数量
     * @return
     */
    int getNum();

    /**
     * 清除缓存中的数据
     */
    void clear();
}
