package com.gqhmt.fss.logicService.pay;


import com.gqhmt.fss.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.fss.logicService.pay.exception.FundsException;

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
     * @throws FundsException
     */
    public FundsResponse createAccount(String thirdPartyType, int custId) throws FundsException;


    /**
     * 创建账户
     *
     * @param thirdPartyType     支付渠道
     * @param customerInfoEntity 客户实体
     * @param pwd                支付渠道登陆密码
     * @param taradPwd           支付渠道交易密码
     * @throws FundsException
     */
    public FundsResponse createAccount(String thirdPartyType, CustomerInfoEntity customerInfoEntity, String pwd, String taradPwd) throws FundsException;


    /**
     * 销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FundsException
     */
    public FundsResponse dropAccount(String thirdPartyType, int custID) throws FundsException;


    /**
     * 销户确认，客户已经发起销户申请，才可以完成销户确认，客户可以在富友发起申请，或者客户致电客服发起销户申请
     *
     * @param thirdPartyType 支付渠道
     * @param custID         客户id
     * @throws FundsException
     */
    public FundsResponse checkDropAccount(String thirdPartyType, int custID) throws FundsException;



    /**
     * 银行卡变更
     * @param thirdPartyType            支付渠道
     * @param cusId                     客户id
     * @param cardNo                    银行卡号
     * @param bankCd                    开户行行别
     * @param bankNm                    开户行支行名称
     * @param cityId                    开户区县代码
     * @param imagePath                 上传照片路径
     * @throws FundsException
     */
    public FundsResponse changeCard(String thirdPartyType, Integer cusId, String cardNo, String bankCd, String bankNm, String cityId, String imagePath) throws FundsException;


    /**
     * 变更富友回调短信
     * @param thirdPartyType            支付渠道
     * @param cusId                     客户id
     * @param cztx                      充值提现
     * @param cz                        出账
     * @param rz                        入账
     * @param hz                        汇总
     * @throws FundsException
     */
    public FundsResponse setMms(String thirdPartyType,Integer cusId,String cztx,String cz ,String rz,String hz) throws FundsException;




}
