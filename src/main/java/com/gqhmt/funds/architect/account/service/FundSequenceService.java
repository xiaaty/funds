package com.gqhmt.funds.architect.account.service;

import com.github.pagehelper.Page;
import com.gqhmt.fss.architect.account.exception.AmountFailException;
import com.gqhmt.fss.architect.account.exception.ChargeAmountNotenoughException;
import com.gqhmt.fss.architect.account.exception.FrozenAmountErrorException;
import com.gqhmt.fss.architect.account.exception.FundAccountNullException;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.funds.architect.account.bean.FundAccountSequenceBean;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.funds.architect.account.mapper.read.FundSequenceReadMapper;
import com.gqhmt.funds.architect.account.mapper.write.FundSequenceWriteMapper;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @param entity
     * @return
     */
    public FundSequenceEntity selectByPrimaryKey(Long id) {
    	return fundSequenceReadMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 存储流水记录
     * @param entity
     */
    public void save(FundSequenceEntity entity){
        fundSequenceWriteMapper.insertSelective(entity);
    }
    
    /**
     * 更新存储流水记录
     * @param entity
     */
    public void update(FundSequenceEntity entity){
        entity.setModifyTime(new Date());
        fundSequenceWriteMapper.updateByPrimaryKeySelective(entity);
    }
    /**
     * 删除流水记录
     * @param id
     */
    public void delete(Long id) {
    	fundSequenceWriteMapper.deleteByPrimaryKey(id);
    }

    /**
     * 充值操作
     * @param entity
     * @param accountType
     * @param amount
     */
    public void charge(FundAccountEntity entity, int accountType, BigDecimal amount, String thirdPartyType, FundOrderEntity orderEntity) throws ChargeAmountNotenoughException,FundAccountNullException{
        //校验账户信息
        if(entity == null || entity.getId() == null){
            throw new FundAccountNullException();
            //此处抛出异常
        }
        //校验资金,充值金额一定大于0
        if(amount.multiply(new BigDecimal(100)).longValue()<0){
            throw new ChargeAmountNotenoughException();
        }
        FundSequenceEntity fundSequenceEntity = this.getFundSequenceEntity(entity.getId(), 1, accountType, amount, thirdPartyType, orderEntity, 0L);
        fundSequenceEntity.setSumary("充值");
        fundSequenceEntity.setToken(getToken(orderEntity,accountType));
        this.save(fundSequenceEntity);

        this.fundAccountService.update(entity);
    }

    /**
     * 提现操作
     * @param entity
     * @param accountType
     * @param amount
     * @throws AmountFailException 
     */
    public void refund(FundAccountEntity entity,int accountType,BigDecimal amount,String thirdPartyType,FundOrderEntity orderEntity,Long oid) throws AmountFailException{
        //校验账户信息
        if(entity == null || entity.getId() == null){
            throw new FundAccountNullException();
            //此处抛出异常
        }
//        操作类型1充值、2提现、3转账、4冻结、5解冻
        //校验资金,提现金额不能大于账户余额 ？？此处传值，是正值还是负值呢，如果传入正值，后台需要处理为负值
       /* if(amount.multiply(new BigDecimal(100)).longValue()>0){
            throw new ChargeAmountNotenoughException();
        }*/
        if(amount.multiply(new BigDecimal("100")).longValue()<0){
            throw new AmountFailException("传入金额不能小于0");
        }
        amount = new BigDecimal("-"+amount.toPlainString());
        FundSequenceEntity fundSequenceEntity = this.getFundSequenceEntity(entity.getId(), 2, accountType, amount, thirdPartyType, orderEntity, oid);
        fundSequenceEntity.setSumary("提现");
        fundSequenceEntity.setToken(getToken(orderEntity,2003));
        this.save(fundSequenceEntity);
        this.fundAccountService.update(entity);
    }

    /**
     * 转账
     * @param fromEntity    转出账户
     * @param toEntiry      转入转换
     * @param amount        转账金额
     */
    public void transfer(FundAccountEntity fromEntity,FundAccountEntity toEntiry,BigDecimal amount,int actionType,int accountType,String thirdPartyType,FundOrderEntity orderEntity){
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
        this.save(fromFundSequenceEntity);

        FundSequenceEntity toFundSequenceEntity = this.getFundSequenceEntity(toEntiry.getId(), actionType, toActionType, amount, thirdPartyType, orderEntity, fromEntity.getId());
        toFundSequenceEntity.setSumary("转账转入 "+fromEntity.getCustName()+"("+fromEntity.getId()+")转入");
        this.save(toFundSequenceEntity);

        this.fundAccountService.update(fromEntity);
        this.fundAccountService.update(toEntiry);
    }

    /**
     * 冻结金额
     * @param orgEntity
     * @param frozenEntiry
     * @param amount
     */
    @Transactional(propagation= Propagation.REQUIRES_NEW,
            isolation= Isolation.READ_COMMITTED,
            noRollbackFor={CommandParmException.class},
            readOnly=false, timeout=3)
    public void frozenAmt(FundAccountEntity orgEntity,FundAccountEntity frozenEntiry,BigDecimal amount,int accountType,String memo,String thirdPartyType,FundOrderEntity orderEntity)throws FrozenAmountErrorException {
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
        this.save(orgFundSequenceEntity);
        int frozenType = 2007;
        if(accountType==1007){
            frozenType = 2007;
        }else if(accountType==3001){
            frozenType = 2004;
        }else if(accountType==1003){
            frozenType = 2001;
        }
        FundSequenceEntity frozenFundSequenceEntity = this.getFundSequenceEntity(frozenEntiry.getId(), 4, frozenType, amount, thirdPartyType, orderEntity, orgEntity.getId());
        this.save(frozenFundSequenceEntity);
        frozenFundSequenceEntity.setSumary("冻结");
        this.fundAccountService.update(orgEntity);
        this.fundAccountService.update(frozenEntiry);

    }

    /**
     * 解冻金额
     * @param orgEntity
     * @param frozenEntiry
     * @param amount
     */
    @Transactional(propagation= Propagation.REQUIRES_NEW,
            isolation= Isolation.READ_COMMITTED,
            noRollbackFor={CommandParmException.class},
            readOnly=false, timeout=3)
    public void unfreeze(FundAccountEntity orgEntity,FundAccountEntity frozenEntiry,BigDecimal amount,int accountType,String memo,String thirdPartyType,FundOrderEntity orderEntity){
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
        this.save(orgFundSequenceEntity);
        FundSequenceEntity frozenFundSequenceEntity =this.getFundSequenceEntity(frozenEntiry.getId(), 4, frozenType, amount, thirdPartyType, orderEntity, orgEntity.getId());
        frozenFundSequenceEntity.setSumary("解冻");
        this.save(frozenFundSequenceEntity);
        this.fundAccountService.update(orgEntity);
        this.fundAccountService.update(frozenEntiry);
    }


    public BigDecimal getSumAmount(long id){
        return fundSequenceReadMapper.getSumAmount(id);
    }


    protected FundSequenceEntity getFundSequenceEntity(Long accountID,int actionType,int accountType,BigDecimal amount,String thirdPartyType,FundOrderEntity orderEntity,Long oAccountId){
        FundSequenceEntity entity = new FundSequenceEntity();
        entity.setCreateTime(new Timestamp(new Date().getTime()));
        entity.setAmount(amount);
        entity.setAccountId(accountID);
        entity.setCurrency("0001");
        entity.setFundType(accountType);
        entity.setActionType(actionType);
        entity.setThirdPartyType(Integer.valueOf(thirdPartyType));
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
        return MD5Util.encryption(fundOrderEntity.getOrderNo()+String.valueOf(seq));
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
}
