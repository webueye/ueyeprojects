package net.ueye.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.ueye.common.common.MethodParam;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.dao.RoleFunctionDao;
import net.ueye.module.entity.RoleFunction;

/**
 * @author rubys@vip.qq.com
 * Jun 23, 2010
 */
@Repository("roleFunctionDao")
public class RoleFunctionDaoImpl extends BaseDaoImpl<RoleFunction> implements RoleFunctionDao {
	
	@SuppressWarnings("unchecked")
	public List<String> findFunctionByRole(long roleId){
		return (List<String>) findDataList("select rf.functionId from RoleFunction rf where rf.roleId=?", roleId);
	}

	public void deleteFunction(long roleId) {
		delete(getGenericClassName(), MethodParam.params("roleId"), MethodParam.values(roleId));
	}
	
	public void deleteFunctionByFunction(long functionId){
		delete(getGenericClassName(), MethodParam.params("functionId"), MethodParam.values(String.valueOf(functionId)));
	}

}
