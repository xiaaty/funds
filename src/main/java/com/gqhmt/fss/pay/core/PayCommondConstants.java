package com.gqhmt.fss.pay.core;

/**
 * Filename:    com.gqhmt.fss.pay.core.PayCommondConstants
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/26 16:22
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/26  于泳      1.0     1.0 Version
 */
public class PayCommondConstants {

    //指令配置  01 开头,表示 账户相关指令
    public static final String COMMAND_ACCOUNT_PRIVATE_CREATE  = "0100";            //创建账户
    public static final String COMMAND_ACCOUNT_PRIVATE_CREATE_BATCH  = "0101";      //批量创建账户
    public static final String COMMAND_ACCOUNT_PUBLIC_CREATE  = "0102";             //创建对公账户
    public static final String COMMAND_ACCOUNT_PUBLIC_CREATE_BATCH  = "0103";      //批量创建对公账户

    public static final String COMMAND_ACCOUNT_DROP_ACCOUNT_APPLY  = "0110";//申请销户
    public static final String COMMAND_ACCOUNT_DROP_ACCOUNT_PASS   = "0111"; //通过销户
    public static final String COMMAND_ACCOUNT_DROP_ACCOUNT_FAIL   = "0112";  //退回销户

    public static final String COMMAND_ACCOUNT_DROP_FUIOU_ACCOUNT_APPLY  = "0113";//申请第三方销户
    public static final String COMMAND_ACCOUNT_DROP_FUIOU_ACCOUNT_PASS   = "0114";//第三方销户确认

    public static final String COMMAND_ACCOUNT_FREEAE   = "0120";                     //账户冻结
    public static final String COMMAND_ACCOUNT_UNFREEZE = "0121";                   //账户解冻

    public static final String COMMAND_ACCOUNT_FUIOU_CARD = "0130";             //富友银行卡变更
    public static final String COMMAND_ACCOUNT_FUIOU_QUERYCARD = "0131";        //富友银行卡变更
    public static final String COMMAND_ACCOUNT_FUIOU_MMS = "0132";              //富友短信配置
    public static final String COMMAND_ACCOUNT_FUIOU_CASHWITHSET = "0133";      //提现时效





    /**
     * 异步回调处理
     */
//    FUNDS_ASYN_VALID,
    /**
     * 异步回调处理
     */
//    FUNDS_ASYN_VALID_NOT_ORDER,


    /**
     * 收取提现手续费
     */
//    FUNDS_WITHDRAW_CHARGE_AMOUNT,


//    FUNDS_RETRUN_WITHDRAW;

    //指令配置 02开头,表示 资金交易指令

    public static final String COMMAND_TRADE_CREATE_RECHARGE_ORDER = "0200";            // 仅生成 充值订单
    public static final String COMMAND_TRADE_CREATE_WITHDRAW_ORDER = "0201";            // 仅生成 提现订单
    public static final String COMMAND_TRADE_WITHHOLDING = "0202";                      // 代扣
    public static final String COMMAND_TRADE_AGENT_WITHDRAW = "0203";                   // 代付
    public static final String COMMAND_TRADE_RECHARGE_APPLY = "0204";                   //代扣申请
    public static final String COMMAND_TRADE_WITHDRAW_APPLY = "0205";                   //代付申请
    public static final String command_TRADE_FREEZE = "0206";                           //资金冻结
    public static final String COMMAND_TRADE_UNFREEZE = "0207";                         //资金解冻
    public static final String COMMAND_TRADE_TRANSFER = "0208";                         //转账
    public static final String COMMAND_TRADE_FREEZE_TRANSFER = "0209";                  //冻结转账
    public static final String COMMAND_TRADE_THIRD_FREEZE = "0210";                     //第三方资金冻结
    public static final String COMMAND_TRADE_THIRD_UNFREEZE = "0211";                   //第三方资金解冻
    public static final String COMMAND_TRADE_WITHDRAW_REFUND = "0212";                  //提现退票
    public static final String COMMAND_TRADE_RECHARGE_REFUND = "0213";                  //充值退票


    //指令配置  03开头 表示 出借投资指令

    public static final String COMMAND_INVEST_BID = "0300";                             //投标
    public static final String COMMAND_INVEST_BID_FAIL = "0301";                        //投标异常撤销
    public static final String COMMAND_INVEST_BID_CANCLE = "0302";                      //投标,主动申请撤销

    public static final String COMMAND_INVEST_SETTLE_ASYNC = "0303";                    //满标,异步
    public static final String COMMAND_INVEST_SETTLE_SYNC = "0304";                     //满标,实时

    public static final String COMMAND_INVEST_REPAYMENT_ASYNC = "0305";                 //还款,异步
    public static final String COMMAND_INVEST_REPAYMENT_SYNC = "0306";                 //还款,实时

    public static final String COMMAND_INVEST_ABORT_ASYNC = "0307";                    //流标,异步
    public static final String COMMAND_INVEST_ABORT_SYNC = "0308";                    //流标,异步

    //债权转让

    public static final String COMMAND_INVEST_DEBT_BID = "0330";                        //购买债权
    public static final String COMMAND_INVEST_DEBT_BID_FAIL = "0331";                   //购买债权异常撤销
    public static final String COMMAND_INVEST_DEBT_BID_CANCLE = "0332";                 //购买债权,主动申请撤销
    public static final String COMMAND_INVEST_DEBT_SETTLE_ASYNC = "0333";               //购买债权转账,异步
    public static final String COMMAND_INVEST_DEBT_SETTLE_SYNC = "0334";                //购买债权转账,实时


    //指令配置  08开头,表示 费用收取指令 代偿等指令
    public static final String COMMAND_CHARGE_WITHDRAW = "0801";           //收取提现手续费;

    public static final String COMMAND_CHARGE = "0802";                    //费用收取

    //指令配置  09开头 调试 账户查询指令
}
