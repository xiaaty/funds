package com.gqhmt.funds.architect.customer.mapper.write;


import java.util.Map;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;

public interface CustomerInfoWriteMapper extends ReadAndWriteMapper<CustomerInfoEntity> {
	
	public void updateCustomer(Map map);
	
}
