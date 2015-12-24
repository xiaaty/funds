package com.gqhmt.fss.architect.account.command;

import com.gqhmt.fss.architect.account.command.CommandEnum.TenderCommand;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.util.ThirdPartyType;

/**
 * Filename:    com.gq.thirdPartyPay.core.command
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/17 20:37
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/17  于泳      1.0     1.0 Version
 */
public class AccountCommand implements Command{


    public static final AccountCommand payCommand = new AccountCommand();
//    private static final FundAccountCommand fundAccountCommand = new FundAccountCommand();
//    private static final FundSequenceCommand fundSequenceCommand = new FundSequenceCommand();
//    private static final TenderCommand tenderCommand = new TenderCommand();
//    private static final PwdCommand pwdCommand = new PwdCommand();
//    private static final CardCommand cardCommand = new CardCommand();
    private AccountCommand(){}

    /**
     * ACCOUNT_CREATE   创建账户      参数：客户id int，用户id int
     * FUNDS_ADD        充值          参数：客户id int，账户类型 int   银行卡cardinfo实体List传送   充值金额 BigDecimal
     * FUNDS_WITHDRAW   提现          参数：客户id int，账户类型 int   银行卡cardinfo实体             提现金额 BigDecimal
     * TENDER_BID       投标          参数  投资Tender  ，
     * TENDER_SETTLE    满标清算      参数  标BID
     * TENDER_REPAY     还款          参数  标BID ，还款客户List  repaymentBean
     * TENDER_REPAY     流标          参数  标BID
     * FUNDS_TRANSFER   转账          参数  转出客户id， 转出客户类型，转入客户Bean list
     * TENDER_NOTIFY    标的通知      参数  标的信息   NotifyNewBidBean.OperatingMode 标的操作方式 NotifyNewBidBean.BidType 标的类型  NotifyNewBidBean.BidState 标的状态
     * @param commandEnum
     * @param objects
     */
    @Override
    public AccountCommandResponse command(CommandEnum commandEnum,ThirdPartyType thirdPartyType, Object... objects) throws CommandParmException {
        thirdPartyType = ThirdPartyType.FUIOU;
        Command command = null;
//        if(commandEnum instanceof CommandEnum.AccountCommand){
//            command  = fundAccountCommand;          //.command(commandEnum,objects);
//        }else if(commandEnum instanceof CommandEnum.FundsCommand){
//            command  = fundSequenceCommand;         //.command(commandEnum,objects);
//        }else if(commandEnum instanceof CommandEnum.TenderCommand){
//            command  = tenderCommand;               //.command(commandEnum,objects);
//        }else if(commandEnum instanceof CommandEnum.PWD){
//            command  = pwdCommand;                  //.command(commandEnum,objects);
//        }else if(commandEnum instanceof  CommandEnum.CARD){
//            command  = cardCommand;                 //.command(commandEnum,objects);
//        }
//        transactionManager.co;
        return command.command(commandEnum,thirdPartyType,objects);
    }


}
