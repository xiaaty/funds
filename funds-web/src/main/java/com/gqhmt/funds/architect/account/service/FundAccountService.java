package com.gqhmt.funds.architect.account.service;


import com.github.pagehelper.Page;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.loan.LoanWithDrawApplyDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.fss.architect.asset.mapper.read.FssAssetReadMapper;
import com.gqhmt.fss.architect.trade.entity.FssTradeApplyEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.funds.architect.account.bean.FundAccountCustomerBean;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.exception.NeedSMSValidException;
import com.gqhmt.funds.architect.account.mapper.read.FundsAccountReadMapper;
import com.gqhmt.funds.architect.account.mapper.write.FundsAccountWriteMapper;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.util.LogUtil;
import com.gqhmt.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Filename:    com.gq.p2p.account.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 16:07
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
@Service
public class FundAccountService {

    @Resource
    private FundsAccountReadMapper fundsAccountReadMapper;
    @Resource
    private FundsAccountWriteMapper fundAccountWriteMapper;

    @Resource
    private FundSequenceService fundSequenceService;

    @Resource
    private BankCardInfoService bankCardInfoService;
    
    @Resource
    private  FssAssetReadMapper assetReadMapper;
    
    @Resource
    private FssAccountReadMapper fssAccountReadMapper;

    @Resource
	private FssTradeApplyService fssTradeApplyService;

    @Resource
	private IFundsTrade iFundsTrade;

    public void update(FundAccountEntity entity) {
    	fundAccountWriteMapper.updateByPrimaryKeySelective(entity);
	}
    public void delete(Long id) {
    	fundAccountWriteMapper.deleteByPrimaryKey(id);
    }


    /**
     * 创建账户
     */
    public FundAccountEntity createAccount(CustomerInfoEntity customerInfoEntity, Integer userID) throws FssException {
        //创建主账户
        try {
            FundAccountEntity entity = this.createCustomerAccount(customerInfoEntity, userID);
            //创建子账户
            this.createCustomerAccount(customerInfoEntity, userID, entity.getId());

            return entity;
        }catch (Exception e){

            String  msg = "90002002";
            if(e.getMessage() != null && e.getMessage().contains("uk_cus_id_type")){
                msg = "90002001";
            }
            throw new FssException(msg,e);
        }

    }
    /**
     * 创建客户主账户
     * @param customerInfoEntity
     * @param userID
     * @throws FssException
     */
    private FundAccountEntity createCustomerAccount(CustomerInfoEntity customerInfoEntity, Integer userID) throws FssException {
        FundAccountEntity entity = getFundAccount(customerInfoEntity,userID,1, GlobalConstants.ACCOUNT_TYPE_PRIMARY);

        try {
			this.fundAccountWriteMapper.insert(entity);
		} catch (Exception e) {
			LogUtil.debug(this.getClass(),entity+":"+entity.getId());
			throw new FssException("91004013");
		}
        return entity;
    }
    /**
     * 创建客户子账户
     * @param customerInfoEntity
     * @param userID
     * @throws FssException
     */
    private void createCustomerAccount(CustomerInfoEntity customerInfoEntity,Integer userID,Long pID) throws FssException {

        List<FundAccountEntity> insertList = new ArrayList<>();

        /*update(entity);*/

        Set<Integer> typeSet = GlobalConstants.accountType.keySet();
        for (int type : typeSet) {
            if (type == 0 || (customerInfoEntity.getId() < GlobalConstants.RESERVED_CUSTOMERID_LIMIT)) {
                continue;
            }

            FundAccountEntity entity = getFundAccount(customerInfoEntity,userID,1,type);
            entity.setParentId(pID);

            insertList.add(entity);

        }

        this.fundAccountWriteMapper.insertList(insertList);
        LogUtil.debug(this.getClass(), insertList);

    }

