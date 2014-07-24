package com.sinius15.updater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamStreamer extends Thread { 
	InputStream is;
	String preString;
	boolean running;
	
	public StreamStreamer(InputStream is, String type) {
		this.is = is;
		this.preString = type;
	}
	 
	@Override
	public void run() {
		running = true;
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(preString + "> " + line);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally{
			running = false;
		}
	}
	
	public boolean isRunning(){
		return running;
	}
}
