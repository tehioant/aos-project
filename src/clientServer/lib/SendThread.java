package clientServer.lib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import clientServer.connector.MyRequestHandler;
import requests.RR;
import requests.Request;

public class SendThread extends Listener {

	
	

	
	public Connection con;
	public Server server;
	ArrayList<String> listConnections = new ArrayList<String>();
	
	static long startTime;
	static long currentTime;
	static Calendar cal;
	LinkedList<Request> queueToFactory;
	int id = 0;
	public static LinkedList<Request> queue;
	ExecutorService pool = Executors.newFixedThreadPool(10);
	
	
	
	
	public SendThread(LinkedList<Request> queue){
		this.queue = queue;
	}
	
	
	
	

	public void connected(Connection c) {
		System.out.println("Connection received from " + c.getRemoteAddressTCP().getHostString());
		
	}
	
	
	public void received(Connection c, Object o) {
		//currentTime = cal.getTimeInMillis();
		//System.out.println("Received request " + id);// + "  " + o.getClass());
		try{
			if(o instanceof Request) {
				
				Request request = (Request) o;
				queue.add(request);
				if(queue.size() >= 100 || currentTime > startTime + 1000){
					queueToFactory = new LinkedList<Request>();
					for(int index=0; index < 100; index++){
						queueToFactory.add(queue.poll());
					}
					System.out.println(" \n__Starting handler in pool : " + queueToFactory.size());
					pool.execute(new MyRequestHandler(c, request, queueToFactory)); 
					
					//startTime = cal.getTimeInMillis();
				}
			} else if(o instanceof FrameworkMessage){
				System.out.println("FrameworkMessage : " + o.toString());
			
			
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
	
	
	
	
	public ArrayList<String> getListConnections(){
		return this.listConnections;
	}
	
	
	
	
	
}



















