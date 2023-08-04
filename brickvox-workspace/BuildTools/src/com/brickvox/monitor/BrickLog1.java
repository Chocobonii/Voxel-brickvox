package com.brickvox.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BrickLog1 {
	public static void make_header(String namef) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
		System.out.println("LOG GENERATED AT: ["+formatter.format(date) + "]");
		System.out.println("-----------------------------------------------------------");
		System.out.println(" BRICKVOX PLUGIN: " + namef + " | LOGGER VERSION: 1.0");
		System.out.println("-----------------------------------------------------------");
	}
	
	public static void insert(String message, int type) {
		String msg_type = "SERVER";
		switch(type) {
		 case 0:msg_type="_SERVER";break;
		 case 1:msg_type="WARNING";break;
		 case 2:msg_type="_ERRORS";break;
		 case 3:msg_type="MESSAGE";break;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    
	    System.out.println("[" + formatter.format(date) + " " + msg_type + "]>> " + message);
	}
}
