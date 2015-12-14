package com.gqhmt.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gqhmt.sys.beans.SysAuthFunc;
import com.gqhmt.sys.mapper.read.FuncListReadMapper;

import javax.annotation.Resource;

@Service
public class FuncListService {
	
	@Resource
	private FuncListReadMapper funcListReadMapper;
	
	public List<SysAuthFunc> selectFuncList(Map<String,Object> map){
		return funcListReadMapper.selectFuncList(map);
	}
}
