package com.gqhmt.controller.api.depos;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.depos.ProjectInfoDto;
import com.gqhmt.extServInter.service.depos.IProjectInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/7/5
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/7/5  jhz     1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssDeposProjectInfo {
    @Resource

    private IProjectInfo projectInfoImpl;

    /**
     * jhz
     * 获取标的信息
     * @param projectInfoDto
     * @return
     */
    @RequestMapping(value = "/depos/projectInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public Object createAccount(@RequestBody ProjectInfoDto projectInfoDto){
        Response response=new Response();
        try {
             response= projectInfoImpl.execute(projectInfoDto);
        }catch (FssException e){
            LogUtil.error(this.getClass(), e);
        }
        return response;

    }
}
