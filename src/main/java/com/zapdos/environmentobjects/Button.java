package com.zapdos.environmentobjects;

import com.zapdos.main.MainApplication;
import com.zapdos.main.Sprite;

public class Button extends Sprite {
	private static double SCALE = MainApplication.SCALE;
	private String name; // The unique identifier of each button.
	private String IMG_FOLDER = "sprites/";
	
	public Button(Double x, Double y, String name) {
		super("T_Button_Up.png", x, y, SCALE);
		this.name = name;
		this.setImage(IMG_FOLDER + "T_Button.png");
	}
	
	public String getName() { // Returns the button's name
		return name;
	}
	
}
