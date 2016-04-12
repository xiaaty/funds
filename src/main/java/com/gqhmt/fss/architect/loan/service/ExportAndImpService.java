package com.gqhmt.fss.architect.loan.service;

import com.gqhmt.fss.architect.loan.bean.FssLoanBean;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.mapper.read.FssLoanReadMapper;
import com.gqhmt.fss.architect.loan.mapper.write.FssLoanWriteMapper;

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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

/**
 * Filename:    com.gq.p2p.customer.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 16:36
 * Description:
 * <p/>导入导出接口
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16 柯禹来      1.0     1.0 Version
 */
@Service
public class ExportAndImpService {

    @Resource
    private FssLoanReadMapper fssLoanReadMapper;
    @Resource
    private FssLoanWriteMapper fssLoanWriteMapper;
    
    String[] excelHeader = { "客户姓名","账户号","身份证号", "合同号","金额","商户号"};    
    public HSSFWorkbook exportLoan(List<FssLoanBean> list) {  
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("还款代扣（纯线下）数据列表");  
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);    
           cell.setCellValue(excelHeader[i]);    
           cell.setCellStyle(style);
        }    
        for (int i = 0; i < list.size(); i++) {
        	sheet.autoSizeColumn(i);
            row = sheet.createRow(i + 1);    
            FssLoanBean fssLoanBean = list.get(i);    
            row.createCell(0).setCellValue(fssLoanBean.getCustName());    
            row.createCell(1).setCellValue(fssLoanBean.getAccNo());    
            row.createCell(2).setCellValue(fssLoanBean.getCertNo());    
            row.createCell(3).setCellValue(fssLoanBean.getContractNo());    
            row.createCell(4).setCellValue(fssLoanBean.getContractAmt());    
            row.createCell(5).setCellValue(fssLoanBean.getMchn());    
        }    
        return wb;    
    }    
}	
