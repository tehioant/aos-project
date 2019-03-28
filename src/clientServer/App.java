package clientServer;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;

import requests.RR;
import requests.Request;

public class App extends Listener {

	static Client client;
	static String ip = "localhost";
	static int tcpPort = 27960;
	static int timeout = 5000; //5000 msec wait time before failing
	
	static boolean responseReceived = false;
	
	public static void main(String args[]) throws Exception{
		client = new Client();
		
		client.getKryo().register(RR.class);
		client.getKryo().register(Request.class);
		
		client.start();
		
		client.connect(timeout,ip,tcpPort);
		
		client.addListener(new App());
		
		System.out.println("App is waiting for response");
		
		while(!responseReceived) {
			Thread.sleep(1000);
		}
		System.out.println("Disconnecting");
		System.exit(0);
	}
	
	//public void connected(Connection c) {
	//	Response request = new Response();
	//	request.message = "New request for I/O";
		
	//	c.sendTCP(request);
		
	//}
	
	public void received(Connection c, Object r) {
		if(r instanceof Request) {
			Request response = (Request) r;

		System.out.println("Received response: "+response.getAppId());
		responseReceived = true;
		}
	}
}
