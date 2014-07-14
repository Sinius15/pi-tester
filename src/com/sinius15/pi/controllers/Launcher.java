package com.sinius15.pi.controllers;

import com.sinius15.launchpad.LaunchListener;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.LaunchpadException;

public class Launcher implements LaunchListener{
	
	public Launcher(){
		try {
			Launchpad pad = new Launchpad("Lauchpad S");
			pad.addListener(this);
			pad.
		} catch (LaunchpadException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onButtonDown(int row, int colomn) {}

	@Override
	public void onButtonUp(int row, int colomn) {}
	
}
