package net.ueye.module.service.impl;

import java.io.Serializable;
import java.util.List;

import net.ueye.common.constant.Message;
import net.ueye.module.common.Common;
import net.ueye.module.entity.Module;
import net.ueye.module.entity.Role;
import net.ueye.module.exception.NotAssignRoleException;
import net.ueye.module.service.ModuleService;

import org.springframework.stereotype.Service;

/**
 * The ModuleService
 * @author rubys@vip.qq.com
 */
@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements ModuleService {
	
	/**
	 * 模块添加
	 * @param module
	 */
	public void insert(Module module) {
		if(module.getParent().getId() == -1){
			insertRootModule(module);
			return;
		}
		Module parent = getModuleDao().get(Module.class, module.getParent().getId());
		module.setWidth(parent.getWidth() + 1);
		module.setParent(parent);
		getModuleDao().insert(module);
	}
	
	/**
	 * 添加根模块
	 * @param module
	 */
	public void insertRootModule(Module module){
		int count = getModuleDao().countRootModule();
		if(count > 0){
			return;
		}
		module.setWidth(0);
		module.setParent(null);
		getModuleDao().insert(module);
	}
	
	/**
	 * 根据模块 ID 获取该模块实体
	 * @param id
	 * @return
	 */
	public Module get(Serializable id) {
		return getModuleDao().get(Module.class, id);
	}
	
	/**
	 * 更新模块
	 * @param entity
	 */
	public void update(Module entity) {
		Module module = getModuleDao().get(Module.class, entity.getParent().getId());
		entity.setWidth(module.getWidth() + 1);
		entity.setParent(module);
		getModuleDao().update(entity);
	}
	
	/**
	 * 删除 module 节点及子节点
	 * @param moduleId 待删除 module 的 id
	 */
	public void delete(long moduleId) {
		Module module = getModuleDao().get(Module.class, moduleId);
		if(module.isLeaf()){
			getModuleDao().delete(module);
		}
		else{
			deepDelete(module);
		}
	}
	
	/**
	 * 深度删除 Module 的节点及子节点
	 * @param Module 迭代删除 Module 的节点及子节点
	 */
	public void deepDelete(Module module){
		List<Module> list = getModuleDao().findModuleByParent(module.getId());
		if(list!=null){
			for(Module m: list){
				if(m.isLeaf()){
					getModuleDao().delete(m);
				}
				else{
					deepDelete(m);
				}
			}
			getModuleDao().delete(module);
		}
	}
	
	/**
	 * 获取根模块
	 * @return Module
	 */
	public Module getRootModule(){
		return getModuleDao().getRootModule();
	}
	
	/**
	 * 按 account 查找 module 列表
	 * @param account 按用户查找 module 节点
	 * @return List<Module> 返回该用户的 module 节点
	 */
	public List<Module> findModuleByRole(List<Role> roleList) {
		Module rootModule = getRootModule();
		if(rootModule == null){
			return null;
		}
		String moduleLs = compose(roleList);
		if("".equals(moduleLs)){
			throw new NotAssignRoleException(Message.NOT_ASSIGN_ROLE_EXCEPTION);
		}
		return getModuleDao().findModuleByRole(moduleLs, rootModule.getId());
	}
	
	/**
	 * 组合角色ID，形式为  id1,id2...
	 * @param roleList
	 * @return
	 */
	public String compose(List<Role> roleList){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < roleList.size(); i++){
			String moduleFunction = getRoleDao().getRoleModuleId(roleList.get(i).getId());
			if(moduleFunction != null && (!"".equals(moduleFunction))){
				sb.append(getRoleDao().getRoleModuleId(roleList.get(i).getId()));					
				if(i != (roleList.size()-1)){
					sb.append(",");	
				}
			}else{
				if(i == (roleList.size()-1)){
					sb.append("0");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param rootModule
	 * @param isAllLoad
	 * @return
	 */
	public List<Module> findModuleList(Module rootModule, boolean ... isAllLoad) {
		if(rootModule != null){
			List<Module> moduleList = this.getModuleDao().findModuleByParent(getRootModule().getId());
			deepQueryModuleByParent(moduleList, isAllLoad);
			return moduleList;
		}
		return null;
	}
		
	/**
	 * 查询所有非叶子节点的模块
	 * @param roleModules 当前用户所拥有的模块ID
	 * @param isAdmin 是否为管理员
	 * @return
	 */
	public Module findModuleNoLeafList(List<Role> roleList, boolean isAdmin){
		if(isAdmin){
			return getModuleDao().findModuleNotLeafList(null, isAdmin);
		}
		else{
			return getModuleDao().findModuleNotLeafList(compose(roleList), isAdmin);
		}
	}
	
	/**
	 * 深度查询 module 的各层子节点
	 * @param list 每层 module 的列表
	 * @param isAllLoad 
	 */
	public void deepQueryModuleByParent(List<Module> list, boolean ...isAllLoad){		
		for(int i = 0; i<list.size(); i++){
			Module tn = list.get(i);
			if(isAllLoad != null && isAllLoad.length > 0 && isAllLoad[0]){
				tn.setExpanded(true);
			}
			if(tn.isExpanded()){
				List<Module> ls = this.getModuleDao().findModuleByParent(tn.getId());		
				tn.setChild(ls);
				deepQueryModuleByParent(ls,isAllLoad);
			}
			if(i == list.size() - 1){
				list.get(list.size() - 1).setLast(true);
			}
		}
	}
	
	/**
	 * 根据 parentId 查找它的子模块
	 * @param parentId
	 * @return List<Module>
	 */
	public List<Module> findModuleByParent(long parentId) {
		return this.getModuleDao().findModuleByParent(parentId);
	}
	
	/**
	 * 根据 parent 查找它的具有相应权限的子模块
	 * @param parent
	 * @param roleHql
	 * @return
	 */
	public List<Module> findModuleByParentAndRole(Module parent, String roleHql){
		if(parent != null)
			return getModuleDao().findEntityListByHql(roleHql, parent.getId());
		return null;
	}
	
	/**
	 * 处理模块节点的展开与折叠
	 * @param list 每层 Module 的列表
	 * @param moduleId 待设置的 Module 的 ID
	 * @return List<Module> 返回首层 Module 的列表
	 */
	@SuppressWarnings("unchecked")
	public List<Module> handleTree(List<Module> list, long moduleId){
		if(list != null){
			for(Module module: list){
				if(module.getId() == moduleId){
					module.setExpanded(!module.isExpanded());
					if(module.isExpanded()&&module.getChild().size() == 0){
						module.setChild(this.getModuleDao().findModuleByParent(module.getId()));
						deepQueryModuleByParent((List<Module>)module.getChild());
					}
					return list;
				}
				else{
					handleTree((List<Module>)module.getChild(), moduleId);
				}
			}
		}
		return list;
	}
	
	/**
	 * 处理模块节点的展开与折叠[可以含有角色的处理]
	 * @param moduleList
	 * @param moduleId
	 * @param roleHql
	 * @param isAdmin 如果为 true 指是含有角色的处理
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Module> handleModule(long moduleId, List<Module> moduleList, List<Role> roleList, boolean isAdmin){
		if(moduleList != null){
			for(Module module: moduleList){
				if(module.getId() == moduleId){
					module.setExpanded(!module.isExpanded());
					if(module.isExpanded() && module.getChild().size() == 0){
						if(isAdmin){
							module.setChild(findModuleByParent(module.getId()));
							deepQueryModuleByParent((List<Module>)module.getChild());
						}
						else{
							module.setChild(getModuleDao().findModuleByRole(compose(roleList), module.getId()));
							getModuleDao().findModuleByRole((List<Module>)module.getChild(), compose(roleList));
						}
					}
					return moduleList;
				}
				else{
					handleModule(moduleId,(List<Module>)module.getChild(),  roleList, isAdmin);
				}
			}
		}
		return moduleList;
	}
	
	/**
	 * 更新修改，添加，删除等操作后的 module
	 * @param list  module 列表
	 * @param Module 被操作的 module
	 * @param id 被操作 module 的 id
	 * @param type 修改、添加、删除等操作类型
	 * @return list<Module>
	 */
	@SuppressWarnings("unchecked")
	public List<Module> updateModule(List<Module> list, Module module, long id, String type){
		if(list != null){
			for(Module m: list){
				if(id == (m.getId())){
					if(Common.MODULE_DELETE.equals(type)){				
						list.remove(m);
					}
					else if(Common.MODULE_NON_PARENT_UPDATE.equals(type)){
						//修改模块的 显示与隐藏 属性时，加载它的子模块
						if(module.isExpanded() && module.getChild().size() == 0){
							m.setChild(this.getModuleDao().findModuleByParent(module.getId()));
						}
						m.setLabel(module.getLabel());
						m.setExpanded(module.isExpanded());
						m.setLeaf(module.isLeaf());
						m.setAction(module.getAction());
					}
					else if(Common.MODULE_UPDATE.equals(type)){
						((List<Module>)m.getChild()).add(module);						
					}
					else if(Common.MODULE_INSERT.equals(type))
						((List<Module>)m.getChild()).add(module);	
					return list;
				}
				updateModule((List<Module>)m.getChild(), module, id, type);
			}
		}
		return list;
	}
	
}
