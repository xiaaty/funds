package com.gqhmt.funds.architect.customer.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.customer.entity.CustomerInfoEntity;

import java.util.List;

public interface CustomerInfoReadMapper extends ReadMapper<CustomerInfoEntity>{
	
	public Object[] queryCustomeAndUserById(Integer id);
	
	public List<Object[]> queryBorrowInfo(Integer bidId,Integer outCardId,Boolean bool);
	//createSQLQuery(" SELECT * FROM t_gq_customer_info WHERE  has_acount = 1 AND (is_batch_sendmsg_called IS NULL OR is_batch_sendmsg_called = 0) LIMIT 1000 ").addEntity(CustomerInfoEntity.class).list();
	public List<CustomerInfoEntity> selectNoChangeCustomer();
	
}
