package clientServer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import clientServer.connectorThreads.MyConnectionThread;
import clientServer.connectorThreads.MyRequestHandler;
import clientServer.threads.ConnectionThread;
import clientServer.threads.RequestHandler;
import requests.RR;
import requests.Request;
import solver.ProcessSolver;

public class Connector extends Listener {
	
	
	static Server server;
	static int tcpPort = 27960;
	
	int id = 0;
	
	public LinkedList<Request> queue;
	
	ExecutorService pool = Executors.newFixedThreadPool(3);
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Creating the server");
		server = new Server(16384, 2048);
		
		server.getKryo().register(RR.class);
		server.getKryo().register(Request.class);
		server.getKryo().register(Response.class);
		server.getKryo().register(ProcessSolver.class);
		server.getKryo().register(ArrayList.class);
		
		
		server.bind(tcpPort);
		
		server.start();
		System.out.println("Server started");
		
		server.addListener(new Connector());
		
	}
	
	public void connected(Connection c) {
		System.out.println("Connection received from "+c.getRemoteAddressTCP().getHostString());
		
	}
	
	public void received(Connection c, Object o) {
		System.out.println("Received request ");
		if(o instanceof Request) {
			Request request = (Request) o;
			
			queue = new LinkedList<Request>() ;
			
			Thread connThread = new MyConnectionThread(c,id, request, queue); 
			System.out.println("Assigning new thread for this client"); 
			// Invoking the start() method 
			connThread.start();
			id++;
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pool.execute(new MyRequestHandler(c, request, queue)); 

			
			//System.out.println("Sending Response");
			//Response response = new Response();
			//response.message = "Perform IO at 0";
			//c.sendTCP(response);
		}
	}
	
	public void disconnected(Connection c) {
		System.out.println("Disconnected");
	}
}
