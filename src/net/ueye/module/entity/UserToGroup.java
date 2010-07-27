package net.ueye.module.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.ueye.common.entity.BaseEntity;

/**
 * 用户表与用户组之间的中间表
 * @author rubys@vip.qq.com
 * Aug 29, 2009
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ueye_userToGroup")
public class UserToGroup extends BaseEntity {
	
	private long account;
	private long userGroup;
	
	public UserToGroup(){}
	
	public UserToGroup(long account,long userGroup){
		this.account=account;
		this.userGroup=userGroup;
	}
	
	public long getAccount() {
		return account;
	}
	public void setAccount(long account) {
		this.account = account;
	}
	public long getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(long userGroup) {
		this.userGroup = userGroup;
	}
	
}
