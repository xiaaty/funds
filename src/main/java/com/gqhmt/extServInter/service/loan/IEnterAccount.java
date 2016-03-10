package com.gqhmt.extServInter.service.loan;
import java.util.List;

import com.gqhmt.core.APIExcuteErrorException;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.loan.EnterAccountDto;

/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年3月9日
 * Description:
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年3月9日  jhz      1.0     1.0 Version
 */
public interface IEnterAccount  {
	
	public Response excute(List<EnterAccountDto> enterAccountDtos) throws APIExcuteErrorException;

}
