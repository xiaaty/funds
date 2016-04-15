package com.gqhmt.quartz.job.account;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.service.FssChangeCardService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.command.AccountCommandResponse;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.pay.fuiou.util.FtpClient;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.quartz.job.SupperJob;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Filename:    com.fuiou.quartz.ChangeCardJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/4 10:24
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/4  于泳      1.0     1.0 Version
 */
@Component
public class ChangeCardJob extends SupperJob {

    @Resource
    public FssChangeCardService changeCardService;
    
    @Resource
    public PaySuperByFuiou paySuperByFuiou;
    
    @Resource
    public FundAccountService fundAccountService;
   
    
//    private static boolean isRunning = false;

    /*@Scheduled(cron="0 0/10 8-21  * * * ")*/
//    @Scheduled(cron="0 0/1 *  * * * ")
    public void changeCard() throws PayChannelNotSupports{
        System.out.println("变更银行卡跑批");
        if(!isIp("upload")){
            return;
        }

        if(isRunning) return;
        isRunning = true;

        try {
            changeCardService.autoPassChangeCard();             //自动审批

            this.uploadImage();                                 //上传图片

            this.uploadData();                                  //传数据到富友

            this.queryDate();

            changeCardService.sycnChangeCardInfoBySucess();     //成功结果同步银行卡信息

            changeCardService.sycnChangeCardInfoByFaile();      //失败结果同步银行卡

            changeCardService.syncBusiness();                   //回调到业务系统

        }catch (Exception e){
            LogUtil.error(this.getClass(),e);

        }finally {
            isRunning = false;
        }
    }

    private void queryDate() throws FssException {
        List<FssChangeCardEntity> list = changeCardService.queryByTradeState(4);
        if(list == null || list.size() == 0){
            return;
        }

        for(FssChangeCardEntity t:list){
            this.queryDate(t);
            t.setTradeState(5);
            changeCardService.update(t);
        }
    }
    
    
    private void queryDate(FssChangeCardEntity changeCardEntity){
        Date crDate = changeCardEntity.getCreateTime();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,-30);

        Calendar crDateCal = Calendar.getInstance();
        crDateCal.setTime(crDate);

        if(cal.compareTo(crDateCal)<1){
            System.out.println("刚刚提交富友,不需要轮询结果");
            return;
        }
        System.out.println("轮询结果");
        try {
        	FundAccountEntity fundAccountEntity= fundAccountService.getFundAccount(Long.valueOf(changeCardEntity.getCustId().toString()), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        	AccountCommandResponse response = paySuperByFuiou.changeCardResult(fundAccountEntity,changeCardEntity.getOrderNo(),Long.valueOf(fundAccountEntity.getBusiType()));
            if( response != null ){
            	CommandResponse r  = (CommandResponse) response;
                String orderNo = (String) r.getMap().get("txn_ssn");
                String bankNo = (String) r.getMap().get("card_no");
                String state = (String) r.getMap().get("examine_st");
                String remark = (String) r.getMap().get("remark");
                this.changeCardService.fuiouCallback(orderNo,bankNo,state,remark);
            }

        }catch (Exception e){

        }
    }


    private void uploadImage() throws FssException{
        List<FssChangeCardEntity> list = changeCardService.queryByTradeState(2);
        for(FssChangeCardEntity changeCardEntity:list){
            try {
                this.uploadImageFtp(changeCardEntity);
                changeCardService.uploadImageFtp(changeCardEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void uploadImageFtp(FssChangeCardEntity changeCardEntity) throws PayChannelNotSupports{
    	Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        System.out.println("上传图片:"+changeCardEntity.getId());
        if(!config.isConnection()){
            changeCardEntity.setTradeState(3);
            return;
        }

        String url = (String)config.getValue("ftp.url.value");
        String port = (String)config.getValue("ftp.port.value");
        String userName = (String)config.getValue("ftp.userName.value");
        String pwd = (String)config.getValue("ftp.pwd.value");
        String msd = (String)config.getValue("public.mchnt_cd.value");
        FtpClient ftp = new FtpClient(Integer.parseInt(port),userName,pwd,url);
        Date dateTime  = new Date();
        String date = String.format("%tY",dateTime)+ String.format("%tm",dateTime)+ String.format("%td",dateTime);
        try {
            ftp.mkfir("/ImageUpload/"+date);
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
        }
        File toFile = new File(changeCardEntity.getFilePath());
        String imagePath = changeCardEntity.getFilePath();
        String ext  = imagePath.substring(imagePath.lastIndexOf("."));
        String fileName="/ImageUpload/"+date+"/"+msd+""+changeCardEntity.getCertNo()+date+ext.toLowerCase();
        boolean flag = ftp.uploadFile(toFile,fileName);        //上传文件

        if(flag){
            changeCardEntity.setTradeState(3);
        }
    }


    private void uploadData() throws FssException{
        List<FssChangeCardEntity> list = changeCardService.queryByTradeState(3);
        for(FssChangeCardEntity changeCardEntity:list){
            try {
                this.uploadData(changeCardEntity);
                changeCardService.uploadData(changeCardEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadData(FssChangeCardEntity changeCardEntity) throws PayChannelNotSupports{
    	Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        System.out.println("提交数据到富友:"+changeCardEntity.getId());
        if(!config.isConnection()){
            changeCardEntity.setTradeState(4);
            return;
        }
        try{
//            AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_UPDATE_CARD, ThirdPartyType.FUIOU,changeCardEntity);
            changeCardEntity.setTradeState(4);
        }catch (Exception e){
            LogUtil.error(this.getClass(),e);
            changeCardEntity.setTradeState(6);
            changeCardEntity.setRespCode("0001");
            changeCardEntity.setRespMsg(e.getMessage());

        }
    }

}
