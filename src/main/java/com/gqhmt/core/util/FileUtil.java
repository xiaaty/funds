package com.gqhmt.core.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.filefilter.FileFileFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gqhmt.util.StringUtils;



public class FileUtil {
private static Log log = LogFactory.getLog(FileUtil.class);
	
	private static String baseDir = "/";

	/**
	 * 根据path和file获得文件.这个path不是真实路径,是相对于URL格式的路径,比如/upload/image/
	 * @param path
	 * @param name
	 * @return
	 */
	public static byte[] getFile(String path,String name) {
		path = path.replace("/", File.separator);

		if (StringUtils.isNotEmptyString(path) && path.startsWith(File.separator)) {
			path = path.substring(1);
		}
		if (StringUtils.isNotEmptyString(path) && !path.endsWith(File.separator)) {
			path += File.separator;
		}
		return getFileData(baseDir+path,name);
	}

	/**
	 * 根据path和file获得文件.
	 * @param path
	 * @param file
	 * @return
	 */
	public static byte[] getFileData(String path, String file) {
		if (StringUtils.isEmpty(path)) {
			return null;
		}
		// 把null转换掉
		if (StringUtils.isEmpty(file)) {
			file = "";
		}
		byte[] data = null;
		FileInputStream fis = null;
		try {
			path = path.trim();
			file = file.trim();
			if (StringUtils.isNotEmptyString(file) && !path.endsWith(File.separator)) {
				path += File.separator;
			}
			String allPath = path + file;
			log.debug("*** get File: " + allPath);
			fis = new FileInputStream(allPath);
			data = new byte[fis.available()];
			fis.read(data);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e1) {
				log.error("", e1);
			}
		}
		return data;
	}

	/**
	 * 文件上传,path是URL格式,比如/upload/image/
	 * @param path
	 * @param name
	 * @param data
	 * @throws Exception
	 */
	public static void uploadFile(String path, String name, byte[] data)
			throws Exception {
		FileOutputStream fos = null;

		path = path.replace("/", File.separator);

		if (StringUtils.isNotEmptyString(path) && path.startsWith(File.separator)) {
			path = path.substring(1);
		}
		if (StringUtils.isNotEmptyString(path) && !path.endsWith(File.separator)) {
			path += File.separator;
		}

		if(System.getProperty("os.name").equals("Linux")){
			path = baseDir + path;
		}
		
		File dir = new File(path);
		boolean isNewDir = false;

		if (!dir.exists() || !dir.isDirectory()) {
			isNewDir = true;
			dir.canWrite();
			dir.mkdirs();
		}

		try {
			fos = new FileOutputStream(baseDir + path + name);
			fos.write(data);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			if (isNewDir) {
				dir.delete();
			}
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	/**
	 * 删除文件,路径是url格式,如/upload/image
	 * @param path
	 * @param name
	 */
	public static void deleteFile(String path,String name) {
		path = path.replace("/", File.separator);

		if (StringUtils.isNotEmptyString(path) && path.startsWith(File.separator)) {
			path = path.substring(1);
		}
		if (StringUtils.isNotEmptyString(path) && !path.endsWith(File.separator)) {
			path += File.separator;
		}
		delete(baseDir+path+name);
		
	}

	/**
	 * 删除文件，可以是单个文件或文件夹
	 * 
	 * @param fileName 待删除的文件名
	 * @return 文件删除成功返回true,否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败：" + fileName + "文件不存在");
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName 被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			System.out.println("删除单个文件" + fileName + "成功！");
			return true;
		} else {
			System.out.println("删除单个文件" + fileName + "失败！");
			return false;
		}
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param dir 被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			System.out.println("删除目录失败" + dir + "目录不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			System.out.println("删除目录失败");
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			System.out.println("删除目录" + dir + "失败！");
			return false;
		}
	}
	
	/**
	 * byte[]转String[]
	 * 
	 * @param dataBytes
	 * @return
	 */
	public static String[] parse2StringArray(byte[] dataBytes) {
		if (dataBytes == null || dataBytes.length == 0) {
			return null;
		}
		String tempStr = new String(dataBytes);
		String[] rtnArr = tempStr.split("\r\n");
		for (String str : rtnArr) {
			log.debug("str:" + str);
		}
		return rtnArr;
	}
	
	/**
	 * 建立目录
	 * @param override
	 * @param path
	 * @param dir
	 */
	public static void mkdirs(boolean override, String path, String dir) {
		dir = path + File.separator + dir;
		log.debug("mkdirs:" + dir);
		File file = new File(dir);
		if (override == true) {
			file.mkdirs();
		} else {
			if (!file.exists() || !file.isDirectory()) {
				file.mkdirs();
			}
		}
	}

	/**
	 * 文件拷贝.
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	public static void copyFile(File in, File out) throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(in);
			fos = new FileOutputStream(out);
			byte[] buf = new byte[10240];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			log.error("", e);
			throw e;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e1) {
				log.error("", e1);

			}
		}
	}

	/**
	 * 写文本文件
	 * @since 1.0
	 */
	public static void writeFile(String filePath, String detail)
			throws Exception {
		try {
			OutputStreamWriter out = new OutputStreamWriter(
					new FileOutputStream(filePath), "UTF-8");
			out.write(detail);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw e;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * 获得某个目录下的所有文件
	 * @param path
	 * @return
	 * @throws Exception
	 */
  	public static Map<String,Long> getFileInfoFromDir(String path) throws Exception {
  		
  		
		path = path.replace("/", File.separator);

		if (StringUtils.isNotEmptyString(path) && path.startsWith(File.separator)) {
			path = path.substring(1);
		}
		if (StringUtils.isNotEmptyString(path) && !path.endsWith(File.separator)) {
			path += File.separator;
		}

		File dir = new File(baseDir+path);
		if (!dir.exists() || !dir.isDirectory()) {
			dir.canWrite();
			dir.mkdirs();
		}

		File[] fileList = dir.listFiles((FileFilter) FileFileFilter.FILE);

		Map<String,Long> fileInfoMap = new HashMap<String,Long>();
		for(int i = 0 ; i < fileList.length ; i++ ) {
			File file = fileList[i];
			if(file.isFile()) {
				fileInfoMap.put(file.getName(), file.length());
			}
		}
		return fileInfoMap;
	}
  	
  	public static String getFileSuffix(String fileName) {
  		return fileName.substring(fileName.lastIndexOf("."));
  	}
  	
  	public static String getMillisFileName(String fileName) {
  		return Calendar.getInstance().getTimeInMillis() + getFileSuffix(fileName);
  	}
  	
  	public static String getOrignalPlusMillisFileName(String fileName) {
  		return fileName.substring(0,fileName.lastIndexOf(".")) + "_" + getMillisFileName(fileName);
  	}

  	/**
  	 * 文件名字符集转换
  	 * @param fileName
  	 * @return
  	 */
/*	public static String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            if (log.isWarnEnabled()) {
                log.info("Don't support this encoding ...");
            }
        }
        return returnFileName;
    }*/
	
	/**
	 * 获取文件扩展名
	 * @param filename 文件名
	 * @param defExt   默认文件名
	 * @return
	 */
	public static String getExtension(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return defExt;
	}
	
	/**
	 * 获取图片 类型
	 * @param input an <code>Object</code> to be used as an input
     * source, such as a <code>File</code>, readable
     * <code>RandomAccessFile</code>, or <code>InputStream</code>.
	 * @return
	 */
	public static String getFormatName(Object input) {
		try {
			// Create an image input stream on the image 
			ImageInputStream iis = ImageIO.createImageInputStream(input);
			// Find all image readers that recognize the image format 
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (!iter.hasNext()) {
				// No readers found 
				return null;
			}
			// Use the first reader 
			ImageReader reader = iter.next();
			// Close stream 
			iis.close();

			// Return the format name 
			return reader.getFormatName();
		} catch (IOException e) {
			// 
		}
		// The image could not be read 
		return null;
	}    
	
	/**
	 * 复制文件
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath, String newPath) { 
		 try {     
			 int byteread = 0;     
			 File oldfile = new File(oldPath);   
			 if (oldfile.exists()) {
				 //文件存在时        
				 InputStream inStream = new FileInputStream(oldPath);
				 //读入原文件               
				 FileOutputStream fs = new FileOutputStream(newPath);  
				 byte[] buffer = new byte[1444];     
				 while ( (byteread = inStream.read(buffer)) != -1) { 
					 fs.write(buffer, 0, byteread); 
				}              
				inStream.close();      
			}     
		}catch (Exception e) {   
			 System.out.println("复制单个文件操作出错");    
			 e.printStackTrace();   
		} 
	}
	
	/**
	 * 读取文件返回字节数组
	 * @param path
	 * @return
	 */
	public static byte[] readFileToByteArray(File path){
		byte[] b = null;
		try {
			FileInputStream fi = new FileInputStream(path);  //创io流 设路径 
			int size = fi.available(); //字节数
			b = new byte[size];//创字节数组
			fi.read(b);//读取
            fi.close();//关流
		} catch (FileNotFoundException e) {//可能异常（找不到文件等）
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b ;	
	}
}
