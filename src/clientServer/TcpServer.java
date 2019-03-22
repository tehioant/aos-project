package clientServer;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import clientServer.threads.*;
import factory.Factory;
import policies.ApplicationPriority;
import policies.Policy;
import requests.Request;

import java.io.*;  




public class TcpServer {
	
	//public String ipAdress = "127.0.0.1";
	public int port = 5096;
	
	public ServerSocket ss;
	public LinkedList<Request> queue;
	public Socket s;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	// private static int MAX_THREAD_POOL = 3;
    
	public static void main(String[] args) throws IOException {
		new TcpServer();
	}
	
	
	public TcpServer(){
		// server is listening on port 5056 
		try {
			ss = new ServerSocket(port);
			System.out.println("Server setup :"); 
			System.out.println("Waiting to accept user...");
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		// Initializing pool
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		// running infinite loop for getting 
		// client request 
		queue = new LinkedList<Request>() ;
		int id=0; // Only for libhandler ID
		while (true) { 
			s = null; 
			
			try	{
				
				// socket object to receive incoming client requests 
				s = ss.accept();
				
				
				// obtaining input and out streams 
				ois = new ObjectInputStream(s.getInputStream()); 
				oos = new ObjectOutputStream(s.getOutputStream()); 
				

				// create a new thread object 
				Thread connThread = new ConnectionThread(s,id, ois, oos, queue); 
				System.out.println("Assigning new thread for this client"); 
				// Invoking the start() method 
				connThread.start(); 
				id++;
				
				
				
				Thread.sleep(100);
				pool.execute(new LibHandler(s, ois, oos, queue)); 

				
			} 
			catch (Exception e){
				e.printStackTrace(); 
				break;
			} 
		}
		
	}
	
	
	public void emptyQueue(){
		this.queue.clear();;
	}
	
	
	
	
	
	
	
	
	
	
} 

















