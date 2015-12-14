package com.gqhmt.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gqhmt.sys.beans.Roles;
import com.gqhmt.sys.mapper.read.RolesReadMapper;
import com.gqhmt.sys.mapper.write.RolesWriteMapper;

import javax.annotation.Resource;

@Service
public class RolesService {
	@Resource
	private RolesReadMapper rolesReadMapper;
	@Resource
	private RolesWriteMapper rolesWriteMapper;
	
	
	public List<Roles> selectRolesList(Roles roles){
		return rolesReadMapper.selectRolesList(roles);
	}

    public List<Roles> selectRolesListAll(){
        return this.rolesReadMapper.selectRolesListAll();
    }

	public List<Roles> selectRolesList(Map<String,Object> map){
		return rolesReadMapper.selectRoles(map);
	}
	public Roles selectRoleById(int id){
		return rolesReadMapper.selectRoleById(id);
	}	

	public int inserRole(Roles roles){
		return rolesWriteMapper.insertRole(roles);
	}
	public int updateRole(Roles roles){
		return rolesWriteMapper.updateRole(roles);
	}
	public int updateRoleDel(String rid,int isDel){
		return rolesWriteMapper.updateRoleDel(rid, isDel);
	}
}
