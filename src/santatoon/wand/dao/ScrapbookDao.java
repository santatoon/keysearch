package santatoon.wand.dao;

import java.util.List;

import santatoon.wand.domain.Scrapbook;

public interface ScrapbookDao {
	int add(Scrapbook entity);

	void update(Scrapbook entity);

	void delete(int id);

	void deleteAll();

	List<Scrapbook> getAll(int customerid);

	int getCount();
	Scrapbook get(int id);
}
