package net.ueye.module.dao.impl;

import org.springframework.stereotype.Repository;

import net.ueye.common.common.MethodParam;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.dao.RoleModuleDao;
import net.ueye.module.entity.RoleModule;

/**
 * @author rubys@vip.qq.com
 * Jun 23, 2010
 */
@Repository("roleModuleDao")
public class RoleModuleDaoImpl extends BaseDaoImpl<RoleModule> implements RoleModuleDao {

	public void deleteModule(long roleId) {
		delete(getGenericClassName(), MethodParam.params("roleId"), MethodParam.values(roleId));
	}

}
