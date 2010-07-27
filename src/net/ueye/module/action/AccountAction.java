package net.ueye.module.action;

import net.ueye.module.common.Common;
import net.ueye.module.common.MessageCons;
import net.ueye.module.common.SessionCons;
import net.ueye.module.entity.Account;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

/**
 * 用户管理 Action
 * @author rubys@vip.qq.com
 * Aug 30, 2009
 */
@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/account/")
@Results({
	@Result(name="account", type="redirectAction", params={"actionName", "account"}),
	@Result(name="login", type="redirectAction", location="/main/login.jsp")
})
public class AccountAction extends BaseAction{
	
	public String index(){		
		setAccountList(getAccountService().getAccountList(getPage()));
		return "account-list";
	}
	
	public String show(){
		setAccount(getAccountService().get(getId()));
		return "account-edit";
	}
	
	public String editNew(){
		return "account-insert";
	}
	
	public String update(){
		getAccount().setPassword(Common.MD5(getAccount().getPassword()));
		if(isModifyPassword()){
			getAccount().setPassword(getOriginalPassword());
		}
		getAccountService().update(getAccount());
		return "account";
	}
	
	public String create(){
		if(isExist())
			return "account-insert";
		else{
			getAccount().setPassword(Common.MD5(getAccount().getPassword()));
			getAccountService().insert(getAccount());
			return "account";
		}
	}
	
	public String password(){
		return "account-password-edit";
	}
	
	public String editPassword(){
		Account account = getAccountService().get(getCurrentAccount().getId());
		if(!account.getPassword().equals(getOriginalPassword())){
			addActionMessage(getText(MessageCons.ORIGINAL_PASSWORD_IS_NOT_CORRENT));
			return "account-password-edit";
		}
		if(!getPassword().equals(getAccount().getPassword())){
			addActionMessage(getText(MessageCons.NEW_PASSWORD_IS_NOT_SAME));
			return "account-password-edit";
		}
		account.setPassword(getAccount().getPassword());
		getAccountService().update(account);
		ServletActionContext.getRequest().getSession().removeAttribute(SessionCons.CURRENT_ACCOUNT);
		return "login";
	}
	
	public String destroy(){
		getAccountService().delete(getId());
		return "account";
	}
	
	public String state(){
		getAccountService().state(getId());
		return "account";
	}
	
	public boolean isExist(){
		int accCount = getAccountService().countAccount(getAccount().getUsername());
		if(accCount > 0){
			addActionMessage(getText(MessageCons.ACCOUNT_ISEXIST));
			return true;
		}
		else
			return false;
	}

}
