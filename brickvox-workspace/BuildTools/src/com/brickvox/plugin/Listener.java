package com.brickvox.plugin;

import java.sql.*;
import java.util.HashMap;

import com.brickvox.core.Command;

public class Listener { 
 
 HashMap <Integer, String> requests = new HashMap<>(); int r_ptr = 0;
	
 Connection listener;
 int last_fetched = 0;
 
 public Listener(Connection _conn) {
	 listener = _conn;
 }
 
 public void Fetch_Requests() {
	try {
		Statement statement = listener.createStatement();
		ResultSet res = statement.executeQuery("SELECT * FROM System_request WHERE Func_ID > "+last_fetched+" AND Dest_jc = 1;");
		while(res.next()) {
			r_ptr = requests.size();
			requests.put(r_ptr, res.getString("FPacket"));
			last_fetched = res.getInt("Func_ID");
		}
		//System.out.println("[DEBUG]>> current ptr: "+last_fetched+" request number: " + requests.size());
	} catch (SQLException e) {
		e.printStackTrace();
	} 
 }
 
 public void Invoke_req(Command handle) {
	 for(int i = 0; i < requests.size(); i++) {
		 String req = requests.get(i);
		 //System.out.println(req);
		 String[] cmd = req.split(" ");
		 String[] arguments = new String[100];
		 String lbl = "";
		 for(int j = 1; j < cmd.length; j++) {
			 arguments[j-1] = cmd[j];
			 lbl = lbl + cmd[j] + " ";
		 }
		 if(cmd[0].equalsIgnoreCase("!shutdown")) {
			 com.brickvox.monitor.BrickLog1.insert("plugin ended with status 0 - Reason: SERVER WAS SHUT DOWN", 0);
			 System.exit(0);
		 }
		 if(cmd[0].equalsIgnoreCase("!execute")) {
			 handle.onCommand(lbl, arguments); 
		 }
		 requests.remove(i);
	 }
 }
}