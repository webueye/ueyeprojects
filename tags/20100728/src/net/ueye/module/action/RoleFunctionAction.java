package net.ueye.module.action;

import java.util.List;

import net.ueye.module.common.Common;
import net.ueye.module.common.SessionCons;
import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.RoleFunction;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/role/")
@Result(name="role", type="redirectAction", params={"actionName", "role"})
public class RoleFunctionAction extends BaseAction {
	
	/**
	 * 读取角色功能列表并选择[打勾]
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String show(){
		List<Module> moduleList = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.ROLE_FUNCTION_LIST);
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
			moduleList=getFunctionService().findFunctionByModule(moduleList);
			getFunctionService().checkedFunction(moduleList, getId());
			ActionContext.getContext().getSession().put(SessionCons.ROLE_FUNCTION_LIST, moduleList);		
		}
		//moduleList=this.getRoleService().checkModule(moduleList,getId());
		return "role-function-authorize";
	}
	
	public String create(){
		List<RoleFunction> funList=Common.moduleFunctionsToFunction(getModuleFunction(),getId());
		getRoleService().deleteFunction(getId());
		for(RoleFunction roleFun:funList){
			this.getRoleService().addRoleFunction(roleFun);
		}		
		return "role";
	}
	
	/**
	 * 模块展开与隐藏
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String expand(){
		List<Module> list = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.ROLE_FUNCTION_LIST);
		if(list != null){
			if(isAdministrator()){
				getModuleService().handleModule(getId(), list, null, true);
			}
			else{
				List<Role> accountRoleList = (List<Role>) ActionContext.getContext().getSession().get(SessionCons.ACCOUNT_ROLE_LIST);
				getModuleService().handleModule(getId(), list, accountRoleList, false);
			}
			//getFunctionService().findFunctionByModule(list);
			//getFunctionService().checkedFunction(list,getId());
		}
		setId(getRole().getId());
		return "role-function-authorize";
	}

}
