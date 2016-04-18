package santatoon.wand.service;

import java.util.List;

import santatoon.wand.domain.Admin;

public interface AdminService {
	void add(Admin admin);

	void update(Admin admin);

	void delete(String id);

	Admin get(String id);
	
	List<Admin> getAll();
}
