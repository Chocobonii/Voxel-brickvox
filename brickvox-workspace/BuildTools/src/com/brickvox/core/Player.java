package com.brickvox.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Player {
 public static int x = 0;
 public static int y = 0;
 public static int z = 0;
 public static int rot = 0;
 public static int session = 0;
 public static String username = "";
 public static String model = "";
 public static String texture = "";
 
 static Connection server_con;
 
 Player(Connection _conn){
	 server_con = _conn;
 }
 
 public static void getPlayer(String _username){
	 try {
		Statement statement = server_con.createStatement();
		ResultSet res = statement.executeQuery("SELECT * FROM Players_ingame WHERE Username = "+_username+";");
		while(res.next()) {
			x = res.getInt("Pos_X");
			y = res.getInt("Pos_Y");
			z = res.getInt("Pos_Z");
			rot = res.getInt("Rot_P");
			session = res.getInt("Sesion");
			username = res.getString("Username");
			model = res.getString("Model");
			texture = res.getString("Texture");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} 
 }
}
