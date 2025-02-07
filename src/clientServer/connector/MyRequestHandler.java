package clientServer.connector;

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
import java.util.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.util.InputStreamSender;
import com.esotericsoftware.kryonet.util.TcpIdleSender;

import requests.*;
import solver.*;



public class MyRequestHandler extends Thread {
	
	
	
	//ObjectInputStream ois; 
	//ObjectOutputStream oos;  
	//final Socket s; 
	Connection con;
	Request request;
	LinkedList<Request> queue;
	LinkedList<Request> queueToFactory;
	ArrayList<ProcessSolver> listSend;
	

	// Constructor 
	public MyRequestHandler(Connection c, Request request, LinkedList<Request> queue) { 
		this.con = c; 
		this.request = request; 
		//this.oos = oos;
		this.queue = queue;
	} 

	@Override
	public void run() { 
 
		while (true) { 
			synchronized (con) {
				try { 
					//System.out.println("---------------------------------------------  " + Thread.currentThread());
					//System.out.println("All requests received: Processing ...");
					//System.out.println(queue.size());
					ArrayList<ProcessSolver> response = Scheduler.getResponse(queue);
					//System.out.println("End process : Sending ... " + response.size() + "   " + Thread.currentThread());
					InputStreamSender streamSend = null;
					for(ProcessSolver process : response){
						con.sendTCP(process);
						//try { Thread.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
					}
					//System.out.println("Response scheduler sent... " + Thread.currentThread()); 
					//System.out.println("---------------------------------------------");
	            	break;
	
				} catch (NullPointerException e) { 
					e.printStackTrace(); 
					continue;
				} 
			}
		} 
	} 
	

	
} 




