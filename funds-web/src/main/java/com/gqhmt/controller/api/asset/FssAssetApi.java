package com.gqhmt.controller.api.asset;

import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.account.BankCardDto;
import com.gqhmt.extServInter.dto.asset.*;
import com.gqhmt.extServInter.dto.fund.BankDto;
import com.gqhmt.extServInter.service.account.IFindBankCardList;
import com.gqhmt.extServInter.service.asset.*;
import com.gqhmt.fss.architect.asset.entity.FssStatisticsEntity;
import com.gqhmt.fss.architect.customer.bean.ChangeCardBean;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.controller.api.asset.FssAssetApi
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/2/28 22:09
 * Description:与查询相关的API
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
    private IAccountBanlance accountBanlanceImpl;
    @Resource
    private IBankList bankListImpl;
    @Resource
    private IBankCardList bankCardListImpl;
    @Resource
    private IFundSeqence fundSeqenceImpl;
	@Resource
	private IFundTrade fundTradeImpl;
	@Resource
	private IFindBankCardList findBankCardListImpl;
    @Resource
    private IRechargeAndWithdrawOrder rechargeAndWithdrawOrder;
    @Resource
    private  BankCardInfoService bankCardInfoService;
    @Resource
    private FundSequenceService fundSequenceService;
    @Resource
    private IOfflineRechargeOrder offlineRechargeOrder;

    @Resource
    private IOfflineRechargeBenaOrder offlineRechargeBenaOrder;
    /**
     * 账户余额查询
     * @param dto
     * @return
     */
    @RequestMapping(value = "/getAccountBanlance")
    public Object getAccountBanlance(AssetDto dto){
        Response response = new Response();
        try {
           response =  accountBanlanceImpl.execute(dto);
        } catch (APIExcuteErrorException e) {
            execute(e);
        }
        return response;
    }
    /**
     * author:柯禹来
     * time:2016年3月1日
     * function：银行列表查询
     */
    @RequestMapping(value = "/getBankInfo",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getBankInfo() throws APIExcuteErrorException{
    	BankDto dto=new BankDto();
    	Response response= new Response();
    	try {
    		response= bankListImpl.execute(dto);
    	} catch (FssException e) {
            execute(e);
    	}
    	return response;
    }
    
    /**
     * author:柯禹来
     * time:2016年3月1日
     * function：银行卡信息查询(查询已经绑定的银行卡)
     */
    @RequestMapping(value = "/getBankCardInfo/{custNo}",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getBankCardInfo(@PathVariable String custNo) throws FssException{
    	List<BankCardInfoEntity> list = bankCardInfoService.findBankCardByCustNo(custNo);
    	if(list==null) throw new FssException("90002036");
    	return list;
    }
    /**
     * author:jhz
     * time:2016年7月20日
     * function：银行卡信息查询（查询用户名下所有银行卡信息）
     */
    @RequestMapping(value = "/bankCardInfoList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object bankCardInfoList( BankCardDto bankCardDto) throws FssException{
        Response response=findBankCardListImpl.execute(bankCardDto);
        return response;
//    	return JSONObject.fromObject(list).toString();
    }
    /**
     * author:jhz
     * time:2016年6月1日
     * function：变更银行卡信息查询
     */
    @RequestMapping(value = "/getChangeCardInfo/{custNo}",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ChangeCardBean getChangeCardInfo(@PathVariable String custNo) throws FssException{
        ChangeCardBean cardBean = bankCardInfoService.findChangeCardInfo(custNo);
        if(cardBean==null) throw new FssException("90004027");
    	return cardBean;
    }

    /**
     * author:柯禹来
     * time:2016年3月1日
     * function：账户资金流水查询
     */
    @RequestMapping(value = "/queryFundSequence",method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryFundSequence(FundSequenceDto tradflowDto){
    	Response response= new Response();
    	try {
    		response =fundSeqenceImpl.execute(tradflowDto);
    	} catch (Exception e) {
            execute(e);
    	}
    	return response;
    }
    /**
     * author:柯禹来
     * time:2016年3月1日
     * function：交易记录查询
     */
    @RequestMapping(value = "/queryFundTrade",method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryFundTrade(FundTradeDto fundTradeDto){
    	Response response=null;
    	try {
    		response = fundTradeImpl.execute(fundTradeDto);
    	} catch (Exception e) {
            execute(e);
    	}
    	return response;
    }

    private Response execute(Exception e){
        LogUtil.error(this.getClass(), e);
        Response response = new Response();
        response.setResp_code(e.getMessage());
        return response;
    }

    /**
     * 统计客户当月充值、提现总金额
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/statistics",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String, BigDecimal> getStatisticsByType(String custId) throws FssException{
    	FssStatisticsEntity  fssStatisticsEntity= fundSequenceService.getStatisticsByType(custId);
    	Map<String, BigDecimal> map=new HashMap<String, BigDecimal>();
    	map.put("sumRecharge", fssStatisticsEntity.getRechargeTotal().setScale(2, BigDecimal.ROUND_HALF_UP));//充值总金额
    	map.put("sumWithdraw", fssStatisticsEntity.getWithdrawTotal().setScale(2, BigDecimal.ROUND_HALF_UP));//提现总金额
    	return map;
    }
    
    /**
     *
     * @param dto
     * @return
     */

    @RequestMapping(value = "/queryOrders",method = RequestMethod.POST)
    public Object queryFundOrder(RechargeAndWithdrawListDto dto){
        Response response=null;

        try {
            response = rechargeAndWithdrawOrder.execute(dto);
        } catch (APIExcuteErrorException e) {
            execute(e);
        }

        return response;
    }

    /**
     *客户线下充值列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/offlineRecharge/query",method = RequestMethod.POST)
    public Object queryRechargeList(OfflineRechargeListDto dto){
        Response response=null;
        try {
            response = offlineRechargeOrder.execute(dto);
        } catch (APIExcuteErrorException e) {
            execute(e);
        }
        return response;
    }

    /**
     *客户线下充值列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/offlineRecharge/detail",method = RequestMethod.POST)
    public Object queryRechargeList(OfflineRechargeBeanDto dto){
        Response response=null;
        try {
            response = offlineRechargeBenaOrder.execute(dto);
        } catch (APIExcuteErrorException e) {
            execute(e);
        }
        return response;
    }

}
