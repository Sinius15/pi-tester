package com.sinius15.pi.services;

import java.io.IOException;
import java.net.ServerSocket;

import com.sinius15.pi.Logger;
import com.sinius15.pi.PiServer;
import com.sinius15.pi.Service;
import com.sinius15.pi.services.socketserver.ClientThread;

public class SocketService extends Service implements Runnable{

	private ServerSocket serverSocket;
	
	@Override
	public boolean start() {
		try {
			serverSocket = new ServerSocket(PiServer.SOCKET_SERVER_PORT);
		} catch (IOException e) {
			Logger.log(e);
			return false;
		}
		new Thread(this).start();
		return true;
	}

	@Override
	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			
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
	}

	@Override
	public String getName() {
		return "Web Socket Server";
	}
	
}
