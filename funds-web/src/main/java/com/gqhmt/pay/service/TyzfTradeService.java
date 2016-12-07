package com.gqhmt.pay.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.service.BidService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.thread.AsyncThreadSendMq;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.bean.FssMappingBean;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.service.FssAccountBindService;
import com.gqhmt.fss.architect.account.service.FssMappingService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.tyzf.common.frame.message.MessageConvertDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Filename:    com.gqhmt.pay.service.TradeRecordService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/11/12
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/11/12  keyulai      1.0     1.0 Version
 */

@Service
public class TyzfTradeService {

    @Resource
    private FssAccountBindService fssAccountBindService;
    @Resource
    private BidService bidService;
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FssMappingService fssMappingService;
    @Resource
    private CustomerInfoService customerInfoService;


    /**
     *统一支付进行开户
     * @param tradeType 交易类型
     * @param custId 客户编号
     * @param custName 客户名称
     * @param certNo 证件号
     * @param certType 证件类型
     * @param busiNo 业务编号
     * @param seq_no 业务流水号
     * @return
     * @throws FssException
     */
    public void createTyzfAccount(String tradeType,Long custId,String custName,String certNo,String certType,String busiNo,String seq_no,String mobile) throws FssException{
        String accType= GlobalConstants.TRADE_ACCOUNT_TYPE_MAPPING.get(tradeType);//设置账户类型
        //如果,线下出借,借款,保理,则业务编号不能为空
        if("10010002".equals(accType) || "10010003".equals(accType) || "10010004".equals(accType) || "10019002".equals(accType) || "10019001".equals(accType) || "10019003".equals(accType)) {
            if(busiNo == null || "".equals(busiNo)){
                LogUtil.error(this.getClass(),"90002016:合同号不能为空");
                return;
            }
        }
        //11020001:wap开户
        //11020002:web开户
        //11020003:安卓开户
        //11020004:微信开户
        //11020005:ios开户
        //11020014:代开互联网账户
        //11020015:app开户
        //11020017:新版wap开户
        //11029100:数据迁移
        if("11020001".equals(tradeType) || "11020002".equals(tradeType) || "11020003".equals(tradeType)|| "11020004".equals(tradeType)|| "11020005".equals(tradeType) || "11020014".equals(tradeType) || "11020015".equals(tradeType) || "11020017".equals(tradeType) || "11029100".equals(tradeType)){
            this.createInternetAccount(tradeType,custId,custName,GlobalConstants.TYZF_PERSONCUST,certNo,certType,seq_no,mobile);
        }
        //委托出借开户 2
        if("11020006".equals(tradeType)){
            this.createInvstmentAccount(tradeType,custId,custName,GlobalConstants.TYZF_PERSONCUST,certNo,certType,busiNo,seq_no,mobile);
        }
        //11020007:借款人开户（冠e通）(就是开通标的账户)
        if("11020007".equals(tradeType)){
//            this.createLoanAccount(tradeType,custId,custName,GlobalConstants.TYZF_PERSONCUST,certNo,certType,busiNo,seq_no, mobile);
            this.createBidAcocunt(tradeType,custId,custName,certNo,certType,busiNo,seq_no,mobile,null);
        }
        //创建对公账户 收费账户：11020021、其他开户：11020022
        if("11020020".equals(tradeType) || "11020021".equals(tradeType) || "11020022".equals(tradeType)){
            this.createBusiAccount(tradeType,custId,custName,certNo,certType,seq_no,mobile);
        }
    }