    private FundAccountEntity getFundAccount(CustomerInfoEntity customerInfoEntity,Integer userID,Integer accountType,Integer busiType){
        FundAccountEntity entity = new FundAccountEntity();
        entity.setCustId(customerInfoEntity.getId());
        entity.setUserName(customerInfoEntity.getMobilePhone());
        entity.setAmount(BigDecimal.ZERO);
        entity.setFreezeAmount(BigDecimal.ZERO);
        entity.setAccountType(accountType);
        entity.setBusiType(busiType);
        entity.setUserId(userID);
        entity.setAccountNo(getAccountNo());
        entity.setBankNo(customerInfoEntity.getBankNo());
        entity.setCityId(customerInfoEntity.getCityCode());
        entity.setParentBankId(customerInfoEntity.getParentBankCode());
        entity.setCustName(customerInfoEntity.getCustomerName());
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setCreateUserId(1);
        entity.setModifyUserId(1);
        return entity;
    }
    
    /**
     * 根据条件查询返回所有资金账户列表
     * @param fundAccountEntity
     * @return
     * @throws FssException
     */
    public List<FundAccountEntity> queryFundsAccountList(FundAccountEntity fundAccountEntity) throws FssException{
    	return fundsAccountReadMapper.queryFundsAccountList(fundAccountEntity);
    }
    /**
     * 根据条件查询返回所有资金账户列表
     * @param fundsAcctBean
     * @return
     * @throws FssException
     */
    public Page queryBusinessFundsAccountList(FundsAccountBean fundsAcctBean) throws FssException{
        return fundsAccountReadMapper.queryBusinessFundsAccountList(fundsAcctBean);
    }


   /**
    * 
    * author:jhz
    * time:2016年2月22日
    * function：通过custId,BUSI_TYPE得到账户
    */
    public FundAccountEntity getFundAccount(Long cusID, Integer type){
        return this.fundsAccountReadMapper.queryFundAccountByCutId(cusID, type);
    }

    /**
     * 获取账户
     * @param userName 第三方账户名
     * @param type  账户类型
     * @return
     */
    public FundAccountEntity getFundAccount(String userName,int type){
        return this.fundsAccountReadMapper.queryFundAccountByUserName(userName, type);
    }

    public String getAccountNo(){
        Date date = new Date();
        String year  = String.format("%tY",date);
        String month = String.format("%tm",date);
        String dateString = String.format("%td",date);
        Double d = Math.random();
        d = d*10000000000L;
        d.longValue();
        return "gq_"+year+month+dateString+String.format("%010d",d.longValue());
    }

    /**
     * 根据编号查询返回对象
     * @param acctId
     * @return
     */
    public FundAccountEntity getFundAccountInfo(Long acctId){
        return fundsAccountReadMapper.selectByPrimaryKey(acctId);
    }

    /**
     * 根据条件查询返回所有借款客户账户列表
     * @param fundsAcctBean
     * @return
     * @throws FssException
     */
    public Page queryLoanFundsAccountList(FundsAccountBean fundsAcctBean) throws FssException{
        return fundsAccountReadMapper.queryLoanFundsAccountList(fundsAcctBean);
    }
    
    
    /**
     * 借款账户充值
     * @param request
     * @param customId
     * @param pwd
     * @param amount
     * @param bankId
     * @return
     */
    public  Object recharge(HttpServletRequest request, int customId,String pwd,String amount,Integer bankId) throws NeedSMSValidException{
    	
		Map<String,String> map = new HashMap<String,String>();
		if(null == amount){
			map.put("code","101");
			map.put("msg","请输入充值金额！");
			return map;
		}
		if(new BigDecimal(amount).compareTo(BigDecimal.ZERO) != 1){
			map.put("code","105");
			map.put("msg","充值金额必须大于0！");
			return map;
		}
		if(pwd == null || "".equals(pwd)){
			map.put("code","102");
			map.put("msg","请输入交易密码！");
			return map;
		}

		if(null == bankId){
			map.put("code","107");
			map.put("msg","请选择银行卡，再进行充值。");
			return map;
		}
		
        String password = pwd;
        String code = "0000";
        String msg = "账户充值成功";
        String orderNo = "";

        BankCardInfoEntity bankCardinfoEntity  = bankCardInfoService.queryBankCardinfoById(bankId);
        String cardIndex = bankCardinfoEntity.getCardIndex();
        try{
//            AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_CHARGE,ThirdPartyType.DAQIAN,customId,1,new BigDecimal(amount),password,cardIndex);
        }catch (CommandParmException e){
            code = "0001";
            msg = e.getMessage();
        }
        map.put("code",code);
        map.put("msg",msg);
        map.put("orderNo",orderNo);
        return map;
    }
    
