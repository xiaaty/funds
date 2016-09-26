package com.gqhmt.controller.api.trade;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.bonus.BonusDto;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.extServInter.dto.trade.*;
import com.gqhmt.extServInter.service.bonus.IBonus;
import com.gqhmt.extServInter.service.cost.ICharges;
import com.gqhmt.extServInter.service.trade.*;
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
 * Create at:   2016年3月25日
 * Description:与冠E通对接---后台接口
 * <p>
 * 	   <ol>
 *         <li>开户接口</li>
 *         <li>代扣申请</li>
 *         <li>代付申请</li>
 *         <li>冻结接口</li>
 *         <li>解冻接口</li>
 *         <li>转账接口</li>
 *         <li>费用收取接口</li>
 *     </ol>
 *  </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月25日  柯禹来      1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssGetTradeApi {

	@Resource
	private IWithHoldApply withHoldApplyImpl;
	@Resource
	private IGetWithDrawApply getWithDrawApplyImpl;
	
	@Resource
	private ICharges chargesImpl;
	@Resource
	private ICompensation compensationImpl;
	@Resource
	private IBonus bonusImpl;
	/*
	 * 冠E通后台--代扣申请接口
	 */
	@RequestMapping(value = "/careateWithholdApply",method = {RequestMethod.POST,RequestMethod.GET})
	public Object careateWithholdApply(@RequestBody GETWithholdAndDrawDto dto){
		Response response=new Response();
		try {
			response = withHoldApplyImpl.execute(dto);
		} catch (Exception e) {
			response = this.execute(e);
		}
		return response;
	}
	
	/*
	 * 冠E通后台--代付申请接口
	 */
	@RequestMapping(value = "/careateWithDrawApply",method = {RequestMethod.POST,RequestMethod.GET})
	public Object careateWithDrawApply(@RequestBody GETWithholdAndDrawDto dto){
		Response response=new Response();
		try {
			response = getWithDrawApplyImpl.execute(dto);
		} catch (Exception e) {
			response = this.execute(e);
		}
		return response;
	}
	
	/**
	 * 费用收取接口
	 * @param dto
	 * @return
     */
	@RequestMapping(value = "/chargeAmount",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getCost(@RequestBody CostDto dto){
		Response response=new Response();
		try {
			response = chargesImpl.execute(dto);
		} catch (Exception e) {
			response = this.execute(e);
		}
		return response;
	}


	/**
	 * 资金代偿、费用收取、 公共接口
	 * @param dto
	 * @return
     */
	@RequestMapping(value = "/compensation",method = {RequestMethod.POST,RequestMethod.GET})
	public Object compensation(@RequestBody CompensationDto dto){
		Response response=new Response();
		try {
			response = compensationImpl.execute(dto);
		} catch (Exception e) {
			response = this.execute(e);
		}
		return response;
	}

	/**
	 * 红包提现
	 * @param dto
	 * @return
     */
	@RequestMapping(value = "/bonusWithDraw",method = {RequestMethod.POST,RequestMethod.GET})
	public Object compensation(@RequestBody BonusDto dto){
		Response response=new Response();
		try {
			response = bonusImpl.execute(dto);
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
