package com.gqhmt.extServInter.service.cost.impl;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.extServInter.service.cost.ICharges;
import com.gqhmt.pay.service.cost.ICost;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * Created by 57627 on 2016/5/27.
 */
@Service
public class ChargesImpl implements ICharges {
    @Resource
    private ICost costImpl;
    @Override
    public Response execute(SuperDto dto) throws APIExcuteErrorException {
        Response response = new Response();
        try {
            costImpl.charge((CostDto)dto);
            response.setResp_code("0000");
        } catch (FssException e) {
            LogUtil.info(this.getClass(), e.getMessage());
            response.setResp_code(e.getMessage());
        }
        return response;
    }

}
