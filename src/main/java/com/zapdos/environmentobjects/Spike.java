package com.zapdos.environmentobjects;


import com.zapdos.main.Sprite;
import com.zapdos.stage.BaseStage2;


public class Spike extends Sprite {
	private BaseStage2 bStage;
	
	public Spike(BaseStage2 bStage, String image, double x, double y, double scale) {
		super(image, x, y, 0.5*scale);
		this.bStage = bStage;
	}
	
	public boolean checkCollision() {
		if (this.getBounds().intersects(bStage.getPlayer().getPlayerSprite().getBounds())) {
			return true;
		}
		
		return false;
	}
}
