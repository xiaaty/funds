package com.gqhmt.fss.architect.customer.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gqhmt.fss.architect.account.service.FundAccountService;
import com.gqhmt.fss.architect.customer.entity.UserEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.gqhmt.fss.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.fss.architect.customer.bean.CustomerInfoBean;
import com.gqhmt.fss.architect.customer.bean.CustomerInfoDetialBean;
import com.gqhmt.fss.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.fss.architect.customer.bean.CustomerInfoSendMsgBean;
import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.customer.mapper.read.CustomerInfoReadMapper;
import com.gqhmt.fss.architect.account.mapper.read.FundAccountReadMapper;
import com.gqhmt.fss.architect.customer.mapper.write.CustomerInfoWriteMapper;
import com.gqhmt.fss.architect.mapping.service.FuiouAreaService;
import com.gqhmt.fss.architect.mapping.service.FuiouBankCodeService;
import com.gqhmt.fss.logicService.pay.exception.FundsException;
import com.gqhmt.fss.pay.exception.CommandParmException;
import com.gqhmt.util.CommonUtil;
import com.gqhmt.util.MD5Util;

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

	@Autowired
	private CustomerInfoReadMapper customerInfoReadMapper;
	@Autowired
	private CustomerInfoWriteMapper customerInfoWriteMapper;

	@Autowired
	private FundAccountReadMapper fundAccountReadMapper;

	@Autowired
	private BankCardInfoService bankCardinfoService;

