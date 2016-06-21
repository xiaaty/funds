package com.gqhmt.fss.architect.merchant.service;

import com.gqhmt.fss.architect.merchant.bean.BusinessApiBean;
import com.gqhmt.fss.architect.merchant.entity.*;
import com.gqhmt.fss.architect.merchant.mapper.read.*;
import com.gqhmt.fss.architect.merchant.mapper.write.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MerchantService {
	
	@Resource
	private MerchantReadMapper merchantReadMapper;
	@Resource
	private MerchantWriteMapper merchantWriteMapper;
	
	@Resource
	private MerchantIpReadMapper merchantIpReadMapper;
	@Resource
	private MerchantIpWriteMapper merchantIpWriteMapper;
	
	@Resource
	private ApiAddrReadMapper apiAddrReadMapper;
	@Resource
	private ApiAddrWriteMapper apiAddrWriteMapper;
	@Resource
	private MerchantApiReadMapper merchantApiReadMapper;
	@Resource
	private MerchantApiWriteMapper merchantApiWriteMapper;
	@Resource
	private MerchantRepayConfigReadMapper merchantRepayConfigReadMapper;
	@Resource
	private MerchantRepayConfigWriteMapper merchantRepayConfigWriteMapper;
	/**
	 * 获取商户认证类型
	 * @param busiCode
	 * @return
	 */
	public Map<String, Object> getAuthTypeByCode(String busiCode) {
		return merchantReadMapper.getAuthTypeByCode(busiCode);
	}

	/**
	 * 获取商户ip白名单
	 * @param busiCode
	 * @return
	 */
	public List<Map<String, String>> getIpAddrListByCode(String busiCode) {
		return merchantReadMapper.getIpAddrListByCode(busiCode);
	}
	
	/**
	 * 获取商户api列表
	 * @param busiCode
	 * @return
	 */
	public List<Map<String, String>> getApiListByCode(String busiCode) {
		return merchantReadMapper.getApiListByCode(busiCode);
	}

	/**
	 * 获取商户列表
	 * @param map
	 * @return
	 */
	public List<MerchantEntity> findBusinessList(Map<String, Object> map) {
		return merchantReadMapper.findBusinessList(map);
	}

	public List<MerchantEntity> findBusinessALl(){
		return merchantReadMapper.selectAll();
	}

	/**
	 * 新增商户信息
	 * @param busi
	 */
	public void insertBusiness(MerchantEntity busi) {
		merchantWriteMapper.insertSelective(busi);
	}
	
	/**
	 * 修改商户信息
	 * @param busi
	 */
	public void updateBusiness(MerchantEntity busi) {
		merchantWriteMapper.updateByPrimaryKeySelective(busi);
	}
	
	/**
	 * 返回apiIp列表
	 * @param merchantIpConfigEntity
	 * @return
	 */
	public List<MerchantIpConfigEntity> findApiIpList(MerchantIpConfigEntity merchantIpConfigEntity) {
		return merchantIpReadMapper.select(merchantIpConfigEntity);
	}
	/**
	 * 新增ip地址
	 * @param merchantIpConfigEntity
	 */
	public void insertApiIpConfig(MerchantIpConfigEntity merchantIpConfigEntity) {
		merchantIpWriteMapper.insertSelective(merchantIpConfigEntity);
	}
	/**
	 * 删除ip地址
	 * @param merchantIpConfigEntity
	 */
	public void deleteApiIpConfig(MerchantIpConfigEntity merchantIpConfigEntity) {
		merchantIpWriteMapper.delete(merchantIpConfigEntity);
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：新增api地址
	 */
	public void insertApiAddr(ApiAddrEntity apiAddrEntity) {
		apiAddrWriteMapper.insertSelective(apiAddrEntity);
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：删除api地址
	 */
	public void deleteApiAddr(Long id) {
		apiAddrWriteMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：通过id查询api地址
	 */
	public ApiAddrEntity findapiAddrById(Long id) {
		
		return	apiAddrReadMapper.selectByPrimaryKey(id);
		
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：修改api地址
	 */
	public void updateApiAddr(ApiAddrEntity apiAddrEntity) {
		
		apiAddrWriteMapper.updateByPrimaryKey(apiAddrEntity);
		
	}
	
	public List<MerchantApiEntity> findBusinessApiList(MerchantApiEntity merchantApiEntity) {
		return merchantApiReadMapper.select(merchantApiEntity);
	}



	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：添加商户API授权
	 */
	public void insertBusinessApi(MerchantApiEntity merchantApiEntity) {
		
		merchantApiWriteMapper.insert(merchantApiEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月15日
	 * function：父商户列表
	 * @param merchantEntity
	 */
	public List<MerchantEntity> getParentBusiness(MerchantEntity merchantEntity) {
		
		return merchantReadMapper.getParentBusiness(merchantEntity);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月15日
	 * function：子商户列表
	 */
	public List<MerchantEntity> getChildBusiness(MerchantEntity merchantEntity) {
		
		return merchantReadMapper.getChildBusiness(merchantEntity);
	}
	   /**
	    * 
	    * author:jhz
	    * time:2016年2月19日
	    * function：API录入列表
	    */
	public List<BusinessApiBean> findBusinessApiList(BusinessApiBean businessApiBean) {
		// TODO Auto-generated method stub
		return merchantApiReadMapper.findBusinessApiList(businessApiBean);
	}
	 /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID删除商户API数据
     */
	public void deleteBusinessApi(Long id) {
		merchantApiWriteMapper.deleteByPrimaryKey(id);
		
	}
	  /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID查询商户API授权
     */
	public BusinessApiBean selectBusinessApi(Long id) {
		
		return merchantApiReadMapper.selectBusinessApi(id);
		
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：返回api地址列表
	 */
	public List<ApiAddrEntity> findApiAddrList() {
		return apiAddrReadMapper.selectAll();
	}

	public int updateBusinessApi(MerchantApiEntity merchantApiEntity) {
		
		return merchantApiWriteMapper.updateByPrimaryKey(merchantApiEntity);
		
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月23日
	 * function：通过id查询商户对象
	 */
	public MerchantEntity findBusinessById(long id) {
		return merchantReadMapper.selectByPrimaryKey(id);
		
	}


	public List<MerchantApiEntity> findMerchantApiAll(){
		return merchantApiReadMapper.selectAll();
	}

	/**
	 * 查询所有商户回盘配置集合
	 * author:xdw
	 * time:2016年6月16日
	 * @param
	 * @return List<MerchantRepayConfigEntity>
	 * @throws
	 */
	public List<MerchantRepayConfigEntity> getMerchantRepayConfigEntityList() {
		return merchantRepayConfigReadMapper.getMerchantRepayConfigEntityList();
	}

	/**
	 * 根据商户查询回盘配置集合
	 * author:xdw
	 * time:2016年6月16日
	 * @param
	 * @return List<MerchantRepayConfigEntity>
	 * @throws
	 */
	public List<MerchantRepayConfigEntity> getMerchantRepayConfigEntityListByMchnNo(String mchnNo) {
		return merchantRepayConfigReadMapper.getMerchantRepayConfigEntityListByMchnNo(mchnNo);
	}

	/**
	 * 修改商户回盘配置
	 * author:xdw
	 * time:2016年6月20日
	 * @param
	 * @return
	 * @throws
	 */
	public void updateMerchantRepayConfigEntity(MerchantRepayConfigEntity merchantRepayConfigEntity) {
		merchantRepayConfigWriteMapper.updateMerchantRepayConfigEntity(merchantRepayConfigEntity);
	}

	/**
	 * 添加商户回盘配置
	 * author:xdw
	 * time:2016年6月21日
	 * @param
	 * @return
	 * @throws
	 */
	public void addMerchantRepayConfigEntity(MerchantRepayConfigEntity merchantRepayConfigEntity) {
		merchantRepayConfigWriteMapper.addMerchantRepayConfigEntity(merchantRepayConfigEntity);
	}
}