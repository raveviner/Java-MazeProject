package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RunClient {

	public static void main(String[] args) throws Exception{
		System.out.println("Client Side");
		Socket theServer=new Socket("localhost",5400);
		System.out.println("connected to server!");
		
		PrintWriter outToServer=new PrintWriter(theServer.getOutputStream());
		outToServer.println("generate 3d maze rave 15 15 3");
		outToServer.flush();
		
		BufferedReader in=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
		System.out.println(in.readLine()); 
		
		
		
		outToServer.println("exit");
		outToServer.flush();
		outToServer.close();
		theServer.close();
	}
}
