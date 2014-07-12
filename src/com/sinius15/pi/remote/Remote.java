package com.sinius15.pi.remote;

import java.io.IOException;

import jline.ConsoleReader;

import com.sinius15.pi.PiServer;

public class Remote {
	
	public Remote(){
		System.out.println("starting remote");
		ConsoleReader cr;
		try {
			cr = new ConsoleReader();
			
			int car;
			while (true){
				PiServer.wireManager.toggle(3);
			}
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
	}
	
}
