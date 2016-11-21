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
     * @throws FssException                                busi_Id,Integer.valueOf(busi_type.toString()),tradeType,seq_no,null,busiNo
     */
    public FssAccountBindEntity createFssAccountMapping(Long busi_id,Integer busi_type,String tradeType,String seqNo,String contractNo,String custName,String mobile) throws FssException{
        FssAccountBindEntity entity = this.getBindAccountByParam(busi_id,busi_type);
        if( entity != null){
            return entity;
        }

        FssAccountBindEntity mappingEntity=null;
        try {
            mappingEntity= GenerateBeanUtil.GenerateClassInstance(FssAccountBindEntity.class);;
            mappingEntity.setBusiId(busi_id);
            mappingEntity.setBusiType(busi_type);
            mappingEntity.setStatus("0");
            mappingEntity.setCustName(custName);
            mappingEntity.setMoblie(mobile);
            mappingEntity.setSeqNo(seqNo);
            mappingEntity.setTradeType(tradeType);
            mappingEntity.setContractNo(contractNo);
            mappingEntity.setCreateTime(new Date());
            mappingEntity.setModifyTime(new Date());
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
    public FssAccountBindEntity getBindAccountByParam(Long busiId,Integer busiType){
        FssAccountBindEntity bindAccountEntity=fssAccountBindReadMapper.getBindAccByParam(busiId,busiType);
        return bindAccountEntity;
    }

    /**
     * 修改客户编号与统一支付账号绑定
     * @throws FssException
     */
   public void updateBindAccount(Long id,String status,String accNo,String seqNo) throws FssException {
       FssAccountBindEntity fssAccountBindEntity = fssAccountBindReadMapper.selectByPrimaryKey(id);
       try {
            fssAccountBindEntity.setAccNo(accNo);
            fssAccountBindEntity.setStatus(status);
            fssAccountBindEntity.setSeqNo(seqNo);
            fssAccountBindEntity.setModifyTime(new Date());
            fssAccountBindWriteMapper.updateByPrimaryKey(fssAccountBindEntity);
        }catch (Exception e){
            throw new FssException("91009804");
        }
    }

    /**
     * 校验账户是否已经绑定
     * @param busiId
     * @param busiType
     */
    public FssAccountBindEntity checkBindAccount(Long busiId,Integer busiType) throws FssException {
        FssAccountBindEntity bindEntity = this.getBindAccountByParam(busiId, busiType);
        if (bindEntity == null) throw new FssException("90004034");//账户未绑定
        return bindEntity;
    }

    /**
     * 根据业务订单号查询绑定账户信息
     * @param seqNo
     * @return
     */
    public FssAccountBindEntity getBindAccountBySeqNo(String seqNo){
        FssAccountBindEntity bindEntity = fssAccountBindReadMapper.getBindAccountBySeqNo(seqNo);
        return bindEntity;
    }


}

