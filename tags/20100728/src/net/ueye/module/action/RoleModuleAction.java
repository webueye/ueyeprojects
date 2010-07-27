package net.ueye.module.action;

import java.util.List;

import net.ueye.module.common.SessionCons;
import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionContext;

/**
 * 角色所对应的模块读取、修改
 * @author rubys@vip.qq.com
 * Aug 30, 2009
 */
@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/role/")
@Result(name="role", type="redirectAction", params={"actionName", "role"})
public class RoleModuleAction extends BaseAction{
	
	/**
	 * 读取角色模块列表并选择[打勾]
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String show(){
		List<Module> moduleList = (List<Module>) ActionContext.getContext().getSession().get("roleModuleList");
		if(moduleList == null){
			Module rootModule = getModuleService().getRootModule();
			if(isAdministrator()){
				moduleList = getModuleService().findModuleList(rootModule, true);
				moduleList = getRoleService().checkModule(moduleList, getId());
			}
			else{
				List<Role> accountRoleList = (List<Role>) ActionContext.getContext().getSession().get(SessionCons.ACCOUNT_ROLE_LIST);
				moduleList = getModuleService().findModuleByRole(accountRoleList);
			}
			ActionContext.getContext().getSession().put(SessionCons.ROLE_MODULE_LIST, moduleList);		
		}
		moduleList = getRoleService().checkModule(moduleList, getId());
		return "role-module-authorize";
	}
	
	public String create(){
		getRoleService().addRoleModule(getId(), getFunction());
		return "role";
	}
	
	/**
	 * 模块展开与隐藏
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String expand(){
		List<Module> list = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.ROLE_MODULE_LIST);
		if(list != null){
			list = getModuleService().handleTree(list, getId());
			list = getRoleService().checkModule(list, getRole().getId());
		}
		else{
			Module rootModule = getModuleService().getRootModule();
			List<Module> moduleList = getModuleService().findModuleList(rootModule);
			moduleList = getRoleService().checkModule(moduleList, getId());
			ActionContext.getContext().getSession().put(SessionCons.ROLE_MODULE_LIST, moduleList);
		}
		this.setId(getRole().getId());
		return "role-module-authorize";
	}
}
