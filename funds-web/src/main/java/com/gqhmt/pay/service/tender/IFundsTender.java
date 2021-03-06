package com.gqhmt.pay.service.tender;


import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.tender.BidDto;

import java.math.BigDecimal;

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
     * @param bid_id
     * @param tender_no
     * @param product_title
     * @param cust_no
     * @param invest_type
     * @param real_Amount
     * @param loan_cust_id
     * @param moto_cust_id
     * @param cust_no
     * @param bonus_Amount
     * @return
     * @throws FssException
     */
    public boolean bid(String  tradeType ,String bid_id,String tender_no,String product_title,String cust_no,int invest_type,BigDecimal real_Amount,String  loan_cust_id,String  moto_cust_id,BigDecimal bonus_Amount,String busi_bid_no,String busi_no,String seqNo) throws FssException;
    /**
     * 新手标投标，根据tender获取标的信息
     * @param bid_id
     * @param tender_no
     * @param product_title
     * @param cust_no
     * @param invest_type
     * @param real_Amount
     * @param loan_cust_id
     * @param moto_cust_id
     * @param cust_no
     * @param bonus_Amount
     * @return
     * @throws FssException
     */
    public boolean newHandBid(String  tradeType ,String bid_id,String tender_no,String product_title,String cust_no,int invest_type,BigDecimal real_Amount,String  loan_cust_id,String  moto_cust_id,BigDecimal bonus_Amount,String busi_bid_no,String busi_no,String seqNo) throws FssException;


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


    public void abortLoop(Bid bid, Tender tender, String contractNo,String seqNo) throws FssException;

}
