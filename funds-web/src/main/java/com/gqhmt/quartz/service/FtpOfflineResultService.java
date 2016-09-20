package com.gqhmt.quartz.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.FssTradeInfoEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeInfoFileEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeInfoFileService;
import com.gqhmt.fss.architect.trade.service.FssTradeInfoService;
import com.gqhmt.pay.core.PayCommondConstants;
import com.gqhmt.pay.core.configer.Config;
import com.gqhmt.pay.core.factory.ConfigFactory;
import com.gqhmt.pay.fuiou.util.FtpClient;
import com.gqhmt.util.ReadExcelUtil;
import com.gqhmt.util.exception.ReadExcelErrorException;
import com.gqhmt.util.exception.ReadExcelException;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/12.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/12.  xdw         1.0     1.0 Version
 */
@Service
public class FtpOfflineResultService {

    @Resource
    private FssTradeInfoService fssTradeInfoService;

    @Resource
    private FssTradeInfoFileService fssTradeInfoFileService;


    /**
     * ftp下载线下回盘交易记录，存在失败可能，失败重新下载
     *
     * @param date, path
     * @return
     * @throws FssException
     */
    public boolean downloadTradeInfo(Date date, String path) throws FssException {
        Config config = ConfigFactory.getConfigFactory().getConfig(PayCommondConstants.PAY_CHANNEL_FUIOU);
        String url = (String) config.getValue("ftp.url.value");
        String port = (String) config.getValue("ftp.port.value");
        String userName = (String) config.getValue("ftp.userName.value");
        String pwd = (String) config.getValue("ftp.pwd.value");
        String prefixFileName = (String) config.getValue("public.mchnt_cd.value");

        FtpClient ftp = new FtpClient(Integer.parseInt(port), userName, pwd, url);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmm");
        String pathDateStr = sdf.format(date);
        String fileDateStr = sdf2.format(date);
        String filePath = path + pathDateStr + "/";
        String fileName = prefixFileName + "-" + fileDateStr + ".xls";

        boolean flag = ftp.exits(filePath + fileName);
        if (!flag) {
            return false;
        }

        String contextPath = getClassPath();
        File localFilepath = new File(contextPath + "/tmp/" + filePath);
        if (!localFilepath.exists()) {
            localFilepath.mkdirs();
        }
        String localFile = localFilepath + "/" + fileName;

        flag = ftp.getFile(filePath + fileName, localFile);
        parseFileTradeInfo(localFile);
        return true;
    }

    /**
     * 对已下载金账户对账文件处理
     *
     * @param localFilePath
     * @throws FssException
     */
    private void parseFileTradeInfo(String localFilePath) throws FssException {

        File localFile = new File(localFilePath);

        String spString[] = localFilePath.split("/");
        String fileName = spString[spString.length - 1];
        FssTradeInfoFileEntity infoFileEntity = fssTradeInfoFileService.queryFileByFileName(fileName);
        //查询文件是否存在
        if (infoFileEntity != null && "1".equals(infoFileEntity.getUploadSts())) return;

        try {

            String backExcelPath = getClassPath() + "/tmp/back/excel/";
            ReadExcelUtil excelUtil = new ReadExcelUtil(backExcelPath, FssTradeInfoEntity.class);
            String[] columnName = new String[]{"dataSource", "sysCode", "orglSeqNo", "chgCd", "toAccTime", "tradeTime", "toAccNm", "toAccNo", "amount", "tradeSts", "cardVerify"};

            int sheetsSize = excelUtil.getWorkBook(localFile).getNumberOfSheets();

            List<FssTradeInfoEntity> listTradeInfo = new ArrayList<FssTradeInfoEntity>();
            if (sheetsSize > 0) {
                for (int i = 0; i < sheetsSize; i++) {
                    listTradeInfo.addAll((List<FssTradeInfoEntity>) excelUtil.getExcelData(localFile, columnName, i));
                }
            }

            if (!CollectionUtils.isEmpty(listTradeInfo)) {

                FssTradeInfoFileEntity tradeInfoFile = new FssTradeInfoFileEntity();
                tradeInfoFile.setFileName(fileName);
                tradeInfoFile.setCreateTime(new Date());
                tradeInfoFile.setUploadSts("1");
                tradeInfoFile.setFilePath(localFilePath);

                fssTradeInfoFileService.insertTradeInfoFile(tradeInfoFile);

                String fileId = new Long(tradeInfoFile.getId()).toString();

                for (FssTradeInfoEntity tradeInf : listTradeInfo) {
                    tradeInf.setFileId(fileId);
                }

                fssTradeInfoService.insertListTradeInfo(listTradeInfo);

                LogUtil.info(this.getClass(), "抓取文件 \"" + fileName + "\" 成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ReadExcelException ree) {
            ree.printStackTrace();
        } catch (ReadExcelErrorException reee) {
            reee.printStackTrace();
        }
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
