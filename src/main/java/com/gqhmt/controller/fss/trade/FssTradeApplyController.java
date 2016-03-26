package com.gqhmt.controller.fss.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.FssException;
import com.gqhmt.fss.architect.backplate.service.FssFssBackplateService;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.controller.fss.trade.FssTradeApplyController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/23 16:46
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/23  于泳      1.0     1.0 Version
 */
@Controller
public class FssTradeApplyController {

    @Resource
    private FssTradeApplyService fssTradeApplyService;
    @Resource
    private FssTradeRecordService fssTradeRecordService;
    @Resource
	private FssFssBackplateService fssFssBackplateService;
    @Resource
    private FssCustomerService fssCustomerService;
    /**
	 * author:柯禹来
	 * function:交易管理---交易审核--代扣审核
	 *  /trade/tradeApply/1103/11030004 出借人委托代扣
	    /trade/tradeApply/1103/11030005   冠E通还款
		/trade/tradeApply/1103/11030006   冠E通抵押权人代扣
		/trade/tradeApply/1103/11030007   代偿人代扣
		/trade/tradeApply/1103/11093001   还款代扣
		/trade/tradeApply/1103/11090001   抵押权人代扣
	   function:交易管理---交易审核--代付审核
		/trade/tradeApply/1104/11091001    借款人提现
		/trade/tradeApply/1104/11040005    冠e通放款
		/trade/tradeApply/1104/11040006   抵押权人提现
		/trade/tradeApply/1104/11040007   代偿人提现
		/trade/tradeApply/1104/11040004   委托出借人提现
	 */
    @RequestMapping(value = "/trade/tradeApply/{type}/{bus}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryMortgageeList(HttpServletRequest request, ModelMap model, FssTradeApplyEntity tradeApply, @PathVariable Integer  type,@PathVariable String bus) throws Exception {
    	Map map=new HashMap();
    	String startime=request.getParameter("startime");
		String endtime=request.getParameter("endtime");
		map.put("applyType", type);
		map.put("busiType", bus);
		if(StringUtils.isNotEmptyString(startime)){
			map.put("startime", startime+" 00:00:00");
    	}
		if(StringUtils.isNotEmptyString(endtime)){
			map.put("endtime", endtime+" 23:59:59");
		}
    	if(!"".equals(tradeApply.getAccNo())){
    		map.put("accNo", tradeApply.getAccNo());
        }
    	if(!"".equals(tradeApply.getBusinessNo())){
    		map.put("businessNo", tradeApply.getBusinessNo());
    	}
        List<FssTradeApplyEntity> tradeApplyList = fssTradeApplyService.queryFssTradeApplyList(map);
        model.addAttribute("page", tradeApplyList);
        model.addAttribute("tradeapply", tradeApply);
        model.addAttribute("startime",startime);
    	model.addAttribute("endtime",endtime);
    	if(type==1103){//充值
    		 return "fss/trade/mortgaee_list";
    	}else{//提现withdraw
    		return "fss/trade/withdraw_list";
    	}
    }
    
    /**
	 * author:柯禹来
	 * function:查看金额拆分列表信息
	 */
    @RequestMapping(value = "/trade/tradeApply/{type}/{bus}/{applyNo}/{id}/records",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryMortgageeDetail(HttpServletRequest request, ModelMap model,FssTradeApplyEntity tradeapply,FssTradeRecordEntity traderecord, @PathVariable Integer  type,@PathVariable String bus,@PathVariable String applyNo,@PathVariable Long id) throws Exception {
		Map map=new HashMap();
    	String startime=request.getParameter("startime");
		String endtime=request.getParameter("endtime");
		map.put("applyNo", applyNo);
		if(StringUtils.isNotEmptyString(startime)){
			map.put("startime", startime+" 00:00:00");
    	}
		if(StringUtils.isNotEmptyString(endtime)){
			map.put("endtime", endtime+" 23:59:59");
		}
    	if(!"".equals(traderecord.getTradeState())){
    		map.put("tradeState", traderecord.getTradeState());
        }
    	
        List<FssTradeRecordEntity> tradeRecordList = fssTradeRecordService.queryFssTradeRecordList(map);
        model.addAttribute("page", tradeRecordList);
        model.addAttribute("traderecord", traderecord);
        model.addAttribute("startime",startime);
    	model.addAttribute("endtime",endtime);
        return "fss/trade/trade_record/traderecord_list";
    }
  
    /**
     * 提现审核
     */
    @RequestMapping(value = "/trade/tradeApply/{type}/{bus}/{applyNo}/{id}/withdrawcheck",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryMortgageeDetail(HttpServletRequest request, ModelMap model,FssTradeApplyEntity tradeapply, @PathVariable Integer  type,@PathVariable String bus,@PathVariable String applyNo,@PathVariable Long id) throws Exception {
		FssTradeApplyEntity tradeapplyentity=fssTradeApplyService.getFssTradeApplyEntityById(id);
		FssCustomerEntity  fssCustomerEntity=fssCustomerService.getCustomerNameById(tradeapplyentity.getCustId());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("tradeapply",tradeapplyentity);
		model.addAttribute("bespokedate",sdf.format(tradeapplyentity.getBespokedate()));
		if(null!=fssCustomerEntity && StringUtils.isNotEmptyString(fssCustomerEntity.getName())){
			model.addAttribute("custName",fssCustomerEntity.getName());
		}else{
			model.addAttribute("custName","");
		}
		return "fss/trade/trade_audit/borrower_withdraw_check";
    }
    
	/**
	 * 提现审核(资金拆分)
	 * @param request
	 * @param model
	 * @param applyType
	 * @param busiType
	 * @return
	 * @throws FssException
	 */
//  审核不通过走回盘
//	审核通过,先进行处理，处理完成后走回盘	
	@RequestMapping("/trade/tradeApply/{applyType}/{busiType}/moneySplit")
	@ResponseBody
	public Object borrowWithDrawCheck(HttpServletRequest request, ModelMap model,@PathVariable Integer  applyType,@PathVariable String busiType) throws FssException {
		Map<String, String> map = new HashMap<String, String>();
		FssTradeApplyEntity tradeapply=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Long id=Long.valueOf(request.getParameter("id"));
		String applyStatus=request.getParameter("applyStatus");
		String bespokedate=request.getParameter("bespokedate");
		tradeapply=fssTradeApplyService.getFssTradeApplyEntityById(id);
		if(StringUtils.isNotEmptyString(applyStatus) && applyStatus.equals("4")){//通过
			try {
				tradeapply.setBespokedate(sdf.parse(bespokedate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fssTradeRecordService.moneySplit(tradeapply);//金额拆分
			fssTradeApplyService.updateTradeApply(tradeapply);//修改预约到账日期
			fssTradeRecordService.insertRecord(tradeapply, 2);
			map.put("code", "0000");
	        map.put("message", "success");
		}else{//不通过，添加回盘记录
			fssFssBackplateService.createFssBackplateEntity(tradeapply);
		}
		return map;
	}
	
}
