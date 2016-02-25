package com.gqhmt.controller.api.trade;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.trade.WithdrawOrderDto;
import com.gqhmt.extServInter.dto.trade.RechargeApplyDto;
import com.gqhmt.extServInter.dto.trade.RechargeOrderDto;
import com.gqhmt.extServInter.dto.trade.WithdrawApplyDto;
import com.gqhmt.extServInter.dto.trade.WithdrawDto;
import com.gqhmt.extServInter.dto.trade.WithholdDto;
import com.gqhmt.extServInter.service.trade.IWithdrawOrder;
import com.gqhmt.extServInter.service.trade.IRechargeOrder;
import com.gqhmt.extServInter.service.trade.IWithdraw;
import com.gqhmt.extServInter.service.trade.IWithdrawApply;
import com.gqhmt.extServInter.service.trade.IRecharge;
import com.gqhmt.extServInter.service.trade.IRechargeApply;

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
 * <p>
 * 	   <ol>
 *         <li>web提现订单生成</li>
 *         <li>web充值订单生成</li>
 *         <li>代扣充值--线上客户</li>
 *         <li>代扣提现--线上客户</li>
 *         <li>委托充值交易申请（出借端出借代扣、借款端还款代扣）</li>
 *         <li>委托提现交易申请</li>
 *     </ol>
 *  </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月22日  jhz      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssTradeApi {

    @Resource
    private ApplicationContext applicationContext;
    
    @Resource
    private IRecharge rechargeImpl;
    
    @Resource
    private IWithdraw withdrawImpl;
    
    @Resource
    private IWithdrawOrder withdrawOrderImpl;
    
    
    @Resource
    private IRechargeApply rechargeApplyImpl;
    
    @Resource
    private IWithdrawApply withdrawApplyImpl;
    
    @Resource
    private IRechargeOrder rechargeOrderImpl;
    
    
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：web提现订单生成
     */
    @RequestMapping(value = "/withdrawOrder",method = RequestMethod.POST)
    public Object orderWithdrawApply(WithdrawOrderDto withdrawOrderDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = withdrawOrderImpl.excute(withdrawOrderDto);
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
    @RequestMapping(value = "/rechargeOrder",method = RequestMethod.POST)
    public Object orderWithholdApply(RechargeOrderDto rechargeOrderDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = rechargeOrderImpl.excute(rechargeOrderDto);
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
    @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
    public Object withdraw(WithdrawDto withdrawDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = withdrawImpl.excute(withdrawDto);
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
    @RequestMapping(value = "/recharge",method = RequestMethod.POST)
    public Object withhold(WithholdDto withholdDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = rechargeOrderImpl.excute(withholdDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月24日
     * function：委托充值交易申请（出借端出借代扣、借款端还款代扣）
     */
    @RequestMapping(value = "/rechargeApply",method = RequestMethod.POST)
    public Object rechargeApply(RechargeApplyDto rechargeApplyDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = rechargeApplyImpl.excute(rechargeApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    /**
     * 
     * author:jhz
     * time:2016年2月24日
     * function：委托提现交易申请
     */
    @RequestMapping(value = "/withdrawApply",method = RequestMethod.POST)
    public Object withdrawApply(WithdrawApplyDto withdrawApplyDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = withdrawApplyImpl.excute(withdrawApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    
    
    

}
