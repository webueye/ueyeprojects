package net.ueye.module.action;

import net.ueye.module.common.MessageCons;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * 
 * @author rubys@vip.qq.com
 * Aug 28, 2009
 */
@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/userGroup/")
@Result(name="userGroup", type="redirectAction", params={"actionName", "user-group"})
public class UserGroupAction extends BaseAction {
	
	public String index(){
		getPage().setData(getUserGroupService().findUserGroup(getPage()));
		return "user-group-list";
	}
	
	public String editNew(){
		return "user-group-insert";
	}
	
	public String edit(){
		setUserGroup(getUserGroupService().get(getId()));
		return "user-group-edit";
	}
	
	public String update(){
		getUserGroupService().update(getUserGroup());
		return "userGroup";
	}
	
	public String create(){
		if(isExist())
			return "user-group-insert";
		getUserGroupService().save(getUserGroup());
		return "userGroup";
	}
	
	public String destroy(){
		getUserGroupService().delete(getId());
		return "userGroup";
	}
	
	public boolean isExist(){
		int userGroupCount = getUserGroupService().countUserGroup(getUserGroup().getGroupName());
		if(userGroupCount > 0){
			this.addActionMessage(getText(MessageCons.USERGROUP_IS_EXIST));
			return true;
		}
		return false;
	}

}
