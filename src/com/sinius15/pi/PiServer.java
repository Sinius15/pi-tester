package com.sinius15.pi;

public class PiServer {
	
	public PiServer() {
		LightServer server = new LightServer();
		Thread serverThread = new Thread(server);
		serverThread.start();
	}
	
	// publci static part
	public static PiServer ligtController;
	public static WireManager wireManager;
	
	public static void main(String[] args) throws InterruptedException {
		wireManager = new WireManager();
		ligtController = new PiServer();
	} 
	
}
