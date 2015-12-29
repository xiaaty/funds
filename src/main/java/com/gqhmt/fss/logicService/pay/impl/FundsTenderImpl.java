package com.gqhmt.fss.logicService.pay.impl;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.BidRepayment;
import com.gqhmt.business.architect.loan.entity.Tender;
import com.gqhmt.fss.logicService.pay.FundsResponse;
import com.gqhmt.fss.logicService.pay.IFundsTender;
import com.gqhmt.fss.logicService.pay.exception.FundsException;

/**
 * Filename:    com.gqhmt.fss.logicService.pay.impl.FundsTenderImpl
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/12/29 13:01
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/12/29  于泳      1.0     1.0 Version
 */
public class FundsTenderImpl implements IFundsTender {
    @Override
    public FundsResponse notice(String thirdPartyType, Bid bid) throws FundsException {
        return null;
    }

    @Override
    public FundsResponse notice(String thirdPartyType, Long id) throws FundsException {
        return null;
    }

    @Override
    public FundsResponse bid(String thirdPartyType, Tender tender, Bid bid) throws FundsException {
        return null;
    }

    @Override
    public FundsResponse bid(String thirdPartyType, Tender tender) throws FundsException {
        return null;
    }

    @Override
    public FundsResponse sellte(String thirdPartyType, Bid bid) throws FundsException {
        return null;
    }

    @Override
    public FundsResponse repayment(String thirdPartyType, Bid bid, BidRepayment bidRepayment) throws FundsException {
        return null;
    }

    @Override
    public FundsResponse abort(String thirdPartyType, Tender tender) throws FundsException {
        return null;
    }

    @Override
    public FundsResponse abort(String thirdPartyType, Bid bid) throws FundsException {
        return null;
    }
}
