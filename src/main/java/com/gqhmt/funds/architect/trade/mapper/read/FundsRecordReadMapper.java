package com.gqhmt.funds.architect.trade.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.trade.entity.FundsRecord;

import java.util.List;

/**
 * Created by chris on 2015/5/2.
 */
public interface FundsRecordReadMapper extends ReadMapper<FundsRecord>{

    public  List<FundsRecord> listTender(Integer id);

    public  List<FundsRecord> listRepayment(Integer id,Integer repaymentId);
}
