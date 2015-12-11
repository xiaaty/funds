package com.gqhmt.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gqhmt.util.GlobalConstants;
import com.gqhmt.sys.beans.SysAuth;
import com.gqhmt.sys.beans.SysAuthFunc;
import com.gqhmt.sys.mapper.read.SysAuthReadMapper;
import com.gqhmt.sys.mapper.write.SysAuthWriteMapper;

@Service
public class SysAuthService {
	@Autowired
	private SysAuthReadMapper sysAuthReadMapper;
	@Autowired
	private SysAuthWriteMapper sysAuthWriteMapper;
	
	public Map<Long,List<SysAuthFunc>> selectRoleAuth(){
		List<SysAuthFunc> funcList=sysAuthReadMapper.selectRoleFuncs();
		ConcurrentMap<Long,List<SysAuthFunc>>  authMap=new  ConcurrentHashMap<Long,List<SysAuthFunc>>();
		List<SysAuthFunc> funcs=null;
		for(SysAuthFunc sysAuthFunc:funcList){
			if(authMap.containsKey(sysAuthFunc.getRoleId())){
				authMap.get(sysAuthFunc.getRoleId()).add(sysAuthFunc);
				continue;
			}
			funcs=new ArrayList<SysAuthFunc>();
			funcs.add(sysAuthFunc);
			authMap.put(sysAuthFunc.getRoleId(),funcs);
		}
		return authMap;
	}
	public Map<Long,Long> selectRoleAuthByRoleId(int rid){
		List<SysAuth> sysAuths=sysAuthReadMapper.selectRoleFuncByRoleId(rid);
		Map<Long,Long> map=new ConcurrentHashMap<Long, Long>();
		for(SysAuth sysAuth:sysAuths){
			map.put(sysAuth.getFuncId(), sysAuth.getFuncId());
		}
		return map;
	}
	
	public int delSysAuth(long rid,long modifyId,Date modifyDate){
		return sysAuthWriteMapper.delSysAuth(rid,GlobalConstants.DEL,modifyId,modifyDate);
	}
	
	public int insertSysAuth(long rid,String funcIds,long createId){
		List<SysAuth> list=new ArrayList<SysAuth>();
		SysAuth sysAuth=null;
		for(String funcId:funcIds.split(",")){
			sysAuth=new SysAuth(rid, Integer.parseInt(funcId), createId);
			list.add(sysAuth);
		}
		sysAuthWriteMapper.insertSysAuthList(list);
		return 1;
	}
}
