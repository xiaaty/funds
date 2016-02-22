package com.gqhmt.pay.service;


import com.gqhmt.core.FssException;
import com.gqhmt.extServInter.dto.account.AccountAccessDto;
import com.gqhmt.extServInter.dto.account.AssetDto;
import com.gqhmt.fss.architect.asset.entity.FssAssetEntity;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.extServInter.dto.account.ChangeBankCardDto;
import com.gqhmt.extServInter.dto.account.ChangeBankCardResultDto;
import com.gqhmt.extServInter.dto.account.CreateAccountByFuiouDto;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;

/**
 * Filename:    com.gq.funds.interaction.IFundsAccount
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/17 下午5:27
 * Description:
 * <p>
 *      账户相关操作，包含
 *      <ol>
 *          <li>创建账户</li>
 *          <li>冻结账户</li>
 *          <li>解冻账户</li>
 *          <li>销户申请</li>
 *          <li>销户复核</li>
 *          <li>银行卡变更</li>
 *          <li>银行卡变更结果查询</li>
 *          <li>第三方短信设置--富友</li>
 *      </ol>
 *
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/17  于泳      1.0     1.0 Version
 */
public interface IFundsAccount {


    /**
     * 创建账户
     *
     * @param thirdPartyType 支付渠道
     * @param custId         客户id
     * @throws FssException
     */
    public boolean createAccount(CreateAccountByFuiouDto  createAccountByFuiouDto) throws FssException;


    /**
     * 创建账户
     *
     * @param thirdPartyType     支付渠道
     * @param customerInfoEntity 客户实体
     * @param pwd                支付渠道登陆密码
     * @param taradPwd           支付渠道交易密码
     * @throws FssException
     */
    public boolean createAccount(CustomerInfoEntity customerInfoEntity,
			String pwd, String taradPwd) throws FssException;


    /**
     * 销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FssException
     */
    public boolean dropAccount(String thirdPartyType, int custID) throws FssException;


    /**
     * 销户确认，客户已经发起销户申请，才可以完成销户确认，客户可以在富友发起申请，或者客户致电客服发起销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FssException
     */
    public boolean checkDropAccount(String thirdPartyType, int custID) throws FssException;



    /**
     * 银行卡变更
     * @param thirdPartyType            支付渠道

     * @throws FssException
     */
    public boolean changeCard(ChangeBankCardDto changeBankCardDto) throws FssException;
    
    /**
     * 银行卡变更结果查询

     * @throws FssException
     */
    public boolean changeCardResult(ChangeBankCardResultDto changeBankCardResultDto) throws FssException;


    /**
     * 变更富友短信发送类型
     * @param thirdPartyType            支付渠道
     * @param cusId                     客户id
     * @param cztx                      充值提现
     * @param cz                        出账
     * @param rz                        入账
     * @param hz                        汇总
     * @throws FssException
     */
    public boolean setMms(String thirdPartyType,Integer cusId,String cztx,String cz ,String rz,String hz) throws FssException;
    
    /**
     * 查询用户账户余额
     * @param accessdto
     * @return
     * @throws FssException
     */
    public FundAccountEntity getAccountAccByCustId(AccountAccessDto accessdto) throws FssException;
    
    /**
     * 查询账户资产
     * @param accessdto
     * @return
     * @throws FssException
     */
    public FssAssetEntity getAccountAsset(AssetDto asset) throws FssException;

}
