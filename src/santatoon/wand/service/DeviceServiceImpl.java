package santatoon.wand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import santatoon.wand.dao.DeviceDao;
import santatoon.wand.domain.Device;

@Service
@Transactional
// @ContextConfiguration(locations ="file:WebContent/WEB-INF/xmppContext.xml")
public class DeviceServiceImpl implements DeviceService {
	private DeviceDao deviceDao;

	@Autowired
	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	@Override
	public int add(Device device) {
		// TODO Auto-generated method stub
		device.initDates();
		return this.deviceDao.add(device);
	}

	@Override
	public void update(Device device) {
		// TODO Auto-generated method stub
		this.deviceDao.update(device);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		this.deviceDao.delete(id);
	}

	@Override
	public Device get(int id) {
		// TODO Auto-generated method stub
		return this.deviceDao.get(id);
	}

	@Override
	public List<Device> getAll() {
		// TODO Auto-generated method stub
		return this.deviceDao.getAll();
	}

	@Override
	public List<Device> getAllForMobile(int id) {
		// TODO Auto-generated method stub
		return this.deviceDao.getAllForMobile(id);
	}

	@Override
	public void pushToDevices(int deviceid) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * ApplicationContext context = new ClassPathXmlApplicationContext(
		 * "file:WebContent/WEB-INF/xmppContext.xml");
		 * 
		 * MessageChannel toUserChannel = context.getBean("toUserChannel",
		 * MessageChannel.class); Message<String> message = new
		 * GenericMessage<String>("Hello from Spring Integration XMPP");
		 * toUserChannel.send(message);
		 */
		/*
		 * Message<String> xmppOutboundMsg = MessageBuilder.withPayload(
		 * "Hello, XMPP!" ) .setHeader("test@121.130.41.58", "userhandle")
		 * .build();
		 * 
		 */

		/*
		 * Device device = this.deviceDao.get(deviceid); String command =
		 * "sudo python ./Desktop/test/switch_servo " +
		 * (device.getStatus().getValue()? "0":"1"); String userName = "pi";
		 * String password = "raspberry"; String connectionIP =
		 * device.getPushid(); SSHManager instance = new SSHManager(userName,
		 * password, connectionIP);
		 * 
		 * instance.connect(); instance.sendCommand(command); instance.close();
		 * 
		 * device.setStatus(device.getStatus().getValue()?
		 * Status.OFF:Status.ON); this.deviceDao.update(device);
		 * 
		 */

	}
}
