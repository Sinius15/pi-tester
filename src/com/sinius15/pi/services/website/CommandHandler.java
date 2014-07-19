package com.sinius15.pi.services.website;

import java.io.IOException;
import java.io.OutputStream;

import com.sinius15.pi.logging.Logger;
import com.sinius15.pi.services.socketserver.Protocol;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CommandHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		String request = t.getRequestURI().toString().split("/")[1];
		request = request.replaceAll("%20", " ");
		
		String response = Protocol.handle(request);
		
		Logger.log("Got GET request on api server: '" + t.getRequestURI() + "'. After decoding input is '" + request + "'");
		
		t.getResponseHeaders().set("Content-Type", "text/html");
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
}
