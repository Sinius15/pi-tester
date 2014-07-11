package com.sinius15.pi;

public class LightController {
	
	public LightController() {
		LightServer server = new LightServer();
		Thread serverThread = new Thread(server);
		serverThread.start();
	}
	
	// publci static part
	public static LightController ligtController;
	public static WireManager wireManager;
	
	public static void main(String[] args) throws InterruptedException {
		//wireManager = new WireManager();
		ligtController = new LightController();
	}
	
}
