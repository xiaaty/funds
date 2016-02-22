package com.gqhmt.core.util;

import java.awt.Image;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import com.gqhmt.core.FssException;
import com.gqhmt.util.StringUtils;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import org.w3c.dom.NodeList;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class ImageUtil {

	/**
	 * jpeg格式的图片都统一格式化成RGB格式.
	 * @param args
	 * @throws Exception
	 */
	public static byte[] cmyk2rgb(byte[] args) throws Exception {
		try {
			BufferedImage i1 = readImage(args);
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			BufferedOutputStream out = new BufferedOutputStream(byteArray);

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(i1);
			param.setQuality(1, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(i1);

			return byteArray.toByteArray();
		} catch (Exception e) {
			return args;
		}

	}

	/**
	 * 根据图片绝对路径进行压缩
	 * @param localFullPathFileName 文件路径 
	 * @param width
	 * @param height
	 * @throws SystemException
	 */
	public static byte[] compactImage(String localFullPathFileName,int width, int height) throws Exception{
		return compactImage(new File(localFullPathFileName),width,height);
	}
	
	/**
	 * 根据指定文件进行图片压缩
	 * @param localFile 指定图片对象
	 * @param width     宽度
	 * @param height    高度
	 * @throws Exception
	 */
	public static byte[] compactImage(File localFile,int width, int height) throws Exception{
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		try {
			fileInputStream = new FileInputStream(localFile);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			return compactImage(bufferedInputStream,width,height);
		} finally {
			if(fileInputStream != null) {
				fileInputStream.close();
			}
			if(bufferedInputStream != null) {
				bufferedInputStream.close();
			}
		}
	}

	/**
	 * 根据图片输入流压缩
	 * @param dataInputStream 图片输入流
	 * @param width 宽度
	 * @param height 高度
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] compactImage(InputStream dataInputStream,int width, int height) throws Exception{
		ByteArrayOutputStream b = null;
		try {
			b = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while (true) {
				int r = dataInputStream.read(buffer);
				if (r == -1) {
					break;
				}
				b.write(buffer);
			}
			return compactImage(b.toByteArray(),width,height);
		} finally {
			if(b != null) {
				b.close();
			}
		}
	}
	
	/**
	 * 根据指定大小对图片字节压缩
	 * @param image 图片字节byte[]
	 * @param width  宽度
	 * @param height 高度
	 * @return byte[]
	 * @throws SystemException
	 */
	public static byte[] compactImage(byte[] image,int width, int height) throws FssException{

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			// 获得图片
			bis = new BufferedInputStream(new ByteArrayInputStream(image));
			Image img = ImageIO.read(bis);
	        
			// 计算宽度高度
			int orignalWidth = img.getWidth(null);
	        int orignalHeight = img.getHeight(null);
	        double widthBi = (double)orignalWidth/(double)width;
	        double heightBi = (double)orignalHeight/(double)height;
	        int destWidth;
	        int destHeight;

			if(widthBi > 1 || heightBi > 1) {
				if(widthBi > heightBi) {
					destWidth = width;
					destHeight = (int)(orignalHeight/widthBi);
				} else {
					destHeight = height;
					destWidth = (int)(orignalWidth/heightBi);
				}
			} else {
				destWidth = orignalWidth;
				destHeight = orignalHeight;
			}

	        // 压缩图片
	        BufferedImage bid = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
			bid.getGraphics().drawImage(img, 0, 0,destWidth, destHeight, null);

			// 把图片转化成输出流
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        bos = new BufferedOutputStream(byteArrayOutputStream);
			JPEGCodec.createJPEGEncoder(bos).encode(bid);

			// 返回新图片
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw new FssException("压缩图片失败:" + e.getMessage());
		}finally {
			try {
				if(bos != null){
					bos.close();
				}
				if(bis != null){
					bis.close();
				}
			} catch (IOException ioe) {
				throw new FssException("关闭文件流失败:" + ioe.getMessage());
			}
		}
	}

	/**
	 * 读入图片至图片缓冲池中
	 */
	@SuppressWarnings("rawtypes")
	private static BufferedImage readImage(byte[] args) throws IOException {
		// Get an ImageReader.
		ImageInputStream input = ImageIO.createImageInputStream(new ByteArrayInputStream(args));
		Iterator readers = ImageIO.getImageReaders(input);
		if (readers == null || !readers.hasNext()) {
			throw new RuntimeException("No ImageReaders found");
		}
		ImageReader reader = (ImageReader) readers.next();
		reader.setInput(input);
		String format = reader.getFormatName();

		if ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) {
			IIOMetadata metadata = reader.getImageMetadata(0);
			String metadataFormat = metadata.getNativeMetadataFormatName();
			IIOMetadataNode iioNode = (IIOMetadataNode) metadata
					.getAsTree(metadataFormat);

			NodeList children = iioNode.getElementsByTagName("app14Adobe");
			if (children.getLength() > 0) {
				iioNode = (IIOMetadataNode) children.item(0);
				int transform = Integer.parseInt(iioNode
						.getAttribute("transform"));
				Raster raster = reader.readRaster(0, reader
						.getDefaultReadParam());

				if (input != null) {
					input.close();
				}
				reader.dispose();

				return createJPEG4(raster, transform);
			}
		}
		throw new RuntimeException("No ImageReaders found");
	}

	/**
	 * Java's ImageIO can't process 4-component images
	 * <p/>
	 * and Java2D can't apply AffineTransformOp either,
	 * <p/>
	 * so convert raster data to RGB.
	 * <p/>
	 * Technique due to MArk Stephens.
	 * <p/>
	 * Free for any use.
	 */
	private static BufferedImage createJPEG4(Raster raster, int xform) {
		int w = raster.getWidth();
		int h = raster.getHeight();
		byte[] rgb = new byte[w * h * 3];
		// if (Adobe_APP14 and transform==2) then YCCK else CMYK
		if (xform == 2) { // YCCK -- Adobe
			float[] Y = raster.getSamples(0, 0, w, h, 0, (float[]) null);
			float[] Cb = raster.getSamples(0, 0, w, h, 1, (float[]) null);
			float[] Cr = raster.getSamples(0, 0, w, h, 2, (float[]) null);
			float[] K = raster.getSamples(0, 0, w, h, 3, (float[]) null);
			for (int i = 0, imax = Y.length, base = 0; i < imax; i++, base += 3) {
				float k = 220 - K[i], y = 255 - Y[i], cb = 255 - Cb[i], cr = 255 - Cr[i];

				double val = y + 1.402 * (cr - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff: (byte) (val + 0.5);

				val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base + 1] = val < 0.0 ? (byte) 0: val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);

				val = y + 1.772 * (cb - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base + 2] = val < 0.0 ? (byte) 0: val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);
			}
		} else {
			// assert xform==0: xform;
			// CMYK
			int[] C = raster.getSamples(0, 0, w, h, 0, (int[]) null);
			int[] M = raster.getSamples(0, 0, w, h, 1, (int[]) null);
			int[] Y = raster.getSamples(0, 0, w, h, 2, (int[]) null);
			int[] K = raster.getSamples(0, 0, w, h, 3, (int[]) null);
			for (int i = 0, imax = C.length, base = 0; i < imax; i++, base += 3) {
				int c = 255 - C[i];
				int m = 255 - M[i];
				int y = 255 - Y[i];
				int k = 255 - K[i];
				float kk = k / 255f;
				rgb[base] = (byte) (255 - Math.min(255f, c * kk + k));
				rgb[base + 1] = (byte) (255 - Math.min(255f, m * kk + k));
				rgb[base + 2] = (byte) (255 - Math.min(255f, y * kk + k));
			}
		}
		// from other image types we know InterleavedRaster's can be
		// manipulated by AffineTransformOp, so create one of
		// those.
		raster = Raster.createInterleavedRaster(new DataBufferByte(rgb,rgb.length), w, h, w * 3, 3, new int[] { 0, 1, 2 }, null);
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
		ColorModel cm = new ComponentColorModel(cs, false, true,Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		return new BufferedImage(cm, (WritableRaster) raster, true, null);
	}

	
    /** 
     * 将字节流转成图片
     */ 
    public static int saveToImgByBytes(byte[] imgFile,String imgName, String bidId){
		String spath = IConstants.OmitGraphFilePath+"/"+bidId;
		return saveToImgByBytesNotPath(imgFile,imgName,spath);
    }
	/**
	 * 将字节流转成图片
	 */
	public static int saveToImgByBytesNotPath(byte[] imgFile,String imgName,String spath){
		int stateInt =1;
		if(imgFile.length>0){
			try{
				/**
				 * 本地测试用 ↓【上线请注释】--------------------------------------------------------------------------------------------
				 */
//        		 HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        		 String path = request.getSession().getServletContext().getRealPath(IConstants.OmitGraphFilePath);
//        		 String spath = path+"/"+bidId;
				/**
				 * 本地测试用 ↑【上线请注释】--------------------------------------------------------------------------------------------
				 */

				FileUtil.uploadFile(spath,imgName,imgFile);
				File file= createNewFile(spath);
				FileOutputStream fos=new FileOutputStream(file);
				InputStream in =new ByteArrayInputStream(imgFile);
				byte[] b =new byte[1024];
				int nRead =0;
				while((nRead = in.read(b)) != -1) {
					fos.write(b,0, nRead);
				}
				fos.flush();
				fos.close();
			}catch(Exception e) {
				stateInt =0;
			}
		}
		return stateInt;
	}
	    
	/**
	 * 创建新文件
	 * @param fileDirectoryAndName
	 */
	public static File createNewFile(String fileDirectoryAndName){
		File myFile=null;
		try{
			myFile = new File(fileDirectoryAndName);
			myFile.createNewFile();
		}catch(Exception ex){
			LogUtil.error(ImageUtil.class,ex);
			System.out.println("无法创建新文件！");
		}
		return myFile;
	}
	    
	/**
	  * @Title: deleteByUserIdAndName 
	  * @Description: 删除省略图 
	  */
	public static void deleteUploadFile(String deletePath, String cttName) {
		/**
		  * 本地测试用 ↓【上线请注释】--------------------------------------------------------------------------------------------
		  */
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		String path = request.getSession().getServletContext().getRealPath(deletePath);
//		String spath = path + "/" + cttName;
		/**
		  * 本地测试用 ↑【上线请注释】--------------------------------------------------------------------------------------------
		  */
        
		String spath = deletePath + "/" + cttName;
		FileUtil.delete(spath);
	}
	
	/**
	 * @throws Exception 
	 * @Description:  根据相对路径保存文件到项目附件根目录中
	 * @throws
	 */
	public static String saveAttachmentByRelativePath(String bidId,String fileName ,byte[] FileBytes) throws Exception{
		//contractInfo保存url路径
		String savePath ;
		/**
		 * 本地测试用 ↓【上线请注释】--------------------------------------------------------------------------------------------
		 */
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		String path = request.getSession().getServletContext().getRealPath(IConstants.OriginalFilePath);
//		String dictPath =  path+"/"+bidId;
		/**
		 * 本地测试用 ↑【上线请注释】--------------------------------------------------------------------------------------------
		 */
		//保存上传文件的文件夹的绝对路径
		String dictPath =  IConstants.OriginalFilePath+"/"+bidId;
	    FileUtil.uploadFile(dictPath, fileName, FileBytes);
	    savePath = dictPath+"/"+fileName; 
		return savePath;
	};

	/** 
	* @Title: getBytesByAbsoluteUrl 
	* @Description:  根据绝对路径获取文件byte[]
	* @param   absolueUrl:绝对路径
	*/
	public static byte[] getBytesByAbsoluteUrl(String absolueUrl) {
		byte[] fileByte = new byte[]{};
		if(StringUtils.isNotEmptyString(absolueUrl)){
			File file = new File(absolueUrl);
			fileByte = FileUtil.readFileToByteArray(file);
		}
		return fileByte;
	};
	
	/**
	  * @Title: listToString 
	  * @Description: 将List集合转换为字符串 
	  */
	public static  String listToString(List<Object> list, char separator) {  
		StringBuilder sb = new StringBuilder();  
		for (int i = 0; i < list.size(); i++) {  
			sb.append(list.get(i)).append(separator);  
		}  
		return sb.toString().substring(0,sb.toString().length()-1);  
	} 
	
	/**
	 * 通过附件上传路径删除附件
	 * @param adjunctPath
	 * @return 
	 */
	public static void deleteImgeByAdjunctPath( String adjunctPath){
		String sf[] = adjunctPath.split("/");
		String directory = "";
		String name = sf[sf.length-1];
		for (int i = 0; i < sf.length; i++) {
			if((i != (sf.length-1)) && ((sf[i]!=null) && (!"".equals(sf[i])))){
				directory = directory + "/" + sf[i];
			}
		}
		directory += "/";
		ImageUtil.deleteUploadFile(directory,name);
	}
}
