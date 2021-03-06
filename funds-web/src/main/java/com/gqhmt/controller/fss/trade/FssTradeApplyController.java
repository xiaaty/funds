package com.gqhmt.controller.fss.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.*;
import com.gqhmt.core.util.StringUtils;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.loan.entity.FssLoanEntity;
import com.gqhmt.fss.architect.loan.service.FssLoanService;
import com.gqhmt.fss.architect.trade.bean.FssTradeApplyBean;
import com.gqhmt.fss.architect.trade.entity.*;
import com.gqhmt.fss.architect.trade.service.*;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.pay.service.trade.impl.FundsTradeImpl;
import com.gqhmt.util.ExportExcelUtil;
import org.apache.commons.lang3.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private FssBackplateService fssBackplateService;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private FssOfflineRechargeService fssOfflineRechargeService;
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FundsTradeImpl fundsTradeImpl;
    @Resource
    private FssAccountService fssAccountService;
    @Resource
    private FssBondTransferService fssBondTransferService;
	@Resource
	private FssLoanService fssLoanService;
	@Resource
	private FssTradeProcessService fssTradeProcessService;

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
		/trade/tradeApply/1104/11092001   抵押权人提现
		/trade/tradeApply/1104/11040007   代偿人提现
		/trade/tradeApply/1104/11040004   委托出借人提现
	 */
    @RequestMapping(value = "/trade/tradeApply/{type}/{bus}",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryMortgageeList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map,FssTradeApplyBean tradeApply, @PathVariable Integer  type,@PathVariable String bus) throws Exception {
    	if(map.size()==0){//默认交易状态为新增
			map.put("tradeState","10080001");
		}
    	map.put("applyType",type.toString());
		map.put("busiType", bus);
        List<FssTradeApplyBean> tradeApplyList = fssTradeApplyService.queryFssTradeApplyList(map);
        model.addAttribute("page", tradeApplyList);
        model.addAttribute("tradeapply", tradeApply);
        model.put("map", map);
    	if(type==1103){//充值
    		 return "fss/trade/mortgaee_list";
    	}else{//提现withdraw
    		return "fss/trade/withdraw_list";
    	}
    }

	/**
	 * jhz
	 * @param request
	 * @param model
	 * @param map
	 * @param tradeApply
	 * @param type
	 * @param fundType
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/trade/process/{type}/{fundType}",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryWithHoldList(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map,FssTradeApplyBean tradeApply, @PathVariable Integer  type,@PathVariable String fundType) throws Exception {
		if(map.size()==0){//默认交易状态为新增
			map.put("status","10030001");
		}
		map.put("type",type.toString());
		map.put("fundType", fundType);
		if("14010007".equals(fundType)){
			map.put("memo","借款人还款代扣");
		}
		List<TradeProcessEntity> list=fssTradeProcessService.listTradeProcess(map);
		model.addAttribute("page", list);
		model.put("map", map);
		return "/fss/trade/withHold/withHold";
	}
	/**
	 * author:jhz
	 * function:查看金额拆分列表信息
	 */
	@RequestMapping(value = "/trade/process/{id}/detail",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String queryTradeDetail(HttpServletRequest request, ModelMap model,@PathVariable Long id) throws Exception {
		// 增加数据展示
		TradeProcessEntity entity=fssTradeProcessService.findById(id);
		List<TradeProcessEntity> list=fssTradeProcessService.childTradeProcess(id);
		model.addAttribute("process", entity);
		model.addAttribute("page", list);
		return "/fss/trade/withHold/withHold_list";
	}
    /**
	 * author:柯禹来
	 * function:查看金额拆分列表信息
	 */
    @RequestMapping(value = "/trade/tradeApply/{applyNo}/records",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryTradeRecord(HttpServletRequest request, ModelMap model,FssTradeRecordEntity traderecord,@PathVariable String applyNo,@RequestParam(required = false,value = "id")String id) throws Exception {
		// 增加数据展示
		Map<String,String> map = new HashMap<String,String>();
		map.put("ApplyBeanId",id);
		List<FssTradeApplyBean> tradeApplyList = fssTradeApplyService.queryFssTradeApplyList(map);


    	List<FssTradeRecordEntity> tradeRecordList = fssTradeRecordService.queryFssTradeRecordList(applyNo,traderecord.getTradeState());
		model.addAttribute("tradeApply", tradeApplyList.get(0));
        model.addAttribute("page", tradeRecordList);
        model.addAttribute("traderecord", traderecord);
        return "fss/trade/trade_record/traderecord_list";
    }

    /**
     * 审核数据查看
     */
    @RequestMapping(value = "/trade/tradeApply/{type}/{bus}/{applyNo}/withdrawcheck",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public String queryMortgageeDetail(HttpServletRequest request, ModelMap model,FssTradeApplyEntity tradeapply, @PathVariable Integer  type,@PathVariable String bus,@PathVariable String applyNo,String token) throws Exception {

    	FssTradeApplyEntity tradeapplyentity=fssTradeApplyService.getFssTradeApplyEntityByApplyNo(applyNo);
    	if(tradeapplyentity==null){
    		throw new FssException("未查到交易申请记录！");
    	}
    	CustomerInfoEntity  customerInfo=customerInfoService.getCustomerById(tradeapplyentity.getCustId());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("tradeapply",tradeapplyentity);
		if(tradeapplyentity.getBespokedate()!=null){
			model.addAttribute("bespokedate",sdf.format(tradeapplyentity.getBespokedate()));
		}else{
			model.addAttribute("bespokedate",null);
		}
		if(null!=customerInfo && StringUtils.isNotEmptyString(customerInfo.getCustomerName())){
			model.addAttribute("custName",customerInfo.getCustomerName());
		}else{
			model.addAttribute("custName","");
		}
		if(null!=customerInfo && StringUtils.isNotEmptyString(customerInfo.getMobilePhone())){
			model.addAttribute("custMobile",customerInfo.getMobilePhone());
		}else{
			model.addAttribute("custMobile","");
		}

		if(type==1103){//充值
			return "fss/trade/trade_audit/borrower_withhold_check";
		}else{
			return "fss/trade/trade_audit/borrower_withdraw_check";
		}

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
	@RequestMapping(value = "/trade/tradeApply/{applyType}/{busiType}/{applyNo}/moneySplit")
	@ResponseBody
	public Object borrowWithDrawCheck(HttpServletRequest request, ModelMap model,@PathVariable Integer  applyType,@PathVariable String busiType,@PathVariable String applyNo,String auditAmount) throws FssException {
//		String server_token  = (String) request.getSession().getAttribute("token");
//		request.getSession().removeAttribute("token");
		Map<String, String> map = new HashMap<String, String>();
//		if(token.equals(server_token)){
		String applyStatus=request.getParameter("applyStatus");
		String bespokedate=request.getParameter("bespokedate");
		FssTradeApplyEntity tradeapply=fssTradeApplyService.getFssTradeApplyEntityByApplyNo(applyNo);
		BigDecimal audit_amount=BigDecimal.ZERO;
		if(auditAmount!=null&&!"".equals(auditAmount)){
			audit_amount=new BigDecimal(auditAmount);
		}else{
			audit_amount=tradeapply.getTradeAmount();
		}
		if(tradeapply.getTradeAmount().compareTo(audit_amount)<0){
			map.put("code", "0002");
			map.put("message", "审核金额不能大于提现金额");
			return  map;
		}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotEmptyString(applyStatus) && applyStatus.equals("4")){//通过
			try {
				if(applyType==1104){//提现
					if(StringUtils.isNotEmptyString(bespokedate)){
						tradeapply.setBespokedate(sdf.parse(bespokedate));
						int settleType=fssTradeApplyService.compare_date(bespokedate);
						tradeapply.setSettleType(settleType);
					}else{
						tradeapply.setSettleType(tradeapply.getSettleType());
					}
				}
			} catch (ParseException e) {
				LogUtil.debug(e.getClass(),e.getMessage());
				e.printStackTrace();
			}
//			fssTradeRecordService.moneySplit(tradeapply);//金额拆分
			tradeapply.setAuditAmount(audit_amount);
			fssTradeApplyService.updateTradeApply(tradeapply,"10100002","10080001");
			map.put("code", "0000");
			map.put("message", "success");
		}else{
			tradeapply.setAuditAmount(audit_amount);
			fssTradeApplyService.updateTradeApply(tradeapply,"10100005","10080010");
			//审核不通过进行资金解冻
			if(applyType==1104){
				String seqNo=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//流水号
				fundsTradeImpl.unFroze(tradeapply.getMchnChild(),tradeapply.getSeqNo(),tradeapply.getBusiType(),String.valueOf(tradeapply.getCustId()),tradeapply.getUserNo(),tradeapply.getTradeAmount(),tradeapply.getCustType(),seqNo);
			}
			//分批提现审核不通过
			if(!"".equals(tradeapply.getFormId()) && tradeapply.getFormId()!=null){
				if("11090004".equals(tradeapply.getBusiType())){
					FssLoanEntity fssLoanEntity = fssLoanService.getFssLoanEntityById(tradeapply.getFormId());
					//审核失败首次提现中改为可以重新首次提现状态
					if("10050023".equals(fssLoanEntity.getStatus())){
						fssLoanEntity.setStatus("10050009");
						fssLoanService.update(fssLoanEntity);
						//审核失败二次提现中改为可以重新首次提现状态
					}else if("10050020".equals(fssLoanEntity.getStatus())){
						fssLoanEntity.setStatus("10050022");
						fssLoanService.update(fssLoanEntity);
					}
				}
			}
			//不是分批提现的，审核不通过，添加回盘记录
			if(!"11090004".equals(tradeapply.getBusiType())) {
				fssBackplateService.createFssBackplateEntity(tradeapply.getSeqNo(), tradeapply.getMchnChild(), tradeapply.getBusiType().toString());
			}
			map.put("code", "0001");
			map.put("message", "success");
		}
		return map;
	}

	/**
	 *线下充值记录
	 * @param request
	 * @param model
	 * @param map
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = "/trade/tradeApply/offlineRecharge",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String getOfflineRechargeApply(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws Exception{
		map.put("applyType","1103");
		List<FssOfflineRechargeEntity> offlineRechargeList=fssOfflineRechargeService.queryFssOfflineRechargeList(map);
		model.addAttribute("page", offlineRechargeList);
		model.put("map", map);
		return "fss/trade/offlineRecharge_list";
	}

	/**
	 * 添加线下充值申请
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
	 */
	@RequestMapping(value = "/trade/tradeApply/createOfflineRecharge/{type}/{certNo}/{accNo}/{flag}",method = {RequestMethod.GET,RequestMethod.POST})
	public Object createOfflineRecharge(HttpServletRequest request, ModelMap model, @PathVariable Integer  type,@PathVariable String certNo,@PathVariable String accNo,@PathVariable Integer flag,CustomerInfoEntity customerInfoEntity) throws FssException {
		 customerInfoEntity=customerInfoService.queryCustomerInfoByCertNo(certNo);
		if (customerInfoEntity!=null){
			model.addAttribute("customerInfoEntity",customerInfoEntity);
		}
		Integer custType=GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(Integer.valueOf(type));
		if (null==custType) throw new FssException("91001006");
		FundAccountEntity fundAccountEntity=fundAccountService.getFundsAccount(customerInfoEntity.getId(),custType);
		model.addAttribute("amount",fundAccountEntity.getAmount());
		model.addAttribute("type",type);
		model.addAttribute("flag",flag);
		model.addAttribute("accNo",accNo);
		return "fss/trade/offlineRecharge_add";
	}

	/**
	 * 保存线下充值申请
	 * @param request
	 * @param model
	 * @return
	 * @throws FssException
	 */
	@RequestMapping(value = "/trade/tradeApply/saveOfflineRecharge",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object saveOfflineRecharge(HttpServletRequest request, ModelMap model,@ModelAttribute(value="customerInfoEntity") CustomerInfoEntity customerInfoEntity) throws FssException {
		String  tradeType=request.getParameter("tradeType");
		String type = request.getParameter("type");
		String accNo = request.getParameter("accNo");
		Integer custType=GlobalConstants.TRADE_BUSINESS_TYPE__MAPPING.get(Integer.valueOf(type));
		if (null==custType) throw new FssException("91001006");
		BigDecimal  amt=new BigDecimal(request.getParameter("amt"));
		String custNo="";
		FssAccountEntity fssAccountEntity=fssAccountService.getAccountByAccNo(accNo);
		if(null!=fssAccountEntity){
			custNo=fssAccountEntity.getCustNo();
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			if("11030014".equals(tradeType)){//委托充值(账户直接充值)
				fssTradeApplyService.whithholdingApply(custNo,accNo,tradeType,amt,null, CommonUtil.getSeqNo(),customerInfoEntity.getId(),custType,null,null,null,false);
			}else if("11040012".equals(tradeType)){//账户直接提现(账户类型)
				fssTradeApplyService.whithdrawApply(custNo,accNo,tradeType,amt,null,CommonUtil.getSeqNo(),customerInfoEntity.getId(),custType,null,null,null,1 );
			}else if("11030015".equals(tradeType)){//线下充值
				fundsTradeImpl.OfflineRechargeApply(null,CommonUtil.getSeqNo(),tradeType,String.valueOf(customerInfoEntity.getId()),String.valueOf(custType),null,amt);
			}
			map.put("code", "0000");
			map.put("message", "success");
		} catch (FssException e) {//保存失败
			String resp_msg = Application.getInstance().getDictName(e.getMessage());
			map.put("code", e.getMessage());
			map.put("message", resp_msg);
		}
		return map;
	}
	/**
	 * 批量体现
	 * @param request
	 * @param model
	 * @throws FssException
	 */
//	审核通过,先进行处理，处理完成后走回盘
	@RequestMapping(value = "/trade/tradeApply/moneySplit")
	@ResponseBody
	public Object WithDrawCheck(HttpServletRequest request, ModelMap model, String no,Integer bespokeDate) throws FssException {
		Map<String, String> map = new HashMap<String, String>();
		int count=1;
		if(bespokeDate==null){
			bespokeDate=2;
		}
		if(StringUtils.isNotEmptyString(no)){
			 count=fssTradeApplyService.withNumbers(no,bespokeDate);
		}
		if(count==0){
			map.put("code", "0000");
			map.put("message", "success");
		}else{
			map.put("code", "0001");
			map.put("message", "共有"+count+"条数据失败，其不符合代扣或提现状态");
		}

		return map;
	}

	/**
	 * 债权转让数据
	 * @param request
	 * @param model
	 * @param map
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = "/trade/tradeApply/bondTransfer",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String getBondTransfer(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws Exception{
		List<FssBondTransferEntity> bondList=fssBondTransferService.queryBondTransferList(map);
		model.addAttribute("page", bondList);
		model.put("map", map);
		return "fss/trade/bondTransfer_list";
	}

	/**
	 * 转账申请
	 * @param request
	 * @param model
	 * @param busiType
	 * @param customerName
	 * @param mobilePhone
	 * @param flag
     * @return
     * @throws FssException
     */
	@RequestMapping(value = "/trade/tradeApply/createTransfer/{custId}/{busiType}/{customerName}/{mobilePhone}/{flag}/{accCustId}",method = {RequestMethod.GET,RequestMethod.POST})
	public Object createTransferApply(HttpServletRequest request, ModelMap model, @PathVariable String custId,@PathVariable Integer busiType,@PathVariable String customerName,@PathVariable String mobilePhone,@PathVariable Integer flag,@PathVariable String accCustId) throws FssException {
		model.addAttribute("busiType",busiType);
		model.addAttribute("custId",custId);
		model.addAttribute("customerName",customerName);
		model.addAttribute("mobilePhone",mobilePhone);
		model.addAttribute("flag",flag);
		model.addAttribute("accCustId",accCustId);//用来区分是对公账户还是旧版客户账户
		return "fss/trade/transfer_add";
	}

	/**
	 * 旧版账户客户转账
	 * @param request
	 * @param model
	 * @param mobilePhone
	 * @param busiType
	 * @param flag
	 * @return
     * @throws FssException
     */
	@RequestMapping(value ="/trade/tradeApply/transfer/{custId}/{mobilePhone}/{busiType}/{flag}",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object saveOfflineRecharge(HttpServletRequest request, ModelMap model,@PathVariable String custId,@PathVariable String mobilePhone,@PathVariable Integer busiType,@PathVariable Integer flag) throws FssException {
		Map<String, String> map = new HashMap<String, String>();
		String accType = request.getParameter("accType");
		String tradeType=request.getParameter("tradeType");
		String phone = request.getParameter("phone");
		BigDecimal  amt=new BigDecimal(request.getParameter("amt"));//转账金额
		String from_cust_no=null;
		String to_cust_no=null;
		Integer from_cust_type=null;
		Integer to_cust_type=null;
		try {
			FundAccountEntity accEntity= fundAccountService.getFundAccount(phone,Integer.valueOf(accType));
			if(accEntity==null) throw new FssException("90002007");
			if(flag==4){//4转账转入
				from_cust_no=String.valueOf(accEntity.getCustId());
				from_cust_type=Integer.valueOf(accType);
				to_cust_no=custId;
				to_cust_type=busiType;
			}else{//转账转出
				from_cust_no=custId;
				from_cust_type=busiType;
				to_cust_no=String.valueOf(accEntity.getCustId());
				to_cust_type=Integer.valueOf(accType);
			}
				fundsTradeImpl.bondTransfer(null,null,tradeType,null,null,null,from_cust_no,null,amt,null,to_cust_no,null,from_cust_type,to_cust_type,1005,3,"0");
				map.put("code", "0000");
				map.put("message", "success");
		}catch (FssException e){
			String resp_msg = Application.getInstance().getDictName(e.getMessage());
			map.put("code", e.getMessage());
			map.put("message", resp_msg);
		}
		return map;
	}

	/**
	 * 导出excle
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/trade/tradeApply/{type}/{bus}/exportExcel",method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody void exportExcel(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map, FssTradeApplyBean tradeApply, @PathVariable Integer  type, @PathVariable String bus) throws Exception {
		Map<String, String> maps = new HashMap<String, String>();
		String mapStr = map.get("map");
        if(!StringUtils.isEmpty(mapStr)){
            maps = StringUtils.transStringToMap(mapStr);
		}

		maps.put("applyType",type.toString());
		maps.put("busiType", bus);

		List<FssTradeApplyBean> tradeApplyList = fssTradeApplyService.queryFssTradeApplyList(maps);

		fssTradeApplyService.exportTradeApplyList(tradeApplyList);

	}

	/**
	 * 导出 newExcle 表
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/trade/tradeApply/{type}/{bus}/exportLoanExcel",method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody void exportLoanExcel(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map, FssTradeApplyBean tradeApply, @PathVariable Integer  type, @PathVariable String bus) throws Exception {
		Map<String, String> maps = new HashMap<String, String>();
		String mapStr = map.get("map");
		if(!StringUtils.isEmpty(mapStr)){
			maps = StringUtils.transStringToMap(mapStr);
		}

		maps.put("applyType",type.toString());
		maps.put("busiType", bus);

		List<FssTradeApplyBean> tradeApplyList = fssTradeApplyService.queryFssTradeApplyList(maps);

		ExportExcelUtil<FssTradeApplyBean> exp = new ExportExcelUtil<>();
		String[] headers = {"业务编号","申请单号","客户姓名","客户电话","交易金额","合同金额","准时还款保证金","咨询服务费","风险备用金","创建时间","修改时间"};
		String[] properties = {"businessNo","applyNo","custName","custMobile","tradeAmount","contractAmt","riskSeserveFund","paymentDeposit","consultingServices","createTime","modifyTime"};
		exp.exportExcel("appleList",headers,tradeApplyList,properties);
	}

	/**
	 * pos充值记录
	 * @param request
	 * @param model
	 * @param map
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = "/trade/tradeApply/posRechargeRecord",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String getPosRecharge(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws Exception{
		map.put("applyType","1001");
		List<FssOfflineRechargeEntity> offlineRechargeList=fssOfflineRechargeService.queryFssOfflineRechargeList(map);
		model.addAttribute("page", offlineRechargeList);
		model.put("map", map);
		return "fss/trade/posRecharge_list";
	}

	/**
	 * pos签约记录
	 * @param request
	 * @param model
	 * @param map
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = "/trade/tradeApply/posSignedRecord",method = {RequestMethod.GET,RequestMethod.POST})
	@AutoPage
	public String getPosSigned(HttpServletRequest request, ModelMap model,@RequestParam Map<String, String> map) throws Exception{
		map.put("applyType","1102");
		List<FssOfflineRechargeEntity> offlineRechargeList=fssOfflineRechargeService.queryFssOfflineRechargeList(map);
		model.addAttribute("page", offlineRechargeList);
		model.put("map", map);
		return "fss/trade/posSigned_list";
	}

}
