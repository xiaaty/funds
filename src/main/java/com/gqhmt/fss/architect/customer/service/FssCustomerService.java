package com.gqhmt.fss.architect.customer.service;


import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;
import com.gqhmt.fss.architect.customer.mapper.write.FssCustomerWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FssCustomerService {


    @Resource
    private FssCustomerReadMapper customerReadMapper;

    @Resource
    private FssCustomerWriteMapper customerWriteMapper;
   
    public List<FssCustomerEntity> findCustomerByParams(Map map){
    	
        return this.customerReadMapper.findCustomerByParams(map);
    }

    /**
     * 创建本地客户信息
     * @param dto
     * @return
     * @throws FssException
     */
    public FssCustomerEntity create(CreateLoanAccountDto dto,String userId) throws FssException {
        //获取客户信息，通过certNo
        try {
            FssCustomerEntity fssCustomerEntity = GenerateBeanUtil.GenerateClassInstance(FssCustomerEntity.class,dto);
            fssCustomerEntity.setName(dto.getName());
            fssCustomerEntity.setMobile(dto.getMobile());
            fssCustomerEntity.setCertType(1);
            fssCustomerEntity.setCertNo(dto.getCert_no());
            fssCustomerEntity.setUserId(userId);
            fssCustomerEntity.setUserNo(userId);
            fssCustomerEntity.setCreateTime(new Date());
            fssCustomerEntity.setModifyTime(new Date());
            fssCustomerEntity.setCustNo(CommonUtil.getCustNo());
            fssCustomerEntity.setMchnChild(dto.getMchn());
            fssCustomerEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
            customerWriteMapper.insertSelective(fssCustomerEntity);
            return fssCustomerEntity;
        } catch (Exception e) {
            //生成errCode
        	  LogUtil.error(this.getClass(),e);
            throw new FssException("91009804");
        }
    }
    
    
    public FssCustomerEntity getCustomerNameById(Long id){
    	return customerReadMapper.selectByPrimaryKey(id);
    }
    
    
    
    
}
