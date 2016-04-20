package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.entity.FssTransRecordEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordReadMapper;
import com.gqhmt.fss.architect.trade.mapper.read.FssTransRecordReadMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.bean.FundTradeBean;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.util.ThirdPartyType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Filename:    com.gqhmt.pay.service.TradeRecordService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/17 09:28
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/17  于泳      1.0     1.0 Version
 */

@Service
public class TradeRecordService {

    private final String  thirdPartyType = "2";

    @Resource
    private FundSequenceService sequenceService;

    @Resource
    private FundTradeService fundTradeService;

    @Resource
    private FundWithrawChargeService fundWithrawChargeService;
    
    @Resource
    private FssTradeRecordReadMapper fssTradeRecordReadMapper;
    
    @Resource
    private FssTransRecordReadMapper fssTransRecordReadMapper;

    public void recharge(final FundAccountEntity entity,final BigDecimal amount,final FundOrderEntity fundOrderEntity,final int  fundType) throws FssException {
        try {
            sequenceService.charge(entity, fundType, amount, ThirdPartyType.FUIOU, fundOrderEntity);
        }catch (Exception e){
            String  tmp = e.getMessage();
            if(tmp != null && tmp.contains("funds_token_uk")){
                throw new FssException("90004019");
            }
        }
        // this.fundTradeService.createFundTrade(entity, amount, BigDecimal.ZERO, fundType, "充值成功，充值金额 " + amount + "元");
        //super.sendNotice(NoticeService.NoticeType.FUND_CHARGE, entity, amount,BigDecimal.ZERO);
    }

    public void withdraw(final FundAccountEntity entity,final BigDecimal amount,final FundOrderEntity fundOrderEntity,final int  fundType) throws FssException {
        sequenceService.refund(entity,fundType,amount,ThirdPartyType.FUIOU,fundOrderEntity);
    }

    public void withdrawByFroze(final FundAccountEntity entity,final BigDecimal amount,final FundOrderEntity fundOrderEntity,final int  fundType) throws FssException {
        sequenceService.refund(entity,fundType,amount,ThirdPartyType.FUIOU,fundOrderEntity);
    }


    public void frozen(FundAccountEntity fromEntity,FundAccountEntity toEntity,BigDecimal amount,int fundType,FundOrderEntity fundOrderEntity,String memo,BigDecimal boundsAmout) throws FssException {
        sequenceService.frozenAmt(fromEntity, toEntity, amount, fundType, memo, ThirdPartyType.FUIOU, fundOrderEntity,boundsAmout);
//        createFundTrade(fromEntity, BigDecimal.ZERO, amount, 3001, "出借" + title + "，冻结账户资金 " + amount + "元" + (boundsAmount !=null ? ",红包抵扣资金 " + boundsAmount + "元" : ""), (boundsAmount != null? boundsAmount : BigDecimal.ZERO));
    }

    /**
     *
     * @param fromEntity
     * @param toEntity
     * @param amount
     * @param fundType
     * @param fundOrderEntity
     * @param memo
     * @param boundsAmout
     * @throws FssException
     */
    public void unFrozen(FundAccountEntity fromEntity,FundAccountEntity toEntity,BigDecimal amount,int fundType,FundOrderEntity fundOrderEntity,String memo,BigDecimal boundsAmout) throws FssException {
        sequenceService.unfreeze(fromEntity, toEntity, amount, fundType, memo, ThirdPartyType.FUIOU, fundOrderEntity);
//        createFundTrade(fromEntity, BigDecimal.ZERO, amount, 3001, "出借" + title + "，冻结账户资金 " + amount + "元" + (boundsAmount !=null ? ",红包抵扣资金 " + boundsAmount + "元" : ""), (boundsAmount != null? boundsAmount : BigDecimal.ZERO));
    }

    public void transfer(FundAccountEntity fromAcc,FundAccountEntity toAcc,BigDecimal amount,Integer  fundType,FundOrderEntity fundOrderEntity) throws FssException {
        sequenceService.transfer(fromAcc,toAcc,amount,8,fundType,null,ThirdPartyType.FUIOU,fundOrderEntity);
    }
    /**
     * 交易记录查询
     * @param cust_no
     * @return
     */
    public List<FundTradeBean> queryFundTrade(Integer cust_no,String str_trade_time,String end_trade_time,String tradeFilters) throws FssException{
    	List<FundTradeBean> tradelist = fundTradeService.queryFundTrade(cust_no,str_trade_time,end_trade_time,tradeFilters);
    	return tradelist;
    }


    public void chargeAmount(FundAccountEntity entity, FundAccountEntity toEntity, FundOrderEntity fundOrderEntity, FundOrderEntity fundOrderEntityCharge) throws FssException {
        sequenceService.transfer(entity, toEntity, fundOrderEntity.getChargeAmount(), 22,4010,"收取提现手续费", ThirdPartyType.FUIOU, fundOrderEntityCharge);
        this.fundWithrawChargeService.updateSrate(fundOrderEntity.getOrderNo(),3);
    }
    
    /**
     * 账户资金流水查询
     * @param cust_no
     * @param user_no
     * @param busi_no
     * @return
     * @throws FssException
     */
  /*  public FundAccountSequenceBean getTradFlowByParams(Integer cust_no,Integer user_no,Integer busi_no) throws FssException{
    	FundAccountSequenceBean fundsequencelist = sequenceService.searchTradFlow(cust_no,user_no,busi_no);
    	return fundsequencelist;
    }*/
    
    /**
     * 查询充值/提现记录
     * @param traderecorder
     * @return
     */
    public List<FssTradeRecordEntity> queryRechargeList(FssTradeRecordEntity traderecorder){
    	List<FssTradeRecordEntity> traderecorderlist=fssTradeRecordReadMapper.select(traderecorder);
    	return traderecorderlist;
    }
    
    /**
     * 转账交易记录查询
     * @param transrecord
     * @return
     */
    public List<FssTransRecordEntity> queryTransRecordList(FssTransRecordEntity transrecord){
    	List<FssTransRecordEntity> transrecordlist=fssTransRecordReadMapper.select(transrecord);
    	return transrecordlist;
    	
    }


    public  int parseBusinessType(int accType){

        int businessType = 0;

        switch (accType){
            case 10010001:
                businessType = 3;
                break;
            case 10010002:
                businessType = 2;
                break;
            case 10010003:
                businessType = 1;
                break;
            case 10010004:
                businessType = 0;
                break;
            case 10010005:
                businessType = 1;
                break;
            case 10011000:
                businessType = 1;
                break;

            case 10011001:
            	businessType = 99;
            	break;
            case 10011002:
            	businessType = 1;
            	break;
            case 10012001:
            	businessType = 1;
            	break;
            case 10012002:
            	businessType = 1;
            	break;
            case 10012003:
            	businessType = 1;
            	break;
            case 10019001:
            	businessType = 2;
            	break;
            case 10019002:
            	businessType = 96;
            	break;
            case 10010007:
                businessType = 1;
                break;
            default:
                businessType = 0;
        }

        return businessType;
    }
    
}
