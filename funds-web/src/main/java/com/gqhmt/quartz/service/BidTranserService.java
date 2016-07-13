package com.gqhmt.quartz.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.quartz.service.BidTranserService
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
public class BidTranserService {


    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    public void batchTracser() throws FssException {
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listFile();//fuiouFtpOrderService.listNotUpload();
        for(FuiouFtpOrder fuiouFtpOrder:list){
            this.transer(fuiouFtpOrder);
            System.out.println("fuiouFtp:upload:"+fuiouFtpOrder.getOrderNo()+":"+fuiouFtpOrder.getUploadStatus());
        }
    }


    public void transer(FuiouFtpOrder fuiouFtpOrder) throws FssException {
        List<FuiouFtpColomField> list = fuiouFtpColomFieldService.getFuiouFtpColunm(fuiouFtpOrder.getOrderNo());

    }




}
