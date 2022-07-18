package com.zapdos.enemies;

import java.awt.Color;



import acm.graphics.GRect;

public class Enemy_Reverbot extends Enemy{
	
	String Name = "T_ReverbotMoveRight.gif";
	//GRect sightRec = new GRect(300, 300);
	//GRect Player = new GRect(50,50); 
	
	/**
	 * Default constructor
	 * 
	 */

	public Enemy_Reverbot(String name, double x, double y) {
		super(name, x, y);
		
		this.isGoomba = false;
		this.isReverbot = true;
		
	}
	
	/**
	 * Constructor that allows the user to pass in a player object and a GRect that 
	 * handles AI Behavior dependent on player + GRect references 
	 * 
	 */
	
	public Enemy_Reverbot(String name, double x, double y, GRect player, GRect sight) {
		super(name, x, y);
		
		this.isGoomba = false;
		this.isReverbot = true;
		this.sightRec = sight;
	
		//this.sightRec.setLocation(this.getX()/2, this.getY()/2);
		
	}
	
	public Enemy_Reverbot(String name, double x, double y, com.zapdos.player.Player player) {
		super(name, x, y);
		
		this.isGoomba = false;
		this.isReverbot = true;
	
		//this.sightRec.setLocation(this.getX()/2, this.getY()/2);
		
	}

}
