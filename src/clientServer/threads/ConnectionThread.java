package clientServer.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import requests.RR;

import java.util.ArrayList;

public class ConnectionThread extends Thread {
	
	
	
	public ObjectInputStream ois; 
	public ObjectOutputStream oos; 
	Socket socket; 
	int id;

	ArrayList<String> listConnections = new ArrayList<String>();
	
	
	

	public ConnectionThread(Socket s, int id) {
		this.socket = s; 
		this.id = id;
		
	}

	
	
	@Override
	public void run() { 
		// socket object to receive incoming client requests  
		System.out.println("A new client is connected : " + socket); 
		while (true) { 
			
			this.addConnection(Integer.toString(this.id));
			
			System.out.println("I am handling the connection..."); 
			
				
			
			break;
		} 
		
	} 
	
	
	



	public ArrayList<String> getListConnections(){
		return this.listConnections;
	}
	
	
	public void addConnection(String conn){
		getListConnections().add(conn);
	}
	
	
	
	
}
