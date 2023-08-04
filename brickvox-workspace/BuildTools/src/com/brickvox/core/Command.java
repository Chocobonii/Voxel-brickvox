package com.brickvox.core;

public class Command {
	 public boolean onCommand(String label, String[] args) {
		 com.brickvox.monitor.BrickLog1.insert("If you see this message, you Must override the onCommand function to write your commands, thanks!", 1);
		 com.brickvox.monitor.BrickLog1.insert("what i've received is: " + label + " for comand " + args[0], 1);
		 return false;
	 }
}
