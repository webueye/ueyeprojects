package net.ueye.module.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import net.ueye.common.common.Page;
import net.ueye.module.entity.AccountRole;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.UserGroup;
import net.ueye.module.entity.UserToGroup;
import net.ueye.module.service.UserGroupService;

/**
 * @author rubys@vip.qq.com
 * Aug 28, 2009
 */
@Service("userGroupService")
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements UserGroupService {

	
	public UserGroup get(Serializable id){
		return getUserGroupDao().get(UserGroup.class, id);
	}
	
	public void insert(UserGroup userGroup){
		getUserGroupDao().insert(userGroup);
	}
	
	public void update(UserGroup userGroup){
		getUserGroupDao().update(userGroup);
	}
	
	/**
	 * 删除用户组以及该用户级所拥有的角色和用户
	 */
	public void delete(long userGroupId) {
		getUserGroupDao().delete(UserGroup.class, userGroupId);
		getUserToGroupDao().deleteUserToGroupByUserGroup(userGroupId);
		getAccountRoleDao().deleteAccountRoleByUserGroup(userGroupId);
	}
	
	/**
	 * 查看该用户组名称的个数
	 * @param userGroup
	 * @return
	 */
	public int countUserGroup(String userGroup){
		return getUserGroupDao().countUserGroup(userGroup);
	}
	
	public void save(UserGroup userGroup) {
		this.getUserGroupDao().save(userGroup);
	}
	
	public void deleteAccountAndUserGroup(long accountId,long userGroupId){
		getUserToGroupDao().deleteUserToGroupByAccount(accountId);
		getUserToGroupDao().deleteAccountAndUserGroup(accountId, userGroupId);
	}
	
	public void deleteRole(long roleId,long userGroupId){
		getAccountRoleDao().deleteByRoleAndUserGroup(roleId, userGroupId);
	}
	
	/**
	 * 编辑用户组角色
	 */
	public void editUserGroupRoles(long userGroupId, long roleId[]){
		getAccountRoleDao().deleteAccountRoleByUserGroup(userGroupId);
		if(roleId != null){
			for(long role: roleId){
				AccountRole ar = new AccountRole();
				ar.setUserGroupId(userGroupId);
				ar.setRoleId(role);
				getUserGroupDao().save(ar);
			}
		}
	}
	
	public void editUserGroupAccounts(long userGroupId,long account[]){
		getUserToGroupDao().deleteUserToGroupByUserGroup(userGroupId);
		if(account != null){
			for(long aid: account){
				getUserGroupDao().save(new UserToGroup(aid, userGroupId));
			}
		}
	}
	
	/**
	 * 根据用户组ID获取用户组角色列表
	 */
	public List<Role> findRoleListByUserGroup(long userGroupId){
		return getUserGroupDao().findRoleByUserGroup(userGroupId);
	}
	
	/**
	 * 获取用户组列表
	 */
	public List<UserGroup> findUserGroup(Page page){
		return getUserGroupDao().findUserGroup(page);
	}

}
