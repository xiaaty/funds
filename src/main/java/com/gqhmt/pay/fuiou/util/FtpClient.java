package com.gqhmt.pay.fuiou.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.TrustManagerUtils;

import java.io.*;
import java.net.SocketException;

/**
 * Created by yuyonf on 15/4/6.
 */
public class FtpClient {

    private static final Log log = LogFactory.getLog(FtpClient.class);

//    private boolean printHash = false;

//    private boolean hidden = false;

//    private long keepAliveTimeout = -1;

//    private int controlKeepAliveReplyTimeout = -1;

    private String protocol = null; // SSL protocol

    private String trustmgr = "all";
    private String proxyHost = null;
    private int proxyPort = 80;
    public FtpClient( String protocol, int ftpPort, String username, String password, String urlPath) {
        super();
        this.protocol = protocol;
        this.username = username;
        this.password = password;
        this.urlPath = urlPath;

        this.ftpPort = ftpPort;
        if(this.ftpPort==0){
            if(protocol == null){
                this.ftpPort=21;
            }else{
                this.ftpPort=22;
            }
        }
        this.initFtp();
    }

    private int ftpPort = 0;

    public FtpClient( int ftpPort, String username,String password, String urlPath) {
        this(null, ftpPort, username, password, urlPath);
    }

    public FtpClient(String username, String password, String urlPath) {
        this( 0, username, password, urlPath);
    }



    public FtpClient(String urlPath) {
        this(null,null,urlPath);
    }

    private String proxyUser = null;
    private String proxyPassword = null;
    private String username = null;
    private String password = null;
    private String urlPath;

    private FTPClient ftp;


    /**
     * 初始化ftp服务器客户端
     */
    public void initFtp(){
        if (protocol == null ) {
            if(proxyHost !=null) {
                System.out.println("Using HTTP proxy server: " + proxyHost);
                this.ftp = new FTPHTTPClient(proxyHost, proxyPort, proxyUser, proxyPassword);
            }
            else {
                this.ftp = new FTPClient();
            }
        } else {
            FTPSClient ftps;
            if (protocol.equals("true")) {
                ftps = new FTPSClient(true);
            } else if (protocol.equals("false")) {
                ftps = new FTPSClient(false);
            }else {
                String prot[] = protocol.split(",");
                if (prot.length == 1) { // Just protocol

                    ftps = new FTPSClient(protocol);
                } else { // protocol,true|false

                    ftps = new FTPSClient(prot[0], Boolean.parseBoolean(prot[1]));
                }
            }
            this.ftp = ftps;
            if ("all".equals(trustmgr)) {
                ftps.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
            } else if ("valid".equals(trustmgr)) {
                ftps.setTrustManager(TrustManagerUtils.getValidateServerCertificateTrustManager());
            } else if ("none".equals(trustmgr)) {
                ftps.setTrustManager(null);
            }
        }
    }


    public boolean mkfir(String path) throws Exception {
        if(!ftp.isConnected()){
            try {
                this.ftpConnection();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("ftp is not connectioned ");
            }
        }

        try {
            String [] tmp = path.split("/");
            for(String t:tmp){
                if(t == null && "".equals(t)){
                    continue;
                }

                boolean flag = ftp.changeWorkingDirectory(t);
                if(!flag){
                    ftp.makeDirectory(t);
                }
            }
            ftp.makeDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 验证远程是否存在该文件
     * @param fileName
     * @return
     */
    public boolean exits(String fileName) {

        try {
            this.ftpConnection();
            if(fileName.contains("/")){
                String spString[] = fileName.split("/");
                for( int i = 0;i<spString.length-1;i++){
                    ftp.changeWorkingDirectory(spString[i]);
                }
                fileName = spString[spString.length-1];
            }
           // this.ftp.setFileType(FTP.BINARY_FILE_TYPE);
            this.ftp.enterLocalPassiveMode();
//                this.ftp.setControlEncoding(CoreConstants.ENCODING_GBK);
            //this.ftp.makeDirectory("tt");
//                System.out.println(this.ftp.getStatus());
            //this.ftp.setControlKeepAliveTimeout(300);
            String[] listName =  this.ftp.listNames(fileName);
            if(listName == null || listName.length>0){
                return true;
            }

        } catch (Exception e) {

        }finally {
            this.ftpDisConnection();
        }

        return false;
    }


    /**
     * 获取文件并存储本地
     * @param remoteFileName
     * @param localFileName
     */
    public boolean getFile(String remoteFileName,String localFileName){

        OutputStream os = null;
        OutputStream out = null;
        try {
            this.ftpConnection();
            if(remoteFileName.contains("/")){
                String spString[] = remoteFileName.split("/");
                for( int i = 0;i<spString.length-1;i++){
                    ftp.changeWorkingDirectory(spString[i]);
                }
                remoteFileName = spString[spString.length-1];
            }
            if(localFileName.contains("/")){
                String tmp = localFileName.substring(0,localFileName.lastIndexOf("/"));
                File file = new File(tmp);
                if(!file.exists()){
                    file.mkdirs();
                }
            }
            System.out.println(this.ftp.printWorkingDirectory());
            os = new FileOutputStream(new File(localFileName));
            out = new BufferedOutputStream(os);
//            remoteFileName = CommonUtil.getString(remoteFileName, CoreConstants.ENCODING_GBK,CoreConstants.ENCODING_ISO8859_1);
            this.ftp.setFileType(FTP.BINARY_FILE_TYPE);
            this.ftp.enterLocalPassiveMode();
//                this.ftp.setControlEncoding(CoreConstants.ENCODING_GBK);
            //this.ftp.makeDirectory("tt");
//                System.out.println(this.ftp.getStatus());
            this.ftp.setControlKeepAliveTimeout(300);
            boolean flag = ftp.retrieveFile(remoteFileName, out);
            return flag;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            this.ftpDisConnection();
            if(out != null ){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                }
            }
            if(os != null ){
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block

                    e.printStackTrace();
                }
            }


        }
        return false;
    }

