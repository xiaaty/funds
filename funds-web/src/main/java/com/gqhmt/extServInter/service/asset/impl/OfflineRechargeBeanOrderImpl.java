package com.gqhmt.extServInter.service.asset.impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.asset.OfflineRechargeBeanDto;
import com.gqhmt.extServInter.dto.asset.OfflineRechargeBeanResponse;
import com.gqhmt.extServInter.service.asset.IOfflineRechargeBenaOrder;
import com.gqhmt.fss.architect.trade.entity.FssOfflineRechargeEntity;
import com.gqhmt.fss.architect.trade.service.FssOfflineRechargeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class OfflineRechargeBeanOrderImpl implements IOfflineRechargeBenaOrder {

    @Resource
    private FssOfflineRechargeService fssOfflineRechargeService;

    @Override
    @APITradeTypeValid(value = "11110005")
    public Response execute(SuperDto dto) throws APIExcuteErrorException {

        OfflineRechargeBeanDto offlineRechargeBeanDto = (OfflineRechargeBeanDto) dto;
        FssOfflineRechargeEntity fssOfflineRechargeEntity = fssOfflineRechargeService.getOfflineRechargeById(offlineRechargeBeanDto.getOfflineRechargeId());
        OfflineRechargeBeanResponse response = new OfflineRechargeBeanResponse();
        if(fssOfflineRechargeEntity == null){
            response.setResp_code("90009001");
        }else{
            if(fssOfflineRechargeEntity.getCustId().longValue() != offlineRechargeBeanDto.getCust_id().longValue()){
                response.setResp_code("90009001");
            }else{
                response.setResp_code("0000");
                response.setOfflineRecharge(fssOfflineRechargeEntity);
            }

        }
        return response;
    }
}
