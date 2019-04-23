package clientServer.lib;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;

import solver.ProcessSolver;

public class ReceiverThread extends Thread {
	
	
	
	
	
	public boolean responseReceived;
	Connection con;
	Object r;
	
	
	
	public ReceiverThread(Connection c, Object r, boolean responseReceived){
		this.con = c;
		this.r = r;
		this.responseReceived = responseReceived;
	}
	
	
	
	
	
	public void run(){
		if(r instanceof ArrayList<?>) {
			System.out.println("--------------------");
			ArrayList<ProcessSolver> response = (ArrayList<ProcessSolver>) r;
			//ProcessSolver response = (ProcessSolver) r;
			// for(ProcessSolver proc : response){ System.out.println("Request Lib ID  : "+ proc.getRequest().getAppId()); }
			System.out.println(response.size() + " : Request buffer ID  : "+ response.get(0).getBufferId() + " Lib ID : " + response.get(0).getRequest().getAppId());
		} else {
			System.out.println("Lib receiver type incorrect" + r.getClass());
			responseReceived = true;
		}
	}
	
	
	
	
	
	
	

}














