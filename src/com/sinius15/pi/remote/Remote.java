package com.sinius15.pi.remote;

import java.io.IOException;

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
		
		things1d =  
		
		System.out.println("starting remote");
		ConsoleReader cr;
		try {
			cr = new ConsoleReader();
			
			while (true){
				char car = cr.readCharacter(things);
				PiServer.wireManager.toggle(3);
			}
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
	}
	
	public static char mode(char[][] arr) {
	    List<Integer> list = new ArrayList<Integer>();
	    for (int i = 0; i < arr.length; i++) {
	        // tiny change 1: proper dimensions
	        for (int j = 0; j < arr[i].length; j++) { 
	            // tiny change 2: actually store the values
	            list.add(arr[i][j]); 
	        }
	    }

	    // now you need to find a mode in the list.

	    // tiny change 3, if you definitely need an array
	    int[] vector = new int[list.size()];
	    for (int i = 0; i < vector.length; i++) {
	        vector[i] = list.get(i);
	    }
	}
	
}
