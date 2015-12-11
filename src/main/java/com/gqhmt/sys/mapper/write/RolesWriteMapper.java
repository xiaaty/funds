package com.gqhmt.sys.mapper.write;

import org.apache.ibatis.annotations.Param;

import com.gqhmt.sys.beans.Roles;

public interface RolesWriteMapper {
	public int insertRole(Roles roles);
	public int updateRole(Roles roles);
	public int updateRoleDel(@Param(value="rid")String rid,@Param(value="isDel")int isDel);
}
