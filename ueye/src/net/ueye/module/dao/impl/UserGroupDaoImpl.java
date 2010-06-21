package net.ueye.module.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.ueye.common.common.MethodParam;
import net.ueye.common.common.Page;
import net.ueye.common.constant.Entity;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.dao.UserGroupDao;
import net.ueye.module.entity.AccountRole;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.UserGroup;

/**
 * The UserGroupDaoImpl
 * @author rubys@vip.qq.com
 * 2009-8-28
 */
@Repository("userGroupDao")
public class UserGroupDaoImpl extends BaseDaoImpl<UserGroup> implements UserGroupDao{
	
	@SuppressWarnings("unchecked")
	public List<Role> findRoleByUserGroup(long userGroupId){
		List<Role> roles=new ArrayList<Role>();
		List<AccountRole> list=this.getHibernateTemplate().find("from AccountRole ar where ar.userGroupId=?",userGroupId);
		for(AccountRole ar:list){
			roles.add(((Role)this.getHibernateTemplate().get(Role.class,ar.getRoleId())));
		}
		return roles;
	}
	
	public List<UserGroup> findUserGroup(Page page){
		return findEntityListByEntityName(page, Entity.USER_GROUP);
	}

	/**
	 * 查询没有被指定的用户组
	 * @param accountId
	 * @return List<UserGroup>
	 */
	public List<UserGroup> findUserGroupNoAssign(long accountId){
		return findEntityListByHql("from UserGroup ug where ug.id not in (select utg.userGroup from UserToGroup utg where utg.account=?)",accountId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroup> findUserGroupByAccount(long account){
		return this.getHibernateTemplate().find("from UserGroup ug where ug.id in(select utg.userGroup from UserToGroup utg where utg.account=?)",account);
	}
	
	public int countUserGroup(String userGroup){
		return count(Entity.USER_GROUP, MethodParam.params("groupName"), MethodParam.values(userGroup));
	}

}
