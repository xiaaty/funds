package com.gqhmt.pay.service.trade;


import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.asset.FundTradeDto;
import com.gqhmt.extServInter.dto.trade.*;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.NoticeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.bean.FundTradeBean;
import java.math.BigDecimal;
import java.util.List;

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
 *         <li>web充值提现订单生成</li>
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
     * 生成web提现订单
     * @return
     * @throws FssException
     */
    public String webWithdrawOrder(WithdrawOrderDto withdrawOrderDto) throws FssException;
    /**
     * 生成web充值订单
     * @return
     * @throws FssException
     */
    public String webRechargeOrder(RechargeOrderDto rechargeOrderDto) throws FssException;


    /**
     * 线上代扣充值
     * @param withholdDto
     * @return
     */
    public boolean withholding(WithholdDto withholdDto)throws FssException;

    /**
     *线上提现，目前已直连富友代付接口，未来改为异步，存入数据库，定时跑批提现
     * @param withdrawDto
     * @return
     * @throws FssException
     */
    public boolean withdraw(WithdrawDto withdrawDto) throws FssException;

    /**
     * 代扣申请
     * @return
     * @throws FssException
     */
    public boolean withholdingApply(RechargeApplyDto rechargeApplyDto) throws FssException;

    public FundOrderEntity withholdingApplyNew(int custID, int businessType, String contractNo, BigDecimal amount, Long busiId) throws FssException ;

    public FundOrderEntity withholdingApplyNew(String accNo, String contractNo, BigDecimal amount, Long busiId) throws FssException ;

//    public FundOrderEntity withholdingApplyNew(Long custId, String contractNo, BigDecimal amount, Long busiId) throws FssException ;
    /**
     *
     * 代付申请
     * @return
     * @throws FssException
     */
    public boolean withdrawApply(WithdrawApplyDto withdrawApplyDto) throws FssException;

    public FundOrderEntity withdrawApplyNew(String accNo,String custId,Integer businessType, String contractNo, BigDecimal amount,Long busiId,int selletType) throws FssException;

