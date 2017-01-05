package com.gqhmt.fss.architect.account.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:28
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
public interface FssAccountReadMapper extends ReadMapper<FssAccountEntity> {
	
	
	/**
	 * 获取客户账户信息
	 * @param fssAccountEntity
	 * @return
	 */
	public List<FssAccountEntity> findCustomerAccountByParams(FssAccountEntity fssAccountEntity);

	
	
	public List<BussAndAccountBean> getBussinessAccountList(Map map);
	/**
	 *
	 * author:jhz
	 * time:2016年3月17日
	 * function：根据acc_no查询账户
	 */
	public FssAccountEntity findAccountByAccNo(@Param("accNo") String accNo);
	/**
	 *
	 * author:jhz
	 * time:2016年6月6日
	 * function：根据contractNo查询账户(busi_no)
	 */
	public FssAccountEntity findAccountByContractNo(@Param("contractNo") String contractNo);
	/**
	 *
	 * author:jhz
	 * time:2016年6月13日
	 * function：根据cust_id查询抵押权人账户（acc_type=10012002）
	 */
	public FssAccountEntity findAccountByCustId(@Param("custId") Long custId);

	/**
	 * 根据custNo查新版账户
	 * @param accNo
	 * @return
     */
	public FssAccountEntity getFssAccountByAccNo(@Param("accNo") String accNo);
	/**
	 * 根据业务编号查询账户信息
	 * @param busiNo
	 * @return
	 */
	public FssAccountEntity getAccountByBusiNo(@Param("busiNo") String busiNo,@Param("accType") Integer accType);


    List<Map> queryExcelValue(@Param("accountId") String id, @Param("startTime")String startTime, @Param("endTime")String endTime);
}
