package com.gqhmt.extServInter.service.depos.Impl;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.depos.ProjectInfoDto;
import com.gqhmt.extServInter.dto.trade.BondTransferDto;
import com.gqhmt.extServInter.service.depos.IProjectInfo;
import com.gqhmt.fss.architect.depos.service.FssProjectInfoService;
import com.gqhmt.pay.service.trade.IFundsTrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @authorjhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年7月06日
 * Description:	项目信息
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年7月06日  jhz      1.0     1.0 Version
 */
@Service
public class ProjectInfoImpl implements IProjectInfo {
	@Resource
	private FssProjectInfoService fssProjectInfoService;

    /**
	 * jhz
     * @param dto
     * @return
     */
	@APITradeTypeValid(value = "11120004")
    @Override
    public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			ProjectInfoDto pDto = (ProjectInfoDto)dto;
			fssProjectInfoService.insertProjectInfo(pDto.getTrade_type(),pDto.getSeq_no(),pDto.getMchn(),pDto.getLoanType(),pDto.getLoanTittle(),pDto.getOrganization(),pDto.getDescription(),pDto.getLoanAmt(),pDto.getExpectedReturn(),pDto.getProductName(),pDto.getRepaymentType(),pDto.getLoanTime(),pDto.getStartDate(),pDto.getEachBidAmount(),pDto.getMinNum(),pDto.getMaxAmount(),pDto.getLoanItemDescription(),pDto.getFeeType(),pDto.getTradeStatus(),pDto.getPeriod(),pDto.getPrepareAmount(),pDto.getPayChannel(),pDto.getBidYearIrr(),pDto.getBorrowType(),pDto.getLicenseNo(),pDto.getCustName(),pDto.getCertType(),pDto.getCertNo(),pDto.getFilePath(),pDto.getCustId(),pDto.getBusi_no());
            response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
