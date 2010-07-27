package net.ueye.module.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net.ueye.common.common.Page;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.UserGroup;

/**
 * 用户组
 * @author rubys@vip.qq.com
 * Aug 28, 2009
 */
public interface UserGroupService  extends BaseService<UserGroup>{
	
	void save(UserGroup userGroup);
	
	void delete(long id);
	
	int countUserGroup(String userGroup);
	
	void deleteAccountAndUserGroup(long accountId,long userGroupId);
	
	void deleteRole(long roleId,long userGroupId);
	
	@Transactional(readOnly = true)
	List<Role> findRoleListByUserGroup(long userGroupId);
	
	@Transactional(readOnly = true)
	List<UserGroup> findUserGroup(Page page);
	
	void editUserGroupRoles(long userGroupId,long roleId[]);
	
	void editUserGroupAccounts(long userGroupId,long account[]);
	
}
