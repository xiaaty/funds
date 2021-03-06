package com.gqhmt.funds.architect.customer.service;

import com.github.pagehelper.Page;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.customer.bean.ChangeCardBean;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssChangeCardReadMapper;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.funds.architect.customer.bean.BankCardBean;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.BankEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.entity.UserEntity;
import com.gqhmt.funds.architect.customer.mapper.read.BankCardInfoReadMapper;
import com.gqhmt.funds.architect.customer.mapper.read.BankReadMapper;
import com.gqhmt.funds.architect.customer.mapper.read.CustomerInfoReadMapper;
import com.gqhmt.funds.architect.customer.mapper.write.BankCardinfoWriteMapper;
import com.gqhmt.funds.architect.customer.mapper.write.BankWriteMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.p2p.customer.service
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/16 16:36
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/16  于泳      1.0     1.0 Version
 */
@Service
public class BankCardInfoService {

    @Resource
    private BankCardInfoReadMapper bankCardinfoReadMapper;
    @Resource
    private BankCardinfoWriteMapper bankCardinfoWriteMapper;
    @Resource
	private FssChangeCardReadMapper bankCardChangeReadMapper;
    @Resource
    private BankReadMapper bankReadMapper;
    
    @Resource
    private BankWriteMapper bankWriteMapper;
    
    @Resource
    private CustomerInfoReadMapper customerReadMapper;
    
    
    
//    @Autowired
//    private BaseService baseService;
    
//	@Resource
//	FuiouAreaService fuiouAreaService;
	
//	@Resource
//	FuiouBankCodeService fuiouBankCodeService;
	
    @Resource
	private CustomerInfoService customerInfoService;

    public int insert(BankCardInfoEntity entity){
        return bankCardinfoWriteMapper.insertSelective(entity);
    }
    
    public int update(BankCardInfoEntity entity){
		return bankCardinfoWriteMapper.updateByPrimaryKey(entity);
    }

    public void updateBankCardInfo(BankCardInfoEntity bankCardInfoEntity){
    	bankCardinfoWriteMapper.updateBankCardByParam(bankCardInfoEntity);
    }
    
    public void remove(BankCardInfoEntity entity){
    	bankCardinfoWriteMapper.delete(entity);
    }
    
    public void removeById(Integer id){
    	bankCardinfoWriteMapper.deleteByPrimaryKey(id);
    }
    
    public void deleteById(Integer id) throws Exception{
    	
    	BankCardInfoEntity bankCard = this.queryBankCardinfoById(id);
    	
		List<String> list = new ArrayList<>();
        list.add(bankCard.getCardIndex());
    	try {
        
			//解除绑定划扣银行卡
//			AccountCommand.payCommand.command(CommandEnum.CARD.CARD_UNBIND, ThirdPartyType.DAQIAN,bankCard.getCustId(),2,list);
			
		} catch(CommandParmException e){
			throw  new Exception("0001" + e.getMessage()); 
		}
		
        removeById(id);

    }
    
