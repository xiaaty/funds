package com.gqhmt.common.buffer;

/**
 * Filename:    com.gqhmt.common.buffer.IBuffer
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/1/13 15:29
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/1/13  于泳      1.0     1.0 Version
 */
public interface IBuffer {

    /**
     *
     * @return
     */
    IBufferData get() throws InterruptedException;

    void put(IBufferData object) throws InterruptedException;

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
