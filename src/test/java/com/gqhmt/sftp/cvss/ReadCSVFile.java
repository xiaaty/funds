package com.gqhmt.sftp.cvss;
import java.io.File;  
import java.io.IOException;  
import java.util.List;

import javax.annotation.Resource;

import com.gqhmt.sftp.cvss.CVSFileLoader;
import com.gqhmt.sftp.cvss.Loader;
import com.gqhmt.sftp.service.FssProjectService;  
  
/** 测试类 */ 
public class ReadCSVFile {
	
	 /** 
     * @param args 
     * @throws IOException  
     */  
    public static void main(String[] args) throws Exception {  
        // TODO Auto-generated method stub  
        File cvsFile = new File("F:/文件导出3673204494051487425.csv");  
//        File logFile = new File("D:/Load.log");  
          
        CVSFileLoader loader = new CVSFileLoader(cvsFile, null);  
          
        loader.load(new Loader(){  
            public void load(List recFieldList) throws Exception{  
                for (int i = 0; i < recFieldList.size(); i++) {  
                    System.out.println("F[" + i + "]=" + recFieldList.get(i));  
                }  
            }  
        });  
    }  
}
