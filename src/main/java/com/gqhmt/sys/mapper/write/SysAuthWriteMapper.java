package com.gqhmt.sys.mapper.write;

import java.util.Date;
import java.util.List;

import com.gqhmt.core.mybatis.WriteMapper;
import org.apache.ibatis.annotations.Param;

import com.gqhmt.sys.beans.SysAuth;

public interface SysAuthWriteMapper  {
	public int delSysAuth(@Param(value="roleId")long roleId,@Param(value="status")long status,@Param(value="modifyId")long modifyId,@Param(value="modifyDate")Date modifyDate);
	public int insertSysAuth(SysAuth sysAuth);
	public int insertSysAuthList(@Param(value="list")List<SysAuth> list);
}
