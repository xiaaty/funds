package com.gqhmt.api;

import com.gqhmt.core.FssException;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.util.JsonUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.LendingDto;
import com.gqhmt.extServInter.dto.loan.LendingFeeListDto;
import com.gqhmt.extServInter.dto.loan.LoanAccountResponse;
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
            dto.setCert_no("220881198005150333");
            dto.setCity_id("1000");
            dto.setContract_id("JK2016032600031");
            dto.setMobile("13581790473");
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

    @Test
    public  void payment(){
        try {
            LendingDto dto = super.getSuperDto(LendingDto.class,"11090001","63488471YHBC");
            dto.setContract_id("100001");
            dto.setContract_no("JK2016032600031");
            dto.setMortgagee_acc_no("6635634325057878");
            dto.setAcc_no("1302692985004666");
            dto.setContract_amt(new BigDecimal("10000000.00"));
            dto.setPay_amt(new BigDecimal("1000000.00"));
            dto.setLoan_platform("10040001");

            List<LendingFeeListDto> feeList  = new ArrayList<>();
            LendingFeeListDto dto1 = new LendingFeeListDto();
            dto1.setFee_amt(new BigDecimal("30000"));
            dto1.setFee_type("10990003");
            feeList.add(dto1);

            LendingFeeListDto dto2 = new LendingFeeListDto();
            dto2.setFee_amt(new BigDecimal("30000"));
            dto2.setFee_type("10990006");
            feeList.add(dto2);

            dto.setFee_list(feeList);

            LoanAccountResponse response = UrlConnectUtil.sendJsonDataReturnObjectUrl(LoanAccountResponse.class,"http://localhost:8080/api/loan/lending", JsonUtil.getInstance().getJson(dto))  ;
            System.out.println(response.getResp_code()+":"+response.getResp_msg());

            dto.setFee_list(feeList);


        } catch (IllegalAccessException e) {
            assert false;
        } catch (InstantiationException e) {
            assert false;
        } catch (FssException e) {
            e.printStackTrace();
        }
    }
}
