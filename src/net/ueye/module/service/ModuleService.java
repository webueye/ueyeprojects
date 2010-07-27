package net.ueye.module.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;

/**
 * @author rubys@vip.qq.com
 *  2009-7-5
 */
public interface ModuleService extends BaseService<Module>{
	

	@Transactional(readOnly=true)
	List<Module> findModuleByRole(List<Role> roleList);

	@Transactional(readOnly=true)
	List<Module> findModuleByParent(long parentId);

	@Transactional(readOnly=true)
	List<Module> updateModule(List<Module> list, Module module, long id, String type);

	@Transactional(readOnly=true)
	Module getRootModule();
	
	String compose(List<Role> roleList);

	@Transactional(readOnly=true)
	void deepQueryModuleByParent(List<Module> list, boolean ...isAllLoad);

	@Transactional(readOnly=true)
	List<Module> findModuleList(Module rootModule, boolean ... isAllLoad);
	
	/**
	 * 
	 * @param list
	 * @param parentId
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Module> handleTree(List<Module> list, long parentId);
	
	/**
	 * 查询所有非叶子节点的模块
	 * @param roleList 当前用户所拥有的角色
	 * @param isAdmin 是否为管理员
	 * @return
	 */
	@Transactional(readOnly = true)
	Module findModuleNoLeafList(List<Role> roleList, boolean isAdmin);
	
	/**
	 * 根据 parent 查找它的具有相应权限的子模块
	 * @param parent
	 * @param roleHql
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Module> findModuleByParentAndRole(Module parent, String roleHql);
	
	/**
	 * 处理模块节点的展开与折叠[可以含有角色的处理]
	 * @param moduleList
	 * @param moduleId
	 * @param roleHql
	 * @param isAdmin 如果为 true 指是含有角色的处理
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Module> handleModule(long moduleId, List<Module> moduleList, List<Role> roleList, boolean isAdmin);
	
	
}
