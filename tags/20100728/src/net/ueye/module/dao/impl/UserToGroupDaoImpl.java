package net.ueye.module.dao.impl;

import org.springframework.stereotype.Repository;

import net.ueye.common.common.MethodParam;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.dao.UserToGroupDao;
import net.ueye.module.entity.UserToGroup;

/**
 * 用户与用户组 DAO
 * @author rubys@vip.qq.com
 * 2010-6-12
 */
@Repository("userToGroupDao")
public class UserToGroupDaoImpl extends BaseDaoImpl<UserToGroup> implements UserToGroupDao {

	/**
	 * 设置用户与组之间的关系
	 * @param accountId
	 * @param userGroups
	 */
	public void insert(long accountId,long userGroups[]){
		if(userGroups!=null){
			for(long userGroup:userGroups){
				insert(new UserToGroup(accountId,userGroup));
			}
		}
	}
	
	/**
	 * 删除用户[accountId]与组之间的关系
	 * @param account
	 */
	public void deleteUserToGroupByAccount(long accountId){
		delete(getGenericClassName(), MethodParam.params("account"), MethodParam.values(accountId));
	}
	
	public void deleteUserToGroupByUserGroup(long userGroupId){
		delete(getGenericClassName(), MethodParam.params("userGroup"), MethodParam.values(userGroupId));
	}
	
	public void deleteAccountAndUserGroup(long accountId,long userGroupId){
		delete(getGenericClassName(), MethodParam.params("account", "userGroup"), MethodParam.values(accountId, userGroupId));
	}
	
}
