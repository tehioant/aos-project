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
			try { 
				System.out.println("All requests received: Processing ..." + queue);
				ArrayList<ProcessSolver> response = Scheduler.getResponse(queue);
				System.out.println("End process : Sending ... ");
				
				for(ProcessSolver process : response){
					con.sendTCP(process);
				}
				System.out.println("Response scheduler sent... "); 
				
            	break;
            	
			} catch (NullPointerException e) { 
				e.printStackTrace(); 
				System.out.println("Null pointer MyRequestHandler");
				continue;
			} 
		} 
	} 
	

	
} 




