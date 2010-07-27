package net.ueye.module.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.ueye.common.common.Page;
import net.ueye.module.common.SessionCons;
import net.ueye.module.entity.Account;
import net.ueye.module.entity.Function;
import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;
import net.ueye.module.entity.UserGroup;
import net.ueye.module.service.AccountService;
import net.ueye.module.service.FunctionService;
import net.ueye.module.service.ModuleService;
import net.ueye.module.service.RoleService;
import net.ueye.module.service.UserGroupService;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	
	private long id;
	private Account account;
	private long parentId;
	private String function[];
	private String moduleFunction[];
	private String chooseValues;
	private String password;
	private String originalPassword;
	private boolean modifyPassword;
	private Module module;
	private Role role;
	private Function funModel;
	private UserGroup userGroup;
	private List<Role> roleList;
	private List<Account> accountList;
	private List<Role> accountRoleList;
	private String result = "moduleList";
	private RoleService roleService;
	private AccountService accountService;
	private ModuleService moduleService;
	private UserGroupService userGroupService;	
	private Map<String,String> targetMap;
	private Map<String,String> sourceMap;
	private FunctionService functionService;
	
	private Page page=new Page();
	
	public void beforeAnsy(){
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setHeader("Cache-Control","no-cache");
		ServletActionContext.getResponse().setHeader("Pragma","no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires",0);
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String[] getFunction() {
		return function;
	}

	public void setFunction(String[] function) {
		this.function = function;
	}

	public List<Role> getAccountRoleList() {
		return accountRoleList;
	}

	public void setAccountRoleList(List<Role> accountRoleList) {
		this.accountRoleList = accountRoleList;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public UserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getChooseValues() {
		return chooseValues;
	}

	public void setChooseValues(String chooseValues) {
		this.chooseValues = chooseValues;
	}

	public Map<String, String> getTargetMap() {
		return targetMap;
	}

	public void setTargetMap(Map<String, String> targetMap) {
		this.targetMap = targetMap;
	}

	public Map<String, String> getSourceMap() {
		return sourceMap;
	}

	public void setSourceMap(Map<String, String> sourceMap) {
		this.sourceMap = sourceMap;
	}
	
	public Account getCurrentAccount(){
		return (Account) ActionContext.getContext().getSession().get(SessionCons.CURRENT_ACCOUNT);
	}
	
	public boolean isAdministrator(){
		Account account = getCurrentAccount();
		if(account != null)
			return account.isAdmin();
		return false;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public Function getFunModel() {
		return funModel;
	}

	public void setFunModel(Function funModel) {
		this.funModel = funModel;
	}

	public String[] getModuleFunction() {
		return moduleFunction;
	}

	public void setModuleFunction(String[] moduleFunction) {
		this.moduleFunction = moduleFunction;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

	public boolean isModifyPassword() {
		return modifyPassword;
	}

	public void setModifyPassword(boolean modifyPassword) {
		this.modifyPassword = modifyPassword;
	}
	
}
