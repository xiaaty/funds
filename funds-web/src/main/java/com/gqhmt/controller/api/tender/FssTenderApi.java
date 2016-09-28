package com.gqhmt.controller.api.tender;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.service.tender.IBidTender;
import com.gqhmt.extServInter.service.tender.INewBidTender;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月11日
 * Description:
 * <p>投标</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月11日  柯禹来      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssTenderApi {

/*    @Resource
    private ApplicationContext applicationContext;*/
    
    @Resource
    private IBidTender bidImpl;
    @Resource
    private INewBidTender newBidImpl;

    /**
     * 
     * author:柯禹来
     * time:2016年2月22日
     * function：投标
     */
    @RequestMapping(value = "/bid",method = RequestMethod.POST)
    public Object bid(@RequestBody BidDto bidDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = bidImpl.execute(bidDto);
    	} catch (Exception e) {
			response = this.execute(e);
    	}
    	return response;
    }

	/**
	 * jhz
	 * 新手标投标
	 * @param bidDto
	 * @return
     */
    @RequestMapping(value = "/newHandBid",method = RequestMethod.POST)
    public Object newHandBid(@RequestBody BidDto bidDto){
    	Response response=new Response();
    	try {
    		response = newBidImpl.execute(bidDto);
    	} catch (Exception e) {
			response = this.execute(e);
    	}
    	return response;
    }

	private Response execute(Exception e){
		LogUtil.error(this.getClass(), e);
		Response response = new Response();
		response.setResp_code(e.getMessage());
		return response;
	}
}
