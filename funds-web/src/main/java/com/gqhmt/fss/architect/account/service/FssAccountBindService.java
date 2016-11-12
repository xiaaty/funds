package com.gqhmt.fss.architect.account.service;


import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountBindReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssAccountBindWriteMapper;
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
 * @author kyl
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 16:07
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  kyl      1.0     1.0 Version
 */
@Service
public class FssAccountBindService {
    @Resource
    private FssAccountBindReadMapper fssAccountBindReadMapper;
    @Resource
    private FssAccountBindWriteMapper fssAccountBindWriteMapper;

    /**
     * 统一支付账户映射表数据查询
     * @param map
     * @return
     */
    public List<FssAccountBindEntity> queryAccountBindList(Map<String,String> map){
       return fssAccountBindReadMapper.queryAccountBindList(map);
    }

    /**
     * 创建映射信息
     * @param busi_id
     * @param busi_type
     * @return
     * @throws FssException
     */
    public FssAccountBindEntity createFssAccountMapping(Long busi_id, Integer busi_type,String tradeType,String accNo,String seqNo,String openAccTime,String contractNo) throws FssException{
        FssAccountBindEntity mappingEntity=null;
        try {
            mappingEntity= GenerateBeanUtil.GenerateClassInstance(FssAccountBindEntity.class);;
            mappingEntity.setBusiId(busi_id);
            mappingEntity.setBusiType(busi_type);
            mappingEntity.setStatus("0");
            mappingEntity.setAccNo(accNo);
            mappingEntity.setTradeType(tradeType);
            mappingEntity.setOpenAccTime(openAccTime);
            mappingEntity.setContractNo(contractNo);
//            mappingEntity.getCreateTime(new Date());
//            mappingEntity.setModifyTime(new Date());
            fssAccountBindWriteMapper.insertUseGeneratedKeys(mappingEntity);
        }catch (Exception e){
            LogUtil.debug(this.getClass(),mappingEntity+":"+mappingEntity.getId());
            throw new FssException("91009804");
        }
        return mappingEntity;
    }

    /**
     * 根据客户号（或标的号）、账户类型查询映射账户信息
     * @param busiId
     * @param busiType
     * @return
     */
    public FssAccountBindEntity getBindAccountByParam(String busiId,String busiType){
        FssAccountBindEntity bindAccountEntity=fssAccountBindReadMapper.getBindAccByParam(busiId,busiType);
        return bindAccountEntity;
    }

    /**
     * 修改客户编号与统一支付账号绑定
     * @param fssAccountBindEntity
     * @throws FssException
     */
   public void updateBindAccount(FssAccountBindEntity fssAccountBindEntity) throws FssException {
        try {
            fssAccountBindWriteMapper.updateByPrimaryKey(fssAccountBindEntity);
        }catch (Exception e){
            throw new FssException("91009804");
        }
    }
  }

