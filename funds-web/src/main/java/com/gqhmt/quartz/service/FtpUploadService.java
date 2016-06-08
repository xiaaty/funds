package com.gqhmt.quartz.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouUploadFile;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpOrderService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouUploadFileService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.pay.fuiou.util.FtpClient;
import com.gqhmt.pay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Filename:    com.fuiou.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/10 16:05
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */
@Service
public class FtpUploadService {

    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @Resource
    private FuiouFtpOrderService fuiouFtpOrderService;

    @Resource
    private FuiouUploadFileService fuiouUploadFileService;

    public void upload()  throws FssException{
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listFile();//fuiouFtpOrderService.listNotUpload();
        for(FuiouFtpOrder fuiouFtpOrder:list){
            this.upload(fuiouFtpOrder);
            System.out.println("fuiouFtp:upload:"+fuiouFtpOrder.getOrderNo()+":"+fuiouFtpOrder.getUploadStatus());
        }
    }

    /**
     * 上传文件
     * @param fuiouFtpOrder
     * @throws FssException 
     */
    public void upload(FuiouFtpOrder fuiouFtpOrder) throws FssException{
      Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        List<FuiouFtpColomField> list = fuiouFtpColomFieldService.getFuiouFtpColunm(fuiouFtpOrder.getOrderNo());
//        if(list.size()>=4000)
        String limitString = (String)config.getValue("upload.fileSize.value");
        long limit = Long.parseLong(limitString);
        if(list.size()<= limit){
            boolean flag  = this.upload(fuiouFtpOrder,list);
            fuiouFtpOrder.setUploadStatus(flag?3:1);
            fuiouFtpOrder.setFileSize(1);
            fuiouFtpOrderService.update(fuiouFtpOrder);
            return;
        }
        fuiouFtpOrder.setFileSize((int)(list.size()/limit)+1);
        List<FuiouFtpColomField> uplpoadList = new ArrayList<>();
        int success = 0;

        int i = 0;
        for(FuiouFtpColomField fuiouFtpColomField:list){
            uplpoadList.add(fuiouFtpColomField);
            i++;
            if(i == limit){
                i = 0;
                boolean flag  = this.upload(fuiouFtpOrder,uplpoadList);
                uplpoadList = new ArrayList<>();
                if(flag){
                    success++;
                }
            }
        }
        if(i<limit && uplpoadList.size()>0){
            boolean flag  = this.upload(fuiouFtpOrder,uplpoadList);
            if(flag){
                success++;
            }
        }
        if(success == 0){
            return;
        }
        fuiouFtpOrder.setUploadStatus(success == fuiouFtpOrder.getFileSize()?3:2);
        fuiouFtpOrderService.update(fuiouFtpOrder);
    }

    public boolean upload(FuiouFtpOrder fuiouFtpOrder, List<FuiouFtpColomField> list)  throws FssException{
    	Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
    	String businessCode = "PW03";
        String mCode = (String)config.getValue("public.mchnt_cd.value");
        String sysdate= getSysDate();
        String fileSeqNo = getFileSeqNo();
        int i = 1;
        BigDecimal sum = BigDecimal.ZERO;
        List<String> fieldList = new ArrayList<>();
        for(FuiouFtpColomField fuiouFtpColomField:list){
            fuiouFtpColomField.setSeqNo(getSeqNo(i++));
            fuiouFtpColomField.setFileId(Long.parseLong(sysdate + fileSeqNo));
            fieldList.add(addColomFieldForSettle(fuiouFtpColomField, fuiouFtpOrder.getType()));
            sum = sum.add(fuiouFtpColomField.getAmt());
            fuiouFtpColomField.setState(2);
            //fuiouFtpColomFieldService.update(fuiouFtpColomField);
        }
        FuiouUploadFile file = fuiouUploadFileService.add(businessCode, mCode, list.size(), sysdate, fileSeqNo, sum, fuiouFtpOrder.getOrderNo());
        fuiouFtpColomFieldService.updateList(list);
        String filePath = this.saveFileTran(fieldList,businessCode,sysdate,fileSeqNo,mCode,sum,list.size());
        //boolean flag = uploadFileToFtp(filePath,businessCode,sysdate,fileSeqNo);
        fuiouFtpOrder.setFileStatus(2);
        fuiouFtpOrderService.update(fuiouFtpOrder);
        return true;
    }

