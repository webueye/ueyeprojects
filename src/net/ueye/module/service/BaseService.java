package net.ueye.module.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseService<T> {
	
	T get(Serializable id);
	
	void delete(long id);

	void update(T entity);

	void insert(T entity);
	
}
