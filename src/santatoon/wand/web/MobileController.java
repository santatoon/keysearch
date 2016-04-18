package santatoon.wand.web;

import java.sql.Date;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import santatoon.wand.domain.Customer;
import santatoon.wand.domain.Device;
import santatoon.wand.domain.Log;
import santatoon.wand.domain.Status;
import santatoon.wand.service.CustomerService;
import santatoon.wand.service.DeviceService;
import santatoon.wand.service.LogService;

@Controller
@Transactional
@RequestMapping("/mobile")
public class MobileController {
	@Autowired
	private View xmlView;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("/customers/{name}/{phone}")
	public String createCustomer(@PathVariable String name, @PathVariable String phone, Model model) {
		Customer customer = new Customer();
		customer.initDates();
		customer.setName(name);
		customer.setPhone(phone);
		model.addAttribute("result", customerService.add(customer));
		return "mobile/result";
	}
	
	@RequestMapping("/devices/{customerid}/{deviceid}")
	public String createDevice(@PathVariable int customerid, @PathVariable int deviceid, Model model) {
		Device device = new Device();
		device = deviceService.get(deviceid);
		device.setModified(new Date(new java.util.Date().getTime()));
		device.setCustomerid(customerid);
		device.setPushid(Integer.toString(customerid));
		device.setStatus(Status.OFF);
		this.deviceService.update(device);
		model.addAttribute("result", "true");
		return "mobile/result";
	}
	
	@RequestMapping("/devices/list/{customerid}")
	public View deviceList(@PathVariable int customerid, Model model) {
		model.addAttribute("xmlData", this.deviceService.getAllForMobile(customerid));
		return xmlView;
	}
	
	@RequestMapping("/devices/update/{deviceid}/{status}")
	public String updateDeviceStatus(@PathVariable int deviceid, @PathVariable boolean status, Model model) {
		Date now = new Date(new java.util.Date().getTime());
		Device device = new Device();
		device = deviceService.get(deviceid);
		device.setModified(now);
		device.setStatus(status?Status.ON:Status.OFF);
		this.deviceService.update(device);
		
		Log log = new Log();
		log.setCustomerid(device.getCustomerid());
		log.setDeviceid(deviceid);
		log.setStatus(device.getStatus());
		log.setRef("Button");
		logService.add(log);
		
		model.addAttribute("result", "true");
		return "mobile/result";
	}
	
	@RequestMapping("/devices/status/{deviceid}")
	public String getDeviceStatus(@PathVariable int deviceid, Model model) {
		model.addAttribute("result", deviceService.get(deviceid).getStatus());
		return "mobile/result";
	}
	
	@RequestMapping("/devices/switch/{deviceid}")
	public String switchDeviceStatus(@PathVariable int deviceid, Model model) throws Exception {
		Date now = new Date(new java.util.Date().getTime());
		Device device = this.deviceService.get(deviceid);
		String topic = "wand/switch_" + deviceid;
		String content = device.getStatus().getValue() ? "0" : "1";
		int qos = 0;
		String broker = "tcp://iot.eclipse.org:1883";
		String clientId = "wand/admin";
		MemoryPersistence persistence = new MemoryPersistence();

		MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		sampleClient.connect(connOpts);
		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(qos);
		sampleClient.publish(topic, message);
		sampleClient.disconnect();
		
		device.setModified(now);
		device.setStatus(device.getStatus().getValue()? Status.OFF:Status.ON);
		this.deviceService.update(device);
		
		Log log = new Log();
		log.setCustomerid(device.getCustomerid());
		log.setDeviceid(deviceid);
		log.setStatus(device.getStatus());
		log.setRef("Mobile");
		logService.add(log);
		
		model.addAttribute("result", deviceService.get(deviceid).getStatus());
		return "mobile/result";
	}
}
