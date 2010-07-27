package net.ueye.module.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import net.ueye.module.entity.UserToGroup;
import net.ueye.module.service.UserToGroupService;

/**
 * @author rubys@vip.qq.com
 * Aug 29, 2009
 */
@Service("userToGroupService")
public class UserToGroupServiceImpl extends BaseServiceImpl<UserToGroup> implements UserToGroupService {

	public void delete(long id) {
		getUserToGroupDao().delete(UserToGroup.class, id);
	}

	public void insert(UserToGroup entity) {
		getUserToGroupDao().insert(entity);
	}

	public void update(UserToGroup entity) {
		getUserToGroupDao().update(entity);
	}

	public UserToGroup get(Serializable id) {
		return getUserToGroupDao().get(UserToGroup.class, id);
	}

	

}
