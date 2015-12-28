package com.gqhmt.fss.logicService.pay;


import com.gqhmt.fss.logicService.pay.exception.FundsException;

import java.math.BigDecimal;

/**
 * Filename:    com.gq.funds.interaction.IFundsTrade
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/18 下午4:55
 * Description:
 * <p>
 *     <ol>
 *         <li>web充值订单生成</li>
 *         <li>web提现订单生成</li>
 *         <li>代扣充值--线上客户</li>
 *         <li>代扣提现--线上客户</li>
 *         <li>代扣申请</li>
 *         <li>代付申请</li>
 *         <li>费用收取</li>
 *         <li>出借端补差额</li>
 *         <li>借款端代偿</li>
 *         <li>账户余额</li>
 *         <li>资金冻结</li>
 *         <li>资金解冻</li>
 *     </ol>
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/18  于泳      1.0     1.0 Version
 */
public interface IFundsTrade {

    /**
     * 生成web充值提现订单
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    交易金额
     * @param chargeAmount              交易手续费
     * @param type                      交易类型 1.充值；2.提现
     * @return
     * @throws FundsException
     */
    public FundsResponse webOrderNo(String thirdPartyType,int custID,BigDecimal amount,BigDecimal chargeAmount,int type) throws FundsException;


    /**
     * 线上代扣充值
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    充值金额
     * @param sourceType                充值来源  1，web端，2wap端，3手机app
     * @return
     */
    public FundsResponse withholding(String thirdPartyType,int custID,BigDecimal amount,int sourceType);

    /**
     *线上提现，目前已直连富友代付接口，未来改为异步，存入数据库，定时跑批提现
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param amount                    提现金额
     * @param chargeAmount              手续费
     * @param sourceType                来源1，web端，2wap端，3手机app
     * @return
     * @throws FundsException
     */
    public FundsResponse withdraw(String thirdPartyType,int custID,BigDecimal amount,BigDecimal chargeAmount,int sourceType) throws FundsException;

    /**
     * 代扣申请
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param businessType              业务类型，1出借客户投资代扣；2借款客户还款代扣；3抵押权人代扣；4代偿人代扣；99其他代扣
     * @param contractNo                业务合同，出借和借款合同号，出借客户必须提供出借合同号，借款还款，必须提供借款合同号，需保证每种类型合同号唯一
     * @param amount                    代扣金额
     * @return
     * @throws FundsException
     */
    public FundsResponse withholdingApply(String thirdPartyType,int custID,int businessType,String contractNo,BigDecimal amount,Long bid) throws FundsException;

    /**
     *
     * 代付申请
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param businessType              业务类型1，出借赎回代付，2借款放款代付；3借款其他资金代付；4抵押权人资金代付；5代偿人资金代付；99，其他代付
     * @param contractNo
     * @param amount
     * @return
     * @throws FundsException
     */
    public FundsResponse withdrawApply(String thirdPartyType,int custID,int businessType,String contractNo,BigDecimal amount,Long bid) throws FundsException;


    /**
     * 费用接口
     * @param thirdPartyType           支付渠道
     * @param custID                   客户id
     * @param businessType             业务类型，1，线下出借，2借款，3债权转让
     * @param costType                 费用类型
     * @param costMode                 费用方式，0收取，1退回
     * @param contractNo
     * @param amount
     * @param bid
     * @return
     * @throws FundsException
     */
    public FundsResponse cost (String thirdPartyType,int custID,int businessType,int costType,int costMode,String contractNo,BigDecimal amount,Long bid) throws FundsException;


    /**
     *
     * 出借端补差额
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param mode                      补偿模式0，低息补偿；1高息收回
     * @param contractNo                合同编号
     * @param amount                    补偿金额
     * @param bid
     * @return
     * @throws FundsException
     */
    public FundsResponse compensateByLend(String thirdPartyType,int custID,int mode,String contractNo,BigDecimal amount,Long bid) throws FundsException;


    /**
     * 借款端逾期代偿
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param costMode                  代偿模式：0，逾期代偿；1逾期还款代偿退回
     * @param contractNo
     * @param amount
     * @param bid
     * @return
     * @throws FundsException
     */
    public FundsResponse compensateByLoad(String thirdPartyType,int custID,int costMode,String contractNo,BigDecimal amount,Long bid) throws FundsException;




}
