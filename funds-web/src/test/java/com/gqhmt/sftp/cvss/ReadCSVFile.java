package com.gqhmt.sftp.cvss;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gqhmt.sftp.entity.FssProjectCallbackEntity;
  
/** 测试类 */ 
public class ReadCSVFile {
	
	 /** 
     * @param args 
     * @throws IOException  
     */  
    public static void main(String[] args) throws Exception {  
    	 File file = new File("F:/P2P_PWXM_20160518_1000.txt");
    	 BufferedReader br=new BufferedReader(new FileReader("F:/P2P_PWXM_20160518_1000.txt"));
 		String r= br.readLine();
		FssProjectCallbackEntity projectCallback=null;
		List<FssProjectCallbackEntity> list=new ArrayList<FssProjectCallbackEntity>();
		while(r!=null){
			projectCallback=new FssProjectCallbackEntity();
		String str[]=r.split("\\|");
		System.out.println(str[1]+"---");
//		projectCallback.setItemNo(str[0]);
//		projectCallback.setItemName(str[1]);
//		projectCallback.setPayChannel(str[2]);
//		projectCallback.setStatus(str[3]);
//		projectCallback.setRespCode(str[4]);
//		projectCallback.setRespMsg(str[5]);
//		projectCallback.setBidId(str[6]);
//		projectCallback.setFailedMsg(str[7]);
//		System.out.println(str[6] + "-----");
//		list.add(projectCallback);
		System.out.println(r);
		r=br.readLine();
		}
//		for (FssProjectCallbackEntity fssProjectCallbackEntity : list) {
//			System.out.println(fssProjectCallbackEntity.getBidId()+"-----");
//		}
    }  
}
