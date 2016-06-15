package com.gqhmt.fss.architect.order.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.order.entity.FssSeqOrderEntity;
import com.gqhmt.fss.architect.order.mapper.write.FssSeqOrderWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.fss.architect.order.service.FssSeqOrderService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/6 15:45
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/6  于泳      1.0     1.0 Version
 */
@Service
public class FssSeqOrderService {

    @Resource
    private FssSeqOrderWriteMapper fssSeqOrderWriteMapper;

    public void save(FssSeqOrderEntity fssSeqOrderEntity) throws FssException {
        try{
            fssSeqOrderWriteMapper.insertSelective(fssSeqOrderEntity);
        }catch (Exception e){

            if(e.getMessage() != null && e.getMessage().contains("uk_seq_no_mchn")){
                throw  new FssException("90008202");
            }else{
                LogUtil.error(this.getClass(),e);
                throw  new FssException("90099005");
            }
        }

    }

    public void update(FssSeqOrderEntity fssSeqOrderEntity)throws FssException{
        try {
            fssSeqOrderWriteMapper.updateByPrimaryKeySelective(fssSeqOrderEntity);
        }catch (Exception e){

            if(e.getMessage() != null && e.getMessage().contains("uk_seq_no_mchn")){
                throw  new FssException("90008202");
            }else{
                LogUtil.error(this.getClass(),e);
                throw  new FssException("90099005");
            }
        }

    }




}
