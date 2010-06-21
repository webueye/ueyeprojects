package net.ueye.module.action;

import java.util.List;

import net.ueye.module.common.Common;
import net.ueye.module.entity.UserGroup;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * 用户里面组设置
 * @author rubys@vip.qq.com
 * Aug 30, 2009
 */
@SuppressWarnings("serial")
@Result(name="account", type="redirectAction", params={"actionName", "account"})
@ResultPath("/WEB-INF/module/manage/account/")
public class AccountGroupAction extends BaseAction {
	
	public String index(){
		getPage().setData(getAccountService().findUserGroupByAccount(getId()));		
		return "account-group-list";
	}
	
	public String show(){
		getPage().setData(getAccountService().findUserGroupByAccount(getId()));		
		return "account-group-list";
	}
	
	public String edit(){
		List<UserGroup> userGroupList = getAccountService().findUserGroupNoAssign(getId());
		List<UserGroup> accountGroupList = getAccountService().findUserGroupByAccount(getId());
		setSourceMap(Common.UserGroupConvertToMap(userGroupList));
		setTargetMap(Common.UserGroupConvertToMap(accountGroupList));	
		return "account-group-edit";
	}
	
	public String update(){
		if("".equals(getChooseValues()))
			this.getAccountService().editAccountGroup(getId(), null);
		else{
			long[] userGroups=Common.stringToLong(getChooseValues().split(","));
			getAccountService().editAccountGroup(getId(), userGroups);		
		}
		return index();
	}
	
	public String destroy(){
		getUserGroupService().deleteAccountAndUserGroup(getAccount().getId(), getId());
		setId(getAccount().getId());
		return index();
	}

}
