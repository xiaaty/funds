package com.gqhmt.fss.controller.api.trade;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gqhmt.annotations.API;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.tender.BidDto;
import com.gqhmt.extServInter.dto.trade.OrderWithdrawApplyDto;
import com.gqhmt.extServInter.dto.trade.OrderWithholdApplyDto;
import com.gqhmt.extServInter.dto.trade.WithdrawDto;
import com.gqhmt.extServInter.dto.trade.WithholdDto;
import com.gqhmt.extServInter.service.tender.IBid;
import com.gqhmt.extServInter.service.trade.IOrderWithdrawApply;
import com.gqhmt.extServInter.service.trade.IOrderWithholdApply;
import com.gqhmt.extServInter.service.trade.IWithdraw;
import com.gqhmt.extServInter.service.trade.IWithhold;

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
 * <p>
 * 	   <ol>
 *         <li>web提现订单生成</li>
 *         <li>web充值订单生成</li>
 *         <li>代扣充值--线上客户</li>
 *         <li>代扣提现--线上客户</li>
 *     </ol>
 *  </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月22日  jhz      1.0     1.0 Version
 */
@RequestMapping(value = "/api")
public class FssTradeApi {

    @Resource
    private ApplicationContext applicationContext;
    
    @Resource
    private IWithhold iWithhold;
    
    @Resource
    private IWithdraw iWithdraw;
    
    @Resource
    private IOrderWithdrawApply iOrderWithdrawApply;
    
    @Resource
    private IOrderWithholdApply iOrderWithholdApply;
    
    
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：web提现订单生成
     */
    @API
    @RequestMapping(value = "/orderWithdrawApply",method = RequestMethod.POST)
    public Object orderWithdrawApply(OrderWithdrawApplyDto orderWithdrawApplyDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = iOrderWithdrawApply.excute(orderWithdrawApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：web代扣订单生成
     */
    @API
    @RequestMapping(value = "/orderWithholdApply",method = RequestMethod.POST)
    public Object orderWithholdApply(OrderWithholdApplyDto orderWithholdApplyDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = iOrderWithholdApply.excute(orderWithholdApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：提现
     */
    @API
    @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
    public Object withdraw(WithdrawDto withdrawDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = iWithdraw.excute(withdrawDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：代扣
     */
    @API
    @RequestMapping(value = "/withhold",method = RequestMethod.POST)
    public Object withhold(WithholdDto withholdDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = iWithhold.excute(withholdDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    

}
