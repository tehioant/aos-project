package clientServer;

import java.net.*;
import java.util.ArrayList;
import java.util.Vector;

import model.Pragma;
import policies.ApplicationPriority;
import policies.Policy;

import java.io.*;  




public class Orchestrator {
	
	
	public static int policyMode = 1;
	static Policy policy;
		
		
	public static void main(String args[]) throws IOException {
		 
	       ServerSocket listener = null;
	 
	       System.out.println("Server is waiting to accept user...");
	       int clientNumber = 0;
	 

	       try {
	           listener = new ServerSocket(7777);
	       } catch (IOException e) {
	           System.out.println(e);
	           System.exit(1);
	       }
	 
	       try {
	           while (true) {
	               Socket socketOfServer = listener.accept();
	               new ServiceThread(socketOfServer, clientNumber++).start();
	           }
	       } finally {
	           listener.close();
	       }
	 
	   }
	 
	   private static void log(String message) {
	       System.out.println(message);
	   }
	 
	   private static class ServiceThread extends Thread {
	 
	       private int clientNumber;
	       private Socket socketOfServer;
	       private ObjectInputStream ois;
	       private ObjectOutputStream oos;
	 
	       public ServiceThread(Socket socketOfServer, int clientNumber) {
	           this.clientNumber = clientNumber;
	           this.socketOfServer = socketOfServer;
	 
	           // Log
	           log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
	       }
	 
	       @Override
	       public void run() {
	 
	           try {
	        	   BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
	               BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
	               
	        	   oos = new ObjectOutputStream(new DataOutputStream(socketOfServer.getOutputStream()));
	               ois = new ObjectInputStream(new DataInputStream(socketOfServer.getInputStream()));
	               	


	            	   
	               
	               ArrayList<Vector> vect = (ArrayList<Vector>) ois.readObject();
	               System.out.println("Pragmas (vectors) server has received : " + vect);
	               
	               setPolicy(policyMode, vect);
	   				ArrayList<Vector> output = getOutput(vect);
	   				// Print results
	   				System.out.println("Policy in place : " + getPolicy().getPolicyName());
	   				for(int i=0; i < output.size(); i++) {
	   					System.out.println(i + " is : " + output.get(i).get(4));
	   				}


	               ois.close();
	               socketOfServer.close();
	 
	           } catch (IOException e) {
	               System.out.println(e);
	               e.printStackTrace();
	           } catch (ClassNotFoundException e) {
	        	   System.out.println(e);
	               e.printStackTrace();
	           }
	       }
	   }

			
			
	
	
	
	// Methodes
	
	public static void setPolicy(int policymode, ArrayList<Vector> list) {
		switch(policymode) {
			case 1 : policy = new ApplicationPriority();
			// Then we add other cases (policies)
		}
	}
	
	public static Policy getPolicy() {
		return policy;
		
	}
	
	
	public static ArrayList<Vector> getOutput(ArrayList<Vector> list){
		if(getPolicy() == null) {
			setPolicy(policyMode, list);
		}
		return getPolicy().sortVectorPragma(list);
	}
	
	/* For the use of class Pragma instead of vectors
	public static ArrayList<Pragma> getOutput(ArrayList<Pragma> list){
		if(getPolicy() == null) {
			setPolicy(policyMode, list);
		}
		return getPolicy().sortPragmas(list);
	}*/

}  




















