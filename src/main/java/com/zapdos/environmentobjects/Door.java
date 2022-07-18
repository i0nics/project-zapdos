package com.zapdos.environmentobjects;

import com.zapdos.main.Sprite;

public class Door extends Sprite {
	
	public Boolean isOpen = false;					// Check the state of the instantiated object to run game logic

	public Door(double x, double y, double scale, Boolean lock) {
		super("T_DoorClosed.png", x, y, scale);
		isOpen = lock;
		
		if (isOpen) setDoorOpen();
		else setDoorClosed();
	}
	
	public void setDoorClosed() {
		this.setImage("T_DoorClosed.png");
		
	}
	
	public void setDoorOpen() {
		this.setImage("T_DoorOpen.png");
		
	}
	
	public Boolean getIsOpen() {
		return isOpen;
	}

}
