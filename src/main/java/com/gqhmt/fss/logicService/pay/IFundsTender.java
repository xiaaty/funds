package com.gqhmt.fss.logicService.pay;/**
 * Created by yuyonf on 15/11/19.
 */


import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.BidRepayment;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.fss.logicService.pay.exception.FundsException;

/**
 * Filename:    com.gq.funds.interaction.IFundsTender
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/19 下午6:13
 * Description:
 * <p>
 *     <ol>
 *         <li>发标通知</li>
 *         <li>投标</li>
 *         <li>满标</li>
 *         <li>流标</li>
 *         <li>还款</li>
 *         <li>债权转让</li>
 *         <li>债权转让确认转账</li>
 *     </ol>
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/19  于泳      1.0     1.0 Version
 */
public interface IFundsTender {

    /**
     * 标的通知接口，传递标的信息
     * @param thirdPartyType                支付渠道
     * @param bid                           标的实体bean
     * @return
     * @throws FundsException
     */


    public FundsResponse notice(String thirdPartyType,Bid bid) throws FundsException;

    /**
     *  标的通知
     * @param thirdPartyType                支付渠道
     * @param id                            标的表id
     * @return
     * @throws FundsException
     */
    public FundsResponse notice(String thirdPartyType,Long id) throws FundsException;

    /**
     * 投标，直接传递投标信息，标的信息
     * @param thirdPartyType                支付渠道
     * @param tender                        投标实体bean
     * @param bid                           标的实体bean
     * @return
     * @throws FundsException
     */
    public FundsResponse bid(String thirdPartyType, Tender tender, Bid bid) throws FundsException;

    /**
     * 投标，根据tender获取标的信息
     * @param thirdPartyType                支付渠道
     * @param tender
     * @return
     * @throws FundsException
     */
    public FundsResponse bid(String thirdPartyType,Tender tender) throws FundsException;


    /**
     * 满标清算，根据Bid获取投标信息，并进行满标结算
     * @param thirdPartyType                支付渠道
     * @param bid
     * @return
     * @throws FundsException
     */
    public FundsResponse sellte(String thirdPartyType,Bid bid) throws FundsException;

    /**
     * 还款
     * @param thirdPartyType                支付渠道
     * @param bid                           标id
     * @param bidRepayment                  还款实体
     * @return
     * @throws FundsException
     */
    public FundsResponse repayment(String thirdPartyType,Bid bid,BidRepayment bidRepayment) throws FundsException;


    /**
     * 投标撤销
     * @param thirdPartyType
     * @param tender
     * @return
     * @throws FundsException
     */
    public FundsResponse abort(String thirdPartyType,Tender tender) throws FundsException;


    /**
     * 标的流标
     * @param thirdPartyType
     * @param bid
     * @return
     * @throws FundsException
     */
    public FundsResponse abort(String String,Bid bid) throws FundsException;


//    public FundsResponse debtApply(String thirdPartyType,int custId);

}
