package com.gqhmt.pay.service.cost;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import java.math.BigDecimal;

/**
 * Filename:    com.gqhmt.pay.service.cost.ICost
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/11 10:02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/11  于泳      1.0     1.0 Version
 */
public interface ICost {


    /**
     * 涉及平台收费
     * @param loanType
     * @param fundsType
     * @param custId
     * @param bustType
     * @param decimal
     */
    public void cost(String loanType,String  fundsType, Long  custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType) throws FssException;


    /**
     * 涉及平台收费
     * @param loanType
     * @param fundsType
     * @param accNo
     * @param decimal
     */
    public FundOrderEntity cost(String loanType, String  fundsType, String accNo, BigDecimal decimal, Long busiId, Integer busiType) throws FssException;


    /**
     * 非平台收费
     * @param fundsType
     * @param custId
     * @param bustType
     * @param decimal
     */
    public void cost(String  fundsType, Long  custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType) throws FssException;


    public FundOrderEntity costReturn(String  fundsType, Long  custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType) throws FssException;

    public FundOrderEntity costReturn(String loanType, String  fundsType, String accNo, BigDecimal decimal, Long busiId, Integer busiType) throws FssException;

    public boolean charge(CostDto dto) throws FssException;

}
