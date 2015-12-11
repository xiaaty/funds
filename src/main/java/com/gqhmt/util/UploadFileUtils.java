package com.gqhmt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadFileUtils {

	/**
	 * 上传文件方法
	 * 
	 * @param filePath
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 王记涛 2014-12-12
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String uploadFile(String filePath, String fileName, MultipartHttpServletRequest request) throws Exception {
		String savePath = request.getSession().getServletContext()
				.getRealPath(filePath);
		File f1 = new File(savePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		f1 = null;

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("UTF-8");
		List fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			ex.printStackTrace();
			return "0";
		}

		if (fileList == null) {
			return "0";
		}
		Iterator<FileItem> it = fileList.iterator();
		String name = "";
		String extName = "";
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				name = item.getName();
				if (name == null || name.trim().equals("")) {
					continue;
				}

				// 文件后缀名如：.mp3
				if (name.lastIndexOf(".") >= 0) {
					extName = name.substring(name.lastIndexOf("."));
				}
				// 获取当前文件名
				// fileName = name.replaceAll("\\s", "");
				// fileName = fileName.substring(0, fileName.lastIndexOf(".") -
				// 1);
				// 自定义文件名
				/* 日期+100以内的随机数 */
				File f = new File(savePath + "/" + fileName + extName);

				while (f.exists()) {// 如果文件存在，在文件名后加上随机数做区分
					int rand = (int) Math.round(Math.random() * 100);
					fileName += "_" + rand;
					f = new File(savePath + "/" + fileName + extName);
				}
				f = null;

				File saveFile = new File(savePath + "/" + fileName + extName);
				try {
					item.write(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				filePath = filePath + "/" + fileName + extName;
			}
		}
		return filePath;
	}

	/**
	 * 上传文件方法
	 * 
	 * @param filePath
	 * @param fileName
	 * @param request
	 * @return
	 * @throws Exception
	 * @author 王记涛 2014-12-12
	 */
	public static String upload(MultipartFile file, String filePath,
			String fileName, HttpServletRequest request) {
		if (!file.isEmpty()) {
			String savePath = request.getSession().getServletContext()
					.getRealPath(filePath);
			File f1 = new File(savePath);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			f1 = null;
			String name="";
			String extName = "";
			try {
				byte[] bytes = file.getBytes();
				name = file.getName();
				if (name == null || name.trim().equals("")) {
					return null;
				}
				// 文件后缀名如：.mp3
				if (name.lastIndexOf(".") >= 0) {
					extName = name.substring(name.lastIndexOf("."));
				}
				FileCopyUtils.copy(bytes, f1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			filePath = filePath + "/" + fileName + extName;
		} 
		return filePath;
	}
	
	/**
	 * 上传文件方法
	 * 
	 * @param filePath
	 * @param fileName
	 * @param request
	 * @return
	 * @throws Exception
	 * @author 王记涛 2014-12-12
	 */
	public static String upload( String filePath, String fileName,MultipartHttpServletRequest multipartRequest){
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
		String savePath = multipartRequest.getSession().getServletContext()
				.getRealPath(filePath);
		File f1 = new File(savePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		f1 = null;
		String name="";
		String extName = "";
		String newFileName = "";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
            // 上传文件名    
            // System.out.println("key: " + entity.getKey());    
            MultipartFile mf = entity.getValue();    
            name = mf.getOriginalFilename();  
            // 文件后缀名如：.mp3
			if (name.lastIndexOf(".") >= 0) {
				extName = name.substring(name.lastIndexOf("."));
			}
            
			//文件保存路径
			newFileName = savePath + "/" + fileName + extName;
            File uploadFile = new File(newFileName);    
            try {  
                FileCopyUtils.copy(mf.getBytes(), uploadFile); 
            } catch (IOException e) {  
            	e.printStackTrace();  
            }    
        }
        return filePath + "/" + fileName + extName;
	}
	
	/**
	 * 附件下载
	 * 
	 * @param annexUrl
	 * @param response
	 * @param request
	 */
	public static void matterDownload(String annexUrl,HttpServletResponse response,HttpServletRequest request){  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("multipart/form-data");  
        try {
        	response.setHeader("Content-Disposition", "attachment;fileName="+new String(annexUrl.getBytes("gb2312"),"iso8859-1"));
            String path=request.getSession().getServletContext().getRealPath("");
            InputStream inputStream=new FileInputStream(path+annexUrl);  
            OutputStream os=response.getOutputStream();  
            byte[] b=new byte[1024];  
            int length;  
            while((length=inputStream.read(b))>0){  
                os.write(b,0,length);  
            }  
            inputStream.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
   }
}
