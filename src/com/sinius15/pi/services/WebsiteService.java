package com.sinius15.pi.services;

import java.net.InetSocketAddress;

import com.sinius15.pi.PiServer;
import com.sinius15.pi.Service;
import com.sinius15.pi.services.website.CommandHandler;
import com.sinius15.pi.services.website.LogInfoHandler;
import com.sun.net.httpserver.HttpServer;

public class WebsiteService extends Service {
	
	private HttpServer server;
	
	@Override
	public boolean start() {
		try {
			server = HttpServer.create(new InetSocketAddress(PiServer.WEB_SERVER_PORT), 0);
			server.createContext("/logging", new LogInfoHandler());
			server.createContext("/api", new CommandHandler());
			server.setExecutor(null); 
			server.start();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void close() {
		server.stop(0);
	}

	@Override
	public String getName() {
		return "Website Service";
	}
	
}
