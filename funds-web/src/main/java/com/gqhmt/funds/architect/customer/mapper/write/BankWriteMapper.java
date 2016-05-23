package com.gqhmt.funds.architect.customer.mapper.write;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.funds.architect.customer.entity.BankEntity;

public interface BankWriteMapper extends ReadAndWriteMapper<BankEntity> {
	  public void saveBank(BankEntity bank);
	  public void updateBank(BankEntity bank);
}
