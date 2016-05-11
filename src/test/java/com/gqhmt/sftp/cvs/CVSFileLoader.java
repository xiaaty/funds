package com.gqhmt.sftp.cvs;
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.RandomAccessFile;  
import java.util.ArrayList;  
import java.util.List; 
/** 
 * 逗号分割文件数据加载类 
 * @version 1.0 
 * @author zhanzhengqiang 
 */ 
public class CVSFileLoader {
	 /** 
     * 加载cvs文件路径 
     */  
    private File cvsFile;  
      
    /** 
     * 加载数据日志文件路径 
     */  
    private File logFile;  
      
    /** 
     * 默认构造函数 
     */  
    public CVSFileLoader() {  
    }  
      
    /** 
     * 构造函数 
     * @param cvsFile 加载cvs文件路径 
     * @param logFile 加载数据日志文件路径 
     * @param loader 
     */  
    public CVSFileLoader(File cvsFile, File logFile) {  
        this.cvsFile = cvsFile;  
        this.logFile = logFile;  
          
        // 准备日志文件  
        this.prepareLogFile();  
    }  
      
    /** 
     * @return the cvsFile 
     */  
    public File getCvsFile() {  
        return cvsFile;  
    }  
  
    /** 
     * @param cvsFile the cvsFile to set 
     */  
    public void setcvsFile(File cvsFile) {  
        this.cvsFile = cvsFile;  
    }  
  
    /** 
     * @return the logFile 
     */  
    public File getLogFile() {  
        return logFile;  
    }  
  
    /** 
     * @param logFile the logFile to set 
     */  
    public void setLogFile(File logFile) {  
        this.logFile = logFile;  
          
        // 准备日志文件  
        this.prepareLogFile();  
    }  
  
    /** 
     * 加载CVS文件 
     * @param loader 文件加载器 
     * @throws Exception 加载异常 
     */  
    public void load(Loader loader) {  
        this.writeLog("#导入数据开始#");  
        try {  
            // 读取CVS文件  
            BufferedReader reader = new BufferedReader(new FileReader(this.cvsFile));  
            // 逐行读取,并导入数据到数据库  
            String line = null;  
            // 行号  
            int rownum = 1;   
            while ((line = reader.readLine()) != null) {  
                try {  
                	this.writeLog("第" + rownum + "行：[" + line + "]导入成功");  
                	loader.load(parseLine(line));  
                } catch(Exception e) {  
                    this.writeLog("第" + rownum + "行：[" + line + "]导入出错：" + e.getMessage());  
                }  
                // 行号加一  
                rownum ++;  
            }              
        } catch (FileNotFoundException e) {  
            this.writeLog("要导入的文件[" + cvsFile.getAbsolutePath() + "]找不到");  
        } catch (IOException e) {  
            this.writeLog("导入数据出错:" + e.getMessage());  
        }  
        this.writeLog("#导入数据结束#");  
    }  
      
    /** 
     * 按逗号分割行 
     * @param line 逗号分割文件行 
     * @return 分割结果列表 
     */  
    private static List parseLine(String line) {  
        // 解析字符串  
        List tokenList = new ArrayList();  
        StringBuffer token = new StringBuffer();  
        // 是否在引号内  
        boolean inQuotation = false;  
        for (int i = 0; i < line.length(); i++) {  
            char c = line.charAt(i);  
            if (c == ',' && !inQuotation) {  
                tokenList.add(token.toString());  
                token.setLength(0);  
            } else if (c == '"') {  
                if (inQuotation) {  
                    char d = line.charAt(i+1);  
                    if (d == '"') {  
                        token.append(c);  
                        i ++;  
                    } else {  
                        inQuotation = !inQuotation;  
                    }  
                } else {  
                    inQuotation = !inQuotation;  
                }  
            } else {  
                token.append(c);  
            }  
        }  
        tokenList.add(token.toString());  
          
        // 返回解析结果  
        return tokenList;  
    }  
      
    /** 
     * 准备日志文件 
     */  
    private void prepareLogFile() {  
        if (this.logFile == null) {  
            return;  
        }  
          
        // 删除原日志文件  
        if (this.logFile.isFile() && this.logFile.exists()) {  
            this.logFile.delete();  
        }  
        // 创建日志目录  
        File logDir = this.logFile.getParentFile();  
        if (!logDir.exists()) {  
            logDir.mkdirs();  
        }  
    }  
      
    /** 
     * 输出数据加载日志到日志文件 
     * @param msg 日志信息 
     */  
    private void writeLog(String msg) {  
        // 没有指定日志文件，则直接返回  
        if (this.logFile == null) {  
            return;  
        }  
          
        // 定义日志文件  
        RandomAccessFile writeLogFile = null;  
        try {  
            writeLogFile = new RandomAccessFile(this.logFile, "rw");  
            // 定位到文件末尾并换行  
            if (writeLogFile.length() > 0) {  
                writeLogFile.seek(logFile.length());  
                writeLogFile.writeBytes(System.getProperty("line.separator"));  
            }  
            writeLogFile.write(msg.getBytes("GBK"));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (writeLogFile != null) {  
                    writeLogFile.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
