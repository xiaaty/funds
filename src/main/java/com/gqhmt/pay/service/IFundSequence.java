package com.gqhmt.pay.service;

import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.asset.FundSequenceDto;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import java.util.List;

public interface IFundSequence {

	/**
	 * 查询资金流水
	 * @param tradrecord
	 * @return
	 */
	public List<FundFlowBean> queryFundSequence(FundSequenceDto fundSeqDto) throws FssException;
	
}