    /**
     * 生成上传报文
     * @param fieldList
     * @param businessCode
     * @param sysdate
     * @param fileSeqNo
     * @param mCode
     * @param sum
     * @param size
     * @return
     */
    public String saveFileTran(List<String> fieldList,String businessCode,String sysdate,String fileSeqNo,String mCode,BigDecimal sum,int size ){
        String path = getClassPath();
        File filepath  = new File(path+"/tmp/upload");
        String fileName= filepath+"/"+businessCode+"_"+sysdate+"_"+fileSeqNo+".txt";
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bufferedWriter = null;
        try {
            String title = businessCode+"|"+sysdate+"|"+mCode+"|"+size+"|"+sum.toPlainString()+"|"+fileSeqNo;
            fos = new FileOutputStream(fileName);
            osw = new OutputStreamWriter(fos, "GBK");
            bufferedWriter = new BufferedWriter(osw);
            StringBuffer signTmp = new StringBuffer(title);
//
            bufferedWriter.write(title);
            for (String field:fieldList){
                signTmp.append(field);
                bufferedWriter.newLine();
                bufferedWriter.write(field);
            }
            bufferedWriter.newLine();
            String toSign =signTmp.toString();
            String sign = SecurityUtils.sign(toSign);
            bufferedWriter.write(sign);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(osw != null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return fileName;
    }


    public void uploadFileToFtp() throws FssException{
    	Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        List<FuiouFtpOrder> list = fuiouFtpOrderService.listNotUpload();
        Map<String,Integer> resultMap = new HashMap<>();
        List<FuiouUploadFile> listUpload = fuiouUploadFileService.list(1);
        for(FuiouUploadFile fuiouUploadFile:listUpload){
            if(config.isConnection() == false){
                fuiouUploadFile.setState(2);
                resultMap.put(fuiouUploadFile.getOrderNo(),(resultMap.get(fuiouUploadFile.getOrderNo())==null?0:resultMap.get(fuiouUploadFile.getOrderNo()))+1);
                fuiouUploadFileService.update(fuiouUploadFile);
                continue;
            }
            String businessCode = fuiouUploadFile.getBusinessCode();
            String sysdate = fuiouUploadFile.getEntrustDate();
            String fileSeqNo = fuiouUploadFile.getNo();
            String path = getClassPath();
            File filepath  = new File(path+"/tmp/upload");
            String fileName= filepath+"/"+businessCode+"_"+sysdate+"_"+fileSeqNo+".txt";
            boolean flag = uploadFileToFtp(fileName,businessCode,sysdate,fileSeqNo);
            if(flag){
                fuiouUploadFile.setState(2);
                resultMap.put(fuiouUploadFile.getOrderNo(),(resultMap.get(fuiouUploadFile.getOrderNo())==null?0:resultMap.get(fuiouUploadFile.getOrderNo()))+1);
                fuiouUploadFileService.update(fuiouUploadFile);
            }
        }
        for(FuiouFtpOrder fuiouFtpOrder:list){
            Integer successSize = resultMap.get(fuiouFtpOrder.getOrderNo());
            if(successSize != null && successSize == fuiouFtpOrder.getFileSize()){
                fuiouFtpOrder.setUploadStatus(3);
                fuiouFtpOrderService.update(fuiouFtpOrder);
            }
        }
    }

    /**
     * 上传文件到ftp
     */
    public boolean uploadFileToFtp(String fileName,String businessCode,String sysdate,String fileSeqNo)  throws PayChannelNotSupports{
    	Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        boolean  flag = false;
        File toFile = new File(fileName);
        String url = (String)config.getValue("ftp.url.value");
        String port = (String)config.getValue("ftp.port.value");
        String userName = (String)config.getValue("ftp.userName.value");
        String pwd = (String)config.getValue("ftp.pwd.value");
        FtpClient ftp = new FtpClient(Integer.parseInt(port),userName,pwd,url);
        flag = ftp.uploadFile(toFile,"/upload/"+businessCode+"_"+sysdate+"_"+fileSeqNo+".txt");        //上传文件
        return flag;
    }


    public String addColomFieldForSettle(FuiouFtpColomField field,int type){
        StringBuffer sb = new StringBuffer();
        sb.append(field.getSeqNo()).append("|").append(field.getFromUserName()).append("|").append(field.getFromCnUserName())
                .append("|").append("0").append("|").append(field.getToUserName()).append("|").append(field.getToCnUserName())
                .append("|").append("0").append("|").append(field.getAmt().toPlainString())
                .append("|").append("").append("|").append(type == 1?field.getContractNo()!=null?field.getContractNo():"":"");
        return sb.toString();
    }

    public String getFileSeqNo() {
        Double d = Math.random();
        d = d*10000;
        return String.format("%04d",d.longValue());
    }

    public String getSysDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    public String getSeqNo(int i) {
        return String.format("%04d",i);
    }

    public String getClassPath(){
        String path = this.getClass().getResource("").getPath();
        String className  = this.getClass().getName();
        String packge = className.substring(0,className.lastIndexOf(".")).replace(".","/");
        if(path.lastIndexOf(packge)>0){
            return path.substring(0,path.lastIndexOf(packge));
        }
        return path;
    }
}
