package com.gqhmt.extServInter.service.loan.impl;

import com.beust.jcommander.internal.Lists;
import com.gqhmt.annotations.APISignature;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.RepaymentChildDto;
import com.gqhmt.extServInter.dto.loan.RepaymentDto;
import com.gqhmt.extServInter.service.loan.IRepayment;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.service.FssRepaymentService;
import com.gqhmt.fss.architect.trade.service.FssTradeProcessService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 还款划扣
 * @author 柯禹来
 */
@Service
public class RepaymentImpl implements IRepayment{
	@Resource
	private FssTradeProcessService fssTradeProcessService;
	@Resource
	private FundAccountService fundAccountService;
	@Resource
	private FssRepaymentService fssRepaymentService;
	
    @Override
	@APITradeTypeValid(value = "11093001,11093002")
	@APISignature
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
    	Response response = new Response();
    	try {
			RepaymentDto rDto=(RepaymentDto)dto;
			RepaymentChildDto child=rDto.getRepay_list().get(0);
			//得到入账账户
			FundAccountEntity toAcc=fundAccountService.getFundsAccount(Long.valueOf(child.getAcc_no()), GlobalConstants.ACCOUNT_TYPE_LOAN);
			//创建主流程
			TradeProcessEntity entity=fssTradeProcessService.general(rDto.getSeq_no(),rDto.getMchn(),rDto.getTrade_type(),null,toAcc,true);
			entity=fssTradeProcessService.creatTradeProcess(entity,null,"借款人还款代扣，代扣金额为"+child.getAmt()+"元",child.getAmt(),"1401","14010007","11160002","11170001",child.getSerial_number(),child.getWithHold_type(),child.getContract_no());
			List<TradeProcessEntity> list= Lists.newArrayList();
			//判断是否中间人代扣
			//是中间人代扣
			if("10180002".equals(child.getWithHold_type())){
				//得到中间人出账账户
				FundAccountEntity midAcc=fundAccountService.getFundsAccount(Long.valueOf(child.getMid_cust_id()), GlobalConstants.ACCOUNT_TYPE_LOAN);
				//创建代扣子订单
				TradeProcessEntity withHoldProcess= fssTradeProcessService.general(rDto.getSeq_no(),rDto.getMchn(),rDto.getTrade_type(),null,midAcc,true);
				withHoldProcess=fssTradeProcessService.creatTradeProcess(withHoldProcess,null,"借款人还款代扣，中间人代扣金额为"+child.getAmt()+"元",child.getAmt(),"1401","14010007","11160002","11170001",child.getSerial_number(),child.getWithHold_type(),child.getContract_no());
				list.add(withHoldProcess);
				//创建转账子订单
				TradeProcessEntity transferProcess= fssTradeProcessService.general(rDto.getSeq_no(),rDto.getMchn(),rDto.getTrade_type(),midAcc,toAcc,true);
				transferProcess=fssTradeProcessService.creatTradeProcess(transferProcess,null,"中间人转账给借款人，转账金额为"+child.getAmt()+"元",child.getAmt(),"1403","14030013","11160001","11170001",child.getSerial_number(),child.getWithHold_type(),child.getContract_no());
				list.add(transferProcess);
			}else{
				//创建代扣子订单
				TradeProcessEntity withHoldProcess= fssTradeProcessService.general(rDto.getSeq_no(),rDto.getMchn(),rDto.getTrade_type(),null,toAcc,true);
				withHoldProcess=fssTradeProcessService.creatTradeProcess(withHoldProcess,null,"借款人还款代扣，代扣金额为"+child.getAmt()+"元",child.getAmt(),"1401","14010007","11160002","11170001",child.getSerial_number(),child.getWithHold_type(),child.getContract_no());
				list.add(withHoldProcess);
			}
    		entity.setParnetId(0l);
			entity.setList(list);
			fssTradeProcessService.saveTradeProcess(entity);
//			response=fssRepaymentService.createRefundDraw(rDto);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.info(this.getClass(), e.getMessage());
	    	response.setResp_code(e.getMessage());
		}
    	return response;
    }
}
