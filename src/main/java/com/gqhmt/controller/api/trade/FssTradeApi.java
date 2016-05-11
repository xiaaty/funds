package com.gqhmt.controller.api.trade;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.trade.*;
import com.gqhmt.extServInter.service.trade.*;
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

	@Resource
	private IRechargeCallback rechargeCallback;

	@Resource
	private IWithdrawCallback withdrawCallback;

	@Resource
	private IFreeze freezeImpl;

	@Resource
	private IUnFreeze unFreezeImpl;

	@Resource
	private ITransefer transeferImpl;
    
	@Resource
	private ISstxTrade sstxTradeImpl;
	
	@Resource
	private ISsdkTrade ssdkTradeImpl;
	
    /**
     * 
     * author:jhz
     * time:2016年2月22日
     * function：web提现订单生成
     */
    @RequestMapping(value = "/withdrawOrder",method = {RequestMethod.POST,RequestMethod.GET})
    public Object orderWithdrawApply(WithdrawOrderDto withdrawOrderDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = withdrawOrderImpl.execute(withdrawOrderDto);
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
    @RequestMapping(value = "/rechargeOrder",method = {RequestMethod.POST,RequestMethod.GET})
    public Object orderWithholdApply(RechargeOrderDto rechargeOrderDto){
    	Response response=new Response();
    	try {
//            FssSeqOrderEntity fssSeqOrderEntity = GenerateBeanUtil.GenerateClassInstance(FssSeqOrderEntity.class,createAccountByFuiou);
//            applicationContext.publishEvent(new CreateAccountEvent(fssSeqOrderEntity));
    		response = rechargeOrderImpl.execute(rechargeOrderDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }


	/**
	 *
	 * author:jhz
	 * time:2016年2月27日
	 * function：PC端网银充值成功入账
	 */
	@RequestMapping(value = "/rechargeCallback",method = {RequestMethod.POST,RequestMethod.GET})
	public Object rechargeSuccess(RechargeSuccessDto rechargeSuccessDto){
		Response response=new Response();
		try {
			response = rechargeCallback.execute(rechargeSuccessDto);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
		return response;
	}
	/**
	 *
	 * author:jhz
	 * time:2016年2月27日
	 * function：PC端提现成功入账
	 */
	@RequestMapping(value = "/withdrawCallback",method = {RequestMethod.POST,RequestMethod.GET})
	public Object freeze(WithdrawSuccessDto withdrawSuccessDto){
		Response response=new Response();
		try {
			response = withdrawCallback.execute(withdrawSuccessDto);
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
    @RequestMapping(value = "/withdraw",method = {RequestMethod.POST,RequestMethod.GET})
    public Object withdraw(WithdrawDto withdrawDto){
    	Response response=new Response();
    	try {
    		response = withdrawImpl.execute(withdrawDto);
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
     * function：代扣充值
     */
    @RequestMapping(value = "/recharge",method = {RequestMethod.POST,RequestMethod.GET})
    public Object withhold(WithholdDto withholdDto){
    	Response response=new Response();
    	try {
    		response = rechargeImpl.execute(withholdDto);
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
    		response = rechargeApplyImpl.execute(rechargeApplyDto);
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
    		response = withdrawApplyImpl.execute(withdrawApplyDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }


	/**
	 *
	 * author:jhz
	 * time:2016年2月27日
	 * function：资金冻结
	 */
	@RequestMapping(value = "/freeze",method = RequestMethod.POST)
	public Object freeze(FreezeDto freezeDto){
		Response response=new Response();
		try {
			response = freezeImpl.execute(freezeDto);
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
	 * function：资金解冻
	 */
	@RequestMapping(value = "/unFreeze",method = RequestMethod.POST)
	public Object unFreeze(UnFreezeDto unFreezeDto){
		Response response= null;
		try {
			response = unFreezeImpl.execute(unFreezeDto);
		} catch (Exception e) {
			response = execute(e);
		}
		return response;
	}

	/**
	 * 转账
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/transfer",method = RequestMethod.POST)
	public Object transfer(TransferDto dto){
		Response response= null;
		try {
			response = transeferImpl.execute(dto);
		} catch (Exception e) {
			response = execute(e);
		}
		return response;
	}
	
	/**
     * author:柯禹来
     * time:2016年3月1日
     * function：实时提现
     
    @RequestMapping(value = "/withhold",method = RequestMethod.POST)
    public Object sstxBusiness(SstxDto sstxDto){
    	Response response=new Response();
    	try {
    		response = sstxTradeImpl.excute(sstxDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
	*/
    /**     * author:柯禹来
     * time:2016年3月1日
     * function：实时代扣
     
    @RequestMapping(value = "/agentWithdraw ",method = RequestMethod.POST)
    public Object ssdkBusiness(SsdkDto ssdkDto){
    	Response response=new Response();
    	try {
    		response = ssdkTradeImpl.excute(ssdkDto);
    	} catch (Exception e) {
    		LogUtil.error(this.getClass(), e);
    		response.setResp_code(e.getMessage());
    	}
    	return response;
    }
    */
	private Response execute(Exception e){
		LogUtil.error(this.getClass(), e);
		Response response = new Response();
		response.setResp_code(e.getMessage());
		return response;
	}

}