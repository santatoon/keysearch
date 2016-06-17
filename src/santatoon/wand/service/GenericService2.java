package santatoon.wand.service;

import java.util.List;


public interface GenericService2<T> {
	int add(T entity);

	void update(T entity);

	void delete(int id);

	T get(String name);
	
	List<T> getAll();
}
