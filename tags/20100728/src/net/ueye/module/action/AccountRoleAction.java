package net.ueye.module.action;

import java.util.List;

import net.ueye.module.common.Common;
import net.ueye.module.entity.Role;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * 用户与角色操作的 Action
 * @author rubys@vip.qq.com
 *  2009-7-4
 */
@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/account/")
@Result(name="account", type="redirectAction", params={"actionName", "account"})
public class AccountRoleAction extends BaseAction{	
		
	public String index(){
		this.setAccountRoleList(getAccountService().getAccountRoles(getId()));
		return "account-role-list";
	}
	
	public String show(){
		this.setAccountRoleList(getAccountService().getAccountRoles(getId()));
		return "account-role-list";
	}
	
	public String edit(){
		List<Role> roleList = getRoleService().getRoleListNoAssignByAccount(getId());
		List<Role> accountRoleList = getAccountService().getAccountRoles(getId());
		this.setSourceMap(Common.collectionConvertToMap(roleList));
		this.setTargetMap(Common.collectionConvertToMap(accountRoleList));	
		return "account-role-edit";
	}
	
	public String update(){		
		if("".equals(getChooseValues()))
			getAccountService().editAccountRole(getId(), null);
		else{
			long[] roleId = Common.stringToLong(getChooseValues().split(","));
			getAccountService().editAccountRole(getId(), roleId);		
		}
		return index();
	}
	
	public String destroy(){
		getAccountService().deleteAccountRole(getAccount().getId(), getId());
		setId(getAccount().getId());
		return index();
	}

}




















