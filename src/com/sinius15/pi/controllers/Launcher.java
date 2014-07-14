package com.sinius15.pi.controllers;

import com.sinius15.launchpad.LaunchListener;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.LaunchpadException;
import com.sinius15.pi.PiServer;

public class Launcher implements LaunchListener{
	
	public Launchpad pad;
	
	public Launcher(){
		try {
			pad = new Launchpad("Lauchpad S");
			pad.addListener(this);
		} catch (LaunchpadException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onButtonDown(int row, int colomn) {
		if(row == 1){
			colomn += 1;
			PiServer.wireManager.toggle(colomn);
		}
	}

	@Override
	public void onButtonUp(int row, int colomn) {}
	
}
