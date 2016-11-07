package com.gqhmt.fss.architect.account.service;


import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountBondEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountMappingReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssAccountMappingWriteMapper;
import com.gqhmt.util.LogUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class FssAccountMappingService {
    @Resource
    private FssAccountMappingReadMapper fundsAccountReadMapper;
    @Resource
    private FssAccountMappingWriteMapper fundAccountWriteMapper;

    /**
     * 统一支付账户映射表数据查询
     * @param map
     * @return
     */
    public List<FssAccountBondEntity> queryAccountMappingList(Map<String,String> map){

       return fundsAccountReadMapper.queryAccountMapping(map);
    }





    /**
     * 创建映射信息
     * @param busi_id
     * @param busi_type
     * @return
     * @throws FssException
     */
    public FssAccountBondEntity createFssAccountMapping(Long busi_id, Integer busi_type) throws FssException{
        FssAccountBondEntity mappingEntity=null;
        try {
            mappingEntity= GenerateBeanUtil.GenerateClassInstance(FssAccountBondEntity.class);;
            mappingEntity.setBusiId(busi_id);
            mappingEntity.setBusiType(busi_type);
            mappingEntity.setStatus("0");
            mappingEntity.getCreateTime(new Date());
            mappingEntity.setModifyTime(new Date());
            fundAccountWriteMapper.insertUseGeneratedKeys(mappingEntity);
        }catch (Exception e){
            LogUtil.debug(this.getClass(),mappingEntity+":"+mappingEntity.getId());
            throw new FssException("91009804");
        }
        return mappingEntity;
    }







  }

