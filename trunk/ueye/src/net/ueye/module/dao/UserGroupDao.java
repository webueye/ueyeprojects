package net.ueye.module.dao;

import java.util.List;

import net.ueye.common.common.Page;
import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.UserGroup;

/**
 * @author rubys@vip.qq.com
 * Aug 28, 2009
 */
public interface UserGroupDao extends BaseDao<UserGroup> {
	
	List<Role> findRoleByUserGroup(long userGroupId);
	
	/**
	 * 查询已被指定的用户组
	 * @param accountId
	 * @return List<UserGroup>
	 */
	List<UserGroup> findUserGroupByAccount(long account);
	
	List<UserGroup> findUserGroup(Page page);
	
	/**
	 * 查询没有被指定的用户组
	 * @param accountId
	 * @return List<UserGroup>
	 */
	List<UserGroup> findUserGroupNoAssign(long accountId);
	
	int countUserGroup(String userGroup);
	

}
