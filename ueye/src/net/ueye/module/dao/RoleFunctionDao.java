package net.ueye.module.dao;

import java.util.List;

import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.RoleFunction;

/**
 * @author rubys@vip.qq.com
 * Jun 23, 2010
 */
public interface RoleFunctionDao extends BaseDao<RoleFunction>{
	
	List<String> findFunctionByRole(long roleId);
	
	void deleteFunction(long roleId);
	
	void deleteFunctionByFunction(long functionId);

}
