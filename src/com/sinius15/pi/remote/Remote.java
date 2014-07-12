package com.sinius15.pi.remote;

import java.io.IOException;

public class Remote {
	
	public Remote(){
		int in;
		try {
			while((in = System.in.read()) != -1){
				System.out.println(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
