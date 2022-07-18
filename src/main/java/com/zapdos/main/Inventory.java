package com.zapdos.main;

import java.util.ArrayList;
import java.util.Collections;

import acm.graphics.GObject;

public class Inventory	{
	private MainApplication program;
	
	public Sprite key = new Sprite ("T_Key.png", 10.0, 10.0);
	public Sprite health = new Sprite ("T_Energy.png", 122.0, 10.0);
	public Sprite energy = new Sprite ("T_Energy.png", 224.0, 10.0);
	public ArrayList<GObject> items = new ArrayList<GObject>();
	public Boolean foundKey = false;
	public Boolean foundHealth = false;
	public Boolean foundENergy = false;
	
	public Inventory () {
	}
	
	public void refreshIventory(){
		if (foundKey == true) {
			program.add(key.getImg());
		}
		
		if (foundHealth == true) {
			program.add(health.getImg());
		}
	}
}
