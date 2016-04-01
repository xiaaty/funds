package com.gqhmt.pay.service.trade.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
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
    private FssTradeRecordService recordService;
    @Resource
    private FssTradeApplyService fssTradeApplyService;

    @Resource
    private IFundsTrade fundsTrade;

    @Override
    public void batchTrade(FssTradeRecordEntity entity){
        FundOrderEntity orderEntity = null;


        try {

            if(entity.getTradeType() != 1103 && entity.getTradeType() != 1104){
                this.recordService.updateTradeRecordExecuteState(entity,2,"90099011");//
                return;
            }

            if(entity.getTradeType() == 1103){
                orderEntity = this.batchWithholding(entity);
            }else if(entity.getTradeType() == 1104){
                orderEntity = this.batchWithdraw(entity);
            }

            entity.setOrderNo(orderEntity.getOrderNo());
            this.recordService.updateTradeRecordExecuteState(entity,1,null);

        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
            this.recordService.updateTradeRecordExecuteState(entity,2,e.getMessage());//todo 增加失败原因ss
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
            int custId = entity.getCustId().intValue();
            businessType= GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(entity.getTradeTypeChild());//获取业务类型
            orderEntity = this.fundsTrade.withholdingApplyNew(custId,businessType.intValue(),entity.getApplyNo(),entity.getAmount(),entity.getId());
        }
        return  orderEntity;
    }
    /**
     * 批量代付
     * @param entity
     * @return
     */
    public FundOrderEntity batchWithdraw(FssTradeRecordEntity entity) throws FssException{
    	String  accNo = entity.getAccNo();//旧版通过账户号获取
    	String custId=null;
    	if(entity.getCustId()!=null){
    		custId = String.valueOf(entity.getCustId());//新版通过custId获取
    	}
    	int	selletType=0;//获取结算类型
    	Integer businessType = GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(entity.getTradeTypeChild());//获取业务类型
        FundOrderEntity orderEntity = null;
        if(entity.getBespokeDate()!=null){
    		selletType=fssTradeApplyService.compare_date(entity.getBespokeDate());//结算类型；0 T+0 ; 1 T+1
    	}
    	if(accNo != null && !"".equals(accNo)){
    		orderEntity =this.fundsTrade.withdrawApplyNew(accNo,null,businessType.intValue(), entity.getApplyNo(), entity.getAmount(), entity.getId(), selletType);
    	}else{
    		orderEntity = this.fundsTrade.withdrawApplyNew(null,custId, businessType.intValue(), entity.getApplyNo(), entity.getAmount(), entity.getId(), selletType);
    	}
        return  orderEntity;
    }
}
