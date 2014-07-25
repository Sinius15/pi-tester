package com.sinius15.pi.services;

import javax.sound.midi.MidiUnavailableException;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.LaunchpadException;
import com.sinius15.launchpad.events.ButtonListener;
import com.sinius15.launchpad.events.PadListener;
import com.sinius15.pi.PiServer;
import com.sinius15.pi.Service;

public class LaunchpadService extends Service implements ButtonListener, PadListener{

	Launchpad pad = null;
	
	@Override
	public boolean start() {
		try {
			pad = new Launchpad(PiServer.LAUNCHPAD_NAME);
			pad.open();
			pad.addButtonListener(this);
			PiServer.wireManager.onChangeListeners.add(onChange);
		} catch (MidiUnavailableException | LaunchpadException e) {
			return false;
		} 
		return true;
	}

	@Override
	public String getName() {
		return "Launchpad Inputter";
	}
	
	private Runnable onChange = new Runnable() {
		@Override
		public void run() {
			pad.reset();
			for(int i = 1; i <= 8; i++){
				if(PiServer.wireManager.getState(i)){
					pad.setLedOn(i-1, 1, Launchpad.COLOR_RED_FULL);
					pad.setLedOn(i-1, 2, Launchpad.COLOR_RED_FULL);
				}
			}
		}
	};

	@Override
	public void onButtonDown(int row, int colomn) {
		colomn += 1;
		if (row == 1) {
			PiServer.wireManager.toggle(colomn);
		}
		if (row == 2) {
			PiServer.wireManager.setWireState(colomn, true);
		}
	}
	
	@Override
	public void onButtonUp(int row, int colomn) {
		colomn += 1;
		if (row == 2) {
			PiServer.wireManager.setWireState(colomn, false);
		}
	}

	@Override
	public void padOpen() {}

	@Override
	public void padClose() {}

	@Override
	public void padDisconnected() {
		pad = null;
		close();
	}
}
