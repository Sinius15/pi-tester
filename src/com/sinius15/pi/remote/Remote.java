package com.sinius15.pi.remote;

import java.io.IOException;

import jline.ConsoleReader;

public class Remote {
	
	public Remote(){
		System.out.println("starting remote");
		try {
			ConsoleReader reader = new ConsoleReader();
			char read;
			while((read = (char) reader.readCharacter("abcdefghijklmnoprstuvwxyz".toCharArray())) != 'q'){
				System.out.println(read);
			}
			
			System.out.println(reader.readVirtualKey());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
