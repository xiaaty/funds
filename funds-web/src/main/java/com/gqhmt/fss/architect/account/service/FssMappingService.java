package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.*;
import com.gqhmt.fss.architect.account.bean.FssMappingBean;
import com.gqhmt.fss.architect.account.entity.FssMappingEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssMappingReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssMappingWriteMapper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
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
     * 修改红包账户状态
     * @param id
     * @throws FssException
     */
    public void delRedAccountById(long id) throws FssException{
     try {
        FssMappingEntity redAccountEntity= fssMappingReadMapper.selectByPrimaryKey(id);
         if(redAccountEntity.getIsValid().equals("1")){
             redAccountEntity.setIsValid("0");
         }else{
             redAccountEntity.setIsValid("1");
         }
         redAccountEntity.setModifyTime(new Date());
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

    public List<FssMappingBean> getMappingListByType(String mappingType){
        List<FssMappingBean> list=fssMappingReadMapper.getMappingByType(mappingType);
        return list;
    }

   public FssMappingEntity getMappingByCustId(String custId,String mappingType){
       Map<String,String> map=new HashMap();
       map.put("custId",custId);
       map.put("mappingType",mappingType);
       FssMappingEntity entity=fssMappingReadMapper.getMappingByCustId(map);
       return entity;
    }

    public FssMappingEntity getMappingEntityById(long id){
        FssMappingEntity entity=fssMappingReadMapper.selectByPrimaryKey(id);
        return entity;
    }

    public FssMappingEntity getMappingBySort(String sort){
        FssMappingEntity entity=fssMappingReadMapper.getMappingBySort(sort);
        return entity;
    }

    /**
     * 修改映射状态是否有效
     * @param id
     * @param mappingType
     * @param tradeType
     * @param isValid
     * @param sort
     * @param updater
     * @throws FssException
     */
    public void updateMappingValid(String id,String mappingType,String tradeType,String isValid,String sort,String updater) throws FssException{
        FssMappingEntity entity=fssMappingReadMapper.selectByPrimaryKey(Long.valueOf(id));
        try{
            entity.setIsValid(isValid);
            entity.setMappingType(mappingType);
            entity.setTradeType(tradeType);
            entity.setSort(sort);
            entity.setUpdater(updater);
            entity.setModifyTime(new Date());
            fssMappingWriteMapper.updateByPrimaryKey(entity);
        }catch (Exception e){
            LogUtil.error(this.getClass(), e);
            throw new FssException("91009805");
        }
    }

    /**
     * 查询红包账户信息
     * @return
     */
    public BigDecimal getBondSumAmount(String mappingType){
        BigDecimal sumAmount=fssMappingReadMapper.getBondSumAmount(mappingType);
        return sumAmount;
    }

    /**
     * 验证手机号是否配置
     * @param mobile
     * @return
     */
  /*  public FssMappingEntity getMappingByMobile(String mobile){
        FssMappingEntity entity=fssMappingReadMapper.getMappingByMobile(Long.valueOf(mobile));
        return entity;
    }*/

    /**
     * 保存映射手机信息
     * @param mobile
     * @param remark
     * @param creator
     * @throws FssException
     */
    public void saveSmsMobile(String mobile,String remark,String creator,String sort) throws FssException{
        try{
            FssMappingEntity redAccountEntity=GenerateBeanUtil.GenerateClassInstance(FssMappingEntity.class);
            redAccountEntity.setMappingType("12020001");//短信通知映射类型
            redAccountEntity.setCreater(creator);
            redAccountEntity.setCreateTime(new Date());
            redAccountEntity.setUpdater(creator);
            redAccountEntity.setModifyTime(new Date());
            redAccountEntity.setIsValid("0");
            redAccountEntity.setRemark(remark);
            redAccountEntity.setCustId(Long.valueOf(mobile));
            redAccountEntity.setSort(sort);
            redAccountEntity.setTradeType("12020001");
            fssMappingWriteMapper.insertUseGeneratedKeys(redAccountEntity);
        }catch (Exception e){
            LogUtil.error(this.getClass(), e);
            throw new FssException("91009804");
        }
    }

    /**
     * 获取手机号列表
     * @param mappingType
     * @return
     */
    public List<FssMappingBean> getMobileList(String mappingType){
        List<FssMappingBean> list=fssMappingReadMapper.getMobileList(mappingType);
        return list;
    }

}
