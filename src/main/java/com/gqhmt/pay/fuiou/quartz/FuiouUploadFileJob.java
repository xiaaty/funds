package com.gqhmt.pay.fuiou.quartz;

import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.pay.fuiou.service.AbortBidService;
import com.gqhmt.pay.fuiou.service.FtpDownloadFileService;
import com.gqhmt.pay.fuiou.service.FtpResultService;
import com.gqhmt.pay.fuiou.service.FtpUploadService;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.pay.fuiou.util.HttpClientUtil;
import com.gqhmt.util.JsonUtil;
import com.gqhmt.util.ServiceLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

        /**
 * Filename:    com.fuiou.quartz
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/4/14 14:09
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/4/14  于泳      1.0     1.0 Version
 */
@Component
public class FuiouUploadFileJob extends AJob{
    private static boolean isRunning = false;
    private static boolean isUploadRunning = false;
    private static boolean isAbortRunning = false;
    private static boolean isSumDayRunning = false;
    @Scheduled(cron="0/27 * 7-23  * * * ")
    protected void execute() throws PayChannelNotSupports {
        if(!isIp("download")){
            return;
        }
        if(isRunning) return;

        isRunning = true;
        try{
            //下载文件
            FtpDownloadFileService ftpDownloadFileService = ServiceLoader.get(FtpDownloadFileService.class);
            //结果分析
            FtpResultService ftpResultService = ServiceLoader.get(FtpResultService.class);

            ftpDownloadFileService.downFile();
            ftpResultService.parseDownloadResult();
            ftpResultService.parseResult();
            ftpResultService.notReturnResult();

        }catch (Exception e){
            e.getMessage();
            LogUtil.error(this.getClass(),e.getMessage(),e);
        }finally {
            isRunning = false;
        }
    }

    @Scheduled(cron="0/11 * 7-23  * * * ")
    protected void upload() throws PayChannelNotSupports{
        if(!isIp("upload")){
            return;
        }
        if(isUploadRunning) return;
        isUploadRunning = true;
        FtpUploadService ftpUploadService = ServiceLoader.get(FtpUploadService.class);
        try{
            ftpUploadService.upload();
            ftpUploadService.uploadFileToFtp();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            isUploadRunning = false;
        }


    }

    @Scheduled(cron="0  0/5 7-23  * * * ")
    public void abort() throws PayChannelNotSupports{
        if(!isIp("abort")){
            return;
        }
        if(isAbortRunning) return;
        System.out.println("job: abort");
        isAbortRunning = true;
        AbortBidService abortBidService = ServiceLoader.get(AbortBidService.class);
        try{
            abortBidService.abortBid();
            abortBidService.bidFailed();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            isAbortRunning = false;
        }
    }

//    @Scheduled(cron="0  5 18 * * * ")
    public void sumByDay()  throws PayChannelNotSupports{
        if(!isIp("abort")){
            return;
        }
        if(isSumDayRunning) return;
        System.out.println("job: sumByDay");
        isSumDayRunning = true;
        FundSequenceService fundSequenceService = ServiceLoader.get(FundSequenceService.class);

        List sumList = fundSequenceService.getSumByDay();

//        Object[] obj = (Object[]) sumList.get(0);
//        System.out.println(obj[0]+":"+obj[0].getClass().getName());
//        System.out.println(obj[1]+":"+obj[1].getClass().getName());
//        System.out.println(obj[2]+":"+obj[2].getClass().getName());


        boolean flag =  this.sendMms(sumList);

        isSumDayRunning = false;


    }

    private boolean sendMms(List sumList){
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> baMap = new HashMap<>();
        baMap.put("sysCode", CoreConstants.FUNDS_SYS_CODE);	//商户系统编码，在平台系统查看
        baMap.put("tempCode", CoreConstants.FUNDS_SUMBYDAY);	//商户模板编码，在平台系统查看
        list.add(baMap);
        for (Object obj:sumList) {
            Object[] o = (Object[]) obj;
            Map<String, String> map = new HashMap<String, String>();
            map.put("phoneNo", o[1].toString());	//手机号，多个用","分开
            map.put("0", "18点");		//模板内容中，参数0
            map.put("2", o[2].toString());		//模板内容中，参数0
            map.put("1", o[3].toString()); 		//模板内容中，参数1
            list.add(map);
        }
        try {
//            String result = HttpClientUtil.postBody(CoreConstants.BACKEND_SMS_URL,JsonUtil.toJson(list));
//            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
  /*  private   String postBody(String urlPath, String json) throws Exception {
        try {
            // Configure and open a connection to the site you will send the
            // request
            URL url = new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            // 设置doOutput属性为true表示将使用此urlConnection写入数据
            urlConnection.setDoOutput(true);
            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
            urlConnection.setRequestProperty("content-type",
                    "application/x-www-form-urlencoded");
            // 得到请求的输出流对象
            OutputStreamWriter out = new OutputStreamWriter(
                    urlConnection.getOutputStream());
            // 把数据写入请求的Body
            out.write(json);
            out.flush();
            out.close();

            // 从服务器读取响应
            InputStream inputStream = urlConnection.getInputStream();
            String encoding = urlConnection.getContentEncoding();
            String body = IOUtils.toString(inputStream, encoding);
            return body;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }*/
}