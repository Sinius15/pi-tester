package com.sinius15.pi.server;

import java.io.IOException;
import java.net.ServerSocket;

public class LightServer implements Runnable{
	
	public static final int port = 3443;
	ServerSocket serverSocket;
	
	public LightServer() throws Exception{
		serverSocket = new ServerSocket(port);
		new Thread(this).start();
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
