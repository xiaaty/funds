package com.gqhmt.common.buffer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Filename:    com.gqhmt.common.buffer.Buffer
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/1/13 15:40
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/1/13  于泳      1.0     1.0 Version
 */
public class Buffer implements IBuffer {

    //本地缓存队列
    private BlockingQueue<IBufferData> bufferQueue;

    public Buffer(){
        new Buffer(BufferConstants.DATA_BUFFER_DEF_CAP);
    }

    public Buffer(int  theCap){
        bufferQueue = new ArrayBlockingQueue<IBufferData>(theCap);
    }

    @Override
    public IBufferData get() throws InterruptedException {
        return bufferQueue.take();
    }

    @Override
    public void put(IBufferData object) throws InterruptedException {
        bufferQueue.put(object);
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
