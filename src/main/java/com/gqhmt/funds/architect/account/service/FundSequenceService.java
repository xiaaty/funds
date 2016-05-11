package com.gqhmt.funds.architect.account.service;

import com.github.pagehelper.Page;
import com.gqhmt.business.architect.loan.bean.RepaymentBean;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import com.gqhmt.funds.architect.account.bean.FundAccountSequenceBean;
import com.gqhmt.core.FssException;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.funds.architect.account.exception.AmountFailException;
import com.gqhmt.funds.architect.account.exception.ChargeAmountNotenoughException;
import com.gqhmt.funds.architect.account.exception.FundAccountNullException;
import com.gqhmt.funds.architect.account.mapper.read.FundSequenceReadMapper;
import com.gqhmt.funds.architect.account.mapper.write.FundSequenceWriteMapper;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.core.util.Encriptor;
import com.gqhmt.util.ThirdPartyType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Filename:    com.g
 * q.p2p.account.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 14:07
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
@Service
public class FundSequenceService {
	
    @Resource
    private FundSequenceReadMapper fundSequenceReadMapper;
    @Resource
    private FundSequenceWriteMapper fundSequenceWriteMapper;
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FundTradeService fundTradeService;

    /**
     * 查询流水
     * @param entity
     * @return
     */
    public List<FundSequenceEntity> select(FundSequenceEntity entity) {
    	return fundSequenceReadMapper.select(entity);
    }
    
    /**
     * 查询流水
     * @param id
     * @return
     */
    public FundSequenceEntity selectByPrimaryKey(Long id) {
    	return fundSequenceReadMapper.selectByPrimaryKey(id);
    }
    


    /**
     * 充值操作
     * @param entity
     * @param accountType
     * @param amount
     */
    public void charge(FundAccountEntity entity, int accountType, BigDecimal amount, ThirdPartyType thirdPartyType, FundOrderEntity orderEntity) throws FssException {
        //校验账户信息
        if(entity == null || entity.getId() == null){
            throw new FundAccountNullException();
            //此处抛出异常
        }
        //校验资金,充值金额一定大于0
        if(amount.multiply(new BigDecimal(100)).longValue()<0){
            throw new ChargeAmountNotenoughException();
        }
        FundSequenceEntity fundSequenceEntity = this.getFundSequenceEntity(entity.getId(), 1, accountType, amount, thirdPartyType, orderEntity, 0L) ;
        fundSequenceEntity.setSumary("充值");
        fundSequenceEntity.setToken(getToken(orderEntity,accountType));
        try{
            this.fundSequenceWriteMapper.insertSelective(fundSequenceEntity);
        }catch (Exception e){

        }

        this.fundTradeService.addFundTrade(entity, amount, BigDecimal.ZERO, accountType, "充值成功，充值金额 " + amount + "元");
    }

    /**
     * 提现操作
     * @param entity
     * @param accountType
     * @param amount
     * @throws AmountFailException 
     */
    public void refund(FundAccountEntity entity,int accountType,BigDecimal amount,ThirdPartyType thirdPartyType,FundOrderEntity orderEntity) throws FssException {

        if(entity.getBusiType() == 99){
            throw new FssException("出账账户错误");
        }
        //校验账户信息
        if(entity == null || entity.getId() == null){
            throw new FundAccountNullException();
            //此处抛出异常
        }
//        操作类型1充值、2提现、3转账、4冻结、5解冻
        //校验资金,提现金额不能大于账户余额 ？？此处传值，是正值还是负值呢，如果传入正值，后台需要处理为负值
        if(amount.multiply(new BigDecimal("100")).longValue()<0){
            throw new AmountFailException("传入金额不能小于0");
        }
        BigDecimal refundAmount = new BigDecimal("-"+amount.toPlainString());
        FundSequenceEntity fundSequenceEntity = this.getFundSequenceEntity(entity.getId(), 2, accountType, refundAmount, thirdPartyType, orderEntity, 0l);
        fundSequenceEntity.setSumary("提现");
        fundSequenceEntity.setToken(getToken(orderEntity,accountType));
        this.fundSequenceWriteMapper.insertSelective(fundSequenceEntity);
        this.fundTradeService.addFundTrade(entity, BigDecimal.ZERO, amount,accountType, "提现成功，提现金额 " + amount + "元");
    }

