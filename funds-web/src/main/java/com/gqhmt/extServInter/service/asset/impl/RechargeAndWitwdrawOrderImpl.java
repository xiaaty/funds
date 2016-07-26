package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.extServInter.dto.QueryListResponse;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.RechargeAndWithdrawListDto;
import com.gqhmt.extServInter.service.asset.IRechargeAndWithdrawOrder;
import com.gqhmt.funds.architect.order.bean.FundOrderBean;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Filename:    com.gqhmt.extServInter.service.asset.impl.RechargeAndWitwdrawOrder
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/8 16:34
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/8  于泳      1.0     1.0 Version
 */
@Service
public class RechargeAndWitwdrawOrderImpl implements IRechargeAndWithdrawOrder {

    @Resource
    private FundOrderService fundOrderService;

    @AutoPage
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
        QueryListResponse response = new QueryListResponse();
        try {
            RechargeAndWithdrawListDto dd = (RechargeAndWithdrawListDto) dto;
            List<FundOrderBean> list = fundOrderService.getFundOrderReWithList(Integer.parseInt(dd.getCust_no()),dd.getType(),dd.getStr_trade_time(),dd.getEnd_trade_time());
            response.setResp_code("00000000");
            response.setPlain(list);
        }catch (Exception e){
            response.setResp_code(e.getMessage());
        }

        return response;
    }
}
