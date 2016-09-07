package com.gqhmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.gqhmt.annotations.DateType;
import com.gqhmt.annotations.DateTypeEnum;
import com.gqhmt.annotations.FetchValueByMethodOfClass;
import com.gqhmt.annotations.Loop;
import com.gqhmt.annotations.NotNull;
import com.gqhmt.annotations.NumberType;
import com.gqhmt.annotations.NumberTypeEnum;
import com.gqhmt.util.exception.ReadExcelErrorException;
import com.gqhmt.util.exception.ReadExcelException;

/**
 * Filename:    com.gqhmt.util
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2014/12/9 18:15
 * Description:
 * <p>读取excel文件工具类</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2014/12/9  于泳      1.0     1.0 Version
 */
public class ReadExcelUtil<T> {

    private static ApplicationContext context;
    static {
        context = ContextLoader.getCurrentWebApplicationContext();
    }

    private CellStyle errorStyle = null;
    private String baseDir = "";
    private Class<T> targetClass;
    public ReadExcelUtil(String baseDir,Class<T> targetClass){
        this.baseDir = baseDir;
        this.targetClass = targetClass;
    }
    /**
     *
     * @param file
     * @param columnNames
     * @param index
     * @return
     * @throws ReadExcelException
     */
    public List<T> getExcelData(MultipartFile file, String[] columnNames, int index) throws ReadExcelException, ReadExcelErrorException {

        if(file == null){
            throw new ReadExcelException("0001");
        }
         boolean error = false;

        List<T> list = new ArrayList<T>();
        try {
            Workbook wb = getWorkBook(file);
            Sheet sheet  = wb.getSheetAt(index);
            if(sheet == null  || sheet.getRow(0).getLastCellNum()<columnNames.length){
                throw new ReadExcelException("0004");

            }
            int nullLineNumber = 0;
            for(int i = 1;i<=sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                if(nullLineNumber>=5){
                    break;
                }
                if (isNull(row)){
                    nullLineNumber++;
                    continue;
                }
                try {
                    T t = readLine(row, columnNames);
                    list.add(t);
                }catch (ReadExcelException e){
                    error = true;
                }
            }

            if(error){
                try {
                    String fileName=System.currentTimeMillis()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    LogUtil.debug(this.getClass(),baseDir);
                    File uploadFile=new File(baseDir);
                    if(!uploadFile.exists())
                        uploadFile.mkdirs();
                    OutputStream out=new FileOutputStream(new File(baseDir,fileName));
                    wb.write(out);
                    out.close();
                    throw new ReadExcelErrorException(fileName);
                } catch (FileNotFoundException e) {
                    LogUtil.error(this.getClass(), e.getMessage(), e);
                  throw new ReadExcelException("0005");
                } catch (IOException e) {
                    LogUtil.error(this.getClass(), e.getMessage(), e);
                    throw new ReadExcelException("0006");
                }
            }

        } catch (IOException e) {
            LogUtil.error(this.getClass(), e.getMessage(), e);
        }



        return list;

    }


    /**
     * 根据文件获取POI workbook对象
     * @param file
     * @return
     * @throws IOException
     */
    public Workbook getWorkBook(MultipartFile file) throws IOException {
            Workbook wb = null;
        try {
            if (file.getOriginalFilename().endsWith("xls")) {
                wb = new HSSFWorkbook(file.getInputStream());
            } else {
                wb = new XSSFWorkbook(file.getInputStream());
            }
        }catch (IOException e){
            throw e;
        }
        errorStyle = getErrrorStyle(wb);
        return wb;
    }

