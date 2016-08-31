package com.gqhmt.fss.architect.account.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.entity.FssAccountBalanceDiffEntity;

import org.apache.ibatis.annotations.Param;

/**
 * @author zhaoenyue
 */
public interface FssAccountBalanceDiffReadMapper extends ReadMapper<FssAccountBalanceDiffEntity>{
	
	/**
	 * 查询T-1日与富友产生交易的账户信息
	 * @author zhaoenyue
	 * @return
	 */
	public List<FssAccountBalanceDiffEntity> queryLastDayBusinessDealAccount();

	/**
	 * 查询账户余额差异表信息集合
	 * @author xdw
	 * @return
	 */
	public List<FssAccountBalanceDiffEntity> queryAccBalanceDiffList(Map<String,String> map);

	/**
	 * 根据custId查询待校验的账户信息
	 * @param custIdList
	 * @return
	 */
	List<FssAccountBalanceDiffEntity> queryBusinessDealAccountByCustIds(@Param("custIdList") List<String> custIdList);

}
