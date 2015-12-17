package com.gqhmt.sys.mapper.read;

import java.util.List;
import java.util.Map;


public interface RestApiReadMapper{
	
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
}
