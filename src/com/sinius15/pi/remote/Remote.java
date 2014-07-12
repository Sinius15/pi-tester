package com.sinius15.pi.remote;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import jline.ConsoleReader;

public class Remote {
	
	public Remote(){
		System.out.println("starting remote");
		try {
			ConsoleReader reader = new ConsoleReader();
			System.out.println(reader.readVirtualKey());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
