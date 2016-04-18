package santatoon.wand.service;

public class XmppTest {
    
    public static void main(String[] args) throws Exception {
        
        String username = "624915004859@gcm.googleapis.com";
        String password = "AIzaSyBBDonRXZK-61Pg4ZiORLZwXSU7RWMjcxE";
        
        XmppManager xmppManager = new XmppManager("gcm.googleapis.com", 5235);
        
        xmppManager.init();
        xmppManager.performLogin(username, password);
        xmppManager.setStatus(true, "Hello everyone");
        
        String buddyJID = "624915004859@gcm.googleapis.com/CB4A3FCB";
        String buddyName = "624915004859@gcm.googleapis.com/CB4A3FCB";
        xmppManager.createEntry(buddyJID, buddyName);
        
        xmppManager.sendMessage("Hello mate", buddyJID);
        
        boolean isRunning = true;
        
        while (isRunning) {
            Thread.sleep(50);
        }
        
        xmppManager.destroy();
        
    }

}