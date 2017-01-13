package com.gqhmt.tyzf.common.frame.buffer;

import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;
import com.gqhmt.tyzf.common.frame.message.IServiceObject;
import com.gqhmt.tyzf.common.frame.util.log.LogUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhou on 2016/10/24.
 * Description: 本地缓存类
 */
public class MsgBuffer implements IMsgBuffer {
    //本地缓存队列
    private BlockingQueue<IServiceObject> bufferQueue;

    /**
     * 构造本地缓存队列
     */
    public MsgBuffer() {
        bufferQueue = new ArrayBlockingQueue<IServiceObject>(MsgBufferConstants.DATA_BUFFER_DEF_CAP);
    }
    /**
     * 构造本地缓存队列，指定队列深度
     */
    public MsgBuffer(int theCap) {
        bufferQueue = new ArrayBlockingQueue<IServiceObject>(theCap);
    }

    /**
     * 初始化队列
     * @param theCap 队列深度
     */
    public void init(int theCap) {
        bufferQueue = new ArrayBlockingQueue<IServiceObject>(theCap);
    }

    @Override
    public IServiceObject get() throws FrameException {
        try {
            return bufferQueue.take();
        } catch (InterruptedException e) {
            LogUtil.getInstance().error(e);
            throw new FrameException(FrameConstans.GET_MESSAGE_ERROR, e);
        }
    }

    @Override
    public void put(IServiceObject obj) throws FrameException {
        try {
            bufferQueue.put(obj);
        } catch (InterruptedException e) {
            LogUtil.getInstance().error(e);
            throw new FrameException(FrameConstans.PUT_MESSAGE_ERROR, e);
        }
    }

    @Override
    public int getNum() {
        return bufferQueue.size();
    }

    @Override
    public void clear() {
        bufferQueue.clear();
    }
}
