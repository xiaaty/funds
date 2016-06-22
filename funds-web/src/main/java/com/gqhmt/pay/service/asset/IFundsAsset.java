package com.gqhmt.pay.service.asset;


import com.gqhmt.core.exception.FssException;

import java.util.Map;

/**
 * Filename:    com.gq.funds.interaction.IFundsAsset
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/21 下午2:09
 * Description:
 * <p>
 *     账户资产类，预留，未来处理账户资产用类
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/21  于泳      1.0     1.0 Version
 */
public interface IFundsAsset {

    /**
     * 线上账户余额
     * @param custId                   客户id
     * @return
     * @throws FssException
     */
    public Map balance(int custId) throws FssException;

    /**
     * 业务账户余额
     * @param custId                客户id
     * @param businesstype          业务类型
     * @param contractNo            合同编号
     * @return
     */
    public Map balance(int custId,int businesstype,String contractNo) throws FssException;

    /**
     * 特殊账户余额
     * @param custId                客户id
     * @param type                  特殊账户类型
     * @return
     * @throws FssException
     */
    public Map balance(int custId,int type) throws FssException;


    /**
     * 线上客户资产信息  当前持有资产=线上账户余额+投资本金（总）- 已还本金（总）
     * @param custId
     * @return
     * @throws FssException
     */
    public Map asset(int custId) throws FssException;


    /**
     * 线上账户客户收益信息   预期收益（由业务系统提供 并记录应收账中)
     * @param custId
     * @return
     * @throws FssException
     */
    public Map prospectiveEarning(int custId) throws FssException;

    /**
     * 线上客户已获收益
     * @param custID
     * @return
     * @throws FssException
     */
    public Map income(int custID) throws FssException;


    //**********************************************************************/

    /**
     * 线下客户合同资产信息  当前持有资产=线上账户余额+投资本金（总）- 已还本金（总）
     * @param custId
     * @return
     * @throws FssException
     */
    public Map asset(int custId,String contract) throws FssException;


    /**
     * 线上账户客户收益信息   预期收益（由业务系统提供 并记录应收账中)
     * @param custId
     * @return
     * @throws FssException
     */
    public Map prospectiveEarning(int custId,String contract) throws FssException;

    /**
     * 线上客户已获收益
     * @param custID
     * @return
     * @throws FssException
     */
    public Map income(int custID,String contract) throws FssException;


    /********************************************************************************/

    /**
     * 资产负债，资产负债，对应合同
     * @param custID
     * @param contract
     * @return
     * @throws FssException
     */

    public Map liabilities(int custID,String contract) throws FssException;


    /**
     * 资产负债，借款客户所有负债
     * @param custID
     * @return
     * @throws FssException
     */
    public Map liabilities(int custID) throws FssException;
    

}
