package com.sinius15.pi.services;

import java.io.IOException;
import java.net.URL;

import com.sinius15.pi.PiServer;
import com.sinius15.pi.Service;
import com.sinius15.pi.WebUtil;
import com.sinius15.pi.logging.Logger;
import com.sinius15.updater.StreamStreamer;

public class UpdateService extends Service {
	
	private boolean isLogged = false;
	
	/**
	 * @return true when there was an update and is updated.
	 */
	@Override
	public boolean start() {
		try {
			String curVersion = WebUtil.getWebContent(new URL("https://raw.githubusercontent.com/Sinius15/pi-tester/master/latestVersion.txt")).trim();
			if(isLogged == false){
				Logger.log("Current version: " + curVersion);
				isLogged = true;
			}
			Logger.showInConsole("jsut collected latest version: " + curVersion);
				
			if(!PiServer.VERSION.equals(curVersion))
				startUpdating();
		} catch (IOException e) {
			//somthing went wrong, well, we will try again in 5 seconds so no logging.
		}

		return false;
	}
	
	@Override
	public void close() {}
	
	@Override
	public String getName() {
		return "Updater";
	}
	
	public static void startUpdating() {
		String[] commands = new String[]{
				"java", "-jar", "updater.jar",
				"git_pull*reboot"
		};
		try{
			ProcessBuilder builder = new ProcessBuilder(commands);
			Process process = builder.start();
			
			StreamStreamer a = new StreamStreamer(process.getInputStream(), "Output", true);
			StreamStreamer b = new StreamStreamer(process.getInputStream(), "Error", true);
			a.start();
			b.start();
			while(a.isRunning() || b.isRunning()){
				Thread.sleep(3);
			}
		}catch(Exception e){
			Logger.log(e);
		}
	}
}
