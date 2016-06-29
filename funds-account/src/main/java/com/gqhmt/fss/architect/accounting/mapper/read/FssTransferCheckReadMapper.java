package com.gqhmt.fss.architect.accounting.mapper.read;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.fss.architect.accounting.entity.FssTransferCheckEntity;

import java.util.List;
import java.util.Map;


public interface FssTransferCheckReadMapper extends ReadMapper<FssTransferCheckEntity>{
	/**
	 * jhz
	 * 条件查询所有划拨对账
	 * @param map
	 * @return
     */
	  List<FssTransferCheckEntity> queryTransferCheck(Map map);
}
