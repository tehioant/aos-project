package clientServer.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

import requests.RR;
import requests.Request;
import factory.*;

public class LibHandler extends Thread {
	
	
	
	ObjectInputStream ois; 
	ObjectOutputStream oos;  
	final Socket s; 
	LinkedList<Request> queue;
	LinkedList<Request> queueToFactory;

	// Constructor 
	public LibHandler(Socket s, ObjectInputStream ois, ObjectOutputStream oos, LinkedList<Request> queue) { 
		this.s = s; 
		this.ois = ois; 
		this.oos = oos;
		this.queue = queue;
	} 

	@Override
	public void run() { 
 
		System.out.println("LibHandler in action : waiting for message ..."); 
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
				
				LinkedList<Request> response = Factory.getResponse(queueToFactory);
				System.out.println("End process : Sending ... ");
				System.out.println("Message sent :/  " + response.toString());
            	oos.writeObject(response);
            	System.out.println("Response scheduler sent... "); 
				
            	break;
            	
			} catch (IOException  | NullPointerException e) { 
				e.printStackTrace(); 
				continue;
			} 
		} 
	} 
	

	
} 




