package com.sinius15.updater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.sinius15.pi.logging.Logger;

public class StreamStreamer extends Thread { 
	InputStream is;
	
	String preString;
	boolean running;
	boolean userLogger;
	
	public StreamStreamer(InputStream is, String type, boolean  useLogger) {
		this.is = is;
		this.preString = type;
		this.userLogger = useLogger;
	}
	 
	@Override
	public void run() {
		running = true;
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null){
				if(userLogger){
					Logger.showInConsole(preString + "> " + line);
					Logger.log(preString + "> " + line);
				}
				else
					System.out.println(preString + "> " + line);
			}
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
