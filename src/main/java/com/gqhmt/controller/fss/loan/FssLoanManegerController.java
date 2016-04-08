package com.gqhmt.controller.fss.loan;

import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.pay.service.cost.ICost;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.util.CommonUtil;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年4月8日
 * Description:
 * <p>纯线下还款划扣导入、导出
 * <p>纯线下放款导入、导出
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年4月8日  jhz      1.0     1.0 Version
 */
@Controller
public class FssLoanManegerController {
	@Resource
	private FssTradeApplyService fssTradeApplyService;
	@Resource
	private FssLoanService fssLoanService;
	@Resource
	private IFundsTrade fundsTradeImpl;
	@Resource
	private FssTradeRecordService fssTradeRecordService;
	@Resource
	private ICost cost;
	@Resource
    private FssCustomerService fssCustomerService;
	@Resource
	private FssBackplateService fssBackplateService;
	@Resource
	private FssAccountService fssAccountService;

	@RequestMapping(value = "/loan/loanListExport/{type}")
	@ResponseBody
	public void  loanExcel(HttpServletRequest request, ModelMap model, @PathVariable String type,
			HttpServletResponse response) throws ParseException{
		
		
		 List<FssLoanEntity> findByType = fssLoanService.findByType(type);
	
		
		String str = request.getSession().getServletContext().getRealPath("/")+ "download/"+"放款记录_"+ System.currentTimeMillis() +".xlsx";
		File file = new File(str);
	
	    if (!file.exists()) {
	    	try {
				file.createNewFile();
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
		try {
	//			FileInputStream fi = new FileInputStream(file);
			XSSFWorkbook xw = new XSSFWorkbook();
			XSSFSheet st = xw.createSheet();
			
			//XSSFSheet st = xw.getSheetAt(0);
			Row row1 = st.createRow(0);
			row1.createCell(0).setCellValue("序号");
			row1.createCell(1).setCellValue("抵押权人资金平台账号");
			row1.createCell(2).setCellValue("借款人资金平台账号");
			row1.createCell(3).setCellValue("客户姓名");
			row1.createCell(4).setCellValue("合同编号");
			row1.createCell(5).setCellValue("合同金额");
			row1.createCell(6).setCellValue("合同利息");
			row1.createCell(7).setCellValue("放款金额");
			row1.createCell(8).setCellValue("借款平台");
			row1.createCell(9).setCellValue("商户号");
			row1.createCell(10).setCellValue("交易日期");
			row1.createCell(11).setCellValue("修改日期");
			if(findByType!=null && findByType.size()>0){
					for(FssLoanEntity fssLoanEntity : findByType){
						String custNo = fssLoanEntity.getCustNo();
						int i=0;
						FssCustomerEntity customerNameByCustNo = fssCustomerService.getCustomerNameByCustNo(custNo);
						Row row = st.createRow(i+1);
						
						row.createCell(0).setCellValue(i+1); //序号
						row.createCell(1).setCellValue(fssLoanEntity.getMortgageeAccNo());
						
						row.createCell(2).setCellValue( fssLoanEntity.getAccNo()); 
		
						row.createCell(3).setCellValue(customerNameByCustNo.getName()); 
						row.createCell(4).setCellValue(fssLoanEntity.getContractNo()); 
						row.createCell(5).setCellValue(fssLoanEntity.getContractAmt().toString()); 
						
						row.createCell(6).setCellValue(fssLoanEntity.getContractInterest().toString()); 

						row.createCell(7).setCellValue(fssLoanEntity.getPayAmt().toString()); 
						
						if("10040001".equals(fssLoanEntity.getLoanPlatform())){
							row.createCell(8).setCellValue("北京");
						}else if("10040002".equals(fssLoanEntity.getLoanPlatform())){
							row.createCell(8).setCellValue("天津");
						}else if("10040003".equals(fssLoanEntity.getLoanPlatform())){
							row.createCell(8).setCellValue("上海");
						}
							
						row.createCell(9).setCellValue(fssLoanEntity.getMchnChild());
						
						row.createCell(10).setCellValue(CommonUtil.dateTostring(fssLoanEntity.getCreateTime())); 
						
						
						row.createCell(12).setCellValue(CommonUtil.dateTostring(fssLoanEntity.getModifyTime())); 

					} 
			}
			FileOutputStream fs = new FileOutputStream(file);
			xw.write(fs);
			fs.close();
			CommonUtil.downFile(response, file);
		} catch (Exception e) {
			System.out.print("系统异常 ：" + e.getMessage());
		}
		
	}

	
	

}