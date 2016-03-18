package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.loan.CreateLoanAccountDto;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.entity.FssFuiouAccountEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssAccountWriteMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssFuiouAccountWriteMapper;
import com.gqhmt.fss.architect.customer.entity.FssCustBankCardEntity;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustBankCardService;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.mapper.write.CustomerInfoWriteMapper;
import com.gqhmt.funds.architect.customer.mapper.write.GqUserWriteMapper;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.account.service.FssAccountService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/1/4 17:34
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/1/4  于泳      1.0     1.0 Version
 */
@Service
public class FssAccountService {

    @Resource
    private FssCustomerService fssCustomerService;
	@Resource
    private FssAccountReadMapper accountReadMapper;
    @Resource
    private FssAccountWriteMapper fssAccountWriteMapper;
    @Resource
    private FssFuiouAccountWriteMapper fssFuiouAccountWriteMapper;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
   	private CustomerInfoWriteMapper customerInfoWriteMapper;
    @Resource
	private GqUserWriteMapper gqUserWriteMapper;
    @Resource
	private FundAccountService fundAccountService;
    @Resource
    private FssCustBankCardService fssCustBankCardService;
    
    
    public List<FssAccountEntity> findCustomerAccountByParams(Map map){
        return this.accountReadMapper.findCustomerAccountByParams(map);
    }


    public List<BussAndAccountBean> queryAccountList(Map map)throws FssException {
        return this.accountReadMapper.getBussinessAccountList(map);
    }

    /**
     * 创建本地账户信息
     * @param dto
     * @throws FssException
     */
    public FssFuiouAccountEntity createAccount(CreateLoanAccountDto dto) throws FssException {
        //生成客户信息
        FssCustomerEntity fssCustomerEntity = fssCustomerService.create(dto);
        //生成银行卡信息
        FssCustBankCardEntity fssCustBankCardEntity=fssCustBankCardService.createFssBankCardInfo(dto,fssCustomerEntity);
        //生成第三方开户账户信息
        FssFuiouAccountEntity fssFuiouAccountEntity = this.createFuiouAccount(dto,fssCustomerEntity);
        
        return fssFuiouAccountEntity;
    }

