package com.gqhmt.fss.architect.customer.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.customer.bean.CustomerAndUser;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.entity.FssCustBankCardEntity;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssChangeCardReadMapper;
import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;
import com.gqhmt.fss.architect.customer.mapper.write.FssBankCardInfoWriteMapper;

/**
 * Filename:    com.gqhmt.fss.architect.customer.service.FssCustBankCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:27
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  柯禹来      1.0     1.0 Version
 */
@Service
public class FssCustBankCardService {
	@Resource
	private FssCustomerReadMapper fssCustomerReadMapper;
	@Resource
	private FssBankCardInfoWriteMapper fssBankCardInfoWriteMapper;
	
	/**
	 * 
	 * author:kyl
	 * time:2016年2月16日
	 * function：得到银行卡和用户信息列表
	 */
	public List<CustomerAndUser> findbankCardAll(CustomerAndUser customerAndUser) {
		return fssCustomerReadMapper.findbankCardAll(customerAndUser);
	}

	/**
	 * 
	 * author:kyl
	 * time:2016年2月16日
	 * function：查询要申请变更银行卡用户信息
	 */
	public CustomerAndUser findCustomerAndUser(Long id) {
		// TODO Auto-generated method stub
		return fssCustomerReadMapper.findCustomerAndUser(id);
	}
	
	/**
	 * 创建本地账户银行卡信息
	 * @return fssbankcardInfo
	 */
	public FssCustBankCardEntity createFssBankCardInfo(CreateLoanAccountDto dto,FssCustomerEntity fssCustomerEntity) throws FssException{
		FssCustBankCardEntity  fssbankcardInfo;
		try {
			fssbankcardInfo= GenerateBeanUtil.GenerateClassInstance(FssCustBankCardEntity.class,dto);
			fssbankcardInfo.setCust_no(String.valueOf(fssCustomerEntity.getId()));
			fssbankcardInfo.setCertType(fssCustomerEntity.getCertType());
			fssbankcardInfo.setCertNo(dto.getCert_no());
			fssbankcardInfo.setBankId(Integer.valueOf(dto.getBank_id()));
			fssbankcardInfo.setCardNo(dto.getBank_card());
			fssbankcardInfo.setArea(Integer.valueOf(dto.getCity_id()));
			fssbankcardInfo.setBankCardNo(dto.getBank_card());
			fssbankcardInfo.setMchnChild(dto.getMchn());
			fssbankcardInfo.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
			fssBankCardInfoWriteMapper.insertSelective(fssbankcardInfo);
		} catch (Exception e) {
			 LogUtil.error(this.getClass(),e);
	         //生成错误码
	         throw  new FssException("91009804");
		}
		return fssbankcardInfo;
	}
	
}
