package com.sinius15.pi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger {

	public static ArrayList<String> logs = new ArrayList<>();
	
	public static void log(Exception e){
		logs.add( "[" + new SimpleDateFormat("dd.MM.yyy HH:mm:ss + SS").format(new Date()) + "] [error]" + e.getMessage() );
		for(StackTraceElement element : e.getStackTrace())
			logs.add("- [trace] " + element.toString());
		
	}
	public static void log(String in){
		logs.add( "[" + new SimpleDateFormat("dd.MM.yyy HH:mm:ss + SS").format(new Date()) + "] [log]" + in );
	}
	public static void logErr(String in){
		logs.add( "[" + new SimpleDateFormat("dd.MM.yyy HH:mm:ss + SS").format(new Date()) + "] [error]" + in );
	}
	public static String getWebString(){
		String out = "";
		
		for(String s : logs){
			out += s + "<br>";
		}
		
		return out;
	}
	
}
