package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.*;
import com.gqhmt.fss.architect.account.entity.FssMappingEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssMappingReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssMappingWriteMapper;
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
public class FssMappingService {

    @Resource
    private FssMappingReadMapper fssMappingReadMapper;
    @Resource
    private FssMappingWriteMapper fssMappingWriteMapper;

    /**
     * 查询红包账户列表
     * @return
     * @throws FssException
     */
    public List<FssMappingEntity> queryRedAccountList(Map<String, String> map){
        Map<String, String> map2=new HashMap<String, String>();
        if(map!=null){
            String startTime = map.get("startTime");
            String endTime = map.get("endTime");
            map2.put("custId", map.get("custId"));
            map2.put("isValid", map.get("isValid"));
            map2.put("mappingType", map.get("mappingType"));
            map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
            map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
        }
        List<FssMappingEntity> redlist= fssMappingReadMapper.queryRedAccountList(map2);
        return redlist;
    }

    /**
     * 添加红包账户
     * @param custId
     * @param remark
     * @param creator
     * @throws FssException
     */
   public void saveRedAccount(String custId,String remark,String creator,String mappingType,String tradeType,String sort) throws FssException{
       try{
           FssMappingEntity redAccountEntity=GenerateBeanUtil.GenerateClassInstance(FssMappingEntity.class);
           redAccountEntity.setCustId(Long.valueOf(custId));
           redAccountEntity.setMappingType(mappingType);
           redAccountEntity.setTradeType(tradeType);
           redAccountEntity.setCreater(creator);
           redAccountEntity.setCreateTime(new Date());
           redAccountEntity.setUpdater(creator);
           redAccountEntity.setModifyTime(new Date());
           redAccountEntity.setIsValid("0");
           redAccountEntity.setRemark(remark);
           redAccountEntity.setSort(sort);
           fssMappingWriteMapper.insertUseGeneratedKeys(redAccountEntity);
       }catch (Exception e){
           LogUtil.error(this.getClass(), e);
           throw new FssException("91009804");
       }
    }

    /**
     * 删除红包账户
     * @param id
     * @throws FssException
     */
    public void delRedAccountById(long id) throws FssException{
     try {
        FssMappingEntity redAccountEntity= fssMappingReadMapper.selectByPrimaryKey(id);
        redAccountEntity.setIsValid("1");
        fssMappingWriteMapper.updateByPrimaryKey(redAccountEntity);
    }catch (Exception e){
        LogUtil.error(this.getClass(), e);
        throw new FssException("91009805");
    }
    }

    /**
     * 查询所有交易类型与映射类型列表
     * @return
     */
    public List<FssMappingEntity> findAll(){
        return fssMappingReadMapper.findAllMapping();
    }

    public List<FssMappingEntity> getMappingListByType(String mappingType){
        List<FssMappingEntity> list=fssMappingReadMapper.getMappingByType(mappingType);
        return list;
    }

   public FssMappingEntity getMappingByCustId(String custId){
       FssMappingEntity entity=fssMappingReadMapper.getMappingByCustId(custId);
       return entity;
    }
}
