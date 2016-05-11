package com.gqhmt.sftp.cvs;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.gqhmt.TestService;
import com.gqhmt.core.FssException;
import com.gqhmt.sftp.cvs.CSVUtils;
import com.gqhmt.sftp.entity.FssProjectInfoEntity;
import com.gqhmt.sftp.service.FssProjectService;
import com.gqhmt.util.CommonUtil;
public class CSVUtils extends TestService{
	
	
	 @Resource
		private FssProjectService fssProjectService;
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
	      csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
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
	    response.setContentType("application/csv;charset=UTF-8");
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
	   * @param args
	 * @throws FssException 
	   */
	  @Test
	  public  void get() throws FssException {
		  List<FssProjectInfoEntity> queryItemsInfos = fssProjectService.queryItemsInfos();
	    List exportData = new ArrayList<Map>();
	    Map row1 = new LinkedHashMap<String, String>();
//	    row1.put("1", "");
	    row1.put("1", "平台账号");
	    row1.put("2", "身份证号");
	    row1.put("3", "身份证类型");
	    exportData.add(row1);
	    for (FssProjectInfoEntity info : queryItemsInfos) {
	    	
	    	row1=new LinkedHashMap<String, String>();
	    	row1.put("1",info.getAccNo() );
	    	row1.put("2", info.getCertNo());
	    	row1.put("3", info.getCertType());
	    	exportData.add(row1);
		}
	    LinkedHashMap map = new LinkedHashMap();
	    map.put("1", "总数据数");
	    map.put("2",String.valueOf(queryItemsInfos.size()));
	    map.put("3", "");
	 
	    String path = "F:/";
	    String fileName = "文件导出";
	    File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
	    file.renameTo(new File("F:/P2P_PWXM_"+CommonUtil.dateTostring(new Date())+".csv"));
	    String fileName2 = file.getName();
	    System.out.println("文件名称：" + fileName2);
	  }
}
