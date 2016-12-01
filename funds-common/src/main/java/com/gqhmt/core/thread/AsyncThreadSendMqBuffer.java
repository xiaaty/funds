package com.gqhmt.core.thread;

import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Filename:    com.gqhmt.core.thread.AsyncThreadSendMqBuffer
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/12/1 14:58
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/12/1  于泳      1.0     1.0 Version
 */
public class AsyncThreadSendMqBuffer {

    private static final int DATA_BUFFER_DEF_CAP = 2000;

    private final BlockingQueue<MessageConvertDto> bufferQueue;

    public AsyncThreadSendMqBuffer(){
        bufferQueue = new ArrayBlockingQueue<>(DATA_BUFFER_DEF_CAP);
    }

    public AsyncThreadSendMqBuffer(int cap){
        this.bufferQueue = new ArrayBlockingQueue<>(cap);
    }

    public boolean isEmpty(){
        return bufferQueue.isEmpty();
    }

    public int getNum(){
        return bufferQueue.size();
    }

    public void clear(){
        bufferQueue.clear();
    }

    public MessageConvertDto get() throws InterruptedException {
        try {
            return bufferQueue.take();
        } catch (InterruptedException e) {
            throw e;
        }
    }

    public void put(MessageConvertDto dto) throws InterruptedException {
        try {
            bufferQueue.put(dto);
        } catch (InterruptedException e) {
            throw e;
        }
    }


}
