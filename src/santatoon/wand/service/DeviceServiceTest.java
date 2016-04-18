package santatoon.wand.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.integration.support.MessageBuilder;

public class DeviceServiceTest{
	static ApplicationContext context;
	static MessageChannel toUserChannel;
	
	public static void main(String[] args) throws InterruptedException
	{
		context = new ClassPathXmlApplicationContext("file:WebContent/WEB-INF/xmppContext.xml");
		toUserChannel = context.getBean("toUserChannel", MessageChannel.class);
		
		pushToDevices();
		System.out.println("5");
		pushToDevices();
		pushToDevices();
		pushToDevices();
		pushToDevices();
		send();
	}
	
	public static void pushToDevices() {
		
		Message<String> message = new GenericMessage<String>("Hello from Spring Integration XMPP");
		toUserChannel.send(message);
	}
	public static void send()
	{
		Message<String> xmppOutboundMsg = MessageBuilder.withPayload("Hello, XMPP!" )
				.setHeader("test", "test")
				.build();
	}
}
