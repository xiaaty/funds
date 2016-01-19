package com.gqhmt.business.architect.loan.service;


import com.gqhmt.business.architect.loan.entity.PointAccount;
import com.gqhmt.business.architect.loan.entity.PointSequence;
import com.gqhmt.business.architect.loan.mapper.read.PointAccountReadMapper;
import com.gqhmt.business.architect.loan.mapper.write.PointAccountWriteMapper;
import com.gqhmt.business.architect.loan.mapper.write.PointSequenceWriteMapper;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.util.GlobalConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author tianwei
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/7/30
 * Description:
 * <p/>
 * Modification History:
 */
@Service
public class PointsAccountService {

    @Resource
    private PointAccountReadMapper pointAccountReadMapper;
    @Resource
    private PointAccountWriteMapper pointAccountWriteMapper;
    @Resource
    private PointSequenceWriteMapper pointSequenceWriteMapper;
    @Resource
    private CustomerInfoService customerInfoService;
    
    /**
     * 
    * @Title: addPointsAccount 
    * @Description: 添加积分/冠钱账户
    * @return    设定文件 
    * @return Map<String,String>    返回类型 
    * @throws
     */
    public void createPointsAccount(int custID){
		createPointsAccount(custID, GlobalConstants.POINT_GQ);
		createPointsAccount(custID, GlobalConstants.POINT_GQ_FREE);
		createPointsAccount(custID, GlobalConstants.POINT_JF);
		createPointsAccount(custID, GlobalConstants.POINT_JF_FREE);
    }
	public PointAccount createPointsAccount(int custID,int type){
		CustomerInfoEntity cus = customerInfoService.queryCustomerById(custID);
		PointAccount account = new PointAccount();
		account.setCustId(custID);
		account.setAccountType(type);
		account.setAmount(0l);
		account.setFreezeAmount(0L);
		account.setCreateTime(new Date());
		account.setUserID(cus.getUserId().longValue());
		// 生成冻结账户
		pointAccountWriteMapper.insertSelective(account);
		return account;
	}

	/**
	 * 充值
	 *
	 * @param custId      客户id
	 * @param accountType 账户类型
	 * @param amount      充值金额
	 * @param type        充值类型
	 * @param date        失效时间，空为永久有效
	 */
	public void pointsRecharge(int custId, int accountType, Long amount, int type, Date date) throws Exception {

		if (accountType > 1000) {
			throw new Exception("accountType参数不对，此方法不允许操作冻结账户");
		}
		PointAccount pointAccount = this.getPointsAccount(custId, accountType);
		// 更新积分/冠钱 账户金额 采用触发器更新，此处不再更新 触发器名称：gqPointInsert
		// 记录流水信息
		PointSequence pointsSeq = this.createPointsSequence(pointAccount, amount, type, date, "", 0);
		pointSequenceWriteMapper.insertSelective(pointsSeq);
	}


	/**
	 * 冠钱消费，从冠钱账户出账，如需冻结后在出账，请使用冻结出账方法
	 * @param custId
	 * @param accountType
	 * @param amount
	 * @param type
	 */
    public void pointsConsume(int custId,int accountType,long amount,int type) throws Exception {
		if(accountType>1000){
			 throw new Exception("accountType参数不对，此方法不允许操作冻结账户");
		}
    	PointAccount pointAccount = pointAccountReadMapper.queryPointAccoutn(custId, accountType);
		if(pointAccount.getAmount()<=0){
			throw new Exception("余额不足");
		}
//    	// 更新积分/冠钱 账户金额 采用触发器更新，此处不再更新
    	// 记录流水信息
    	PointSequence pointsSeq = this.createPointsSequence(pointAccount, -amount, type, null, "",0);
    	pointSequenceWriteMapper.insertSelective(pointsSeq);
    }

