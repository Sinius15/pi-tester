package com.sinius15.pi;

import java.util.Arrays;

import org.jsresources.MidiCommon;

import com.sinius15.pi.controllers.Launcher;
import com.sinius15.pi.controllers.Remote;
import com.sinius15.pi.server.LightServer;
import com.sinius15.pi.server.WireManager;

public class PiServer implements Runnable{
	
	public PiServer() {
		LightServer server = new LightServer();
		Thread serverThread = new Thread(server);
		serverThread.start();
	}
	
	// publci static part
	public static PiServer ligtController;
	public static WireManager wireManager;
	public static Launcher launcher;
	public static Remote remote;
	
	public static void main(String[] args) throws InterruptedException {
		if(args.length == 0){
			wireManager = new WireManager();
			ligtController = new PiServer();	 
		}
		
		remote = new Remote();
		 
	}
	boolean running = true;
	
	@Override
	public void run() {
		while(running){
			String[] deviceNames = MidiCommon.listDevices(true, true);
			Arrays.asList(deviceNames).contains(Launcher.NAME);
			if(launcher == null && Arrays.asList(deviceNames).contains(Launcher.NAME)){
				try{
					launcher = new Launcher();
				}catch(Exception e){
					launcher = null;
				}
			}
			
		}
		
	} 
	
}