    private void refundByFroze(FundAccountEntity entity,int accountType,BigDecimal amount,ThirdPartyType thirdPartyType,FundOrderEntity orderEntity) throws FssException {
        if(entity.getBusiType() == 99){
            throw new FssException("出账账户错误");
        }
        FundAccountEntity frozeEntity = this.fundAccountService.getFundAccount(entity.getCustId(), GlobalConstants.ACCOUNT_TYPE_FREEZE);
//        操作类型1充值、2提现、3转账、4冻结、5解冻
        //校验资金,提现金额不能大于账户余额 ？？此处传值，是正值还是负值呢，如果传入正值，后台需要处理为负值
        if(amount.multiply(new BigDecimal(100)).longValue()>0){
            throw new ChargeAmountNotenoughException();
        }
        if(amount.multiply(new BigDecimal("100")).longValue()<0){
            throw new AmountFailException("传入金额不能小于0");
        }
        amount = new BigDecimal("-"+amount.toPlainString());
        FundSequenceEntity fundSequenceEntity = this.getFundSequenceEntity(frozeEntity.getId(), 2, accountType, amount, thirdPartyType, orderEntity, entity.getId());
        fundSequenceEntity.setSumary("提现");
        fundSequenceEntity.setToken(getToken(orderEntity,accountType));
        this.fundSequenceWriteMapper.insertSelective(fundSequenceEntity);
        this.fundTradeService.addFundTrade(entity,  BigDecimal.ZERO, amount,accountType, "提现成功，提现金额 " + amount + "元");
    }



