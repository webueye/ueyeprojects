package net.ueye.module.dao;

import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.RoleModule;

/**
 * @author rubys@vip.qq.com
 * Jun 23, 2010
 */
public interface RoleModuleDao extends BaseDao<RoleModule>{
	
	void deleteModule(long roleId);

}
