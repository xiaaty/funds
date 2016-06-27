package com.gqhmt.pay.service.trade.impl;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.trade.IFundsBatchTrade;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.pay.service.trade.impl.FundsBatchTradeImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/28 15:57
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/28  于泳      1.0     1.0 Version
 */
@Service
public class FundsBatchTradeImpl implements IFundsBatchTrade {


    @Resource
    private FssTradeRecordService fssTradeRecordService;
    @Resource
    private FssTradeApplyService fssTradeApplyService;
    @Resource
    private IFundsTrade fundsTrade;
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private TradeRecordService tradeRecordService;
    
    @Override
    public void batchTrade(FssTradeRecordEntity entity) throws FssException {
        FundOrderEntity orderEntity = null;

        try {

            if(entity.getTradeType() != 1103 && entity.getTradeType() != 1104){
                this.fssTradeRecordService.updateTradeRecordExecuteState(entity,2,"90099011");
                return;
            }

            if(entity.getTradeType() == 1103){
                orderEntity = this.batchWithholding(entity);
            }else if(entity.getTradeType() == 1104){
                orderEntity = this.batchWithdraw(entity);
            }
            entity.setOrderNo(orderEntity.getOrderNo());
            this.fssTradeRecordService.updateTradeRecordExecuteState(entity,1,"0000");

        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
            this.fssTradeRecordService.updateTradeRecordExecuteState(entity,2,e.getMessage());//todo 增加失败原因ss
            throw e;
        }
    }

    /**
     * 批量代扣
     * @param entity
     * @return
     * @throws FssException
     */
    public FundOrderEntity batchWithholding(FssTradeRecordEntity entity) throws FssException {
        String  accNo = entity.getAccNo();
        FundOrderEntity orderEntity = null;
        Integer businessType;
        if(accNo != null && !"".equals(accNo)) {
            orderEntity = this.fundsTrade.withholdingApplyNew(accNo, entity.getApplyNo(), entity.getAmount(), entity.getId());
        }else{
        	FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(entity.getCustId(), entity.getCustType());
        	businessType=fundAccountEntity.getBusiType();
            orderEntity = this.fundsTrade.withholdingApplyNew(Integer.valueOf(entity.getCustId().toString()).intValue(),businessType.intValue(),entity.getApplyNo(),entity.getAmount(),entity.getId());
        }
        return  orderEntity;
    }
    /**
     * 批量代付
     * @param entity
     * @return
     */
    public FundOrderEntity batchWithdraw(FssTradeRecordEntity entity) throws FssException{
    	FundOrderEntity orderEntity = null;
    	String  accNo = entity.getAccNo();//旧版通过账户号获取
    	int	selletType=0;//获取结算类型
    	Integer businessType = 0;//获取业务类型
        if(entity.getBespokeDate()!=null){
    		selletType=fssTradeApplyService.compare_date(entity.getBespokeDate());//结算类型；0 T+0 ; 1 T+1
    	}
    	if(accNo != null && !"".equals(accNo)){
    		orderEntity =this.fundsTrade.withdrawApplyNew(accNo,null,businessType, entity.getApplyNo(), entity.getAmount(), entity.getId(), selletType);
    	}else{
    		String custId=null;
        	if(entity.getCustId()!=null && !"".equals(entity.getCustId())){
        		custId = String.valueOf(entity.getCustId());//新版通过custId获取
        		orderEntity = this.fundsTrade.withdrawApplyNew(null,custId, entity.getCustType(), entity.getApplyNo(), entity.getAmount(), entity.getId(), selletType);
        	}else{
        		throw new FssException("90002006");
        	}
    	}
        return  orderEntity;
    }
}
