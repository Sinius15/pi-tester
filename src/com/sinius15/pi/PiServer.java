package com.sinius15.pi;

import java.util.Arrays;

import org.jsresources.MidiCommon;

import com.sinius15.pi.controllers.Launcher;
import com.sinius15.pi.controllers.Remote;
import com.sinius15.pi.server.LightServer;

public class PiServer implements Runnable{
	
	//public static PiServer piServer;
	public static WireManager wireManager = null;
	public static Launcher launcher = null;
	public static Remote remote = null;
	public static LightServer server = null;
	
	public static void main(String[] args) throws InterruptedException {
		wireManager = new WireManager();
		remote = new Remote();
		new Thread(new PiServer());
	}
	boolean running = true;
	
	@Override
	public void run() {
		while(running){
			//try to start launchpad if avalable
			String[] deviceNames = MidiCommon.listDevices(true, true);
			Arrays.asList(deviceNames).contains(Launcher.NAME);
			if(launcher == null && Arrays.asList(deviceNames).contains(Launcher.NAME)){
				try{
					launcher = new Launcher();
				}catch(Exception e){
					launcher = null;
				}
			}
			
			//try to start webserver if not yet running
			if(server == null){
				try {
					server = new LightServer();
				} catch (Exception e) {
					server = null;
				}
			}
				
			
		}
		
	}

	public static void startUpdating() {
		System.exit(0);
		
	} 
	
}
