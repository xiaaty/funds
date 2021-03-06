package com.gqhmt.funds.architect.trade.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.funds.architect.trade.entity.FuiouPreauth;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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

    public List<FuiouPreauth> getContractNo(@Param("bid") Long bid,@Param("userName") String userName ,@Param("toUserName") String toUserName,@Param("amt") Long amt);

    public List<FuiouPreauth> getContractNos(@Param("bid") Long bid);

//   public Map<Integer, FuiouPreauth> getFuiouPreauth(Long bid);

    public List<FuiouPreauth> bidFaild();

}