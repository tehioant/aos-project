package clientServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import clientServer.libThreads.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;

import requests.RR;
import requests.Request;
import solver.ProcessSolver;

public class Lib extends Listener {

	static Client client;
	static String ip = "localhost";
	static int tcpPort = 33278;
	static int timeout = 500000000; //5000 msec wait time before failing
	static int totalRequest = 200;
	static int tot;
	
	static boolean responseReceived = false;
	
	public static void main(String args[]) throws Exception{
		client = new Client(16384, 16384);
		tot = 0;
		
		client.getKryo().register(RR.class);
		client.getKryo().register(Request.class);
		client.getKryo().register(ArrayList.class);
		client.getKryo().register(ProcessSolver.class);
		
		Log.ERROR();
		Log.WARN();
		Log.INFO();
		Log.DEBUG();
		Log.TRACE();

		client.addListener(new Lib());
		client.start();
		
		
		client.connect(timeout,ip,tcpPort);
		
		
		System.out.println("Lib is waiting for response");
		
		while(!responseReceived) {
			Thread.sleep(100);
		}
		//System.out.println("Disconnecting");
		//System.exit(0);
	}
	
	public void connected(Connection c) {
		
		System.out.println("Connected to Server");
		
		Application app = new Application();
		ArrayList<Request> list = app.getList();
		for(Request request : list){

			c.sendTCP(request);
		}
		System.out.println("All request sent => " + list.size());
	}
	
	public void received(Connection c, Object r) {
			System.out.println("--------------------");
			try{
				
				if(r instanceof ProcessSolver){
					ProcessSolver response = (ProcessSolver) r;
					//ProcessSolver response = (ProcessSolver) r;
					// for(ProcessSolver proc : response){ System.out.println("Request Lib ID  : "+ proc.getRequest().getAppId()); }
					//System.out.println("Request App ID  : " + response.getRequest().getAppId());
					tot ++;
				} else if(r instanceof FrameworkMessage){
					System.out.println("FrameworkMessage : " + r.toString());
				}
		
				System.out.println("Total requests received : " + tot);
				
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
	}
	
	
	
	
	
	
	public void disconnected(Connection c) {
		System.out.println("Disconnected.");
		/**
		try {
			client.connect(timeout,ip,tcpPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		**/
	}
	
	
	
}











