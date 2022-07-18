package com.zapdos.enemies;

public class Enemy_Goomba extends Enemy{
	
	String Name = "T_Goomba.gif";

	public Enemy_Goomba(String name, double x, double y) {
		super(name, x, y);
		
		this.isGoomba = true;
		this.isReverbot = false;
		
	}

}
