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
	PriorityQueue<Request> queue;

	// Constructor 
	public LibHandler(Socket s, ObjectInputStream ois, ObjectOutputStream oos) { 
		this.s = s; 
		this.ois = ois; 
		this.oos = oos; 
	} 

	@Override
	public void run() { 
 
		System.out.println("LibHandler in action : waiting for message ..."); 
		queue = new PriorityQueue<Request>(); 
		while (true) { 
			try { 
				
				Request request = (Request) ois.readObject();
				queue.add(request);
				System.out.println("All requests received"); 
				System.out.println("Processing ..."); 
				
				PriorityQueue<Request> response = Factory.getResponse(queue);
				System.out.println("End process : Sending ... "); 
            	oos.writeObject(response);
            	System.out.println("Response scheduler sent... "); 
				
				break;
			} catch (IOException e) { 
				e.printStackTrace(); 
			} catch (ClassNotFoundException e) {
	        	   System.out.println(e);
	               e.printStackTrace();
	        }
		} 
		
		try { 
			// closing resources 
			this.ois.close(); 
			this.oos.close();
			
		}catch(IOException e){ 
			e.printStackTrace(); 
		} 
	} 
	

	
} 




