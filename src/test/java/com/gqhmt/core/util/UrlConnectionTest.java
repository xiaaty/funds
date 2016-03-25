package com.gqhmt.core.util;

import com.gqhmt.TestService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.connection.UrlConnectUtil;
import com.gqhmt.extServInter.dto.Response;
import org.junit.Test;

/**
 * Filename:    com.gqhmt.core.util.UrlConnectionTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/22 09:53
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/22  于泳      1.0     1.0 Version
 */
public class UrlConnectionTest extends TestService  {

    @Test
    public void test() throws FssException {
        String  json ="{\n" +
                "    \"mchn\":\"63488471YHBC\",\n" +
                "    \"seq_no\":\"123111122322wwwwww\",\n" +
                "    \"trade_type\":\"11093001\",\n" +
                "    \"signature\":\"22414214\",\n" +
                "    \"repay_list\":[\n" +
                "    {\"serial_number\":\"0001\",\"contract_id\":\"1000001\",\"accounting_no\":\"123456\",\"acc_no\":\"1102688362697310\",\"settle_list\":[{\"settle_amt\":\"\",\"account_type\":\"21011005\"}]},\n" +
                "    {\"serial_number\":\"0002\",\"contract_id\":\"1000001\",\"accounting_no\":\"123456\",\"acc_no\":\"1102688362697310\",\"settle_list\":[{\"settle_amt\":\"\",\"account_type\":\"21011005\"}]},\n" +
                "    {\"serial_number\":\"0003\",\"contract_id\":\"1000001\",\"accounting_no\":\"123456\",\"acc_no\":\"1102688362697310\",\"settle_list\":[{\"settle_amt\":\"\",\"account_type\":\"21011005\"}]}\n" +
                "    ]\n" +
                "    \n" +
                "}";
        Response response = UrlConnectUtil.sendJsonDataReturnObject(Response.class,"getLendWithDraw",json);

        assert response.getResp_code().equals("0000");

    }
}
