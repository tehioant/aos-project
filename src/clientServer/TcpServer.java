package clientServer;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import clientServer.threads.*;
import factory.Factory;
import policies.ApplicationPriority;
import policies.Policy;

import java.io.*;  




public class TcpServer {

    
	public static void main(String[] args) throws IOException { 
		// server is listening on port 5056 
		ServerSocket ss = new ServerSocket(5056); 
		System.out.println("Server setup :"); 
		System.out.println("Waiting to accept user...");
		
		// running infinite loop for getting 
		// client request 
		int id=0; // Only for libhandler ID
		while (true) { 
			Socket s = null; 
			
			try	{ 
				
				// socket object to receive incoming client requests 
				s = ss.accept(); 

				// create a new thread object 
				Thread connThread = new ConnectionThread(s,id); 
				System.out.println("Assigning new thread for this client"); 
				// Invoking the start() method 
				connThread.start(); 
				id++;
				

				// obtaining input and out streams 
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); 
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream()); 
				
				Thread libHandler = new LibHandler(s, ois, oos);
				libHandler.start();
				
				
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		}
		
	} 
} 

















