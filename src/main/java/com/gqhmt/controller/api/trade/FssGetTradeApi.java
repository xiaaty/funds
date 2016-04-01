package com.gqhmt.controller.api.trade;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.trade.*;
import com.gqhmt.extServInter.service.trade.*;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.pay.service.trade.impl.FundsBatchTradeImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.List;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private ApplicationContext applicationContext;
	@Resource
	private IWithHoldApply withHoldApplyImpl;
	@Resource
	private IPrePaymentApply prePaymentApplyImpl;
	
	@Resource
	private FundsBatchTradeImpl fundsBatchTradeImpl;
	
	/*
	 * 冠E通后台--代扣申请接口
	 */
	@RequestMapping(value = "/careateWithholdApply",method = RequestMethod.POST)
	public Object careateWithholdApply(GET_WithholdDto dto){
		Response response=new Response();
		try {
			response = withHoldApplyImpl.execute(dto);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
		return response;
	}
	
	/*
	 * 冠E通后台--代付申请接口
	 */
	@RequestMapping(value = "/careatePrePaymentApply",method = RequestMethod.POST)
	public Object careateWithholdApply(GET_PrePaymentDto dto){
		Response response=new Response();
		try {
			response = prePaymentApplyImpl.execute(dto);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
		return response;
	}
	
	
	/**
	 * 测试批量代扣
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/batchTrade",method = RequestMethod.POST)
	public Object batchTrade(){
		Response response=new Response();
		try {
			FssTradeRecordEntity entity=new FssTradeRecordEntity();
			entity.setId(new Long(6005));
			entity.setApplyNo("DYKK2016040116673667");
			entity.setTradeType(1103);
			entity.setTradeTypeChild(11090001);
			entity.setAmount(new BigDecimal("6666.00"));
			entity.setCustId(new Long(10));
			entity.setTradeState(98070001);
			entity.setCreateTime(new Date());
			entity.setMchnParent("42592543ZVNC");
			entity.setMchnChild("88721657SUKQ");
			entity.setChannelNo("97010001");
			
			fundsBatchTradeImpl.batchTrade(entity);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
		return response;
	}
	
	/**
	 * 测试批量代付
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/batchWithdraw",method = RequestMethod.POST)
	public Object batchWithdraw(){
		Response response=new Response();
		try {
			FssTradeRecordEntity entity=new FssTradeRecordEntity();
			entity.setId(new Long(6020));
			entity.setBespokeDate(new Date());
			entity.setCustId(new Long(611797));
			entity.setTradeType(1104);
			entity.setTradeTypeChild(11091001);
			entity.setAmount(new BigDecimal("4444.00"));
			entity.setApplyNo("JKTX2016040140206608");
			entity.setTradeState(98070001);
			entity.setChannelNo("97010001");
			
			fundsBatchTradeImpl.batchTrade(entity);
		} catch (Exception e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
		return response;
	}
	
	private Response excute(Exception e){
		LogUtil.error(this.getClass(), e);
		Response response = new Response();
		response.setResp_code(e.getMessage());
		return response;
	}

}
