package com.gqhmt.pay.fuiou.quartz;

import com.gqhmt.pay.fuiou.util.FtpClient;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.util.ThirdPartyType;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.service.FssChangeCardService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.fuiou.util.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.scheduling.annotation.Scheduled;
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
public class ChangeCardJob extends AJob{
/*
    @Resource
    public FssChangeCardService changeCardService;
    
    Config config=ConfigFactory.getConfigFactory().getConfig();
   
    private static boolean isRunning = false;

    @Scheduled(cron="0 0/10 8-21  * * * ")
    public void changeCard(){
        System.out.println("Change bank card job");
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

    private void queryDate() {
        List<FssChangeCardEntity> list = changeCardService.query(4);
        if(list == null || list.size() == 0){
            return;
        }

        for(FssChangeCardEntity t:list){
            this.queryDate(t);
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
            Response response =  Response.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_UPDATE_CARD_QUERY, ThirdPartyType.FUIOU,changeCardEntity);

            if( response != null ){
            	Response r  = (Response) response;
                String orderNo = (String) r.getMap().get("txn_ssn");
                String bankNo = (String) r.getMap().get("card_no");
                String state = (String) r.getMap().get("examine_st");
                String remark = (String) r.getMap().get("remark");
                this.changeCardService.fuiouCallback(orderNo,bankNo,state,remark);
            }

        }catch (Exception e){

        }
    }


    private void uploadImage(){
        List<FssChangeCardEntity> list = changeCardService.query(2);
        for(FssChangeCardEntity changeCardEntity:list){
            try {
                this.uploadImageFtp(changeCardEntity);
                changeCardService.uploadImageFtp(changeCardEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void uploadImageFtp(FssChangeCardEntity changeCardEntity){
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
        FTPClient ftp = new FtpClient(Integer.parseInt(port),userName,pwd,url);
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


    private void uploadData(){
        List<ChangeCardEntity> list = changeCardService.query(3);
        for(ChangeCardEntity changeCardEntity:list){
            try {
                this.uploadData(changeCardEntity);
                changeCardService.uploadData(changeCardEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadData(ChangeCardEntity changeCardEntity){
        System.out.println("提交数据到富友:"+changeCardEntity.getId());
        if(!config.isConnection()){
            changeCardEntity.setTradeState(4);
            return;
        }
        try{
            AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_UPDATE_CARD, ThirdPartyType.FUIOU,changeCardEntity);
            changeCardEntity.setTradeState(4);
        }catch (Exception e){
            LogUtil.error(this.getClass(),e);
            changeCardEntity.setTradeState(6);
            changeCardEntity.setRespCode("0001");
            changeCardEntity.setRespMsg(e.getMessage());

        }
    }

*/
}
