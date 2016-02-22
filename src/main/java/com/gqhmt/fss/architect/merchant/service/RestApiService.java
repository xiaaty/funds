package com.gqhmt.fss.architect.merchant.service;

import com.gqhmt.fss.architect.merchant.bean.BusinessApiBean;
import com.gqhmt.fss.architect.merchant.entity.ApiAddr;
import com.gqhmt.fss.architect.merchant.entity.ApiIpConfig;
import com.gqhmt.fss.architect.merchant.entity.Business;
import com.gqhmt.fss.architect.merchant.entity.BusinessApi;
import com.gqhmt.fss.architect.merchant.mapper.read.RestApiAddrReadMapper;
import com.gqhmt.fss.architect.merchant.mapper.read.RestApiIpReadMapper;
import com.gqhmt.fss.architect.merchant.mapper.read.RestApiReadMapper;
import com.gqhmt.fss.architect.merchant.mapper.read.RestBusinessApiReadMapper;
import com.gqhmt.fss.architect.merchant.mapper.write.RestApiAddrWriteMapper;
import com.gqhmt.fss.architect.merchant.mapper.write.RestApiIpWriteMapper;
import com.gqhmt.fss.architect.merchant.mapper.write.RestApiWriteMapper;
import com.gqhmt.fss.architect.merchant.mapper.write.RestBusinessApiWriteMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RestApiService {
	
	@Resource
	private RestApiReadMapper restApiReadMapper;
	@Resource
	private RestApiWriteMapper restApiWriteMapper;
	
	@Resource
	private RestApiIpReadMapper restApiIpReadMapper;
	@Resource
	private RestApiIpWriteMapper restApiIpWriteMapper;
	
	@Resource
	private RestApiAddrReadMapper restApiAddrReadMapper;
	@Resource
	private RestApiAddrWriteMapper restApiAddrWriteMapper;
	@Resource
	private RestBusinessApiReadMapper restBusinessApiReadMapper;
	@Resource
	private RestBusinessApiWriteMapper restBusinessApiWriteMapper;
	/**
	 * 获取商户认证类型
	 * @param busiCode
	 * @return
	 */
	public Map<String, Object> getAuthTypeByCode(String busiCode) {
		return restApiReadMapper.getAuthTypeByCode(busiCode);
	}

	/**
	 * 获取商户ip白名单
	 * @param busiCode
	 * @return
	 */
	public List<Map<String, String>> getIpAddrListByCode(String busiCode) {
		return restApiReadMapper.getIpAddrListByCode(busiCode);
	}
	
	/**
	 * 获取商户api列表
	 * @param busiCode
	 * @return
	 */
	public List<Map<String, String>> getApiListByCode(String busiCode) {
		return restApiReadMapper.getApiListByCode(busiCode);
	}

	/**
	 * 获取商户列表
	 * @param map
	 * @return
	 */
	public List<Business> findBusinessList(Map<String, Object> map) {
		return restApiReadMapper.findBusinessList(map);
	}

	/**
	 * 新增商户信息
	 * @param busi
	 */
	public void insertBusiness(Business busi) {
		restApiWriteMapper.insertSelective(busi);
	}
	
	/**
	 * 修改商户信息
	 * @param busi
	 */
	public void updateBusiness(Business busi) {
		restApiWriteMapper.updateByPrimaryKeySelective(busi);
	}
	
	/**
	 * 返回apiIp列表
	 * @param apiIpConfig
	 * @return
	 */
	public List<ApiIpConfig> findApiIpList(ApiIpConfig apiIpConfig) {
		return restApiIpReadMapper.select(apiIpConfig);
	}
	/**
	 * 新增ip地址
	 * @param apiIpConfig
	 */
	public void insertApiIpConfig(ApiIpConfig apiIpConfig) {
		restApiIpWriteMapper.insertSelective(apiIpConfig);
	}
	/**
	 * 删除ip地址
	 * @param apiIpConfig
	 */
	public void deleteApiIpConfig(ApiIpConfig apiIpConfig) {
		restApiIpWriteMapper.delete(apiIpConfig);
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：新增api地址
	 */
	public void insertApiAddr(ApiAddr apiAddr) {
		restApiAddrWriteMapper.insertSelective(apiAddr);
	}
	
	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：删除api地址
	 */
	public void deleteApiAddr(Long id) {
		restApiAddrWriteMapper.delete(id);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：通过id查询api地址
	 */
	public ApiAddr findapiAddrById(Long id) {
		
		return	restApiAddrReadMapper.selectByPrimaryKey(id);
		
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：修改api地址
	 */
	public void updateApiAddr(ApiAddr apiAddr) {
		
		restApiAddrWriteMapper.updateByPrimaryKey(apiAddr);
		
	}
	
	public List<BusinessApi> findBusinessApiList(BusinessApi businessApi) {
		return restBusinessApiReadMapper.select(businessApi);
	}



	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：添加商户API授权
	 */
	public void insertBusinessApi(BusinessApi businessApi) {
		
		restBusinessApiWriteMapper.insert(businessApi);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月15日
	 * function：父商户列表
	 * @param business 
	 */
	public List<Business> getParentBusiness(Business business) {
		
		return restApiReadMapper.getParentBusiness(business);
	}
	/**
	 * 
	 * author:jhz
	 * time:2016年2月15日
	 * function：子商户列表
	 */
	public List<Business> getChildBusiness(Business business) {
		
		return restApiReadMapper.getChildBusiness(business);
	}
	   /**
	    * 
	    * author:jhz
	    * time:2016年2月19日
	    * function：API录入列表
	    */
	public List<BusinessApiBean> findBusinessApiList(BusinessApiBean businessApiBean) {
		// TODO Auto-generated method stub
		return restBusinessApiReadMapper.findBusinessApiList(businessApiBean);
	}
	 /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID删除商户API数据
     */
	public void deleteBusinessApi(Long id) {
		restBusinessApiWriteMapper.deleteByPrimaryKey(id);
		
	}
	  /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID查询商户API授权
     */
	public BusinessApiBean selectBusinessApi(Long id) {
		
		return restBusinessApiReadMapper.selectBusinessApi(id);
		
	}

	/**
	 * 
	 * author:jhz
	 * time:2016年2月19日
	 * function：返回api地址列表
	 */
	public List<ApiAddr> findApiAddrList() {
		return restApiAddrReadMapper.selectAll();
	}

	public int updateBusinessApi(BusinessApi businessApi) {
		
		return restBusinessApiWriteMapper.updateByPrimaryKey(businessApi);
		
	}

	
}
