package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.FssException;
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
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
   
    public List<FssAccountEntity> findCustomerAccountByParams(Map map){
        return this.accountReadMapper.findCustomerAccountByParams(map);
    }


    public List<BussAndAccountBean> queryAccountList(Map map)throws FssException {
        return this.accountReadMapper.getBussinessAccountList(map);
    }


    private void createAccount(SuperDto dto) throws FssException {
        //生成客户信息
        FssCustomerEntity fssCustomerEntity = fssCustomerService.create(dto);
        //生成银行卡信息
        //生成第三方开户账户信息
        FssFuiouAccountEntity fssFuiouAccountEntity = this.createFuiouAccount(dto,fssCustomerEntity);
        //生成本地账户

    }


    private FssFuiouAccountEntity createFuiouAccount(SuperDto dto,FssCustomerEntity fssCustomerEntity) throws FssException {
        try {
            FssFuiouAccountEntity fssFuiouAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssFuiouAccountEntity.class,dto);
            fssFuiouAccountEntity.setCusNo(fssCustomerEntity.getCustNo());
            fssFuiouAccountEntity.setUserNo(fssCustomerEntity.getUserId());
            fssFuiouAccountEntity.setAccNo(fssCustomerEntity.getMobile());
            fssFuiouAccountEntity.setAccUserName(fssCustomerEntity.getName());
            fssFuiouAccountWriteMapper.insertSelective(fssFuiouAccountEntity);
            return fssFuiouAccountEntity;
        } catch (Exception e) {
            LogUtil.error(this.getClass(),e);
            //生成错误码
            throw  new FssException("");
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
    	StringBuffer busi_no=new StringBuffer("1001");
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
    
    
    
    public FssAccountEntity createFssAccountEntity(CreateLoanAccountDto dto,CustomerInfoEntity fssCustomerEntity) throws FssException {
        try {
            FssAccountEntity fssAccountEntity = GenerateBeanUtil.GenerateClassInstance(FssAccountEntity.class,dto);
            fssAccountEntity.setCustNo(String.valueOf(fssCustomerEntity.getId()));
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
     * 根据cust_id查询账户
     * @param id
     * @return
     */
    public FssAccountEntity getFssAccountByCustId(Integer custId){
    	FssAccountEntity fssAccountEntity=new FssAccountEntity();
    	fssAccountEntity.setCustId(custId);
    	accountReadMapper.selectOne(fssAccountEntity);
    	return fssAccountEntity;
    	
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
