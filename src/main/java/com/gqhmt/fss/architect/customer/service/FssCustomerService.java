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
import com.gqhmt.util.StringUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FssCustomerService {


    @Resource
    private FssCustomerReadMapper customerReadMapper;

    @Resource
    private FssCustomerWriteMapper customerWriteMapper;
   
    public List<FssCustomerEntity> findCustomerByParams(Map<String,String> map){
    	Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("name",map.get("name")!=null ? map.get("name") : null);
			map2.put("certNo", map.get("certNo")!=null ? map.get("certNo") : null);
			map2.put("mobile", map.get("mobile")!=null ? map.get("mobile") : null);
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
        return this.customerReadMapper.findCustomerByParams(map2);
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
    
    public FssCustomerEntity getFssCustomerEntityByCertNo(String certNo){
    	FssCustomerEntity fssCustomerEntity=new FssCustomerEntity();
    	fssCustomerEntity.setCertNo(certNo);
    	return customerReadMapper.selectOne(fssCustomerEntity);
    }
    /**
     * 
     * author:jhz
     * time:2016年3月28日
     * function：根据custNo得到用户对象
     */
    public FssCustomerEntity getCustomerNameByCustNo(String custNo){
    	FssCustomerEntity record=new FssCustomerEntity();
    	record.setCustNo(custNo);
		return customerReadMapper.selectOne(record);
    }
}
