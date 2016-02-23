package com.gqhmt.fss.controller.api.tender;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gqhmt.annotations.API;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.service.tender.IBid;

import javax.annotation.Resource;

/**
 * stController
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月22日
 * Description:
 * <p>投标</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月22日  jhz      1.0     1.0 Version
 */
@RequestMapping(value = "/api")
public class FssTenderApi {

    @Resource
    private ApplicationContext applicationContext;
    
    @Resource
    private IBid bidImpl;
    
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：投标
     */
    @API
    @RequestMapping(value = "/bid",method = RequestMethod.POST)
    public Object bid(BidDto bidDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = bidImpl.excute(bidDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    

}
