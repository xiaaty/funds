package com.gqhmt.sys.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sys.beans.SysAuthFunc;

public interface FuncListReadMapper{
	public List<SysAuthFunc> selectFuncList(Map<String,Object> map);
}
