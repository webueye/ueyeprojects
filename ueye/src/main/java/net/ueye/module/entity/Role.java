package net.ueye.module.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.ueye.common.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com
 * Aug 30, 2009
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ueye_role")
public class Role extends BaseEntity{
	
	private String roleName;
	private String description;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
