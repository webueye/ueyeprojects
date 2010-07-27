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
@Table(name="ueye_roleModule")
public class RoleModule extends BaseEntity{
	
	private long roleId;	
	private String moduleId;
	
	public RoleModule(){}
	
	public RoleModule(long roleId,String moduleId){
		this.roleId=roleId;
		this.moduleId=moduleId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}
