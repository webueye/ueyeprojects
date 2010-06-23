package net.ueye.module.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.ueye.common.common.Page;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.common.Config;
import net.ueye.module.dao.AccountDao;
import net.ueye.module.entity.Account;
import net.ueye.module.entity.AccountRole;
import net.ueye.module.entity.Role;

import org.springframework.stereotype.Repository;

/**
 * The AccountDao
 * @author rubys@vip.qq.com
 *  2009-7-4
 */
@Repository("accountDao")
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao{
	
	/**
	 * 根据用户名查询该用户
	 * @param username
	 * @param password
	 * @return
	 */
	public Account findUserByUsername(String username) {
		List<Account> list = findEntityListByHql(Config.get("AccountDao.findUserByUsername"), username);
		if(list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
	
	/**
	 * 查询所有用户
	 * @param page 分页参数
	 * @return List<Account> 用户列表
	 */
	public List<Account> getAccountList(Page page){
		return findEntityListByEntityName(page, getGenericClassName());
	}
	
	/**
	 * 根据 用户 ID 查询该用户的角色列表
	 * @param accountId 用户ID
	 * @return List<Role> 返回该用户的角色列表
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAccountRoles(final long accountId) {
		List<Role> roles = new ArrayList<Role>();
		List<AccountRole> list = (List<AccountRole>) findDataList(Config.get("AccountDao.findAccountRoleByAccount"), accountId);
		for(AccountRole ar: list){
			roles.add(((Role)getHibernateTemplate().get(Role.class, ar.getRoleId())));
		}
		return roles;		
	}
	
	/**
	 * 根据 username 查询用户的数量
	 * @param username
	 * @return
	 */
	public int countAccountByUsername(String username){
		return countByHql(Config.get("AccountDao.countAccountByUsername"), username);
	}
	
	/**
	 * 查找没有被分配的用户组
	 * @param userGroup
	 * @return List<Account>
	 */
	public List<Account> findAccountListNoUserGroup(long userGroup){
		return findEntityListByHql(Config.get("AccountDao.findAccountListNoUserGroup"), userGroup);
	}
	
	/**
	 * 查询该用户组的所有用户
	 * @param userGroup
	 * @return List<Account>
	 */
	public List<Account> findAccountListByUserGroup(long userGroup){
		return findEntityListByHql(Config.get("AccountDao.findAccountListByUserGroup"), userGroup);
	}

}
