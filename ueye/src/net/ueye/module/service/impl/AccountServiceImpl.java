package net.ueye.module.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import net.ueye.common.common.Page;
import net.ueye.module.entity.Account;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.UserGroup;
import net.ueye.module.service.AccountService;

/**
 * The AccountService
 * @author rubys@vip.qq.com
 */
@Service("accountService")
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{
	
	public Account get(Serializable accountId){
		return getAccountDao().get(Account.class, accountId);
	}
	
	public void insert(Account account){
		getAccountDao().insert(account);
	}
	
	/**
	 * 获取所有用户[带分页]
	 * @param page 
	 * @return list<Account> 用户信息的结果
	 */
	public List<Account> getAccountList(Page page) {
		return getAccountDao().getAccountList(page);
	}
	
	/**
	 * 用户审核
	 * @param accountId
	 * @param auditState
	 */
	public Account audit(long accountId, int auditState){
		Account account = getAccountDao().get(Account.class, accountId);
		getAccountDao().update(account);
		return account;
	}
	
	/**
	 * 修改用户信息
	 * @param account 用户实体
	 */
	public void update(Account account){
		Account acc = getAccountDao().get(Account.class, account.getId());
		acc.setUsername(account.getUsername());
		acc.setPassword(account.getPassword());
		acc.setEmail(account.getEmail());
		acc.setAccountState(account.isAccountState());
		acc.setAdmin(account.isAdmin());
		getAccountDao().update(acc);
	}
	
	/**
	 * 查看相应的用户是否已经存在
	 * @param username
	 * @return
	 */
	public int countAccount(String username){
		return getAccountDao().countAccountByUsername(username);
	}
	
	/**
	 * 编辑用户所拥有的角色
	 * @param accountId 用户的ID
	 * @param roleId 用户所拥有角色的ID
	 */
	public void editAccountRole(long accountId, long roleId[]){
		getAccountRoleDao().deleteAccountRoleByAccount(accountId);
		getAccountRoleDao().insertAccountRole(accountId, roleId);
	}
	
	/**
	 * 编辑用户所属的组
	 * @param accountId
	 * @param userGroups
	 */
	public void editAccountGroup(long accountId, long userGroups[]){
		getUserToGroupDao().deleteUserToGroupByAccount(accountId);
		getUserToGroupDao().insert(accountId, userGroups);
	}
	
	/**
	 * 根据ID查找用户该用户所拥有的角色信息
	 * @param accountId 用户ID
	 */
	public List<Role> getAccountRoles(long accountId){
		return getAccountDao().getAccountRoles(accountId);
	}
	
	/**
	 * 根据ID查找用户该用户所拥有的角色信息包括用户组中拥有的角色信息
	 * @param accountId 用户ID
	 */
	public List<Role> getAccountAllRoles(long accountId){
		List<UserGroup> userGroupList = getUserGroupDao().findUserGroupByAccount(accountId);
		List<Role> roleList = getAccountDao().getAccountRoles(accountId);
		for(UserGroup ug:userGroupList){
			List<Role> roleLs = getUserGroupDao().findRoleByUserGroup(ug.getId());
			for(Role role:roleLs){
				roleList.add(role);
			}
			List<Role> list = getUserGroupDao().findRoleByUserGroup(ug.getId());
			for(Role role:list){
				roleList.add(role);
			}
		}
		return roleList;
	}
	
	/**
	 * 查询 用户组列表
	 * @param accountId
	 * @return List<UserGroup>
	 */
	public List<UserGroup> findUserGroupByAccount(long accountId){
		return getUserGroupDao().findUserGroupByAccount(accountId);		
	}
	
	/**
	 * 查询没有被指定的用户组
	 * @param accountId
	 * @return List<UserGroup>
	 */
	public List<UserGroup> findUserGroupNoAssign(long accountId){
		return getUserGroupDao().findUserGroupNoAssign(accountId);
	}
	
	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return Account
	 */
	public Account getAccountByUsername(String username){
		return getAccountDao().findUserByUsername(username);
	}
	
	/**
	 * 删除用户以及该用户所拥有的角色和用户组
	 */
	public void delete(long accountId) {
		getAccountDao().delete(Account.class, accountId);
		getAccountRoleDao().deleteAccountRoleByAccount(accountId);
		getUserToGroupDao().deleteUserToGroupByAccount(accountId);
	}
	
	
	public void deleteAccountRole(long accountId,long roleId){
		getAccountRoleDao().deleteAccountRole(accountId, roleId);;
	}
	
	/**
	 * 查找没有被分配的用户组
	 * @param userGroup
	 * @return List<Account>
	 */
	public List<Account> findAccountListNoUserGroup(long userGroup){
		return getAccountDao().findAccountListNoUserGroup(userGroup);
	}
	
	/**
	 * 查询该用户组的所有用户
	 * @param userGroup
	 * @return List<Account
	 */
	public List<Account> findAccountListByUserGroup(long userGroup){
		return getAccountDao().findAccountListByUserGroup(userGroup);
	}
	
	public void state(long accountId){
		Account account = getAccountDao().get(Account.class, accountId);
		account.setAccountState(!account.isAccountState());
		getAccountDao().update(account);
	}
	
}
