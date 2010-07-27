package net.ueye.module.dao;

import java.util.List;

import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.Module;

public interface ModuleDao extends BaseDao<Module>{
	
	/**
	 * 获取根模块
	 * @return Module
	 */
	Module getRootModule();
	
	List<Module> findModuleByRole(String moduleList, long parentModule);
	
	List<Module> findModuleByRole(List<Module> moduleList, String moduleIdList);
	
	/**
	 * 查询所有非叶子节点的模块
	 * @param roleModules 当前用户所拥有的模块ID
	 * @param isAdmin 是否为管理员
	 * @return
	 */
	Module findModuleNotLeafList(String roleModules, boolean isAdmin);
	
	/**
	 * 统计根模块的数量
	 * @return
	 */
	int countRootModule();
	
	/**
	 * 根据父模块的ID查询它的子模块列表
	 * @param parentId
	 * @return
	 */
	List<Module> findModuleByParent(long parentId);	
		

}
