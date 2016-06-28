package com.gqhmt.quartz.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.account.service.FuiouAccountInfoService;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouUploadFile;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouUploadFileService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.fuiou.util.FtpClient;
import com.gqhmt.pay.fuiou.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
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

    public void downFile()  throws NumberFormatException, FssException{
    	Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
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

    public boolean download(FuiouUploadFile file) throws FssException{
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
    private boolean downloadReject(FuiouUploadFile file)  throws FssException{
    	Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
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
    public boolean downloadReturn(FuiouUploadFile file) throws FssException{
    	Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
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
    private void parseFile(FuiouUploadFile file) throws FssException{
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
    private void parseFileResult(FuiouUploadFile file,List<String> list) throws NumberFormatException, FssException{
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
    public boolean downloadFuiouAccount(FuiouAccountInfoEntity file) throws FssException{
        Config config=ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        String url = (String)config.getValue("ftp.url.value");
        String port = (String)config.getValue("ftp.port.value");
        String userName = (String)config.getValue("ftp.userName.value");
        String pwd = (String)config.getValue("ftp.pwd.value");
        FtpClient ftp = new FtpClient(Integer.parseInt(port),userName,pwd,url);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(file.getTradingTime());
        String url1 = "/account/" + file.getBusinessCode()  + date +  ".txt";
        boolean flag = ftp.exits(url1);
        if(!flag){
            return false;
        }
        String path = getClassPath();
        File filepath  = new File(path+"/tmp/account");
        String fileName= filepath+"/"+ file.getBusinessCode()  + date +  ".txt";
        flag = ftp.getFile("/account/" + file.getBusinessCode()  + date +  ".txt",fileName);
        parseFileFuiouAcount(file);
        return true;
    }



    /**
     * 重载的方法 ， 通过String 类型的 businessCode, 和Date 类型的 TradingTime
     * ftp下载金账户对账文件，存在失败可能，失败重新下载
     * @param BusinessCode
     * @param TradingTime
     * @return
     * @throws FssException
     */
    public boolean downloadFuiouAccount(String BusinessCode, Date TradingTime) throws FssException{
        FuiouAccountInfoEntity file = new FuiouAccountInfoEntity();
        file.setBusinessCode(BusinessCode);
        file.setTradingTime(TradingTime);
        downloadFuiouAccount(file);
        return true;
    }

    /**
     * 重载的方法 ， 通过String 类型的 businessCode, 和String 类型的 TradingTime
     * ftp下载金账户对账文件，存在失败可能，失败重新下载
     * @param BusinessCode
     * @param TradingTime
     * @return
     * @throws FssException
     */
    public boolean downloadFuiouAccount(String BusinessCode, String TradingTime) throws FssException, ParseException {
        FuiouAccountInfoEntity file = new FuiouAccountInfoEntity();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        java.util.Date date=sdf.parse(TradingTime);

        file.setBusinessCode(BusinessCode);
        file.setTradingTime(date);
        downloadFuiouAccount(file);
        return true;
    }

    /**
     * 对已下载金账户对账文件处理
     * @param file
     * @throws FssException
     */
    private void parseFileFuiouAcount(FuiouAccountInfoEntity file) throws FssException{
        String path = getClassPath();
        File filepath  = new File(path+"/tmp/account");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(file.getTradingTime());

        String fileName= filepath+"/"+file.getBusinessCode()+date+".txt";
        File localFile = new File(fileName);
        try {
            InputStream is = new FileInputStream(localFile);
            InputStreamReader inputStreamReader = new InputStreamReader(is,"GBK");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> returnList = new ArrayList();
            while(bufferedReader.ready()){
                returnList.add(bufferedReader.readLine());
            }
            SaveOrUpdateResult(file,returnList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * 结果处理，返回结果添加或者跟新数据库
     * @param file
     * @param list
     * @throws FssException
     * @throws NumberFormatException
     */
    private void SaveOrUpdateResult(FuiouAccountInfoEntity file,List<String> list) throws NumberFormatException, FssException, ParseException {

        List<FuiouFtpColomField> fuiouFtpColomFields = new ArrayList<>();
        List<String> strList = new ArrayList<String>();
        String str = "";
        for(int i = 0;i<list.size();i++) {
            String s = list.get(i);
            str += s;
        }
        String[] aStr = str.split("\\|");
        strList = Arrays.asList(aStr);

        String tradeType = null;
        if(strList == null && strList.get(0) == null && file.getBusinessCode() == null){
            return;
        }
        int size = strList.size();
        tradeType = file.getBusinessCode();
        int num = 0;
        if("DJJD".equals(tradeType) && size == 9){
            num = 1;
        }else if("ZZ".equals(tradeType) && size == 11){
            num = 2;
        }else if("HB".equals(tradeType) && size == 11){
            num = 3;
        }else if("WTCZ".equals(tradeType) && size == 10){
            num = 4;
        }else if("WTTX".equals(tradeType) && size == 10){
            num = 5;
        }else if("YSQ".equals(tradeType) && size == 10){
            num = 6;
        }else{
            System.out.println("格式错误");
            return;
        }

        switch(num){
            case 1 :
                file.setTradeType("DJJD");
                file.setBusinessCode(strList.get(0));
                file.setTradeSources(strList.get(1));
                file.setBatch(strList.get(2));
                file.setTradingTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(strList.get(3)));
                file.setTransactionAmount(new BigDecimal(strList.get(4)));
                file.setUserAccount(strList.get(5));
                file.setUserName(strList.get(6));
                file.setRemark(strList.get(7));
                file.setReturnNum(strList.get(8));
                break;
            case 2 :
                file.setTradeType("ZZ");
                file.setBusinessCode(strList.get(0));
                file.setTradeSources(strList.get(1));
                file.setBatch(strList.get(2));
                file.setTradingTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(strList.get(3)));
                file.setTransactionAmount(new BigDecimal(strList.get(4)));
                file.setUserAccount(strList.get(5));
                file.setUserName(strList.get(6));
                file.setInAccount(strList.get(7));
                file.setInUserName(strList.get(8));
                file.setRemark(strList.get(9));
                file.setReturnNum(strList.get(10));
                break;
            case 3 :
                file.setTradeType("HB");
                file.setBusinessCode(strList.get(0));
                file.setTradeSources(strList.get(1));
                file.setBatch(strList.get(2));
                file.setTradingTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(strList.get(3)));
                file.setTransactionAmount(new BigDecimal(strList.get(4)));
                file.setUserAccount(strList.get(5));
                file.setUserName(strList.get(6));
                file.setInAccount(strList.get(7));
                file.setInUserName(strList.get(8));
                file.setRemark(strList.get(9));
                file.setReturnNum(strList.get(10));
                break;
            case 4 :
                file.setTradeType("WTCZ");
                file.setBusinessCode(strList.get(0));
                file.setTradeSources(strList.get(1));
                file.setBatch(strList.get(2));
                file.setBatchFoiuFinance(strList.get(3)); //
                file.setTradingTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(strList.get(4)));
                file.setTransactionAmount(new BigDecimal(strList.get(5)));
                file.setUserAccount(strList.get(6));
                file.setUserName(strList.get(7));
                file.setRemark(strList.get(8));
                file.setState(strList.get(9));
                break;
            case 5 :
                file.setTradeType("WTTX");
                file.setBusinessCode(strList.get(0));
                file.setTradeSources(strList.get(1));
                file.setBatch(strList.get(2));
                file.setBatchFoiuFinance(strList.get(3)); //
                file.setTradingTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(strList.get(4)));
                file.setTransactionAmount(new BigDecimal(strList.get(5)));
                file.setUserAccount(strList.get(6));
                file.setUserName(strList.get(7));
                file.setRemark(strList.get(8));
                file.setState(strList.get(9));
                break;
            case 6 :
                file.setTradeType("YSQ");
                file.setBusinessCode(strList.get(0));
                file.setContractNum(strList.get(1));
                file.setUserAccount(strList.get(2));
                file.setUserName(strList.get(3));
                file.setInAccount(strList.get(4));
                file.setInUserName(strList.get(5));
                file.setTotalMoney(new BigDecimal(strList.get(6)));
                file.setBalance(new BigDecimal(strList.get(7)));
                file.setRemark(strList.get(8));
                file.setAccountState(strList.get(9));
                break;
            default:
                return;
        }
        fuiouAccountInfoService.addFuiouAccountInfoEntity(file);
    }

}
