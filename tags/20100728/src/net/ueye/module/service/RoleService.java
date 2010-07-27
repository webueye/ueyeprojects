package net.ueye.module.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net.ueye.common.common.Page;
import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.RoleFunction;

public interface RoleService extends BaseService<Role>{
	
	@Transactional(readOnly = true)
	List<Role> getRoleList(Page page);
	
	@Transactional(readOnly = true)
	List<Role> getAllRoleList();
	
	int countRole(String roleName);
	
	@Transactional(readOnly = true)
	List<Role> getRoleListNoAssignByAccount(long accountId);
	
	@Transactional(readOnly = true)
	List<Role> getRoleListNoAssignByUserGroup(long userGroupId);
	
	void deleteFunction(long roleId);
	
	void addRoleFunction(RoleFunction roleFunction);
	
	void addRoleModule(long roleId,String[] module);
	
	List<Module> checkModule(List<Module> list,long roleId);
	
	String getRoleFunction(long roleId);
	
}
