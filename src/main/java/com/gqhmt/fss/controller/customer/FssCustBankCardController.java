package com.gqhmt.fss.controller.customer;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.fss.architect.customer.entity.CustomerAndUser;
import com.gqhmt.fss.architect.customer.entity.FssCustBankCardEntity;
import com.gqhmt.fss.architect.customer.service.FssCustBankCardService;
import com.gqhmt.sys.entity.Menu;

/**
 * Filename:    com.gqhmt.sys.controller.MenuController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/12/19 7:46
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/12/19  于泳      1.0     1.0 Version
 */

@Controller
public class FssCustBankCardController {
	
	@Resource
	private FssCustBankCardService bankCardService;
	
	/**
	 * author:jhz
	 * time:2016年1月25日
	 * function：银行卡信息列表
	 */						
	@RequestMapping(value = "/fss/customer/bankCards",method = RequestMethod.GET)
    @AutoPage
    public Object bankCardList(HttpServletRequest request,ModelMap model){
        List<CustomerAndUser> bankCards  = bankCardService.findbankCardAll();
        		model.addAttribute("page",bankCards);
        return "fss/customer/bankCardList";
    }
}
