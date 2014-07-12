package com.sinius15.pi.remote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jline.ConsoleReader;

import com.sinius15.pi.PiServer;

public class Remote {
	
	char[][] things2d = new char[][]{
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
	char[] things1d;
	
	public Remote(){
		
		things1d =  mode(things2d);
		
		System.out.println("starting remote");
		ConsoleReader cr;
		try {
			cr = new ConsoleReader();
			
			while (true){
				char in = (char) cr.readCharacter(things1d);
				cr.printNewline();
				cr.printString(Character.toString(in));
				PiServer.wireManager.toggle(3); 
			}
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
	}
	
	public static char[] mode(char[][] arr) {
	    List<Character> list = new ArrayList<Character>();
	    for (int i = 0; i < arr.length; i++) {
	        for (int j = 0; j < arr[i].length; j++) { 
	            list.add(arr[i][j]); 
	        }
	    }

	    char[] vector = new char[list.size()];
	    for (int i = 0; i < vector.length; i++) {
	        vector[i] = list.get(i);
	    }
	    return vector;
	}
	
}
