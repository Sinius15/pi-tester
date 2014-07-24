package com.sinius15.pi;

import java.util.ArrayList;
import java.util.Arrays;

import com.sinius15.pi.logging.Logger;
import com.sinius15.pi.services.LaunchpadService;
import com.sinius15.pi.services.RemoteService;
import com.sinius15.pi.services.SocketService;
import com.sinius15.pi.services.UpdateService;
import com.sinius15.pi.services.WebsiteService;

public class PiServer {
	 
	public static final String VERSION = "2.5a";
	public static final String LAUNCHPAD_NAME = "S [hw:1,0,0]";
	public static final int WEB_SERVER_PORT = 80;
	public static final int SOCKET_SERVER_PORT = 3443;
	
	public static WireManager wireManager;
	
	private static ArrayList<Service> services = new ArrayList<>();
	private static boolean isCloseRequested = false;
	
	public static void main(String[] args) {
		
		Logger.log("Starting PiServer program");
		wireManager = new WireManager();
		
		Logger.log("Adding Services...");
		services.add(new RemoteService());
		services.add(new WebsiteService());
		services.add(new SocketService());
		services.add(new LaunchpadService());
		services.add(new UpdateService());
		
		Logger.log("Adding Services done");
		
		while (!isCloseRequested) {
			for (Service service : services) {
				if (service.isRunning == false) {
					if (service.start() == true) {
						service.isRunning = true;
						Logger.log("Started " + service.getName() + "!");
					} else {
						// Logger.log("Could not start " + service.getName() +
						// ". Lets try again in 5 seconds.");
					}
				}
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static final String[] avalableCommands = new String[]{"on 6", "on 7", "on 8", "off 6", "off 7", "off 8"};
	
	public static boolean checkPermission(String pass, String in) {
		if(pass == null)
			return true;
		if(Arrays.asList(avalableCommands).contains(in))
			return true;
		if(in.startsWith("state"))
			return true;
		return false;
	}
}
