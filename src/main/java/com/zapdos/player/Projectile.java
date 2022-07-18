package com.zapdos.player;

import com.zapdos.main.MainApplication;
import com.zapdos.main.Sprite;


public class Projectile extends Sprite {
	private static double SCALE = MainApplication.SCALE;
	boolean isBasicShot = true;
	boolean basicShotSound = true;
	
	
	//Player player = new Player(null, null, 0, 0);
	
	//GImage basicShot = new GImage ("T_projectileR.gif", player.getPlayerImg().getLocation().getX(), player.getPlayerImg().getLocation().getY());
	
	
	public Projectile(String name, double x, double y) {
		super(name, x, y, SCALE);
	}
}
