package com.sinius15.pi.remote;

import java.io.IOException;

import com.sinius15.pi.PiServer;
import com.sinius15.pi.server.WireManager;

import jline.ConsoleReader;

public class Remote {
	
	public Remote(){
		System.out.println("starting remote");
		ConsoleReader cr;
		try {
			cr = new ConsoleReader();
			int car;
			while ((car = cr.readVirtualKey()) != 0x09){
				PiServer.wireManager.toggle(3);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
