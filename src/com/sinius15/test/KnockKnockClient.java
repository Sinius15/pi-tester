package com.sinius15.test;

/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.sinius15.pi.server.LightServer;

public class KnockKnockClient {
	
	public static PrintWriter out;
	public static BufferedReader in;
	public static Socket server;
	public static Scanner scanner;
	
	public static void main(String[] args) throws IOException {
		
		String hostName = "10.233.0.116";
		
		server = new Socket(hostName, LightServer.port);
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