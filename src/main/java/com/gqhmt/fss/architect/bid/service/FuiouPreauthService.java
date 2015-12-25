package com.gqhmt.fss.architect.bid.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.fss.architect.account.bean.FundAccountEntity;
import com.gqhmt.fss.architect.bid.bean.FuiouPreauth;
import com.gqhmt.fss.architect.bid.mapper.read.FuiouPreauthReadMapper;
import com.gqhmt.fss.architect.bid.mapper.write.FuiouPreauthWriteMapper;
import com.gqhmt.fss.architect.order.entity.FundOrderEntity;
import com.gqhmt.util.GlobalConstants;

/**
 * Filename:    com.fuiou.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/10 13:46
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/10  于泳      1.0     1.0 Version
 */
@Service
public class FuiouPreauthService {
    @Resource
    private FuiouPreauthReadMapper fuiouPreauthReadMapper;
    
    @Resource
    private FuiouPreauthWriteMapper fuiouPreauthWriteMapper;

    public void insert(FuiouPreauth fuiouPreauth){
    	fuiouPreauthWriteMapper.insertSelective(fuiouPreauth);
    }
    
    public void update(FuiouPreauth fuiouPreauth){
    	fuiouPreauthWriteMapper.updateByPrimaryKeySelective(fuiouPreauth);
    }
    
    public void delete(Long id){
    	fuiouPreauthWriteMapper.deleteByPrimaryKey(id);
    }


    public List<FuiouPreauth> getContractNo(Long bid,String userName ,String toUserName,Long amt){
        return fuiouPreauthReadMapper.getContractNo(bid,userName,toUserName,amt);
    }

    public Map<Integer,String> getContractNo(Long bid){
        return fuiouPreauthReadMapper.getContractNo(bid);
    }
    public Map<Integer,FuiouPreauth> getFuiouPreauth(Long bid){
        return fuiouPreauthReadMapper.getFuiouPreauth(bid);
    }

    //
    public void addFuiouPreauth(FundAccountEntity fromEntity,FundAccountEntity toSFEntity,Tender tender,String contractNo,FundOrderEntity fundOrderEntity){
        FuiouPreauth fuiouPreauth = new FuiouPreauth();
        fuiouPreauth.setAccountId(fromEntity.getId());
        fuiouPreauth.setAmount((new BigDecimal(tender.getInvestAmount())));
        fuiouPreauth.setSourceId(tender.getBidId());
        fuiouPreauth.setBid(tender.getBidId());
        fuiouPreauth.setTenderid(tender.getId());
        fuiouPreauth.setType(GlobalConstants.ORDER_BID);
        fuiouPreauth.setUserName(fromEntity.getUserName());
        fuiouPreauth.setState(1);
        fuiouPreauth.setUseAmount(BigDecimal.ZERO);
        fuiouPreauth.setToUserName(toSFEntity.getUserName());
        fuiouPreauth.setContractNo(contractNo);
        fuiouPreauth.setOrderNo(fundOrderEntity.getOrderNo());
        insert(fuiouPreauth);
    }

    public List<FuiouPreauth> bidFaild(){
        return fuiouPreauthReadMapper.bidFaild();
    }
}
