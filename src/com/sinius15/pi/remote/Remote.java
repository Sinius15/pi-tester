package com.sinius15.pi.remote;

import java.io.IOException;

import jline.ConsoleReader;

import com.sinius15.pi.PiServer;

public class Remote {
	
	char[][] things = new char[][]{
			{'1','.','?'},
			{'2','a','b', 'c', 'A', 'B', 'C'},
			{'3','d','e', 'f', 'D', 'E', 'F'},
			{'4','g','h', 'i', 'G', 'H', 'I'},
			{'5','j','k', 'l', 'J', 'K', 'L'},
			{'6','m','n', 'o', 'M', 'N', 'O'},
			{'7','p','q', 'r', 's', 'P', 'Q', 'R', 'S'},
			{'8','t','u', 'v', 'T', 'U', 'V'},
			{'9','w','x', 'y', 'z', 'X', 'Y', 'Z', 'W'},
	};
	
	public Remote(){
		System.out.println("starting remote");
		ConsoleReader cr;
		try {
			cr = new ConsoleReader();
			
			while (true){
				PiServer.wireManager.toggle(3);
			}
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
	}
	
}
