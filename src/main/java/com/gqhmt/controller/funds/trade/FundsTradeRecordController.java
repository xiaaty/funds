package com.gqhmt.controller.funds.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.trade.bean.FundFlowBean;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.entity.FssTransRecordEntity;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.pay.service.TradeRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* <h1>交易管理-交易记录</h1>
* <p>充值记录
* <p>提现记录
* <p>转账交易记录
* @author 柯禹来
* @version 1.0
* @createTime:2016-02-18 
*/
@Controller
public class FundsTradeRecordController {
	
	@Resource
	private TradeRecordService tradeRecordService;
	@Resource
	private FundSequenceService fundSequenceService;
	
	/**
	 * author:柯禹来
	 * function:交易记录
	 */
	@RequestMapping(value = "/trade/record/{type}",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryWithdrawList(HttpServletRequest request,ModelMap model,FssTradeRecordEntity traderecorder,@PathVariable Integer  type) throws Exception {
		traderecorder.setTradeType(type);//充值(1103),提现(1104)
		if(traderecorder.getAccNo()!=null && !"".equals(traderecorder.getAccNo())){
			traderecorder.setAccNo(traderecorder.getAccNo());
		}else{
			traderecorder.setAccNo(null);
		}
		if(traderecorder.getTradeState()!=null && !"".equals(traderecorder.getTradeState())){
			traderecorder.setTradeState(traderecorder.getTradeState());
		}
		if(traderecorder.getTradeResult()!=null && !"".equals(traderecorder.getTradeResult())){
			traderecorder.setTradeState(traderecorder.getTradeResult());
		}
		List<FssTradeRecordEntity> traderecorderlist = tradeRecordService.queryRechargeList(traderecorder);
		model.addAttribute("page", traderecorderlist);
		model.addAttribute("traderecorder", traderecorder);
		if(type==1103){//充值
			return "fss/trade/trade_record/recharge_list";
		}else{//提现
			return "fss/trade/trade_record/withdraw_list";
		}
	}
	
	/**
	 * author:柯禹来
	 * function:转账交易记录
	 */
	@RequestMapping(value = "/trade/transferlist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryRepaymentDetail(HttpServletRequest request,ModelMap model,FssTransRecordEntity transrecord) throws Exception {
		if(transrecord!=null && !"".equals(transrecord.getAccNo())){
			transrecord.setAccNo(transrecord.getAccNo());
		}else{
			transrecord.setAccNo(null);
		}
		List<FssTransRecordEntity> transrecorderlist = tradeRecordService.queryTransRecordList(transrecord);
		model.addAttribute("page", transrecorderlist);
		model.addAttribute("transrecord", transrecord);
		return "fss/trade/trade_record/transfer_list";
	}
	
	/**
	 * author:柯禹来
	 * function:资金流水
	 */
	@RequestMapping(value = "/trade/fundsflowlist",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryFundsFlowList(HttpServletRequest request,ModelMap model,FundFlowBean fundflow) throws Exception {
		if(fundflow!=null && !"".equals(fundflow.getAccountNo())){
			fundflow.setAccountNo(fundflow.getAccountNo());
		}
		List<FundFlowBean> fundflowlist = fundSequenceService.queryFundFlowBean(fundflow);
		model.addAttribute("page", fundflowlist);
		model.addAttribute("fundflow", fundflow);
		return "fss/trade/trade_record/fundsflow_list";
	}
	
	
	
	
	
}
