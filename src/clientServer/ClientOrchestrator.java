package clientServer;



import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Vector;
import java.util.Scanner; 

import policies.*;
import requests.*;
import java.io.*;  



public class ClientOrchestrator {
	
	    
	public static void main(String[] args) throws IOException { 
        try { 
            Scanner scn = new Scanner(System.in); 
              
            // getting localhost ip 
            InetAddress ip = InetAddress.getByName("localhost"); 
      
            // establish the connection with server port 5056 
            Socket s = new Socket(ip, 5056); 
      
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
            
            
      
        	
            // the following loop performs the exchange of 
            // information between client and client handler 
            while (true) { 
            	
            	
            	// we create a request to modelise an app request
            	int requestType = 0;
            	String payload = "payload of RR request";
            	RR request = new RR(requestType,  payload);
            	System.out.println("Sending a request ::  " + request.toString()); 
            	
            	ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            	oos.writeObject(request);
            	
            	ObjectInputStream  ois = new ObjectInputStream(s.getInputStream()); 
            	PriorityQueue<Request> answer = (PriorityQueue<Request>) ois.readObject();
            	System.out.println("----------------------");
            	System.out.println("The answer is ::  " + answer.toString()); 
            	break;
            	/*
                System.out.println(dis.readUTF()); 
                String tosend = scn.nextLine(); 
                dos.writeUTF(tosend); 
                  
                // If client sends exit,close this connection  
                // and then break from the while loop 
                if(tosend.equals("Exit")) { 
                    System.out.println("Closing this connection : " + s); 
                    s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                // printing date or time as requested by client 
                String received = dis.readUTF(); 
                System.out.println(received); */
            } 
              
            // closing resources 
            scn.close(); 
            dis.close(); 
            dos.close(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 
} 
