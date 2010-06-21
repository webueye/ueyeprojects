package net.ueye.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.ueye.common.constant.Message;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.common.Config;
import net.ueye.module.dao.ModuleDao;
import net.ueye.module.entity.Module;
import net.ueye.module.exception.NotAssignRoleException;

/**
 * The ModuleDao
 * @author rubys@vip.qq.com
 * 2010-6-13
 */
@Repository("moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl<Module> implements ModuleDao {
	
	/**
	 * 获取根模块
	 * @return Module
	 */
	public Module getRootModule(){
		//List<Module> moduleList = findEntityListByHql("FROM Module m WHERE m.parent=null");
		List<Module> moduleList = findEntityListByHql(Config.get("ModuleDao.getRootModule"));
		if(moduleList.size() > 0)
			return moduleList.get(0);
		return null;
	}
	
	public List<Module> findModuleByRole(String moduleIdList, long parentModule){
		//String hql = "FROM Module m WHERE m.parent.id=? AND m.id in("+ moduleIdList +") ORDER BY m.orderValue";
		List<Module> moduleList = findEntityListByHql(Config.get("ModuleDao.findModuleByParentAndModules"), parentModule, moduleIdList);
		deepQueryModuleByHql(moduleList, Config.get("ModuleDao.findModuleByParentAndModuleIds"), false);
		return moduleList;
	}
	
	public List<Module> findModuleByRole(List<Module> moduleList, String moduleIdList){
		String hql = "FROM Module m WHERE m.parent.id=? and m.id in("+ moduleIdList +") ORDER BY m.orderValue";
		deepQueryModuleByHql(moduleList, hql, false);
		return moduleList;
	}
	
	/**
	 * 查询所有非叶子节点的模块
	 * @param roleModules 当前用户所拥有的模块ID
	 * @param isAdmin 是否为管理员
	 * @return
	 */
	public Module findModuleNotLeafList(String roleModules, boolean isAdmin){
		Module module = getRootModule();
		if(module != null){
			List<Module> moduleList = null;
			//String hql="FROM Module m WHERE m.parent.id = ? AND m.id in("+ roleModules +") and m.parent.leaf = false ORDER BY m.orderValue";
			String hql = Config.get("ModuleDao.findAllModuleNotLeafListByModules");
			if(isAdmin){
				//hql = "FROM Module m WHERE m.parent.id = ? AND m.parent.leaf=false  ORDER BY m.orderValue";
				hql = Config.get("ModuleDao.findAllModuleNotLeafList");
			}
			moduleList = findEntityListByHql(hql, module.getId());
			deepQueryModuleByHql(moduleList, hql, true);
			module.setChild(moduleList);
		}
		return module;
	}
	
	/**
	 * @param moduleList
	 * @param hql
	 * @param isAllLoad 如果为 true 则不区别 它的显示与隐藏属性 全部加载加载出来
	 */
	@SuppressWarnings("unchecked")
	private void deepQueryModuleByHql(List<Module> moduleList, String hql, boolean isAllLoad){
		if(hql == null || "".equals(hql)){
			throw new NotAssignRoleException(Message.NOT_ASSIGN_ROLE_EXCEPTION);
		}
		for(int i = 0; i < moduleList.size(); i++){
			Module module = moduleList.get(i);
			if(isAllLoad){
				module.setExpanded(true);
			}
			if(module.isExpanded()){
				module.setChild(findEntityListByHql(hql, module.getId()));
				deepQueryModuleByHql((List<Module>)module.getChild(), hql, isAllLoad);
			}
			if(i == moduleList.size() - 1){
				moduleList.get(moduleList.size() - 1).setLast(true);
			}
		}
	}
	
	public int countRootModule(){
		//return countByHql("SELECT count(*) FROM Module module WHERE module.parent = null");
		return countByHql(Config.get("ModuleDao.countRootModule"));
	}

	public List<Module> findModuleByParent(long parentId) {
		//return findEntityListByHql("FROM Module module WHERE module.parent.id=? ORDER BY module.orderValue", parentId);
		return findEntityListByHql(Config.get("ModuleDao.findModuleByParent"), parentId);
	}

}
