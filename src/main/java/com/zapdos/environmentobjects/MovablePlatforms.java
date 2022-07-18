package com.zapdos.environmentobjects;

import com.zapdos.main.Sprite;

public class MovablePlatforms extends Sprite {

	private char letter;
	private Boolean canMove = true;
	private double timer = 300;

	private Boolean canMoveVert = false;
	private Boolean canMoveHor = false;
	private Boolean isMovingVert = false;
	private double platformTimer = 40;
	private double movTimerVert = 0;
	private double movTimerHor = 0;
	private double moveY = 10.0;
	private double moveX = 10.0;
	private double isDownOrRight = 0.0;
	private double defX = 0.0;
	private double defY = 0.0;

	public MovablePlatforms(String fileName, Double x, Double y, Double scale, char letter, boolean isMovingVert, double isDownOrRight) {
		super(fileName, x, y, scale);
		this.letter = letter;
		this.isDownOrRight = isDownOrRight;
		this.isMovingVert = isMovingVert;
		defX = x;
		defY = y;
	}

	public void moveDown() {
		if (canMove) {
			canMove = false;
			for (int i = 0; i < timer; i++) {
				this.move(0.0, 300.0);
			}
		}
	}

	public void moveUp() {
		if (!canMove) {
			canMove = true;
			this.move(0.0, -300.0);
		}

	}

	public void movePlatformVert() {
		if (isMovingVert) {
			if (canMoveVert) {
				movTimerVert++;
				System.out.println(movTimerVert);
				if (movTimerVert < platformTimer) {
					this.move(0.0, isDownOrRight * moveY);
				}
			}
			if (movTimerVert > platformTimer) {
				this.move(0.0, -isDownOrRight * moveY);
			}

			if (movTimerVert == platformTimer) {
				canMoveVert = false;
				//this.setLocation(this.getX(), defY + 10);
//				System.out.print("Platform position X : " + defX);
//				System.out.print("Platform position Y : " + defY);



			}
			if (movTimerVert == platformTimer * 2) {
				canMoveVert = false;
				movTimerVert = 0;
				//this.setLocation(this.getX(), defY);
				//this.getImg().setLocation(this.getX(), defY);
//				System.out.print("Platform position X : " + defX);
//				System.out.print("Platform position Y : " + defY);
		
			}
		}
	}

	public void movePlatformHor() {
		if (!isMovingVert) {
			if (canMoveHor) {
				movTimerHor++;
				System.out.println(movTimerHor);
				if (movTimerHor < platformTimer) {
					this.move(isDownOrRight * moveY, 0.0);
				}
			}
			if (movTimerHor > platformTimer) {
				this.move(-isDownOrRight * moveY, 0.0);
			}

			if (movTimerHor == platformTimer) {
				canMoveHor = false;
			//	this.setLocation(defX - 10, defY);
				System.out.print("Platform position X : " + defX);
				System.out.print("Platform position Y : " + defY);
			}

			if (movTimerHor == platformTimer * 2) {
				canMoveHor = false;
				movTimerHor = 0;
				System.out.print("Platform position X : " + defX);
				System.out.print("Platform position Y : " + defY);
			}
			
		}
		
	}
	
	
	public void move(double x, double y) {
		this.getImg().move(x, y);
		defX = this.getX();
		defY = this.getY();

	}

	public char getLetter() {
		return letter;
	}
	
	public Boolean getIsMovingVert() {
		return isMovingVert;
	}
	
	public Double getIsDownOrRight() {
		return isDownOrRight;
	}

	public void setCanMove(Boolean canMove) {
		this.canMove = canMove;
	}
	
	public void setCanMoveHor(Boolean canMoveHor) {
		this.canMoveHor = canMoveHor;
	}
	
	public void setCanMoveVert(Boolean canMoveVert) {
		this.canMoveVert = canMoveVert;
	}

}