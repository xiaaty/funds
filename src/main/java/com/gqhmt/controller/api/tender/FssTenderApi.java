package com.gqhmt.controller.api.tender;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.service.tender.IBidTender;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping(value = "/api")
public class FssTenderApi {

/*    @Resource
    private ApplicationContext applicationContext;*/
    
    @Resource
    private IBidTender bidImpl;
    
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：投标
     */
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
    

//    public Object



	private Response excute(Exception e){
		LogUtil.error(this.getClass(), e);
		Response response = new Response();
		response.setResp_code(e.getMessage());
		return response;
	}
}
