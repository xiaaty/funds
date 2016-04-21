package com.gqhmt.api;

import com.gqhmt.core.FssException;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.util.JsonUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.FailedBidDto;
import com.gqhmt.extServInter.dto.loan.LendingDto;
import com.gqhmt.extServInter.dto.loan.LendingFeeListDto;
import com.gqhmt.extServInter.dto.loan.LoanAccountResponse;
import com.gqhmt.extServInter.dto.loan.RepaymentChildDto;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Filename:    com.gqhmt.api.LoanApiTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/26 14:41
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/26  于泳      1.0     1.0 Version
 */
public class LoanApiTest extends SupperAPI {

    @Test
    public  void createAccount() {
        try {
            CreateLoanAccountDto dto = super.getSuperDto(CreateLoanAccountDto.class,"11020009","63488471YHBC");
            dto.setBank_card("2201211");
            dto.setBank_id("0105");
            dto.setCert_no("rweqrqwrwer");
            dto.setCity_id("110000");
            dto.setContract_no("JK201603260eee0031");
            dto.setMobile("123232222");
            dto.setName("dy1");
            LoanAccountResponse response = UrlConnectUtil.sendJsonDataReturnObjectUrl(LoanAccountResponse.class,"http://localhost:8080/api/createLoanAccount", JsonUtil.getInstance().getJson(dto))  ;
            System.out.println(response.getResp_code()+":"+response.getResp_msg());

            assert "00000000".equals(response.getResp_code());
        } catch (FssException e) {
           assert false;
        } catch (InstantiationException e) {
            assert false;
        } catch (IllegalAccessException e) {
            assert false;
        }

    }
    /**
     * 
     * author:jhz
     * time:2016年3月29日
     * function：抵押权人放款
     */
    @Test
    public  void payment(){
        try {
            LendingDto dto = super.getSuperDto(LendingDto.class,"11090001","63488471YHBC");
            dto.setContract_id("100001");
            dto.setContract_no("JK2016032600031");
            dto.setMortgagee_acc_no("6635634325057878");
            dto.setAcc_no("1302692985004666");
            dto.setContract_interest(new BigDecimal("100000.00"));
            dto.setContract_amt(new BigDecimal("1000000.00"));
            dto.setPay_amt(new BigDecimal("20000.00"));
            dto.setLoan_platform("10040001");

            List<LendingFeeListDto> feeList  = new ArrayList<>();
            LendingFeeListDto dto1 = new LendingFeeListDto();
            dto1.setFee_amt(new BigDecimal("30000"));
            dto1.setFee_type("10990003");
            feeList.add(dto1);

            LendingFeeListDto dto2 = new LendingFeeListDto();
            dto2.setFee_amt(new BigDecimal("30000.00"));
            dto2.setFee_type("10990006");
            feeList.add(dto2);

            dto.setFee_list(feeList);
            System.out.println(JsonUtil.getInstance().getJson(dto)+"*********");
            Response response = UrlConnectUtil.sendJsonDataReturnObjectUrl(Response.class,"http://localhost:8080/funds/api/loan/lending", JsonUtil.getInstance().getJson(dto))  ;
            System.out.println(response.getResp_code()+":"+response.getResp_msg());

//            dto.setFee_list(feeList);


        } catch (IllegalAccessException e) {
            assert false;
        } catch (InstantiationException e) {
            assert false;
        } catch (FssException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * author:jhz
     * time:2016年4月6日
     * function：流标
     */
    @Test
    public  void failedBid(){
    	try {
    		FailedBidDto dto = super.getSuperDto(FailedBidDto.class,"11090010","63488471YHBC");
    		dto.setContract_id("1000010002");
    		dto.setContract_no("JK2016032600031");
    		dto.setMortgagee_acc_no("6635634325057878");
    		dto.setAcc_no("1302692985004666");
    		dto.setMortgagee_acc_no("6635634325057878");
    		dto.setContract_amt(new BigDecimal("10000.00"));
    		dto.setPay_amt(new BigDecimal("2000.00"));
    		dto.setLoan_platform("10040002");
    		
    		List<LendingFeeListDto> feeList  = new ArrayList<>();
    		LendingFeeListDto dto1 = new LendingFeeListDto();
    		dto1.setFee_amt(new BigDecimal("3000.00"));
    		dto1.setFee_type("10990003");
    		feeList.add(dto1);
    		
    		LendingFeeListDto dto2 = new LendingFeeListDto();
    		dto2.setFee_amt(new BigDecimal("3000.00"));
    		dto2.setFee_type("10990006");
    		feeList.add(dto2);
    		
    		dto.setFee_list(feeList);
    		System.out.println(JsonUtil.getInstance().getJson(dto)+"*********");
    		Response response = UrlConnectUtil.sendJsonDataReturnObjectUrl(Response.class,"http://localhost:8080/funds/api/loan/failedBid", JsonUtil.getInstance().getJson(dto))  ;
    		System.out.println(response.getResp_code()+":"+response.getResp_msg());
    		
//            dto.setFee_list(feeList);
    		
    		
    	} catch (IllegalAccessException e) {
    		assert false;
    	} catch (InstantiationException e) {
    		assert false;
    	} catch (FssException e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 
     * author:jhz
     * time:2016年3月29日
     * function：批量代扣
     */
    @Test
    public  void rePayment(){
    	try {
    		RepaymentDto dto = super.getSuperDto(RepaymentDto.class,"11093001","63488471YHBC");
    		
    		List<RepaymentChildDto> repay_list=new ArrayList<>();
    		
    		RepaymentChildDto repaymentChildDto=new RepaymentChildDto();
    		repaymentChildDto.setContract_id("100001");
    		repaymentChildDto.setContract_no("JK2016032600031");
    		repaymentChildDto.setSerial_number("00000000000");
    		repaymentChildDto.setAcc_no("1302692985004666");
    		repaymentChildDto.setAmt(new BigDecimal("600000.00"));
    		repaymentChildDto.setRemark("jax");
    		repay_list.add(repaymentChildDto);
    		
    		RepaymentChildDto repaymentChildDto1=new RepaymentChildDto();
    		repaymentChildDto1.setContract_id("100001");
    		repaymentChildDto1.setContract_no("JK2016032600031");
    		repaymentChildDto1.setSerial_number("66666666");
    		repaymentChildDto1.setAcc_no("1302692985004666");
    		repaymentChildDto1.setAmt(new BigDecimal("1000000.00"));
    		repaymentChildDto1.setRemark("jax");
    		repay_list.add(repaymentChildDto1);
    		
    		List<LendingFeeListDto> feeList  = new ArrayList<>();
    		LendingFeeListDto dto1 = new LendingFeeListDto();
    		dto1.setFee_amt(new BigDecimal("30000"));
    		dto1.setFee_type("10990003");
    		feeList.add(dto1);
    		
    		
    		dto.setRepay_list(repay_list);
    		System.out.println(JsonUtil.getInstance().getJson(dto)+"*********");
    		Response response = UrlConnectUtil.sendJsonDataReturnObjectUrl(Response.class,"http://localhost:8080/funds/api/createRefundDraw", JsonUtil.getInstance().getJson(dto))  ;
    		System.out.println(response.getResp_code()+":"+response.getResp_msg());
    		
//            dto.setFee_list(feeList);
    		
    		
    	} catch (IllegalAccessException e) {
    		assert false;
    	} catch (InstantiationException e) {
    		assert false;
    	} catch (FssException e) {
    		e.printStackTrace();
    	}
    }

    public void dyWithdraw(){

    }
}
