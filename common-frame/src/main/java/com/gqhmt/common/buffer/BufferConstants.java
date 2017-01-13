package com.gqhmt.common.buffer;

/**
 * Filename:    com.gqhmt.common.buffer.MsgBufferConstants
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/1/13 15:33
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/1/13  于泳      1.0     1.0 Version
 */
public class BufferConstants {

    /**
     * 默认构造
     */
    private BufferConstants() {
        throw new IllegalAccessError("Utility class");
    }

    // 数据缓存深度默认值
    public static final int DATA_BUFFER_DEF_CAP = 500;
}
