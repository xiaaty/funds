package com.gqhmt.fss.architect.merchant.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.merchant.entity.Business;


public interface RestApiReadMapper extends ReadMapper<Business> {
	
	/**
	 * 根据商户标识获取授权类型
	 * @param busiCode
	 * @return 授权类型
	 */
	public Map<String, Object> getAuthTypeByCode(String busiCode);

	/**
	 * 获取商户ip白名单
	 * @param busiCode
	 * @return
	 */
	public List<Map<String, String>> getIpAddrListByCode(String busiCode);

	/**
	 * 获取api列表
	 * @param busiCode
	 * @return
	 */
	public List<Map<String, String>> getApiListByCode(String busiCode);

	/**
	 * 获取商户列表
	 * @param pid
	 * @return
	 */
	public List<Business> findBusinessList(Map<String, Object> map);
}
