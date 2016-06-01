package com.gqhmt.funds.architect.account.service;


import com.github.pagehelper.Page;
import com.gqhmt.funds.architect.trade.entity.FundsRecord;
import com.gqhmt.funds.architect.trade.mapper.read.FundsRecordReadMapper;
import com.gqhmt.funds.architect.trade.mapper.write.FundsRecordWriteMapper;
import com.gqhmt.core.FssException;
import com.gqhmt.funds.architect.account.bean.FundAccountCustomerBean;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.fss.architect.asset.mapper.read.FssAssetReadMapper;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.exception.NeedSMSValidException;
import com.gqhmt.funds.architect.account.mapper.read.FundsAccountReadMapper;
import com.gqhmt.funds.architect.account.mapper.write.FundsAccountWriteMapper;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.util.LogUtil;
import com.gqhmt.util.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Filename:    com.gq.p2p.account.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 16:07
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
@Service
public class FundsRecorderService {

    @Resource
    private FundsRecordWriteMapper fundsRecordWriteMapper;
	
	/**
	 * 保存资金
	 * @param fromFundAccount
	 * @param toFundAccount
	 * @param orderEntity
	 * @param bid
	 * @param oid
	 * @param type
	 * @param rem
	 */
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
	
    public void save(FundsRecord fundsRecord){
    	fundsRecordWriteMapper.insert(fundsRecord);
    }
	
	
}

