package santatoon.support;

import santatoon.wand.dao.GenericDao;


public interface EntityProxyFactory {
	<T> T createProxy(Class<T> clazz, GenericDao<T> dao, int id);
}
