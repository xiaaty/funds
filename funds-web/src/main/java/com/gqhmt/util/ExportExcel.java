package com.gqhmt.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.gqhmt.fss.architect.trade.bean.FssTradeApplyBean;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

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
public class ExportExcel<T> {


    public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out) throws IllegalAccessException, IOException {
        exportExcel(title, headers, dataset, out, "yyyy-MM-dd");
    }

    /**
     * 导出FssTradeApplyBean and
     */


    public void exportExcel(String title, String[] headers,
                            Collection<T> dataset, OutputStream out, String pattern) throws IllegalAccessException, IOException {
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
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

//-----------------------------2-1------------

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;

        int mapCount = 1;
        while (it.hasNext()) {
            index++;

            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值

            if (t instanceof Map) {

                HSSFRow staRow = sheet.createRow(index);
                HSSFCell cell = staRow.createCell(0);

                cell.setCellStyle(style2);
                cell.setCellValue(mapCount++);

                Iterator iter = ((Map) t).entrySet().iterator();

                while (iter.hasNext()) {
                    Map.Entry entry = (java.util.Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    Object value = entry.getValue();


                    if ("tradeApply".equals(key)) {
                        HSSFCell cell1 = staRow.createCell(1);
                        HSSFCell cell2 = staRow.createCell(2);
                        HSSFCell cell3 = staRow.createCell(3);
                        HSSFCell cell4 = staRow.createCell(4);
                        HSSFCell cell5 = staRow.createCell(5);
                        cell1.setCellStyle(style2);
                        cell2.setCellStyle(style2);
                        cell3.setCellStyle(style2);
                        cell4.setCellStyle(style2);
                        cell5.setCellStyle(style2);
                        FssTradeApplyBean tradeApply = (FssTradeApplyBean) value;
                        HSSFRichTextString text = new HSSFRichTextString(tradeApply.getApplyNo());
                        cell1.setCellValue(tradeApply.getBusinessNo());
                        cell2.setCellValue(text);
                        cell3.setCellValue(tradeApply.getCustName());
                        cell4.setCellValue(tradeApply.getCustMobile());
                        cell5.setCellValue(tradeApply.getTradeAmount().toString());
                    }
                    if ("tradeRecordList".equals(key)) {
                        int endCount = 0;

                        List<FssTradeRecordEntity> tradeRecordList = (List<FssTradeRecordEntity>) value;

                        if (tradeRecordList != null && tradeRecordList.size() > 0) {
                            for (FssTradeRecordEntity tradeRecord : tradeRecordList) {

                                if(endCount == 0){
                                    row = staRow;
                                    endCount++;
                                }else{
                                    row = sheet.createRow(index + endCount++);
                                }

                                HSSFCell cell0 = row.createCell(6);
                                HSSFCell cell1 = row.createCell(7);
                                cell0.setCellStyle(style2);
                                cell1.setCellStyle(style2);
                                String Amount = tradeRecord.getAmount().toString();
                                Date date = tradeRecord.getCreateTime();
                                HSSFRichTextString text = new HSSFRichTextString(Amount);
                                cell0.setCellValue(text);
                                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                                cell1.setCellValue(sdf.format(date));

                            }

                            index += endCount;

                        }
                    }
                }

            } else {
                Field[] fields = t.getClass().getDeclaredFields();
                for (short i = 0; i < fields.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellStyle(style2);
                    Field field = fields[i];

                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);

                    try {
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName,
                                new Class[]
                                        {});
                        Object value = getMethod.invoke(t, new Object[]
                                {});
                        // 判断值的类型后进行强制类型转换
                        String textValue = null;
                        // if (value instanceof Integer) {
                        // int intValue = (Integer) value;
                        // cell.setCellValue(intValue);
                        // } else if (value instanceof Float) {
                        // float fValue = (Float) value;
                        // textValue = new HSSFRichTextString(
                        // String.valueOf(fValue));
                        // cell.setCellValue(textValue);
                        // } else if (value instanceof Double) {
                        // double dValue = (Double) value;
                        // textValue = new HSSFRichTextString(
                        // String.valueOf(dValue));
                        // cell.setCellValue(textValue);
                        // } else if (value instanceof Long) {
                        // long longValue = (Long) value;
                        // cell.setCellValue(longValue);
                        // }
                        if (value instanceof Boolean) {
                            boolean bValue = (Boolean) value;
                            textValue = "是";
                            if (!bValue) {
                                textValue = "否";
                            }
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof byte[]) {
                            // 有图片时，设置行高为60px;
                            row.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(i, (short) (35.7 * 80));
                            // sheet.autoSizeColumn(i);
                            byte[] bsValue = (byte[]) value;
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                    1023, 255, (short) 6, index, (short) 6, index);
                            anchor.setAnchorType(2);
                            patriarch.createPicture(anchor, workbook.addPicture(
                                    bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }
                        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if (textValue != null) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                HSSFRichTextString richString = new HSSFRichTextString(
                                        textValue);
                                HSSFFont font3 = workbook.createFont();
                                font3.setColor(HSSFColor.BLUE.index);
                                richString.applyFont(font3);
                                cell.setCellValue(richString);
                            }
                        }
                    }catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        if(out!=null){
                            out.close();
                        }
                    }
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null){
                out.close();
            }
        }
    }

}
