package com.brickvox.plugin;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

import com.brickvox.core.Materials;

public class Bricks{
	String SRVR = "";
	String USER = "";
	String PASW = "";
	String CTDB = "";
	public int debug = 1;
	public Connection server_conn = null;
	
	public Bricks(String pluginName) {
		/*
		 * ------------------------------
		 * - FETCH THE AUTH CREDENTIALS -
		 * ------------------------------
		 * */
		System.out.println("Build tools version: 1.0.0.1");
		com.brickvox.monitor.BrickLog1.make_header(pluginName);
		File credentials = new File("loginCredentials.auth");
		if(!credentials.exists()) {
			com.brickvox.monitor.BrickLog1.insert("Could not find file",2);
			System.exit(-1);
		}
		// - READ THE LOGINCREDENTIALS.AUTH FILE
		Scanner rdf;
		try {
			rdf = new Scanner(credentials);
			while (rdf.hasNextLine()) {
				// -------------------------------
				String blockr = rdf.nextLine();   
				String[] cmdr = blockr.split(" ");
				// -------------------------------
				if(cmdr[0].equalsIgnoreCase("h")) {SRVR = cmdr[1];}
				if(cmdr[0].equalsIgnoreCase("u")) {USER = cmdr[1];}
				if(cmdr[0].equalsIgnoreCase("p")) {PASW = cmdr[1];}
				if(cmdr[0].equalsIgnoreCase("d")) {CTDB = cmdr[1];}
			}
			if(debug==1) {com.brickvox.monitor.BrickLog1.insert("logged in with credentials: " + SRVR + ", " + USER + ", " + PASW + ", "+ CTDB,3);}
			// - CLOSE THE FILES
			rdf.close();
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				server_conn = DriverManager.getConnection("jdbc:mysql://"+SRVR+":3306/"+CTDB,USER,PASW);
			}catch(Exception e) {
				com.brickvox.monitor.BrickLog1.insert("SERVER CONNECTION FAILED: " + e,2);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// - AUTHENTICATE CREDENTIALS
	}
	
	public void sendChat(String label, int session) {
		Statement statement;
        try {
			statement = server_conn.createStatement();
			String msg = "INSERT INTO chat_data (Msg_Data,User_Sent,Session) VALUES (\'< SYSTEM >>"+label+"\',\'SERVER\',"+session+");";
	        if(debug==1) {com.brickvox.monitor.BrickLog1.insert("packet sent: " + msg,0);}
			statement.execute(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setBlock(int x, int y, int z, int session, int _blockID) {
		float get_x = ((float)x)/(15.0f);
		float get_y = ((float)z)/(15.0f);
		
		int chunk_x = (int)get_x;
		int chunk_y = (int)get_y;
		
		float conv_get_x = 0;
        float conv_get_z = 0;
        if(get_x<0){conv_get_x=get_x*-1;}else{conv_get_x=get_x;}
        if(get_y<0){conv_get_z=get_y*-1;}else{conv_get_z=get_y;}
        
        int pos_x = (int) ((conv_get_x-chunk_x)*15);
        int pos_z = (int) ((conv_get_z-chunk_y)*15);
        
		Statement statement;
        try {
			statement = server_conn.createStatement();
			String msg = "";
			ResultSet res = statement.executeQuery("SELECT * FROM Modifications WHERE Chunk_X="+chunk_x+" AND Chunk_Y="+chunk_y+" AND Modif_X="+pos_x+" AND Modif_Y="+y+" AND Modif_Z="+pos_z+" AND session="+session+";");
			if(!res.next()) {
				com.brickvox.monitor.BrickLog1.insert("Object Is new object generating for script....",0);
				msg = "INSERT INTO Modifications (Chunk_X,Chunk_Y,Modif_X,Modif_Y,Modif_Z,session,Block_E) VALUES ("+chunk_x+","+chunk_y+","+pos_x+","+y+","+pos_z+","+session+","+_blockID+");";
			}else {
				com.brickvox.monitor.BrickLog1.insert("Object was created already updating...",0);
				msg = "UPDATE Modifications SET Block_E="+_blockID+" WHERE Chunk_X="+chunk_x+" AND Chunk_Y="+chunk_y+" AND Modif_X="+pos_x+" AND Modif_Y="+y+" AND Modif_Z="+pos_z+" AND session="+session+";";
			}
			
			if(debug==1) {com.brickvox.monitor.BrickLog1.insert("block sent: " + msg,0);}
			statement.execute(msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void item_register(String name, String code, int session /* 0 - for all*/, String _url ) {
		Statement statement;
        try {
        	String msg = "";
			statement = server_conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM custom_object WHERE Nameob=\'"+name+"\' ");
			if(!res.next()) {
				com.brickvox.monitor.BrickLog1.insert("Object Is new object generating for script....",0);
				msg = "INSERT INTO custom_object (Nameob,obCode,Sesion,img_ul, is_cube) VALUES (\'"+name+"\',\'"+code+"\',"+session+",\'"+_url+"\', 0);";
			}else {
				com.brickvox.monitor.BrickLog1.insert("Object was created already updating...",0);
				msg = "UPDATE custom_object SET obCode = \'"+code+"\',Sesion="+session+",img_ul=\'"+_url+"\' WHERE Nameob=\'"+name+"\';";
			}
	        if(debug==1) {com.brickvox.monitor.BrickLog1.insert("packet sent: " + msg,0);}
			statement.execute(msg);
			// --------- REGISTER MATERIAL ----------------
			ResultSet res2 = statement.executeQuery("SELECT * FROM custom_object WHERE Nameob=\'"+name+"\' ");
			if(res2.next()) {
				Materials.register_material(name, res2.getInt("Obj_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void brick_register(String name, String code, int session /* 0 - for all*/, String _url ) {
		Statement statement;
        try {
        	String msg = "";
			statement = server_conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM custom_object WHERE Nameob=\'"+name+"\' ");
			if(!res.next()) {
				com.brickvox.monitor.BrickLog1.insert("Object Is new object generating for script....",0);
				msg = "INSERT INTO custom_object (Nameob,obCode,Sesion,img_ul, is_cube) VALUES (\'"+name+"\',\'"+code+"\',"+session+",\'"+_url+"\', 1);";
			}else {
				com.brickvox.monitor.BrickLog1.insert("Object was created already updating...",0);
				msg = "UPDATE custom_object SET obCode = \'"+code+"\',Sesion="+session+",img_ul=\'"+_url+"\' WHERE Nameob=\'"+name+"\';";
			}
	        if(debug==1) {com.brickvox.monitor.BrickLog1.insert("packet sent: " + msg,0);}
			statement.execute(msg);
			// --------- REGISTER MATERIAL ----------------
			ResultSet res2 = statement.executeQuery("SELECT * FROM custom_object WHERE Nameob=\'"+name+"\' ");
			if(res2.next()) {
				Materials.register_material(name, res2.getInt("Obj_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
