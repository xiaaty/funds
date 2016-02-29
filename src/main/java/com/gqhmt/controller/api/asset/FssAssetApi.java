package com.gqhmt.controller.api.asset;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.asset.AssetDto;
import com.gqhmt.extServInter.service.asset.IAccountBanlance;
import com.gqhmt.extServInter.service.asset.ITransaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.controller.api.asset.FssAssetApi
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/2/28 22:09
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/2/28  于泳      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssAssetApi {

    @Resource
    private IAccountBanlance accountBanlance;

    private ITransaction transaction;

    @RequestMapping(value = "/balance")
    public Object balance(AssetDto dto){
        Response response = null;
        try {
           response =  accountBanlance.excute(dto);
        } catch (APIExcuteErrorException e) {
            excute(e);
        }
        return response;
    }


    @RequestMapping(value = "/queryTradeRecord")
    public Object tradeRecord(AssetDto dto){
        Response response = null;
        try {
            response =  transaction.excute(dto);
        } catch (APIExcuteErrorException e) {
            excute(e);
        }
        return response;
    }

    @RequestMapping(value = "/querySecRecord")
    public Object SecRecord(AssetDto dto){
       /* Response response = null;
        try {
            response =  transaction.excute(dto);
        } catch (APIExcuteErrorException e) {
            excute(e);
        }*/
        return null;
    }

//    public  Object

    private Response excute(Exception e){
        LogUtil.error(this.getClass(), e);
        Response response = new Response();
        response.setResp_code(e.getMessage());
        return response;
    }
}
