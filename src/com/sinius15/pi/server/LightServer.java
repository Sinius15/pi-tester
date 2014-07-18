package com.sinius15.pi.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.sinius15.pi.PiServer;

public class LightServer implements Runnable{
	
	public static final int port = 3443;
	ServerSocket serverSocket;
	
	public LightServer() throws Exception{
		System.out.println("a");
		serverSocket = new ServerSocket(port);
		System.out.println("b");
		new Thread(this).start();
		System.out.println("c");
	}

	@Override
	public void run() {
		System.out.println("d");
		while(!serverSocket.isClosed()){
			System.out.println("e");
			try {
				System.out.println("f"); 
				new ClientThread(serverSocket.accept()).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("g");
		try {
			serverSocket.close();
		} catch (IOException e) {}
		System.out.println("h");
		PiServer.server = null;
		
	}
	
}
