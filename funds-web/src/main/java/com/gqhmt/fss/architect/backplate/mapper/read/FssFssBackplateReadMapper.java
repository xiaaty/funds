package com.gqhmt.fss.architect.backplate.mapper.read;


import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月19日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月19日  jhz      1.0     1.0 Version
 */
public interface FssFssBackplateReadMapper extends ReadMapper<FssBackplateEntity>{

    public List<FssBackplateEntity> findBackAll();


    public List<FssBackplateEntity> findBackAllByTime(@Param("repayCount") int repayCount,@Param("timeType") int timeType);

    public List<FssBackplateEntity> selectBackPlateByParam(Map map);
    /**
     *
     * author:jhz
     * time:2016年6月20日
     * function:通过商户号流水号查询
     */
    public FssBackplateEntity selectByMchnAndseqNo(@Param("mchn")String mchn,@Param("seqNo")String seqNo);
}
