package com.gqhmt.fss.architect.depos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.depos.entity.FssCorporateEntity;
import com.gqhmt.fss.architect.depos.mapper.read.FssCorporateReadMapper;

/**
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2016
 * Company:     冠群驰骋投资管理(北京)有限公司
 * @author 柯禹来
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年5月17日
 * Description:
 * <p>p2p法人平台开户文件
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年5月17日 柯禹来      1.0     1.0 Version
 */
@Service
public class FssCorporateService {
	@Resource
	private FssCorporateReadMapper fssCorporateReadMapper;
	
	public List<FssCorporateEntity> queryCorporateList(Map<String,String> map)throws FssException {
		Map<String, String> map2=new HashMap<String, String>();
		if(map!=null){
			map2.put("mchn",map.get("mchn"));
			map2.put("itemNo", map.get("itemNo"));
		}
		return fssCorporateReadMapper.queryCorporateList(map2);
	}
	
}
