package net.ueye.module.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.ueye.common.entity.BaseEntity;

/**
 * 用户表与角色表多对多的中间表
 * @author rubys@vip.qq.com
 *  2009-7-4
 */ 
@SuppressWarnings("serial")
@Entity
@Table(name="ueye_accountRole")
public class AccountRole extends BaseEntity{

	private long accountId;
	private long roleId;
	private long userGroupId;
	
	public AccountRole(){}
	
	public AccountRole(long accountId,long roleId){
		this.accountId=accountId;
		this.roleId=roleId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(long userGroupId) {
		this.userGroupId = userGroupId;
	}

}
