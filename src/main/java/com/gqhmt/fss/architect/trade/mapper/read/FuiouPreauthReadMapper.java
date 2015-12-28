package com.gqhmt.fss.architect.trade.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.trade.entity.FuiouPreauth;

/**
 * Filename:    com.gq.p2p.account.dao
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/1/15 16:03
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/1/15  于泳      1.0     1.0 Version
 */
public interface FuiouPreauthReadMapper extends ReadMapper<FuiouPreauth> {

    public List<FuiouPreauth> getContractNo(Long bid,String userName ,String toUserName,Long amt);

    public Map<Integer,String> getContractNo(Long bid);

    public Map<Integer, FuiouPreauth> getFuiouPreauth(Long bid);

    public List<FuiouPreauth> bidFaild();

}