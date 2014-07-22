package com.sinius15.pi.logging;

import java.util.Date;

public class LogMessage implements Comparable<LogMessage>{
	
	public static final int MESSAGE = 0, ERROR = 1, DEBUG = 2;
	
	private Date time;
	private int type;
	private String[] message;
	
	public LogMessage(int type, String... message) {
		setTime(new Date());
		this.type = type;
		this.message = message;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String MessageString() {
		String out = "";
		out += "<p style='color:"+getHTMLColor()+"'>";
		for(String msg : message){
			out += msg + "<br>"; 
		}
		out += "</p>";
		return out;
	}
	
	public String getHTMLColor(){
		switch (type) {
			case MESSAGE:
				return "black";
			case ERROR:
				return "red";
			case DEBUG:
				return "blue";
			default:
				return null;
		}
	}
	
	public String getTypeString() {
		switch (type) {
			case MESSAGE:
				return "Message";
			case ERROR:
				return "Error";
			case DEBUG:
				return "Debug";
			default:
				return null;
		}
	}

	@Override
	public int compareTo(LogMessage o) {
		return time.compareTo(o.time);
	}
	
	
	
}
