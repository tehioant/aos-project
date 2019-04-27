package clientServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import clientServer.lib.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;

import requests.RR;
import requests.Request;
import solver.ProcessSolver;
import utilitaire.Chrono;

public class Lib extends Listener {

	static Client client;
	static String ip = "localhost";
	static int tcpPort = 33278;
	static int timeout = 500000000; //5000 msec wait time before failing
	static int totalRequest = 100000;
	static int tot;
	static Chrono chrono = new Chrono();
	
	static boolean responseReceived = false;
	
	public static void main(String args[]) throws Exception{
		client = new Client(160384, 160384);
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
		
		while(!responseReceived) {
			Thread.sleep(100);
		}
		
		
		System.out.println("Lib is waiting for response");
		
		
		
		//System.out.println("Disconnecting");
		//System.exit(0);
	}
	
	public void connected(Connection c) {
		
		System.out.println("Connected to Server");
		
		
		ArrayList<Application> listApps = new ArrayList<Application>();
		
		/**
		for(int num=1; num <= 20; num++){
			listApps.add(new Application(num, 1000));
		}
		
		ArrayList<Request> list = new ArrayList<Request>();
		for(Application app : listApps){
			list.addAll(app.getList());
		}
		Collections.shuffle(list);
		
		**/
		Application application = new Application(1, totalRequest);
		
		// sending requests
		for(Request request : application.getList()){
			c.sendTCP(request);
			//try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
		}
		chrono.start();
		System.out.println("All request sent for app : => " + application.getList().size());
	
	}
	
	public void received(Connection c, Object r) {
			//System.out.println("\n--------------------");
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
		
				if(tot == totalRequest){
					chrono.stop();
					System.out.println("Total requests " + tot + " received in : " + chrono.getDureeMs());
				}
				
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
	}
	
	
	
	
	
	
	public void disconnected(Connection c) {
		System.out.println("Disconnected.");
		client.close();
		/**
		try {
			client.connect(timeout,ip,tcpPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		**/
	}

	
	
	
	
}


