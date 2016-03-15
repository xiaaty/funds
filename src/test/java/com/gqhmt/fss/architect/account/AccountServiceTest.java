package com.gqhmt.fss.architect.account;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Filename:    com.gqhmt.fss.architect.account.AccountServiceTest
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/15 18:17
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/15  于泳      1.0     1.0 Version
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class AccountServiceTest {

//    @Resource
//    private FssAccountService fssAccountService;

    @Test
    public void findAccountByAccNoTest(){
//        FssAccountEntity fssAccountEntity = null;
//        try {
//            fssAccountEntity = fssAccountService.fundAccountBuAccNo("1001966114195701");
//        } catch (FssException e) {
//            e.printStackTrace();
//            assert false;
//        }

        try {
            String t = URLDecoder.decode("UVegZXR6ETZpy3AkeFoAzF9qWiwVoYl%2BFSMjVtCRAr7pbo2PVLa1A5YraeDmEGGaicGduk2HaJ5C0gQsPt%2BoCPXW5zUsNzFW2QA0k2h0nE8Ta75iboSOS6IkkIAj3IzsAbglGKL1DvT4or5hDCYpHESb%2BAZ6LnSDXj62ZWCHM8k%3D","utf-8");

            System.out.println(t);


            assert t.equals("UVegZXR6ETZpy3AkeFoAzF9qWiwVoYl+FSMjVtCRAr7pbo2PVLa1A5YraeDmEGGaicGduk2HaJ5C0gQsPt+oCPXW5zUsNzFW2QA0k2h0nE8Ta75iboSOS6IkkIAj3IzsAbglGKL1DvT4or5hDCYpHESb+AZ6LnSDXj62ZWCHM8k=");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //assert "1001966114195701".equals(fssAccountEntity.getAccNo());
    }
}
