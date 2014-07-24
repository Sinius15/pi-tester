package com.sinius15.pi.logging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logger {
	
	public static List<LogMessage> logs = new ArrayList<>();
	
	public static final int MAX_LOG_MESSAGES = 150;
	
	public static void log(Exception e) {
		String[] message = new String[e.getStackTrace().length + 1];
		message[0] = e.getMessage();
		int i = 1;
		for (StackTraceElement element : e.getStackTrace()) {
			message[i] = element.toString();
			i++;
		}
		logs.add(new LogMessage(LogMessage.ERROR, message));
	}
	
	public static void log(String in) {
		logs.add(new LogMessage(LogMessage.MESSAGE, in));
	}
	
	public static void logErr(String in) {
		logs.add(new LogMessage(LogMessage.ERROR, in));
	}
	
	public static String getWebString() {
		update();
		String out = "<table>";
		
		for (LogMessage s : logs) {
			out += "<tr>";
			out += "<td>" + new SimpleDateFormat("dd.MM.yyy HH:mm:ss SSS").format(s.getTime())
					+ "</td>";
			out += "<td>" + s.getTypeString() + "</td>";
			out += "<td>" + s.MessageString() + "</td>";
			out += "</tr>";
		}
		out += "</table>";
		
		return out;
	}
	
	public static void logDebug(String in) {
		logs.add(new LogMessage(LogMessage.DEBUG, in));
	}
	
	private static void update(){
		if(logs.size() > MAX_LOG_MESSAGES){
			Collections.sort(logs);
			for(int i = MAX_LOG_MESSAGES; i < logs.size(); i++){
				logs.remove(i);
			}
		}	
		
	}
	
}
