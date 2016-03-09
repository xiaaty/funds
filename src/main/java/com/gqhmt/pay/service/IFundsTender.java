package com.gqhmt.pay.service;


import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.tender.BidDto;

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
     * @throws FssException
     */


    //public boolean notice(String thirdPartyType,Bid bid) throws FssException;

    /**
     *  标的通知
     * @param thirdPartyType                支付渠道
     * @param id                            标的表id
     * @return
     * @throws FssException
     */
    //public boolean notice(String thirdPartyType,Long id) throws FssException;




    /**
     * 投标，根据tender获取标的信息
     * @param thirdPartyType                支付渠道
     * @param tenderId
     * @return
     * @throws FssException
     */
    public boolean bid(BidDto bidDto) throws FssException;


    /**
     * 满标清算，根据Bid获取投标信息，并进行满标结算
     * @param thirdPartyType                支付渠道
     * @param bid
     * @return
     * @throws FssException
     */
    public boolean sellte(String thirdPartyType,long bid) throws FssException;

    /**
     * 还款
     * @param thirdPartyType                支付渠道
     * @param bid                           标id
     * @param bidRepaymentId                还款表id
     * @return
     * @throws FssException
     */
    public boolean repayment(String thirdPartyType,long bid,long bidRepaymentId) throws FssException;


    /**
     * 投标撤销
     * @param thirdPartyType
     * @param tenderId
     * @return
     * @throws FssException
     */
    public boolean abortByTneder(String thirdPartyType,long tenderId) throws FssException;


    /**
     * 标的流标
     * @param thirdPartyType
     * @param bid
     * @return
     * @throws FssException
     */
    public boolean abortByBid(String thirdPartyType,long bid) throws FssException;


//    public boolean debtApply(String thirdPartyType,int custId);

}
