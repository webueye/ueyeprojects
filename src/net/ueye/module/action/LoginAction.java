package net.ueye.module.action;

import java.util.List;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;

import net.ueye.module.common.Common;
import net.ueye.module.common.MessageCons;
import net.ueye.module.common.SessionCons;
import net.ueye.module.entity.Account;
import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;

/**
 * 登陆
 * @author rubys@vip.qq.com
 *  2009-7-4
 */

@SuppressWarnings("serial")
@ResultPath("/main")
@Results(value={
	@Result(name="index", location="/main/index.jsp"),
	@Result(name="left", type="redirectAction", location="/main/left.jsp")
})
public class LoginAction extends BaseAction{
	
	public String create(){
		Account account = getAccountService().getAccountByUsername(getAccount().getUsername());
		if(account == null){
			addActionMessage(getText(MessageCons.ACCOUNT_NOEXIST));
			return "login";
		}
		if(account.getPassword().equals(Common.MD5(getAccount().getPassword()))){			
			if(account.isAccountState()){
				addActionMessage(getText(MessageCons.ACCOUNT_ISLOCKED));
				return "login";
			}
			ActionContext.getContext().getSession().put(SessionCons.CURRENT_ACCOUNT, account);
			List<Module> moduleList = null;
			if(isAdministrator()){
				Module rootModule = getModuleService().getRootModule();
				if(rootModule != null){
					moduleList = getModuleService().findModuleByParent(rootModule.getId());	
					getModuleService().deepQueryModuleByParent(moduleList);
				}
			}
			else{
				List<Role> accountRoleList = getAccountService().getAccountAllRoles(account.getId());			
				moduleList = getModuleService().findModuleByRole(accountRoleList);	
				ActionContext.getContext().getSession().put(SessionCons.ACCOUNT_ROLE_LIST, accountRoleList);
			}
			ActionContext.getContext().getSession().put(SessionCons.MODULE, moduleList);
			return "index";
		}
		else{
			addActionMessage(getText(MessageCons.PASSWORD_NOT_CORRECT));
			return "login";
		}
	}
	
	public String index() {
		return "login";
	}
	
	@SuppressWarnings("unchecked")
	public String show(){
		List<Module> moduleList = (List<Module>) ActionContext.getContext().getSession().get("module");
		if(moduleList != null){
			if(isAdministrator()){
				getModuleService().handleModule(getId(), moduleList, null, true);
			}
			else{
				List<Role> accountRoleList = (List<Role>) ActionContext.getContext().getSession().get(SessionCons.ACCOUNT_ROLE_LIST);
				getModuleService().handleModule(getId(), moduleList, accountRoleList, false);
			}
		}
		return "left";
	}

}
