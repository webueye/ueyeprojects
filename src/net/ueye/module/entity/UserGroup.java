package net.ueye.module.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.ueye.common.entity.BaseEntity;

/**
 * 用户组
 * @author rubys@vip.qq.com
 * 2009-7-12
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ueye_userGroup")
public class UserGroup extends BaseEntity {
	
	private String groupName;
	private String groupDesc;
	private String rolesId;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public String getRolesId() {
		return rolesId;
	}
	public void setRolesId(String rolesId) {
		this.rolesId = rolesId;
	}

}
