package com.gqhmt.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.ChannelSftp;

public class SFTPuploadTest {
	 public SFTPChannel getSFTPChannel() {
	        return new SFTPChannel();
	    }

	    /**
	     * @param args
	     * @throws Exception
	     */
	    public static void main(String[] args) throws Exception {
	        SFTPuploadTest test = new SFTPuploadTest();

	        Map<String, String> sftpDetails = new HashMap<String, String>();
	        // 设置主机ip，端口，用户名，密码
	        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "127.0.0.1");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "root");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "root");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "26");
	        
	        String src = "F:\\P2P_PW10_20160516.txt.tmp"; // 本地文件名
	        String dst = "/"; // 目标文件名
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
	        chSftp.rename(dst+src.substring(2), dst+src.substring(2,src.length()-4));
	        // chSftp.put(new FileInputStream(src), dst, new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 代码段3
	        
	        chSftp.quit();
	        channel.closeChannel();
	    }
}
