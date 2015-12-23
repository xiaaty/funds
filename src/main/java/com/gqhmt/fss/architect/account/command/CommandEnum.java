package com.gqhmt.fss.architect.account.command;

/**
 * Filename:    com.gq.thirdPartyPay.core.command
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/17 21:33
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/17  于泳      1.0     1.0 Version
 */
public interface CommandEnum {
    enum AccountCommand implements CommandEnum{
        /**
         * 创建账户
         */
        ACCOUNT_CREATE,         //创建账户

        /**
         * 批量创建账户
         */
        ACCOUNT_BATCH_CREATE,       //批量创建账户
        /**
         * 创建对公账户
         */
        ACCOUNT_BUSINESS_CREATE,         //创建账户
        /**
         * 冻结账户
         */
        ACCOUNT_FREEZE,         //冻结账户
        /**
         * 解冻账户
         */
        ACCOUNT_UNFREEZE  ,      //解冻账户

        ACCOUNT_UPDATE_CARD,     //更换银行卡

        ACCOUNT_UPDATE_CARD_QUERY,//变更银行卡查询

        ACCOUNT_SET_MMS,    //设置短信
        ACCOUNT_DROP_USER    //账户注销


    }

    enum FundsCommand implements CommandEnum{
        /**
         * 充值
         */
        FUNDS_CHARGE,
        /**
         * 对公帐户充值
         */
        FUNDS_BUSINESS_CHARGE,

        /**
         * 提现
         */
        FUNDS_WITHDRAW,
        /**
         * 提现
         */
        FUNDS_WITHDRAW_ACTUAL_TIME,
        /**
         * 代付
         */

        FUNDS_AGENT_WITHDRAW,
        /**
         * 对公账户提现
         */
        FUNDS_BUSINESS_WITHDRAW,

        /**
         * 提现申请
         */
        FUNDS_WITHDRAW_APPLY,
        /**
         * 提现失败
         */
        FUNDS_WITHDRAW_CANCLE,
        /**
         * 冻结资金
         */
        FUNDS_FREEZE,
        /**
         * 解冻资金
         */
        FUNDS_UNFREEZE,

        /**
         * 转账
         */
        FUNDS_TRANSFER,
        /**
         * 债权转让
         */
        FUNDS_DEBT,
        /**
         * 代扣
         */
        FUNDS_WITHHOLDING,
        /**
         * 账户余额
         */
        FUNDS_BALANCE,
        /**
         * 主账户余额
         */
        FUNDS_PRIMARY_BALANCE,
        /**
         * 第三方账户余额
         */
        FUNDS_THIRD_BALANCE,
        /**
         * 第三方账户流水s
         */
        FUNDS_THIRD_WATER,

        /**
         * 短信校验码
         */
        FUNDS_SMS_VALID,
        /**
         * 异步回调处理
         */
        FUNDS_ASYN_VALID,
        /**
         * 异步回调处理
         */
        FUNDS_ASYN_VALID_NOT_ORDER,
        FUNDS_FREEZE_THIRD,

        FUNDS_UNFREEZE_THIRD,

        FUNDS_SETTLE_FREEZE,
        /**
         * 收取提现手续费
         */
        FUNDS_WITHDRAW_CHARGE_AMOUNT,

        FUNDS_CASHWITHSETREQ,

        FUNDS_RETRUN_WITHDRAW;


    }

    enum TenderCommand implements CommandEnum{
        /**
         * 投标
         */
        TENDER_BID,
        /**
         * 还款业务
         */
        TENDER_REPAY,
        /**
         * 还款后冻结
         */
        TENDER_REPAY_FORZEN,
        /**
         * 满标清算解冻
         */
        TENDER_SETTLE_UNFROZEN,
        /**
         * 满标清算
         */
        TENDER_SETTLE,
        /**
         * 流标
         */
        TENDER_ABORT,
        /**
         * 标的通知
         */
        TENDER_NOTIFY,
        /**
         * 回调
         */
        TENDER_CALLBACK,

        /**
         * 流标异步处理
         */
        TENDER_ABORT_ASYN,

        /**
         * 投标失败处理
         */
        TENDER_BID_FAILED_RETURN


    }

    enum PWD implements  CommandEnum{
        /**
         * 创建交易密码
         */
        PWD_CREATE,
        /**
         * 修改交易密码
         */
        PWD_UPDATE,
        /**
         * 下发验证码短信
         */
        PWD_SENDMMS
    }

    enum CARD implements CommandEnum{
        /**
         * 绑定银行卡
         */
        CARD_BIND,
        /**
         * 绑定单一银行卡
         */
        CARD_BIND_SINGLE,
        /**
         * 解绑银行卡
         */
        CARD_UNBIND,
        /**
         * 解绑银行卡,通过index
         */
        CARD_UNBIND_INDEX,
        /**
         * 签约代扣
         */
        CARD_SIGN_WITHHOLDING,

        /**
         * 获取绑定的银行卡列表
         */
        CARD_QUERY


    }

}