    public T readLine(Row row ,String[] columnNames) throws ReadExcelException {
        T t = null;
        boolean error = false;
        try {
                t =   targetClass.newInstance();

                for(int i = 0 ;i<columnNames.length;i++){
                    if(columnNames[i] == null || columnNames[i].equals("")){
                        continue;
                    }
                    Cell cell = row.getCell(i);
                    if(isNull(cell)){
                        continue;
                    }
                    Field field = targetClass.getDeclaredField(columnNames[i]);
                    NotNull notNull  = field.getAnnotation(NotNull.class);
                    try {
                        String value = getCellValue(cell,notNull == null?true:false);

                        NumberType numberType = field.getAnnotation(NumberType.class);
                        DateType dateType = field.getAnnotation(DateType.class);
                        Loop loop  = field.getAnnotation(Loop.class);
                        FetchValueByMethodOfClass fetchValue = field.getAnnotation(FetchValueByMethodOfClass.class);
                        Method method = targetClass.getMethod(getSetMethod(columnNames[i]),field.getType());
                        if(numberType != null && value != null){
                            analyseNumberType(numberType, t, value, field, method);
                        }
                        if(dateType != null){
                            analyseDateType(dateType,t,value,field,method);
                        }
                        if(fetchValue != null){
                            analyseFetchValue(fetchValue,t,value);
                        }
                        if(loop != null){
                              Method getMethod = targetClass.getMethod(getGetMethod(columnNames[i]));
                              Object getValue = getMethod.invoke(t);
                              if(getValue != null){
                                  value  = getValue+","+value;
                              }
                             method.invoke(t,value);
                        }
                        if(numberType == null && dateType == null && loop==null){
                            method.invoke(t,value);
                        }

                    } catch (Exception e) {
                        LogUtil.error(this.getClass(), e.getMessage(), e);
                        cell.setCellStyle(errorStyle);
                        error  = true;
                        continue;
                    }
                }

        } catch (InstantiationException e) {
            LogUtil.error(this.getClass(), e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LogUtil.error(this.getClass(), e.getMessage(), e);
        } catch (NoSuchFieldException e) {
            LogUtil.error(this.getClass(), e.getMessage(), e);
        }
        if(error){
            throw new ReadExcelException("error");
        }
        return t;
    }

    private void analyseFetchValue(FetchValueByMethodOfClass fetchValue, T t, String value) throws Exception {
        String className = fetchValue.className();
        String methodName = fetchValue.methodName();
        String javaType = fetchValue.javaType();
        String fieldName = fetchValue.targetField();
        boolean isNotNull  = fetchValue.isNotNull();
        String memo = fetchValue.memo();

        Object obj = context.getBean(Class.forName(className));
        Class class1 = obj.getClass();
        try {
            Method method1 = class1.getMethod(methodName,Class.forName(javaType));
            Object returnValue =  method1.invoke(obj,value);
            if(!returnValue.getClass().getName().equals(javaType)){
                throw new Exception("javaType类型错误");
            }
            if(returnValue == null && isNotNull && (!javaType.equals("java.lang.String") || (javaType.equals("java.lang.String") && !((String)returnValue).equals("") ) )){
                throw new Exception(memo+"不存在");
            }

            Method methodField = t.getClass().getMethod(getSetMethod(fieldName),Class.forName(javaType));
            methodField.invoke(t,returnValue);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//
    }

    private void analyseNumberType(NumberType numberType, T t,  String value,Field field,Method method) {
        if(numberType.value() == NumberTypeEnum.MONEY){
            value = value.replace(",","");
            long money = new BigDecimal(value).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN).longValue();
            LogUtil.debug(this.getClass(),money);
            try {
                method.invoke(t,money);
            } catch (IllegalAccessException e) {
                LogUtil.error(this.getClass(), e.getMessage(), e);
            } catch (InvocationTargetException e) {
                LogUtil.error(this.getClass(), e.getMessage(), e);
            }
        }
        if(numberType.value() == NumberTypeEnum.LONG){
            value = value.replace(",","");
            long money = new BigDecimal(value).longValue();
            try {
                method.invoke(t,money);
            } catch (IllegalAccessException e) {
                LogUtil.error(this.getClass(),e.getMessage(),e);
            } catch (InvocationTargetException e) {
                LogUtil.error(this.getClass(),e.getMessage(),e);
            }
        }

        if(numberType.value() == NumberTypeEnum.DECIMAL){
            value = value.replace(",","");
            BigDecimal money = new BigDecimal(value);
            LogUtil.debug(this.getClass(),money);
            try {
                method.invoke(t,money);
            } catch (IllegalAccessException e) {
                LogUtil.error(this.getClass(), e.getMessage(), e);
            } catch (InvocationTargetException e) {
                LogUtil.error(this.getClass(), e.getMessage(), e);
            }
        }


    }

    private void analyseDateType(DateType dateType,T t,String value,Field field,Method method) throws ParseException {

        if(dateType.value() == DateTypeEnum.DATE_STRING_EIGHT){
            value = value.replace("-","").replace("/","");
        }
        if(dateType.value() == DateTypeEnum.TIME_STRING_SIX){
            value = value.replace(":","");
        }
        try {

            if(field.getType().getName().equals("java.lang.Long") || field.getType().getName().equals("long")){
                method.invoke(t,Long.parseLong(value));
            }else if(field.getType().getName().equals("java.lang.Integer") || field.getType().getName().equals("int")){
                method.invoke(t,Integer.parseInt(value));
            }else if(field.getType().getName().equals("java.lang.String") ){
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                Date str =df.parse(value);
                method.invoke(t,df.format(str));
            }else if(field.getType().getName().equals("java.util.Date") ){
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                method.invoke(t,df.parse(value));
            }
        } catch (IllegalAccessException e) {
           LogUtil.error(this.getClass(),e.getMessage(),e);
        } catch (InvocationTargetException e) {
            LogUtil.error(this.getClass(), e.getMessage(), e);
        } catch (ParseException e) {
        	throw e;
        }


    }



    /**
     * 判断行是否为空
     * @param row
     * @return
     */
    private boolean isNull(Row row){
        if (row == null) {
            return true;
        }
        int number = row.getLastCellNum();
        boolean isAllNull= true;
        for (int i = 0; i < number; i++) {
            Cell cell = row.getCell(i);
            if (!isNull(cell)) {
                isAllNull = false;
            }
        }
        return isAllNull;
    }

    /**
     * 判断单元格为空
     * @param cell
     * @return
     */
    public boolean isNull(Cell cell){
        if(cell == null){
            return true;
        }
        if(cell.getCellType() == Cell.CELL_TYPE_BLANK){
            return true;
        }
        if(cell.getCellType() == Cell.CELL_TYPE_STRING && (cell.getStringCellValue() == null || "".equals(cell.getStringCellValue().trim())) ){
            return true;
        }

        return false;
    }

    private String getCellValue(Cell cell,boolean isNull) throws Exception{
        if (cell == null && isNull){
            return "";
        }else if (cell == null){
            throw new Exception("is not allow null");
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return new BigDecimal(cell.getNumericCellValue()).toPlainString();
            case Cell.CELL_TYPE_STRING:
                String value  = cell.getStringCellValue();
                return value != null ?value.trim():"";
        }
        if( isNull){
            return "";
        }else{
            throw new Exception("is not allow null");
        }
    }

    private Double getDoubleCellValue(Cell cell){
        if (cell == null)
            return 0.0;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_STRING:
                String tmp = cell.getStringCellValue().trim();
                Double value = 0.0;
                try {
                    value = Double.valueOf(tmp);
                    return value;
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    throw e;
                }
        }
        return 0.0;
    }

    private Long getLongCellValue(Cell cell){
        if (cell == null)
            return 0L;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return Double.valueOf(cell.getNumericCellValue()).longValue();
            case Cell.CELL_TYPE_STRING:
                String tmp = cell.getStringCellValue();
                Double value = 0.0;
                try {
                    value = Double.valueOf(tmp);
                    return value.longValue();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    throw e;
                }


        }
        return 0L;
    }




    /**
     * 获取错误格式样式
     * @param wb
     * @return
     */
    private CellStyle getErrrorStyle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.YELLOW.index);
        return style;
    }

    private String getSetMethod(String string) {
        String tmp = "set";
        tmp = tmp + string.substring(0, 1).toUpperCase() + string.substring(1);
        return tmp;
    }
    private String getGetMethod(String string) {
        String tmp = "get";
        tmp = tmp + string.substring(0, 1).toUpperCase() + string.substring(1);
        return tmp;
    }
}
