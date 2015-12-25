package com.gqhmt.fss.architect.bid.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.architect.account.bean.FundAccountEntity;
import com.gqhmt.fss.architect.bid.bean.FundsRecord;
import com.gqhmt.fss.architect.bid.mapper.read.FundsRecordReadMapper;
import com.gqhmt.fss.architect.bid.mapper.write.FundsRecordWriteMapper;
import com.gqhmt.fss.architect.order.entity.FundOrderEntity;

/**
 * Created by chris on 2015/5/2.
 */
@Service
public class FundsRecordService {

    @Resource
    private FundsRecordReadMapper fundsRecordReadMapper;
    @Resource
    private FundsRecordWriteMapper fundsRecordWriteMapper;

    /**
     *
     * @param fundsRecord
     */
    public void save(FundsRecord fundsRecord){
    	fundsRecordWriteMapper.insertSelective(fundsRecord);
    }

    public void update(FundsRecord fundsRecord){
    	fundsRecordWriteMapper.updateByPrimaryKeySelective(fundsRecord);
    }
    
    public void delete(Long id){
    	fundsRecordWriteMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据标的id获取投标记录
     * @param id
     * @return
     */
    public List<FundsRecord> listTender(Integer id){
        return fundsRecordReadMapper.listTender(id);
    }

    /**
     * 根据标的id获取还款记录
     * @param id     标的id
     * @param rId    还款id
     * @return
     */
    public List<FundsRecord> listRepayment(Integer id,Integer rId){
        return fundsRecordReadMapper.listRepayment(id,rId);
    }

    public void add(FundAccountEntity fromFundAccount,FundAccountEntity toFundAccount,FundOrderEntity orderEntity,Long bid,Long oid,int type,String rem){
        this.save(getFundsrecord(fromFundAccount,toFundAccount,orderEntity,bid,oid,type,rem));
    }

    public FundsRecord getFundsrecord(FundAccountEntity fromFundAccount,FundAccountEntity toFundAccount,FundOrderEntity orderEntity,Long bid,Long oid,int type,String rem){
        FundsRecord fundsRecord = new FundsRecord();

        fundsRecord.setFromAccountId(fromFundAccount.getId());
        fundsRecord.setFromUserName(fromFundAccount.getUserName());
        fundsRecord.setFromUserNameCn(fromFundAccount.getCustName());

        fundsRecord.setToAccountId(toFundAccount.getId());
        fundsRecord.setToUserName(toFundAccount.getUserName());
        fundsRecord.setToUserNameCn(toFundAccount.getCustName());

        fundsRecord.setAmount(orderEntity.getOrderAmount());
        fundsRecord.setOrderNo(orderEntity.getOrderNo());


        fundsRecord.setStatus(1);

        fundsRecord.setType(type);
        fundsRecord.setBid(bid);
        fundsRecord.setoId(oid);

        return fundsRecord;
    }

}