//    public FundOrderEntity withdrawApplyNew(int custID, int businessType, String contractNo, BigDecimal amount,Long busiId,int selletType) throws FssException;

    /**
     * 转账接口
     * @param from_cust_no
     * @param from_user_no
     * @param from_cust_type
     * @param to_cust_no
     * @param to_user_no
     * @param to_cust_type
     * @param amt
     * @param funds_type
     * @param busi_type
     * @param busi_id
     * @return
     * @throws FssException
     */
    public boolean transfer(Integer from_cust_no,Integer from_user_no,Integer from_cust_type,Integer to_cust_no,Integer to_user_no,Integer to_cust_type,BigDecimal amt,Integer  funds_type,Integer  busi_type,Long  busi_id) throws FssException;


    /**
     * 转账接口,老账户提现 预留接口,新版账户提现,不设此接口
     * @param thirdPartyType
     * @param fromCusID
     * @param fromType
     * @param toCusID
     * @param toType
     * @param amount
     * @param orderType
     * @param busiId
     * @param busiType
     * @return
     * @throws FssException
     */
    public FundOrderEntity transefer(Integer fromCusID,Integer  fromType, Integer  toCusID,Integer toType,BigDecimal amount,Integer orderType,Long busiId,int busiType) throws FssException;


    public FundOrderEntity transefer(String fromAccNo,String toAccno,BigDecimal amount,Integer orderType,Long busiId,int busiType) throws FssException;


    /**
     *
     * author:jhz
     * time:2016年2月27日
     * function：线下代扣充值
     * @param custID                    客户id
     * @param businessType              业务类型1，出借赎回代付，2借款放款代付；3借款其他资金代付；4抵押权人资金代付；5代偿人资金代付；99，其他代付
     * @param contractNo
     * @param amount
     * @return
     * @throws FssException
     */
    boolean withholdingApply(int custID, int businessType, String contractNo, BigDecimal amount,
                             Long busiId) throws FssException;
    /**
     *
     * author:jhz
     * time:2016年2月27日
     * function：线下提现代付
     * @param custID                    客户id
     * @param businessType              业务类型1，出借赎回代付，2借款放款代付；3借款其他资金代付；4抵押权人资金代付；5代偿人资金代付；99，其他代付
     * @param contractNo
     * @param amount
     * @return
     * @throws FssException
     */
    boolean withdrawApply(int custID, int businessType, String contractNo, BigDecimal amount,
                          Long busiId,int selletType) throws FssException;

    /**
     * 资金冻结
     * @param custId
     * @param busiType
     * @param amt
     * @return
     * @throws FssException
     */
    public boolean froze(Long custId,Integer busiType,BigDecimal amt) throws FssException;

    /**
     * 资金解冻
     * @param mchn
     * @param seq_no
     * @param trade_type
     * @param cust_no
     * @param user_no
     * @param amt
     * @param busi_type
     * @return
     * @throws FssException
     */
    public boolean unFroze(String mchn,String seq_no,String trade_type,String cust_no,String user_no,BigDecimal amt,Integer busi_type) throws FssException;

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
     * @throws FssException
     */
    //public boolean cost (String thirdPartyType,int custID,int businessType,int costType,int costMode,String contractNo,BigDecimal amount,Long bid) throws FssException;


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
     * @throws FssException
     */
    //public boolean compensateByLend(String thirdPartyType,int custID,int mode,String contractNo,BigDecimal amount,Long bid) throws FssException;


    /**
     * 借款端逾期代偿
     * @param thirdPartyType            支付渠道
     * @param custID                    客户id
     * @param costMode                  代偿模式：0，逾期代偿；1逾期还款代偿退回
     * @param contractNo
     * @param amount
     * @param bid
     * @return
     * @throws FssException
     */
    //public boolean compensateByLoad(String thirdPartyType,int custID,int costMode,String contractNo,BigDecimal amount,Long bid) throws FssException;

    /**
     * 查询交易记录
     * @param tradrecord
     * @return
     */
    public List<FundTradeBean> queryFundTrade(FundTradeDto tradrecord) throws FssException;

    /**
     *
     * author:jhz
     * time:2016年2月27日
     * function：充值成功入账
     */
    public void recharge(RechargeSuccessDto rechargeSuccessDto) throws FssException;
    /**
     *
     * author:jhz
     * time:2016年2月27日
     * function：提现成功入账
     */
    public void withdraw(WithdrawSuccessDto withdrawSuccessDto) throws FssException;

    /**
     * 充值提现金额变动通知
     *
     * @param noticeType
     * @param entity
     * @param amount
     */
    public void sendNotice(String tempCode, NoticeService.NoticeType noticeType, FundAccountEntity entity, BigDecimal amount, BigDecimal chargeAmount) ;
    /**
     * 线下充值
     * @param dto
     * @param mchn
     * @param seq_no
     * @param trade_type
     * @param cust_id
     * @param cust_type
     * @param busi_no
     * @param amt
     * @return
     * @throws FssException
     */
    public OfflineRechargeResponse OfflineRechargeApply(String mchn,String seq_no,String trade_type,String cust_id,String cust_type,String busi_no,BigDecimal amt) throws FssException;

    /**
     * 债权转让
     * @param mchn
     * @param seq_no
     * @param trade_type
     * @param bid_id
     * @param busi_bid_no
     * @param tender_no
     * @param cust_no
     * @param busi_no
     * @param amt
     * @param o_tender_no
     * @param o_cust_no
     * @param o_busi_no
     * @return
     * @throws FssException
     */
    public boolean bondTransfer(String mchn,String seq_no,String trade_type,String bid_id,String busi_bid_no,String tender_no,String cust_no,String busi_no,BigDecimal amt,String o_tender_no,String o_cust_no,String o_busi_no,Integer acc_type,Integer to_acc_type) throws FssException;
}