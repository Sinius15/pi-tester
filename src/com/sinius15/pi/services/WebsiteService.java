package com.sinius15.pi.services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sinius15.pi.Logger;
import com.sinius15.pi.Service;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebsiteService extends Service implements HttpHandler {
	
	private HttpServer server;
	
	@Override
	public boolean start() {
		try {
			server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/", this);
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
	public void handle(HttpExchange t) throws IOException {
		String response = Logger.getWebString();
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	@Override
	public String getName() {
		return "Website Service";
	}
	
}
