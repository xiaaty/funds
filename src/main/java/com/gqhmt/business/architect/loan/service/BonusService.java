package com.gqhmt.business.architect.loan.service;

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
	
}
