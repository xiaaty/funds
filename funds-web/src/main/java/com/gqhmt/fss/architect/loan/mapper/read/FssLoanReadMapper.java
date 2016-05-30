package com.gqhmt.fss.architect.loan.mapper.read;

import com.gqhmt.core.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.extServInter.dto.loan.FailedBidResponse;
import com.gqhmt.extServInter.dto.loan.LendingResponse;
import com.gqhmt.extServInter.dto.loan.MortgageeWithDrawRespons;
import com.gqhmt.extServInter.dto.p2p.BidApplyResponse;
import com.gqhmt.fss.architect.loan.bean.FssLoanBean;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月7日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月7日  jhz      1.0     1.0 Version
 */
public interface FssLoanReadMapper extends ReadMapper<FssLoanEntity> {
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：得到借款人放款回调对象
	 */
	FssLoanEntity getResponse(Map<String, String> map) throws FssException;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月7日
	 * function：抵押权人提现回盘
	 */
	MortgageeWithDrawRespons getMortgageeWithDrawRespons(Map<String, String> map)throws FssException;
	/**
	 * 
	 * author:jhz
	 * time:2016年3月8日
	 * function：流标回盘
	 */
	FailedBidResponse getFailedBidResponse(Map<String, String> map)throws FssException;
	
	/**
	 * 
	 * author:jhz
	 * time:2016年3月11日
	 * function：抵押权人付款列表
	 */
	public List<FssLoanEntity> findBorrowerLoan(Map map);

	/**
	 * 获取需要执行满标操作的数据
	 * @return
     */
	public List<FssLoanEntity> findLoanBySettle();

	/**
	 * 获取回款列表
	 * @return
     */
	public List<FssLoanEntity> findLoanRepayment();
	/**
	 * 获取回款列表
	 * @return
	 */
	public List<FssLoanEntity> findAbortBid();
	/**
	 * 
	 * author:jhz
	 * time:2016年4月8日
	 * function：根据交易类型得到放款列表
	 */
	List<FssLoanEntity> findByType(String type);
	/**
	 *
	 * author:jhz
	 * time:2016年5月27日
	 * function：交易类型.标的Id查询满标信息是否存在
	 */
	FssLoanEntity getFssLoanEntityByBidBusiNo(@Param("tradeType") String tradeType,@Param("busiBidNo") String busiBidNo);
	/**
	 *
	 * author:jhz
	 * time:2016年5月30日
	 * function：（回款编号，交易类型，回款类型）查询回款信息是否存在
	 */
	FssLoanEntity getLoanRepayment(@Param("repayNo") String repayNo,@Param("tradeType") String tradeType,@Param("repayType") String repayType);

//    public List<FssLoanBean> findBorrowerLoanOffline(@Param("type") String type);	
    public List<FssLoanBean> findBorrowerLoanOffline();
	/**
	 * 
	 * author:jhz
	 * time:2016年4月25日
	 * function：得到冠e通回调对象
	 */
	BidApplyResponse getBidApplyResponse(Map<String, String> map);	
	
}
