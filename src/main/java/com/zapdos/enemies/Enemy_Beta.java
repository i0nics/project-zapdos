package com.zapdos.enemies;

public class Enemy_Beta extends Enemy{
	
	String Name = "T_Beta.gif";

	public Enemy_Beta(String name, double x, double y) {
		super(name, x, y);
		
		this.isGoomba = false;
		this.isReverbot = false;
		this.isAlpha = false;
		this.isBeta = true;
	}

}
