/**
 * Base enemy class that represents all enemy types
 * Inherited classes can access built in methods that auto-generate AI behavior
 */

package com.zapdos.enemies;

import java.awt.Color;

import com.zapdos.main.AudioPlayer;
import com.zapdos.main.Sprite;
import acm.graphics.GRect;

public class Enemy extends Sprite {

	private AudioPlayer audio = AudioPlayer.getInstance();

	public GRect sightRec = new GRect(750, 500, 750, 750);
	GRect Player = new GRect(750, 500, 100, 100); // TODO: Change to player object once player class is implemented
	com.zapdos.player.Player newPlayer = new com.zapdos.player.Player(null, null, null, 50.0, 50.0);
	Boolean isGoomba = true;
	Boolean isReverbot = false;
	Boolean isAlpha = false;
	Boolean isBeta = false;
	public Boolean canPlaySound = false;
	public Boolean isFalling = false;
	public Boolean seePlayer = false;
	private Boolean isDead = false;
	int upper = 400;
	int lower = 0;
	int r = (int) (Math.random() * (upper - lower)) + lower;
	int i = 0;

	/**
	 * Base constructor & extended constructor that allows the user to pass in a
	 * player object that AI events can use to produce player specific AI event
	 * logic
	 */

	public Enemy(String name, double x, double y) {
		super(name, x, y, 1.0);
		sightRec.setLineWidth(5); // remove this later
		sightRec.setColor(Color.ORANGE);
		sightRec.setFillColor(Color.RED);
		sightRec.setFilled(false);
		//sightRec.setVisible(true);
	}

	public GRect getSightRec() {
		return sightRec;
	}
	
	public void dead() {
		isDead = true;
	}

	/**
	 * Function to call to auto update the derived enemy class & produce default AI
	 * behavior
	 */

	public void updateEnemy() {																		// this is the unit test
		if (!isDead) {
			i++;
			sightRec.setLocation(this.getX() - 200, this.getY() - 500);

			if (sightRec.getBounds().intersects(Player.getBounds()) == true) {
				seePlayer = true;
				if (canPlaySound) {
					audio.playSound("sfx", "Sound_SeePlayer.mp3", false);
					canPlaySound = false;
				}
			}
			
			if (sightRec.getBounds().intersects(Player.getBounds()) == false) {
				seePlayer = false;
				canPlaySound = true;
			}

			if (isFalling == true) {
				this.move(0.0, 1.0);
			}

			if (isGoomba == true) {

				goombaLeft();
				if (i < 400) {
					this.move(-0.5, 0.0);
				}
				if (i > 400) {
					this.move(0.5, 0.0);
				}
				if (i > 800) {
					i = 0;
				}
			}
			if (isReverbot && seePlayer) {

				if (seePlayer == true) {
					if (Player.getLocation().getX() > this.getX()) {
						this.move(5.5, 0.0);
						reverbotRight();

					}
					if (Player.getLocation().getX() < this.getX()) {
						this.move(-5.5, 0.0);
						reverbotLeft();

					}
				}
			}

			if (isReverbot && !seePlayer) {

				if (i < 400) {
					this.move(-2.0, 0.0);
					reverbotLeft();
				}
				if (i > 400) {
					this.move(2.0, 0.0);
					reverbotRight();
				}
				if (i > 800) {
					i = 0;
				}

			}

			if (isAlpha) {

				alphaLeft();
				if (i < 400) {
					this.move(-0.5, 0.0);

				}
				if (i > 400) {
					this.move(0.5, 0.0);

				}
				if (i > 800) {
					i = 0;

				}
			}

			if (isBeta) {

				betaLeft();
				if (i < 400) {
					this.move(-0.5, 0.0);

				}
				if (i > 400) {
					this.move(0.5, 0.0);

				}
				if (i > 800) {
					i = 0;
				}
			}
		}
	}

	/**
	 * Methods that handle Sprite updates depending on enemy direction
	 */

	private void goombaLeft() {
		this.setImage("T_Goomba.gif");
	}

	private void alphaLeft() {
		this.setImage("T_Alpha.gif");
	}

	private void betaLeft() {
		this.setImage("T_Beta.gif");
	}

	public void reverbotLeft() {
		this.setImage("T_ReverbotMoveLeft.gif");
	}

	public void reverbotRight() {
		this.setImage("T_ReverbotMoveRight.gif");
	}
}
