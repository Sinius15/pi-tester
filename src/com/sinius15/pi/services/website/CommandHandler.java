package com.sinius15.pi.services.website;

import java.io.IOException;
import java.io.OutputStream;

import com.sinius15.pi.PiServer;
import com.sinius15.pi.logging.Logger;
import com.sinius15.pi.services.socketserver.Protocol;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CommandHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange t) throws IOException {
		String[] split = t.getRequestURI().toString().split("/");
		
		
		String password = split[2];
		String command = split[3].replaceAll("%20", " ");
		
		Logger.log("Got GET request on api server: '" + t.getRequestURI() + "'. After decoding input is '" + command + "' and password is '" + password + "'");
		
		String response;
		if(PiServer.checkPassword(password))
			response = Protocol.handle(command);
		else
			response = "error_invalid_password";
		
		t.getResponseHeaders().set("Content-Type", "text/html");
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		t.close();
	}
	
}
