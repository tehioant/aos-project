package clientServer;

import java.util.ArrayList;
import java.util.Random;

import requests.RR;
import requests.Request;

public class Application {
	
	
	ArrayList<Request> list;
	static int totalRequest = 200;
	int id;
	
	public Application(int id){
		list = this.createRequest();
		this.id = id;
	}
	
	
	
	public ArrayList<Request> createRequest(){
		
		ArrayList<Request> requests = new ArrayList<Request>();
		Random random = new Random();
		int payload;
		int appId;
		for(int i =0; i < totalRequest; i++) {
        	payload = random.nextInt(1000);
        	appId = random.nextInt(10);
        	requests.add(new RR(i,  payload, false, payload, this.id));
		}
		
		return requests;
	}
	
	
	public ArrayList<Request> getList(){
		return this.list;
	}
	
	
	public int getID(){
		return this.id;
	}
	
	
	
}
