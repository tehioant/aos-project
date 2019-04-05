package clientServer.appThreads;

import java.util.Random;

import requests.RR;

public class SendThread extends Thread {

	
	
	
	
	
	public SendThread(){
		
		
	}
	
	
	
	
	
	
	
	
	public void run(){
		
		int payload, appId;
		Random random = new Random();
		
		for(int i =0; i < totalRequest; i++) {
        	payload = random.nextInt(1000);
        	appId = random.nextInt(10);
            RR request = new RR(appId,  payload, false, payload*10, appId);
			System.out.println("Sending Request :: " + appId);
			//RR request = new RR(0,  100, false, 100*10, 2);
			c.sendTCP(request);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}



