    /**
     * 根据条件查询返回所有客户银行卡列表
     * @param bankDto
     * @return
     */
    public Page queryCardListByCustomer(BankCardBean bankDto) throws FssException{
    	return bankCardinfoReadMapper.queryCardListByCustomer(bankDto);
    }
    /**
     * 银行列表
     * @return
     */
    public List<BankCardBean> queryBankList() {
    	return bankCardinfoReadMapper.queryBankList();
    }
	/**
	 * 根据id查询银行信息
	 * @param id
	 * @return
	 */
	public BankCardInfoEntity queryBankCardinfoById(int id){
		return bankCardinfoReadMapper.selectByPrimaryKey(Integer.valueOf(id));
	}

	
	/**
	 * 
	 *修改银行卡信息
	 * @param entity
	 * @throws Exception 
	 */
	public String updateBankCard(BankCardInfoEntity entity, String userId) throws Exception {
		String code ="0000";

		// 校验卡号重复 
        BankCardInfoEntity bankCardinfo = queryBankCardinfoByBankNo(entity.getBankNo());
        if (bankCardinfo != null  && !bankCardinfo.getId().equals(entity.getId())) {
        	throw  new Exception("0002"); 
        }
		
		BankCardInfoEntity bankCard = queryBankCardinfoById(entity.getId());
		//客户信息bean
		CustomerInfoEntity customerInfo = customerInfoService.queryCustomerById(bankCard.getCustId().intValue());
        
		//是否需要掉用富友银行卡变更接口
		boolean bankChangeFlg = false;
		//如果变更后的银行卡号与原来的不一致并且已经开过户
		if (!bankCard.getBankNo().equals(entity.getBankNo()) && customerInfo.getHasAcount() == 1) {
			bankChangeFlg = true;
		}
		
        bankCard.setBankLongName(entity.getBankLongName());
        bankCard.setBankNo(entity.getBankNo());
        bankCard.setBankSortName(entity.getBankSortName());

        bankCard.setCertName(entity.getCertName());
        bankCard.setCertNo(entity.getCertNo()); 
        bankCard.setCustId(entity.getCustId());
        bankCard.setIsPersonalCard(entity.getIsPersonalCard());
        bankCard.setMobile(entity.getMobile());
        bankCard.setModifyTime((new Date(System.currentTimeMillis())));
        bankCard.setModifyUserId(Integer.parseInt(userId));
		//开户行地区代码 开户行行别
        update(bankCard);
        
		if (bankChangeFlg) {
			//富友修改客户绑定银行卡信息
			try{
//				AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_UPDATE_CARD, ThirdPartyType.FUIOU,customerInfo.getId(), bankCard.getBankNo(), bankCard.getParentBankId(), bankCard.getBankLongName(), bankCard.getCityId());
			} catch(CommandParmException e){
				throw  new Exception("0001" + e.getMessage());
			}
		}
        return code;
	}
	
	/**
	 * 根据id查询银行信息
	 * @param bankNo
	 * @return
	 */
	public BankCardInfoEntity queryBankCardinfoByBankNo(String bankNo){
		BankCardInfoEntity queryEntity = new BankCardInfoEntity();
	    queryEntity.setBankNo(bankNo);
		return bankCardinfoReadMapper.selectOne(queryEntity);
	}
	
