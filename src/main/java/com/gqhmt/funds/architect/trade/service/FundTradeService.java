package com.gqhmt.funds.architect.trade.service;

import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.entity.FuiouPreauth;
import com.gqhmt.funds.architect.trade.entity.FundTradeEntity;
import com.gqhmt.funds.architect.trade.mapper.read.FuiouPreauthReadMapper;
import com.gqhmt.funds.architect.trade.mapper.read.FundTradeReadMapper;
import com.gqhmt.funds.architect.trade.mapper.write.FuiouPreauthWriteMapper;
import com.gqhmt.funds.architect.trade.mapper.write.FundTradeWriteMapper;
import com.gqhmt.core.util.GlobalConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
public class FundTradeService {
    @Resource
    private FundTradeReadMapper fundTradeReadMapper;
    
    @Resource
    private FundTradeWriteMapper fundTradeWriteMapper ;
    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：新增FundTrade
     */
    public int insertFundTrade(FundTradeEntity fundTradeEntity){
    	return fundTradeWriteMapper.insert(fundTradeEntity);
    }
    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：修改FundTrade
     */
    public int updateFundTrade(FundTradeEntity fundTradeEntity){
    	return fundTradeWriteMapper.updateByPrimaryKey(fundTradeEntity);
    }
    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：删除FundTrade
     */
    public int deletefundTradeEntity(Long id){
    	return fundTradeWriteMapper.delete(id);
    }

    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：查询fundTradeEntity集合信息
     */
    public List<FundTradeEntity> findFundTradeList(){
    	return fundTradeReadMapper.selectAll();
    }

    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：通过ID查询FundTradeEntity
     */
    public FundTradeEntity findFundTradeById(Integer id){
    	 return fundTradeReadMapper.selectByPrimaryKey(id);
    }
    
}
