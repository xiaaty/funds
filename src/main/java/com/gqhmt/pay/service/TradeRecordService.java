package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.funds.architect.account.bean.FundAccountSequenceBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;
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

    public void recharge(final FundAccountEntity entity,final BigDecimal amount,final FundOrderEntity fundOrderEntity,final int  fundType) throws FssException {
        sequenceService.charge(entity, fundType, amount, ThirdPartyType.FUIOU, fundOrderEntity);
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

    public void unFrozen(FundAccountEntity fromEntity,FundAccountEntity toEntity,BigDecimal amount,int fundType,FundOrderEntity fundOrderEntity,String memo,BigDecimal boundsAmout) throws FssException {
        sequenceService.unfreeze(fromEntity, toEntity, amount, fundType, memo, ThirdPartyType.FUIOU, fundOrderEntity);
//        createFundTrade(fromEntity, BigDecimal.ZERO, amount, 3001, "出借" + title + "，冻结账户资金 " + amount + "元" + (boundsAmount !=null ? ",红包抵扣资金 " + boundsAmount + "元" : ""), (boundsAmount != null? boundsAmount : BigDecimal.ZERO));
    }

    public void transfer(FundAccountEntity fromAcc,FundAccountEntity toAcc,BigDecimal amount,int fundType,int  accountType,FundOrderEntity fundOrderEntity) throws FssException {
        sequenceService.transfer(fromAcc,toAcc,amount,fundType,accountType,null,ThirdPartyType.FUIOU,fundOrderEntity);
    }
    /**
     * 交易记录查询
     * @param cust_no
     * @param user_no
     * @param busi_no
     * @return
     */
    public List<FundTradeEntity> getTradeRecordByParams(Integer cust_no,Integer user_no,Integer busi_no,String trade_type) throws FssException{
    	List<FundTradeEntity> tradelist = fundTradeService.searchTradeRecord(cust_no,user_no,busi_no,trade_type);
    	return tradelist;
    }
    
    /**
     * 账户资金流水查询
     * @param cust_no
     * @param user_no
     * @param busi_no
     * @return
     * @throws FssException
     */
    public FundAccountSequenceBean getTradFlowByParams(Integer cust_no,Integer user_no,Integer busi_no) throws FssException{
    	FundAccountSequenceBean fundsequencelist = sequenceService.searchTradFlow(cust_no,user_no,busi_no);
    	return fundsequencelist;
    }
}
