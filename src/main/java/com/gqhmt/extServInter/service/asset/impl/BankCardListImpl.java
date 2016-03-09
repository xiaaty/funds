package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.BankCardDto;
import com.gqhmt.extServInter.dto.account.BankCardResponse;
import com.gqhmt.extServInter.service.asset.IBankCardList;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.pay.service.IFundBankCard;
import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 银行卡信息
 * @author 柯禹来
 */
@Service
public class BankCardListImpl implements IBankCardList{
	@Resource
	private IFundBankCard fundBankCardImpl;
	
    @Override
    public Response excute(SuperDto dto) throws APIExcuteErrorException {
    	BankCardResponse response = new BankCardResponse();
    	try {
    		List<BankCardInfoEntity> bankcardlist=fundBankCardImpl.getBankCardInfo((BankCardDto)dto);
    		response.setBankcardlist(bankcardlist);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
