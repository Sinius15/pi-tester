package com.sinius15.pi;

import java.io.Closeable;

public abstract class Service implements Closeable{
	
	public abstract boolean start();
	
	public void close(){
		isRunning = false;
	};
	
	public abstract String getName();
	
	public boolean isRunning = false;
	
}
