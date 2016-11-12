package com.gqhmt.pay.service;

import com.gqhmt.conversion.bean.request.ConverBean;
import com.gqhmt.conversion.bean.response.ReqContentResponse;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.service.ConversionService;
import com.gqhmt.fss.architect.account.service.FssAccountBindService;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.entity.FssTransRecordEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeRecordReadMapper;
import com.gqhmt.fss.architect.trade.mapper.read.FssTransRecordReadMapper;
import com.gqhmt.fss.architect.trade.service.FssOfflineRechargeService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.account.service.NoticeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.bean.FundTradeBean;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.util.ThirdPartyType;
import com.gqhmt.util.XmlUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final String thirdPartyType = "2";
    @Resource
    private FundSequenceService sequenceService;
    @Resource
    private FundTradeService fundTradeService;
    @Resource
    private PaySuperByFuiou paySuperByFuiou;
    @Resource
    private FundWithrawChargeService fundWithrawChargeService;
    @Resource
    private FssTradeRecordReadMapper fssTradeRecordReadMapper;
    @Resource
    private FssTransRecordReadMapper fssTransRecordReadMapper;
    @Resource
    private FundOrderService fundOrderService;
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private IFundsTrade fundsTradeImpl;
    @Resource
    private FssTradeRecordService fssTradeRecordService;
    @Resource
    private FssOfflineRechargeService fssOfflineRechargeService;
    @Resource
    private ConversionService conversionService;
    @Resource
    private FssAccountBindService fssAccountBindService;


    public void recharge(final FundAccountEntity entity, final BigDecimal amount, final FundOrderEntity fundOrderEntity, final int fundType, String tradeType) throws FssException {
        try {
            sequenceService.charge(entity, fundType, amount, ThirdPartyType.FUIOU, fundOrderEntity, tradeType);
//            -----------------------调用统一支付进行记账----------------
            this.asynchronousCallRecharge(entity,fundOrderEntity.getOrderAmount(),fundOrderEntity,1001,null,"充值");
        } catch (Exception e) {
            String tmp = e.getMessage();
            if (tmp != null && tmp.contains("funds_token_uk")) {
                throw new FssException("90004019");
            }
        }
        // this.fundTradeService.createFundTrade(entity, amount, BigDecimal.ZERO, fundType, "充值成功，充值金额 " + amount + "元");
        //super.sendNotice(NoticeService.NoticeType.FUND_CHARGE, entity, amount,BigDecimal.ZERO);
    }

    public void withdraw(final FundAccountEntity entity, final BigDecimal amount, final FundOrderEntity fundOrderEntity, final int fundType, final String tradeType) throws FssException {
        sequenceService.refund(entity, fundType, amount, ThirdPartyType.FUIOU, fundOrderEntity, tradeType);
//        ---------------------------异步调用统一支付---------------------------
//        this.asynchronousCallWithDraw(entity, fundOrderEntity.getOrderAmount(),fundOrderEntity,1001,null,"提现");
    }

    public void withdrawByFroze(final FundAccountEntity entity, final BigDecimal amount, final FundOrderEntity fundOrderEntity, final int fundType) throws FssException {
        sequenceService.refundByFroze(entity, fundType, amount, ThirdPartyType.FUIOU, fundOrderEntity);
//        ---------------------------批量代付异步调用统一支付-----------------
//        this.asynchronousCallTyzf();
    }


    public void frozen(FundAccountEntity fromEntity, FundAccountEntity toEntity, BigDecimal amount, int fundType, FundOrderEntity fundOrderEntity, String memo, BigDecimal boundsAmout, String tradeType) throws FssException {
        sequenceService.frozenAmt(fromEntity, toEntity, amount, fundType, memo, ThirdPartyType.FUIOU, fundOrderEntity, boundsAmout, tradeType);
//        createFundTrade(fromEntity, BigDecimal.ZERO, amount, 3001, "冻结账户资金 " + amount + "元" + (boundsAmout !=null ? ",红包抵扣资金 " + boundsAmout + "元" : ""), (boundsAmout != null? boundsAmout : BigDecimal.ZERO));
//        ---------------------------异步调用统一支付处理冻结-------------------------
//        this.asynchronousCallTyzf();
    }

    public void refundFeozzen(FundAccountEntity fromEntity, FundAccountEntity toEntity, BigDecimal amount, BigDecimal chargeAmount, String tradeType) throws FssException {
        sequenceService.frozenAmtByRefund(fromEntity, toEntity, amount, chargeAmount, tradeType);
//        --------------调用统一支付处理冻结--------------------
//        this.asynchronousCallTyzf();
    }

    /**
     * 冻结重载
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
    public void frozen(FundAccountEntity fromEntity, FundAccountEntity toEntity, BigDecimal amount, int fundType, FundOrderEntity fundOrderEntity, String memo, BigDecimal boundsAmout, String newFundsType, String tradeType, String lendNo, Long toCustId, String toLendNo, Long loanCustId, String loanNo) throws FssException {
        sequenceService.frozenAmt(fromEntity, toEntity, amount, fundType, memo, fundOrderEntity, boundsAmout, newFundsType, tradeType, lendNo, toCustId, toLendNo, loanCustId, loanNo);
//        --------------------异步调用统一支付处理投标冻结-----------------------------
//        this.asynchronousCallTyzf();
    }

    /**
     * 资金解冻
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
    public void unFrozen(FundAccountEntity fromEntity, FundAccountEntity toEntity, BigDecimal amount, int fundType, FundOrderEntity fundOrderEntity, String memo, BigDecimal boundsAmout, String tradeType) throws FssException {
        sequenceService.unfreeze(fromEntity, toEntity, amount, fundType, memo, ThirdPartyType.FUIOU, fundOrderEntity, tradeType);
//        --------------------异步冻调用统一支付处理解-----------------------
//        this.asynchronousCallTyzf();


//        createFundTrade(fromEntity, BigDecimal.ZERO, amount, 3001, "出借" + title + "，冻结账户资金 " + amount + "元" + (boundsAmount !=null ? ",红包抵扣资金 " + boundsAmount + "元" : ""), (boundsAmount != null? boundsAmount : BigDecimal.ZERO));
    }

    public void unFrozen(FundAccountEntity fromEntity, FundAccountEntity toEntity, BigDecimal amount, int fundType, FundOrderEntity fundOrderEntity, String memo, BigDecimal boundsAmout, String lendNo, String loanNo, Long loanCustId) throws FssException {
//        sequenceService.unfreeze(fromEntity, toEntity, amount, fundType, memo, fundOrderEntity,"1108",null,lendNo,fromEntity.getCustId(),toEntity.getCustId(),null,loanNo);
        sequenceService.unfreeze(fromEntity, toEntity, amount, fundType, memo, fundOrderEntity, "1108", null, lendNo, fromEntity.getCustId(), null, loanCustId, loanNo);
//        createFundTrade(fromEntity, BigDecimal.ZERO, amount, 3001, "出借" + title + "，冻结账户资金 " + amount + "元" + (boundsAmount !=null ? ",红包抵扣资金 " + boundsAmount + "元" : ""), (boundsAmount != null? boundsAmount : BigDecimal.ZERO));
//       ---------------------------- 流标调用统一支付------------------------
//        this.asynchronousCallTyzf();

    }

    public void transfer(FundAccountEntity fromAcc, FundAccountEntity toAcc, BigDecimal amount, Integer fundType, FundOrderEntity fundOrderEntity, Integer actionType) throws FssException {
        sequenceService.transfer(fromAcc, toAcc, amount, actionType, fundType, null, ThirdPartyType.FUIOU, fundOrderEntity);
        //----------------------------调用统一支付------------------
//        this.asynchronousCallTyzf();
    }

    /**
     * 转账接口重载
     *
     * @param fromAcc
     * @param toAcc
     * @param amount
     * @param fundType
     * @param fundOrderEntity
     * @param actionType
     * @param memo
     * @param newFundsType
     * @param tradeType
     * @param lendNo
     * @param toCustId
     * @param toLendNo
     * @param loanCustId
     * @param loanNo
     * @throws FssException
     */
    public void transfer(FundAccountEntity fromAcc, FundAccountEntity toAcc, BigDecimal amount, Integer fundType, FundOrderEntity fundOrderEntity, Integer actionType, String memo, String newFundsType, String tradeType, String lendNo, Long toCustId, String toLendNo, Long loanCustId, String loanNo) throws FssException {
        sequenceService.transfer(fromAcc, toAcc, actionType, fundType, amount, memo, fundOrderEntity, newFundsType, tradeType, lendNo, toCustId, toLendNo, loanCustId, loanNo);
//        异步调用统一支付处理转账
//        this.asynchronousCallTyzf();
    }

    /**
     * 交易记录查询
     *
     * @param cust_no
     * @return
     */
    public List<FundTradeBean> queryFundTrade(Integer cust_no, String str_trade_time, String end_trade_time, String tradeFilters) throws FssException {
        List<FundTradeBean> tradelist = fundTradeService.queryFundTrade(cust_no, str_trade_time, end_trade_time, tradeFilters);
        return tradelist;
    }


    public void chargeAmount(FundAccountEntity entity, FundAccountEntity toEntity, FundOrderEntity fundOrderEntity, FundOrderEntity fundOrderEntityCharge) throws FssException {
        sequenceService.transfer(entity, toEntity, fundOrderEntity.getChargeAmount(), 22, 4010, "收取提现手续费", ThirdPartyType.FUIOU, fundOrderEntityCharge);
        this.fundWithrawChargeService.updateSrate(fundOrderEntity.getOrderNo(), 3);
        this.fundTradeService.addFundTrade(entity, BigDecimal.ZERO, fundOrderEntity.getChargeAmount(), 4010, "收取手续费", BigDecimal.ZERO);
        this.fundTradeService.addFundTrade(toEntity, fundOrderEntity.getChargeAmount(), BigDecimal.ZERO, 4010, "收取手续费");
//        ------------提现手续费-------------
//        this.asynchronousCallTyzf();

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
     *
     * @param traderecorder
     * @return
     */
    public List<FssTradeRecordEntity> queryRechargeList(FssTradeRecordEntity traderecorder) {
        List<FssTradeRecordEntity> traderecorderlist = fssTradeRecordReadMapper.select(traderecorder);
        return traderecorderlist;
    }

    /**
     * 查询充值/提现记录
     *
     * @param map
     * @return
     */
    public List<FssTradeRecordEntity> queryRechargeList(Map<String, String> map) {
        Map<String, String> map2 = new HashMap<String, String>();
        if (map != null) {
            String startTime = map.get("startTime");
            String endTime = map.get("endTime");
            map2.put("type", map.get("type"));
            map2.put("applyNo", map.get("applyNo"));
            map2.put("accNo", map.get("accNo"));
            map2.put("tradeState", map.get("tradeState"));
            map2.put("resultState", map.get("resultState"));
            map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
            map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
        }
        List<FssTradeRecordEntity> traderecorderlist = fssTradeRecordReadMapper.getRecordList(map2);
        return traderecorderlist;
    }

    /**
     * 转账交易记录查询
     *
     * @param transrecord
     * @return
     */
    public List<FssTransRecordEntity> queryTransRecordList(FssTransRecordEntity transrecord) {
        List<FssTransRecordEntity> transrecordlist = fssTransRecordReadMapper.select(transrecord);
        return transrecordlist;

    }

    /**
     * 无订单，富友后台充值回调此接口
     *
     * @param orderNo
     * @param state
     * @param amt
     * @param mobile
     */
    public void asynNotOrderCommand(String orderNo, String state, String amt, String mobile) throws FssException {
        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);

        if (fundOrderEntity == null) {
            FundAccountEntity entity = fundAccountService.getFundAccount(mobile, GlobalConstants.ACCOUNT_TYPE_LEND_ON);
            if (entity == null) {
                entity = fundAccountService.getFundAccount(mobile, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
            }
            int soruceType = 0;
            BigDecimal amount = new BigDecimal(amt).divide(new BigDecimal("100"));
            fundOrderEntity = paySuperByFuiou.createOrder(entity, amount, soruceType, 0, 0, "", "");
            fundOrderEntity.setOrderNo(orderNo);
        }
        asynCommand(fundOrderEntity, state);
    }

    private void asynCommand(String orderNo, String state, String amt) throws FssException {
        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
        if (fundOrderEntity == null) {
            LogUtil.info(this.getClass(), "未找到订单号:" + orderNo);
            return;
        }
        if (fundOrderEntity == null) {
            throw new FssException(orderNo + "订单获取失败");
        }
        asynCommand(fundOrderEntity, state);

    }

    public void asynCommand(FundOrderEntity fundOrderEntity, String state) throws FssException {
        if (!"success".equalsIgnoreCase(state)) {
            paySuperByFuiou.updateOrder(fundOrderEntity, 3, "10000", "失败");
            this.asynSequenceFailed(fundOrderEntity);
            throw new FssException("交易失败，请重新交易，如已成功，请勿重读操作");
        }

        this.asynSequence(fundOrderEntity);
    }

    /**
     * 成功订单回调
     *
     * @param fundOrderEntity
     */
    private void asynSequence(FundOrderEntity fundOrderEntity) throws FssException {
        FundAccountEntity entity = fundAccountService.getFundAccountInfo(fundOrderEntity.getAccountId());
        if (entity == null) {
            throw new FssException("未获取到相关账户信息");
        }

        if (fundOrderEntity.getOrderState() == 2) {
            return;
        }

        //验证入账数量，充值、提现每个订单只允许一条入账记录，本记录作为再次验证，以免出现多次入账问题
        int size = sequenceService.getSizeByOrderNo(fundOrderEntity.getOrderNo());
        if (size > 0) {
            return;
        }

        if (fundOrderEntity.getOrderType() == GlobalConstants.ORDER_CHARGE) {
            LogUtil.info(this.getClass(), entity.getCustName() + " 订单:" + fundOrderEntity.getOrderNo() + " 充值成功 " + fundOrderEntity.getOrderAmount().toPlainString());
            //充值成功，充值金额 "+amount+"元
            //充值操作
            try {

                boolean isOffline = false;
                if (fundOrderEntity.getOrderFrormId() != null && fundOrderEntity.getOrderFrormId() != 0) {
                    isOffline = true;
                }
                sequenceService.charge(entity, 1001, fundOrderEntity.getOrderAmount(), ThirdPartyType.FUIOU, fundOrderEntity, fundOrderEntity.getTradeType());
                fundsTradeImpl.sendNotice(CoreConstants.FUND_CHARGE_TEMPCODE, NoticeService.NoticeType.FUND_WITHDRAW, entity, fundOrderEntity.getOrderAmount(), BigDecimal.ZERO);

                if (isOffline) {
                    fssOfflineRechargeService.fuiouCallBack(fundOrderEntity.getOrderFrormId(), "0000");
                }
            } catch (FssException e) {
                boolean isfundUk = false;
                Throwable t = e.getCause();
                while (t != null) {
                    if (t.getMessage().contains("funds_token_uk")) {
                        isfundUk = true;
                        break;
                    }
                    t = t.getCause();
                }
                if (!isfundUk) {
                    throw e;
                }
            }
        } else if (fundOrderEntity.getOrderType() == GlobalConstants.ORDER_WITHDRAW) {
            LogUtil.info(this.getClass(), entity.getCustName() + " 订单:" + fundOrderEntity.getOrderNo() + " 提现成功 " + fundOrderEntity.getOrderAmount().toPlainString());
            //提现
            try {
                sequenceService.refund(entity, 2003, fundOrderEntity.getOrderAmount(), ThirdPartyType.FUIOU, fundOrderEntity, fundOrderEntity.getTradeType());
                fundsTradeImpl.sendNotice(CoreConstants.FUND_WITHDRAW_TEMPCODE, NoticeService.NoticeType.FUND_WITHDRAW, entity, fundOrderEntity.getOrderAmount(), BigDecimal.ZERO);
                fundWithrawChargeService.updateSrate(fundOrderEntity.getOrderNo(), 2);
                //提现手续费收取实现方法
//                this.chargeAmount(entity.getUserName(),fundOrderEntity);
            } catch (FssException e) {
                boolean isfundUk = false;
                Throwable t = e.getCause();
                while (t != null) {
                    if (t.getMessage().contains("funds_token_uk")) {
                        isfundUk = true;
                        LogUtil.info(this.getClass(), "重复记账异常");
                        break;
                    }
                    t = t.getCause();
                }
                if (!isfundUk) {
                    throw e;
                }
            } catch (Exception e) {
                LogUtil.error(this.getClass(), e.getMessage(), e);
            }
        } else if (fundOrderEntity.getOrderType() == GlobalConstants.ORDER_WITHHOLDING) {
            LogUtil.info(this.getClass(), entity.getCustName() + " 订单:" + fundOrderEntity.getOrderNo() + " 充值成功 " + fundOrderEntity.getOrderAmount().toPlainString());

            //代扣
            sequenceService.charge(entity, 1002, fundOrderEntity.getOrderAmount(), ThirdPartyType.FUIOU, fundOrderEntity, fundOrderEntity.getTradeType());
            fundsTradeImpl.sendNotice(CoreConstants.FUND_CHARGE_TEMPCODE, NoticeService.NoticeType.FUND_WITHDRAW, entity, fundOrderEntity.getOrderAmount(), BigDecimal.ZERO);
            if (entity.getBusiType().intValue() == GlobalConstants.ACCOUNT_TYPE_LEND_ON) {
                //首充红包or冠钱派发
//                promoteService.saveFirstRecharge(entity.getCustId(), entity.getUserId());
            }

            try {
                fssTradeRecordService.updateTradeRecord(fundOrderEntity.getOrderFrormId(), "0");
            } catch (Exception e) {
                LogUtil.error(this.getClass(), e.getMessage(), e);
            }
        } else if (fundOrderEntity.getOrderType() == GlobalConstants.ORDER_AGENT_WITHDRAW) {
            LogUtil.info(this.getClass(), entity.getCustName() + " 订单:" + fundOrderEntity.getOrderNo() + " 提现成功 " + fundOrderEntity.getOrderAmount().toPlainString());

            //代付
            sequenceService.refund(entity, 2003, fundOrderEntity.getOrderAmount(), ThirdPartyType.FUIOU, fundOrderEntity, fundOrderEntity.getTradeType());
            if (fundOrderEntity.getOrderSource() != null && fundOrderEntity.getOrderSource() == GlobalConstants.BUSINESS_WITHDRAW) {
                try {
                    fssTradeRecordService.updateTradeRecord(fundOrderEntity.getOrderFrormId(), "0");
                } catch (Exception e) {
                    LogUtil.error(this.getClass(), e.getMessage(), e);
                }
            }
            fundsTradeImpl.sendNotice(CoreConstants.FUND_WITHDRAW_TEMPCODE, NoticeService.NoticeType.FUND_WITHDRAW, entity, fundOrderEntity.getOrderAmount(), BigDecimal.ZERO);
        } else if (fundOrderEntity.getOrderType() == GlobalConstants.ORDER_RECHARGE_OFFLINE) {
            sequenceService.charge(entity, 1014, fundOrderEntity.getOrderAmount(), ThirdPartyType.FUIOU, fundOrderEntity, fundOrderEntity.getTradeType());
            fssOfflineRechargeService.fuiouCallBack(fundOrderEntity.getId(), "0000");

        }

        fundOrderService.updateOrder(fundOrderEntity, 2, "0000", "成功");
    }

    /**
     * 处理失败订单回调
     *
     * @param fundOrderEntity
     */
    private void asynSequenceFailed(FundOrderEntity fundOrderEntity) {
        if (fundOrderEntity.getOrderType() == GlobalConstants.ORDER_WITHHOLDING) {
            try {
                fssTradeRecordService.updateTradeRecord(fundOrderEntity.getOrderFrormId(), "1");
            } catch (Exception e) {
                LogUtil.error(this.getClass(), e.getMessage(), e);
            }
        } else if (fundOrderEntity.getOrderType() == GlobalConstants.ORDER_AGENT_WITHDRAW) {

            try {
                fssTradeRecordService.updateTradeRecord(fundOrderEntity.getOrderFrormId(), "1");
            } catch (Exception e) {
                LogUtil.error(this.getClass(), e.getMessage(), e);
            }
        } else if (fundOrderEntity.getOrderType() == GlobalConstants.ORDER_WITHDRAW) {
            //提现
            FundAccountEntity entity = fundAccountService.getFundAccountInfo(fundOrderEntity.getAccountId());
            //去掉资金冻结
            //forzen(entity,fundOrderEntity);
        }
    }


    public int parseBusinessType(int accType) {

        int businessType = 0;

        switch (accType) {
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
                businessType = 0;
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
                businessType = 1;
                break;
            case 10010007:
                businessType = 1;
                break;
            default:
                businessType = 0;
        }

        return businessType;
    }





    public void asynchronousCallRecharge(FundAccountEntity entity,BigDecimal amount,FundOrderEntity fundOrderEntity,int fundType,String tradeType,String flag) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId().toString(), entity.getBusiType().toString());
        ConverBean bean = new ConverBean();
        //参数传入
        if ("充值".equals(flag)) {//参数待定
            bean.setOrderId(bindEntity.getAccNo());
            bean.setProdID(bindEntity.getBusiType().toString());
        }
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }




    public void asynchronousCallWithHold(final FundAccountEntity entity, final BigDecimal amount, final FundOrderEntity fundOrderEntity, final int fundType, String tradeType, String flag) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId().toString(), entity.getBusiType().toString());
        ConverBean bean = new ConverBean();
        //参数传入
        if ("提现".equals(flag)) {//参数待定
            bean.setOrderId(bindEntity.getAccNo());
            bean.setProdID(bindEntity.getBusiType().toString());
        }
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }

    /**
     * 异步调用统一支付记账处理
     * @param entity
     * @param amount
     * @param fundOrderEntity
     * @param fundType
     * @param tradeType
     * @param flag 用来区分是充值、提现、转账、投标、满标、回款 交易
     * @throws FssException
     */
    public void asynchronousCallTyzf(final FundAccountEntity entity, final BigDecimal amount, final FundOrderEntity fundOrderEntity, final int fundType, String tradeType, String flag) throws FssException {
        //TODO: 2016/11/11 需要考虑记账账户类型
        FssAccountBindEntity bindEntity = fssAccountBindService.getBindAccountByParam(entity.getCustId().toString(), entity.getBusiType().toString());
        ConverBean bean = new ConverBean();
        //参数传入
        if ("充值".equals(flag)) {//参数待定
            bean.setOrderId(bindEntity.getAccNo());
            bean.setProdID(bindEntity.getBusiType().toString());
        }
        if ("提现".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        if ("转账".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        if ("投标".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        if ("满标".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        if ("回款".equals(flag)) {//参数待定
            bean.setCustID("");
            bean.setProdID("");
        }
        ReqContentResponse transContentResponse=conversionService.sendAndReceiveMsg(bean,false);
    }

    /**
     * 统一支付提现
     *
     * @param mchn
     * @param seq_no
     * @param trade_type
     * @param ithdrawAccountId
     * @param WithdrawCrdrFlag
     * @param CapitalAccountId
     * @param capitalCrdrFlag
     * @param postingAmount
     * @param psotingCurrency
     * @param accountType
     * @param postingType
     * @throws FssException
     */
    public void tyzfWithdrawAccounting(String mchn, String seq_no, String trade_type, String ithdrawAccountId, String WithdrawCrdrFlag, String CapitalAccountId, String capitalCrdrFlag, String postingAmount, String psotingCurrency, BigDecimal rate, String accountType, String postingType) throws FssException {
        //// TODO: 2016/11/11
    }

    /**
     * 统一支付转账
     * @param mchn
     * @param seq_no
     * @param trade_type
     * @param fromAccountId
     * @param toAccountId
     * @param amount
     * @param crdrFlag
     * @param postingCurrency
     * @param rate
     * @param accountType
     * @param postingType
     * @throws FssException
     */
    public void tyzfTransfer(String mchn, String seq_no, String trade_type, String fromAccountId, String toAccountId, BigDecimal amount, String crdrFlag, String postingCurrency, String rate, String accountType, String postingType) throws FssException {
        //// TODO: 2016/11/11
    }

}





