package com.gqhmt.extServInter.service.cost.impl;

import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.extServInter.service.cost.ICharges;
import com.gqhmt.pay.service.cost.ICost;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 费用接口
 */
@Service
public class ChargesImpl implements ICharges {
    @Resource
    private ICost costImpl;
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
        Response response = new Response();
        try {
            CostDto cdto=(CostDto)dto;
            costImpl.charge(cdto.getPlatform(), cdto.getTrade_type(), cdto.getCust_no(), cdto.getBusi_type(),cdto.getAmt(),cdto.getAccounts_type());
            response.setResp_code("0000");
        } catch (FssException e) {
            LogUtil.info(this.getClass(), e.getMessage());
            response.setResp_code(e.getMessage());
        }
        return response;
    }

}
