package com.gqhmt.sftp.txt;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import com.gqhmt.TestService;
import com.gqhmt.core.FssException;
import com.gqhmt.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.sftp.service.FssProjectInfoService;
public class TXTUtils extends TestService{
	
	
	 @Resource
	 private FssProjectInfoService fssProjectInfoService;
	 @Resource
	 private CreateTXT createTxt;
	 /**
	   * 生成为CVS文件 
	   * @param exportData
	   *       源数据List
	   * @param map
	   *       csv文件的列表头map
	   * @param outPutPath
	   *       文件路径
	   * @param fileName
	   *       文件名称
	   * @return
	   */
	  @SuppressWarnings("rawtypes")
	  public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath,
	                   String fileName) {
	    File csvFile = null;
	    BufferedWriter csvFileOutputStream = null;
	    try {
	      File file = new File(outPutPath);
	      if (!file.exists()) {
	        file.mkdir();
	      }
	      //定义文件名格式并创建
	      csvFile = File.createTempFile(fileName, ".txt", new File(outPutPath));
	      System.out.println("csvFile：" + csvFile);
	      // UTF-8使正确读取分隔符"," 
	      csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
	        csvFile), "UTF-8"), 1024);
	      System.out.println("csvFileOutputStream：" + csvFileOutputStream);
	      // 写入文件头部 
	      for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
	        java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
	        csvFileOutputStream
	          .write("'" + (String) propertyEntry.getValue() != null ? (String) propertyEntry
	            .getValue() : "" + "'");
	        if (propertyIterator.hasNext()) {
	          csvFileOutputStream.write(",");
	        }
	      }
	      csvFileOutputStream.newLine();
	      // 写入文件内容 
	      for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
	        Object row = (Object) iterator.next();
	        for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
	          .hasNext();) {
	          java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
	            .next();
	          csvFileOutputStream.write((String) BeanUtils.getProperty(row,
	            (String) propertyEntry.getKey()));
	          if (propertyIterator.hasNext()) {
	            csvFileOutputStream.write(",");
	          }
	        }
	        if (iterator.hasNext()) {
	          csvFileOutputStream.newLine();
	        }
	      }
	      csvFileOutputStream.flush();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        csvFileOutputStream.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return csvFile;
	  }
	  /**
	   * 下载文件
	   * @param response
	   * @param csvFilePath
	   *       文件路径
	   * @param fileName
	   *       文件名称
	   * @throws IOException
	   */
	  public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName)
	                                                  throws IOException {
	    response.setContentType("application/txt;charset=UTF-8");
	    response.setHeader("Content-Disposition",
	      "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
	 
	    InputStream in = null;
	    try {
	      in = new FileInputStream(csvFilePath);
	      int len = 0;
	      byte[] buffer = new byte[1024];
	      response.setCharacterEncoding("UTF-8");
	      OutputStream out = response.getOutputStream();
	      while ((len = in.read(buffer)) > 0) {
	        out.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
	        out.write(buffer, 0, len);
	      }
	    } catch (FileNotFoundException e) {
	      System.out.println(e);
	    } finally {
	      if (in != null) {
	        try {
	          in.close();
	        } catch (Exception e) {
	          throw new RuntimeException(e);
	        }
	      }
	    }
	  }
	 
	  /**
	   * 删除该目录filePath下的所有文件
	   * @param filePath
	   *      文件目录路径
	   */
	  public static void deleteFiles(String filePath) {
	    File file = new File(filePath);
	    if (file.exists()) {
	      File[] files = file.listFiles();
	      for (int i = 0; i < files.length; i++) {
	        if (files[i].isFile()) {
	          files[i].delete();
	        }
	      }
	    }
	  }
	 
	  /**
	   * 删除单个文件
	   * @param filePath
	   *     文件目录路径
	   * @param fileName
	   *     文件名称
	   */
	  public static void deleteFile(String filePath, String fileName) {
	    File file = new File(filePath);
	    if (file.exists()) {
	      File[] files = file.listFiles();
	      for (int i = 0; i < files.length; i++) {
	        if (files[i].isFile()) {
	          if (files[i].getName().equals(fileName)) {
	            files[i].delete();
	            return;
	          }
	        }
	      }
	    }
	  }
	 
	  /**
	   * 测试数据
	   * @param
	 * @throws FssException 
	   */
	  @Test
	  public  void getsss() throws FssException {
		  
		    List<FssProjectInfoEntity> queryItemsInfos = fssProjectInfoService.queryItemsInfos();
		  //导出txt文件 
		  String fileName="123333"; 
		  BufferedOutputStream buff = null;   
		  StringBuffer write = new StringBuffer();   
		  String enter = "\r\n";   
		  OutputStream outSTr = null;   
		  try {   
		    outSTr = new FileOutputStream("F:\\"+fileName+".txt"); // 建立   
		    buff = new BufferedOutputStream(outSTr); 
		    //把内容写入文件 
		    if(queryItemsInfos.size()>0){ 
		    	write.append("数据总数："+queryItemsInfos.size()+"|"+enter);
		     for (int i = 0; i < queryItemsInfos.size(); i++) { 
		      write.append(queryItemsInfos.get(i).getAccNo()+"|"); 
		      write.append(queryItemsInfos.get(i).getCertNo()+"|"); 
		      write.append(queryItemsInfos.get(i).getCertType()+"|"); 
		      write.append(queryItemsInfos.get(i).getCustName()+"|"); 
		      write.append(queryItemsInfos.get(i).getBidYearIrr()+"|"); 
		      write.append(queryItemsInfos.get(i).getDescription()+"|"); 
		      write.append(enter);   
		     } 
		    } 
		    System.out.println(fileName+"3333333333333");
		    buff.write(write.toString().getBytes("UTF-8"));   
		    buff.flush();   
		    buff.close();   
		  } catch (Exception e) {   
		   e.printStackTrace();   
		  } finally {   
		   try {   
		    buff.close();   
		    outSTr.close();   
		   } catch (Exception e) {   
		    e.printStackTrace();   
		   }   
		  } 
	  }
	  
	  
	  @Test
	  public void testCreateTXT() throws FssException{
//		  createTxt.createProjectInfoTXT();
	  }
	  @Test
	  public void createFinanceSumTXT() throws FssException{
//		  createTxt.createFinanceSumTXT();
	  }
}
