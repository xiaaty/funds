package com.gqhmt.funds.architect.customer.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerInfoReadMapper extends ReadMapper<CustomerInfoEntity>{
	
	public Object[] queryCustomeAndUserById(Integer id);
	
	public List<Object[]> queryBorrowInfo(Integer bidId,Integer outCardId,Boolean bool);
	//createSQLQuery(" SELECT * FROM t_gq_customer_info WHERE  has_acount = 1 AND (is_batch_sendmsg_called IS NULL OR is_batch_sendmsg_called = 0) LIMIT 1000 ").addEntity(CustomerInfoEntity.class).list();
	public List<CustomerInfoEntity> selectNoChangeCustomer();
	
	public List<CustomerInfoEntity> queryCustomerInfoEntityList(CustomerInfoEntity customer);

	/**
	 * jhz
	 *
	 * @param certNo
	 * @return
     */
	public CustomerInfoEntity queryCustomerByCertNo(@Param("certNo") String certNo);
	/**
	 * jhz
	 * 根据手机号号查询客户信息
	 * @param mobile
	 * @return
	 */
	public CustomerInfoEntity searchCustomerInfoByMobile(@Param("mobile") String mobile);
}
