package com.zapdos.player;

import java.util.ArrayList;

import com.zapdos.environmentobjects.MovablePlatforms;
import com.zapdos.main.MainApplication;
import com.zapdos.main.Sprite;
import com.zapdos.stage.BaseStage2;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Player {
	private static double SCALE = MainApplication.SCALE;
	private static final int WINDOW_WIDTH = MainApplication.WINDOW_WIDTH;
	private static final int WINDOW_HEIGHT = MainApplication.WINDOW_HEIGHT;
	private static final String IMG_FOLDER = "player/";
	private static final double GRAVITY = 1.01;
	private static final double MAX_PLAYER_SPEED = 10;
	private static final double MIN_PLAYER_SLOWDOWN = -0.75;
	private static final double MAX_JUMP = -25;
	
	private static final String STAND = "Stand.png";
	private static final String RUN = "Run.gif";
	private static final String JUMP = "Jump.gif";
	private static final String FALL = "Fall.png";
	private static final String SHOOT = "Shoot.png";
	private static final String LEFT = "Left";
	private static final String RIGHT = "Right";


	private String character = "P_MM";
	private String direction = "Right";
	private String state = "Stand.png";
	private boolean isDead = false;
	
	private double xCoord;
	private double yCoord;
	private double xSpeed = 0.0;
	private double ySpeed = 0.0;
	
	private Boolean isMoveLeft = false;
	private Boolean isMoveRight = false;
	private Boolean isMoveValid = false;
	private Boolean isMove = false;
	private Boolean isJump = false;
	private Boolean isFall = false;
	private Boolean isShoot = false;
	private Boolean isVertCollision = false;
	private Boolean isHorizCollision = false;
	
	private Boolean projActive = false;
	private Boolean projLeft = false;
	private Boolean projRight = false;
	private Boolean canShoot = true;
	private Projectile basicShot;
	private Boolean isPrint = false;
	
	private Sprite player;
	private GRectangle playerHitbox;

	private ArrayList <Sprite> Environment;
	private ArrayList <MovablePlatforms> movablePlatforms;
	private BaseStage2 baseStage2;
	
	private int playerHealth;
	
	public Player(BaseStage2 baseStage, ArrayList <Sprite> Environment, ArrayList <MovablePlatforms> movablePlatforms, double xCoord, double yCoord) {
		super();
		this.baseStage2 = baseStage;
		player = new Sprite(IMG_FOLDER + character + direction + state, xCoord, yCoord, SCALE);
		this.xCoord = player.getX();
		this.yCoord = player.getY();

		playerHitbox = player.getBounds();
		
		basicShot = new Projectile("T_projectileR.gif", player.getX(), player.getY());
		
		this.Environment = Environment;
		this.movablePlatforms = movablePlatforms;
	}
	
	
	public void handlePlayerMovement() {
	
		print("\nMOVEMENT");
		print("1) Assigning boolean, ycoord " + player.getY() + " " + yCoord);
		
		
		isMoveValid = !(isMoveLeft && isMoveRight);  // Edge Case: check if player does not press left and right button at same time
		isMove = isMoveValid && (isMoveLeft && !isMoveRight) || (!isMoveLeft && isMoveRight);  // check that user presses only left or only right button
		
		print("2) isValidMove: " + isMoveValid + " isMove: " + isMove + " moveLeft " + isMoveLeft + " moveRight " + isMoveRight); 
		
		// Slow down player if user releases A and D keys
		if (!isMove) {
			print("3.0) xspeed 0.8 and STAND " + xSpeed); 
			xSpeed *= 0.8;
			state = STAND;
			print("3.1) new xSpeed " + xSpeed); 
		}
		
	
		// Move left if player only presses A
		else if (isMoveLeft) {
			print("4.0) RUN LEFT PASS xspeed " + xSpeed); 
			xSpeed--;
			direction = LEFT;
			state = RUN;
			print("4.1) new RUN LEFT xspeed " + xSpeed); 
		}
				
		// Move right if player only presses D
		else if (isMoveRight) {
			print("4.0 RUN RIGHT pass, xspeed " + xSpeed);
			xSpeed++;
			direction = RIGHT;
			state = RUN;
			print("4.1 new RUN RIGHT xspeed " + xSpeed);
		}
		
		// Slowing xSpeed to 0 to prevent player from sliding
		if (xSpeed > 0 && xSpeed < MIN_PLAYER_SLOWDOWN || xSpeed < 0 && xSpeed > -MIN_PLAYER_SLOWDOWN) {
			print("5.1 slowing xspeed to 0 to prevent player from sliding");
			xSpeed = 0;
			state = STAND;
		}
		
		
		print("6) Checking MAX xpseed");
		// Set maximum player speed
		if (xSpeed > MAX_PLAYER_SPEED) {
			print("6) SET MAX RIGHT POS xpseed " + MAX_PLAYER_SPEED);
			xSpeed = MAX_PLAYER_SPEED ;
		}
		if (xSpeed < -MAX_PLAYER_SPEED) {
			print("6) SET LEFT NEG xpseed " + -MAX_PLAYER_SPEED);
			xSpeed = -MAX_PLAYER_SPEED;
		}
	}
	
	
	// Handles jump logic when user presses the jump button
	// Player should not be able to jump and press left and right button at same time
	public void handlePlayerJump() {
		print("\nJUMP");
		print("7) isJump " + isJump + " validMove " + isMoveValid);
		
		// Check if user has pressed jump button but has not pressed A and D at the same time
		if (isJump && isMoveValid) {
			isJump = false;
			print("6.1 	Check if player can jump");			
			// Check if a pixel below player's location intersects with environment object
			playerHitbox = player.getBounds();
			playerHitbox.setLocation(playerHitbox.getX(), playerHitbox.getY() + 1);
							
			// PLayer can jump only once when player is on a ground
			for (Sprite envObj: Environment) {
				
				if(envObj.getBounds().intersects(playerHitbox)) {
					ySpeed = MAX_JUMP;
				}
				print("6.2 Player Jumps at ySpeed " + ySpeed);
			}		
			
			for (Sprite envObj: movablePlatforms) {
				
				if(envObj.getBounds().intersects(playerHitbox)) {
					ySpeed = MAX_JUMP;
				}
				print("6.2 Player Jumps at ySpeed " + ySpeed);
			}	
			// Set player hitbox to original location
			playerHitbox.setLocation(playerHitbox.getX(), playerHitbox.getY() - 1);
		}
		
	}
	
	
	// Checks for player collision
	public void handlePlayerLogic() {
		handlePlayerMovement();
		handlePlayerJump();
		isVertCollision = false;
		isHorizCollision = false;
		//isShoot = false;
		
		print("\nCOLLISION");
		print("7) Init yspeed: " + ySpeed);
		// Gravity effect to move player down
		ySpeed += GRAVITY;
		print("7.1) Gravity added yspeed: " + ySpeed);
		
		
		print("8) HORIZONTAL collision");
		playerHitbox = player.getBounds();
		// Check for horizontal collision by moving the hitbox's x by xSpeed which is the player's future location
		
		if (direction == LEFT) playerHitbox.setLocation(playerHitbox.getX() + xSpeed -0.1, playerHitbox.getY());
		else if (direction == RIGHT)playerHitbox.setLocation(playerHitbox.getX() + xSpeed +0.1, playerHitbox.getY());
		
		
		print("8) HORIZONTAL hitboxX " + playerHitbox.getX() + " xspeed " + xSpeed + " hitboxYbot " + (playerHitbox.getY()+playerHitbox.getHeight()));
		for (Sprite envObj: Environment) {
			if(envObj.getBounds().intersects(playerHitbox)) {
				print("8.1) H COLLISION PASS");
				print("8.1.1 ENV OBJ X: " + envObj.getX() + ", Y" + envObj.getY());
				isHorizCollision = true;
				// If hitbox collides with object, return hitbox to original position
				playerHitbox.setLocation(playerHitbox.getX() - xSpeed, playerHitbox.getY());
				
				// Move hitbox as close to obj as possible in right or left direction by 1 pixel until it intersects
				while (!envObj.getBounds().intersects(playerHitbox)) {
					playerHitbox.setLocation(playerHitbox.getX() + Math.signum(xSpeed), playerHitbox.getY());
				}
				
				// Player is now 1 pixel deep in the object so this corrects it
				// Adding 2 pixels to make up difference
				playerHitbox.setLocation(playerHitbox.getX() - Math.signum(xSpeed) - Math.signum(xSpeed), playerHitbox.getY());
				
				xSpeed = 0;  // Player should not move upon collision
				xCoord = playerHitbox.getX(); // Change player's location to hitbox's location
				print("8.2) H COLLISION xspeed 0 xCoord " + xCoord);
				break;
			}
		}
		
		for (Sprite envObj: movablePlatforms) {
			if(envObj.getBounds().intersects(playerHitbox)) {
				print("8.1) H COLLISION PASS");
				print("8.1.1 ENV OBJ X: " + envObj.getX() + ", Y" + envObj.getY());
				isHorizCollision = true;
				// If hitbox collides with object, return hitbox to original position
				playerHitbox.setLocation(playerHitbox.getX() - xSpeed, playerHitbox.getY());
				
				// Move hitbox as close to obj as possible in right or left direction by 1 pixel until it intersects
				while (!envObj.getBounds().intersects(playerHitbox)) {
					playerHitbox.setLocation(playerHitbox.getX() + Math.signum(xSpeed), playerHitbox.getY());
				}
				
				// Player is now 1 pixel deep in the object so this corrects it
				// Adding 2 pixels to make up difference
				playerHitbox.setLocation(playerHitbox.getX() - Math.signum(xSpeed) - Math.signum(xSpeed), playerHitbox.getY());
				
				xSpeed = 0;  // Player should not move upon collision
				xCoord = playerHitbox.getX(); // Change player's location to hitbox's location
				print("8.2) H COLLISION xspeed 0 xCoord " + xCoord);
				break;
			}
		}
		
		print("9) CHECK VERTICAL collision, set isFall true");
		
		// Assume player is falling
		isFall = true;
		
		// Check for vertical collision by moving the hitbox's y by ySpeed which is the player's future location
		playerHitbox.setLocation(xCoord, playerHitbox.getY() + ySpeed);
		GRectangle og_hitbox = playerHitbox;
		for (Sprite envObj: Environment) {
			
			if(envObj.getBounds().intersects(playerHitbox)) {
				print("9.1 VERTICAL COLLISION Pass, isFall set to false");
				print("9.1.1 ENV OBJ X: " + envObj.getX() + ", Y" + envObj.getY());
				isVertCollision = true;
				isFall = false; // Player is not falling if it collides with an environment object
			
				
				// Check player is not colliding with object above
				if (player.getY() + player.getHeight() <= envObj.getY() + envObj.getY()/2) {
					
					// IMPORTANT: Player is always 0.01 pixels above ground object to prevent horizontal collision logic from executing
					playerHitbox.setLocation(playerHitbox.getX(), envObj.getY() - player.getHeight() - 0.1);
					yCoord = playerHitbox.getY(); // Change player's location to hitbox's location
					print("9.2 VERTICAL COLLISION Player is above object");
					ySpeed = 0;
				}
				
				else {
					ySpeed = GRAVITY;
				}
				
				playerHitbox = og_hitbox;
			//	ySpeed = 0;  // Player should not move upon collision
				print("9.3 VERT ySpeed 0 ycoord" + yCoord);

			}
		}
		
		for (Sprite envObj: movablePlatforms) {
			
			if(envObj.getBounds().intersects(playerHitbox)) {
				print("9.1 VERTICAL COLLISION Pass, isFall set to false");
				print("9.1.1 ENV OBJ X: " + envObj.getX() + ", Y" + envObj.getY());
				isVertCollision = true;
				isFall = false; // Player is not falling if it collides with an environment object
			
				
				// Check player is not colliding with object above
				if (player.getY() + player.getHeight() <= envObj.getY() + envObj.getY()/2) {
					
					// IMPORTANT: Player is always 0.01 pixels above ground object to prevent horizontal collision logic from executing
					playerHitbox.setLocation(playerHitbox.getX(), envObj.getY() - player.getHeight() - 0.1);
					yCoord = playerHitbox.getY(); // Change player's location to hitbox's location
					print("9.2 VERTICAL COLLISION Player is above object");
					ySpeed = 0;
				}
				
				else {
					ySpeed = GRAVITY;
				}
				
				playerHitbox = og_hitbox;
			//	ySpeed = 0;  // Player should not move upon collision
				print("9.3 VERT ySpeed 0 ycoord" + yCoord);

			}
		}
		
				
		print("10 isMoveValid check");
		// If player is running towards an object it cannot pass through and user releases move key, make player stands
		if (!isMoveValid) {
			print("10 isMoveValid pass player STAND");
			state = STAND;
		}
		
		if (ySpeed < 0) state = JUMP;
		else if (ySpeed > 0 && ySpeed != GRAVITY) state = FALL;
		
		print("11 FINAL xspeed " + xSpeed + " yspeed " + ySpeed);
		xCoord += xSpeed;
		yCoord += ySpeed;
		print("11 FINAL xCoord "+ xCoord + " yCoord " + yCoord);
		
		print("vert collison " + isVertCollision);
		// Boundary collision
		if (xCoord + player.getWidth() > WINDOW_WIDTH - baseStage2.getRightMargin()) {
			xCoord = WINDOW_WIDTH - baseStage2.getRightMargin() - player.getWidth();
			print("11.1 Right boundary collided. xCoord= " + xCoord);
		}
		else if (xCoord <= baseStage2.getLeftMargin()) {
			xCoord = baseStage2.getLeftMargin();
			print("11.2 Left boundary collided. xCoord= " + xCoord);
		}
		if (!isVertCollision && yCoord + player.getHeight() > WINDOW_HEIGHT - baseStage2.getVertMargin()) {
			yCoord = WINDOW_HEIGHT - baseStage2.getVertMargin() - player.getHeight();
			print("11.3 Bottom boundary collided. yCoord= " + yCoord);
		}
		else if (!isVertCollision && yCoord < baseStage2.getVertMargin()) {
			yCoord = baseStage2.getVertMargin();
			print("11.3 Top boundary collided. yCoord= " + yCoord);
		}
		
		
		if (isShoot) state = SHOOT;
		
		print("12 FINAL BOUNDARY xCoord " + xCoord + "yCoord  " + yCoord);
		updatePlayerLocation();
	}
	
	public void shootUpdate() {
		if (projActive) {
			if (projRight) {
				basicShot.move(20.0, 0.0);
				basicShot.setImage("T_projectileR.gif");

			} else if (projLeft) {
				basicShot.move(-20.0, 0.0);
				basicShot.setImage("T_projectileL.gif");

			}
			if (basicShot.getX() > 1600 - basicShot.getWidth() || basicShot.getX() < 0) {
				projRight = false;
				projLeft = false;
				projActive = false;
				canShoot = true;
				basicShot.setLocation(0.0, 0.0);
				baseStage2.remove(basicShot.getImg());	
			}
		}
		if (!projActive) {
			basicShot.setLocation(0.0, 0.0);
		}
	}

	public void shoot() {
		if (canShoot) {
			basicShot.setLocation(player.getX(), player.getY());
			baseStage2.add(basicShot.getImg());
			canShoot = false;
			if (direction == RIGHT) {
				projRight = true;
				projLeft = false;
			} else if (direction == LEFT) {
				basicShot.setLocation(player.getX(), player.getY());
				projLeft = true;
				projRight = false;
			}
			projActive = true;
		}
	}
	
	private void print(String str) {
		if (isPrint) System.out.println(str);
	}
	
	private void updatePlayerLocation() {
		player.setImage(IMG_FOLDER + character + direction + state);
	    player.setLocation(xCoord, yCoord);
		playerHitbox = player.getBounds();
	}
	
	public void updatePlayerImg() {
		player.setImage(IMG_FOLDER + character + direction + state);
	}
	
	public void setMoveLeft(boolean isMoveLeft) {
		this.isMoveLeft = isMoveLeft;
	}
	
	public void setMoveRight(boolean isMoveRight) {
		this.isMoveRight = isMoveRight;
	}
	
	public void setIsJump(boolean isJump) {
		this.isJump = isJump;
	}
	
	public void setIsShoot() {
		isShoot = true;
	}
	
	
	public void reset() {
		ySpeed = 0.0;
		isMoveLeft = false;
		isMoveRight = false;
		isMoveValid = false;
		isMove = false;
		isJump = false;
		isFall = false;
		state = STAND;
		direction = RIGHT;
		player.setImage(IMG_FOLDER + character + direction + state);
	}
	
	public boolean getIsMove() {
		return isMove;
	}
	
	public boolean getIsMoveLeft() {
		return isMoveLeft;
	}
	
	public boolean getIsMoveRight() {
		return isMoveRight;
	}
	
	public boolean getIsJump() {
		return isJump;
	}
	
	public boolean getIsFall() {
		return isFall;
	}
	
	public double getXSpeed() {
		return xSpeed;
	}
	
	public double getYSpeed() {
		return ySpeed;
	}
	
	public GImage getBasicShot() {
		return basicShot.getImg();
	}
	
	public boolean isHorizCollision() {
		return isHorizCollision;
	}
	
	public void setLocation(double x, double y) {
		player.setLocation(x, y);
		xCoord = x;
		yCoord = y;
	}
	
	public GImage getPlayerImg() {
		return player.getImg();
	}
	
	public Sprite getPlayerSprite() {
		return player;
	}
	
	public String getDirection() {
		return direction;
	}
}
