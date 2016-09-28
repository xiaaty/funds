package com.gqhmt.extServInter.service.bonus.impl;

import com.google.common.collect.Lists;
import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.bonus.BonusDto;
import com.gqhmt.extServInter.service.bonus.IBonus;
import com.gqhmt.fss.architect.bonus.bean.BonusBean;
import com.gqhmt.fss.architect.bonus.entity.FssBonusEntity;
import com.gqhmt.fss.architect.bonus.entity.FssBonusParentEntity;
import com.gqhmt.fss.architect.bonus.service.FssBonusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月20日
 * Description:  红包批量体现
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月20日  柯禹来      1.0     1.0 Version
 */
@Service
public class BonusImpl implements IBonus {
	
	@Resource
	private FssBonusService fssBonusService;

	@APITradeTypeValid(value = "11130001")
	public Response execute(SuperDto dto) {
    	Response response = new Response();
    	try {
			BonusDto bDto=(BonusDto)dto;
			FssBonusParentEntity entity=fssBonusService.creatParentParentBonus(bDto.getMchn(),bDto.getSeq_no(),bDto.getTrade_type(),bDto.getBonus_list().size());
			List<FssBonusEntity> list= Lists.newArrayList();
			for (BonusBean bean:bDto.getBonus_list()) {
				FssBonusEntity bonusEntity= fssBonusService.createBonus(bDto.getMchn(),bean.getSeq_no(),bDto.getTrade_type(),bean.getCust_id(),bean.getBusi_no(),bean.getAmount(),entity.getId(),bean.getBusi_type());
				list.add(bonusEntity);
			}
			fssBonusService.insertList(list);
			response.setResp_code("0000");
		} catch (FssException e) {
			LogUtil.error(this.getClass(), e);
			response.setResp_code(e.getMessage());
		}
        return response;
    }
}
