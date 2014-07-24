package com.sinius15.pi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class WebUtil {
	
	public static String getWebContent(URL url) throws IOException {
		URLConnection urlConnection = url.openConnection();
		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection)
			connection = (HttpURLConnection) urlConnection;
		else
			throw new IOException("Connection was not an HTTP-URL-Connection!");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String urlString = "";
		String current;
		while ((current = in.readLine()) != null) {
			urlString += current;
		}
		return urlString;
	}
}
