package com.gqhmt.sftp.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import com.gqhmt.pay.fuiou.util.FtpClient;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月10日
 * Description:
 * <p>上传
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月10日  jhz      1.0     1.0 Version
 */
@Service
public class SFTPuploadUtils {
	 public SFTPChannel getSFTPChannel() {
	        return new SFTPChannel();
	    }

	    /**
	     * 
	     * author:jhz
	     * time:2016年5月16日
	     * function：
	     * upPath：上传到富有的路径，filePath：本地文件路径
	     */
	    public void upLoadFile(String upPath,String filePath) throws Exception {
	        SFTPuploadUtils test = new SFTPuploadUtils();

	        Map<String, String> sftpDetails = new HashMap<String, String>();
	        // 设置主机ip，端口，用户名，密码
	        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "ftp-1.fuiou.com");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "gqjmsftp");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "8GHBR3bvpasCHRT5");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "9022");
	        
	        SFTPChannel channel = test.getSFTPChannel();
	        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
	        
	        File file = new File(filePath);
	        long fileSize = file.length();
	        try{
	        chSftp.cd(upPath);
	        }catch(SftpException sException){
	        	if(chSftp.SSH_FX_NO_SUCH_FILE == sException.id){
	        		chSftp.mkdir(upPath);
	        		chSftp.cd(upPath);
	        	}
	        }
	        chSftp.put(filePath, upPath, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 代码段2
//	        chSftp.rename(upPath+empName.substring(2), upPath+empName.substring(2).lastIndexOf(4));
	        // chSftp.put(new FileInputStream(src), dst, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 代码段3
	        
	        chSftp.quit();
	        channel.closeChannel();
	    }
}
