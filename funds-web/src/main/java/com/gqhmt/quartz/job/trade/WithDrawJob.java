package com.gqhmt.quartz.job.trade;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.quartz.job.SupperJob;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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
 * <p>线上提现</p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/14  于泳      1.0     1.0 Version
 */
@Component
public class WithDrawJob extends SupperJob{

    @Resource
    private FssTradeProcessService tradeProcessService;
    @Resource
    private PaySuperByFuiou paySuperByFuiou;
    @Resource
    private TradeRecordService tradeRecordService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private IFundsTrade fundsTradeImpl;

    @Resource
    private FundWithrawChargeService fundWithrawChargeService;
    private static boolean isRunning = false;
    @Scheduled(cron="32 0/1 * * * * ")
    public void execute() throws FssException {
        if(!isIp("upload")){
            return;
        }
        if(isRunning) return;
        startLog("线上提现");
        isRunning = true;
        try {
            List<TradeProcessEntity> list= tradeProcessService.getWithDrawProcess();
            if(CollectionUtils.isNotEmpty(list)){
                for (TradeProcessEntity entity:list) {
                    //进行提现
                    FundOrderEntity fundOrderEntity=this.withdraw(entity);
                    //进行提现费用收取
                    tradeProcessService.charge(entity,fundOrderEntity);
                    //修改主订单流程状态
                    entity.setProcessState("10170003");
                    tradeProcessService.updateTradeProcessEntity(entity);


                }
            }
		} catch (Exception e) {
			  LogUtil.error(getClass(),e);
		}finally{
			isRunning = false;
		}
        endtLog();
    }


    @Override
    public boolean isRunning() {
        return isRunning;
    }
    //提现
    public FundOrderEntity withdraw(TradeProcessEntity entity) throws FssException {
        //查询提现子交易
        List<TradeProcessEntity> withList=tradeProcessService.findByParentIdAndActionType("1104",String.valueOf(entity.getId()));
        if(CollectionUtils.isEmpty(withList)) return null;
        TradeProcessEntity withdraw=withList.get(0);
        //查询提现出账账户
        FundAccountEntity fromEntity=fundAccountService.select(entity.getFromAccId());
        //得到线上账户
        FundAccountEntity account = fundsTradeImpl.getFundAccount(Integer.parseInt(entity.getFromCustNo()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
        FundOrderEntity fundOrderEntity=null;
        try {
            //调用富友提现接口
            fundOrderEntity = paySuperByFuiou.withdraw(fromEntity, entity.getAmt(), withdraw.getAmt(), GlobalConstants.ORDER_AGENT_WITHDRAW, 0l, 0, "1104", entity.getTradeType(), null, null, entity.getOrderNo());
            //创建交易流水
            tradeRecordService.withdrawByFroze(account, fundOrderEntity.getOrderAmount(), fundOrderEntity, 2003, entity.getSeqNo(), entity.getTradeType());
            //提现成功修改提现子交易状态
            withdraw.setProcessState("10050030");//处理完成
            withdraw.setStatus("10030002");//交易成功
            //修改主交易状态
            entity.setProcessState("10050030");//提现成功
            entity.setStatus("10030002");//交易成功
        }catch (Exception e){
            //提现失败修改提现子交易状态
            withdraw.setProcessState("10050031");//处理完成
            withdraw.setStatus("10030003");//交易失败
            //修改主交易状态

            entity.setProcessState("10050031");//提现失败
            entity.setStatus("10030003");//交易失败
        }
        tradeProcessService.updateTradeProcessEntity(withdraw);
        tradeProcessService.updateTradeProcessEntity(entity);
        return fundOrderEntity;
    }


}