	/**
	 * 冻结消费，从冻结账户直接出账
	 * @param custId
	 * @param accountType
	 * @param amount
	 * @param type
	 * @throws Exception
	 */
	public void pointsConsumeByFreeze(int custId,int accountType,long amount,int type) throws Exception {
		if(accountType>1000){
			throw new Exception("accountType参数不对，此方法不允许操作冻结账户");
		}
		PointAccount pointAccount = pointAccountReadMapper.queryPointAccoutn(custId, accountType);
		PointAccount pfreeezePintAccount = pointAccountReadMapper.queryPointAccoutn(custId, 1000+accountType);
		if(pfreeezePintAccount.getAmount()<=0){
			throw new Exception("冻结资金不足，无法出账");
		}
//    	// 更新积分/冠钱 账户金额  采用触发器更新，此处不再更新
		// 记录流水信息
		PointSequence pointsSeq = this.createPointsSequence(pfreeezePintAccount, -amount, type, null, "",pointAccount.getId());
		pointSequenceWriteMapper.insertSelective(pointsSeq);
	}

	/**
	 * 冻结
	 * @param custId
	 * @param accountType
	 * @param amount
	 */
    public void pointsFreeze(int custId,int accountType,long amount) throws Exception {
		if(accountType>1000){
			throw new Exception("accountType参数不对，此方法不允许操作冻结账户");
		}
		PointAccount account = pointAccountReadMapper.queryPointAccoutn(custId,accountType );
		if(account.getAmount()<=0){
			throw new Exception("余额不足");
		}
    	// 更新冠钱冻结账户  采用触发器更新，此处不再更新
    	PointAccount freezeAccount = pointAccountReadMapper.queryPointAccoutn(custId,Integer.parseInt("100"+accountType) );
    	// 记录流水信息
    	PointSequence pointsSeq = this.createPointsSequence(account, -amount, 2001, null, "冻结",freezeAccount.getId());
		PointSequence freezePointsSeq = this.createPointsSequence(freezeAccount, amount, 2001, null, "冻结",account.getId());
		pointSequenceWriteMapper.insertSelective(pointsSeq);
		pointSequenceWriteMapper.insertSelective(freezePointsSeq);
    }

	/**
	 * 解冻
	 * @param custId
	 * @param accountType
	 * @param amount
	 */
    public void pointsUnfreeze(int custId,int accountType,long amount) throws Exception {
		if(accountType>1000){
			throw new Exception("accountType参数不对，此方法不允许操作冻结账户");
		}
		PointAccount account = pointAccountReadMapper.queryPointAccoutn(custId,accountType );
		PointAccount freezeAccount = pointAccountReadMapper.queryPointAccoutn(custId,Integer.parseInt("100"+accountType));
		if(freezeAccount.getAmount()<=0){
			throw new Exception("解冻失败，冻结额不足");
		}
		// 记录流水信息
		PointSequence pointsSeq = this.createPointsSequence(account, amount, 2002, null, "解冻",freezeAccount.getId());
		PointSequence freezePointsSeq = this.createPointsSequence(freezeAccount,-amount,2002,null,"解冻",account.getId());
		pointSequenceWriteMapper.insertSelective(pointsSeq);
		pointSequenceWriteMapper.insertSelective(freezePointsSeq);
    }

	/**
	 * 获取账户
	 *
	 * @param custId			客户id
	 * @param type             账户类型，1，冠钱，2积分
	 * @return
	 */
	public PointAccount getPointsAccount(int custId,int type){
		PointAccount PointAccount = pointAccountReadMapper.queryPointAccoutn(custId,type);
		if(PointAccount == null){
			PointAccount = this.createPointsAccount(custId, type);
			if(type <1000){
				this.createPointsAccount(custId, 1000+type);
			}
		}
		return PointAccount;
	}

	/**
	 * 根据账户生成流水信息
	 * @param pointAccount
	 * @param amount
	 * @param type
	 * @param date
	 * @param remak
	 * @return
	 */
	public PointSequence createPointsSequence(PointAccount pointAccount,long amount,int type,Date date,String remak,long oid){
		// 生成流水信息
		PointSequence pointsSeq = new PointSequence();
		pointsSeq.setAccountId(pointAccount.getId());
		pointsSeq.setAmount(amount);
		pointsSeq.setCreateTime(new Date());
		pointsSeq.setTradeType(type);
		pointsSeq.setRemark(remak);
		pointsSeq.setOid(oid);
		return pointsSeq;
	}
}
