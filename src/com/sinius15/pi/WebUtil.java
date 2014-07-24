package com.sinius15.pi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class WebUtil {
	
	public static String getWebContent(URL url) throws IOException {
		URLConnection urlConn = url.openConnection();
		BufferedInputStream inStream = new BufferedInputStream(urlConn.getInputStream());
		
		StringBuilder out = new StringBuilder();
		byte[] b = new byte[1024];
		while(inStream.read(b) != -1)
			out.append(new String(b));
		inStream.close();
		
		return out.toString();
	}
}
