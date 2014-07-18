package com.sinius15.pi.services.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.sinius15.pi.Logger;

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
			Logger.log("Client " + clientSocket.getInetAddress() + " connected.");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String inputLine;
			
			while((inputLine = in.readLine()) != null){
				Logger.log("Client " + clientSocket.getInetAddress() + " issued command " + inputLine);
				out.println(Protocol.handle(inputLine));
			}
		} catch (IOException e) {
			e.printStackTrace();
			Logger.log("Client " + clientSocket.getInetAddress() + " closed.");
		}finally{
			try {
				clientSocket.close();
				out.close();
				in.close();
			} catch (IOException e) {}
		}
		
		
	}
}
