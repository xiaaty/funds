package com.gqhmt.sys.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gqhmt.sys.mapper.read.RestApiReadMapper;

@Service
public class RestApiService {
	
	@Resource
	private RestApiReadMapper restApiReadMapper;
	
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
}
