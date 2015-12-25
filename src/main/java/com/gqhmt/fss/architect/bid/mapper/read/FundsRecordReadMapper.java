package com.gqhmt.fss.architect.bid.mapper.read;

import java.util.List;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.bid.bean.FundsRecord;

/**
 * Created by chris on 2015/5/2.
 */
public interface FundsRecordReadMapper extends ReadMapper<FundsRecord>{

    public  List<FundsRecord> listTender(Integer id);

    public  List<FundsRecord> listRepayment(Integer id,Integer repaymentId);
}
