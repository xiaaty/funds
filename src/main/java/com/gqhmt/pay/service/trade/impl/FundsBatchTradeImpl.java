package com.gqhmt.pay.service.trade.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
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
    private IFundsTrade fundsTrade;

    @Override
    public void batchTrade(FssTradeRecordEntity entity){
        FundOrderEntity orderEntity = null;


        try {

            if(entity.getTradeType() !=1003 || entity.getTradeType() != 1004){
                this.recordService.updateTradeRecordExecuteState(entity,2,"90099011");//
                return;
            }

            if(entity.getTradeType() == 1003){
                orderEntity = this.batchWithholding(entity);
            }else if(entity.getTradeType() == 1004){
                orderEntity = this.batchWithdraw(entity);
            }

            entity.setOrderNo(orderEntity.getOrderNo());
            this.recordService.updateTradeRecordExecuteState(entity,1,null);

        } catch (FssException e) {
            LogUtil.error(this.getClass(),e);
            this.recordService.updateTradeRecordExecuteState(entity,2,e.getMessage());//todo 增加失败原因ss
        }




    }


    public FundOrderEntity batchWithholding(FssTradeRecordEntity entity) throws FssException {
        String  accNo = entity.getAccNo();
        FundOrderEntity orderEntity = null;
        if(accNo != null) {
            orderEntity = this.fundsTrade.withholdingApplyNew(accNo, entity.getApplyNo(), entity.getAmount(), entity.getId());
        }else{
//                Integer custId = entity.getCustNo();
//                orderEntity = this.fundsTrade.withholdingApply("");
        }

        return  orderEntity;

    }

    public FundOrderEntity batchWithdraw(FssTradeRecordEntity entity){


        return  null;
    }
}
