package net.ueye.module.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.ueye.common.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 *	Oct 7, 2009
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ueye_roleFunction")
public class RoleFunction extends BaseEntity {
	
	private long roleId;
	private String functionId;
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

}