    /**
     * 借款账户提现
     * @param request
     * @param customId
     * @param bankId
     * @param amount
     * @param pwd
     * @return
     */
    public Object withdraw(HttpServletRequest request, int customId,Integer bankId,String amount,String pwd){
    	
		Map<String,String> map = new HashMap<String,String>();
		if(null == amount){
			map.put("code","101");
			map.put("msg","请输入提现金额！");
			return map;
		}
		if(new BigDecimal(amount).compareTo(BigDecimal.ZERO) != 1){
			map.put("code","101");
			map.put("msg","提现金额必须大于0！");
			return map;
		}
		if(null == pwd){
			map.put("code","101");
			map.put("msg","请输入交易密码！");
			return map;
		}

		if(null == bankId){
			map.put("code","101");
			map.put("msg","请选择银行卡，再进行提现。");
			return map;
		}

        String code = "0000";
        String msg = "账户提现已申请";
        String orderNo = "";
        try{
//            AccountCommand.payCommand.command(CommandEnum.FundsCommand.FUNDS_WITHDRAW,ThirdPartyType.DAQIAN,customId,1,pwd,new BigDecimal(amount),bankId);
        }catch (CommandParmException e){
            code = "0001";
            msg = e.getMessage();
        }
        map.put("code", code);
        map.put("msg",msg);
        map.put("orderNo",orderNo);
        return map;
    }
    
    
    /**
     * 根据客户id更新客户名字 add by guofu
     * @param cusID
     * @param custName
     * @return
     */
    public void updateBycustId(Integer cusID,String custName) throws FssException {
    	this.fundAccountWriteMapper.updateCustName(cusID, custName);
    }
    
    

   /**
    * 
    * author:jhz
    * time:2016年2月16日
    * function：funds账号管理
    */
   	public List<FundAccountCustomerBean> findAcountList(Map<String,String> map) {
	   // TODO Auto-generated method stub
		Map<String, String> map2=new HashMap<String, String>();
		map2.putAll(map);
   		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("customerName",map.get("customerName"));
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
	   return fundsAccountReadMapper.findAcountList(map2);
   	}
   	
    /**
     * 得到账户资产信息
     * @return
     */
   	public FssAssetEntity getAccountAsset(String cust_no,String user_no,String acc_no){
   		Map map=new HashMap();
   		if(StringUtils.isNotEmptyString(cust_no)){
   			map.put("cust_no", cust_no);
   		}
   		if(StringUtils.isNotEmptyString(user_no)){
   			map.put("user_no", user_no);
   		}
   		if(StringUtils.isNotEmptyString(acc_no)){
   			map.put("acc_no", acc_no);
   		}
   		return assetReadMapper.getAccountAssets(map);
   	}
    

    /**
	  * 
	  * author:jhz
	  * time:2016年2月18日
	  * function：找到指定的客户
	  */
	public FundAccountCustomerBean fundAccountCustomerById(Integer withHoldId) {
		// TODO Auto-generated method stub
		return fundsAccountReadMapper.fundAccountCustomerById(withHoldId);
	}
	/**
	 * 费用接口
	 * @param id
	 * @param totalAmaount
	 * @return
	 * @throws FssException
	 */
	public boolean savetoAccount(Long id,BigDecimal totalAmaount) throws FssException{
		Map map=new HashMap();
		map.put("id", id);
		map.put("totalAmaount", totalAmaount);
		fundAccountWriteMapper.updateAndSaveAccount(map);
		return true;
	}
	
