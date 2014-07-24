package com.sinius15.pi.services;

import java.io.IOException;
import java.net.URL;

import com.sinius15.pi.PiServer;
import com.sinius15.pi.Service;
import com.sinius15.pi.WebUtil;
import com.sinius15.pi.logging.Logger;

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
				
			if(PiServer.VERSION.equals(curVersion))
				return false;
			///so, we need to update :(
			PiServer.startUpdating();
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
	
}
