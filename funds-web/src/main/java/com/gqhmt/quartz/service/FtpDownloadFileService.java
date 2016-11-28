package com.gqhmt.quartz.service;

import com.google.common.collect.Lists;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoFileEntity;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoFileService;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoService;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.fss.architect.accounting.service.FssImportDataService;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouUploadFile;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouUploadFileService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.fuiou.util.FtpClient;
import com.gqhmt.pay.fuiou.util.SecurityUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
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
 * Create at:   2015/5/10 16:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */

@Service
public class FtpDownloadFileService {
    @Resource
    private FuiouUploadFileService fuiouUploadFileService;

    @Resource
    private FuiouFtpColomFieldService fuiouFtpColomFieldService;

    @Resource
    private FuiouAccountInfoService fuiouAccountInfoService;

    @Resource
    private FuiouAccountInfoFileService fuiouAccountInfoFileService;

    @Resource
    private FssCheckAccountingService fssCheckAccountingService;

    @Resource
    private FssImportDataService fssImportDataService;

    //判断文件是否存在
    private boolean haveFile = true;

    public boolean isHaveFile() {
        return haveFile;
    }

    public void setHaveFile(boolean haveFile) {
        this.haveFile = haveFile;
    }

    public void downFile()  throws NumberFormatException, FssException {
    	Config config= ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        List<FuiouUploadFile> list = this.fuiouUploadFileService.list(2);
        for(FuiouUploadFile file:list){
            if(config.isConnection() == false){
                file.setState(3);
                fuiouUploadFileService.update(file);
                Map<String,FuiouFtpColomField> fields = this.fuiouFtpColomFieldService.getFuiouFtpColunm(Long.parseLong(file.getEntrustDate() + file.getNo()));
                List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
                Collection<FuiouFtpColomField> collection = fields.values();
                for(FuiouFtpColomField field  : collection){
                    String returnCode = "0000";
                    String returnMsg = "操作成功";
                    field.setState(4);
                    field.setReturnCode(returnCode);
                    field.setReturnMsg(returnMsg);
                    fuiouFtpColomFields.add(field);
                }
                fuiouFtpColomFieldService.updateCollection(collection);
                continue;
            }
            if(download(file)){
                continue;
            }
        }
    }

    public boolean download(FuiouUploadFile file) throws FssException {
        boolean isReject = downloadReject(file);
        if(isReject)  return true;      //确认是否获取到拒盘文件，处理拒盘结果
        boolean isReturn = downloadReturn(file);
        if(!isReturn)  return true;  //文件未取回，稍后重新获取文件，超过一天得文件将不再获取,此处未处理，稍后处理
        //处理 结果
        parseFile(file);
        return false;
    }




    /**
     * 验证拒盘文件
     * @param file
     * @return  存在拒盘文件，true，不存在，false
     * @throws FssException
     */
    private boolean downloadReject(FuiouUploadFile file)  throws FssException {
    	Config config= ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        String url = (String)config.getValue("ftp.url.value");
        String port = (String)config.getValue("ftp.port.value");
        String userName = (String)config.getValue("ftp.userName.value");
        String pwd = (String)config.getValue("ftp.pwd.value");
        FtpClient ftp = new FtpClient(Integer.parseInt(port),userName,pwd,url);

        boolean flag =  ftp.exits("/reject/"+file.getBusinessCode()+"_"+file.getEntrustDate()+"_"+file.getNo()+"_reject.txt");

        if(!flag)   return false;

        String path = getClassPath();
        File filepath  = new File(path+"/tmp/reject");
        String fileName= filepath+"/"+file.getBusinessCode()+"_"+file.getEntrustDate()+"_"+file.getNo()+"_reject.txt";
        flag = ftp.getFile("/reject/"+file.getBusinessCode()+"_"+file.getEntrustDate()+"_"+file.getNo()+"_reject.txt",fileName);

        if(!flag)   return false;
        file.setState(5);
        fuiouUploadFileService.update(file);
        this.fuiouFtpColomFieldService.updateByFileSeqId(file.getEntrustDate() + file.getNo(), "9999","文件拒盘");
        return flag;
    }

