package net.ueye.module.dao.impl;

import org.springframework.stereotype.Repository;

import net.ueye.common.common.MethodParam;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.dao.AccountRoleDao;
import net.ueye.module.entity.AccountRole;

/**
 * @author rubys@vip.qq.com
 *  2009-7-4
 */
@Repository("accountRoleDao")
public class AccountRoleDaoImpl extends BaseDaoImpl<AccountRole> implements AccountRoleDao {
	
	/**
	 * 为用户ID为 accountId的用户添加角色列表 
	 * @param accountId
	 * @param roleId
	 */
	public void insertAccountRole(long accountId, long roleId[]){
		if(roleId != null){
			for(long role: roleId){
				insert(new AccountRole(accountId, role));
			}
		}
	}
	
	public void deleteAccountRoleByRole(long roleId){
		delete(getGenericClassName(), MethodParam.params("roleId"), MethodParam.values(roleId));
	}
	
	public void deleteAccountRoleByUserGroup(long userGroup){
		delete(getGenericClassName(), MethodParam.params("userGroupId"), MethodParam.values(userGroup));
	}
	
	/**
	 * 删除用户ID为 accountId 的用户角色
	 * @param accountId
	 */
	public void deleteAccountRoleByAccount(long accountId){
		delete(getGenericClassName(), MethodParam.params("accountId"), MethodParam.values(accountId));
	}
	
	/**
	 * 删除用户所对应的角色
	 * @param accountId
	 * @param roleId
	 */
	public void deleteAccountRole(long accountId, long roleId){
		delete(getGenericClassName(),MethodParam.params("accountId","roleId"), MethodParam.values(accountId, roleId));
	}
	
	public void deleteByRoleAndUserGroup(long roleId, long userGroupId){
		delete(getGenericClassName(), MethodParam.params("roleId", "userGroupId"), MethodParam.values(roleId, userGroupId));
	}
	
}
