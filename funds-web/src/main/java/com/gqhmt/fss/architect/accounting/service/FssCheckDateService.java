package com.gqhmt.fss.architect.accounting.service;

import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
import com.gqhmt.fss.architect.accounting.mapper.read.FssCheckDateReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssCheckDateWriteMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/1
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/1  jhz     1.0     1.0 Version
 */
@Service
public class FssCheckDateService {

    @Resource
    private FssCheckDateReadMapper fssCheckDateReadMapper;

    @Resource
    private FssCheckDateWriteMapper fssCheckDateWriteMapper;

    /**
     * 查询对账日期
     * @return
     */
    public String queryInputDate() {
        FssCheckDate fssCheckDate = fssCheckDateReadMapper.queryInputDate();
        String inputDate = StringUtils.isEmpty(fssCheckDate.getInputDate()) ? "" : fssCheckDate.getInputDate();
        return inputDate;
    }

    /**
     * 更新已对账日期状态
     * @param inputDate
     * @return
     */
    public int updateInputDate(String inputDate) {
        return fssCheckDateWriteMapper.updateInputState(inputDate);
    }

}
