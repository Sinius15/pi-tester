package com.sinius15.updater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamStreamer extends Thread {
	InputStream is;
	String type;
	
	StreamStreamer(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}
	
	@Override
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(type + "> " + line);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
