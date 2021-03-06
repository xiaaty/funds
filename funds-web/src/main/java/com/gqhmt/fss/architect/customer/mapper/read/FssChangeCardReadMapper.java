package com.gqhmt.fss.architect.customer.mapper.read;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.extServInter.dto.loan.ChangeCardResponse;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Filename:    com.gq.funds.dao.ChangeCardDao
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/30 16:45
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/30  于泳      1.0     1.0 Version
 */
public interface FssChangeCardReadMapper extends ReadMapper<FssChangeCardEntity> {

    public List<FssChangeCardEntity> queryChangeCardList(FssChangeCardEntity fssBankcard);
    
    
    public ChangeCardResponse getChangeCardByParam(@Param("seq_no") String seq_no,@Param("mchn") String mchn);

	/**
	 * 根据custId查询银行卡变更账户
	 * @param custId
	 * @return
	 */
	public FssChangeCardEntity selectByCustId(@Param("custId")Long custId);
	/**
	 *
	 * author:jhz
	 * time:2016年4月13日
	 * function：根据状态查询对象
	 */
	public List<FssChangeCardEntity> queryByTradeState(@Param("tradeState")int tradeState);
	/**
	 *
	 * author:jhz
	 * time:2016年4月14日
	 * function：根据custId查询银行卡变更账户
	 */
	public FssChangeCardEntity queryByChangeCardBankInfoId(@Param("bBankInfoId")Long bBankInfoId);

}
