package com.gqhmt.fss.architect.merchant.mapper.read;

import java.util.List;

import com.gqhmt.core.mybatis.ReadAndWriteMapper;
import com.gqhmt.fss.architect.merchant.bean.BusinessApiBean;
import com.gqhmt.fss.architect.merchant.entity.BusinessApi;

/**
 * 商户api地址
 * @author fanfever
 *
 */
public interface RestBusinessApiReadMapper extends ReadAndWriteMapper<BusinessApi> {
	  /**
	    * 
	    * author:jhz
	    * time:2016年2月19日
	    * function：API录入列表
	    */
	public List<BusinessApiBean> findBusinessApiList(BusinessApiBean businessApiBean);
	  /**
     * 
     * author:jhz
     * time:2016年2月19日
     * function：根据ID查询商户API授权
	 * @return 
     */
	public BusinessApiBean selectBusinessApi(Long id);



}