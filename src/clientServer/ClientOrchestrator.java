package clientServer;



import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Pragma;
import policies.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.*;  



public class ClientOrchestrator {
	
	
	static Random rand = new Random();
	
	 public static void main(String[] args) {
		 
		  
	       final String serverHost = "localhost";
	 
	       Socket socketOfClient = null;
	 
	       try {
	           // Send a request to connect to the server is listening
	           // on machine 'localhost' port 7777.            
	           socketOfClient = new Socket(serverHost, 7777);
	           System.out.println("socket connexion");
	           
	           // Open input and output streams
               //BufferedReader is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
               //BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
 
	           
	           // Stream object 
	           //DataInputStream dis = new DataInputStream(socketOfClient.getInputStream());
	           DataOutputStream dos = new DataOutputStream(socketOfClient.getOutputStream());
	           //ObjectInputStream ois = new ObjectInputStream(dis);
	           ObjectOutputStream oos = new ObjectOutputStream(dos);
		       
				ArrayList<Pragma> list = new ArrayList<Pragma>();
				for(int p= 0; p < 20; p++) {
					Pragma pragma = new Pragma(rand.nextInt(5));
					list.add(pragma);
				}
				
				// if we use vectors
				ArrayList<Vector> listVect = new ArrayList<Vector>(); 
				for(int v= 0; v < 20; v++) {
					Vector vector = new Vector(); 
					vector.add(v); // id
					vector.add(10); // start (int)
					vector.add(10); // duration (int)
					vector.add(10); // space (int) 
					vector.add(rand.nextInt(5)); // priority (int)
					listVect.add(vector);
				}
				System.out.println("Pragmas (vectors) : " + listVect);
			
				oos.writeObject(listVect);
			
	           
			
	    	   System.out.println("Closing socket and terminating program.");
	           oos.close();
	           socketOfClient.close();
	         
	       } catch (UnknownHostException e) {
	           System.err.println("Don't know about host " + serverHost);
	           return;
	       } catch (IOException e) {
	           System.err.println("Couldn't get I/O for the connection to " + serverHost);
	           return;
	       }
	       
	   }
		
		
		
	
	
	
}  
