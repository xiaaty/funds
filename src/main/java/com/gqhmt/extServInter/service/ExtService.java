package com.gqhmt.extServInter.service;

import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.SuperDto;
import com.gqhmt.core.APIExcuteErrorException;

/**
 * Filename:    com.gqhmt.extServInter.service.ExtService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/2/19 14:56
 * Description:
 * <p> 标记service,无任何定义方法
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/2/19  于泳      1.0     1.0 Version
 */
public interface ExtService {

    public Response excute(SuperDto dto) throws APIExcuteErrorException;

	
}
