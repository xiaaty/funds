package com.gqhmt.fss.architect.customer.service;


import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.*;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;
import com.gqhmt.fss.architect.customer.mapper.write.FssBankCardInfoWriteMapper;
import com.gqhmt.fss.architect.customer.mapper.write.FssCustomerWriteMapper;
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
    
    @Resource
    private FssCustBankCardService fssCustBankCardService;
    
    @Resource
    private FssBankCardInfoWriteMapper fssBankCardInfoWriteMapper;
   
    public List<FssCustomerEntity> findCustomerByParams(Map<String,String> map)throws FssException{
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
    
    
    public FssCustomerEntity getCustomerNameById(Long id)throws FssException{
    	return customerReadMapper.selectByPrimaryKey(id);
    }
    
    public FssCustomerEntity getFssCustomerEntityByCertNo(String certNo)throws FssException{
    	return customerReadMapper.selectByCertNo(certNo);
    }

    public void updateCustId(FssCustomerEntity fssCustomerEntity,Long custId){
        fssCustomerEntity.setCustId(custId);
        this.customerWriteMapper.updateByPrimaryKeySelective(fssCustomerEntity);
    }

    /**
     * 
     * author:jhz
     * time:2016年3月28日
     * function：根据custNo得到用户对象
     */
    public FssCustomerEntity getCustomerNameByCustNo(String custNo)throws FssException{
		return customerReadMapper.selectByCustNo(custNo);
    }
    
    
//	public FssCustomerEntity createFssAccountInfo(CreateLoanAccountDto dto) throws FssException {
////			1.创建账户
//		FssCustomerEntity fssCustomerEntity;
//			try {
//				fssCustomerEntity = this.createFssCustomerEntity(dto);
//				customerWriteMapper.insertSelective(fssCustomerEntity);
//			} catch (Exception e) {
//				LogUtil.info(this.getClass(), e.getMessage());
//				throw new FssException("91009804");
//			}
//			/*//2.创建用户
//			UserEntity userEntity;
//			try {
//				userEntity = gqUserService.createUser(loanAccountDto,customerInfoEntity);
//				gqUserWriteMapper.insertSelective(userEntity);
//			} catch (Exception e) {
//				LogUtil.info(this.getClass(), e.getMessage());
//				throw new FssException("91009804");
//			}*/
//			//3.创建银行卡信息     t_gq_bank_info
//			FssCustBankCardEntity fssBankCardInfoEntity;
//			try {
//				fssBankCardInfoEntity = fssCustBankCardService.createFssBankCardEntity(dto, fssCustomerEntity);
//				fssBankCardInfoWriteMapper.insertSelective(fssBankCardInfoEntity);
//			} catch (Exception e) {
//				LogUtil.info(this.getClass(), e.getMessage());
//				throw new FssException("91009804");
//			}
//		return fssCustomerEntity;
//	}

    
    
	   public FssCustomerEntity createFssCustomerEntity(String  name,String  mobile,String certNo,Long custId,String mchn) throws FssException {
			FssCustomerEntity fssCustomerEntity = GenerateBeanUtil.GenerateClassInstance(FssCustomerEntity.class);
            if(custId != null && custId<100){
                name = GlobalConstants.COMPANY_ACCOUNT_NAME.get(custId);
            }
			fssCustomerEntity.setName(name);
			fssCustomerEntity.setMobile(mobile);
			fssCustomerEntity.setCertType(1);
			fssCustomerEntity.setCertNo(certNo);
			fssCustomerEntity.setCreateTime(new Date());
			fssCustomerEntity.setModifyTime(new Date());
			fssCustomerEntity.setCustNo(CommonUtil.getCustNo());
		    fssCustomerEntity.setCustId(custId);
			fssCustomerEntity.setMchnChild(mchn);
			fssCustomerEntity.setMchnParent(Application.getInstance().getParentMchn(mchn));
			fssCustomerEntity.setUserId("1");
		   	this.customerWriteMapper.insertSelective(fssCustomerEntity);
		    return fssCustomerEntity;
	        
	  }





}
