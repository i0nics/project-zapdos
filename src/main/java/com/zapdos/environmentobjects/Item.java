package com.zapdos.environmentobjects;

import java.awt.Image;

import com.zapdos.main.Sprite;

import acm.graphics.GImage;

public class Item extends Sprite {
	
	public Item(String name, double x, double y) {
		super(name, x, y, 1.0);
		// TODO Auto-generated constructor stub
	}





	enum itemType{KEY, ENERGY, HEALTH};
	int itemNum = 0;
	GImage itemAdd;
	Boolean isEnergy;
	Boolean isKey;
	Boolean isHealth;
	String energy = "T_Energy.png";
	String key = "T_Key.png";
	
	
	

	
	public GImage getItem(){
		return itemAdd;
	}

}
