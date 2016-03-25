package com.gqhmt.controller.api.p2p;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.p2p.BidApplyDto;
import com.gqhmt.extServInter.dto.p2p.RePaymentDto;
import com.gqhmt.extServInter.service.p2p.IBidRepayApply;
import com.gqhmt.extServInter.service.p2p.IFullBidApply;
import com.gqhmt.extServInter.service.p2p.IRePayments;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月23日
 * Description:冠e通后台
 * <p>满标
 * <p>回款
 * <p>流标
 * <p>冠e通后台
 * <p>冠e通后台
 * 
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月23日  jhz      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssBankEndApi {

    
    @Resource
    private IFullBidApply fullBidApplyImpl;
    
    @Resource
    private IBidRepayApply bidRepayApplyImpl;
    
    @Resource
    private IRePayments rePaymentsImpl;
    
    /**
     * 
     * author:jhz
     * time:2016年3月23日
     * function：满标
     */
    @RequestMapping(value = "/p2p/fullBidApply",method = RequestMethod.POST)
    @ResponseBody
    public Object fullBidApply(BidApplyDto fullBidApplyDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = fullBidApplyImpl.excute(fullBidApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年3月23日
     * function：流标
     */
    @RequestMapping(value = "/p2p/bidRepayApply",method = RequestMethod.POST)
    @ResponseBody
    public Object bidRepayApply(BidApplyDto bidApplyDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = bidRepayApplyImpl.excute(bidApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年3月23日
     * function：满标
     */
    @RequestMapping(value = "/p2p/rePayments",method = RequestMethod.POST)
    @ResponseBody
    public Object rePayments(RePaymentDto rePaymentDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = rePaymentsImpl.excute(rePaymentDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    

//    public Object



	@SuppressWarnings("unused")
	private Response excute(Exception e){
		LogUtil.error(this.getClass(), e);
		Response response = new Response();
		response.setResp_code(e.getMessage());
		return response;
	}
}
