package net.ueye.module.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import net.ueye.common.common.Page;
import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.RoleFunction;
import net.ueye.module.entity.RoleModule;
import net.ueye.module.service.RoleService;
import net.ueye.module.common.Common;

/**
 * @author rubys@vip.qq.com
 * 2009-8-16
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
	
	public Role get(Serializable roleId){
		return getRoleDao().get(Role.class, roleId);
	}
	
	public void insert(Role role){
		getRoleDao().insert(role);
	}
	
	public void update(Role role){
		getRoleDao().update(role);
	}
	
	/**
	 * 获取角色列表  [不带分页]
	 */
	public List<Role> getAllRoleList(){
		return getRoleDao().findAllRoleList();
	}
	
	/**
	 * 查看该角色名称是否存在
	 * @param roleName
	 * @return
	 */
	public int countRole(String roleName){
		return getRoleDao().countRole(roleName);
	}
	
	/**
	 * 根据用户ID获取没有分配给该用户的角色列表
	 */
	public List<Role> getRoleListNoAssignByAccount(long accountId){
		return getRoleDao().findRoleListNotAssignByAccount(accountId);
	}
	
	/**
	 * 根据用户组ID获取没有分配给该用户组的角色列表
	 */
	public List<Role> getRoleListNoAssignByUserGroup(long userGroupId){
		return getRoleDao().findRoleListNotAssignByUserGroup(userGroupId);
	}
	
	/**
	 * 获取角色列表 
	 * @param page 
	 * @return List<Role>
	 */
	public List<Role> getRoleList(Page page) {
		return getRoleDao().findAllRoleList(page);
	}
	
	public void deleteFunction(long roleId){
		//getRoleDao().deleteFunction(roleId);
		getRoleFunctionDao().deleteFunction(roleId);
	}
	
	public void addRoleFunction(RoleFunction roleFunction){
		getRoleDao().addFunction(roleFunction);
	}
	
	/**
	 * 给角色 [roleId] 划分模块 [function]
	 * @param roleId
	 * @param function
	 */
	public void addRoleModule(long roleId, String[] module){
		StringBuffer sb = new StringBuffer();
		getRoleModuleDao().deleteModule(roleId);
		if(module != null){
			for(int i = 0; i<module.length; i++){
				if(i == module.length - 1){
					sb.append(module[i]);
				}
				else{
					sb.append(module[i]).append(",");
				}
			}
			getRoleDao().addModule(new RoleModule(roleId, sb.toString()));
		}
	}
	
	/**
	 * 在权限列表中选择此角色所拥有的权限
	 * @param list<Module> 权限列表
	 * @param roleId 角色 ID
	 */
	public List<Module> checkModule(List<Module> list, long roleId){		
		String roleFunction = getRoleDao().getRoleModuleId(roleId);
		long[] moduleId = Common.stringToLong(roleFunction.split(","));
		deepChecked(list, moduleId);
		return list;
	}
	
	/**
	 * 递归选择权限列表中选择此角色所拥有的权限
	 * @param list<Module> 权限列表
	 * @param roleFunction 角色拥有的权限
	 */
	@SuppressWarnings("unchecked")
	public void deepChecked(List<Module> list, long[] moduleId){
		if(list != null){
			for(Module module: list){
				if(valueInArray(module.getId(), moduleId)){
					module.setChecked("checked");
				}
				else{
					module.setChecked("");
				}
				if(!module.isLeaf() && module.isExpanded() && module.getChild().size() > 0){
					deepChecked((List<Module>)module.getChild(), moduleId);
				}
			}
		}
	}
	
	private boolean valueInArray(long id, long[] moduleId){
		for(long md: moduleId){
			if(id == md){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据 [roleId] 获取权限
	 * @param roleId
	 * @return string
	 */
	public String getRoleFunction(long roleId){
		return getRoleDao().getRoleModuleId(roleId);
	}
	
	/**
	 * 删除该角色以及该角色所拥有的用户和用户组
	 */
	public void delete(long roleId) {
		getRoleDao().delete(Role.class, roleId);
		getAccountRoleDao().deleteAccountRoleByRole(roleId);
	}
		
}
