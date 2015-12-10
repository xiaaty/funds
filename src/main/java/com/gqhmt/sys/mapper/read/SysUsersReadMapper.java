package com.gqhmt.sys.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.sys.beans.SysUsers;

public interface SysUsersReadMapper {
	public SysUsers selectSysUsersById(long id);
	public SysUsers selectSysUsersByLoginName(String loginName);
	public List<SysUsers> selectSysUsers(SysUsers sysUsers);
	public List<SysUsers> selectUsers(Map<String,Object> map);

	public int checkEmp(Map<String,Object> map);
}
