package net.ueye.module.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import net.ueye.module.dao.AccountDao;
import net.ueye.module.dao.AccountRoleDao;
import net.ueye.module.dao.FunctionDao;
import net.ueye.module.dao.ModuleDao;
import net.ueye.module.dao.RoleDao;
import net.ueye.module.dao.RoleFunctionDao;
import net.ueye.module.dao.RoleModuleDao;
import net.ueye.module.dao.UserGroupDao;
import net.ueye.module.dao.UserToGroupDao;
import net.ueye.module.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T>{
		
	@Autowired
	private UserGroupDao userGroupDao;
	@Autowired
	private UserToGroupDao userToGroupDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private AccountRoleDao accountRoleDao;
	@Autowired
	private FunctionDao functionDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private RoleModuleDao roleModuleDao;
	@Autowired
	private RoleFunctionDao roleFunctionDao;
		
	public UserGroupDao getUserGroupDao() {
		return userGroupDao;
	}

	public void setUserGroupDao(UserGroupDao userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public FunctionDao getFunctionDao() {
		return functionDao;
	}

	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}

	public AccountRoleDao getAccountRoleDao() {
		return accountRoleDao;
	}

	public void setAccountRoleDao(AccountRoleDao accountRoleDao) {
		this.accountRoleDao = accountRoleDao;
	}

	public UserToGroupDao getUserToGroupDao() {
		return userToGroupDao;
	}

	public void setUserToGroupDao(UserToGroupDao userToGroupDao) {
		this.userToGroupDao = userToGroupDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public RoleModuleDao getRoleModuleDao() {
		return roleModuleDao;
	}

	public void setRoleModuleDao(RoleModuleDao roleModuleDao) {
		this.roleModuleDao = roleModuleDao;
	}

	public RoleFunctionDao getRoleFunctionDao() {
		return roleFunctionDao;
	}

	public void setRoleFunctionDao(RoleFunctionDao roleFunctionDao) {
		this.roleFunctionDao = roleFunctionDao;
	}
	
}
