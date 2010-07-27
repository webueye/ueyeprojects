package net.ueye.module.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import net.ueye.module.common.MessageCons;

@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/role/")
@Result(name="role", type="redirectAction", params={"actionName", "role"})
public class RoleAction extends BaseAction{
	
	/**
	 * 角色列表
	 * @return
	 */
	public String index(){
		setRoleList(getRoleService().getRoleList(getPage()));
		return "role-list";
	}
	
	/**
	 * 跳转到添加角色页面
	 * @return
	 */
	public String editNew(){		
		return "role-insert";
	}
	
	/**
	 * 添加角色
	 * @return
	 */
	public String create(){
		if(isExist())
			return "role-insert";
		else{
			getRoleService().insert(getRole());
			return "role";
		}
	}
	
	/**
	 * 根据ID查看角色
	 * @return
	 */
	public String show(){
		setRole(getRoleService().get(getId()));
		return "role-edit";
	}
	
	/**
	 * 角色修改
	 * @return
	 */
	public String update(){
		getRoleService().update(getRole());
		return "role";
	}
	
	public String destroy(){
		getRoleService().delete(getId());
		return index();
	}
	
	public boolean isExist(){
		int roleCount = getRoleService().countRole(getRole().getRoleName());
		if(roleCount > 0){
			addActionMessage(getText(MessageCons.ROLE_IS_EXIST));
			return true;
		}
		else{
			return false;
		}
	}
}
