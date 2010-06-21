package net.ueye.module.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import net.ueye.common.common.MethodParam;
import net.ueye.common.constant.Entity;
import net.ueye.module.entity.Function;
import net.ueye.module.entity.Module;
import net.ueye.module.service.FunctionService;

/**
 * @author rubys@vip.qq.com
 */
@Service("functionService")
public class FunctionServiceImpl extends BaseServiceImpl<Function> implements FunctionService {
	
	public Function get(Serializable functionId){
		return getFunctionDao().get(Function.class, functionId);
	}
	
	/**
	 * 功能添加
	 */
	public void insert(Function entity) {
		getFunctionDao().insert(entity);
	}
	
	public void update(Function function){
		getFunctionDao().update(function);
	}
	
	/**
	 * 查找模块的所有的功能
	 */
	@SuppressWarnings("unchecked")
	public List<Module> findFunctionByModule(List<Module> moduleList){
		if(moduleList != null){
			for(Module module: moduleList){
				List<Function> functionList = getFunctionDao().findEntityListByEntityName(Entity.FUNCTION, MethodParam.params("moduleId"), MethodParam.values(module.getId()));
				module.setFunctions(functionList);
				findFunctionByModule((List<Module>)module.getChild());
			}
		}		
		return moduleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> checkedFunction(List<Module> moduleList, long roleId){
		if(moduleList != null){
			List<String> funId = (List<String>) getFunctionDao().findDataList("select rf.functionId from RoleFunction rf where rf.roleId=?",roleId);
			for(Module module: moduleList){
				List<Function> functionList=module.getFunctions();
				if(functionList != null){
					nextFun:for(Function fun: functionList){
						for(String fid: funId){
							if(fun.getId() == Long.valueOf(fid)){
								fun.setChecked("checked");								
								continue nextFun;
							}
						}
					}
				}
			}
		}
		return moduleList;
	}
	
	/**
	 * 根据模块ID查找功能列表
	 */
	public List<Function> findFunctionListByModule(long moduleId){
		//return getFunctionDao().find("from Function function where function.moduleId=?",moduleId);
		return getFunctionDao().findEntityListByEntityName(Entity.FUNCTION, MethodParam.params("moduleId"),MethodParam.values(moduleId));
	}
	
	public void delete(long id){
//		getFunctionDao().delete("delete from Function function where function.id=?",id);
//		getFunctionDao().delete("delete from RoleFunction roleFun where roleFun.functionId=?",String.valueOf(id));
		
		getFunctionDao().delete(Function.class,id);
		getFunctionDao().delete(Entity.ROLE_FUNCTION, MethodParam.params("functionId"), MethodParam.values(String.valueOf(id)));
	}
	

}
