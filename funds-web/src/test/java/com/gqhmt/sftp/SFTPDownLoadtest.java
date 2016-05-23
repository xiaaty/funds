package com.gqhmt.sftp;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
public class SFTPDownLoadtest {
	 public SFTPChannel getSFTPChannel() {
	        return new SFTPChannel();
	    }

	    public static void main(String[] args) throws Exception {
	        SFTPDownLoadtest test=new SFTPDownLoadtest();

	        Map<String, String> sftpDetails = new HashMap<String, String>();
	        // 设置主机ip，端口，用户名，密码
	        sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, "127.0.0.1");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, "root");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, "root");
	        sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, "26");
	        
	        SFTPChannel channel = test.getSFTPChannel();
	        ChannelSftp chSftp = channel.getChannel(sftpDetails, 60000);
	        String filename = "/DriversBackup/6666666.csv";
	        SftpATTRS attr = chSftp.stat(filename);
	        long fileSize = attr.getSize();
	        
	        String dst = "F:\\DTLFolder\\33333333.csv";
	        OutputStream out = new FileOutputStream(dst);
	        try {
	            chSftp.get(filename, dst, new FileProgressMonitor(fileSize)); // 代码段1
	            
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
