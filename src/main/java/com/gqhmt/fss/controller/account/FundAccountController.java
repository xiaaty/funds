package com.gqhmt.fss.controller.account;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Filename:    com.gqhmt.sys.controller.MenuController
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
public class FundAccountController {

    @Resource
    private FundAccountService fundAccountService;

    private FundAccountEntity entity;
    
    /**
     * 旧版商户列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fss/account/oldlist",method = RequestMethod.GET)
	@AutoPage
    public Object accountOldList(HttpServletRequest request,ModelMap model){
//		List<FundAccountEntity> accountList = fundAccountService.queryFundsAccountList(entity);
//		model.addAttribute("page", accountList);
		return "fss/account/accountList";
    }

	public FundAccountEntity getEntity() {
		return entity;
	}

	public void setBean(FundAccountEntity entity) {
		this.entity = entity;
	}
    
}
