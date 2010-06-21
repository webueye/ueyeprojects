package net.ueye.module.dao;

import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.UserToGroup;


public interface UserToGroupDao extends BaseDao<UserToGroup> {
	
	/**
	 * 设置用户与组之间的关系
	 * @param accountId
	 * @param userGroups
	 */
	void insert(long accountId,long userGroups[]);
	
	/**
	 * 删除用户[accountId]与组之间的关系
	 * @param account
	 */
	void deleteUserToGroupByAccount(long accountId);
	
	void deleteUserToGroupByUserGroup(long userGroupId);
	
	void deleteAccountAndUserGroup(long accountId,long userGroupId);

}
