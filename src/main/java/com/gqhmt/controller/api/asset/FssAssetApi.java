package com.gqhmt.controller.api.asset;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.asset.AssetDto;
import com.gqhmt.extServInter.dto.asset.FundSequenceDto;
import com.gqhmt.extServInter.dto.asset.FundTradeDto;
import com.gqhmt.extServInter.dto.asset.RechargeAndWithdrawListDto;
import com.gqhmt.extServInter.dto.fund.BankDto;
import com.gqhmt.extServInter.service.asset.*;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.annotation.Resource;

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
    private IRechargeAndWithdrawOrder rechargeAndWithdrawOrder;
    @Resource
    private  BankCardInfoService bankCardInfoService;
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
     * function：银行卡信息查询
     */
    @RequestMapping(value = "/getBankCardInfo/{custNo}",method = {RequestMethod.POST,RequestMethod.GET})
    public List<BankCardInfoEntity> getBankCardInfo(@PathVariable String custNo) throws FssException{
    	List<BankCardInfoEntity> list = bankCardInfoService.findBankCardByCustNo(custNo);
    	if(list==null) throw new FssException("90002036");
    	return list;
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
    @RequestMapping(value = "/queryFundTrade",method = RequestMethod.POST)
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
}
