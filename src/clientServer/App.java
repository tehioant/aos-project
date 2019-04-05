package clientServer;

import java.util.ArrayList;
import java.util.Random;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;

import requests.RR;
import requests.Request;
import solver.ProcessSolver;

public class App extends Listener {

	static Client client;
	static String ip = "localhost";
	static int tcpPort = 27960;
	static int timeout = 5000; //5000 msec wait time before failing
	static int totalRequest = 100;
	
	static boolean responseReceived = false;
	
	public static void main(String args[]) throws Exception{
		client = new Client(16384, 2048);
		
		client.getKryo().register(RR.class);
		client.getKryo().register(Request.class);
		client.getKryo().register(Response.class);
		client.getKryo().register(ProcessSolver.class);
		client.getKryo().register(ArrayList.class);
		

		client.addListener(new App());
		
		client.start();
		
		
		client.connect(timeout,ip,tcpPort);
		
		
		System.out.println("App is waiting for response");
		
		while(!responseReceived) {
			Thread.sleep(1000);
		}
		//System.out.println("Disconnecting");
		//System.exit(0);
	}
	
	public void connected(Connection c) {
		
		System.out.println("Connected to Server");
		
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
	
	public void received(Connection c, Object r) {
		System.out.println("--------------------");
		ArrayList<ProcessSolver> response = (ArrayList<ProcessSolver>) r;

		for(ProcessSolver proc : response){
			System.out.println("Request App ID  : "+ proc.getRequest().getAppId());
		}
		
		responseReceived = true;
	}
	
	
	
	
	
	
	
	
	
	
}











