package com.sinius15.pi.services.website;

import java.io.IOException;
import java.io.OutputStream;

import com.sinius15.pi.logging.Logger;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CommandHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		Logger.logErr("uri: " + t.getRequestURI().toString());
		Logger.logErr("method: " + t.getRequestMethod());
		
		String response = "<html><body>";
		
		t.getResponseHeaders().set("Content-Type", "text/html");
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
}
