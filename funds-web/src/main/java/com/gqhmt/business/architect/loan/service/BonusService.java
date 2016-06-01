package com.gqhmt.business.architect.loan.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gqhmt.business.architect.loan.entity.Bonus;
import com.gqhmt.business.architect.loan.mapper.read.BonusReadMapper;
import com.gqhmt.business.architect.loan.mapper.write.BonusWriteMapper;

@Service
public class BonusService {

	@Autowired
	private BonusReadMapper bonusReadMapper;
	@Autowired
	private BonusWriteMapper bonusWriteMapper;
	
	public Bonus select(Object id) {
		return bonusReadMapper.selectByPrimaryKey(id);
	}
	
	public int queryBounds(int userId,int ruleID) {
		return bonusReadMapper.queryBounds(userId, ruleID);
	}
	
	/**
	 *
	 * 修改红包状态，1，未使用，2冻结，3已使用，4已过期
	 * @param ids
	 * @param status
	 * @param sysUserId
	 */
	public void updateStatus(Object[] ids, int status, int sysUserId) {
		if(null != ids && ids.length > 0) {
			for (Object id : ids) {
				Bonus bonus = select(id);
				bonus.setStatus(status);
				bonus.setUpdateBy(sysUserId);
				bonus.setUpdateTime(new Date());
				bonusWriteMapper.updateByPrimaryKeySelective(bonus);
			}
		}
	}
	
	
	/**
	 * 修改红包的状态和金额
	 * @param bonudId 红包的Id
	 * @param status  红包的状态 1，未使用。2，冻结。3，已使用。4，已过期
	 * @param amount  对应红包的实际使用金额
	 * @param userId  用户Id
	 */
	public void updateStatusAndAmount(Integer bonudId,int status,BigDecimal actualAmount,int userId){
		Bonus bonus = bonusReadMapper.selectByPrimaryKey(bonudId);
		if(bonus != null){
			bonus.setStatus(status);
			bonus.setAmount(actualAmount);
			bonus.setUpdateBy(userId);
			bonus.setUpdateTime(new Date());
			bonusWriteMapper.updateByPrimaryKeySelective(bonus);
		}
	}

	
	
	
	
	
}
