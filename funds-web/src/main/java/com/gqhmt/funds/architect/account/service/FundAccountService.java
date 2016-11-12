package com.gqhmt.funds.architect.account.service;


import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.gqhmt.conversion.bean.request.*;
import com.gqhmt.conversion.bean.response.PmtIdResponse;
import com.gqhmt.conversion.bean.response.ReqContentResponse;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.StringUtils;
import com.gqhmt.fss.architect.account.entity.FssAccountBindEntity;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper;
import com.gqhmt.fss.architect.account.service.ConversionService;
import com.gqhmt.fss.architect.account.service.FssAccountBindService;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.fss.architect.asset.mapper.read.FssAssetReadMapper;
import com.gqhmt.funds.architect.account.bean.FundAccountCustomerBean;
import com.gqhmt.funds.architect.account.bean.FundsAccountBean;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.mapper.read.FundsAccountReadMapper;
import com.gqhmt.funds.architect.account.mapper.write.FundsAccountWriteMapper;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.tyzf.common.frame.amq.AmqSendAndReceive;
import com.gqhmt.tyzf.common.frame.amq.AmqSender;
import com.gqhmt.tyzf.common.frame.amq.exception.AmqException;
import com.gqhmt.util.ConvertReportEnum;
import com.gqhmt.util.ConvertUtils;
import com.gqhmt.util.LogUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.TextMessage;
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
    private  FssAssetReadMapper assetReadMapper;
    @Resource
    private FssAccountReadMapper fssAccountReadMapper;
	@Resource
	private CustomerInfoService customerInfoService;
	@Resource
	private FssAccountBindService fssAccountBindService;
	@Resource
	private ConversionService conversionService;

    public void update(FundAccountEntity entity) {
    	fundAccountWriteMapper.updateByPrimaryKeySelective(entity);
	}
    public void delete(Long id) {
    	fundAccountWriteMapper.deleteByPrimaryKey(id);
    }
    public FundAccountEntity select(Long id) {
		return  fundsAccountReadMapper.selectByPrimaryKey(id);
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
			map2.put("mobilePhone",map.get("mobilePhone"));
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
	 * jhz
	 * 获取客户的所有账户信息
	 * @param custId
	 * @return
	 */
	public List<FundAccountEntity> getFundsAccountsByCustId(Long custId) throws FssException {
		return fundsAccountReadMapper.queryFundAccountsByCutId(custId);
	}
	/**
	 * jhz
	 * 获取客户手机号所有账户id
	 * @param mobile
	 * @return
	 */
	public String getFundsAccountIds(String mobile) throws FssException {
		CustomerInfoEntity  customerInfoEntity= customerInfoService.searchCustomerInfoByMobile(mobile);
		List<FundAccountEntity> list= Lists.newArrayList();
		if (customerInfoEntity!=null) {
			list= this.getFundsAccountsByCustId(customerInfoEntity.getId());
		}

		String accNos="";
		if(list.size()>0){
			for (FundAccountEntity entity:list) {
//				accNos.add(entity.getId());
				accNos+=entity.getId().toString()+",";
			}
		}
		return accNos;
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

		/**
		 * 查询红包账户
		 * @return
		 */
		public List<FundAccountEntity> getRedAccountList(List list){
			return fundsAccountReadMapper.getRedAccountList(list);
		}

		public FundAccountEntity getFundAccountById(Long id){
			return fundsAccountReadMapper.selectByPrimaryKey(id);
		}


		/**
		 * 查询所有账号信息
		 * @return
		 */
		public List<FundAccountCustomerBean> findAllFundAcountList() {
			List<FundAccountCustomerBean> list=fundsAccountReadMapper.findAllFundAcountList();
			return list;
		}

		/**
		 * 调用统一支付进行开户
		 * @param customerId 客户编号
		 * @param custName 客户姓名
		 * @param tradeType 交易类型
		 * @param custType 客户类型（个人，内部企业，外部企业)
		 * @param certType 证件类型（01-身份证，02-护照，03-军官证等）
		 * @param certNo 证件号
		 */
		public void createTyzfAccount(String tradeType,Long customerId,String custName,String custType,String certNo,String certType,String mchn,String accType, String busiNo,String orderNo,String seq_no) throws FssException{
			ReqContentResponse transContentResponse=null;
			List list=new ArrayList();
			//判断开户类型（冠E通线上开通出借账户）
			if("10010001".equals(accType)){//线上出借，开通线上出借账户（3）busi_type=3;
				 list.add(3);
			}
			if("10010002".equals(accType)){//线下出借，开通线下出借账户（2）、应付款账户（96）busi_type=2;busi_type=96;
				list.add(2);
				list.add(96);
			}
			if("10010003".equals(accType) || "10019002".equals(accType)){//借款，开通借款账户（1）和标的账户（90）busi_type=1,90;
				list.add(1);
				list.add(90);
			}
			for(Object busi_type:list){
				//创建映射账户
				FssAccountBindEntity fssAccountBindEntity = fssAccountBindService.createFssAccountMapping(customerId,Integer.valueOf(busi_type.toString()),tradeType,null,seq_no,null,busiNo==null?null:busiNo);
				try {
					ConverBean bean = new ConverBean();
					bean.setService_id("0001");//服务号
					bean.setTxnTp(tradeType);//交易类型
					bean.setOrderId(orderNo == null ? "" : orderNo);//业务订单号
					bean.setCdtrId("fuiou_1");//通道编号
					bean.setExMerchId("88721657SUKQ");//通道商户号
					bean.setChnlID("1");//线上线下类型
					bean.setCdtr_Nm("zhangsan");//客户姓名
					bean.setCdtr_PorO(custType == null ? "" : custType);//客户类型
					bean.setCdtrAcct_IssrCd("10001");//存管银行编号
					bean.setCdtrAcct_IdTp(busi_type==null ? "":String.valueOf(busi_type));//账户类型
					bean.setCdtr_ContractNo(busiNo == null ? "" : busiNo);//合同编号（标的账户需要）
					bean.setCdtrAcct_Ccy("RMB");//货币类型
					bean.setCdtrAcct_Branch("12345");//机构号
					bean.setMerchID("88721657SUKQ");//商户号
					transContentResponse = conversionService.sendAndReceiveMsg(bean,true);
					//统一支付开户成功返回结果
					List<PmtIdResponse> PmtIdlist= transContentResponse.getRequestMsg().getPmtID();
					String respCode=null;
					String busiId=null;
					String accountId=null;
					if(PmtIdlist.size()>0){
						for(PmtIdResponse pmtIdResponse:PmtIdlist){
							respCode=pmtIdResponse.getRespCode();//统一支付返回码0000成功，其他失败
						}
						if("0000".equals(respCode)){
							List<CdtrAcct> cdtrAcctList= transContentResponse.getRequestMsg().getCdtrAcct();
							if(cdtrAcctList.size()>0){
								for(CdtrAcct cdtrAcct:cdtrAcctList){
									accountId= cdtrAcct.getId();//统一支付返回的账号
								}
							}
						}
						//修改映射账户信息
						fssAccountBindEntity.setAccNo(accountId);
						fssAccountBindEntity.setStatus("1");
						fssAccountBindEntity.setOpenAccTime(String.valueOf(new Date()));
						fssAccountBindService.updateBindAccount(fssAccountBindEntity);
					}
				}catch (Exception e){
					throw new FssException(e.getMessage());
				}
			}
	}
}

