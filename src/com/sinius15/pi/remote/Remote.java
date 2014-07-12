package com.sinius15.pi.remote;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Remote {
	
	public Remote(){
		System.out.println("starting remote");
		int in;
		Reader input = new InputStreamReader(System.in);
		try {
			while((in = input.read()) != -1){
				System.out.println(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
