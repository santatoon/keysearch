package santatoon.wand.dao;

import java.util.List;

public interface GenericDao<T> {
	int add(T entity);

	void update(T entity);

	void delete(int id);

	T get(String id);

	void deleteAll();

	List<T> getAll();

	int getCount();
}
