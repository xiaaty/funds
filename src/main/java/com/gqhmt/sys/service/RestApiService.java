package com.gqhmt.sys.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.fss.merchant.entity.ApiAddr;
import com.gqhmt.fss.merchant.entity.ApiIpConfig;
import com.gqhmt.fss.merchant.entity.Business;
import com.gqhmt.fss.merchant.mapper.read.RestApiAddrReadMapper;
import com.gqhmt.fss.merchant.mapper.read.RestApiIpReadMapper;
import com.gqhmt.fss.merchant.mapper.read.RestApiReadMapper;
import com.gqhmt.fss.merchant.mapper.write.RestApiAddrWriteMapper;
import com.gqhmt.fss.merchant.mapper.write.RestApiIpWriteMapper;
import com.gqhmt.fss.merchant.mapper.write.RestApiWriteMapper;

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
	 * @param pid
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
	 * 返回api地址列表
	 * @param apiIpConfig
	 * @return
	 */
	public List<ApiAddr> findApiAddrList(ApiAddr apiAddr) {
		return restApiAddrReadMapper.select(apiAddr);
	}
	/**
	 * 新增api地址
	 * @param apiIpConfig
	 */
	public void insertApiAddr(ApiAddr apiAddr) {
		restApiAddrWriteMapper.insertSelective(apiAddr);
	}
	/**
	 * 删除api地址
	 * @param apiAddr
	 */
	public void deleteApiAddr(ApiAddr apiAddr) {
		restApiAddrWriteMapper.delete(apiAddr);
	}
}
