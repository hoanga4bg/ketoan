package com.htttql.service;

import java.util.List;

public interface GeneralService<T> {
	List<T> findAll();
	T save(T t);
	T findOneById(int id);
	void delete(int id);
	T update(int id,T t);

}
