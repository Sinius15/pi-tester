package com.sinius15.pi.controllers;

import javax.sound.midi.MidiUnavailableException;

import org.jsresources.MidiCommon;

import com.sinius15.launchpad.LaunchListener;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.LaunchpadException;
import com.sinius15.pi.PiServer;

public class Launcher implements LaunchListener {
	
	public Launchpad pad;
	
	public Launcher() {
		for(String s : MidiCommon.listDevices(true, true)){
			System.out.println(s); 
		} 
		try {
			pad = new Launchpad("S [hw:1,0,0]");
			pad.open(); 
			pad.addListener(this);
			pad.LEDTest();
			PiServer.wireManager.onChangeListeners.add(onChange);
			System.out.println("Found launchpad!");
		} catch (LaunchpadException | MidiUnavailableException e) {
			e.printStackTrace();
			System.err.println("Could not find launchpad.");
		}
		
	}
	
	private Runnable onChange = new Runnable() {
		@Override
		public void run() {
			pad.reset();
			for(int i = 1; i <= 8; i++){
				if(PiServer.wireManager.getState(i))
					pad.setLedOn(i-1, 1, Launchpad.COLOUR_RED_FULL);
			}
		}
	};
	
	@Override
	public void onButtonDown(int row, int colomn) {
		if (row == 1) {
			colomn += 1;
			PiServer.wireManager.toggle(colomn);
		}
	}
	
	@Override
	public void onButtonUp(int row, int colomn) {}
	
}
