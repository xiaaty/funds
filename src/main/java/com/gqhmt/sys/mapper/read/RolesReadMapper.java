package com.gqhmt.sys.mapper.read;

import java.util.List;
import java.util.Map;

import com.gqhmt.sys.beans.Roles;

public interface RolesReadMapper {
	public List<Roles> selectRolesList(Roles roles);
	public List<Roles> selectRoles(Map<String,Object> map);
	public Roles selectRoleById(int id);

    public List<Roles> selectRolesListAll();
}