    /**
     * 获取文件
     * @param remoteFileName
     * @return
     */
    public InputStream getFile(String remoteFileName){
        InputStream is = null;
        try {
            this.ftpConnection();
            is = ftp.retrieveFileStream(remoteFileName);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            this.ftpDisConnection();
        }

        return is;
    }


    /**
     * 获取文件列表
     * @return
     */
    public String[] list(){
        try {
            this.ftpConnection();
            return ftp.listNames();
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            this.ftpDisConnection();
        }

        return null;
    }

 /*   public boolean uploadFile(String localFile,String remoteFileName){
        File file = new File(localFile);
        return this.uploadFile(file,remoteFileName);
    }
*/
/*    public boolean uploadFile(File file ,String fileName){
        try {
            if(!ftp.isConnected()) {
                this.ftpConnection();
            }
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            return this.uploadFile(bis,fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.ftpDisConnection();
        }

        return false;
    }*/

    /**
     * 上传文件
     * @param in
     * @param fileNme
     */
 /*   public boolean uploadFile(InputStream in,String fileNme) {
        try {
            this.ftpConnection();

            if(fileNme.contains("/")){
                String tmp = fileNme.substring(0,fileNme.lastIndexOf("/"));
                if(!this.ftp.changeWorkingDirectory(tmp)){
                    this.ftp.changeWorkingDirectory(tmp);
                }
                System.out.println(this.ftp.printWorkingDirectory());

//                this.ftp.setFileType(FTP.ASCII_FILE_TYPE);

               String  fileName  = fileNme.substring(fileNme.lastIndexOf("/")+1);
                String tmpFileName = fileName+".tmp";
                String remoteFileName = CommonUtil.getString(tmpFileName, CoreConstants.ENCODING_GBK,
                        CoreConstants.ENCODING_ISO8859_1);
                this.ftp.setFileType(FTP.BINARY_FILE_TYPE);
                this.ftp.enterLocalPassiveMode();
//                this.ftp.enterRemotePassiveMode();
//                this.ftp.enterLocalActiveMode();
//                this.ftp.setControlEncoding(CoreConstants.ENCODING_GBK);
                //this.ftp.makeDirectory("tt");
//                System.out.println(this.ftp.getStatus());
                this.ftp.setBufferSize(1024);
                this.ftp.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
//                this.ftp.setControlKeepAliveTimeout(300);
                BufferedInputStream bs = new BufferedInputStream(in);
                boolean flag = this.ftp.storeFile(tmpFileName, bs);
                if(flag == true){
                    flag = this.ftp.rename(tmpFileName,fileName);
//                    System.out.println(this.ftp.getReply()+":::"+this.ftp.getReplyCode());
                }
                if(log.isDebugEnabled()){
                    log.debug("ftpUpload:"+flag);
                }
                in.close();


                return flag;

            }

        } catch (SocketException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            this.ftpDisConnection();
        }

        return false;
    }
*/
//    public boolean isExist(String filePath){
//        this.ftp.
//    }


    /**
     * 连接ftp服务器
     * @throws SocketException
     * @throws IOException
     */
    protected void ftpConnection() throws Exception {
        if(log.isDebugEnabled()){
            log.debug(" ftpConnection");

        }

        //ftp连接
        ftp.connect(urlPath,ftpPort);
        if(log.isDebugEnabled()){
            log.debug("RemotePort:"+ ftp.getRemotePort());
            log.debug("ftpLogin");
        }
        //ftp登陆

       boolean isLogin =  ftp.login(username, password);
        if(!isLogin){
            throw new Exception("登录失败");
        }
    }

    //断开ftp连接

    protected void ftpDisConnection() {
        if(!ftp.isConnected()){
            if(log.isDebugEnabled()){
                log.debug("ftp is not Connect");
            }
            return;
        }
        try {
            this.ftp.logout();
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
