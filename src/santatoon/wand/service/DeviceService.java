package santatoon.wand.service;

import java.util.List;

import santatoon.wand.domain.Device;

public interface DeviceService extends GenericService<Device>{
	List<Device> getAllForMobile(int id);
	void pushToDevices(int deviceId) throws Exception;
}
