package com.gqhmt.sftp.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.ChannelSftp;
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
public class SFTPuploadUtils {
	 public SFTPChannel getSFTPChannel() {
	        return new SFTPChannel();
	    }

	    /**
	     * @param args
	     * @throws Exception
	     */
	    public static void main(String[] args) throws Exception {
	        SFTPuploadUtils test = new SFTPuploadUtils();

	        Map<String, String> sftpDetails = new HashMap<String, String>();
	        // 设置主机ip，端口，用户名，密码
	        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "127.0.0.1");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "root");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "root");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "26");
	        
	        String src = "F:\\DTLFolder\\666.csv"; // 本地文件名
	        String dst = "/DriversBackup/6666666.csv"; // 目标文件名
	              
	        SFTPChannel channel = test.getSFTPChannel();
	        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
	        
	        File file = new File(src);
	        long fileSize = file.length();
	        
	        /**
	         * 代码段1
	        OutputStream out = chSftp.put(dst, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 使用OVERWRITE模式
	        byte[] buff = new byte[1024 * 256]; // 设定每次传输的数据块大小为256KB
	        int read;
	        if (out != null) {
	            System.out.println("Start to read input stream");
	            InputStream is = new FileInputStream(src);
	            do {
	                read = is.read(buff, 0, buff.length);
	                if (read > 0) {
	                    out.write(buff, 0, read);
	                }
	                out.flush();
	            } while (read >= 0);
	            System.out.println("input stream read done.");
	        }
	        **/
	        
	        chSftp.put(src, dst, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 代码段2
	        
	        // chSftp.put(new FileInputStream(src), dst, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 代码段3
	        
	        chSftp.quit();
	        channel.closeChannel();
	    }
}
