package com.sinius15.pi.remote;

import java.io.IOException;

import jline.ConsoleReader;
import jline.Terminal;

import com.sinius15.pi.PiServer;

public class Remote {
	
	public Remote(){
		System.out.println("starting remote");
		Terminal cr;
		try {
			cr = new Terminal();
			int car;
			while ((car = cr.readVirtualKey()) != 0x09){
				PiServer.wireManager.toggle(3);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
