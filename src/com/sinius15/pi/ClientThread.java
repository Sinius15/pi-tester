package com.sinius15.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	
	public ClientThread(Socket socket) {
		this.clientSocket = socket;
	}
	
	@Override
	public void run() {
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String inputLine;
			
			while((inputLine = in.readLine()) != null){
				out.println(Protocol.handle(inputLine));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				clientSocket.close();
				out.close();
				in.close();
			} catch (IOException e) {}
		}
		
		
	}
}