    /**
     * 转账
     * @param fromEntity    转出账户
     * @param toEntiry      转入转换
     * @param amount        转账金额
     */
    public void transfer(FundAccountEntity fromEntity,FundAccountEntity toEntiry,BigDecimal amount,int actionType,int accountType,String memo,ThirdPartyType thirdPartyType,FundOrderEntity orderEntity) throws FssException {
        if(amount.multiply(new BigDecimal("100")).longValue()<0){
            throw new AmountFailException("传入金额不能小于0");
        }
        int toActionType = accountType;
        if(accountType == 1005){
            toActionType = 1006;
        }else if(accountType == 3007){
            toActionType = 3008;
        }else if(accountType == 3003){
            toActionType = 3005;
        }else if(accountType == 3004){
            toActionType = 3006;
        }else if(accountType == 2009){
            toActionType = 2010;
        }else if(accountType == 2006){
            toActionType = 3002;
        }else if(accountType == 2002){
            toActionType = 1004;
        }else if (accountType == 4001){
            toActionType = 4002;
        }else if (accountType == 4003){
            toActionType = 4004;
        }else if(accountType == 4005){
            toActionType = 1009;
        }

        FundSequenceEntity fromFundSequenceEntity =this.getFundSequenceEntity(fromEntity.getId(), actionType, accountType, new BigDecimal("-" + amount.toPlainString()), thirdPartyType, orderEntity, toEntiry.getId());
        fromFundSequenceEntity.setSumary("转账转出  转给"+toEntiry.getCustName()+"("+toEntiry.getId()+") ");

        FundSequenceEntity toFundSequenceEntity = this.getFundSequenceEntity(toEntiry.getId(), actionType, toActionType, amount, thirdPartyType, orderEntity, fromEntity.getId());
        toFundSequenceEntity.setSumary("转账转入 "+fromEntity.getCustName()+"("+fromEntity.getId()+")转入");
        List<FundSequenceEntity> list = new ArrayList<>();
        list.add(fromFundSequenceEntity);
        list.add(toFundSequenceEntity);
        this.fundSequenceWriteMapper.insertList(list);
        this.fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO,amount,  accountType, memo == null && "".equals(memo)?"转账转出":memo,BigDecimal.ZERO);
        this.fundTradeService.addFundTrade(toEntiry,amount, BigDecimal.ZERO,accountType, memo == null && "".equals(memo)?"转账转入":memo);
    }
    
    /**
     * 冻结金额
     * @param orgEntity
     * @param frozenEntiry
     * @param amount
     */
    public void frozenAmt(FundAccountEntity orgEntity,FundAccountEntity frozenEntiry,BigDecimal amount,int accountType,String memo,ThirdPartyType thirdPartyType,FundOrderEntity orderEntity,BigDecimal bounsAmount) throws FssException {
        if(orgEntity == null || orgEntity.getId() == null || frozenEntiry == null || frozenEntiry.getId() == null){
            throw new FundAccountNullException();
            //此处抛出异常
        }
        if(amount.multiply(new BigDecimal("100")).longValue()<0){
            throw new AmountFailException("传入金额不能小于0");
        }
        //冻结金额需要小于账户余额
        long money = amount.multiply(new BigDecimal("10000")).longValue();

        FundSequenceEntity orgFundSequenceEntity = this.getFundSequenceEntity(orgEntity.getId(), 4, accountType, new BigDecimal(-money).divide(new BigDecimal("10000")), thirdPartyType, orderEntity, frozenEntiry.getId());
        orgFundSequenceEntity.setSumary("冻结");
        int frozenType = 2007;
        if(accountType==1007){
            frozenType = 2007;
        }else if(accountType==3001){
            frozenType = 2004;
        }else if(accountType==1003){
            frozenType = 2001;
        }
        FundSequenceEntity frozenFundSequenceEntity = this.getFundSequenceEntity(frozenEntiry.getId(), 4, frozenType, amount, thirdPartyType, orderEntity, orgEntity.getId());
        frozenFundSequenceEntity.setSumary("冻结");
        List<FundSequenceEntity> list = new ArrayList<>();
        list.add(orgFundSequenceEntity);
        list.add(frozenFundSequenceEntity);
        this.fundSequenceWriteMapper.insertList(list);
        this.fundTradeService.addFundTrade(orgEntity, BigDecimal.ZERO, amount, accountType, memo,bounsAmount);

//        createFundTrade(fromEntity, BigDecimal.ZERO, amount, 3001, "出借" + title + "，冻结账户资金 " + amount + "元" + (boundsAmount !=null ? ",红包抵扣资金 " + boundsAmount + "元" : ""), (boundsAmount != null? boundsAmount : BigDecimal.ZERO));


    }

    /**
     * 解冻金额
     * @param orgEntity
     * @param frozenEntiry
     * @param amount
     */
    public void unfreeze(FundAccountEntity orgEntity,FundAccountEntity frozenEntiry,BigDecimal amount,int accountType,String memo,ThirdPartyType thirdPartyType,FundOrderEntity orderEntity) throws FssException {
        if(orgEntity == null || orgEntity.getId() == null || frozenEntiry == null || frozenEntiry.getId() == null){
            throw new FundAccountNullException();
            //此处抛出异常
        }
        if(amount.multiply(new BigDecimal("100")).longValue()<0){
            throw new AmountFailException("传入金额不能小于0");
        }
        //解冻金额需要小于冻结余额
        long money = amount.multiply(new BigDecimal("10000")).longValue();

        int frozenType = 2008;
        if(accountType==1008){
            frozenType = 2008;
        }else if(accountType==3002){
            frozenType = 2006;
        }else if(accountType==3009){
            frozenType = 2005;
        }else if(accountType==1004){
            frozenType = 2002;
        }
        FundSequenceEntity orgFundSequenceEntity = this.getFundSequenceEntity(orgEntity.getId(),4,accountType,new BigDecimal(-money).divide(new BigDecimal("10000")),thirdPartyType,orderEntity,frozenEntiry.getId());
        orgFundSequenceEntity.setSumary("解冻");
        FundSequenceEntity frozenFundSequenceEntity =this.getFundSequenceEntity(frozenEntiry.getId(), 4, frozenType, amount, thirdPartyType, orderEntity, orgEntity.getId());
        frozenFundSequenceEntity.setSumary("解冻");
        List<FundSequenceEntity> list = new ArrayList<>();
        list.add(orgFundSequenceEntity);
        list.add(frozenFundSequenceEntity);
        this.fundSequenceWriteMapper.insertList(list);
        this.fundTradeService.addFundTrade(orgEntity, amount, BigDecimal.ZERO, accountType, memo,BigDecimal.ZERO);
    }


    public BigDecimal getSumAmount(long id){
        return fundSequenceReadMapper.getSumAmount(id);
    }

    /**
     * 创建流水
     * @param accountID
     * @param actionType
     * @param accountType
     * @param amount
     * @param thirdPartyType
     * @param orderEntity
     * @param oAccountId
     * @return
     */
    protected FundSequenceEntity getFundSequenceEntity(Long accountID,int actionType,int accountType,BigDecimal amount,ThirdPartyType thirdPartyType,FundOrderEntity orderEntity,Long oAccountId){
        FundSequenceEntity entity = new FundSequenceEntity();
        entity.setCreateTime(new Timestamp(new Date().getTime()));
        entity.setAmount(amount);
        entity.setAccountId(accountID);
        entity.setCurrency("0001");
        entity.setFundType(accountType);
        entity.setActionType(actionType);
        entity.setThirdPartyType(thirdPartyType.getKey());
        if(orderEntity != null){
            entity.setOrderNo(orderEntity.getOrderNo());
        }
        entity.setModifyTime(new Date());
        entity.setoAccountId(oAccountId);
        return entity;
    }

    /**
     * 根据主账户及客户实体查询并返回对应的交易流水信息
     * @param accountBean
     * @return
     */
    public Page queryFundSequenceList(FundsAccountBean accountBean){
        return fundSequenceReadMapper.queryFundSequenceList(accountBean);
    }

    /**
     * 获取订单入账数量
     * @param orderNo
     * @return
     */
    public int getSizeByOrderNo(String orderNo){
        return fundSequenceReadMapper.getSizeByOrderNo(orderNo);
    }

    /**
     * 获取订单入账金额，转账 冻结 0，充值 提现 不等于0
     * @param orderNo
     * @return
     */
    public BigDecimal getSumByOrderNo(String orderNo){
        return fundSequenceReadMapper.getSumByOrderNo(orderNo);
    }


    public String getToken(FundOrderEntity fundOrderEntity,int seq){
        return Encriptor.getMD5(fundOrderEntity.getOrderNo()+String.valueOf(seq));
    }

    public int querySequence(long accountId){
        return fundSequenceReadMapper.querySequence(accountId);
    }
    /**
     * 
     * author:jhz
     * time:2016年2月17日
     * function：查询流水列表
     */
    public List<FundAccountSequenceBean> selectAccountSequenceList(Map fasMap) {
    	return fundSequenceReadMapper.selectAccountSequenceList(fasMap);
    }
    
    /**
     * 账户资金流水查询
     * @return
     */
 /*   public FundAccountSequenceBean searchTradFlow(Integer cust_no,Integer user_no,Integer busi_no) throws FssException{
    	Map map=new HashMap();
    	if(null!=cust_no){
    		map.put("cust_no", cust_no);
    	}
    	if(null!=user_no){
    		map.put("user_no", user_no);
    	}
    	return this.fundSequenceReadMapper.queryFundTradeFlow(map);
    }*/
    
   public void insertFundSequence(FundSequenceEntity fundsequence)  throws FssException{
	  this.fundSequenceWriteMapper.insert(fundsequence);
    }
   
   /**
    * 查询账户余额
    * @param entity
    * @param amount
    * @throws FssException
    */
   public void hasEnoughBanlance(FundAccountEntity entity, BigDecimal amount) throws FssException {
		BigDecimal seqamount = fundSequenceReadMapper.getSumAmount(entity.getId());
		if (seqamount.compareTo(amount) < 0) {
			throw new FssException("账户余额不足");
		}
	}
   
   /**
    * 存储流水记录
    * @param entity
    */
   public void save(FundSequenceEntity entity,Map<Integer,Long> map){
       entity.setModifyTime(new Date());
       fundSequenceWriteMapper.insert(entity);
   }
   

   public List<FundSequenceEntity> getSumByDay(){
       return fundSequenceReadMapper.getSumByDay();
   }
   
   /**
    * 账户资金流水
    * @return
    */
  public List<FundFlowBean> queryFundSequence(Integer user_no,Integer fundType) throws FssException{
	  Map map=new HashMap();
	  if(StringUtils.isNoneBlank(user_no.toString())){
		  map.put("user_no", user_no.intValue());
	  }
	  if(StringUtils.isNoneBlank(fundType.toString())){
		  map.put("fundType", fundType.intValue());
	  }
	  List<FundFlowBean> list=fundSequenceReadMapper.getFundSequence(map);
	  return list;
  }
   
  /**
   * 查询资金流水列表
   */
  public List<FundFlowBean> queryFundFlowBean(FundFlowBean fundflow){
	  List<FundFlowBean> list=fundSequenceReadMapper.selectAllFundFlow(fundflow);
	  return list;
  }


  public void selletSequence(List<Tender> list,FundAccountEntity toEntity, FundOrderEntity fundOrderEntity ,String title) throws FssException {

      BigDecimal bonusAmount = BigDecimal.ZERO;
      List<Tender> tenders = new ArrayList<>();		//返现tender集合


      for (Tender tender : list) {
          FundAccountEntity fromEntity = fundAccountService.getFundAccount(Long.valueOf(tender.getCustomerId()), GlobalConstants.ACCOUNT_TYPE_FREEZE); // service.getFundAccount(tender.getCustomerId(),99);
          try {
              this.transfer(fromEntity, toEntity, tender.getRealAmount(), 6, 2006,null, ThirdPartyType.FUIOU, fundOrderEntity);
          } catch (FssException e) {
              LogUtil.error(this.getClass(), e.getMessage());
          }

          this.fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO, BigDecimal.ZERO, 2006, "你出借的产品" + title + " 已满标，转给借款人 " + tender.getRealAmount() + "元" + (tender.getBonusAmount().intValue() > 0 ? ",红包抵扣 " + tender.getBonusAmount() + "元" : ""));

          //红包使用金额汇总2015.07.31 于泳
          if(tender.getBonusAmount() != null) {
              bonusAmount = bonusAmount.add(tender.getBonusAmount());
          }
      }

      //红包账户出账，使用红包汇总 2015.07.31 于泳
      if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
          FundAccountEntity fromEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_FREEZE);
          this.transfer(fromEntity, toEntity, bonusAmount, 6, 2006,"",ThirdPartyType.FUIOU, fundOrderEntity);
          this.fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO, bonusAmount, 4011, "产品" + title + " 已满标，红包金额转给借款人 " + bonusAmount + "元");
      }

  }

    public void abortSequence(List<Tender> list,FundAccountEntity  fromEntity , FundOrderEntity fundOrderEntity ,String title) throws FssException {

        BigDecimal bonusAmount = BigDecimal.ZERO;
        List<Tender> tenders = new ArrayList<>();		//返现tender集合


        for (Tender tender : list) {
            FundAccountEntity toEntity = fundAccountService.getFundAccount(Long.valueOf(tender.getCustomerId()), GlobalConstants.ACCOUNT_TYPE_FREEZE); // service.getFundAccount(tender.getCustomerId(),99);
            try {
                this.transfer(fromEntity, toEntity, tender.getRealAmount(), 6, 2011,null, ThirdPartyType.FUIOU, fundOrderEntity);
            } catch (FssException e) {
                LogUtil.error(this.getClass(), e.getMessage());
            }

            this.fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO, BigDecimal.ZERO, 2011, "你出借的产品" + title + " 已流标，退回资金 " + tender.getRealAmount() + "元");

            //红包使用金额汇总2015.07.31 于泳
            if(tender.getBonusAmount() != null) {
                bonusAmount = bonusAmount.add(tender.getBonusAmount());
            }
        }

        //红包账户出账，使用红包汇总 2015.07.31 于泳
        if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
            FundAccountEntity toEntity = fundAccountService.getFundAccount(4l, GlobalConstants.ACCOUNT_TYPE_FREEZE);
            this.transfer(fromEntity, toEntity, bonusAmount, 6, 2006,"",ThirdPartyType.FUIOU, fundOrderEntity);
            this.fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO, bonusAmount, 4011, "产品" + title + " 已满标，红包金额转给借款人 " + bonusAmount + "元");
        }

    }


    public void repaymentSequence(List<RepaymentBean> list,String title,FundAccountEntity fromEntity,FundOrderEntity fundOrderEntity,BigDecimal sumRepay){
        //产品名称，如果产品名称为空，则去标的title
        ThirdPartyType thirdPartyType = ThirdPartyType.FUIOU;
        for (RepaymentBean bean : list) {
            FundAccountEntity toEntity = fundAccountService.getFundAccount(Long.valueOf(bean.getCustomerId()), bean.getInvestType() == 0 ? GlobalConstants.ACCOUNT_TYPE_PRIMARY : bean.getInvestType() == 1 ? 3 : 2);

            if (bean.getCustomerId() < GlobalConstants.RESERVED_CUSTOMERID_LIMIT) {
                if (bean.getRepaymentAmount().compareTo(BigDecimal.ZERO) > 0) {
                    try {
                        this.transfer(fromEntity, toEntity, bean.getRepaymentAmount(), 7, 4001,null,thirdPartyType.FUIOU, fundOrderEntity);
                    } catch (FssException e) {
                        LogUtil.error(this.getClass(), e);
                    }
                }
            } else {
                if (bean.getRepaymentPrincipal().compareTo(BigDecimal.ZERO) > 0) {
                    try {
                        this.transfer(fromEntity, toEntity, bean.getRepaymentPrincipal(), 7, 3003,null,thirdPartyType, fundOrderEntity);
                    } catch (FssException e) {
                        LogUtil.error(this.getClass(), e);
                    }
                    try {
                        fundTradeService.addFundTrade(toEntity, bean.getRepaymentPrincipal(), BigDecimal.ZERO, 3005, title + " 收到还款本金 " + bean.getRepaymentPrincipal() + "元");
                    } catch (FssException e) {
                        LogUtil.error(this.getClass(), e);
                    }
                }
                if (bean.getRepaymentInterest().compareTo(BigDecimal.ZERO) > 0) {
                    try {
                        this.transfer(fromEntity, toEntity, bean.getRepaymentInterest(), 7, 3004,null,thirdPartyType, fundOrderEntity);
                    } catch (FssException e) {
                        LogUtil.error(this.getClass(), e);
                    }
                    try {
                        fundTradeService.addFundTrade(toEntity, bean.getRepaymentInterest(), BigDecimal.ZERO, 3006, title + " 收到还款利息 " + bean.getRepaymentInterest() + "元");
                    } catch (FssException e) {
                        LogUtil.error(this.getClass(), e);
                    }
                }
                if (bean.getRepaymentExtrinterest().compareTo(BigDecimal.ZERO) > 0) {
                    try {
                        this.transfer(fromEntity, toEntity, bean.getRepaymentExtrinterest(), 7, 3004,null,thirdPartyType, fundOrderEntity);
                    } catch (FssException e) {
                        LogUtil.error(this.getClass(), e);
                    }
                    try {
                        fundTradeService.addFundTrade(toEntity, bean.getRepaymentExtrinterest(), BigDecimal.ZERO, 3012, title + " 收到提前还款利息补偿 " + bean.getRepaymentInterest() + "元");
                    } catch (FssException e) {
                        LogUtil.error(this.getClass(), e);
                    }
                }
            }

            // 转到应付账户
            BigDecimal amount = bean.getPayableAmount();
            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                FundAccountEntity toA0Account = fundAccountService.getFundAccount(Long.valueOf(bean.getCustomerId()), GlobalConstants.ACCOUNT_TYPE_PAYMENT);
                try {
                    this.transfer(toEntity, toA0Account, amount, 7, 1005,null,thirdPartyType, fundOrderEntity);
                } catch (FssException e) {
                    LogUtil.error(this.getClass(), e);
                }
            }
        }
        try {
            fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO, sumRepay, 3003, title + " 还款成功，扣除还款金额 " + sumRepay + "元");
        } catch (FssException e) {
            LogUtil.error(this.getClass(), e);
        }
    }
   
   
}