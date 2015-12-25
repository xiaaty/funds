package com.gqhmt.business.architect.loan.mapper.read;

import com.gqhmt.business.architect.loan.entity.Bonus;
import com.gqhmt.core.mybatis.ReadMapper;

public interface BonusReadMapper extends ReadMapper<Bonus> {

	public int queryBounds(int userId,int ruleID);
	
}
