package com.gqhmt.sftp.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
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
 * <p>下载文件
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月10日  jhz      1.0     1.0 Version
 */
@Service
public class SFTPDownLoadutils {
	 public SFTPChannel getSFTPChannel() {
	        return new SFTPChannel();
	    }
	 	/**
	 	 * 
	 	 * author:jhz
	 	 * time:2016年5月16日
	 	 * function：
	 	 * downPath：上传到服务器的目录，filePath：本地文件路径
	 	 */
	    public void downLoadFile(String downPath,String filePath) throws Exception {
	        SFTPDownLoadutils test=new SFTPDownLoadutils();

	        Map<String, String> sftpDetails = new HashMap<String, String>();
	        // 设置主机ip，端口，用户名，密码
	        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "ftp-1.fuiou.com");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "gqjmsftp");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "8GHBR3bvpasCHRT5");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "9022");
	        
	        SFTPChannel channel = test.getSFTPChannel();
	        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
	        SftpATTRS attr = chSftp.stat(downPath);
	        long fileSize = attr.getSize();
	        OutputStream out = new FileOutputStream(filePath);
	        try {
	            chSftp.get(downPath, filePath, new FileProgressMonitor(fileSize)); // 代码段1
	            
	            // chSftp.get(filename, out, new FileProgressMonitor(fileSize)); // 代码段2
	            
	            /**
	             * 代码段3
	             * 
	            InputStream is = chSftp.get(filename, new MyProgressMonitor());
	            byte[] buff = new byte[1024 * 2];
	            int read;
	            if (is != null) {
	                System.out.println("Start to read input stream");
	                do {
	                    read = is.read(buff, 0, buff.length);
	                    if (read > 0) {
	                        out.write(buff, 0, read);
	                    }
	                    out.flush();
	                } while (read >= 0);
	                System.out.println("input stream read done.");
	            }
	            */
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            chSftp.quit();
	            channel.closeChannel();
	        }
	    }
}
