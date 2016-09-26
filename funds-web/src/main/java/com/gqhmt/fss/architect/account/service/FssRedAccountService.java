package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.*;
import com.gqhmt.fss.architect.account.entity.FssRedAccountEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssRedAccountReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssRedAccountWriteMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.account.service.FssAccountService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:34
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  keyulai      1.0     1.0 Version
 */
@Service
public class FssRedAccountService {

    @Resource
    private FssRedAccountReadMapper fssRedAccountReadMapper;
    @Resource
    private FssRedAccountWriteMapper fssRedAccountWriteMapper;

    /**
     * 查询红包账户列表
     * @return
     * @throws FssException
     */
    public List<FssRedAccountEntity> queryRedAccountList(Map<String, String> map){
        Map<String, String> map2=new HashMap<String, String>();
        if(map!=null){
            String startTime = map.get("startTime");
            String endTime = map.get("endTime");
            map2.put("custId", map.get("custId"));
            map2.put("accountName", map.get("accountName"));
            map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
            map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
        }
        List<FssRedAccountEntity> redlist=fssRedAccountReadMapper.queryRedAccountList(map2);
        return redlist;
    }

    /**
     * 添加红包账户
     * @param custId
     * @param accountName
     * @param creator
     * @throws FssException
     */
   public void saveRedAccount(String custId,String accountName,String creator) throws FssException{
       try{
           FssRedAccountEntity redAccountEntity=GenerateBeanUtil.GenerateClassInstance(FssRedAccountEntity.class);
           redAccountEntity.setCustId(Long.valueOf(custId));
           redAccountEntity.setAccountName(accountName);
           redAccountEntity.setAccountType("0");
           redAccountEntity.setCreater(creator);
           redAccountEntity.setCreateTime(new Date());
           redAccountEntity.setUpdater(creator);
           redAccountEntity.setModifyTime(new Date());
           fssRedAccountWriteMapper.insertUseGeneratedKeys(redAccountEntity);
       }catch (Exception e){
           LogUtil.error(this.getClass(), e);
           throw new FssException("91009804");
       }
    }

    public void delRedAccountById(long id) throws FssException{
    try {
        fssRedAccountWriteMapper.deleteByPrimaryKey(id);
    }catch (Exception e){
        LogUtil.error(this.getClass(), e);
        throw new FssException("91009805");
    }
    }

}
