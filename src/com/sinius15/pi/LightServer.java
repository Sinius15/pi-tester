package com.sinius15.pi;

import java.io.IOException;
import java.net.ServerSocket;

public class LightServer implements Runnable{
	
	public static final int port = 3443;
	ServerSocket serverSocket;
	
	public LightServer(){
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				serverSocket.close();
			} catch (IOException e1) {}
		}
	}

	@Override
	public void run() {
		while(!serverSocket.isClosed()){
			try {
				new ClientThread(serverSocket.accept()).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			serverSocket.close();
		} catch (IOException e) {}
		
		
	}
	
}
