package net.ueye.module.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import net.ueye.common.common.MethodParam;
import net.ueye.common.common.Page;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.dao.RoleDao;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.RoleFunction;
import net.ueye.module.entity.RoleModule;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{
	
	/**
	 * 获取角色列表  [不带分页]
	 */
	public List<Role> findAllRoleList(){
		return findEntityListByEntityName(getGenericClassName());
	}
	
	/**
	 * 获取角色列表 
	 */
	public List<Role> findAllRoleList(Page page){
		return findEntityListByEntityName(page, getGenericClassName());
	}

	public List<Role> getEntityList(String hql, Page page) {
		return (List<Role>) findEntityListByHql(page, hql,null);
	}
	
	/**
	 * 根据用户ID获取没有分配给该用户的角色列表
	 */
	public List<Role> findRoleListNotAssignByAccount(long accountId){
		return findEntityListByHql("from Role role where role.id not in(select ar.roleId from AccountRole ar where ar.accountId=?)",accountId);
	}
	
	/**
	 * 根据用户组ID获取没有分配给该用户组的角色列表
	 */
	public List<Role> findRoleListNotAssignByUserGroup(long userGroupId){
		return findEntityListByHql("from Role role where role.id not in(select ar.roleId from AccountRole ar where ar.userGroupId=?)",userGroupId);
	}
	
	public void addModule(RoleModule roleModule){
		this.getHibernateTemplate().save(roleModule);
	}
	
	public void addFunction(RoleFunction roleFunction){
		this.getHibernateTemplate().save(roleFunction);
	}

	@SuppressWarnings("unchecked")
	public String getRoleModuleId(long roleId){
		List<String> list= this.getHibernateTemplate().find("select module.moduleId from RoleModule module where module.roleId=?",roleId);
		if(list.size()>0){
			return list.get(0);
		}
		return "";
	}
	
	public int countRole(String roleName){
		return count(getGenericClassName(), MethodParam.params("roleName"), MethodParam.values(roleName));
	}
		
}
