package net.ueye.module.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net.ueye.common.common.Page;
import net.ueye.module.entity.Account;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.UserGroup;

/**
 * @author rubys@vip.qq.com
 *  2009-7-4
 */
public interface AccountService extends BaseService<Account>{
	
	@Transactional(readOnly = true)
	Account get(Serializable accountId);
	
	void deleteAccountRole(long accountId,long roleId);
	
	@Transactional(readOnly = true)
	List<Account> getAccountList(Page page);
	
	Account audit(long accountId,int auditState);
	
	int countAccount(String username);
	
	@Transactional(readOnly = true)
	Account getAccountByUsername(String username);
	
	@Transactional(readOnly = true)
	List<Role> getAccountRoles(long accountId);
	
	@Transactional(readOnly = true)
	List<Role> getAccountAllRoles(long accountId);
	
	void editAccountRole(long accountId,long roleId[]);
	
	void editAccountGroup(long accountId,long userGroups[]);
	
	@Transactional(readOnly = true)
	List<UserGroup> findUserGroupByAccount(long id);
	
	@Transactional(readOnly = true)
	List<UserGroup> findUserGroupNoAssign(long account);
	
	@Transactional(readOnly = true)
	List<Account> findAccountListNoUserGroup(long userGroup);
	
	@Transactional(readOnly = true)
	List<Account> findAccountListByUserGroup(long userGroup);
	
	void state(long id);
		
}
