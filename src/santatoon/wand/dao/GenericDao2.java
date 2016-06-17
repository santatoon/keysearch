package santatoon.wand.dao;

import java.util.List;

public interface GenericDao2<T> {
	int add(T entity);

	void update(T entity);

	void delete(int id);

	T get(String name);

	void deleteAll();

	List<T> getAll();

	int getCount();
}