    /**
     * ftp下载回盘文件，存在失败可能，失败重新下载
     * @param file
     * @return
     * @throws FssException
     */
    public boolean downloadReturn(FuiouUploadFile file) throws FssException {
    	Config config= ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        String url = (String)config.getValue("ftp.url.value");
        String port = (String)config.getValue("ftp.port.value");
        String userName = (String)config.getValue("ftp.userName.value");
        String pwd = (String)config.getValue("ftp.pwd.value");
        FtpClient ftp = new FtpClient(Integer.parseInt(port),userName,pwd,url);
        boolean flag = ftp.exits("/return/" + file.getBusinessCode() + "_" + file.getEntrustDate() + "_" + file.getNo() + "_over.txt");
        if(!flag){
            return false;
        }
        String path = getClassPath();
        File filepath  = new File(path+"/tmp/return");
        String fileName= filepath+"/"+file.getBusinessCode()+"_"+file.getEntrustDate()+"_"+file.getNo()+"_over.txt";
        flag = ftp.getFile("/return/" + file.getBusinessCode() + "_" + file.getEntrustDate() + "_" + file.getNo() + "_over.txt",fileName);
        file.setState(3);
        fuiouUploadFileService.update(file);
        return true;
    }

    /**
     * 对已下载回盘文件处理
     * @param file
     * @throws FssException
     */
    private void parseFile(FuiouUploadFile file) throws FssException {
        String path = getClassPath();
        File filepath  = new File(path+"/tmp/return");
        String fileName= filepath+"/"+file.getBusinessCode()+"_"+file.getEntrustDate()+"_"+file.getNo()+"_over.txt";
        File localFile = new File(fileName);
        try {
            InputStream is = new FileInputStream(localFile);
            InputStreamReader inputStreamReader = new InputStreamReader(is,"GBK");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> returnList = new ArrayList();
            while(bufferedReader.ready()){
                returnList.add(bufferedReader.readLine());
            }
            boolean flag = check(returnList);
            if(flag == false){
                System.out.print("验签失败");
                this.fuiouFtpColomFieldService.updateByFileSeqId(file.getEntrustDate() + file.getNo(), "9999","验签失败");
                return;
            }
            parseFileResult(file,returnList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 结果处理，返回结果更新数据库
     * @param file
     * @param list
     * @throws FssException
     * @throws NumberFormatException
     */
    private void parseFileResult(FuiouUploadFile file, List<String> list) throws NumberFormatException, FssException {
        Map<String,FuiouFtpColomField> fields = this.fuiouFtpColomFieldService.getFuiouFtpColunm(Long.parseLong(file.getEntrustDate() + file.getNo()));
        if(fields == null || fields.size()==0){
            return;
        }
        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
        for(int i = 1;i<list.size()-1;i++){
            String s  = list.get(i);
            String seqNo = "";
            String returnCode = "";
            String returnMsg = "";
            String[] tmp  = s.split("\\|");
            seqNo  = tmp[0];
            if("PW03".equals(file.getBusinessCode())){
                returnCode = tmp[10];
                returnMsg  = tmp[11];
            }else if("PWDJ".equals(file.getBusinessCode())){
                returnCode = tmp[5];
                returnMsg  = tmp[6];
            }else if("PWJD".equals(file.getBusinessCode())){
                returnCode = tmp[5];
                returnMsg  = tmp[6];
            }
            FuiouFtpColomField field = fields.get(seqNo);
            field.setState(4);
            field.setReturnCode(returnCode);
            field.setReturnMsg(returnMsg);
            fuiouFtpColomFields.add(field);
        }
        try {
			fuiouFtpColomFieldService.updateList(fuiouFtpColomFields);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }


    /**
     * 验签
     * @param list
     * @return
     */
    private boolean check(List<String> list){
        if(list == null || list.size()<=1){
            return false;
        }
        String sign  = list.get(list.size()-1);
        StringBuffer value = new StringBuffer();
        for(int i = 0;i<list.size()-1;i++){
            value.append(list.get(i));
        }
        return SecurityUtils.verifySign(value.toString(), sign);
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

    /**
     * ftp下载金账户对账文件，存在失败可能，失败重新下载
     * @param file  // 需要 只 TradingTime 和 BusinessCode两个参数
     * @return
     * @throws FssException
     */
    public boolean downloadFuiouAccount(FuiouAccountInfoFileEntity file) throws FssException {
        Config config= ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        String url = (String)config.getValue("ftp.url.value");
        String port = (String)config.getValue("ftp.port.value");
        String userName = (String)config.getValue("ftp.userName.value");
        String pwd = (String)config.getValue("ftp.pwd.value");
        FtpClient ftp = new FtpClient(Integer.parseInt(port),userName,pwd,url);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Date createTime = file.getCreateTime();
        String dateStr = sdf.format(createTime);

        String fileName = file.getTradeType()  + dateStr +  ".txt";
        String filePath = "/account/" + dateStr + "/" + fileName;

        boolean flag = ftp.exits(filePath);

        if(!flag){
            return false;
        }

        String path = getClassPath();
        File localFile  = new File(path+"/tmp/account/"+dateStr);
        if(localFile.exists()){
            localFile.mkdirs();
        }

        String localFilePath = localFile+"/"+ fileName;
        flag = ftp.getFile(filePath,localFilePath);
        return flag;
    }


    /**
     * 对已下载金账户对账文件处理
     * @param file
     * @throws FssException
     */
    public boolean parseFileFuiouAcount(FuiouAccountInfoFileEntity file) {
        boolean parseType = false;

        List<String> returnList = new ArrayList();
        Date createTime = file.getCreateTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");

        String date = sdf.format(createTime);

        String path = getClassPath();
        File filepath  = new File(path+"/tmp/account/"+date);


        String fileName= filepath+"/"+file.getTradeType()+date+".txt";
        File localFile = new File(fileName);

        try {
            InputStream is = new FileInputStream(localFile);
            InputStreamReader inputStreamReader = new InputStreamReader(is,"GBK");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while(bufferedReader.ready()){
                returnList.add(bufferedReader.readLine());
            }
            if(CollectionUtils.isEmpty(returnList)){
                return parseType;
            }

            file.setFilePath(fileName);
            SaveOrUpdateResult(file,returnList);
            parseType = true;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FssException e) {
            e.printStackTrace();
        }
        return parseType;
    }

    /**
     * 结果处理，返回结果添加或者跟新数据库
     * @param file
     * @param list
     * @throws FssException
     * @throws NumberFormatException
     */
    private void SaveOrUpdateResult(FuiouAccountInfoFileEntity file, List<String> list) throws NumberFormatException, FssException, ParseException {

        if(CollectionUtils.isEmpty(list)){
            return;
        }

        int fileId = 0;
        if(file != null && file.getId() != 0){
            //判断已传过来的是否是有已存在的对象,如果存在则更新
            fuiouAccountInfoFileService.updateFuiouAccountInfoFileEntity(file);
            fileId = file.getId();
        }else{

                // 如果不是存在的对象， 数据库也查不到则添加
                file.setBooleanType("-1");
                fuiouAccountInfoFileService.addFuiouAccountInfoFileEntity(file);
                fileId = file.getId();
        }

        List<FuiouAccountInfoEntity> accInfoList = new ArrayList<FuiouAccountInfoEntity>();
        List<FssCheckAccountingEntity> checkLists= Lists.newArrayList();
        for(String str:list){
            FuiouAccountInfoEntity accountInfo = new FuiouAccountInfoEntity();
            accountInfo.setAccountInfo(str, file.getTradeType());
            if(fileId!=0){
                accountInfo.setFileId(fileId);
            }
            FssCheckAccountingEntity checkAccountingEntity= fssCheckAccountingService.createChecking(accountInfo,file.getTradeType());

            accInfoList.add(accountInfo);
            checkLists.add(checkAccountingEntity);
        }
        //循环便利集合得到客户表id并添加进对象
        List<FssCheckAccountingEntity> enList= fssImportDataService.list(checkLists);
        fuiouAccountInfoService.addFuiouAccountInfoList(accInfoList);
        fssCheckAccountingService.insertCheckList(checkLists);
        file.setBooleanType("1");
        fuiouAccountInfoFileService.updateFuiouAccountInfoFileEntity(file);
        LogUtil.info(this.getClass(),"抓取文件：" + file.getTradeType() + new SimpleDateFormat("yyyyMMdd").format(file.getCreateTime())+".txt 成功");
    }

}
