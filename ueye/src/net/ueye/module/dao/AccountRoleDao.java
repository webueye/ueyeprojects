package net.ueye.module.dao;

import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.AccountRole;

/**
 * 用户-角色 DAO
 * @author rubys@vip.qq.com
 * 2009-7-4
 */
public interface AccountRoleDao extends BaseDao<AccountRole>{
	
	/**
	 * 为用户ID为 accountId的用户添加角色列表 
	 * @param accountId
	 * @param roleId
	 */
	void insertAccountRole(long accountId, long roleId[]);
	
	void deleteAccountRoleByRole(long roleId);
	
	void deleteAccountRoleByUserGroup(long userGroup);
	
	/**
	 * 删除用户ID为 accountId 的用户角色
	 * @param accountId
	 */
	void deleteAccountRoleByAccount(long accountId);
	
	/**
	 * 删除用户所对应的角色
	 * @param accountId
	 * @param roleId
	 */
	void deleteAccountRole(long accountId, long roleId);
	
	void deleteByRoleAndUserGroup(long roleId,long userGroupId);

}
