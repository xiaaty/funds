package com.gqhmt.tyzf.common.frame.common;

/**
 * Created by zhou on 2016/10/20.
 * Description: 状态接口
 */
public interface IStatus {
    /**
     * 获取状态boolean值
     * @return
     */
    boolean getStatus();

    /**
     * 获取错误编码
     * @return
     */
    String getErrCode();

    /**
     * 获取错误描述
     * @return
     */
    String getErrMsg();

    /**
     * 获取详细信息
     * @return
     */
    String getDetail();
}
