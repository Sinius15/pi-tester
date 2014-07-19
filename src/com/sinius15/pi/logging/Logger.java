package com.sinius15.pi.logging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Logger {
	
	public static List<LogMessage> logs = new ArrayList<>();
	
	public static void log(Exception e) {
		String[] message = new String[e.getStackTrace().length];
		int i = 0;
		for (StackTraceElement element : e.getStackTrace()){
			message[i] = element.toString();
			i++;
		}
		new LogMessage(LogMessage.ERROR, message);
	}
	
	public static void log(String in) {
		logs.add(new LogMessage(LogMessage.MESSAGE, in));
	}
	
	public static void logErr(String in) {
		logs.add(new LogMessage(LogMessage.ERROR, in));
	}
	
	public static String getWebString() {
		String out = "<table>";
		
		for (LogMessage s : logs) {
			out += "<tr>";
			out += "<td>" + new SimpleDateFormat("dd.MM.yyy HH:mm:ss SSS").format(s.getTime()) + "</td>";
			out += "<td>" + s.getTypeString() + "</td>";
			out += "<td>" + s.MessageString() + "</td>";
			out += "</tr>";
		}
		out+= "</table>";
		
		return out;
	}
	
}