	/**
	 * 获取其他账户信息
	 * @param cusID
	 * @param type
	 * @return
	 */
	public FundAccountEntity getFundsAccount(Long cusID, int type) throws FssException {
		FundAccountEntity fundaccount = null;
		if (cusID < 100) {
			fundaccount = fundsAccountReadMapper.queryFundAccountByCutId(cusID, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		} else {
			fundaccount = fundsAccountReadMapper.queryFundAccountByCutId(cusID, type);
		}
		if (fundaccount == null) {
			throw new FssException("" + GlobalConstants.accountType.get(type) + "不存在");
		}
		return fundaccount;
	}
	
	   /**
	    * 
	    * author:柯禹来
	    * time:2016年2月22日
	    * function 查询账户余额
	    */
	    public FundAccountEntity getAccountBanlance(Long cust_no, int busi_type){
	        return this.fundsAccountReadMapper.getAccountBanlance(cust_no,busi_type);
	    }
	    
		/**
		 * 借款人提现
		 */
		public boolean createWithDrawApply(LoanWithDrawApplyDto wthDrawApplyDto) throws FssException {
			FssTradeApplyEntity fssTradeApplyEntity=null;
			//1.根据acc_no查询借款人账户信息
		 	FssAccountEntity fssAccountEntity= this.getFssFundAccountInfo(wthDrawApplyDto.getAcc_no());
		 	if(fssAccountEntity==null){
		 		throw new FssException("90004006");
		 	}else{
				//账户余额小于提现金额
				FundAccountEntity fundAccountEntity=getFundsAccount(fssAccountEntity.getCustId(),GlobalConstants.ACCOUNT_TYPE_LOAN);
				if(fundAccountEntity.getAmount().compareTo(wthDrawApplyDto.getPay_amt())<0) throw new FssException("90004007");
				//冻结资金
				iFundsTrade.froze(fssAccountEntity.getCustId(),GlobalConstants.ACCOUNT_TYPE_LOAN,wthDrawApplyDto.getPay_amt());
	 			try {
					//创建提现申请信息
					fssTradeApplyEntity = fssTradeApplyService.createTradeApplyEntity(fssAccountEntity,wthDrawApplyDto);
					fssTradeApplyService.createTradeApply(fssTradeApplyEntity);
				} catch (FssException e) {
					LogUtil.info(this.getClass(), e.getMessage());
					throw new FssException("90099005");
				}
	 		
		 	}
		 	return true;
		}
	    /**
	     * 根据accNo查询账户
	     * @param accNo
	     * @return
	     * @throws FssException
	     */
	    public FssAccountEntity getFssFundAccountInfo(String accNo) throws FssException{
	    	FssAccountEntity fssAccountEntity=new FssAccountEntity();
	    	fssAccountEntity.setAccNo(accNo);
	        return fssAccountReadMapper.selectOne(fssAccountEntity);
	    }
	
	    public FssAccountEntity getFundsAccountByCustId(String custId) throws FssException{
	    	FssAccountEntity fssAccountEntity=new FssAccountEntity();
	    	fssAccountEntity.setCustId(Long.valueOf(custId));
	    	return fssAccountReadMapper.selectOne(fssAccountEntity);
	    }
	    
	    /**
	     * 根据custId更新账户信息
	     */
	    public void  updateAccountCustomerName(Long custId,String custName,String cityId,String parentBankId,String bankNo){
	    	Map map=new HashMap();
	    	map.put("custId", custId);
	    	map.put("custName", custName);
	    	map.put("cityId", cityId);
	    	map.put("parentBankId", parentBankId);
	    	map.put("bankNo", bankNo);
	    	fundAccountWriteMapper.updateCustNameByCustId(map);
	    }
	    
	    
	    public FundAccountEntity getFundAccountInfo(String accNo) throws FssException{
	        return fundsAccountReadMapper.selectFundAccountEntity(accNo);
	    }
		  

	public List<FundAccountEntity> getFundsAccountByBusiType( String busi_type){
		return fundsAccountReadMapper.getFundsAccountByBusiType(busi_type);
	}
}
