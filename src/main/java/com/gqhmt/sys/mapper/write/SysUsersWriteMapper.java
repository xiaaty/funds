package com.gqhmt.sys.mapper.write;

import com.gqhmt.core.mybatis.WriteMapper;
import org.apache.ibatis.annotations.Param;

import com.gqhmt.sys.beans.SysUsers;

public interface SysUsersWriteMapper  {
	public int insertSysUsers(SysUsers sysUsers);
	public int updateSysUsers(SysUsers sysUsers);
	public int uppwd(@Param(value="pwd")String pwd,@Param(value="id")long id);
	public int updateUserRole(@Param(value="id")int id,@Param(value="roleIds")String roleIds);
	public int updateEmpStatus(int id);
}
