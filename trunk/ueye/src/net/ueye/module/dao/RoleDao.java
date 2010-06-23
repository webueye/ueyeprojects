package net.ueye.module.dao;

import java.util.List;

import net.ueye.common.common.Page;
import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.RoleFunction;
import net.ueye.module.entity.RoleModule;

/**
 * @author rubys@vip.qq.com
 * Oct 2, 2009
 */
public interface RoleDao extends BaseDao<Role> {
	
	List<Role> findAllRoleList();
	
	/**
	 * 获取角色列表 
	 */
	List<Role> findAllRoleList(Page page);
	
	/**
	 * 根据用户ID获取没有分配给该用户的角色列表
	 */
	List<Role> findRoleListNotAssignByAccount(long accountId);
	
	/**
	 * 根据用户组ID获取没有分配给该用户组的角色列表
	 */
	List<Role> findRoleListNotAssignByUserGroup(long userGroupId);
	
	void addFunction(RoleFunction roleFunction);
	
	void addModule(RoleModule roleModule);
	
	String getRoleModuleId(long roleId);
	
	int countRole(String roleName);

}
