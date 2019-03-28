

import java.io.IOException;
import java.util.ArrayList;

import dispatcher.model.ProcessBuffer;
import requests.Request;


public class test {
	
	public static int nombre;
	
	public static void main(String[] args) throws IOException {
		nombre = 5;
		ArrayList<Integer> process = new ArrayList<Integer>();
		process.add(1);
		process.add(1);
		process.add(nombre);
		System.out.println(process.size());
		System.out.println(process.get(process.size()-1));
	}
	
	
	
	
	public static boolean process(int n){
		if(n < 4){
			nombre = n;
			return true;
		} else {
			return false;
		}
		
	}

}
