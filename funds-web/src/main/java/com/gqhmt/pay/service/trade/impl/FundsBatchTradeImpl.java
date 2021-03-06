package com.gqhmt.pay.service.trade.impl;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
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
    private FssTradeRecordService fssTradeRecordService;
    @Resource
    private FssTradeApplyService fssTradeApplyService;
    @Resource
    private IFundsTrade fundsTrade;
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FssTradeProcessService fssTradeProcessService;
    @Override
    public void batchTrade(FssTradeRecordEntity entity,String contractNo,int custType,String seqNo) throws FssException {
        FundOrderEntity orderEntity = null;

        try {

            if(entity.getTradeType() != 1103 && entity.getTradeType() != 1104){
                this.fssTradeRecordService.updateTradeRecordExecuteState(entity,2,"90099011");
                return;
            }

            if(entity.getTradeType() == 1103){
                orderEntity = this.batchWithholding(entity,contractNo,custType,seqNo);
            }else if(entity.getTradeType() == 1104){
                orderEntity = this.batchWithdraw(entity,contractNo,custType,seqNo);
            }
            entity.setOrderNo(orderEntity.getOrderNo());
            this.fssTradeRecordService.updateTradeRecordExecuteState(entity,1,"0000");

        } catch (FssException e) {
            LogUtil.error(this.getClass(),e.getMessage());
            this.fssTradeRecordService.updateTradeRecordExecuteState(entity,2,e.getMessage());//todo 增加失败原因ss
            throw e;
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage());
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
    public FundOrderEntity batchWithholding(FssTradeRecordEntity entity,String contractNo,int custType,String seqNo) throws FssException {
        String  accNo = entity.getAccNo();
        FundOrderEntity orderEntity = null;
        Integer businessType;
        if(accNo != null && !"".equals(accNo)) {
            orderEntity = this.fundsTrade.withholdingApplyNew(accNo,contractNo,entity.getAmount(),entity.getId(),entity.getTradeType(),entity.getTradeTypeChild(),seqNo);
        }else{
        	FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(entity.getCustId(), entity.getCustType());
        	businessType=fundAccountEntity.getBusiType();
            orderEntity = this.fundsTrade.withholdingApplyNew(entity.getCustId().intValue(),businessType,contractNo,entity.getAmount(),entity.getId(),entity.getTradeType(),entity.getTradeTypeChild(),seqNo);
        }
        return  orderEntity;
    }
    /**
     * 批量代付
     * @param entity
     * @return
     */
    public FundOrderEntity batchWithdraw(FssTradeRecordEntity entity,String contractNo,int custType,String seqNo) throws FssException{
    	FundOrderEntity orderEntity = null;
    	String  accNo = entity.getAccNo();//旧版通过账户号获取
    	int	selletType=0;//获取结算类型
    	Integer businessType = 0;//获取业务类型
        if(entity.getBespokeDate()!=null){
    		selletType=fssTradeApplyService.compare_date(entity.getBespokeDate());//结算类型；0 T+0 ; 1 T+1
    	}
    	if(accNo != null && !"".equals(accNo)){
    		orderEntity =this.fundsTrade.withdrawApplyNew(accNo,null,businessType, contractNo, entity.getAmount(), entity.getId(), selletType,entity.getTradeType(),entity.getTradeTypeChild(),seqNo);
    	}else{
    		String custId=null;
        	if(entity.getCustId()!=null && !"".equals(entity.getCustId())){
        		custId = String.valueOf(entity.getCustId());//新版通过custId获取
        		orderEntity = this.fundsTrade.withdrawApplyNew(null,custId, entity.getCustType(),contractNo, entity.getAmount(), entity.getId(), selletType,entity.getTradeType(),entity.getTradeTypeChild(),seqNo);
        	}else{
        		throw new FssException("90002006");
        	}
    	}
        return  orderEntity;
    }

    @Override
    public void batchTrade(TradeProcessEntity entity) throws FssException {
        FundOrderEntity orderEntity = null;
        try {
            if(!entity.getActionType().equals("1401") && !entity.getActionType().equals("1402")){//
                fssTradeProcessService.updateTradeProcessExecuteState(entity,2,"90099011");
                return;
            }
            if(entity.getActionType().equals("1401")){//充值
                orderEntity = this.batchWithholding(entity);
            }else if(entity.getActionType().equals("1402")){//提现
                orderEntity = this.batchWithdraw(entity);
            }
            entity.setOrderNo(orderEntity.getOrderNo());
            fssTradeProcessService.updateTradeProcessExecuteState(entity,1,"0000");

        } catch (FssException e) {
            LogUtil.error(this.getClass(),e.getMessage());
            fssTradeProcessService.updateTradeProcessExecuteState(entity,2,e.getMessage());
            throw e;
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage());
            fssTradeProcessService.updateTradeProcessExecuteState(entity,2,e.getMessage());
            throw e;
        }

    }
    /**
     * 批量代扣
     * @param entity
     * @return
     * @throws FssException
     */
    public FundOrderEntity batchWithholding(TradeProcessEntity entity) throws FssException {
        FundOrderEntity   orderEntity = this.fundsTrade.withholdingApplyNew(Integer.valueOf(entity.getToCustNo()),Integer.valueOf(entity.getToCustType()),entity.getLoanContractNo(),entity.getAmt(),entity.getId(),Integer.valueOf(entity.getTradeTypeParent()),Integer.valueOf(entity.getTradeType()),entity.getSeqNo(),entity.getOrderNo());
        return  orderEntity;
    }
    /**
     * 批量代付
     * @param entity
     * @return
     */
    public FundOrderEntity batchWithdraw(TradeProcessEntity entity) throws FssException{
        FundOrderEntity orderEntity = null;
        if(entity.getFromAccId()!=null && !"".equals(entity.getFromAccId())){
            orderEntity = this.fundsTrade.withdrawApplyNew(null,entity.getFromAccId().toString(), Integer.valueOf(entity.getFromCustType()),entity.getLoanContractNo(), entity.getAmt(), entity.getId(), Integer.valueOf(entity.getSettleType()),Integer.valueOf(entity.getTradeTypeParent()),Integer.valueOf(entity.getTradeType()),entity.getSeqNo(),entity.getOrderNo());
        }else{
            throw new FssException("90002006");
        }
        return  orderEntity;
    }
}
