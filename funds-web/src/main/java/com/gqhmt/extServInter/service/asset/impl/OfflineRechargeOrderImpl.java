package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.extServInter.dto.QueryListResponse;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.OfflineRechargeListDto;
import com.gqhmt.extServInter.service.asset.IOfflineRechargeOrder;
import com.gqhmt.fss.architect.trade.bean.FssOfflineRechargeBean;
import com.gqhmt.fss.architect.trade.service.FssOfflineRechargeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.extServInter.service.asset.impl.RechargeAndWitwdrawOrder
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/8 16:34
 * Description:客户线下充值列表
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/8  柯禹来      1.0     1.0 Version
 */
@Service
public class OfflineRechargeOrderImpl implements IOfflineRechargeOrder {

    @Resource
    private FssOfflineRechargeService fssOfflineRechargeService;

    @AutoPage
    @Override
    @APITradeTypeValid(value = "11110004")
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
        QueryListResponse response = new QueryListResponse();
        try {
            OfflineRechargeListDto cDto = (OfflineRechargeListDto) dto;
            List<FssOfflineRechargeBean> list = fssOfflineRechargeService.getOfflineRechargeByCustId(cDto.getCust_id(),cDto.getCust_type(),cDto.getStr_trade_time(),cDto.getEnd_trade_time());
            response.setResp_code("0000");
            response.setPlain(list);
        }catch (Exception e){
            response.setResp_code(e.getMessage());
        }
        return response;
    }
}
