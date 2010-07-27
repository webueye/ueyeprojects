package net.ueye.module.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.ueye.common.entity.BaseEntity;

/**
 * @author rubys@vip.qq.com 
 * Oct 2, 2009
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ueye_function")
public class Function extends BaseEntity {
	
	private long moduleId;
	private String functionName;
	private String functionCode;
	private String functionDesc;
	@Transient
	private String checked;

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
