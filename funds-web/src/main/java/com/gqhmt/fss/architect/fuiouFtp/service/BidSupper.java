package com.gqhmt.fss.architect.fuiouFtp.service;

import com.gqhmt.business.architect.loan.entity.Bid;
import com.gqhmt.business.architect.loan.entity.Tender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.fss.architect.fuiouFtp.service.BidSupper
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/7/14 11:02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/7/14  于泳      1.0     1.0 Version
 */
public class BidSupper {

    protected Map<String,String> titleMap = new ConcurrentHashMap<>();

    protected Map<String,Bid> bidMap = new ConcurrentHashMap<>();

    protected Map<String,Tender> tenderMap = new ConcurrentHashMap<>();

    protected Map<String,Tender> bidRepaymentMap = new ConcurrentHashMap<>();

}
