package net.ueye.module.service;

import java.util.List;

import net.ueye.module.entity.Function;
import net.ueye.module.entity.Module;

/**
 * @author rubys@vip.qq.com
 * Oct 2, 2009
 */
public interface FunctionService extends BaseService<Function> {
	
	/**
	 * 查找模块的所有的功能
	 */
	List<Module> findFunctionByModule(List<Module> moduleList);
	
	/**
	 * 根据模块ID查找功能列表
	 */
	List<Function> findFunctionListByModule(long moduleId);
	
	void delete(long id);
	
	/**
	 * 选中已拥有的功能
	 * @param functionList
	 * @param roleId
	 * @return
	 */
	List<Module> checkedFunction(List<Module> moduleList,long roleId);

}
