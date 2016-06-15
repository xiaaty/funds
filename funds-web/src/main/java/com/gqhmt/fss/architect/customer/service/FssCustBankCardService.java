package com.gqhmt.fss.architect.customer.service;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.CommonUtil;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.fss.architect.customer.bean.CustomerBankcardView;
import com.gqhmt.fss.architect.customer.entity.FssCustBankCardEntity;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssBankCardInfoReadMapper;
import com.gqhmt.fss.architect.customer.mapper.read.FssCustomerReadMapper;
import com.gqhmt.fss.architect.customer.mapper.write.FssBankCardInfoWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
	private FssCustomerReadMapper fssCustomerReadMapper ;

	@Resource
	private FssBankCardInfoWriteMapper fssBankCardInfoWriteMapper;

	@Resource
	private FssBankCardInfoReadMapper fssBankCardInfoReadMapper;


	
	/**
	 *
	 * author:kyl
	 * time:2016年2月16日
	 * function：得到银行卡和用户信息列表
	 */
	public List<CustomerBankcardView> findbankCardAll(CustomerBankcardView customerAndUser) throws FssException{
		return fssCustomerReadMapper.findbankCardAll(customerAndUser);
	}

	/**
	 * 
	 * author:kyl
	 * time:2016年2月16日
	 * function：查询要申请变更银行卡用户信息
	 */
	public CustomerBankcardView findCustomerAndUser(Long id)throws FssException {
		// TODO Auto-generated method stub
		return fssCustomerReadMapper.findCustomerAndUser(id);
	}


	/**
	 *
	 * @param bankType			银行类型
	 * @param bankNo			银行卡号
	 * @param area				所属地区
	 * @param mchn				商户号
	 * @param fssCustomerEntity 客户实例
	 * @return
     * @throws FssException
     */
	public FssCustBankCardEntity createFssBankCardEntity(String bankType, String bankNo, String area, String mchn, FssCustomerEntity fssCustomerEntity) throws FssException{
			FssCustBankCardEntity fssbankcardInfo= GenerateBeanUtil.GenerateClassInstance(FssCustBankCardEntity.class);
			fssbankcardInfo.setCust_no(String.valueOf(fssCustomerEntity.getCustNo()));
			fssbankcardInfo.setCertType(fssCustomerEntity.getCertType());
			fssbankcardInfo.setCertNo(fssCustomerEntity.getCertNo());

			fssbankcardInfo.setBankType(bankType.length() == 8 ? bankType : bankType.length() == 3? "97030"+bankType : "9703"+bankType );
			fssbankcardInfo.setCardNo(bankNo);
			fssbankcardInfo.setArea(area.length() == 8 ? area : area.length() == 6 ? "95" + area : Application.getInstance().getEightCode( area)   );

			fssbankcardInfo.setAreaThird(area.length() == 6 ? Application.getInstance().getFourCode(area):area);
			fssbankcardInfo.setBankTypeThird(fssbankcardInfo.getBankType().substring(4));

			fssbankcardInfo.setBankCardNo(CommonUtil.getBankCardNo());
			fssbankcardInfo.setMchnChild(mchn);
			fssbankcardInfo.setMchnParent(Application.getInstance().getParentMchn(mchn));
			try {
				this.fssBankCardInfoWriteMapper.insertSelective(fssbankcardInfo);
			} catch (Exception e) {
				  throw new FssException("90002028");
			}
			return fssbankcardInfo;
	}
	
	
	
}
