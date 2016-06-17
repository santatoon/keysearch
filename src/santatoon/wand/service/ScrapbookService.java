package santatoon.wand.service;


import java.util.List;

import santatoon.wand.domain.Scrapbook;

public interface ScrapbookService{
	int add(Scrapbook entity);

	void update(Scrapbook entity);

	void delete(int id);

	Scrapbook get(int id);
	
	List<Scrapbook> getAll(int customerid);
}
