package clientServer.lib;

import java.util.ArrayList;
import java.util.Random;

import requests.RR;
import requests.Request;

public class Application {
	
	
	ArrayList<Request> list;
	int totalRequest;
	int id;
	
	public Application(int id, int totalRequest){
		this.id = id;
		this.totalRequest = totalRequest;
		list = this.createRequest();
	}
	
	
	
	public ArrayList<Request> createRequest(){
		
		ArrayList<Request> requests = new ArrayList<Request>();
		Random random = new Random();
		int payload;
		int appId;
		for(int i =0; i < totalRequest; i++) {
        	payload = random.nextInt(1000) + 10;
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
