package com.gqhmt.funds.architect.customer.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.card.entiry.FssPosBackEntity;
import com.gqhmt.fss.architect.card.service.FssPosBackService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.bean.CustomerInfoDetialBean;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.entity.UserEntity;
import com.gqhmt.funds.architect.customer.mapper.read.CustomerInfoReadMapper;
import com.gqhmt.funds.architect.customer.mapper.write.CustomerInfoWriteMapper;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.service.PaySuperByFuiou;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 *
 * com.gq.p2p
 *
 * @className CustomerInfoService<br/>
 * @author
 * @createDate 2015-01-14 上午10:42:21<br/>
 * @version 1.0 <br/>
 */
@Service
public class CustomerInfoService {

	@Resource
	private CustomerInfoReadMapper customerInfoReadMapper;
	@Resource
	private CustomerInfoWriteMapper customerInfoWriteMapper;
	@Resource
	private FundAccountService fundAccountService;
	@Resource
    private GqUserService gqUserService;

	@Resource
	private BankCardInfoService bankCardinfoService;

	@Resource
	private PaySuperByFuiou paySuperByFuiou;
	@Resource
	private FssPosBackService fssPosBackService;


	/**
	 * 根据id查询客户信息
	 *
	 * @param id
	 * @return
	 */
	public CustomerInfoEntity queryCustomerById(int id) {
		Long id1=Long.valueOf(String.valueOf(id));
		return customerInfoReadMapper.selectByPrimaryKey(id1);
	}
	public CustomerInfoEntity getCustomerById(Long id) {
		return customerInfoReadMapper.selectByPrimaryKey(id);
	}

	/**//**
	 * 根据身份证号查询客户信息
	 *
	 * @param certNo
	 * @return
	 */
	public CustomerInfoEntity queryCustomerInfoByCertNo(String certNo) {
		return this.searchCustomerInfoByCertNo(certNo);
	}

	/**
	 * 根据身份份证号查询客户信息
	 * @param certNo
	 * @return
	 */
	public CustomerInfoEntity searchCustomerInfoByCertNo(String certNo) {
		return customerInfoReadMapper.queryCustomerByCertNo(certNo);
	}
	/**
	 * jhz
	 * 根据手机号号查询客户信息
	 * @param mobile
	 * @return
	 */
	public CustomerInfoEntity searchCustomerInfoByMobile(String mobile) {
		return customerInfoReadMapper.searchCustomerInfoByMobile(mobile);
	}

