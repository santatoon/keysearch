package santatoon.wand.dao;

import java.util.List;

import santatoon.wand.domain.Device;

public interface DeviceDao extends GenericDao<Device>{
	List<Device> getAllForMobile(int id);
}
