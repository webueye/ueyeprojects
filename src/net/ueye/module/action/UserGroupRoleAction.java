package net.ueye.module.action;

import java.util.List;

import net.ueye.module.common.Common;
import net.ueye.module.entity.Role;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;

/**
 * 用户组角色管理	Action
 * @author rubys@vip.qq.com
 * Aug 29, 2009
 */
@SuppressWarnings("serial")
@ResultPath("/WEB-INF/module/manage/userGroup/")
@Result(name="userGroupRole", type="redirectAction", params={"actionName", "user-group-role"})
public class UserGroupRoleAction extends BaseAction {
	
	/**
	 * 获取用户组角色列表
	 * @return
	 */
	public String index(){
		getPage().setData(getUserGroupService().findRoleListByUserGroup(getId()));		
		return "user-group-role-list";
	}
	
	/**
	 * 编辑用户组中的角色列表
	 * @return
	 */
	public String edit(){
		List<Role> roleList = getRoleService().getRoleListNoAssignByUserGroup(getId());
		List<Role> userGroupRoleList = getUserGroupService().findRoleListByUserGroup(getId());
		setSourceMap(Common.collectionConvertToMap(roleList));
		setTargetMap(Common.collectionConvertToMap(userGroupRoleList));	
		return "user-group-role-edit";
	}
	
	/**
	 * 更新用户组的中角色
	 * @return
	 */
	public String update(){
		if("".equals(getChooseValues()))
			this.getUserGroupService().editUserGroupRoles(getId(), null);
		else{
			long[] roleId = Common.stringToLong(getChooseValues().split(","));
			getUserGroupService().editUserGroupRoles(getId(), roleId);		
		}
		return index();
	}
			
	/**
	 * 删除用户组中的某一角色
	 * @return
	 */
	public String destroy(){
		getUserGroupService().deleteRole(getId(),getUserGroup().getId());
		this.setId(getUserGroup().getId());
		return index();
	}

}
