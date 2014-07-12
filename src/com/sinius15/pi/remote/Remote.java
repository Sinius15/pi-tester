package com.sinius15.pi.remote;

import java.io.IOException;

import jline.ConsoleReader;

public class Remote {
	
	public Remote(){
		System.out.println("starting remote");
		ConsoleReader cr;
		try {
			cr = new ConsoleReader();
			int car;
			while ((car = cr.readVirtualKey()) != 0x09){
				System.out.println(car); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