    private FssFuiouAccountEntity createFuiouAccount(CreateLoanAccountDto dto,FssCustomerEntity fssCustomerEntity) throws FssException {
        try {
            FssFuiouAccountEntity fssFuiouAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssFuiouAccountEntity.class,dto);
            fssFuiouAccountEntity.setCusNo(String.valueOf(fssCustomerEntity.getId()));
            fssFuiouAccountEntity.setUserNo(fssCustomerEntity.getUserId());
            fssFuiouAccountEntity.setAccNo(getAccno(dto.getTrade_type()));
            fssFuiouAccountEntity.setAccUserName(fssCustomerEntity.getName());
            fssFuiouAccountEntity.setBankCardNo(dto.getBank_card());
            fssFuiouAccountEntity.setCreateTime(new Date());
            fssFuiouAccountEntity.setModifyTime(new Date());
            fssFuiouAccountEntity.setMchnChild(dto.getMchn());
            fssFuiouAccountEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
            fssFuiouAccountEntity.setHasOpenAccFuiou(0);
            fssFuiouAccountWriteMapper.insertSelective(fssFuiouAccountEntity);
            return fssFuiouAccountEntity;
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
            throw  new FssException("91009804");
        }
    }


    private FssAccountEntity createLocalAccount(SuperDto dto,FssCustomerEntity fssCustomerEntity) throws FssException {
        try {
            FssAccountEntity fssAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssAccountEntity.class,dto);
            fssAccountEntity.setCustNo(fssCustomerEntity.getCustNo());
            fssAccountEntity.setAccBalance(BigDecimal.ZERO);
            fssAccountEntity.setAccFreeze(BigDecimal.ZERO);
            fssAccountEntity.setAccAvai(BigDecimal.ZERO);
            fssAccountEntity.setAccNo(getAccno(dto.getTrade_type()));
            //设置账户类型
            //设置开户来源
            //设置渠道id
            fssAccountWriteMapper.insertSelective(fssAccountEntity);
            return fssAccountEntity;
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
            throw new FssException("");
        }
    }

    /**
     *
     * @param trade_type
     * @return
     */
	/*	账务账号共16位 0000-0000000000-1-1，账务账号与业务编号一一对应。
		账务账号分为三部分，第一部分 4位数字 为业务编号定义，
		，中间10位为账户账号，生成规则，随机 or 顺序（根据实现），倒数第二位，为账户类型标志，
		偶数（0，2，4，6，8）表示冻结账户，单数（1，3，5，7，9）表示正常账户每个账户随机使用某个数值，最后一位为校验位，校验规则待定。
	*/
    private String getAccno(String trade_type) {
    	String acc_no="";
    	StringBuffer busi_no=new StringBuffer(trade_type.substring(0,4));
    	StringBuffer str=this.getRedom();
    	acc_no=busi_no.append(str).append(this.getSecond()).toString();
        return acc_no;
    }

    /**
     * 新增账户信息
     * @param dto
     * @param fssCustomerEntity
     * @return
     * @throws FssException
     */
    public FssAccountEntity createFssAccount(CreateLoanAccountDto dto,CustomerInfoEntity fssCustomerEntity) throws FssException {
        try {
            FssAccountEntity fssAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssAccountEntity.class,dto);
            fssAccountEntity.setCustNo(dto.getFssSeqOrderEntity().getCustNo());
            fssAccountEntity.setAccBalance(BigDecimal.ZERO);
            fssAccountEntity.setAccFreeze(BigDecimal.ZERO);
            fssAccountEntity.setAccAvai(BigDecimal.ZERO);
            fssAccountEntity.setAccNo(getAccno(dto.getTrade_type()));
            //设置账户类型
            //设置开户来源
            //设置渠道id
            fssAccountWriteMapper.insertSelective(fssAccountEntity);
            return fssAccountEntity;
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
            throw new FssException("");
        }
    }
    
    /**
     * 创建资金账户
     * @param dto
     * @param fssCustomerEntity
     * @return
     * @throws FssException
     */
    public FssAccountEntity createFssAccountEntity(CreateLoanAccountDto dto,CustomerInfoEntity fssCustomerEntity) throws FssException {
    		FssFuiouAccountEntity fssFuiouAccountEntity=null;
    		fssFuiouAccountEntity=this.createAccount(dto);	
    	try {
            FssAccountEntity fssAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssAccountEntity.class,dto);
            fssAccountEntity.setAccNo(fssFuiouAccountEntity.getAccNo());
            fssAccountEntity.setCustNo(String.valueOf(fssCustomerEntity.getId()));
            fssAccountEntity.setAccBalance(BigDecimal.ZERO);
            fssAccountEntity.setAccFreeze(BigDecimal.ZERO);
            fssAccountEntity.setAccAvai(BigDecimal.ZERO);
            fssAccountEntity.setAccNotran(BigDecimal.ZERO);
            fssAccountEntity.setCustNo("");
            fssAccountEntity.setUserNo(fssCustomerEntity.getUserId().toString());
            String accType="";//设置账户类型
            String channelNo="";//渠道编号
            switch (dto.getTrade_type()){
            case "11020001"://web开户
            	accType="10010001"; //互联网账户
            	channelNo="97010001";//富友开户
            	break;
            case "11020002"://wep开户
            	accType="10010001";
            	channelNo="97010001";//富友开户
            	break;
            case "11020003"://app开户
            	accType="10010001";
            	channelNo="97010001";//富友开户
            	break;
            case "11020004"://委托出借开户
            	accType="10010002";//委托出借账户
            	channelNo="97010001";//富友开户
            	break;
            case "11020005"://借款人开户（冠e通）
            	accType="10010003";//借款账户
            	channelNo="97010001";//富友开户
            	break;
            case "11020006"://代偿人开户
            	accType="10010005";//代偿人账户
            	channelNo="97010001";//富友开户
            	break;
            case "11020007"://抵押权人开户
            	accType="10010006";//抵押权人账户
            	channelNo="97010001";//富友开户
            	break;
            case "11020008"://保理合同开户
            	accType="10010004";//保理业务账户
            	channelNo="97010001";//富友开户
            	break;
            case "11020009"://借款人（纯线下）开户
            	accType="10010003";
            	channelNo="97010000";//本地账户
            	break;
            case "11020010"://借款人开户（借款系统）
            	accType="10010003";
            	channelNo="97010001";//富友开户
            	break;
            case "11029001"://借款人（出借系统）银行卡变更
            	accType="10010003";
            	channelNo="97010001";//富友开户
            	break;
            case "11029002"://线下出借人（借款系统）银行卡变更
            	accType="10011000";
            	channelNo="97010001";//富友开户
            	break;
            case "11029003"://互联网客户银行卡变更
            	accType="10010001";
            	channelNo="";//富友开户
            	break;
            case "11029004"://银行号变更（纯线下）
            	accType="10011000";//公司账户
            	channelNo="97010000";//本地账户
            	break;
            default:
            	throw new FssException("90008403");//交易类型错误
            }
           /* 
            97010000  本地账户
            97010001  上海富友
            97010002  银联商务（第二方）
            */
            fssAccountEntity.setAccType(Integer.parseInt(accType));
            fssAccountEntity.setState(10020001);//默认为有效账户
            fssAccountEntity.setBusiNo(dto.getContract_id());
            fssAccountEntity.setCustId(fssCustomerEntity.getId());
            //设置开户来源
            //设置渠道id
            fssAccountEntity.setChannelNo(Integer.parseInt(channelNo));//根据tradeType匹配
            fssAccountEntity.setMchnChild(dto.getMchn());
            fssAccountEntity.setMchnParent(Application.getInstance().getParentMchn(dto.getMchn()));
            fssAccountWriteMapper.insertSelective(fssAccountEntity);
            return fssAccountEntity;
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
            if(e != null && e.getMessage().contains("busi_no_uk")){
                throw new FssException("90002017");
            }else{
                throw new FssException("90099005");
            }

        }
    }
    
    /**
     * 根据cust_id查询账户
     * @return
     */
    public FssAccountEntity getFssAccountByCustId(Integer custId){
    	FssAccountEntity fssAccountEntity=new FssAccountEntity();
    	fssAccountEntity.setCustId(custId);
    	accountReadMapper.selectOne(fssAccountEntity);
    	return fssAccountEntity;
    	
    }
    /**
     * 
     * author:jhz
     * time:2016年3月17日
     * function：根据acc_no查询账户
     */
    public FssAccountEntity getFssAccountByAccNo(String accNo){
    	return this.accountReadMapper.findAccountByAccNo(accNo);
    }
    
    /**
     * 生成10位随机数
     * @return
     */
	public StringBuffer getRedom(){
		StringBuffer sb=new StringBuffer();
		int a[] = new int[10];
	      for(int i=0;i<a.length;i++ ) {
	          a[i] = (int)(10*(Math.random()));
	          System.out.print(a[i]);
	          sb.append(a[i]+"");
	      }
	      return sb;
	}
	/**
	 * 生成2位随机数
	 * @return
	 */
	public StringBuffer getSecond(){
		StringBuffer sb=new StringBuffer();
		int a[] = new int[2];
		for(int i=0;i<a.length;i++ ) {
			a[i] = (int)(2*(Math.random()));
			System.out.print(a[i]);
			sb.append(a[i]+"");
		}
		return sb;
	}


}
