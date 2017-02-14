package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.exception.PayChannelNotSupports;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.quartz.job.SupperJob;
import com.gqhmt.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.quartz.fuiouFtp.trade.BatchWithholdingJob
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/14 16:15
 * Description:
 * <p>线上提现失败job</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class FailWithDrawJob extends SupperJob{

    @Resource
    private FssTradeProcessService tradeProcessService;
    @Resource
    private TradeRecordService tradeRecordService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private IFundsTrade fundsTradeImpl;

    @Resource
    private FssCheckAccountingService fssCheckAccountingService;

    @Resource
    private FundOrderService fundOrderService;
    private static boolean isRunning = false;
    @Scheduled(cron="36 0/5 * * * * ")
    public void execute() throws PayChannelNotSupports {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        startLog("线上提现失败JOB");
        isRunning = true;
        try {
            //获得提现失败数据
            List<TradeProcessEntity> list= tradeProcessService.getFailWithDrawProcess();
            if(CollectionUtils.isNotEmpty(list)){
                for (TradeProcessEntity entity:list) {
                    //查询出该笔交易的提现子交易
                    TradeProcessEntity withDraw=tradeProcessService.findByParentIdAndActionType("1104",entity.getId().toString()).get(0);
                    //查询出账账户
                    FundAccountEntity fromEntity=fundAccountService.select(withDraw.getFromAccId());
                    //查询出账账户
                    String repCode=this.getResult(withDraw);
                        if(StringUtils.isNotEmpty(repCode)){

                            FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(withDraw.getOrderNo());
//                            //查询提现收费子交易
//                            List<TradeProcessEntity> chilList=tradeProcessService.findByParentIdAndActionType("1106",String.valueOf(entity.getId()));
//                            TradeProcessEntity chargeEntity=null;
//                            if(CollectionUtils.isNotEmpty(chilList)){
//                                //收费子交易只有一条
//                                 chargeEntity=chilList.get(0);
//                            }
//                            BigDecimal chargeAmt=BigDecimal.ZERO;
//                            if(chargeEntity!=null){
//                                chargeAmt=chargeEntity.getAmt();
//                            }
                            //富友成功
                            if(StringUtils.equals(repCode,"0000")){
                                //查询订单表修改订单状态
                                fundOrderEntity.setOrderState(2);
                                fundOrderEntity.setRetCode("0000");
                                fundOrderEntity.setRetMessage("成功");
                                fundOrderService.update(fundOrderEntity);
                                //创建交易流水
                                tradeRecordService.withdrawByFroze(account, fundOrderEntity.getOrderAmount(), fundOrderEntity, 2003, entity.getSeqNo(), entity.getTradeType());

                                //提现成功修改提现子交易状态
                                withdraw.setRespCode(fundOrderEntity.getRetCode());
                                withdraw.setRespMsg(fundOrderEntity.getRetMessage());
                                withDraw.setProcessState("10050030");//提现成功
                                withDraw.setStatus("10030002");//交易成功
                                tradeProcessService.updateTradeProcessEntity(withDraw);
                                //修改流程表状态
                                entity.setProcessState("10050030");//提现成功
                                entity.setStatus("10030002");//交易成功
                                tradeProcessService.updateTradeProcessEntity(entity);
                                //进行收费
                                tradeProcessService.charge(entity,fundOrderEntity);
                            }else {
                                //得到线上账户
                                FundAccountEntity account = fundsTradeImpl.getFundAccount(Integer.parseInt(entity.getFromCustNo()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
                                //进行资金解冻
                                tradeRecordService.unFrozen(fromEntity,account,entity.getAmt(),1004,null,"提现失败，退回金额"+ entity.getAmt() + "元",BigDecimal.ZERO,entity.getTradeType(),entity.getSeqNo());
                                //修改流程表状态
                                entity.setProcessState("10050088");//处理完成
                                entity.setStatus("10030003");
                                tradeProcessService.updateTradeProcessEntity(entity);
                            }
                    }
                }
            }
		} catch (Exception e) {
			  LogUtil.error(getClass(),e);
		}finally{
			isRunning = false;
		}
        endtLog();
    }
    //查询富友，查看该笔交易是成功还是失败
    public String getResult(TradeProcessEntity entity) throws FssException {
        //查询出账账户
        FundAccountEntity fromEntity=fundAccountService.select(entity.getFromAccId());

        String startTime=DateUtil.dateToString_24(entity.getCreateTime());
        String endTime=DateUtil.dateToString_24(new Date());

        if(StringUtils.isEmpty(entity.getOrderNo())) return null;

        //查询富友查看得到该客户提现数据
        List<Map<String,String>> listMap=fssCheckAccountingService.getFuiouTradeCz(fromEntity,startTime,endTime);
        for (Map<String,String> map: listMap ) {
            if(StringUtils.equals(map.get("mchnt_txn_ssn"),entity.getOrderNo())){
                return map.get("txn_rsp_cd");
            }
        }
        return null;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }



}
