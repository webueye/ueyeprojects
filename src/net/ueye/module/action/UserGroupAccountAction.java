package net.ueye.module.action;

import java.util.List;

import net.ueye.module.common.Common;
import net.ueye.module.entity.Account;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * 用户组中的用户 Action
 * @author rubys@vip.qq.com
 * Aug 29, 2009
 */
@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/userGroup/")
@Result(name="userGroupAccount", type="redirectAction", params={"actionName", "user-group-account"})
public class UserGroupAccountAction extends BaseAction {
	
	/**
	 * 获取用户组的用户信息
	 * @return
	 */
	public String index(){
		getPage().setData(getAccountService().findAccountListByUserGroup(getId()));		
		return "user-group-user-list";
	}
	
	/**
	 * 获取用户组中的用户信息及用户组没有的用户信息
	 * @return
	 */
	public String edit(){
		List<Account> accountList = getAccountService().findAccountListNoUserGroup(getId());
		List<Account> userGroupAccoutList = getAccountService().findAccountListByUserGroup(getId());
		setSourceMap(Common.accountConvertToMap(accountList));
		setTargetMap(Common.accountConvertToMap(userGroupAccoutList));
		return "user-group-account-edit";
	}
	
	/**
	 * 更新用户组中的用户数量
	 * @return
	 */
	public String update(){
		if("".equals(getChooseValues()))
			this.getUserGroupService().editUserGroupAccounts(getId(), null);
		else{
			long[] accountId = Common.stringToLong(getChooseValues().split(","));
			getUserGroupService().editUserGroupAccounts(getId(), accountId);		
		}
		return index();
	}
	
	/**
	 * 删除用户组中的某一用户
	 * setId(getUserGroup().getId())
	 * 设置用户组的ID 根据ID获取该用户组的用户信息
	 * @return
	 */
	public String destroy(){
		getUserGroupService().deleteAccountAndUserGroup(getId(), getUserGroup().getId());
		setId(getUserGroup().getId());
		return index();
	}

}
