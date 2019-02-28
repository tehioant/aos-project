package clientServer;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import clientServer.threads.LibHandler;
import model.Pragma;
import policies.ApplicationPriority;
import policies.Policy;

import java.io.*;  




public class Orchestrator {
	
	
	public static void main(String[] args) throws IOException { 
		// server is listening on port 5056 
		ServerSocket ss = new ServerSocket(5056); 
		
		// running infinite loop for getting 
		// client request 
		int id=0; // Only for libhandler ID
		while (true) { 
			Socket s = null; 
			
			try	{ 
				// socket object to receive incoming client requests 
				s = ss.accept(); 
				System.out.println("A new client is connected : " + s); 
				
				// obtaining input and out streams 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
				
				

				// create a new thread object 
				Thread t = new LibHandler(s, dis, dos, id); 
				System.out.println("Assigning new thread for this client"); 

				// Invoking the start() method 
				t.start(); 
				id++;
			} 
			catch (Exception e){ 
				s.close(); 
				e.printStackTrace(); 
			} 
		} 
	} 
} 

















