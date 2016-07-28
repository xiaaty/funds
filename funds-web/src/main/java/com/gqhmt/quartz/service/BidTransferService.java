package com.gqhmt.quartz.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ThreadExecutor;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.service.PaySuperByFuiou;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
@Service
public class BidTransferService {


    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;


    public void batchTransfer() throws FssException {
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listFile();//fuiouFtpOrderService.listNotUpload();
        for(FuiouFtpOrder fuiouFtpOrder:list){
            this.transfer(fuiouFtpOrder);
            System.out.println("fuiouFtp:upload:"+fuiouFtpOrder.getOrderNo()+":"+fuiouFtpOrder.getUploadStatus());
        }
    }


    public void transfer(FuiouFtpOrder fuiouFtpOrder) throws FssException {
        Runnable runnable = this.transferRunProcess(fuiouFtpOrder);
        if(runnable != null ){
            ThreadExecutor.execute(runnable);
        }

    }

    public void transfer(final  FuiouFtpColomField field, final FuiouFtpOrder fuiouFtpOrder){

        if(field.getState() != 10890001){
            return;
        }
        String  fromUserName = field.getFromUserName();
        String  toUserName = field.getToUserName();
        BigDecimal amt  = field.getAmt();
        String contractNo = field.getContractNo();
        String newOrderType = "";
        if(fuiouFtpOrder.getType() == 1){//满标
            newOrderType = "1109";
        }else if(fuiouFtpOrder.getType() == 2){//回款
            newOrderType = "1110";
        }else if(fuiouFtpOrder.getType() == 3){//
            newOrderType = "1114";
        }else if(fuiouFtpOrder.getType() == 4){
            newOrderType = "1113";
        } else if(fuiouFtpOrder.getType() == 8){
            newOrderType = "1107";
        }

        FundAccountEntity fromAccount =fundAccountService.getFundAccount(fromUserName, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        FundAccountEntity toAccount  = fundAccountService.getFundAccount(toUserName,GlobalConstants.ACCOUNT_TYPE_PRIMARY);


        try {
            CommandResponse response  = paySuperByFuiou.transerer(fromAccount,toAccount,amt,GlobalConstants.ORDER_TRANSFER_BATCH,field.getId(),GlobalConstants.ORDER_TRANSFER_BATCH,newOrderType,null,field.getLendNo(),null,field.getLoanCustId(),field.getLoanNo(),field.getContractNo());

            FundOrderEntity fundOrderEntity = response.getFundOrderEntity();
            field.setFeildOrderNo(fundOrderEntity.getOrderNo());
            field.setReturnCode(response.getThirdReturnCode());
            field.setReturnMsg(response.getMsg());
            field.setState(10890004);//执行结束状态
        } catch (FssException e) {
            field.setReturnCode(e.getMessage());
            field.setReturnMsg(e.getMessage());
            field.setState(10890004);//执行结束状态
        }
        try {
            fuiouFtpColomFieldService.update(field);
        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
        }


    }


    public Runnable transferRunProcess(final FuiouFtpOrder fuiouFtpOrder){
        try {
            fuiouFtpOrder.setFileSize(1);
            fuiouFtpOrder.setFileStatus(2);
            fuiouFtpOrder.setUploadStatus(3);
            fuiouFtpOrderService.update(fuiouFtpOrder);
            fuiouFtpOrder.setDownloadStatus(4);
            Thread.sleep(1000);
        }catch (FssException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runnable thread = new Runnable(){

            @Override
            public void run() {

                try {
                    List<FuiouFtpColomField> list = fuiouFtpColomFieldService.getFuiouFtpColunm(fuiouFtpOrder.getOrderNo());
                    for(FuiouFtpColomField fuiouFtpColomField : list){
                        transfer(fuiouFtpColomField,fuiouFtpOrder);
                    }

                } catch (FssException e) {
                    e.printStackTrace();
                }
            }
        };


        return thread;
    }




}
