package clientServer.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;

import requests.RR;
import requests.Request;

import java.util.ArrayList;

public class ConnectionThread extends Thread {
	
	
	
	public ObjectInputStream ois; 
	public ObjectOutputStream oos; 
	Socket socket; 
	int id;
	LinkedList<Request> queue;

	
	
	ArrayList<String> listConnections = new ArrayList<String>();
	
	
	

	public ConnectionThread(Socket s, int id, ObjectInputStream ois, ObjectOutputStream oos, LinkedList<Request> queue2) {
		this.socket = s; 
		this.id = id;
		this.ois = ois; 
		this.oos = oos;
		this.queue = queue2;
	}

	
	
	@Override
	public void run() { 
		// socket object to receive incoming client requests  
		System.out.println("A new client is connected : " + socket); 
		this.addConnection(Integer.toString(this.id));
		System.out.println("I am handling the connection..."); 
		while (true) { 
			Request request;
			try {
			
				request = (Request) ois.readObject();
				queue.add(request);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EOFException exception){
				System.out.println("This is a EOFException"); 
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
			
				
			
		} 
		
	} 
	
	
	



	public ArrayList<String> getListConnections(){
		return this.listConnections;
	}
	
	
	public void addConnection(String conn){
		getListConnections().add(conn);
	}
	
	
	
	
}