//	@Autowired
//	private UserDao userDao;
	@Autowired
	private FuiouAreaService fuiouAreaService;
	@Autowired
	private FuiouBankCodeService fuiouBankCodeService;

	@Autowired
	FundAccountService fundAccountService;

	@Resource
	private FssChangeCardService changeCardService;

	/**
	 * 查询客户管理列表
	 *
	 */
	public Page queryCustomerList(CustomerInfoBean customerInfo, String flg) {
		return null;
	}

	/**
	 * 批量插入客户信息 富友调用
	 *
	 */
	public void savaCustomerInfofuyou(List<CustomerInfoEntity> customerInfoList, String sysUserId) throws Exception {

		List<String> custNoList = new ArrayList<String>();
		List<String> phoneNoList = new ArrayList<String>();
		List<String> bankNoList = new ArrayList<String>();
		List<String> areaCodeList = new ArrayList<String>();
		List<String> bankCodeList = new ArrayList<String>();

		// 客户信息bean
		CustomerInfoEntity customerInfo = null;
		// 用户信息bean
		UserEntity userBean = null;

		// 银行卡信息实体bean
		BankCardInfoEntity bankCardinfoEntity = null;

		List<CustomerInfoEntity> changeList = new ArrayList<CustomerInfoEntity>();
		for (CustomerInfoEntity customerInfoEntity : customerInfoList) {
			customerInfo = queryCustomerInfoByCertNo(customerInfoEntity.getCertNo());
			if (customerInfo != null) {
				custNoList.add(customerInfoEntity.getCertNo());
				continue;
			}
			customerInfo = queryCustomerInfoByMobile(customerInfoEntity.getMobilePhone());
			if (customerInfo != null) {
				phoneNoList.add(customerInfoEntity.getMobilePhone());
				continue;
			}
			// 查询卡号是否存在
			bankCardinfoEntity = bankCardinfoService.queryBankCardinfoByBankNo(customerInfoEntity.getBankNo());
			if (bankCardinfoEntity != null) {
				bankNoList.add(customerInfoEntity.getBankNo());
				continue;
			}
			customerInfoEntity.setCertNo(customerInfoEntity.getCertNo().trim().toUpperCase());
			customerInfoEntity.setCustomerName(customerInfoEntity.getCustomerName().trim());
			customerInfoEntity.setMobilePhone(customerInfoEntity.getMobilePhone().trim());
			customerInfoEntity.setCreateTime((new Timestamp(new Date().getTime())));
			customerInfoEntity.setCreateUserId(Integer.parseInt(sysUserId));

			// 如果地区code填写有误 error列表里面添加
			if (!fuiouAreaService.queryFuiouAreaCodeValueByCode(customerInfoEntity.getCityCode())) {
				areaCodeList.add(customerInfoEntity.getCityCode());
				continue;
			}
			// 如果银行code填写有误 error列表里面添加
			if (!fuiouBankCodeService.queryFuiouBankCodeValueByCode(customerInfoEntity.getParentBankCode())) {
				bankCodeList.add(customerInfoEntity.getParentBankCode());
				continue;
			}
			if (custNoList.size() > 0 || phoneNoList.size() > 0 || bankNoList.size() > 0 || areaCodeList.size() > 0 || bankCodeList.size() > 0) {
				continue;
			}
			// 保存客户信息
			this.customerInfoWriteMapper.insertSelective(customerInfoEntity);
			/*************************************************************/

			// 划扣银行信息
			bankCardinfoEntity = new BankCardInfoEntity();
			// 客户id
			bankCardinfoEntity.setCustId(customerInfoEntity.getId());
			// 银行名称具体地址
			bankCardinfoEntity.setBankLongName(customerInfoEntity.getBankLongName());
			// 银行名称
			bankCardinfoEntity.setBankSortName(customerInfoEntity.getParentBankCode());
			// 银行卡号
			bankCardinfoEntity.setBankNo(customerInfoEntity.getBankNo());
			// 开户行地区代码
			bankCardinfoEntity.setCityId(fuiouAreaService.queryFuiouAreaCodeByValue(customerInfoEntity.getCityCode()));
			// 开户行行别
			bankCardinfoEntity.setParentBankId(fuiouBankCodeService.queryFuiouBankCodeByValue(customerInfoEntity.getParentBankCode()));
			// 是否个人银行卡 1：个人
			bankCardinfoEntity.setIsPersonalCard(1);
			// 富友默认卡已经签约
			bankCardinfoEntity.setCardIndex("fuyou");
			// 创建时间
			bankCardinfoEntity.setCreateTime((new Timestamp(new Date().getTime())));
			bankCardinfoEntity.setCreateUserId(Integer.parseInt(sysUserId));
			// 身份证号码
			bankCardinfoEntity.setCertNo(customerInfoEntity.getCertNo());
			// 银行卡绑定的手机号码 默认和客户信息的 手机号码一致 暂时
			bankCardinfoEntity.setMobile(customerInfoEntity.getMobilePhone());
			// 银行卡对应的开户名字 默认和客户信息一致 暂时
			bankCardinfoEntity.setCertName(customerInfoEntity.getCustomerName());
			// 保存划扣银行信息
			bankCardinfoService.insert(bankCardinfoEntity);

			// 富有开户关联用银行卡id
			customerInfoEntity.setBankId(bankCardinfoEntity.getId());
			// 设置支付渠道为 富友
			customerInfoEntity.setPayChannel(2);
			/*************************************************************/

			userBean = new UserEntity();
			// 客户关联用id
			userBean.setCustId(customerInfoEntity.getId());
			// 用户Uuid
			userBean.setUserUuid(CommonUtil.getUUID());
			// 用户名
			userBean.setUserName(customerInfoEntity.getMobilePhone());
			// 手机号
			userBean.setMobilePhone(customerInfoEntity.getMobilePhone());
			// 邮箱
			userBean.setEmail(customerInfoEntity.getEmail());
			// 密码
			userBean.setPassword(MD5Util.encryption(customerInfoEntity.getMobilePhone().substring(customerInfoEntity.getMobilePhone().length() - 6)));
			// 推荐人
			userBean.setRecommender(customerInfoEntity.getEmployeeNo());
			// 1:可用，2:禁用
			userBean.setDisable("1");
			// 首次转让
			userBean.setIsFirstDebt(1);
			userBean.setCreateTime(new Date(System.currentTimeMillis()));
			userBean.setModifyTime(new Date(System.currentTimeMillis()));

			// 用户来源 0：线上注册，1线下自动
			userBean.setUserFrom(1);
			// 注册用户信息
//			this.userDao.saveOrUpdate(userBean);

			// 更新客户信息的 用户ID
			customerInfoEntity.setUserId(userBean.getId());
			// 有效用户
			customerInfoEntity.setIsvalid(0);

			// 是否签署第三方协议 默认导入的客户都是签署的
			customerInfoEntity.setHasThirdAgreement(1);

			// 是否实名认证 0：未认证 1:已认证
			customerInfoEntity.setNameIdentification(0);
			// 是否电话认证 0：未认证 1:已认证
			customerInfoEntity.setPhoneIdentification(0);

			// 是否创建账户
			customerInfoEntity.setHasAcount(0);

			// 是否email认证 0：未认证 1:已认证
			customerInfoEntity.setEmailIdentification(0);

			// 开户行地区代码
			customerInfoEntity.setCityCode(fuiouAreaService.queryFuiouAreaCodeByValue(customerInfoEntity.getCityCode()));
			// 开户行行别
			customerInfoEntity.setParentBankCode(fuiouBankCodeService.queryFuiouBankCodeByValue(customerInfoEntity.getParentBankCode()));

			this.customerInfoWriteMapper.updateByPrimaryKeySelective(customerInfoEntity);

			changeList.add(customerInfoEntity);
		}

		// 身份证相同的客户信息已经存在，抛出异常
		if (custNoList.size() > 0) {
			StringBuffer no = new StringBuffer();
			for (int i = 0; i < custNoList.size(); i++) {
				if (i == custNoList.size() - 1) {
					no.append(custNoList.get(i));
				} else {
					no.append(custNoList.get(i)).append(",");
				}

			}
			throw new Exception("0012" + no.toString());
		}

		// 手机号重复的的客户信息已经存在，抛出异常
		if (phoneNoList.size() > 0) {
			StringBuffer no = new StringBuffer();
			for (int i = 0; i < phoneNoList.size(); i++) {
				if (i == phoneNoList.size() - 1) {
					no.append(phoneNoList.get(i));
				} else {
					no.append(phoneNoList.get(i)).append(",");
				}

			}
			throw new Exception("0013" + no.toString());
		}

		// 银行卡号重复的的客户信息已经存在，抛出异常
		if (bankNoList.size() > 0) {
			StringBuffer no = new StringBuffer();
			for (int i = 0; i < bankNoList.size(); i++) {
				if (i == bankNoList.size() - 1) {
					no.append(bankNoList.get(i));
				} else {
					no.append(bankNoList.get(i)).append(",");
				}

			}
			throw new Exception("0014" + no.toString());
		}

		// 地区code不存在的的的客户信息已经存在，抛出异常
		if (areaCodeList.size() > 0) {
			StringBuffer no = new StringBuffer();
			for (int i = 0; i < areaCodeList.size(); i++) {
				if (i == areaCodeList.size() - 1) {
					no.append(areaCodeList.get(i));
				} else {
					no.append(areaCodeList.get(i)).append(",");
				}

			}
			throw new Exception("0015" + no.toString());
		}

		// 银行code不存在的的的客户信息，抛出异常
		if (bankCodeList.size() > 0) {
			StringBuffer no = new StringBuffer();
			for (int i = 0; i < bankCodeList.size(); i++) {
				if (i == bankCodeList.size() - 1) {
					no.append(bankCodeList.get(i));
				} else {
					no.append(bankCodeList.get(i)).append(",");
				}

			}
			throw new Exception("0016" + no.toString());
		}

		// // TODO 富友接口 开发中
		for (CustomerInfoEntity customer:changeList) {
			try{
			//注册账户信息
//				 AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_CREATE,ThirdPartyType.FUIOU, customer);
			} catch(CommandParmException e){
				 throw new Exception("0020" + e.getMessage());
			}
		}
	}

	/**
	 * 添加客户信息
	 *
	 */
	public String saveCustomerInfo(CustomerInfoDetialBean customerInfoDetialBean, String sysUserId) throws Exception {

		// 客户信息bean
		CustomerInfoEntity customerInfo = null;

		// 用户信息bean
		UserEntity userBean = null;

		customerInfo = queryCustomerInfoByCertNo(customerInfoDetialBean.getCertNo());

		// 身份证重复
		if (customerInfo != null) {
			return "0012";
		}

		customerInfo = queryCustomerInfoByMobile(customerInfoDetialBean.getMobilePhone());
		// 手机号重复
		if (customerInfo != null) {
			return "0013";
		}

		// 富友渠道判断code的有效性
		if (2 == customerInfoDetialBean.getPayChannel().intValue()) {
			// 如果地区code填写有误 error列表里面添加
			if (!fuiouAreaService.queryFuiouAreaCodeValueByCode(customerInfoDetialBean.getCityCode())) {
				return "0016";
			}
			// 如果银行code填写有误 error列表里面添加
			if (!fuiouBankCodeService.queryFuiouBankCodeValueByCode(customerInfoDetialBean.getParentBankCode())) {
				return "0017";
			}
		}

		customerInfo = new CustomerInfoEntity();

		// bean的copy
		BeanUtils.copyProperties(customerInfoDetialBean, customerInfo);
//		ClassReflection.reflectionAttr(customerInfoDetialBean, customerInfo);

		customerInfo.setCertNo(customerInfoDetialBean.getCertNo().trim().toUpperCase());
		customerInfo.setCustomerName(customerInfoDetialBean.getCustomerName().trim());
		customerInfo.setMobilePhone(customerInfoDetialBean.getMobilePhone().trim());

		// 签发日期
		customerInfo.setCertIssueDate(CommonUtil.stringToDate(customerInfoDetialBean.getCertIssueDateView()));

		// 失效日期
		customerInfo.setCertFailDate(CommonUtil.stringToDate(customerInfoDetialBean.getCertFailDateView()));

		// 出生日期
		customerInfo.setBirthdate(CommonUtil.stringToDate(customerInfoDetialBean.getBirthdateView()));

		// 地址
		String addr = "";

		if (StringUtils.isNotBlank(customerInfoDetialBean.getAddrProvince())) {
			addr = customerInfoDetialBean.getAddrProvince();
		}
		if (StringUtils.isNotBlank(customerInfoDetialBean.getAddrCity())) {
			addr = addr + customerInfoDetialBean.getAddrCity();
		}
		if (StringUtils.isNotBlank(customerInfoDetialBean.getAddrTown())) {
			addr = addr + customerInfoDetialBean.getAddrTown();
		}

		customerInfo.setAddress(addr);

		customerInfo.setCreateTime((new Timestamp(new Date().getTime())));
		customerInfo.setCreateUserId(Integer.parseInt(sysUserId));

		// 是否实名认证 0：未认证 1:已认证
		customerInfo.setNameIdentification(0);
		// 是否电话认证 0：未认证 1:已认证
		customerInfo.setPhoneIdentification(0);

		// 是否email认证 0：未认证 1:已认证
		customerInfo.setEmailIdentification(0);

		if (customerInfoDetialBean.getCustomerType() == 4) {
			customerInfo.setHasThirdAgreement(0);
		} else {
			customerInfo.setHasThirdAgreement(1);
		}

		// 保存客户信息
		this.customerInfoWriteMapper.insertSelective(customerInfo);

		/*************************************************************/

		if (2 == customerInfoDetialBean.getPayChannel().intValue()) {
			// 划扣银行信息
			BankCardInfoEntity bankCardinfoEntity = new BankCardInfoEntity();
			// 客户id
			bankCardinfoEntity.setCustId(customerInfo.getId());
			// 银行名称
			bankCardinfoEntity.setBankSortName(customerInfoDetialBean.getBankSortName());
			if (customerInfo.getBankLongName() == null || customerInfo.getBankLongName().equals("")) {
				bankCardinfoEntity.setBankLongName(customerInfoDetialBean.getBankSortName());
			} else {
				bankCardinfoEntity.setBankLongName(customerInfo.getBankLongName());
			}
			// 银行卡号
			bankCardinfoEntity.setBankNo(customerInfo.getBankNo());
			// 开户行地区代码
			bankCardinfoEntity.setCityId(fuiouAreaService.queryFuiouAreaCodeByValue(customerInfo.getCityCode()));
			// 开户行行别
			bankCardinfoEntity.setParentBankId(fuiouBankCodeService.queryFuiouBankCodeByValue(customerInfo.getParentBankCode()));
			// 是否个人银行卡 1：个人
			bankCardinfoEntity.setIsPersonalCard(1);
			// 富友默认卡已经签约 冠群标注用
			bankCardinfoEntity.setCardIndex("fuyou");
			// 创建时间
			bankCardinfoEntity.setCreateTime((new Timestamp(new Date().getTime())));
			bankCardinfoEntity.setCreateUserId(Integer.parseInt(sysUserId));

			// 身份证号码
			bankCardinfoEntity.setCertNo(customerInfo.getCertNo());

			// 银行卡绑定的手机号码 默认和客户信息的 手机号码一致 暂时
			bankCardinfoEntity.setMobile(customerInfo.getMobilePhone());

			// 银行卡对应的开户名字 默认和客户信息一致 暂时
			bankCardinfoEntity.setCertName(customerInfo.getCustomerName());

			// 保存划扣银行信息
			bankCardinfoService.saveOrUpdate(bankCardinfoEntity);

			// 富有开户关联用银行卡id
			customerInfo.setBankId(bankCardinfoEntity.getId());

			// 设置支付渠道为 富友
			customerInfo.setPayChannel(2);
		} else {
			// 设置支付渠道为 大钱
			customerInfo.setPayChannel(1);
		}
		/*************************************************************/

		userBean = new UserEntity();

		// 客户关联用id
		userBean.setCustId(customerInfo.getId());

		// 用户Uuid
		userBean.setUserUuid(CommonUtil.getUUID());

		// 用户名
		userBean.setUserName(customerInfo.getMobilePhone());

		// 手机号
		userBean.setMobilePhone(customerInfo.getMobilePhone());
		// 邮箱
		userBean.setEmail(customerInfo.getEmail());
		// 密码
		userBean.setPassword(MD5Util.encryption(customerInfo.getMobilePhone().substring(customerInfo.getMobilePhone().length() - 6)));
		// 1:可用，2:禁用
		userBean.setDisable("1");

		// 首次转让
		userBean.setIsFirstDebt(1);

		// 推荐人
		userBean.setRecommender(customerInfo.getEmployeeNo());

		// 用户来源 0：线上注册，1线下自动
		userBean.setUserFrom(1);

		userBean.setCreateTime(new Date(System.currentTimeMillis()));
		userBean.setModifyTime(new Date(System.currentTimeMillis()));

		// userList.add(userBean);

		// 注册用户信息
//		this.userDao.saveOrUpdate(userBean);

		// 更新客户信息的 用户ID
		customerInfo.setUserId(userBean.getId());
		// 有效用户
		customerInfo.setIsvalid(0);

		// 是否签署第三方协议 默认后台添加的的客户都是签署的
		// customerInfo.setHasThirdAgreement(1);

		// 是否创建账户
		customerInfo.setHasAcount(0);

		customerInfoWriteMapper.updateByPrimaryKeySelective(customerInfo);

		// // TODO 富友接口 开发中
		// try{
		// //注册账户信息
		//
		// //开户行地区代码
		// customerInfo.setCityCode(fuiouAreaService.queryFuiouAreaCodeByValue(customerInfo.getCityCode()));
		// //开户行行别
		// customerInfo.setParentBankCode(fuiouBankCodeService.queryFuiouBankCodeByValue(customerInfo.getParentBankCode()));
		//
		// AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_CREATE,ThirdPartyType.FUIOU,
		// customerInfo);
		// } catch(CommandParmException e){
		// throw new Exception("0020" + e.getMessage());
		// }
		//
		// }

		return "0000";
	}

	/**
	 * 根据id逻辑删除客户信息
	 *
	 * @param ids
	 * @return
	 */
	public void updateIsvalidById(String ids) throws Exception {
		String idArray[] = ids.split(",");
		CustomerInfoEntity customerInfoEntity = null;
		// String bankIds[] = new String[idArray.length];
		String bankIds = "";
		for (int i = 0; i < idArray.length; i++) {
			customerInfoEntity = this.queryCustomerById(Integer.parseInt(idArray[i]));
			if (customerInfoEntity.getHasAcount().intValue() == 1) {
				throw new Exception("0001" + customerInfoEntity.getCustomerName());
			}
			// bankIds[i]= customerInfoEntity.getBankId().toString();
			if (customerInfoEntity.getBankId() != null) {
				if (i == idArray.length - 1) {
					bankIds = bankIds + customerInfoEntity.getBankId().toString();
				} else {
					bankIds = bankIds + customerInfoEntity.getBankId().toString() + ",";
				}
			}
		}
//		customerInfoWriteMapper.createSQLQuery("delete from t_gq_customer_info  where id in (" + ids + ") ").executeUpdate();
//		userDao.createSQLQuery("delete from t_gq_user where cust_id in (" + ids + ") ").executeUpdate();

		bankCardinfoService.deleteBankCardinfoByIds(bankIds);

	}

	/**
	 * 根据id查询客户信息
	 *
	 * @param id
	 * @return
	 */
	public CustomerInfoEntity queryCustomerById(int id) {
		return customerInfoReadMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据身份证号查询客户信息
	 *
	 * @param certNo
	 * @return
	 */
	public CustomerInfoEntity queryCustomerInfoByCertNo(String certNo) {
		CustomerInfoEntity entity = new CustomerInfoEntity();
		entity.setCertNo(certNo);
		return customerInfoReadMapper.selectOne(entity);
	}

	/**
	 * 根据手机号查询客户信息
	 *
	 * @param mobile
	 * @return
	 */
	public CustomerInfoEntity queryCustomerInfoByMobile(String mobile) {
		CustomerInfoEntity entity = new CustomerInfoEntity();
		entity.setMobilePhone(mobile);
		return customerInfoReadMapper.selectOne(entity);
	}

	/**
	 * 根据id删除客户信息
	 *
	 * @param id
	 */
	public void deleted(int id) {
		customerInfoWriteMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询客户信息 画面修改表示用
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CustomerInfoDetialBean queryCustInfoyId(String id) throws Exception {
		CustomerInfoDetialBean customerInfoDetialBean = new CustomerInfoDetialBean();
		CustomerInfoEntity entity = this.queryCustomerById(Integer.valueOf(id));

		// bean的copy
		BeanUtils.copyProperties(entity, customerInfoDetialBean);
//		ClassReflection.reflectionAttr(entity, customerInfoDetialBean);
//		customerInfoDetialBean.setId(entity.getId());

		// 签发日期
		customerInfoDetialBean.setCertIssueDateView(CommonUtil.dateToString(entity.getCertIssueDate()));
		// 失效日期
		customerInfoDetialBean.setCertFailDateView(CommonUtil.dateToString(entity.getCertFailDate()));
		// 出生日期
		customerInfoDetialBean.setBirthdateView(CommonUtil.dateToString(entity.getBirthdate()));

		// 如果第三方支付接口是富友，查询对应的银行卡信息
		if (2 == entity.getPayChannel().intValue()) {
			if (entity.getBankId() != null) {
				BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(entity.getBankId());
				// 开户行地区(富友开户用)
				customerInfoDetialBean.setCityCode(fuiouAreaService.queryFuiouAreaValueByCode(bankCardinfoEntity.getCityId()));
				// 开户行行别(富友开户用)
				customerInfoDetialBean.setParentBankCode(fuiouBankCodeService.queryFuiouBankValueByCode(bankCardinfoEntity.getParentBankId()));
				// 开户银行名称
				customerInfoDetialBean.setBankSortName(bankCardinfoEntity.getBankSortName());
				// 开户银行具体地址
				customerInfoDetialBean.setBankLongName(bankCardinfoEntity.getBankLongName());
				// 银行卡号
				customerInfoDetialBean.setBankNo(bankCardinfoEntity.getBankNo());
                if(bankCardinfoEntity.getChangeState() !=null &&  bankCardinfoEntity.getChangeState() == 1){
                    customerInfoDetialBean.setIsChangeBankCard("99");
                }
			}
		}
		return customerInfoDetialBean;
	}

	/**
	 * 修改客户信息
	 *
	 */
	public String updateCustomerInfo(CustomerInfoDetialBean customerInfoDetialBean, String sysUserId) throws Exception {

		// 客户信息bean
		CustomerInfoEntity customerInfo = this.queryCustomerById(customerInfoDetialBean.getId());
		String beforeName = customerInfo.getCustomerName();
		String afterName = customerInfoDetialBean.getCustomerName();
		Integer certType = customerInfo.getCertType();

		CustomerInfoEntity customer = queryCustomerInfoByCertNo(customerInfoDetialBean.getCertNo());
		// 身份证重复
		if (customer != null && customer.getId() != customerInfo.getId()) {
			return "0014";
		}
		customer = queryCustomerInfoByMobile(customerInfoDetialBean.getMobilePhone());
		// 手机号重复
		if (customer != null && customer.getId() != customerInfo.getId()) {
			return "0015";
		}
		// 富友渠道判断code的有效性
		if (2 == customerInfo.getPayChannel().intValue() && "2".equals(customerInfoDetialBean.getIsChangeBankCard())) {
			// 如果地区code填写有误 error列表里面添加
			if (!fuiouAreaService.queryFuiouAreaCodeValueByCode(customerInfoDetialBean.getCityCode())) {
				return "0018";
			}
			// 如果银行code填写有误 error列表里面添加
			if (!fuiouBankCodeService.queryFuiouBankCodeValueByCode(customerInfoDetialBean.getParentBankCode())) {
				return "0019";
			}
		}

		// 是否需要掉用富友银行卡变更接口
		boolean bankChangeFlg = false;

		// bean的copy
//		ClassReflection.reflectionAttr(customerInfoDetialBean, customerInfo);
		BeanUtils.copyProperties(customerInfoDetialBean, customerInfo);
		
		customerInfo.setCertNo(customerInfoDetialBean.getCertNo().trim().toUpperCase());
		customerInfo.setCustomerName(customerInfoDetialBean.getCustomerName().trim());
		customerInfo.setMobilePhone(customerInfoDetialBean.getMobilePhone().trim());

		// 画面disable属性值丢失防止措施
		if (customerInfo.getCertType() == null) {
			customerInfo.setCertType(certType);
		}
		// 签发日期
		customerInfo.setCertIssueDate(CommonUtil.stringToDate(customerInfoDetialBean.getCertIssueDateView()));
		// 失效日期
		customerInfo.setCertFailDate(CommonUtil.stringToDate(customerInfoDetialBean.getCertFailDateView()));
		// 出生日期
		customerInfo.setBirthdate(CommonUtil.stringToDate(customerInfoDetialBean.getBirthdateView()));
		// 地址
		String addr = "";

		if (StringUtils.isNotBlank(customerInfoDetialBean.getAddrProvince())) {
			addr = customerInfoDetialBean.getAddrProvince();
		}
		if (StringUtils.isNotBlank(customerInfoDetialBean.getAddrCity())) {
			addr = addr + customerInfoDetialBean.getAddrCity();
		}
		if (StringUtils.isNotBlank(customerInfoDetialBean.getAddrTown())) {
			addr = addr + customerInfoDetialBean.getAddrTown();
		}
		customerInfo.setAddress(addr);

		// 是否email认证 0：未认证 1:已认证
		if (customerInfo.getEmail() != null && !"".equals(customerInfo.getEmail())) {
			customerInfo.setEmailIdentification(1);
		} else {
			customerInfo.setEmailIdentification(0);
		}
		customerInfo.setModifyTime((new Timestamp(new Date().getTime())));
		customerInfo.setModifyUserId(Integer.parseInt(sysUserId));

		UserEntity userBean = null;
		if (customerInfo.getUserId() == null) {
			userBean = new UserEntity();

			// 客户关联用id
			userBean.setCustId(customerInfo.getId());

			// 用户名
			userBean.setUserName(customerInfo.getMobilePhone());

			// 手机号
			userBean.setMobilePhone(customerInfo.getMobilePhone());
			// 邮箱
			userBean.setEmail(customerInfo.getEmail());
			// 密码
			userBean.setPassword(MD5Util.encryption(customerInfo.getMobilePhone().substring(customerInfo.getMobilePhone().length() - 6)));
			// 推荐人
			userBean.setRecommender(customerInfo.getEmployeeNo());

			// 1:可用，2:禁用
			userBean.setDisable("1");

			userBean.setCreateTime(new Date(System.currentTimeMillis()));
			userBean.setModifyTime(new Date(System.currentTimeMillis()));

			// 注册用户信息
//			this.userDao.saveOrUpdate(userBean);
		} else {
			// 用户信息bean
//			userBean = this.userDao.get(customerInfo.getUserId());

			// 客户关联用id
			userBean.setCustId(customerInfo.getId());

			if (customerInfo.getCustomerType().intValue() != 4) {
				// 用户名
				userBean.setUserName(customerInfo.getMobilePhone());
			}

			// 手机号
			userBean.setMobilePhone(customerInfo.getMobilePhone());
			// 邮箱
			userBean.setEmail(customerInfo.getEmail());
			// 密码
			// 推荐人
			userBean.setRecommender(customerInfo.getEmployeeNo());
			// 1:可用，2:禁用
			userBean.setDisable("1");

			userBean.setModifyTime(new Date(System.currentTimeMillis()));

			// 修改用户信息
//			this.userDao.saveOrUpdate(userBean);

		}

		this.changeCard(customerInfo, customerInfoDetialBean, sysUserId,customerInfoDetialBean.getIsChangeBankCard());

		// 保存客户信息
		customerInfoWriteMapper.insert(customerInfo);

		FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(customerInfo.getId(), 0);

		if (fundAccountEntity != null && !afterName.equals(beforeName)) {
			fundAccountService.updateBycustId(customerInfo.getId(), customerInfoDetialBean.getCustomerName().trim());
		}

		/*
			修改银行卡程序流程变更,此处作废
		if (bankChangeFlg && "2".equals(customerInfoDetialBean.getIsChangeBankCard())) {
			// 富友修改客户绑定银行卡信息
			try {
				AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_UPDATE_CARD, ThirdPartyType.FUIOU, customerInfo.getId(), bankCardinfoEntity.getBankNo(), bankCardinfoEntity.getParentBankId(), bankCardinfoEntity.getBankLongName(), bankCardinfoEntity.getCityId());
			} catch (CommandParmException e) {
				throw new Exception("0021" + e.getMessage());
			}
		}*/

		// FundAccountEntity fundAccountEntity =
		// fundAccountDao.queryFundAccount(customerInfo.getId(), 0);
		//
		// if (fundAccountEntity == null ||
		// fundAccountEntity.getHasThirdAccount() == 1) {
		// } else {
		// // TODO 富友接口 开发中
		// try{
		// //注册账户信息
		// AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_CREATE,ThirdPartyType.FUIOU,
		// customerInfo);
		// } catch(CommandParmException e){
		// throw new Exception("0020" + e.getMessage());
		// }
		//
		// }

		return "0000";
	}


	//银行卡变更,第三方未开户,直接变更,已开户,走变更流程
	private void changeCard(CustomerInfoEntity customerInfo, CustomerInfoDetialBean customerInfoDetialBean, String sysUserId ,String isChangeFuiouBankCard) throws Exception {

		//上传图片暂不处理
		if(customerInfo.getHasAcount() == 1){
			if("2".equals(isChangeFuiouBankCard)) {
				changeCardService.addChangeCard(customerInfo, customerInfoDetialBean.getBankNo(), fuiouBankCodeService.queryFuiouBankCodeByValue(customerInfoDetialBean.getParentBankCode()), customerInfoDetialBean.getBankLongName(), fuiouAreaService.queryFuiouAreaCodeByValue(customerInfoDetialBean.getCityCode()), customerInfoDetialBean.getImageFileName(), 2, "");
				return;
			}
		}

		BankCardInfoEntity bankCardinfoEntity = null;
		// 富友渠道要更新银行卡关联信息
		if (2 == customerInfo.getPayChannel().intValue()) {
			if (customerInfo.getBankId() != null) {
				bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(customerInfo.getBankId());
				if (bankCardinfoEntity != null) {
					// 身份证号码
					bankCardinfoEntity.setCertNo(customerInfoDetialBean.getCertNo());

					// 银行卡绑定的手机号码 默认和客户信息的 手机号码一致 暂时
					bankCardinfoEntity.setMobile(customerInfoDetialBean.getMobilePhone());

					// 银行卡对应的开户名字 默认和客户信息一致 暂时
					bankCardinfoEntity.setCertName(customerInfoDetialBean.getCustomerName());

					// 银行名称
					bankCardinfoEntity.setBankSortName(customerInfoDetialBean.getBankSortName());
					// 银行名称
					bankCardinfoEntity.setBankLongName(customerInfoDetialBean.getBankLongName());

					// 银行卡号
					bankCardinfoEntity.setBankNo(customerInfoDetialBean.getBankNo());

					// 开户行地区代码
					bankCardinfoEntity.setCityId(fuiouAreaService.queryFuiouAreaCodeByValue(customerInfoDetialBean.getCityCode()));
					// 开户行行别
					bankCardinfoEntity.setParentBankId(fuiouBankCodeService.queryFuiouBankCodeByValue(customerInfoDetialBean.getParentBankCode()));

					// 修改时间
					bankCardinfoEntity.setModifyTime((new Timestamp(new Date().getTime())));
					bankCardinfoEntity.setModifyUserId(Integer.parseInt(sysUserId));

					// 保存划扣银行信息
					bankCardinfoService.saveOrUpdate(bankCardinfoEntity);

				}
			} else {
				// 划扣银行信息
				bankCardinfoEntity = new BankCardInfoEntity();

				// 客户id
				bankCardinfoEntity.setCustId(customerInfo.getId());

				// 银行名称
				bankCardinfoEntity.setBankSortName(customerInfoDetialBean.getBankSortName());
				if (customerInfo.getBankLongName() == null || customerInfo.getBankLongName().equals("")) {
					bankCardinfoEntity.setBankLongName(customerInfoDetialBean.getBankSortName());
				} else {
					bankCardinfoEntity.setBankLongName(customerInfo.getBankLongName());
				}

				// 银行卡号
				bankCardinfoEntity.setBankNo(customerInfo.getBankNo());

				// 开户行地区代码
				bankCardinfoEntity.setCityId(fuiouAreaService.queryFuiouAreaCodeByValue(customerInfo.getCityCode()));
				// 开户行行别
				bankCardinfoEntity.setParentBankId(fuiouBankCodeService.queryFuiouBankCodeByValue(customerInfo.getParentBankCode()));

				// 是否个人银行卡 1：个人
				bankCardinfoEntity.setIsPersonalCard(1);
				// 富友默认卡已经签约 冠群标注用
				bankCardinfoEntity.setCardIndex("fuyou");
				// 创建时间
				bankCardinfoEntity.setCreateTime((new Timestamp(new Date().getTime())));
				bankCardinfoEntity.setCreateUserId(Integer.parseInt(sysUserId));

				// 身份证号码
				bankCardinfoEntity.setCertNo(customerInfo.getCertNo());

				// 银行卡绑定的手机号码 默认和客户信息的 手机号码一致 暂时
				bankCardinfoEntity.setMobile(customerInfo.getMobilePhone());

				// 银行卡对应的开户名字 默认和客户信息一致 暂时
				bankCardinfoEntity.setCertName(customerInfo.getCustomerName());

				// 保存划扣银行信息
				bankCardinfoService.saveOrUpdate(bankCardinfoEntity);
				customerInfo.setHasAcount(0);
				customerInfo.setBankId(bankCardinfoEntity.getId());
			}
		}
	}

	/**
	 * 创建第三方账户
	 *
	 */
	public String createAcount(String id, String sysUserId) throws Exception {

		// 客户信息bean
		CustomerInfoEntity customerInfo = this.queryCustomerById(Integer.parseInt(id));
		// 如果已经创建
		if (customerInfo.getCustomerType().intValue() == 4) {
			throw new Exception("0003" + customerInfo.getMobilePhone());
		}
		// 如果已经创建
		if (customerInfo.getHasAcount() != null && customerInfo.getHasAcount().intValue() == 1) {
			throw new Exception("0001" + customerInfo.getCustomerName());
		}
		FundAccountEntity fundAccountEntity = fundAccountReadMapper.queryFundAccount(customerInfo.getId(), 0);

		if (fundAccountEntity == null || fundAccountEntity.getHasThirdAccount() == 1) {
			// 富友开户接口
			if (2 == customerInfo.getPayChannel().intValue()) {
				BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(customerInfo.getBankId());
				// 银行卡号
				customerInfo.setBankNo(bankCardinfoEntity.getBankNo());
				// 开户行地区代码
				customerInfo.setCityCode(bankCardinfoEntity.getCityId());
				// 开户行行别
				customerInfo.setParentBankCode(bankCardinfoEntity.getParentBankId());
				try {
					// 注册账户信息
//					AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_CREATE, ThirdPartyType.FUIOU, customerInfo);
				} catch (CommandParmException e) {
					throw new Exception("0002" + e.getMessage());
				}
			}
		}
		// 是否签署第三方协议 默认导入的客户都是签署的
		customerInfo.setHasThirdAgreement(1);
		// 是否实名认证 0：未认证 1:已认证
		customerInfo.setNameIdentification(1);
		// 是否电话认证 0：未认证 1:已认证
		customerInfo.setPhoneIdentification(1);
		// 是否email认证 0：未认证 1:已认证
		if (customerInfo.getEmail() != null && !"".equals(customerInfo.getEmail())) {
			customerInfo.setEmailIdentification(1);
		} else {
			customerInfo.setEmailIdentification(0);
		}

		customerInfo.setHasAcount(1);
		customerInfo.setModifyTime((new Timestamp(new Date().getTime())));
		customerInfo.setModifyUserId(Integer.parseInt(sysUserId));
		// 保存客户信息
		update(customerInfo);

		return "0000";
	}

	public void update(CustomerInfoEntity entity) {
		this.customerInfoWriteMapper.updateByPrimaryKeySelective(entity);
	}

	public void custDestroy(String ids) throws Exception {
		String idArray[] = ids.split(",");
		CustomerInfoEntity customerInfoEntity = null;
		String bankIds = "";
		for (int i = 0; i < idArray.length; i++) {
			customerInfoEntity = this.queryCustomerById(Integer.parseInt(idArray[i]));

			if (i == idArray.length - 1) {
				if (customerInfoEntity.getBankId() != null) {
					bankIds = bankIds + customerInfoEntity.getBankId().toString();
				}
			} else {
				if (customerInfoEntity.getBankId() != null) {
					bankIds = bankIds + customerInfoEntity.getBankId().toString() + ",";
				}
			}
		}
//		// 备份数据
//		customerInfoWriteMapper.createSQLQuery("INSERT t_gq_customer_info_history SELECT * FROM t_gq_customer_info where id in (" + ids + ") ").executeUpdate();
//		customerInfoWriteMapper.createSQLQuery("INSERT t_gq_fund_account_history SELECT * FROM t_gq_fund_account where cust_id in (" + ids + ")").executeUpdate();
//		customerInfoWriteMapper.createSQLQuery("INSERT t_gq_user_history SELECT * FROM t_gq_user where cust_id in (" + ids + ")").executeUpdate();
//		if (!bankIds.equals("")) {
//			customerInfoWriteMapper.createSQLQuery("INSERT t_gq_bank_info_history SELECT * FROM t_gq_bank_info where cust_id in (" + ids + ")").executeUpdate();
//		}
//		// 删除该用户相关数据信息
//		customerInfoWriteMapper.createSQLQuery("delete from t_gq_customer_info  where id in (" + ids + ") ").executeUpdate();
//		userDao.createSQLQuery("delete from t_gq_user where cust_id in (" + ids + ") ").executeUpdate();
//		customerInfoWriteMapper.createSQLQuery("delete from t_gq_fund_account where cust_id in (" + ids + ")").executeUpdate();
//		if (!bankIds.equals("")) {
//			customerInfoWriteMapper.createSQLQuery("delete from t_gq_bank_info where cust_id in (" + ids + ")").executeUpdate();
//		}

	}

	/**
	 * 根据id查询客户发送短信模式
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CustomerInfoSendMsgBean queryCustsendMsgInfo(String id) throws Exception {
		CustomerInfoSendMsgBean customerInfoSendMsgBean = new CustomerInfoSendMsgBean();

		CustomerInfoEntity entity = this.queryCustomerById(Integer.valueOf(id));

		customerInfoSendMsgBean.setId(entity.getId());
		customerInfoSendMsgBean.setCustomerType(entity.getCustomerType());
		customerInfoSendMsgBean.setCustomerName(entity.getCustomerName());
		customerInfoSendMsgBean.setMobilePhone(entity.getMobilePhone());
		customerInfoSendMsgBean.setSendMsgRechargeWithdrawFouyou(entity.getSendMsgRechargeWithdrawFouyou());
		customerInfoSendMsgBean.setSendMsgTransferOutFouyou(entity.getSendMsgTransferOutFouyou());
		customerInfoSendMsgBean.setSendMsgTransferInFouyou(entity.getSendMsgTransferInFouyou());
		customerInfoSendMsgBean.setSendMsgTransferAllFouyou(entity.getSendMsgTransferAllFouyou());

		return customerInfoSendMsgBean;
	}

	/**
	 * 更改富友短信接收方式 调用富友接口
	 *
	 */
	public String updateCustSengMsgMode(CustomerInfoSendMsgBean customerInfoSendMsgBean, String sysUserId,
			String thirdPartyType) throws FundsException {

		// 客户信息bean
		CustomerInfoEntity customerInfo = this.queryCustomerById(customerInfoSendMsgBean.getId());

		// 如果未创建
		if (customerInfo.getHasAcount().intValue() != 1) {
			throw new FundsException("0001" + customerInfo.getCustomerName());
		}

		try {
			// 更改富友短信方式
//			AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_SET_MMS, thirdPartyType, customerInfo.getId(), customerInfoSendMsgBean.getSendMsgRechargeWithdrawFouyou().toString(), customerInfoSendMsgBean.getSendMsgTransferOutFouyou().toString(), customerInfoSendMsgBean.getSendMsgTransferInFouyou().toString(), customerInfoSendMsgBean.getSendMsgTransferAllFouyou().toString());
		} catch (CommandParmException e) {
			throw new FundsException("0002" + e.getMessage());
		}

		customerInfo.setIsBatchSendmsgCalled(1);
		customerInfo.setSendMsgRechargeWithdrawFouyou(customerInfoSendMsgBean.getSendMsgRechargeWithdrawFouyou());
		customerInfo.setSendMsgTransferOutFouyou(customerInfoSendMsgBean.getSendMsgTransferOutFouyou());
		customerInfo.setSendMsgTransferInFouyou(customerInfoSendMsgBean.getSendMsgTransferInFouyou());
		customerInfo.setSendMsgTransferAllFouyou(customerInfoSendMsgBean.getSendMsgTransferAllFouyou());
		customerInfo.setModifyTime((new Timestamp(new Date().getTime())));
		customerInfo.setModifyUserId(Integer.parseInt(sysUserId));
		// 保存客户信息
		update(customerInfo);

		return "0000";
	}

	/**
	 * 设定批处理富友短信接收方式
	 *
	 */
	public String updateCustSengMsgModeBatch(CustomerInfoSendMsgBean customerInfoSendMsgBean, String sysUserId) throws Exception {

		Map<String, String> sendmsgMap = null;//(Map<String, String>) CacheUtils.get(CustomerConstants.CACHE_FUIOU_SEND_MSG_MAP);

//		CacheUtils.remove(CustomerConstants.CACHE_FUIOU_SEND_MSG_MAP);
		sendmsgMap = new HashMap<String, String>();

		sendmsgMap.put("rechargeMode", customerInfoSendMsgBean.getSendMsgRechargeWithdrawFouyou().toString());
		sendmsgMap.put("outMode", customerInfoSendMsgBean.getSendMsgTransferOutFouyou().toString());
		sendmsgMap.put("inMode", customerInfoSendMsgBean.getSendMsgTransferInFouyou().toString());
		sendmsgMap.put("allMode", customerInfoSendMsgBean.getSendMsgTransferAllFouyou().toString());

//		CacheUtils.put(CustomerConstants.CACHE_FUIOU_SEND_MSG_MAP, sendmsgMap);

		return "0000";
	}

	/**
	 * 更改富友短信接收方式 调用富友接口批处理接口
	 *
	 */
	public Map<String, Object> updateAutoCustSengMsgMode() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 查询未变更过的客户数据
		List<CustomerInfoEntity> custList = this.customerInfoReadMapper.selectNoChangeCustomer();

		// 如果没有数据 不处理
		if (custList == null || custList.size() == 0) {
			map.put("totalCount", "0");
			map.put("successCount", "0");
			map.put("failCount", "0");
			map.put("message", "");
			return map;
		}

		int successCount = 0;
		int failCount = 0;
		String message = "";

		String rechargeMode = "0";
		String outMode = "1";
		String inMode = "1";
		String allMode = "1";

		Map<String, String> sendmsgMap = null;//(Map<String, String>) CacheUtils.get(CustomerConstants.CACHE_FUIOU_SEND_MSG_MAP);
		if (sendmsgMap != null) {
			rechargeMode = sendmsgMap.get("rechargeMode");
			outMode = sendmsgMap.get("outMode");
			inMode = sendmsgMap.get("inMode");
			allMode = sendmsgMap.get("allMode");
		}

		Date date = new Timestamp(new Date().getTime());

		List<CustomerInfoEntity> returnList = new ArrayList<CustomerInfoEntity>();

		for (int i = 0; i < custList.size(); i++) {

			try {
				// 更改富友短信方式
//				AccountCommand.payCommand.command(CommandEnum.AccountCommand.ACCOUNT_SET_MMS, ThirdPartyType.FUIOU, custList.get(i).getId(), rechargeMode, outMode, inMode, allMode);
			} catch (CommandParmException e) {
				failCount++;
				message = message + (failCount + 1) + ":" + e.getMessage();
				continue;
			}

			custList.get(i).setIsBatchSendmsgCalled(1);
			custList.get(i).setSendMsgRechargeWithdrawFouyou(Integer.parseInt(rechargeMode));
			custList.get(i).setSendMsgTransferOutFouyou(Integer.parseInt(outMode));
			custList.get(i).setSendMsgTransferInFouyou(Integer.parseInt(inMode));
			custList.get(i).setSendMsgTransferAllFouyou(Integer.parseInt(allMode));
			custList.get(i).setModifyTime(date);
			custList.get(i).setModifyUserId(1);

			successCount++;

			returnList.add(custList.get(i));
		}

		map.put("totalCount", String.valueOf(custList.size()));
		map.put("successCount", String.valueOf(successCount));
		map.put("failCount", String.valueOf(failCount));
		map.put("message", message);
		map.put("list", returnList);

		return map;
	}

	/**
	 * 更改富友短信接收方式 调用富友接口批处理接口
	 *
	 */
	public void updateCustInfoList(List<CustomerInfoEntity> list) throws Exception {
		for (CustomerInfoEntity customerInfoEntity : list) {
			update(customerInfoEntity);
		}
	}

	/**
	 * 富友修改个人信息回调用方法
	 *
	 * @param custMap
	 */
	public void updateCustomerInfoCallBack(Map<String, String> custMap) throws Exception {

		// 身份证号码
		String CertNo = custMap.get("certif_id").trim();
		// 根据身份证id查出客户信息
		CustomerInfoEntity custInfo = this.queryCustomerInfoByCertNo(CertNo);
		if (custInfo == null) {
			throw new Exception("没有相关客户数据！");
		}

		String moblieFrom = custInfo.getMobilePhone();
		String moblieTo = custMap.get("mobile_no");


		// 银行卡关联信息
		BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(custInfo.getBankId());

		if (bankCardinfoEntity == null) {
			throw new Exception("没有相关客户银行卡数据！");
		}

		String bankNoFrom = custInfo.getMobilePhone();
		String bankNoTo = custMap.get("capAcntNo");

		bankCardinfoEntity.setMobile(moblieTo);
		bankCardinfoEntity.setBankLongName(custMap.get("bank_nm"));
		bankCardinfoEntity.setBankNo(custMap.get("capAcntNo"));
//		bankCardinfoEntity.setBankSortName(fuiouBankCodeService.queryFuiouBankValueByCode(custMap.get("city_id")));
		bankCardinfoEntity.setCityId(custMap.get("city_id"));
		bankCardinfoEntity.setParentBankId(custMap.get("parent_bank_id"));
		Date updateDate = new Date();
		bankCardinfoEntity.setModifyTime(updateDate);




		bankCardinfoService.saveOrUpdate(bankCardinfoEntity);

		// 如果手机号变更
		if (!moblieFrom.equals(moblieTo)) {
			// 用户信息bean
			UserEntity userBean = new UserEntity();
//			userBean = this.userDao.get(custInfo.getUserId());
			userBean.setMobilePhone(moblieTo);
			userBean.setModifyTime(updateDate);
//			userDao.saveOrUpdate(userBean);
			custInfo.setMobilePhone(moblieTo);
			custInfo.setEmail(custMap.get("email"));
			custInfo.setModifyTime(updateDate);

		}

		//如果银行卡变更 把签约状态设置为未签约
		if (!bankNoFrom.equals(bankNoTo)) {
			custInfo.setHasThirdAgreement(0);
		}
		update(custInfo);

	}

	public List<CustomerInfoEntity> queryConnectBidCustomerList() {
		CustomerInfoEntity entity = new CustomerInfoEntity();
		entity.setCustomerType(8);
		return customerInfoReadMapper.select(entity);
	}

}
