package com.gqhmt.util;

import com.gqhmt.core.util.FssBeanUtil;
import com.gqhmt.fss.architect.trade.bean.FssTradeApplyBean;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gqhmt.core.util.XmlUtil.log;
import static com.gqhmt.pay.core.configer.ConfigAbstract.getClassPath;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/7/13.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/7/13.  xdw         1.0     1.0 Version
 */
public class ExportExcelUtil<T> {

    public void exportExcel(String title, String[] headers, List<T> listT) throws IllegalAccessException, IOException {
        exportExcel(title, headers, listT, null);
    }

    /**
     * 导出FssTradeApplyBean and
     */
    public void exportExcel(String title, String[] headers,
                            List<T> listT, String[] tParamName) throws IllegalAccessException, IOException {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 20);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");

        // 产生表格标题行
        int rowIndex = 0;


        HSSFRow row = sheet.createRow(rowIndex++);

        HSSFCell cellStart = row.createCell(0);
        cellStart.setCellStyle(style);
        HSSFRichTextString textStart = new HSSFRichTextString("序号");
        cellStart.setCellValue(textStart);

        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i+1);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

//-----------------------------2-1------------

        // 遍历集合数据，产生数据行
        for (T t : listT) {

            HSSFRow staRow = sheet.createRow(rowIndex++);
            HSSFCell cellHead = staRow.createCell(0);
            cellHead.setCellStyle(style2);
            cellHead.setCellValue(rowIndex - 1);

            if (tParamName != null && tParamName.length > 0) {
                for (int i = 0; i < tParamName.length; i++) {
                    HSSFCell cell = staRow.createCell(i + 1);
                    cell.setCellStyle(style2);

                    //如果不是map
                    if (t instanceof Map) {

                        Map map = (Map) t;

                        Object value = map.get(tParamName[i]);

                        if(value instanceof Date){
                            Date date = (Date)value;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            value = sdf.format(date);
                        }
                        HSSFRichTextString text = new HSSFRichTextString(String.valueOf(value));
                        cell.setCellValue(text);

                    } else {

                        PropertyDescriptor propertyDescriptor = FssBeanUtil.getPropertyDescriptor(t.getClass(), tParamName[i]);

                        Method getMethod = propertyDescriptor.getReadMethod();
                        Object value = null;
                        try {
                            value = getMethod.invoke(t);
                        } catch (InvocationTargetException e) {
                            LogUtil.error(this.getClass(), e.getMessage());
                            e.printStackTrace();
                        }

                        if(value instanceof Date){
                            Date date = (Date)value;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            value = sdf.format(date);
                        }

                        HSSFRichTextString text = new HSSFRichTextString(String.valueOf(value));
                        cell.setCellValue(text);
                    }
                }
            } else {
                PropertyDescriptor[] propertyDescriptors = FssBeanUtil.getPropertyDescriptors(t.getClass());
                for (int i = 0; i <= propertyDescriptors.length; i++) {
                    HSSFCell cell = staRow.createCell(i + 1);
                    cell.setCellStyle(style2);

                    Method getMethod = propertyDescriptors[i].getReadMethod();
                    Object value = null;
                    try {
                        value = getMethod.invoke(t);
                    } catch (InvocationTargetException e) {
                        LogUtil.error(this.getClass(), e.getMessage());
                        e.printStackTrace();
                    }
                    HSSFRichTextString text = new HSSFRichTextString(String.valueOf(value));
                    cell.setCellValue(text);
                }
            }
        }

        //------------------------------- 3-1 -----------------------------------------------

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String path = getClassPath();
        File filepath = new File(path + File.separator + "excel");
        String address = filepath + File.separator + sdf.format(new Date()) + ".xls";

        String fileName = checkFileName(address, 0);

        OutputStream out = new FileOutputStream(fileName);
        System.out.println("导出路径： " + fileName);

        try {
            workbook.write(out);
        } catch (IOException e) {
            LogUtil.error(this.getClass(), e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

        //. 弹出下载对话框
        File exportFile = new File(fileName);

        int index = fileName.lastIndexOf(File.separator);
        String excelName = fileName.substring(index);

        if (exportFile == null) {
            log.error("生成excel错误! exportFile 为空");
            return;
        }

        //先建立一个文件读取流去读取这个临时excel文件
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(exportFile);
        } catch (FileNotFoundException e) {
            log.error("生成excel错误! " + exportFile + " 不存在!", e);
            return;
        }
        // 设置响应头和保存文件名
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //这个一定要设定，告诉浏览器这次请求是一个下载的数据流
        response.setContentType("APPLICATION/OCTET-STREAM");
        try {
            excelName = URLEncoder.encode(excelName, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            log.error("转换excel名称编码错误!", e1);
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\"");
        // 写出流信息
        int b = 0;
        try {
            //从服务器下载到本地
            PrintWriter out1 = response.getWriter();
            while ((b = fs.read()) != -1) {
                out1.write(b);
            }
            if (fs != null) {
                fs.close();
            }
            if (out1 != null) {
                out1.close();
            }
            log.debug(excelName + " 文件下载完毕.");
        } catch (Exception e) {
            log.error(excelName + " 下载文件失败!.", e);
        }

    }

    //验证文件是否存在。
    public String checkFileName(String fileName, int i) {
        //验证文件是否存在
        String addressFileName;

        int index = fileName.lastIndexOf(".");
        int index2 = fileName.lastIndexOf(File.separator);
        String fileEnd = fileName.substring(index);
        String fileStart = fileName.substring(0, index);

        String fileFolder = fileStart.substring(0, index2);
        // 验证文件夹是否存在
        File folder = new File(fileFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String newFileName;
        if (i != 0) {
            newFileName = fileStart + "(" + i + ")" + fileEnd;
        } else {
            newFileName = fileName;
        }
        i++;
        File file = new File(newFileName);

        if (!file.exists()) {
            addressFileName = newFileName;
        } else {
            addressFileName = checkFileName(fileName, i);
        }

        return addressFileName;
    }

}
