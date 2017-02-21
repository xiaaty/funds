package com.gqhmt.common.base;

/**
 * Created by zhou on 2016/10/20.
 * Description: 基础状态类
 */
public class BasicStatus implements IStatus{

    //状态
    private boolean status = true;
    //状态明细
    private String detail = null;
    //错误编码
    private String errCode = null;
    //错误描述
    private String errMsg = null;

    /**
     * 无参构造
     */
    public BasicStatus(){
    }

    /**
     * 构造函数
     * @param status boolean 状态
     */
    public BasicStatus(boolean status){
        this.status = status;
    }

    /**
     * 构造函数
     * @param status boolean 状态
     * @param detail 状态明细
     */
    public BasicStatus(boolean status, String detail){
        this.status = status;
        this.detail = detail;
    }

    /**
     * 构造函数
     * @param status boolean 状态
     * @param detail 状态明细
     * @param errCode 错误编码
     * @param errMsg 错误描述
     */
    public BasicStatus(boolean status, String detail, String errCode, String errMsg){
        this.status = status;
        this.detail = detail;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * 获取状态boolean值
     * @return
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * 获取详细信息
     * @return
     */
    public String getDetail() {
        return this.detail;
    }

    /**
     * 获取错误编码
     * @return
     */
    public String getErrCode() {
        return this.errCode;
    }

    /**
     * 获取错误描述
     * @return
     */
    public String getErrMsg() {
        return this.errMsg;
    }
}