    /**
     * 开通互联网账户
     * @param tradeType 交易类型
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param seq_no
     */
    public void createInternetAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String seq_no,String mobile) throws FssException{
        this.createAccount(tradeType,custId,custName,custType,certNo,certType,null,seq_no,3,mobile,"30130001","30010001");
    }

    /**
     * 创建线下出借账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param custType 客户主体类型 个人、企业
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @throws FssException
     */
    public void createInvstmentAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,String mobile) throws FssException{
        //判断互联账户是否开通，如没有，开通互联网账户
        this.createInternetAccount(tradeType,custId,custName,custType,certNo,certType,seq_no+"_"+1,mobile);
        //开通线下出借账户
        this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no+"_"+2,2,mobile,"30130002","30010013");
        //开通线下出借应付款账户
        this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no+"_"+3,96,mobile,"30130002","30010016");
    }

    /**
     * 创建借款人账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     */
    public void createLoanAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,String mobile) throws FssException{
        //判断互联账户是否开通，如没有，开通互联网账户
        this.createInternetAccount(tradeType,custId,custName,custType,certNo,certType,seq_no+"_"+1,mobile);
        //开通借款人信贷账户统一支付账户类型，借款账户：30010003
        this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no+"_"+2,1,mobile,"30130001","30010003");
    }

    /**
     * 创建标的账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param certNo
     * @param certType
     * @param contractNo
     * @param seq_no
     * @throws FssException
     */
    public void createBidAcocunt(String tradeType,Long custId,String custName,String certNo,String certType,String contractNo,String seq_no,String mobile,Long bid_id) throws FssException{
        if (contractNo == null || "".equals(contractNo)) {
            LogUtil.error(this.getClass(),"90002016:合同号不能为空");
            return;
        }
        Bid bid = bidService.getBidByContractNo(contractNo);
        //判断是否开通借款账户
        this.createLoanAccount(tradeType,custId,custName,GlobalConstants.TYZF_PERSONCUST,certNo,certType,contractNo,seq_no,mobile);
        //判断标的类型是否为抵押标，如果是抵押标，则开通抵押权人借款账户(loan_type=2 为抵押标)
        if(bid.getLoanType()==2){
            //获取抵押权人客户信息
            if(bid.getHypothecarius()==null) {
                LogUtil.error(this.getClass(),"90004039:未获取到抵押权人信息");
                return;
            }
            CustomerInfoEntity customerInfo = customerInfoService.getCustomerById(Long.valueOf(bid.getHypothecarius()));
            if(customerInfo==null){
                LogUtil.error(this.getClass(),"90004039:未获取到抵押权人信息");
                return;
            }
            this.createMortgageeAccount("11020009", customerInfo.getId(),customerInfo.getCustomerName(),GlobalConstants.TYZF_PERSONCUST,customerInfo.getCertNo(),String.valueOf(customerInfo.getCertType()),contractNo,seq_no, customerInfo.getMobilePhone());
        }
        this.createAccount(tradeType,bid.getId().longValue(),custName,GlobalConstants.TYZF_PERSONCUST,certNo,certType,contractNo,seq_no+"_"+3,90,mobile,"30130001","30010015");
    }

    /**
     * 创建抵押权人账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param mobile
     * @throws FssException
     */
    public void createMortgageeAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,String mobile) throws FssException{
        //抵押权人开通互联网账户
        this.createInternetAccount(tradeType,custId,custName,custType,certNo,certType,seq_no+"_"+4,mobile);
        //抵押权人开通互借款账户
        this.createAccount(tradeType,custId,custName,custType,certNo,certType,busiNo,seq_no+"_"+5,1,mobile,"30130001","30010003");
    }
    /**
     * 创建对公账户
     * @param tradeType
     * @param custId
     * @param custName
     * @param certNo
     * @param certType
     * @param seq_no
     * @throws FssException
     */
    public void createBusiAccount(String tradeType,Long custId,String custName,String certNo,String certType,String seq_no,String mobile) throws FssException{
        //开通对公账户
        Integer busi_type=null;
        String accType=null;//统一支付8位账户类型
        if("11020020".equals(tradeType)){//运营账户开户：11020020
            busi_type=70;
            accType="30010009";
        }
        if("11020021".equals(tradeType)){//收费账户开户：11020021
            busi_type=60;
            accType="30010006";
        }
        if("11020022".equals(tradeType)){//其他开户：11020022
            busi_type=50;
            accType="30010017";
        }
        this.createOnThePublicAcocunt(tradeType,custId,custName,certNo,certType,seq_no,busi_type,mobile,accType);
    }

    /**
     * 创建对公账户
     * @param tradeType 运营账户开户：11020020，收费账户开户：11020021，//其他开户：11020022
     * @param custId
     * @param custName
     * @param certNo
     * @param certType
     * @param seq_no
     * @param busi_type
     * @return
     * @throws FssException
     */
     public void createOnThePublicAcocunt(String tradeType,Long custId,String custName,String certNo,String certType,String seq_no,Integer busi_type,String mobile,String accType) throws FssException{
         this.createAccount(tradeType,custId,custName,GlobalConstants.TYZF_COMPCUST,certNo,certType,null,seq_no,busi_type,mobile,"30040002",accType);
     }

    /**
     * 开户
     * @param tradeType
     * @param custId
     * @param custName
     * @param custType
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param busi_type
     * @param mobile
     * @param chnlId
     * @param accType
     * @return
     * @throws FssException
     */
      public FssAccountBindEntity createAccount(String tradeType,Long custId,String custName,String custType,String certNo,String certType,String busiNo,String seq_no,Integer busi_type,String  mobile,String chnlId,String accType) throws FssException{
           FssAccountBindEntity entity = fssAccountBindService.createFssAccountMapping(custId,busi_type,tradeType,seq_no,busiNo,custName,mobile);
           if(entity.getSeqNo() == null){
               entity.setSeqNo(seq_no);
               fssAccountBindService.updateBindAccountSeqNo(entity.getId(),seq_no);
           }
          if("1".equals(entity.getStatus())) return entity;
           this.createAccount(tradeType,custName,certNo,certType,busiNo,entity.getSeqNo(),accType,chnlId,mobile,custType);
           return entity;
      }

    /**
     * 统一支付开户
     * @param tradeType
     * @param custName
     * @param certNo
     * @param certType
     * @param busiNo
     * @param seq_no
     * @param accType
     * @param chnlId
     * @param mobile
     * @return
     * @throws FssException
     */
    public void createAccount(String tradeType,String custName,String certNo,String certType,String busiNo,String seq_no,String accType,String chnlId,String mobile,String custType) throws FssException{
        MessageConvertDto bean = new MessageConvertDto();
        //发送报文调用统一支付开户
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setTxnType(GlobalConstants.TYZF_ACCTYPE);//交易类型
        bean.setBizTp(tradeType);//业务类型
        bean.setOrderId(seq_no==null?"":seq_no);//业务订单号
        bean.setChanId(chnlId);//线上线下类型
        bean.setCdtrNm(custName);//客户姓名
        bean.setCdtrPoFlag(custType);//个人或公司
        bean.setCdtrPoFlag(GlobalConstants.TYZF_PERSONCUST);
        bean.setCdtrIdTp(certType);//证件类型
        bean.setCdtrIdNumber(certNo);//证件号
        bean.setCdtrPoFlag(custType);//客户类型
        bean.setLoanContractNo(busiNo == null ? "" : busiNo);//借款合同号
        bean.setCdtrAcctIdtp(accType);//账户类型
        bean.setCdtrAcctCcy("30080001");//货币类型
        bean.setCdtrContactno(mobile);//手机号
        bean.setMerchId(CoreConstants.TYZF_MERCHID);//商户号
        this.sendMsg(bean);
    }

    /**
     * 充值
     * @param custId
     * @param busiType
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfRecharge(Long custId,Integer busiType,BigDecimal amount,String fundType,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.checkBindAccount(custId,busiType);
        MessageConvertDto bean = new MessageConvertDto();
        String chnlId=null;
        if("11030006".equals(tradeType) || "11030015".equals(tradeType) || "11030017".equals(tradeType)){
            chnlId="30040002";//线下
        }else {
            chnlId="30040001";
        }
        //参数传入
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setTxnType(GlobalConstants.TYZF_RECHARGE);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setChanId(chnlId);//渠道编号(WEB交易，POS交易还是APP交易等)
        bean.setCdtrAcctTp("30010001");//充值账户类型
        bean.setCdtrAcctId(bindEntity.getAccNo());//充值账户号
        bean.setDbtrAcctCcy("30080001");//货币类型
        bean.setSttlAmt(amount);//交易金额
        bean.setCardTp(GlobalConstants.TYZF_DAI);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        this.sendMsg(bean);
    }

    /**
     * 提现
     * @param custId
     * @param busiType
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfWithDraw(Long custId,Integer busiType,BigDecimal amount,Integer fundType,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.checkBindAccount(custId,busiType);
        MessageConvertDto bean = new MessageConvertDto();
        //参数传入
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setTxnType(GlobalConstants.TYZF_WITHDRAW);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setDbtrAcctId(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcctCcy("30080001");//货币类型
        bean.setSttlAmt(amount);//交易金额
        bean.setCardTp(GlobalConstants.TYZF_JIE);//个人账户记账借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        this.sendMsg(bean);
    }

    /**
     * 转账
     * @param fromCustId
     * @param fromAccountType
     * @param toCustId
     * @param toAccountType
     * @param amount
     * @param tradeType
     * @param seqNo
     * @throws FssException
     */
    public void tyzfTransfer(Long fromCustId,Integer fromAccountType,Long toCustId,Integer toAccountType,BigDecimal amount,String tradeType,String seqNo,String transf_flag) throws FssException {
        FssAccountBindEntity fromBindEntity = fssAccountBindService.getBindAccountByParam(fromCustId,fromAccountType);
        FssAccountBindEntity toBindEntity = fssAccountBindService.getBindAccountByParam(toCustId,toAccountType);
        this.tyzfTransfer(fromBindEntity,toBindEntity,amount,tradeType,seqNo,transf_flag);
    }

    /**
     * 转账重载
     * @param fromEntity
     * @param toEntity
     * @param amount
     * @param tradeType
     * @param seqNo
     * @throws FssException
     */
    public void tyzfTransfer(FssAccountBindEntity fromEntity,FssAccountBindEntity toEntity,BigDecimal amount,String tradeType,String seqNo,String transf_flag) throws FssException {
        String txnType=GlobalConstants.TYZF_TRANSFER;
        if("1".equals(transf_flag)){//冻结转账
            txnType=GlobalConstants.TYZF_FRZEN_TRANSFER;
        }
        if (fromEntity == null || toEntity == null){
            LogUtil.error(this.getClass(),"90004034:该账户未绑定");
            return;
        }
        MessageConvertDto bean = new MessageConvertDto();
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setTxnType(txnType);//交易类型
        bean.setOrderId(seqNo== null ? "" : seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setDbtrAcctId(fromEntity.getAccNo());//转出账户
        bean.setCdtrAcctId(toEntity.getAccNo());//转入账户
        bean.setSttlAmtCcy("30080001");//货币类型
        bean.setSttlAmt(amount);
        bean.setCardTp(GlobalConstants.TYZF_JIE);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);
        this.sendMsg(bean);
    }

    /**
     * 冻结
     * @param custId
     * @param busiType
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfFroze(Long custId,Integer busiType,BigDecimal amount,String fundType,String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.checkBindAccount(custId,busiType);
        MessageConvertDto bean = new MessageConvertDto();
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setTxnType(GlobalConstants.TYZF_FRZEN);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setCdtrAcctId(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcctCcy("30080001");//货币类型
        bean.setSttlAmt(amount);//交易金额
        bean.setCardTp(GlobalConstants.TYZF_JIE);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        this.sendMsg(bean);
    }

    /**
     * 解冻
     * @param custId
     * @param busiType
     * @param amount
     * @param fundType
     * @param tradeType
     * @throws FssException
     */
    public void tyzfUnFroze(Long custId,Integer busiType,BigDecimal amount,String fundType, String tradeType,String seqNo) throws FssException {
        FssAccountBindEntity bindEntity = fssAccountBindService.checkBindAccount(custId,busiType);
        MessageConvertDto bean = new MessageConvertDto();
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setTxnType(GlobalConstants.TYZF_UNFRZEN);//交易类型
        bean.setOrderId(seqNo);//业务订单号
        bean.setCdtMop("97010001");//通道编号
        bean.setCdtrAcctId(bindEntity.getAccNo());//提现账户
        bean.setDbtrAcctCcy("30080001");//货币类型
        bean.setSttlAmt(amount);//交易金额
        bean.setCardTp(GlobalConstants.TYZF_DAI);//借贷标识
        bean.setOperateType(GlobalConstants.TYZF_NORMAL_ACCOUNTING);//记账类型
        this.sendMsg(bean);
    }

     /** 投标
     * @param busiId 业务id（冠E通客户的id，标的账户标的表的id）
     * @param busiType 账户类型
     * @param amount    //实际出账金额
     * @param boundsAmount  //红包金额
     * @param tradeType //交易类型
     * @param bidId     //标的Id
     * @param seqNo     //流水号
     * @throws FssException
     */
    public void tender(Long busiId,Integer busiType,BigDecimal amount,BigDecimal boundsAmount,String tradeType,String bidId,String seqNo) throws FssException {
        this.tyzfTransfer(busiId,busiType,Long.valueOf(bidId),90,amount,tradeType,seqNo,"0");
        //红包账户
        if (boundsAmount.compareTo(BigDecimal.ZERO) > 0) {
            FundAccountEntity fromEntity=null;
            //获取所有运营商的红包账户，（通过custId关联红包账户表查询）
            List<FssMappingBean> mappinglist=fssMappingService.getMappingListByType("10010006");
            if(mappinglist.size()>0){
                for(FssMappingBean  entity:mappinglist){
                    if (entity.getAmount().compareTo(boundsAmount)>=0){//账户余额大于红包金额，则从该账户扣除红包金额
                        fromEntity=fundAccountService.getFundAccountById(entity.getAccountId());
                        break;
                    }
                }
            }
            this.tyzfTransfer(fromEntity.getCustId(),fromEntity.getBusiType(),Long.valueOf(bidId),90,boundsAmount,tradeType,seqNo,"0");
        }
    }

    /**
     * jhz
     * 满标
     * @param toAccEntity
     * @param amount
     * @param tradeType
     * @param bidId
     * @param seqNo
     * @throws FssException
     */
    public void fullStandard(String bidId,FundAccountEntity toAccEntity,BigDecimal amount,
                             String tradeType,String seqNo) throws FssException {
        //// TODO: 2016/11/14
        this.tyzfTransfer(Long.valueOf(bidId),90,toAccEntity.getCustId(),toAccEntity.getBusiType(),amount,tradeType,seqNo,"0");
    }

    /**
     * jhz
     * 回款
     * @param fromCustId
     * @param fromAccountType
     * @param toCustId
     * @param toAccountType
     * @param amount
     * @param tradeType
     * @param seqNo
     * @throws FssException
     */
    public void backSection(Long fromCustId,Integer fromAccountType,Long toCustId,Integer toAccountType,BigDecimal amount,String tradeType,String seqNo) throws FssException {
    //// TODO: 2016/11/14

    }

    /**
     * 异步回调结果通知
     * @param bm
     * @throws FssException
     */
    public void asyncallBack(MessageConvertDto bm) throws FssException{//业务流水号
        if(GlobalConstants.TYZF_ACCTYPE.equals(bm.getTxnType())){//开户
             if("0000".equals(bm.getRespCode())){//开户成功
                 String seq_no=bm.getOrderId();
                 String accNO=bm.getCdtrAcctId();//统一支付返回的账号
                 FssAccountBindEntity entity = fssAccountBindService.getBindAccountBySeqNo(seq_no);
                 fssAccountBindService.updateBindAccount(entity.getId(),"1",accNO);
            }
        }
        if(GlobalConstants.TYZF_RECHARGE.equals(bm.getTxnType())){//充值
            if (!"0000".equals(bm.getRespCode())) {
                LogUtil.error(this.getClass(),bm.getRespCode());
                return;
            }
        }
        if(GlobalConstants.TYZF_WITHDRAW.equals(bm.getTxnType())){//提现
            if (!"0000".equals(bm.getRespCode())) {
                LogUtil.error(this.getClass(),bm.getRespCode());
                return;
            }
        }
        if(GlobalConstants.TYZF_TRANSFER.equals(bm.getTxnType())){//转账
            if (!"0000".equals(bm.getRespCode())) {
                LogUtil.error(this.getClass(),bm.getRespCode());
                return;
            }
        }
        if(GlobalConstants.TYZF_FRZEN.equals(bm.getTxnType())){//冻结
            if (!"0000".equals(bm.getRespCode())) {
                LogUtil.error(this.getClass(),bm.getRespCode());
                return;
            }
        }
        if(GlobalConstants.TYZF_UNFRZEN.equals(bm.getTxnType())){//解冻
            if (!"0000".equals(bm.getRespCode())) {
                LogUtil.error(this.getClass(),bm.getRespCode());
                return;
            }
        }
    }

    /**
     * 账户注销
     * @throws FssException
     */
    public void logOutAccount(String trade_type,String cust_no,String cust_name,String cert_no,String seq_no,String mobile_phone) throws FssException{
        MessageConvertDto bean = new MessageConvertDto();
        //发送报文调用统一支付开户
        bean.setServiceId("0001");
        bean.setIsActual("N");//是否同步交易
        bean.setIsBatch("N");//是否批量
        bean.setTxnType(GlobalConstants.TYZF_LOGOUT_ACCOUNT);//交易类型
        bean.setBizTp(trade_type);//业务类型
        bean.setOrderId(seq_no==null?"":seq_no);//业务订单号
        bean.setCdtrNm(cust_name);//客户姓名
        bean.setCdtrPoFlag(GlobalConstants.TYZF_PERSONCUST);
        bean.setCdtrIdTp("1");//证件类型
        bean.setCdtrIdNumber(cert_no);//证件号
        bean.setCdtrAcctCcy("30080001");//货币类型
        bean.setCdtrContactno(mobile_phone);//手机号
        bean.setMerchId(CoreConstants.TYZF_MERCHID);//商户号
        this.sendMsg(bean);

    }


    private void sendMsg(MessageConvertDto bean ) {
//        try {
//            conversionService.sendAndReceiveMsg(bean);
//        } catch (Exception e) {
//            LogUtil.error(this.getClass(),e.getMessage(),e);
//            throw new FssException("91002005");
//        }
        try {
            AsyncThreadSendMq.getInstance().sendMqMsg(bean);
        } catch (InterruptedException e) {
            LogUtil.info(this.getClass(),"消息进入队列失败");
        } catch (FssException e) {
            LogUtil.info(this.getClass(),e.getMessage());
        }
    }
}
