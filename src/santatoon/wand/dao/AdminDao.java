package santatoon.wand.dao;

import java.util.List;

import santatoon.wand.domain.Admin;

public interface AdminDao {
	void add(Admin admin);

	void update(Admin admin);

	void delete(String id);

	Admin get(String id);

	void deleteAll();

	List<Admin> getAll();

	int getCount();
}
