package net.ueye.module.dao;

import java.util.List;

import net.ueye.common.dao.BaseDao;
import net.ueye.module.entity.Function;

/**
 * 功能	Dao
 * @author rubys@vip.qq.com
 * Oct 2, 2009
 */
public interface FunctionDao extends BaseDao<Function>{
	
	List<Function> findFunctionListByModule(long moduleId);

}