package com.gqhmt.sys.mapper.read;

import java.util.List;

import com.gqhmt.core.mybatis.ReadMapper;
import com.gqhmt.sys.beans.SysAuth;
import com.gqhmt.sys.beans.SysAuthFunc;

public interface SysAuthReadMapper {
	public List<SysAuth> selectRoleFuncByRoleId(int roleId);
	public List<SysAuthFunc> selectRoleFuncs();
}
