package clientServer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import requests.RR;
import requests.Request;

public class Connector extends Listener {
	
	
	static Server server;
	static int tcpPort = 27960;
	
	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Creating the server");
		server = new Server();
		
		server.getKryo().register(RR.class);
		server.getKryo().register(Request.class);
		
		server.bind(tcpPort);
		
		server.start();
		System.out.println("Server started");
		
		server.addListener(new Connector());
		
	}
	
	public void connected(Connection c) {
		System.out.println("Connection received from "+c.getRemoteAddressTCP().getHostString());
		Response response = new Response();
		RR request = new RR(0,  100, false, 100*10, 2);
		int location = 0;
		response.message = "Perform I/O at "+location;
		
		c.sendTCP(request);
	}
	
	public void received(Connection c, Request req) {
		//System.out.println("Received request " +req.toString());
	}
	
	public void disconnected(Connection c) {
		System.out.println("Disconnected");
	}
}
