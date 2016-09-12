package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.fss.architect.trade.entity.FssTradeInfoEntity;
import com.gqhmt.fss.architect.trade.mapper.read.FssTradeInfoReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.FssTradeInfoWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/6.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/6.  xdw         1.0     1.0 Version
 */
@Service
public class FssTradeInfoService {

    @Resource
    private FssTradeInfoReadMapper fssTradeInfoReadMapper;

    @Resource
    private FssTradeInfoWriteMapper fssTradeInfoWriteMapper;

    public List<FssTradeInfoEntity> listTradeInfo(Map<String, String> map) {
        return fssTradeInfoReadMapper.listTradeInfo(map);
    }

    public void insertListTradeInfo(List<FssTradeInfoEntity> listTradeInfo){
        fssTradeInfoWriteMapper.insertList(listTradeInfo);
    }
}
