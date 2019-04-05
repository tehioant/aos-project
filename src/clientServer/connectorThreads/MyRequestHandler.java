package clientServer.connectorThreads;

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
import java.util.*;

import com.esotericsoftware.kryonet.Connection;

import requests.*;
import clientServer.Response;
import solver.*;



public class MyRequestHandler extends Thread {
	
	
	
	//ObjectInputStream ois; 
	//ObjectOutputStream oos;  
	//final Socket s; 
	Connection con;
	Request request;
	LinkedList<Request> queue;
	LinkedList<Request> queueToFactory;

	// Constructor 
	public MyRequestHandler(Connection c, Request request, LinkedList<Request> queue) { 
		this.con = c; 
		this.request = request; 
		//this.oos = oos;
		this.queue = queue;
	} 

	@Override
	public void run() { 
 
		System.out.println("RequestHandler in action : waiting for message ..."); 
		queueToFactory = new LinkedList<Request>();
		while (true) { 
			try { 
				Calendar cal = Calendar.getInstance();
		        long startTime = cal.getTimeInMillis();
		        long currentTime = startTime;
		        
		        while(queueToFactory.size() < 100 && currentTime < startTime + 1000 ){
		        	this.queueToFactory.add(this.queue.poll());
		        	currentTime = cal.getTimeInMillis();
		        }
		        
				System.out.println("All requests received"); 
				System.out.println("Processing ..."); 
				
				ArrayList<ProcessSolver> response = Scheduler.getResponse(queueToFactory);
				//ArrayList response = Scheduler.getResponse(queueToFactory);
				System.out.println("End process : Sending ... ");
				
				//System.out.println("Message sent :/  " + response.toString());
            	//oos.writeObject(response);
            	con.sendTCP(response);
				System.out.println("Response scheduler sent... "); 
				
            	break;
            	
			} catch (NullPointerException e) { 
				e.printStackTrace(); 
				continue;
			} 
		} 
	} 
	

	
} 




