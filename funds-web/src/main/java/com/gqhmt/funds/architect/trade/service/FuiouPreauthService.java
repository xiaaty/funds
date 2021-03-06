package com.gqhmt.funds.architect.trade.service;

import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.entity.FuiouPreauth;
import com.gqhmt.funds.architect.trade.mapper.read.FuiouPreauthReadMapper;
import com.gqhmt.funds.architect.trade.mapper.write.FuiouPreauthWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
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

    public Map<Long,String> getContractNo(Long bid){
    	Map<Long,String> map = new HashMap<>();
    	List<FuiouPreauth>	fuiouPreauthlist = fuiouPreauthReadMapper.getContractNos(bid);
    	if(fuiouPreauthlist == null || fuiouPreauthlist.size() == 0){
            return null;
        }
    	for(FuiouPreauth fuiouPreauth:fuiouPreauthlist){
            map.put(Long.valueOf(fuiouPreauth.getTenderid()),fuiouPreauth.getContractNo());
        }
    	return map;
    }
    public Map<Long,FuiouPreauth> getFuiouPreauth(Long bid){
//        return fuiouPreauthReadMapper.getFuiouPreauth(bid);
    	Map<Long,FuiouPreauth> map = new HashMap<>();
    	List<FuiouPreauth>	list = fuiouPreauthReadMapper.getContractNos(bid);
    	if(list == null || list.size() == 0){
            return null;
        }
    	for(FuiouPreauth fuiouPreauth:list){
            Integer tId = fuiouPreauth.getTenderid();
    		map.put(Long.valueOf(tId),fuiouPreauth);
        }
    	return map;
    	
    }

    //
    public void addFuiouPreauth(FundAccountEntity fromEntity, FundAccountEntity toSFEntity,
                                BigDecimal amount, Integer bid,Integer tenderId,
                                String contractNo, FundOrderEntity fundOrderEntity){
        FuiouPreauth fuiouPreauth = new FuiouPreauth();
        fuiouPreauth.setAccountId(fromEntity.getId());
        fuiouPreauth.setAmount(amount);
        fuiouPreauth.setSourceId(bid);
        fuiouPreauth.setBid(bid);
        fuiouPreauth.setTenderid(tenderId);
        fuiouPreauth.setType(GlobalConstants.ORDER_BID);
        fuiouPreauth.setUserName(fromEntity.getUserName());
        fuiouPreauth.setState(1);
        fuiouPreauth.setUseAmount(BigDecimal.ZERO);
        if(toSFEntity!=null) {
            fuiouPreauth.setToUserName(toSFEntity.getUserName());
        }
        fuiouPreauth.setContractNo(contractNo);
        fuiouPreauth.setOrderNo(fundOrderEntity.getOrderNo());
        insert(fuiouPreauth);
    }

    public List<FuiouPreauth> bidFaild(){
        return fuiouPreauthReadMapper.bidFaild();
    }
    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：查询FuiouPreauth集合信息
     */
    public List<FuiouPreauth> findFuiouPreauthList(){
    	return fuiouPreauthReadMapper.selectAll();
    }

    /**
     * 
     * author:jhz
     * time:2016年2月16日
     * function：通过ID查询FuiouPreauth
     */
    public FuiouPreauth findFuiouPreauthById(Long id){
    	 return  fuiouPreauthReadMapper.selectByPrimaryKey(id);
    }
    
}
