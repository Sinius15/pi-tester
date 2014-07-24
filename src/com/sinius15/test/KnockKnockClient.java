package com.sinius15.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

import com.sinius15.pi.PiServer;
import com.sinius15.pi.WebUtil;

public class KnockKnockClient {
	
	public static PrintWriter out;
	public static BufferedReader in;
	public static Socket server;
	public static Scanner scanner;
	
	public static void main(String[] args) throws IOException {
		
		try {
			System.out.println(WebUtil.getWebContent(new URL("")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String hostName = "10.233.0.116";
		
		server = new Socket(hostName, PiServer.SOCKET_SERVER_PORT);
		out = new PrintWriter(server.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		scanner = new Scanner(System.in, "UTF-8");
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String fromServer;
				try {
					while ((fromServer = in.readLine()) != null) {
						System.out.println("Server: " + fromServer);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						server.close();
						out.close();
						in.close();
						scanner.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String to;
					for(int i = 1; i < 9; i++){
						out.println("on " + i);
						Thread.sleep(1000);
					}
					for(int i = 1; i < 9; i++){
						out.println("off " + i);
						Thread.sleep(1000);
					}
					while ((to = scanner.nextLine()) != null) {
						out.println(to);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
				}
			}
		}).start();;
		
	}
}