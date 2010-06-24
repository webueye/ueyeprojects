package net.ueye.module.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;

import net.ueye.module.common.Common;
import net.ueye.module.common.SessionCons;
import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;

@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/module/")
@Results(value={
		//@Result(name="module-list",type="redirectAction",location="manage/module/module-list.jsp"),
		@Result(name="moduleList",type="redirectAction",params={"actionName","module"}),
		@Result(name="role-module-authorize",location="/WEB-INF/module/manage/role/role-module-authorize.jsp")
})
public class ModuleAction extends BaseAction{
		
	@SuppressWarnings("unchecked")
	public String index() {
		List<Module> moduleList = (List<Module>)ActionContext.getContext().getSession().get(SessionCons.MODULE_LIST);
		if(moduleList == null || (moduleList !=null && moduleList.size() == 0)){
			Module rootModule = getModuleService().getRootModule();
			if(isAdministrator()){
				if(rootModule != null){
					moduleList = getModuleService().findModuleByParent(rootModule.getId());
					getModuleService().deepQueryModuleByParent(moduleList);
				}
			}
			else{
				List<Role> accountRoleList = (List<Role>) ActionContext.getContext().getSession().get(SessionCons.ACCOUNT_ROLE_LIST);
				moduleList = getModuleService().findModuleByRole(accountRoleList);
			}
		}
		ActionContext.getContext().getSession().put(SessionCons.MODULE_LIST, moduleList);
		return "module-list";
	}
	
	public String editNew(){
		Module parent = getModuleService().get(getId());
		setModule(new Module());
		this.getModule().setParent(parent);
		return "module-editNew";
	}
	
	@SuppressWarnings("unchecked")
	public String create(){
		this.getModuleService().insert(getModule());
		List<Module> list = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.MODULE_LIST);
		if(list != null && list.size() > 0){
			getModuleService().updateModule(list, getModule(), getModule().getParent().getId(), Common.MODULE_INSERT);
			Module root=list.get(0).getParent();
			if(root.getId() == (getModule().getParent().getId())){
				list.add(getModule());
			}
		}
		return "moduleList";
	}
	
	/**
	 * 模块修改
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String update(){
		List<Module> list = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.MODULE_LIST);
		this.getModuleService().update(getModule());
		if(getParentId() == getModule().getParent().getId()){
			getModuleService().updateModule(list, getModule(), getId(), Common.MODULE_NON_PARENT_UPDATE);
			return "moduleList";
		}
		if(list != null && list.size() > 0){
			Module root = list.get(0).getParent();
			getModuleService().updateModule(list, getModule(), getModule().getId(), Common.MODULE_DELETE);
			if(root.getId() == (getModule().getParent().getId())){
				list.add(getModule());
			}
			else{
				getModuleService().updateModule(list, getModule(), getModule().getParent().getId(), Common.MODULE_UPDATE);
			}
		}
		return "moduleList";
	}
	
	/**
	 * 根据ID查看某一模块
	 * @return
	 */
	public String show(){
		setModule(this.getModuleService().get(getId()));
		return "module-edit";
	}
	
	/**
	 * 模块删除
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String destroy(){
		getModuleService().delete(getId());
		List<Module> list = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.MODULE_LIST);
		getModuleService().updateModule(list, getModule(), getId(), Common.MODULE_DELETE);
		return "moduleList";
	}
	
	/**
	 * 模块展开与隐藏
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String expand(){
		List<Module> moduleList = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.MODULE_LIST);
		if(moduleList != null){
			if(isAdministrator()){
				getModuleService().handleModule(getId(), moduleList, null, isAdministrator());
			}
			else{
				List<Role> accountRoleList = (List<Role>) ActionContext.getContext().getSession().get("accountRoleList");
				getModuleService().handleModule(getId(), moduleList, accountRoleList, isAdministrator());
			}
		}
		return getResult();
	}
	
	public String forward(){
		return "module-insert";
	}

}
