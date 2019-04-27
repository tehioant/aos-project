package clientServer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import clientServer.connector.MyRequestHandler;
import requests.RR;
import requests.Request;
import solver.ProcessSolver;

public class Connector extends Listener {
	
	
	static Server server;
	static int tcpPort = 33278;
	
	int id = 0;
	static long startTime;
	static long currentTime;
	static Calendar cal;
	LinkedList<Request> queueToFactory;
	
	
	public static LinkedList<Request> queue;
	ExecutorService pool = Executors.newFixedThreadPool(4);
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		queue = new LinkedList<Request>() ;
		cal =  Calendar.getInstance();
		
		System.out.println("Creating the server");
		server = new Server(160384, 160384);
		
		server.getKryo().register(RR.class);
		server.getKryo().register(Request.class);
		server.getKryo().register(ArrayList.class);
		server.getKryo().register(ProcessSolver.class);
		Log.ERROR();
		Log.WARN();
		Log.INFO();
		Log.DEBUG();
		Log.TRACE();
		
		server.bind(tcpPort);
		
		server.start();
		System.out.println("Server started");
		
		
		
		server.addListener(new Connector());
		
		while(true) {
			Thread.sleep(100);
		}
		
		//new Thread(new MyConnectionThread(server, queue)).start();;
		//new Thread(new MyConnectionThread(server, queue)).start();;
		//new Thread(new MyConnectionThread(server, queue)).start();;

		
	}
	
	
	
	
	

	public void connected(Connection c) {
		System.out.println("___________Connection received from " + c.getRemoteAddressTCP().getHostString() + "____________________");

		System.out.println("____________List of connections : " + server.getConnections().length + "____________________");
		startTime = cal.getTimeInMillis();
	}
	
	
	public void received(Connection c, Object o) {
		currentTime = cal.getTimeInMillis();
		try{
			if(o instanceof Request) {
				
				Request request = (Request) o;
				queue.add(request);
				if(queue.size() >= 100 || currentTime > startTime + 1000){
					queueToFactory = new LinkedList<Request>();
					for(Request req : queue){
						queueToFactory.add(req);
					}
					queue.clear();
					//System.out.println(" \n__Starting handler in pool : " + queueToFactory.size());
					pool.execute(new MyRequestHandler(c, request, queueToFactory)); 
					
					startTime = cal.getTimeInMillis();
				}
			} else if(o instanceof FrameworkMessage){
				System.out.println("FrameworkMessage : " + o.toString());
			
			
			} else if(o instanceof String){
				System.out.println(o);
			
			
			} else {
				System.out.println("not request type : " + o.getClass());
				
			}
			id++;
		} catch (NullPointerException e){
			e.printStackTrace();
		}
	}
	
	public void disconnected(Connection c) {
		System.out.println("Disconnected");
	}
	
	
	
	
	
	
	
}










