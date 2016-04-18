package santatoon.wand.service;

public class SSHAgent {

	public static void main(String[] args) {
		System.out.println("sendCommand");

		String command = "sudo python ./Desktop/test/switch_servo 0";
		String userName = "pi";
		String password = "raspberry";
		String connectionIP = "192.168.0.13";
		SSHManager instance = new SSHManager(userName, password, connectionIP);

		instance.connect();
		instance.sendCommand(command);
		instance.close();
	}
}
