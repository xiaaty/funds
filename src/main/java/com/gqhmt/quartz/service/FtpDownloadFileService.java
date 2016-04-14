package com.gqhmt.quartz.service;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouUploadFile;
import com.gqhmt.fss.architect.fuiouFtp.service.FuiouFtpColomFieldService;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
     */
    public boolean downloadReturn(FuiouUploadFile file) throws PayChannelNotSupports{
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
}
