package com.gqhmt.extServInter.service.account;

import com.gqhmt.annotations.APITradeTypeValid;
import com.gqhmt.core.exception.APIExcuteErrorException;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.extServInter.dto.account.UpdateBankCardAnotherDto;
import com.gqhmt.extServInter.dto.account.UpdateBankCardByIdDto;
import com.gqhmt.extServInter.service.ExtService;
import com.gqhmt.fss.architect.customer.service.FssChangeCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年07月26日
 * Description:	银行卡变更
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年07月26日  xdw      1.0     1.0 Version
 */
public interface IChangeBankCardAnotherById extends ExtService {
	
}
