package com.gqhmt.core.util;

import com.gqhmt.extServInter.dto.QueryListResponse;
import com.gqhmt.extServInter.dto.asset.FundTradeDto;
import org.junit.Test;

/**
 * Filename:    com.gqhmt.core.util.GenerateBeanUtilTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/16 15:55
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/16  于泳      1.0     1.0 Version
 */
public class GenerateBeanUtilTest {

    @Test
    public void generateBeanTest() throws Exception {
//        FssSeqOrderEntity fssSeqOrderEntity = null;
//        CreateAccountByFuiou createAccountByFuiou = new CreateAccountByFuiou();
//        createAccountByFuiou.setSeq_no("123456");
//        createAccountByFuiou.setMchn("ERRR");
//        try {
//            fssSeqOrderEntity =   GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//        } catch (Exception e) {
//            e.printStackTrace();
//            assert false;
//        }
//


//        assert fssSeqOrderEntity != null && fssSeqOrderEntity.getModifyTime() != null;

        QueryListResponse queryListResponse = new QueryListResponse();
        queryListResponse.setResp_code("00000");
        FundTradeDto fundTradeDto = new FundTradeDto();

        fundTradeDto.setMchn("213123213");
        fundTradeDto.setSeq_no("2ffafdasf");
        fundTradeDto.setTrade_type("10010001");
        fundTradeDto.setSignature("r23r23");

        queryListResponse = GenerateBeanUtil.GenerateClassInstance(queryListResponse,fundTradeDto);


        assert  fundTradeDto.getMchn().equals(queryListResponse.getMchn());

    }
}
