package clientServer.connectorThreads;

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.EOFException;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.PriorityQueue;

import com.esotericsoftware.kryonet.Connection;

import requests.RR;
import requests.Request;
import java.util.LinkedList;
import java.util.ArrayList;

public class MyConnectionThread extends Thread {
	
	
	
	//public ObjectInputStream ois; 
	//public ObjectOutputStream oos; 
	//Socket socket;
	public Connection con;
	public Request req;
	int id;
	LinkedList<Request> queue;

	
	
	ArrayList<String> listConnections = new ArrayList<String>();
	
	
	

	public MyConnectionThread(Connection c, int id, Request request, LinkedList<Request> queue) {
		this.con = c; 
		this.id = id;
		this.req = request; 
		//this.oos = oos;
		this.queue = queue;
	}

	
	
	@Override
	public void run() { 
		// socket object to receive incoming client requests  
		// add request to the queue
		this.addConnection(Integer.toString(this.id));
		while (true) {
			queue.add(req);
			
		} 
		
	} 
	
	
	



	public ArrayList<String> getListConnections(){
		return this.listConnections;
	}
	
	
	public void addConnection(String conn){
		getListConnections().add(conn);
	}
	
	
	
	
}
