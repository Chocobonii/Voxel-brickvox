package com.brickvox.core;

import java.util.HashMap;

public class Materials {
	public static int BASIC_BRICKS = 28;
	// ------ NORMAL BRICKS ---------
	public int EMPTY       = 0;
	public int GRASS       = 1;
	public int STONE       = 2;
	public int STONE_BRICKS= 3;
	public int RAW_WOOD    = 4;
	public int TREE_LEAVES = 5;
	public int PLANTS      = 6;
	public int TREE_BASE   = 7;
	public int COBBLESTONE = 8;
	public int WOOD_PLANKS = 9;
	public int BOOK_SHELF  =10;
	public int PURPLE_STONE=11;
	public int GLASS_BLOCK =12;
	public int STONE_DOOR  =13;
	public int BRICK_WALL  =14;
	public int SAND        =15;
	// -------COLOR BRICKS ------------
	public int COLOR_BRICK_RED    = 16;
	public int COLOR_BRICK_ORANGE = 17;
	public int COLOR_BRICK_YELLOW = 18;
	public int COLOR_BRICK_GREEN  = 19;
	public int COLOR_BRICK_BLUE   = 20;
	public int COLOR_BRICK_PINK   = 21;
	public int COLOR_BRICK_WHITE  = 22;
	public int COLOR_BRICK_BLACK  = 23;
	// --------------------------------
	public int DIRT        = 24;
	public int RED_LIGHT   = 25;
	public int BLUE_LIGHT  = 26;
	public int GREEN_LIGHT = 27;
	public int WATER_PLANE = 28;
	// --------------------------------
	static HashMap <String, Integer> custom = new HashMap<>();
	// --------------------------------
	public static void register_material(String Locator, int block) {
		custom.put(Locator, BASIC_BRICKS+block);
	}
	
	public static int get_material(String Locator) {
		return custom.get(Locator);
	}
}
