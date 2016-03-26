package com.gqhmt.api;

import com.gqhmt.core.FssException;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.core.util.JsonUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.extServInter.dto.loan.LoanAccountResponse;
import org.junit.Test;

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
            CreateLoanAccountDto dto = super.getSuperDto(CreateLoanAccountDto.class,"11020010","63488471YHBC");
            dto.setBank_card("2201211");
            dto.setBank_id("0105");
            dto.setCert_no("220881198005150331");
            dto.setCity_id("1000");
            dto.setContract_id("JK2016032600031");
            dto.setMobile("13581790472");
            dto.setName("于泳");
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
}
