package com.gqhmt.quartz.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ThreadExecutor;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.service.BidTransferService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/7/13 10:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/7/13  于泳      1.0     1.0 Version
 */
public class BidTransferService {


    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    public void batchTransfer() throws FssException {
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listFile();//fuiouFtpOrderService.listNotUpload();
        for(FuiouFtpOrder fuiouFtpOrder:list){
            this.transfer(fuiouFtpOrder);
            System.out.println("fuiouFtp:upload:"+fuiouFtpOrder.getOrderNo()+":"+fuiouFtpOrder.getUploadStatus());
        }
    }


    public void transfer(FuiouFtpOrder fuiouFtpOrder) throws FssException {
        List<FuiouFtpColomField> list = fuiouFtpColomFieldService.getFuiouFtpColunm(fuiouFtpOrder.getOrderNo());
        for(FuiouFtpColomField fuiouFtpColomField : list){
            ThreadExecutor.execute(this.transferRunProcess(fuiouFtpColomField,fuiouFtpOrder));
        }

    }


    public Runnable transferRunProcess(final  FuiouFtpColomField field, final FuiouFtpOrder fuiouFtpOrder){

        field.setState(10890002);//排队状态
        try {
            fuiouFtpColomFieldService.update(field);
        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
        }

        Runnable thread = new Runnable(){

            @Override
            public void run() {
                field.setState(10890003);//执行状态
                try {
                    fuiouFtpColomFieldService.update(field);
                } catch (FssException e) {
                    LogUtil.error(this.getClass(),e);
                }
                //数据处理
                if(fuiouFtpOrder.getType() == 1){//满标

                }else if(fuiouFtpOrder.getType() == 2){//回款

                }else if(fuiouFtpOrder.getType() == 8){//出借赎回代偿退回

                }

                field.setState(10890004);//执行结束状态
                try {
                    fuiouFtpColomFieldService.update(field);
                } catch (FssException e) {
                    LogUtil.error(this.getClass(),e);
                }
            }
        };


        return thread;
    }




}