	/**
	 * 根据id删除银行信息
	 * @param ids
	 * @return
	 */
	public void deleteBankCardinfoByIds(String ids){
		if(StringUtils.isNoneBlank(ids)) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				bankCardinfoWriteMapper.deleteByPrimaryKey(id);
			}
		}
	}


    /**
     * 查询该客户是否已配置银行卡信息
     * @param bankCardId
     * @param userId
     * @return
     */
    public Long queryBankCardByUserId(String bankCardId,Integer userId){
        return bankCardinfoReadMapper.queryBankCardByUserId(bankCardId, userId);
    }
    /*TODO
    public String addBidSaveBankCard(Bid bid,CustomerInfoEntity customerInfo,SysUsers user,Integer customerId) throws Exception{
        Integer putCardId = null;
        // 查询该卡在数据库中是否存在
        BankCardInfoEntity putBankCardEntity = queryBankCardinfoByBankNo(bid.getPutBankNo());
        // 如果不存在则添加银行卡信息
        if (null == putBankCardEntity) {
            putCardId = savePutBankCard(bid, customerInfo,user); 
            AccountCommand.payCommand.command(CommandEnum.CARD.CARD_BIND,ThirdPartyType.DAQIAN, customerId, 2, queryBankCardinfoById(putCardId));
            // 如果存在则把该银行卡记录id添加给bid
        } else {
            if(!putBankCardEntity.getCustId().equals(customerInfo.getId())){
                throw new RuntimeException("对不起，你绑定的不是本人银行卡,请重新输入!");
            }
            putCardId = putBankCardEntity.getId();
        }
        Integer outCardId = null;
        // 判断放款卡号和还款卡号是否相等如果不相等则添加还款卡号
        if (!bid.getPutBankNo().equals(bid.getOutBankNo())) {
            // 查询还款卡号是否在数据库中存在
            BankCardInfoEntity outBankCardEntity = queryBankCardinfoByBankNo(bid.getOutBankNo());
            // 如果不存在则添加还款银行卡
            if (null == outBankCardEntity) {
                outCardId = saveOutBankCard(bid, customerInfo,user);
                AccountCommand.payCommand.command(CommandEnum.CARD.CARD_BIND,ThirdPartyType.DAQIAN, customerId, 2, queryBankCardinfoById(outCardId));
                // 如果存在则把银行卡记录id添加给bid
            } else {
                if(!outBankCardEntity.getCustId().equals(customerInfo.getId())){
                    throw new RuntimeException("对不起，你绑定的不是本人银行卡,请重新输入!");
                }
                outCardId = outBankCardEntity.getId();
            }
        } else {
            outCardId = putCardId;
        }
        return putCardId.toString()+"|"+outCardId.toString();
    }
    */
    /* TODO
    public void saveBidSaveBankCard(Bid bidDTO , Bid bid,Integer putCardId,Integer outCardId,Integer customerId,CustomerInfoEntity customerInfo,SysUsers user) throws Exception{
     // 查询该银行卡是否已修改
        // 如已修改删除原来绑定银行卡重新添加
        BankCardInfoEntity putEntity = bankCardinfoReadMapper.queryBankCardByIdAndCardNo(bid.getPutBankNo(), putCardId);
        BankCardInfoEntity outEntity;
        // 如果查询数据库中银行卡为空说明银行卡已改，需要把原来绑定的卡进行解绑!
        if (null == putEntity) {
            // 查询还款卡号是否在数据库中存在
            BankCardInfoEntity putBankCardEntity = queryBankCardinfoByBankNo(bid.getPutBankNo());
            if (null == putBankCardEntity) {
                putCardId = savePutBankCard(bid, customerInfo,user); 
                bidDTO.setPutCardId(putCardId);
                AccountCommand.payCommand.command(CommandEnum.CARD.CARD_BIND,ThirdPartyType.DAQIAN, customerId, 2, queryBankCardinfoById(putCardId));
            } else {
                if(!putBankCardEntity.getCustId().equals(customerInfo.getId())){
                    throw new RuntimeException("你绑定的不是本人银行卡,请重新输入!");
                }
                bidDTO.setPutCardId(putBankCardEntity.getId());
            }
        }
        outEntity = bankCardinfoReadMapper.queryBankCardByIdAndCardNo(bid.getOutBankNo(), outCardId);
        if (null == outEntity) {
            BankCardInfoEntity outBankCardEntity = queryBankCardinfoByBankNo(bid.getOutBankNo());
            if (null == outBankCardEntity) {
                outCardId = saveOutBankCard(bid, customerInfo,user);
                bidDTO.setOutCardId(outCardId);
                AccountCommand.payCommand.command(CommandEnum.CARD.CARD_BIND,ThirdPartyType.DAQIAN, customerId, 2, queryBankCardinfoById(outCardId));
            } else {
                if(!outBankCardEntity.getCustId().equals(customerInfo.getId())){
                    throw new RuntimeException("对不起，你绑定的不是本人银行卡,请重新输入!");
                }
                bidDTO.setOutCardId(outBankCardEntity.getId());
            }
        }
    }*/
    /* TODO
    public Integer savePutBankCard(Bid bid, CustomerInfoEntity customerInfo,SysUsers user) throws Exception {
        BankCardInfoEntity entity = new BankCardInfoEntity();
        entity.setBankLongName(bid.getPutBankName());
        entity.setBankNo(bid.getPutBankNo());
        entity.setIsPersonalCard(bid.getPutBankCardType());
        entity.setCustId(customerInfo.getId());
        entity.setCertName(customerInfo.getCustomerName());
        entity.setCertNo(customerInfo.getCertNo());
        entity.setMobile(customerInfo.getMobilePhone());
        entity.setCreateUserId(Integer.valueOf(String.valueOf(user.getId())));
        entity.setCreateTime(new Date());
        return saveOrUpdate(entity);  
    }

    public Integer saveOutBankCard(Bid bid, CustomerInfoEntity customerInfo,SysUsers user) throws Exception {
        BankCardInfoEntity entity = new BankCardInfoEntity();
        entity.setBankLongName(bid.getOutBankName());
        entity.setBankNo(bid.getOutBankNo());
        entity.setIsPersonalCard(bid.getOutBankCardType());
        entity.setCustId(customerInfo.getId());
        entity.setCertName(customerInfo.getCustomerName());
        entity.setCertNo(customerInfo.getCertNo());
        entity.setMobile(customerInfo.getMobilePhone());
        entity.setCreateUserId(Integer.valueOf(String.valueOf(user.getId())));
        entity.setCreateTime(new Date());
        return insert(entity); 
         
    }

    public Integer savePutBankCardForBo(BidBean bid, CustomerInfoEntity customerInfo,SysUsers user) throws Exception {

        BankCardInfoEntity entity = new BankCardInfoEntity();
        entity.setBankLongName(bid.getPutBankName());
        entity.setBankNo(bid.getPutBankNo());
        entity.setIsPersonalCard(bid.getPutBankCardType());
        entity.setCustId(customerInfo.getId());
        entity.setCertName(customerInfo.getCustomerName());
        entity.setCertNo(customerInfo.getCertNo());
        entity.setMobile(customerInfo.getMobilePhone());
        entity.setCreateUserId(Integer.valueOf(String.valueOf(user.getId())));
        entity.setCreateTime(new Date());
        return insert(entity); 
    }

    public Integer saveOutBankCardForBo(BidBean bid, CustomerInfoEntity customerInfo,SysUsers user) throws Exception {
        BankCardInfoEntity entity = new BankCardInfoEntity();
        entity.setBankLongName(bid.getOutBankName());
        entity.setBankNo(bid.getOutBankNo());
        entity.setIsPersonalCard(bid.getOutBankCardType());
        entity.setCustId(customerInfo.getId());
        entity.setCertName(customerInfo.getCustomerName());
        entity.setCertNo(customerInfo.getCertNo());
        entity.setCreateUserId(Integer.valueOf(String.valueOf(user.getId())));
        entity.setMobile(customerInfo.getMobilePhone());
        entity.setCreateTime(new Date());
        return insert(entity); 
        
    }
    */
    /**
     * 银行卡变更信息列表
     * @param fssBankcard
     * @return
     */
	public List<FssChangeCardEntity> getBankCardChangeList(FssChangeCardEntity fssBankcard){
		return bankCardChangeReadMapper.queryChangeCardList(fssBankcard);
	}
	
	/**
	 * 查询银行信息
	 * @param bankinfo
	 * @return
	 */
	public List<BankEntity> getBankList(BankEntity bankinfo){
		return bankReadMapper.selectBankList(bankinfo);
	}

	/**
	 * 根据id得到银行信息
	 * @param
	 * @return
	 */
	public BankEntity getBankById(Long id){
		return bankReadMapper.selectByPrimaryKey(id);
	}
    
	public void saveBank(BankEntity bank){
		this.bankWriteMapper.saveBank(bank);
	}
    
	public void updateBank(BankEntity bank){
		this.bankWriteMapper.updateBank(bank);
	}
	
	/**
	 * 银行卡管理
	 * @param
	 * @return
	 */
	public List<BankCardInfoEntity> findAllbankCards(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("certName",map.get("certName")!=null ? map.get("certName") : null);
			map2.put("bankNo", map.get("bankNo")!=null ? map.get("bankNo") : null);
			map2.put("bankSortName", map.get("bankSortName")!=null ? map.get("bankSortName") : null);
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		return this.bankCardinfoReadMapper.selectBankCardList(map2);
	}
    
	
	public BankCardInfoEntity getBankCardById(Integer id){
		return bankCardinfoReadMapper.selectByPrimaryKey(id);
	}
	
	public void updateAndSaveBankCard(BankCardInfoEntity bankcard){
		this.bankCardinfoWriteMapper.updateBankCardInfo(bankcard);
	}
	
	public void delBankCard(Integer id){
		BankCardInfoEntity bankCardInfoEntity=this.getBankCardInfoById(id);
		bankCardInfoEntity.setModifyTime(new Date());
		bankCardInfoEntity.setIsDel(0);
		this.update(bankCardInfoEntity);
	}
	
	public List<CustomerInfoEntity> getAllCustomers(CustomerInfoEntity customer){
		return this.customerReadMapper.queryCustomerInfoEntityList(customer);
		
	}
	
	public BankCardInfoEntity getBankCardByBankNo(String CardNo){
		BankCardInfoEntity  bankCardInfo=new BankCardInfoEntity();
		bankCardInfo.setBankNo(CardNo);
		bankCardinfoReadMapper.selectOne(bankCardInfo);
		return bankCardInfo;
	}
	
	
	/**
	 * 创建银行账户
	 * @param loanAccountDto
	 * @param customer
	 * @return
	 * @throws FssException
	 */
	public BankCardInfoEntity createBankCardInfoEntity(CreateLoanAccountDto loanAccountDto,CustomerInfoEntity customer,UserEntity userEntity) throws FssException{
		BankCardInfoEntity bankCardInfoEntity=new BankCardInfoEntity();
		String  bankCode=loanAccountDto.getBank_id();
		bankCardInfoEntity.setCustId(customer.getId().intValue());
		bankCardInfoEntity.setBankLongName(Application.getInstance().getDictName("9703"+bankCode));
		bankCardInfoEntity.setBankSortName(Application.getInstance().getDictName("9703"+bankCode));
		bankCardInfoEntity.setBankNo(loanAccountDto.getBank_card());
		bankCardInfoEntity.setIsPersonalCard(1);
		bankCardInfoEntity.setCertNo(loanAccountDto.getCert_no());
		bankCardInfoEntity.setMobile(loanAccountDto.getMobile());
		bankCardInfoEntity.setCertName(customer.getCustomerName());
		bankCardInfoEntity.setCityId(Application.getInstance().getFourCode(loanAccountDto.getCity_id()));
		String bank_id = loanAccountDto.getBank_id();
		if(bank_id.length()==3){
			bank_id="0"+bank_id;
		}
		bankCardInfoEntity.setParentBankId(bank_id);
		bankCardInfoEntity.setCreateTime(new Date());
		bankCardInfoEntity.setCreateUserId(1);
		bankCardInfoEntity.setModifyTime(new Date());
		bankCardInfoEntity.setModifyUserId(1);
		return bankCardInfoEntity;
	}
	
	


	 public List<BankCardInfoEntity> getBankCardByCustId(int custId){
		 return bankCardinfoReadMapper.findBankCardByCustNo(String.valueOf(custId));
	 }
	 public BankCardInfoEntity getBankCardInfoById(Integer id){
		 return bankCardinfoReadMapper.selectByPrimaryKey(id);
	 }

	 	/**
	 	 * 创建银行卡信息
	 	 * @return
	 	 * @throws FssException
	 	 */
		public BankCardInfoEntity createBankCardInfo(CustomerInfoEntity customerInfoEntity,String tradeType) throws FssException{
			BankCardInfoEntity bankCardInfoEntity=new BankCardInfoEntity();
			String bankCode=customerInfoEntity.getParentBankCode();
			bankCardInfoEntity.setCustId(customerInfoEntity.getId().intValue());
			bankCardInfoEntity.setBankLongName(Application.getInstance().getDictName("9703"+bankCode));
			bankCardInfoEntity.setBankSortName(Application.getInstance().getDictName("9703"+bankCode));
			bankCardInfoEntity.setBankNo(customerInfoEntity.getBankNo());
			bankCardInfoEntity.setIsPersonalCard(1);
			bankCardInfoEntity.setCertNo(customerInfoEntity.getCertNo());
			bankCardInfoEntity.setMobile(customerInfoEntity.getMobilePhone());
			bankCardInfoEntity.setCertName(customerInfoEntity.getCustomerName());
			bankCardInfoEntity.setCityId(customerInfoEntity.getCityCode());
			bankCardInfoEntity.setParentBankId(customerInfoEntity.getParentBankCode());
			bankCardInfoEntity.setCreateTime(new Date());
			bankCardInfoEntity.setCreateUserId(1);
			bankCardInfoEntity.setModifyTime(new Date());
			bankCardInfoEntity.setModifyUserId(1);
			bankCardInfoEntity.setCardIndex("fuyou");
			bankCardInfoEntity.setSource(tradeType);//存交易类型
			bankCardinfoWriteMapper.insertSelective(bankCardInfoEntity);
			return bankCardInfoEntity;
		}
	 
		public BankCardInfoEntity queryBankCardByBankNo(String cardNo){
			
			return bankCardinfoReadMapper.queryBankCardByBankNo(cardNo);
		}
	 
		/**
		 * 查询客户银行卡信息
		 * @param custNo
		 * @return
		 * @throws FssException
		 */
		public List<BankCardInfoEntity> findBankCardByCustNo(String custNo) throws FssException{
			return bankCardinfoReadMapper.findBankCardByCustNo(custNo);
		}

		/**
		 * 查询客户银行卡信息
		 * @param custNo
		 * @return
		 * @throws FssException
		 */
		public ChangeCardBean findChangeCardInfo(String custNo) throws FssException{
			ChangeCardBean changecard =null;
			List<BankCardInfoEntity> bankCardInfos=this.findBankCardByCustNo(custNo);
			if(bankCardInfos==null) throw new FssException("90002036");
			for (BankCardInfoEntity bankCardInfo:bankCardInfos) {
				if(1==bankCardInfo.getChangeState()){
					FssChangeCardEntity changeCard=	bankCardChangeReadMapper.queryByChangeCardBankInfoId(Long.valueOf(bankCardInfo.getId().toString()));
					if(changeCard!=null) {
						changecard = new ChangeCardBean();
						changecard.setBankName(changeCard.getBankName());
						changecard.setBankAdd(changeCard.getBankAdd());
						changecard.setBankCity(changeCard.getBankCity());
						changecard.setBankType(changeCard.getBankType());
						changecard.setCardNo(changeCard.getCardNo());
						}
					}
				}
			return  changecard;
		}


	public List<BankCardInfoEntity> getBankCardByCustNo(String custId)throws  FssException{
		return bankCardinfoReadMapper.queryBankCard(Integer.valueOf(custId));
	}

	/**
	 * jhz
	 * 添加银行卡
	 * @param custNo
	 * @param bankNo
	 * @param bankId
	 * @param bankAddr
	 * @param bankCity
	 * @param filePath
	 * @param seqNo
	 * @param tradeType
     * @param mchn
     * @throws FssException
     */
	 public void addCardInfo(String custNo, String bankNo, String bankId, String bankAddr, String bankCity, String filePath,String seqNo,String tradeType,String mchn) throws  FssException{
		 BankCardInfoEntity bankCardInfo=new BankCardInfoEntity();
		  CustomerInfoEntity customerInfoEntity  = customerInfoService.getCustomerById(Long.valueOf(custNo));
		 if(customerInfoEntity == null){
			 throw new FssException("90002007");
		 }
		if(bankCity.length()==6){
			bankCity=Application.getInstance().getFourCode(bankCity);
		}
		 BankCardInfoEntity bankCardInfoEntity=this.creatBankInfoEntity(customerInfoEntity.getId(),customerInfoEntity.getCertNo(),customerInfoEntity.getMobilePhone(),customerInfoEntity.getCustomerName(),bankNo,bankId,bankCity,filePath,tradeType);
		 bankCardinfoWriteMapper.insertSelective(bankCardInfoEntity);
	 }

	/**
	 * jhz
	 * 创建银行卡信息
	 * @param custId
	 * @param certNo
	 * @param mobile
	 * @param custName
	 * @param bankNo
	 * @param bankId
	 * @param bankCity
	 * @param filePath
	 * @param tradeType
     * @return
     * @throws FssException
     */
	public BankCardInfoEntity creatBankInfoEntity(Long custId,String certNo,String mobile,String custName,String bankNo, String bankId, String bankCity, String filePath,String tradeType)throws  FssException{
		BankCardInfoEntity bankCardInfoEntity=new BankCardInfoEntity();
		bankCardInfoEntity.setCustId(custId.intValue());
		bankCardInfoEntity.setCertNo(certNo);
		bankCardInfoEntity.setMobile(mobile);
		bankCardInfoEntity.setCertName(custName);
		bankCardInfoEntity.setBankLongName(Application.getInstance().getDictName("9703"+bankId));
		bankCardInfoEntity.setBankSortName(Application.getInstance().getDictName("9703"+bankId));
		bankCardInfoEntity.setBankNo(bankNo);
		bankCardInfoEntity.setIsPersonalCard(1);
		bankCardInfoEntity.setCityId(bankCity);
		bankCardInfoEntity.setCreateTime(new Date());
		bankCardInfoEntity.setCreateUserId(1);
		bankCardInfoEntity.setParentBankId(bankId);
		bankCardInfoEntity.setModifyTime(new Date());
		bankCardInfoEntity.setModifyUserId(1);
//		bankCardInfoEntity.setFilePath(filePath);
		bankCardInfoEntity.setChangeState(3); //3新增
//		bankCardInfoEntity.setStatus("90004041");//90004041 银行卡未激活 90004043 已提交激活申请
//		bankCardInfoEntity.setCardIndex("fuiou");
		bankCardInfoEntity.setSource(tradeType);//存交易类型
		return  bankCardInfoEntity;
	}

	/**
	 * jhz
	 * 删除银行卡信息
	 * @param bankId
	 * @throws FssException
     */
	public  void deleteBankCardInfo(String bankId)throws  FssException{
		BankCardInfoEntity bankCardInfoEntity=this.getBankCardById(Integer.valueOf(bankId));
		if(bankCardInfoEntity==null) throw  new FssException("银行卡信息不存在");
		if(bankCardInfoEntity.getChangeState()==0) throw  new FssException("银行卡已绑定，不允许删除");
		bankCardInfoEntity.setIsDel(0);
		bankCardInfoEntity.setModifyTime(new Date());
		this.update(bankCardInfoEntity);
	}

	/**
	 * xdw
	 * 根据银行卡号查询
	 * @param bankCardInfoEntity
	 * @throws FssException
	 */
	public void updateBankCardinfo(BankCardInfoEntity bankCardInfoEntity) throws FssException {
		BankCardInfoEntity bankCardInfo=this.getBankCardById(bankCardInfoEntity.getId());
		if(bankCardInfo==null) throw  new FssException("银行卡信息不存在");
		if(bankCardInfo.getChangeState()==0) throw  new FssException("银行卡已绑定，不允许修改");
		if(bankCardInfo.getChangeState()==1) throw  new FssException("银行卡变更中，不允许修改");
		bankCardInfoEntity.setId(bankCardInfo.getId());
		bankCardInfoEntity.setIsDel(0);
		bankCardInfoEntity.setModifyTime(new Date());
		this.update(bankCardInfoEntity);
	}


}
