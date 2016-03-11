package com.gqhmt.fss.architect.trade.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.trade.entity.FssRepaymentEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssRepaymentReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssRepaymentWriteMapper;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/1/10 21:29
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/1/10  于泳      1.0     1.0 Version
 */
@Service
public class FssRepaymentService {
	
	@Resource
    private FssRepaymentWriteMapper fssRepaymentWriteMapper;
	
	@Resource
	private FssRepaymentReadMapper fssRepaymentReadMapper;
	
	/**
	 * 创建借款人提现申请
	 * @param fssTradeApplyEntity
	 * @throws FssException
	 */
	public void createRepayments(List<FssRepaymentEntity> repayments) throws FssException{
		fssRepaymentWriteMapper.insertRepaymentList(repayments);
	}
	
	/**
	 * 根据商户号和流水号查询还款信息
	 * @param seqNo
	 * @param mchn
	 * @return
	 * @throws FssException
	 */
	public List<FssRepaymentEntity> searRepaymentByparam(String seqNo,String mchn) throws FssException{
		FssRepaymentEntity entity=new FssRepaymentEntity();
		entity.setSeqNo(seqNo);
		entity.setMchnChild(mchn);
		return fssRepaymentReadMapper.select(entity);
	}
	
	
}
