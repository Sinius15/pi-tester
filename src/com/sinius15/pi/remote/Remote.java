package com.sinius15.pi.remote;

import java.io.IOException;

public class Remote {
	
	public Remote(){
		System.out.println("starting remote");
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
