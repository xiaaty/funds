package com.gqhmt.business.architect.loan.mapper.read;

import com.gqhmt.business.architect.loan.entity.PointAccount;
import com.gqhmt.core.mybatis.ReadMapper;

/**
 *
 * @author tianwei
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/7/30 
 * Description:
 * <p/>
 * Modification History:

 */
public interface PointAccountReadMapper extends ReadMapper<PointAccount> {
	
	/**
	 * 
	* @Title: queryPointAccoutn 
	* @Description: 获取积分账户信息
	* @param custId
	* @param accountType
	* @return    设定文件 
	* @return PointsAccountEntity    返回类型 
	* @throws
	 */
	public PointAccount queryPointAccoutn(Integer custId,Integer accountType);

}
