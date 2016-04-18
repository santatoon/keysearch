package santatoon.wand.service;

import java.util.List;


public interface GenericService<T> {
	int add(T entity);

	void update(T entity);

	void delete(int id);

	T get(int id);
	
	List<T> getAll();
}
