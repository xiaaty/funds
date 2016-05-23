package com.gqhmt.fss.architect.merchant.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.merchant.bean.BusinessAndApi;
import com.gqhmt.fss.architect.merchant.entity.MerchantApiEntity;
import com.gqhmt.fss.architect.merchant.entity.MerchantEntity;


public interface MerchantReadMapper extends ReadMapper<MerchantEntity> {
	
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
	public List<MerchantEntity> findBusinessList(Map<String, Object> map);
	/**
	 * 
	 * author:jhz
	 * time:2016年2月3日
	 * function：查询商户api列表
	 */
	public List<BusinessAndApi> findBusinessAndApiList(String mchnNo);
	/**
	 * 
	 * author:jhz
	 * time:2016年2月3日
	 * function：删除原有的apiUrl
	 */
	public void deleteApiUrl(String mchnNo);

	/**
	 * 
	 * author:jhz
	 * time:2016年2月3日
	 * function：删除原有的apiUrl
	 */
	public void deleteApi(String mchnNo);
	/**
	 * 
	 * author:jhz
	 * time:2016年2月15日
	 * function：父商户列表
	 * @param merchantEntity
	 */
	public List<MerchantEntity> getParentBusiness(MerchantEntity merchantEntity);
	/**
	 * 
	 * author:jhz
	 * time:2016年2月15日
	 * function：子商户列表
	 */
	public List<MerchantEntity> getChildBusiness(MerchantEntity merchantEntity);
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：通过商户号查询商户对象
	 * @param mchnNo 
	 */
	public MerchantEntity selectByMchnNo(String mchnNo);
	
}
