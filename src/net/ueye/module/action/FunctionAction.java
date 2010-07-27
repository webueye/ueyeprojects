package net.ueye.module.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.ueye.module.common.SessionCons;
import net.ueye.module.entity.Function;
import net.ueye.module.entity.Module;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import com.opensymphony.xwork2.ActionContext;

/**
 * 功能
 * @author rubys@vip.qq.com
 * Oct 2, 2009
 */
@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/function")
@Results(value={
	@Result(name="functionList", type="redirectAction", params={"actionName", "function"})
})
public class FunctionAction extends BaseAction {
	
	@SuppressWarnings("unchecked")
	public String index(){
		List<Module> moduleList = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.FUNCTIONLIST);
		if(moduleList == null){
			Module rootModule = getModuleService().getRootModule();
			moduleList = getModuleService().findModuleList(rootModule);
			moduleList = getRoleService().checkModule(moduleList, getId());
			ActionContext.getContext().getSession().put(SessionCons.FUNCTIONLIST, moduleList);		
		}
		moduleList = this.getRoleService().checkModule(moduleList, getId());
		moduleList = getFunctionService().findFunctionByModule(moduleList);
		return "function-list";
	}
	
	public String create(){
		getFunModel().setModuleId(getModule().getId());
		getFunctionService().insert(getFunModel());
		return "functionList";
	}
	
	/**
	 * 根据模块ID查找属于该模块的功能列表 
	 */
	public String show() throws IOException{
		beforeAnsy();
		List<Function> functionList = getFunctionService().findFunctionListByModule(getId());
		JSONArray json = JSONArray.fromObject(functionList);
		ServletActionContext.getResponse().getWriter().println(json);
		ServletActionContext.getResponse().getWriter().close();
		return null;
	}
	
	public String destroy() throws IOException{
		getFunctionService().delete(getId());
		setId(getModule().getId());
		show();
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String expand(){
		List<Module> list = (List<Module>) ActionContext.getContext().getSession().get(SessionCons.FUNCTIONLIST);
		if(list != null)
			list = getModuleService().handleTree(list, getId());
		else{
			Module rootModule = getModuleService().getRootModule();
			List<Module> moduleList = getModuleService().findModuleList(rootModule);
			moduleList = getRoleService().checkModule(moduleList, getId());
			ActionContext.getContext().getSession().put(SessionCons.FUNCTIONLIST, moduleList);	
		}
		return "function-list";
	}

}
