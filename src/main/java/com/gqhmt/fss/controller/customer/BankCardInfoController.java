package com.gqhmt.fss.controller.customer;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Filename:    com.gqhmt.fss.controller.BankCardInfoController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 李俊龙
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015年12月21日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/21  李俊龙      1.0     1.0 Version
 */
@Controller
public class BankCardInfoController {

    private BankCardInfoService bankCardInfoService;

    private BankCardInfoEntity entity;
    
    /**
     * 旧版商户列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fss/customer/cardlist",method = RequestMethod.GET)
	@AutoPage
    public Object accountOldList(HttpServletRequest request,ModelMap model){
//		List<BankCardInfoEntity> cardList = bankCardInfoService.queryBankCardList(entity);entity
//		model.addAttribute("page", cardList);
		return "fss/customer/bankCardList";
    }

	public BankCardInfoEntity getEntity() {
		return entity;
	}

	public void setBean(BankCardInfoEntity entity) {
		this.entity = entity;
	}
    
}
