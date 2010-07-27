package net.ueye.module.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

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
				List<Function> functionList = findFunctionListByModule(module.getId());
				module.setFunctions(functionList);
				findFunctionByModule((List<Module>)module.getChild());
			}
		}		
		return moduleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> checkedFunction(List<Module> moduleList, long roleId){
		if(moduleList != null){
			List<String> funId = (List<String>) getRoleFunctionDao().findFunctionByRole(roleId);
			for(Module module: moduleList){
				List<Function> functionList = module.getFunctions();
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
		return getFunctionDao().findFunctionListByModule(moduleId);
	}
	
	public void delete(long functionId){
		getFunctionDao().delete(Function.class, functionId);
		getRoleFunctionDao().deleteFunctionByFunction(functionId);
		
	}

}
