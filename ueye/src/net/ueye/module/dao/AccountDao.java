package net.ueye.module.dao;

import java.util.List;

import net.ueye.common.common.Page;
import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.Account;
import net.ueye.module.entity.Role;

/**
 * The AccountDao
 * @author rubys@vip.qq.com
 *  2009-7-4
 */
public interface AccountDao extends BaseDao<Account> {
	
	/**
	 * 根据用户名查询该用户
	 * @param username
	 * @param password
	 * @return
	 */
	Account findUserByUsername(String username);
	
	/**
	 * 根据 用户 ID 查询该用户的角色列表
	 * @param accountId 用户ID
	 * @return List<Role> 返回该用户的角色列表
	 */
	List<Role> getAccountRoles(long accountId);
	
	/**
	 * 查询用户列表
	 * @param page
	 * @return List<Account>
	 */
	List<Account> getAccountList(Page page);
	
	/**
	 * 根据 username 查询用户的数量
	 * @param username
	 * @return
	 */
	int countAccountByUsername(String username);
	
	/**
	 * 查找没有被分配的用户组
	 * @param userGroup
	 * @return List<Account>
	 */
	List<Account> findAccountListNoUserGroup(long userGroup);
	
	/**
	 * 查询该用户组的所有用户
	 * @param userGroup
	 * @return List<Account>
	 */
	List<Account> findAccountListByUserGroup(long userGroup);
	
}
