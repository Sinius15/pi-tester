package com.sinius15.pi.server;

import com.sinius15.pi.PiServer;

public class Protocol {
	
	public static String handle(String in) {
		int argCount = in.split(" ", -1).length;
		
		if (in.startsWith("on")) {
			if (argCount != 2)
				return "error_invalid_argument";
			int key = Integer.parseInt(in.split(" ")[1]);
			if (key < 1 || key > 8)
				return "error_invalid_argument";
			PiServer.wireManager.setWireState(key, true);
			return "succes";
		}
		if (in.startsWith("off")) {
			if (argCount != 2)
				return "error_invalid_argument";
			int key = Integer.parseInt(in.split(" ")[1]);
			if (key < 1 || key > 8)
				return "error_invalid_argument";
			PiServer.wireManager.setWireState(key, false);
			return "succes";
		}
		if (in.startsWith("allon")) {
			PiServer.wireManager.allOn();
			return "succes";
		}
		if (in.startsWith("alloff")) {
			PiServer.wireManager.allOff();
			return "succes";
		}
		if (in.startsWith("toggle")) {
			if (argCount != 2)
				return "error_invalid_argument";
			int key = Integer.parseInt(in.split(" ")[1]);
			if (key < 1 || key > 8)
				return "error_invalid_argument";
			if (PiServer.wireManager.toggle(key))
				return "succes_state_on";
			return "succes_state_off";
		}
		if (in.startsWith("state")) {
			if (argCount != 2)
				return "error_invalid_argument";
			int key = Integer.parseInt(in.split(" ")[1]);
			if (key < 1 || key > 8)
				return "error_invalid_argument";
			if (PiServer.wireManager.getState(key))
				return "succes_state_on";
			return "succes_state_off";
		}
		if (in.startsWith("restart"))
		return "error_invalid_request";
	}
	
}
