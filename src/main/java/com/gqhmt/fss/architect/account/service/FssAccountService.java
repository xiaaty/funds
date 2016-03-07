package com.gqhmt.fss.architect.account.service;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GenerateBeanUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.fss.architect.account.bean.BussAndAccountBean;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.entity.FssFuiouAccountEntity;
import com.gqhmt.fss.architect.account.mapper.read.FssAccountReadMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssAccountWriteMapper;
import com.gqhmt.fss.architect.account.mapper.write.FssFuiouAccountWriteMapper;
import com.gqhmt.fss.architect.customer.entity.FssCustomerEntity;
import com.gqhmt.fss.architect.customer.service.FssCustomerService;
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
    private String getAccno(String trade_type) {

        return "";
    }


}
