package net.ueye.module.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.ueye.common.entity.BaseEntity;

/**
 * 用户实体
 * @author rubys@vip.qq.com
 *  2009-7-4
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ueye_account")
public class Account extends BaseEntity {

	private String username;//用户名称
	private String password;//用户密码
	private boolean admin;//是否为管理
	private boolean accountState;//用户状态：锁定|激活
	private String email;
	private Date lockDate;//锁定日期
	private Date expiryDate;
	
	public Account(){}
	
	public Account(long id,String username){
		this.setId(id);
		this.username=username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAccountState() {
		return accountState;
	}

	public void setAccountState(boolean accountState) {
		this.accountState = accountState;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