	public void update(CustomerInfoEntity entity) {
		this.customerInfoWriteMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 修改客户状态
	 * @param entity
     */
	public void updateState(CustomerInfoEntity entity) throws FssException{
		if(entity.getHasThirdAgreement()==0){
			entity.setHasThirdAgreement(1);
		}else{
			entity.setHasThirdAgreement(0);
		}
		entity.setModifyTime(new Date());
		customerInfoWriteMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 *
	 * author:jhz
	 * time:2016年2月15日
	 * function：根据id查询客户信息
	 */
	public CustomerInfoEntity queryCustomeById(Long id) {
		return customerInfoReadMapper.selectByPrimaryKey(id);
	}


	public CustomerInfoEntity createCustomer(String certNo,String name,String mobile) throws FssException{
		try{
			CustomerInfoEntity customerInfoEntity = this.createCustomerInfo(certNo,name,mobile);
			this.customerInfoWriteMapper.insertSelective(customerInfoEntity);
			UserEntity userEntity = gqUserService.createUser(customerInfoEntity.getCustomerName(),customerInfoEntity.getMobilePhone(),customerInfoEntity.getId());
			customerInfoEntity.setUserId(userEntity.getId());
			this.customerInfoWriteMapper.updateByPrimaryKeySelective(customerInfoEntity);
			return customerInfoEntity;
		}catch (Exception e){
			String  tmp = e.getMessage();
			if(tmp != null && tmp.contains("mobile_uk")){
				throw new FssException("90002030");
			}
			throw new FssException("90099005");
		}
	}

	/**
	 * 创建账户
	 * @param certNo
	 * @param name
	 * @param mobile
	 * @return
	 * @throws FssException
     */
	public CustomerInfoEntity createCustomerInfo(String certNo,String name,String mobile) throws FssException{
		CustomerInfoEntity customerInfoEntity=new CustomerInfoEntity();
		customerInfoEntity.setCustomerName(name);
		customerInfoEntity.setCertNo(certNo);
		customerInfoEntity.setMobilePhone(mobile);
		customerInfoEntity.setCustomerType(1);
		customerInfoEntity.setCertType(1);
		customerInfoEntity.setNameIdentification(0);
		customerInfoEntity.setPhoneIdentification(0);
		customerInfoEntity.setEmailIdentification(0);
		customerInfoEntity.setUserId(1);
		customerInfoEntity.setIsvalid(0);
		customerInfoEntity.setHasThirdAgreement(1);
		customerInfoEntity.setHasAcount(1);
		customerInfoEntity.setPayChannel(2);
//		customerInfoEntity.setBankId(Integer.parseInt(loanAccountDto.getBank_id()));
		customerInfoEntity.setIsBatchSendmsgCalled(0);
		customerInfoEntity.setCreateTime((new Timestamp(new Date().getTime())));
		customerInfoEntity.setCreateUserId(0);
		customerInfoEntity.setModifyTime((new Timestamp(new Date().getTime())));
		customerInfoEntity.setModifyUserId(0);
		return customerInfoEntity;
	}

	/**
	 * 创建账户
	 * @param loanAccountDto
	 * @return
	 * @throws FssException
	 */
	public CustomerInfoEntity createCustomerInfo(CreateLoanAccountDto loanAccountDto) throws FssException{
		return this.createCustomer(loanAccountDto.getCert_no(),loanAccountDto.getName(),loanAccountDto.getMobile());
	}


	/**
	 * jhz
	 * 查询客户信息表
	 * @param map
	 * @return
     */
	public List<CustomerInfoDetialBean> queryCustomerinfoList(Map<String,String> map){
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			map2.put("id",map.get("id")!=null ? map.get("id") : null);
			map2.put("name",map.get("name")!=null ? map.get("name") : null);
			map2.put("certNo", map.get("certNo")!=null ? map.get("certNo") : null);
			map2.put("mobile", map.get("mobile")!=null ? map.get("mobile") : null);
			map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
			map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
		}
		return customerInfoReadMapper.queryCustomerinfoList(map2);
	}
	/**
	 * jhz
	 * 查询客户信息
	 * @param id
	 * @return
     */
	public CustomerInfoDetialBean queryCustomerinfoById(Integer id){
		return customerInfoReadMapper.queryCustomerinfoById(id);
	}
	/**
	 * jhz
	 * 查询富友
	 * @param custId
	 * @return
     */
	public Map<String,String > userInfoQuery(String  custId) throws FssException{
		FundAccountEntity entity= fundAccountService.getFundAccount(Long.valueOf(custId), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
		Map<String,String> customerMap = null;
		CommandResponse response=null;
		try{
			response =paySuperByFuiou.userInfoQuery(entity);
			Map<String, Object> map=response.getMap();
			if(map == null || map.get("results") == null){
				return null;
			}
			Map<String, Object> resultsMap = (Map<String, Object>) map.get("results");
			if(resultsMap.get("result") == null){
				return null;
			}
			 customerMap = (Map<String, String>) resultsMap.get(("result"));
			return customerMap;

		}catch (FssException e){
			LogUtil.error(this.getClass(),e.getMessage());

		}

		return null;
	}
	/**
	 * jhz
	 * 进行销户
	 * @param custId
	 * @return
     */
	public Boolean dropAccount(String  custId) throws FssException{
		FundAccountEntity entity= fundAccountService.getFundAccount(Long.valueOf(custId), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
			Boolean result=false;
			try{
				result=paySuperByFuiou.dropAccount(entity,"2");

			return result;

		}catch (FssException e){
			LogUtil.error(this.getClass(),e.getMessage());

		}

		return result;
	}


	public List<CustomerInfoEntity> queryCustomerInfoByDate(String date){
		return customerInfoReadMapper.queryCustomerInfoByDate(date);
	}

	/**
	 * jhz
	 * 修改客户表签约状态
	 * @param mobile
	 * @param state
	 * @param bankNo
     */
	public void updateCustomerState(FssPosBackEntity posBack,String mobile, String state, String bankNo)throws FssException{
		if(!"1".equals(state)){
			return;
		}
		//通过手机号查询客户信息
		CustomerInfoEntity entity=this.searchCustomerInfoByMobile(mobile);
		if(entity==null){
			return;
		}
		if(entity.getHasThirdAgreement()==1){
			return;
		}
		if(entity.getBankId()==null){
			return;
		}
		BankCardInfoEntity bankCardInfoEntity=bankCardinfoService.getBankCardById(entity.getBankId());
		if(bankCardInfoEntity==null){
			return;
		}
		if(StringUtils.equals(bankNo,bankCardInfoEntity.getBankNo()) && bankCardInfoEntity.getChangeState()==0){
			entity.setModifyTime(new Date());
			entity.setHasThirdAgreement(1);
			this.update(entity);
			//修改为已使用
			posBack.setState("98010001");
			fssPosBackService.update(posBack);
		}
	}
}
