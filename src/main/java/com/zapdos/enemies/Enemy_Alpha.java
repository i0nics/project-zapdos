package com.zapdos.enemies;

public class Enemy_Alpha extends Enemy{
	
	String Name = "T_Alpha.gif";

	public Enemy_Alpha(String name, double x, double y) {
		super(name, x, y);
		
		this.isGoomba = false;
		this.isReverbot = false;
		this.isBeta = false;
		this.isAlpha = true;
	}
	
	private void shootPlayer() {
		// todo
	}

}
