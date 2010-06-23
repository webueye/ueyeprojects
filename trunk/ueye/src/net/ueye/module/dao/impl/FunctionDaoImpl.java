package net.ueye.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.ueye.common.common.MethodParam;
import net.ueye.common.dao.impl.BaseDaoImpl;
import net.ueye.module.dao.FunctionDao;
import net.ueye.module.entity.Function;

/**
 * 功能 Dao
 * @author rubys@vip.qq.com
 * Oct 2, 2009
 */
@Repository("functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao{

	public List<Function> findFunctionListByModule(long moduleId) {
		return findEntityListByEntityName(getGenericClassName(), MethodParam.params("moduleId"), MethodParam.values(moduleId));
	}
	
}
