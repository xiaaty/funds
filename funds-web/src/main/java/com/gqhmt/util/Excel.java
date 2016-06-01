package com.gqhmt.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Excel<E> {

//
//	@SuppressWarnings("unchecked")
//	public List<E> readContent(MultipartFile file, String[] columnNames,
//			Class<? extends Object> targetClass,Map<String,String > messageMap){
//		List<E> list = new ArrayList<E>();
//		String suffix="";
//		Workbook wb = null;
//		CellStyle style=null;
//		try{
//			if (file.getOriginalFilename().endsWith("xls")) {
//				suffix=".xls";
//				wb = new HSSFWorkbook(file.getInputStream());
//			} else {
//				suffix=".xlsx";
//				wb = new XSSFWorkbook(file.getInputStream());
//			}
//			Sheet sheetAtIndex = wb.getSheetAt(0);
//			boolean isErrorFlag = false;
//			int nullLineNumber = 0;
//			if(sheetAtIndex.getRow(0).getLastCellNum()<columnNames.length){
//				messageMap.put("code","0004");
//				return null;
//			}
//			for (int rowStart = 1, rowEnd = sheetAtIndex.getLastRowNum(); rowStart <= rowEnd; rowStart++) {
//				boolean flag=true;
//				Row rowAtIndex = sheetAtIndex.getRow(rowStart);
//				if(nullLineNumber>=5){
//					break;
//				}
//				if (isNull(rowAtIndex)){
//					nullLineNumber++;
//					continue;
//				}
//				E target = (E) targetClass.newInstance();
//				for (int i = 0, length = columnNames.length; i < length; i++) {
//					Field field = targetClass.getDeclaredField(columnNames[i]);
//					if (field.getType().getName().equals("java.lang.String")) {
//					try{
//						Method meth = targetClass.getDeclaredMethod(
//							getSetMethod(columnNames[i]), String.class);
//						meth.invoke(target, getCellValue(rowAtIndex.getCell(i),true));
//
//						BillLogUtil.debug(this.getClass(), (rowStart+1)+":"+(i+1)+":"+rowAtIndex.getCell(i));
//					}catch(Exception e){
//						e.printStackTrace();
//						style=style==null?getStyle(wb):style;
//						flag=false;
//						rowAtIndex.getCell(i).setCellStyle(style);
//					}
//				} else if (field.getType().getName().equals("java.lang.Long")
//						|| field.getType().getName().equals("long")) {
//					try{
//					Method meth = targetClass.getDeclaredMethod(
//							getSetMethod(columnNames[i]), Long.class);
//					meth.invoke(target, getLongCellValue(rowAtIndex.getCell(i)));
//					}catch(Exception e){
//					}
//				} else if (field.getType().getName().equals("java.lang.Double")
//						|| field.getType().getName().equals("double")) {
//					try{
//						Method meth = targetClass.getDeclaredMethod(
//							getSetMethod(columnNames[i]), Double.class);
//						meth.invoke(target,
//							getDoubleCellValue(rowAtIndex.getCell(i)));
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				}
//			}
//			if(!flag){
//				isErrorFlag = true;
//				continue;
//			}
//			list.add(target);
//		}
//		if(isErrorFlag){
//			try {
//				String fileName=System.currentTimeMillis()+suffix;
//				messageMap.put("fileName",fileName);
//				File uploadFile=new File(Resources.getString("excel.upload"));
//				if(!uploadFile.exists())
//					uploadFile.mkdirs();
//				OutputStream out=new FileOutputStream(new File(Resources.getString("excel.upload"),fileName));
//				wb.write(out);
//				out.close();
//			} catch (FileNotFoundException e) {
//				messageMap.put("code","0005");
//			} catch (IOException e) {
//				messageMap.put("code","0006");
//			}
//			return null;
//		}
//
//		BillLogUtil.debug(this.getClass(), list.size());
//
//		return list;
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	private String getSetMethod(String string) {
//		String tmp = "set";
//		tmp = tmp + string.substring(0, 1).toUpperCase() + string.substring(1);
//		return tmp;
//	}
//
//	@SuppressWarnings("unchecked")
//	public void exportExcel(String fileName, String sheetName,
//			Map<String, String> titleMap, List<E> data,
//			HttpServletRequest request, HttpServletResponse response)
//			throws NoSuchMethodException, SecurityException,
//			IllegalAccessException, IllegalArgumentException,
//			InvocationTargetException {
//		try {
//			OutputStream os = response.getOutputStream();
//			response.reset();
//			response.setHeader("Content-disposition", "attachment; filename="
//					+ getCorrectFileName(request, fileName));
//			response.setContentType("application/msexcel");
//
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			HSSFSheet sheet = workbook.createSheet(sheetName);
//			sheet.setDefaultColumnWidth(15);
//
//			HSSFCellStyle style = workbook.createCellStyle();
//			style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//
//			HSSFFont font = workbook.createFont();
//			font.setColor(HSSFColor.VIOLET.index);
//			font.setFontHeightInPoints((short) 12);
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//
//			style.setFont(font);
//
//			HSSFCellStyle style2 = workbook.createCellStyle();
//			style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//			style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//			style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//			HSSFFont font2 = workbook.createFont();
//			font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//
//			style2.setFont(font2);
//
//			HSSFRow row = sheet.createRow(0);
//			HSSFCell cell = null;
//			int i = 0;
//			for (String title : titleMap.keySet()) {
//				cell = row.createCell(i++);
//				cell.setCellStyle(style);
//				cell.setCellValue(new HSSFRichTextString(title));
//			}
//			@SuppressWarnings("rawtypes")
//			Class classObj = null;
//			Method meth = null;
//			int r = 1;
//			for (E t : data) {
//				i = 0;
//				row = sheet.createRow(r++);
//				classObj = t.getClass();
//				for (String methodName : titleMap.values()) {
//					cell = row.createCell(i++);
//					cell.setCellStyle(style2);
//					meth = classObj.getDeclaredMethod(methodName);
//					Object o = meth.invoke(t);
//					cell.setCellValue(new HSSFRichTextString(o == null ? "" : o
//							.toString()));
//				}
//			}
//			workbook.write(os);
//			os.flush();
//			os.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public static void exportExcel(String fileName, String sheetName,
//			Map<String, String> titleMap,Map<String,String> listTitleMap, List<BankRecord> data,
//			HttpServletRequest request, HttpServletResponse response)
//			throws NoSuchMethodException, SecurityException,
//			IllegalAccessException, IllegalArgumentException,
//			InvocationTargetException {
//		try {
//			OutputStream os = response.getOutputStream();
//			response.reset();
//			response.setHeader("Content-disposition", "attachment; filename="
//					+ getCorrectFileName(request, fileName));
//			response.setContentType("application/msexcel");
//
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			HSSFSheet sheet = workbook.createSheet(sheetName);
//			sheet.setDefaultColumnWidth(15);
//
//			HSSFCellStyle style = workbook.createCellStyle();
//			style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//
//			HSSFFont font = workbook.createFont();
//			font.setColor(HSSFColor.VIOLET.index);
//			font.setFontHeightInPoints((short) 12);
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//
//			style.setFont(font);
//
//			HSSFCellStyle style2 = workbook.createCellStyle();
//			style2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//			HSSFRow row = sheet.createRow(0);
//			HSSFCell cell = null;
//			int i = 0;
//
//			for (String title :titleMap.keySet()) {
//				cell = row.createCell(i++);
//				cell.setCellStyle(style);
//				cell.setCellValue(new HSSFRichTextString(title));
//			}
//			for (String title :listTitleMap.keySet()) {
//				cell = row.createCell(i++);
//				cell.setCellStyle(style);
//				cell.setCellValue(new HSSFRichTextString(title));
//			}
//			@SuppressWarnings("rawtypes")
//			Class classObj =BankRecord.class;
//			@SuppressWarnings("rawtypes")
//			Class classDetailObj = MatchDetail.class;
//			Method meth = null;
//			int r = 1;
//			int s=0;
//			for(BankRecord record:data){
//				s++;
//				i=0;
//				row = sheet.createRow(r++);
//				for (String methodName : titleMap.values()) {
//					cell = row.createCell(i++);
//					meth = classObj.getDeclaredMethod(methodName);
//					Object o = meth.invoke(record);
//					cell.setCellValue(new HSSFRichTextString(o == null ? "" : o
//							.toString()));
//				}
//				List<MatchDetail> detailList=record.getDetailList();
//				int m=0;
//				if(detailList!=null&&detailList.size()>0){
//					for(MatchDetail detail:detailList){
//						if(m>0)
//							row=sheet.createRow(r++);
//						m++;
//						i=titleMap.size();
//						for (String methodName : listTitleMap.values()) {
//							cell = row.createCell(i++);
//							if(s%2==0)
//								cell.setCellStyle(style2);
//							meth = classDetailObj.getDeclaredMethod(methodName);
//							Object o = meth.invoke(detail);
//							cell.setCellValue(new HSSFRichTextString(o == null ? "" : o
//									.toString()));
//						}
//					}
//				}
//
//			}
//
//			workbook.write(os);
//			os.flush();
//			os.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static String getCorrectFileName(HttpServletRequest request,
//			String fileName) throws UnsupportedEncodingException {
//		final String userAgent = request.getHeader("USER-AGENT");
//		if (userAgent.indexOf("MSIE") > -1) {// IE娴忚鍣�
//			return URLEncoder.encode(fileName, "UTF8");
//		} else if (userAgent.indexOf("Mozilla") > -1) {// google,鐏嫄娴忚鍣�
//			return new String(fileName.getBytes(), "ISO8859-1");
//		} else {
//			return URLEncoder.encode(fileName, "UTF8");// 鍏朵粬娴忚鍣�
//		}
//	}
//
//	private String getCellValue(Cell cell,boolean isNull) throws Exception{
//		if (cell == null && isNull){
//			return "";
//		}else if (cell == null){
//			throw new Exception("is not allow null");
//		}
//		switch (cell.getCellType()) {
//		case Cell.CELL_TYPE_NUMERIC:
//			return new BigDecimal(cell.getNumericCellValue()).toPlainString();
//		case Cell.CELL_TYPE_STRING:
//			return cell.getStringCellValue();
//		}
//		if( isNull){
//			return "";
//		}else{
//			throw new Exception("is not allow null");
//		}
//	}
//
//	private Double getDoubleCellValue(Cell cell){
//		if (cell == null)
//			return 0.0;
//		switch (cell.getCellType()) {
//		case Cell.CELL_TYPE_NUMERIC:
//			return cell.getNumericCellValue();
//		case Cell.CELL_TYPE_STRING:
//			String tmp = cell.getStringCellValue().trim();
//			Double value = 0.0;
//			try {
//				value = Double.valueOf(tmp);
//				return value;
//			} catch (RuntimeException e) {
//				e.printStackTrace();
//				throw e;
//			}
//		}
//		return 0.0;
//	}
//
//	private Long getLongCellValue(Cell cell){
//		if (cell == null)
//			return 0L;
//		switch (cell.getCellType()) {
//		case Cell.CELL_TYPE_NUMERIC:
//			return Double.valueOf(cell.getNumericCellValue()).longValue();
//		case Cell.CELL_TYPE_STRING:
//			String tmp = cell.getStringCellValue();
//			Double value = 0.0;
//			try {
//				value = Double.valueOf(tmp);
//				return value.longValue();
//			} catch (RuntimeException e) {
//				e.printStackTrace();
//				throw e;
//			}
//
//
//		}
//		return 0L;
//	}
//
//	/**
//	 * 判断行是否为空
//	 * @param row
//	 * @return
//	 */
//	private boolean isNull(Row row){
//		if (row == null) {
//			return true;
//		}
//		int number = row.getLastCellNum();
//		boolean isAllNull= true;
//		for (int i = 0; i < number; i++) {
//			Cell cell = row.getCell(i);
//			if (!isNull(cell)) {
//				isAllNull = false;
//			}
//		}
//		if(isAllNull){
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 判断单元格为空
//	 * @param cell
//	 * @return
//	 */
//	public boolean isNull(Cell cell){
//		if(cell == null){
//			return true;
//		}
//		if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
//			return true;
//		}
//		if(cell.getCellType() == Cell.CELL_TYPE_STRING && (cell.getStringCellValue() == null || "".equals(cell.getStringCellValue().trim())) ){
//			return true;
//		}
//
//		return false;
//	}
//
//	/**
//	 * 获取错误格式样式
//	 * @param wb
//	 * @return
//	 */
//	private CellStyle getStyle(Workbook wb){
//		CellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style.setFillForegroundColor(HSSFColor.YELLOW.index);
//		return style;
//	}
}
