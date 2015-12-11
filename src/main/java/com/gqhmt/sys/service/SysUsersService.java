package com.gqhmt.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gqhmt.sys.beans.SysUsers;
import com.gqhmt.sys.mapper.read.SysUsersReadMapper;
import com.gqhmt.sys.mapper.write.SysUsersWriteMapper;

@Service
public class SysUsersService {
	@Autowired
	SysUsersReadMapper sysUsersReadMapper;
	@Autowired
	SysUsersWriteMapper sysUsersWriteMapper;
	
	public SysUsers selectSysUsersByLoginName(String loginName){
		return sysUsersReadMapper.selectSysUsersByLoginName(loginName);
	}
	public SysUsers selectSysUsersById(long id){
		return sysUsersReadMapper.selectSysUsersById(id);
	}
	public int insertSysUsers(SysUsers sysUsers){
		return sysUsersWriteMapper.insertSysUsers(sysUsers);
	}
	
	public int upPwd(String pwd,long id){
		return sysUsersWriteMapper.uppwd(pwd, id);
	}
	
	public int updateSysUsers(SysUsers sysUsers){
		return sysUsersWriteMapper.updateSysUsers(sysUsers);
	}
	public List<SysUsers> selectSysUsers(SysUsers sysUsers){
		return sysUsersReadMapper.selectSysUsers(sysUsers);
	}
	public List<SysUsers> selectSysUsers(Map<String,Object> map){
		return sysUsersReadMapper.selectUsers(map);
	}
	public int updateUserRole(int id,String roleIds){
		return sysUsersWriteMapper.updateUserRole(id,roleIds);
	}
	
	public int updateEmpStatus(int id){
		return sysUsersWriteMapper.updateEmpStatus(id);
	}
	
	public int checkEmp(Map<String,Object> map){
		return sysUsersReadMapper.checkEmp(map);
	}
}
