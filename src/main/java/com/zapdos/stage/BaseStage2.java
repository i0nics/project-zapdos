package com.zapdos.stage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

import com.zapdos.audio.MusicPlayer;
import com.zapdos.audio.SFXPlayer;
import com.zapdos.enemies.Enemy;
import com.zapdos.environmentobjects.Button;
import com.zapdos.environmentobjects.Door;
import com.zapdos.environmentobjects.MovablePlatforms;
import com.zapdos.environmentobjects.Spike;
import com.zapdos.main.MainApplication;
import com.zapdos.main.Sprite;
import com.zapdos.panes.StageSelect;
import com.zapdos.player.Player;
import com.zapdos.starter.GraphicsPane;
import com.zapdos.puzzles.*;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;


public class BaseStage2 extends GraphicsPane implements ActionListener {

	private static final int WINDOW_WIDTH = MainApplication.WINDOW_WIDTH;
	private static final int WINDOW_HEIGHT = MainApplication.WINDOW_HEIGHT;
	private static final double LEFT_BOUNDARY_LIMIT = 0.0;
	private static final double RIGHT_BOUNDARY_LIMIT = 9000.0;
	private static final double SCALE = MainApplication.SCALE;
	
	private static final double DEFAULT_X_COORD = 0.0;
	private static final double DEFAULT_BG_Y = -7600.0;
	private static final double RIGHT_MARGIN = WINDOW_WIDTH / 2.6;
	private static final double LEFT_MARGIN = WINDOW_WIDTH * 0.4;
	private static final double VERTICAL_MARGIN = WINDOW_HEIGHT * 0.2;
	private static final double DEFAULT_PlAYER_X_COORD = LEFT_MARGIN;
	private static final double DEFAULT_PlAYER_Y_COORD = 530;
	
	private static final int MS = 17;
	private boolean isTestMode;
	private MainApplication program;
	private int lastX;
	private int lastY;
	private int jumpKeyPressCounter = 0;
	
	private MusicPlayer musicPlayer;
	private SFXPlayer sfxPlayer;

	public Sprite backgroundSprite = new Sprite("T_background_robohub.jpg", DEFAULT_X_COORD, DEFAULT_BG_Y, SCALE);
	private Timer paneTimer = new Timer(MS, this);

	private ArrayList<Sprite> platforms = new ArrayList<Sprite>();
	private ArrayList<Sprite> items = new ArrayList<Sprite>();
	private ArrayList<Door> doors  = new ArrayList<Door>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Sprite> puzzleElems; // On showContents, retrieves the sprite list from the loaded puzzle
	private ArrayList<Spike> spikes = new ArrayList<Spike>();
	private ArrayList<MovablePlatforms> movablePlatforms = new ArrayList<MovablePlatforms>();

	// STAGE STUFF
	private Player player = new Player(this, platforms, movablePlatforms, DEFAULT_PlAYER_X_COORD, DEFAULT_PlAYER_Y_COORD);
	private Sprite ceiling = new Sprite("T_Floor.png", 0.0, -550.0, SCALE);

	public Boolean stageCondition = false; // Condition that'll allow the player to progress to the next stage
	public Boolean collectedKey = false;

	// Stage Five
	public Sprite spikesLong = new Sprite("T_Spikes_Long.png", 1600.0, 700.0, SCALE);
	public Sprite spikesLong2 = new Sprite("T_Spikes_Long.png", 3600.0, 700.0, SCALE);
	public Sprite diamond1 = new Sprite("T_Diamond.gif", 1500.0, -300.0, SCALE);
	public Sprite diamond2 = new Sprite("T_Diamond.gif", 2500.0, 300.0, SCALE);
	public GLabel diamondLabel = new GLabel("Shine like a diamond", 0, 300);
	public int stageDCounter = 0;

	// PUZZLE THINGS
	private int puzzleNum = 0; // To be changed later.

	private Puzzle1 puzzle1;
	private Puzzle2 puzzle2;
	private Puzzle3 puzzle3;
	private Puzzle4 puzzle4;
	
	private Boolean resetPuzzle = false; //manually resetting to address a bug w/ puzzle logic remaining solved for all puzzles

	private double xCoord;
	private double yCoord;
	private GObject obj;

	private Double moveX = 0.0;
	private Double moveY = 0.0;
	private Double backgroundViewX = 0.0;
	private Double backgroundViewY = 0.0;
	private double rightBoundary;
	private double leftBoundary;
	private double bottomBoundary;
	private double topBoundary;

	private boolean isDevMode = false;
	private double viewX = 0.0;
	private double viewY = 0.0;

	private int stageNum;

	private StageCreator stageCreator;
	private Sprite backButton;
	private static final double BACK_BUTTON_X = 20.0;
	private static final double BACK_BUTTON_Y = 50.0;

	public BaseStage2(MainApplication app, Boolean isTestMode) {
		super();
		program = app;
		
		musicPlayer = program.getMusicPlayer();
		sfxPlayer = program.getSFXPlayer();
		
		isDevMode = true;
		stageNum = 0;
		stageCreator = new StageCreator(this);

		this.isTestMode = isTestMode;
		puzzleNum = stageNum;
		updatePuzzleElements();
		// player = new Player(this, platforms, DEFAULT_PlAYER_X_COORD,
		// DEFAULT_PlAYER_Y_COORD);
		backButton = new Sprite("T_Menu_Back_Button.png", BACK_BUTTON_X, BACK_BUTTON_Y, SCALE/2.5);

		// avoid calling program methods in test mode
		if (!isTestMode) {
			// Loads default stage
			stageCreator.loadStage(0);
		}
	}

	private void initLabel(GLabel label) { // Method that handles initializing GLabels
		label.setFont(Font.SANS_SERIF);
		label.setColor(Color.yellow);
		label.scale(2);
		label.setVisible(true);
		label.sendToFront();
	}

	// determines when to scroll environment objects
	private void scroll() {
		Sprite playerSprite = player.getPlayerSprite();

		moveX = 0.0;
		moveY = 0.0;
	//System.out.println(viewX);
		// Scroll environment left when player is moving right
		rightBoundary = WINDOW_WIDTH - RIGHT_MARGIN;
		if (playerSprite.getRight() >= rightBoundary && player.getIsMove() && !player.getIsMoveLeft()
				&& viewX < RIGHT_BOUNDARY_LIMIT) {
			moveX = -player.getXSpeed();
		}

		// Scroll environment right when player is moving left
		leftBoundary = LEFT_MARGIN;
		if (playerSprite.getLeft() <= leftBoundary && player.getIsMove() && !player.getIsMoveRight()
				&& viewX > LEFT_BOUNDARY_LIMIT) {
			moveX = -player.getXSpeed();
		}

		// Scroll environment up when player is falling down
		bottomBoundary = WINDOW_HEIGHT - VERTICAL_MARGIN;
		if (playerSprite.getBottom() >= bottomBoundary)
			moveY = -player.getYSpeed();

		// Scroll environment down when player is moving up
		topBoundary = VERTICAL_MARGIN;
		if (playerSprite.getTop() <= topBoundary)
			moveY = -player.getYSpeed();

		moveEnvironment();
	}

	// Moves specific env object
	private void moveEnvObject(ArrayList<Sprite> envObjList) {
		for (Sprite f : envObjList) {
			f.move(moveX, moveY);
		}
	}

	// moves all environment objects
	private void moveEnvironment() {
		// set moveX to 0 if viewX-moveX < 0
		if (viewX - moveX < 0) {
			moveX =  viewX;
		}
		viewX -= moveX;
		viewY -= moveY;
		
		backgroundViewX = moveX;
		backgroundViewY = moveY;
		if (backgroundViewX != 0) backgroundViewX -= backgroundViewX/1.15;
		if (backgroundViewY != 0) backgroundViewY -= backgroundViewY/1.15;

		backgroundSprite.move(backgroundViewX, backgroundViewY);
		// Stage 5 objects =
		spikesLong.move(moveX, moveY);
		spikesLong2.move(moveX, moveY);
		diamond1.move(moveX, moveY);
		diamond2.move(moveX, moveY);
		diamondLabel.move(moveX, moveY);
		
		// ===================
		moveEnvObject(platforms);
		moveEnvObject(items);
	
		if (puzzleElems != null) moveEnvObject(puzzleElems);
		for (Button button: buttons) button.move(moveX, moveY);
		for (Spike spike: spikes) spike.move(moveX, moveY);
		for (Door door: doors) door.move(moveX, moveY);
		for (Enemy enemy: enemies) enemy.move(moveX, moveY);
		for (MovablePlatforms mvp: movablePlatforms) mvp.move(moveX, moveY);
	}
	
	public void reset() {
		viewX = 0.0;
		viewY = 0.0;
		platforms.clear();
		enemies.clear();
		buttons.clear();
		items.clear();
		doors.clear();
		spikes.clear();
		movablePlatforms.clear();
		stageCreator.loadStage(stageNum);
		puzzleNum = stageNum;
		player.reset();
		player.setLocation(DEFAULT_PlAYER_X_COORD, DEFAULT_PlAYER_Y_COORD);
		backgroundSprite.setLocation(DEFAULT_X_COORD, DEFAULT_BG_Y);
		updatePuzzleElements();
		
		if(stageNum == 1) {
			puzzle1.setNotSolved();
		}
		if(stageNum == 3) {
			player.setLocation(200, 630.0);
		}
		if(stageNum == 6) {
			Sprite key = new Sprite("T_Key.png", 3546.0, -445.0, 1.0);
			items.add(key);
		}
		if(stageNum == 5) {
			Sprite key = new Sprite("T_Key.png", 700.0, -402.0, 1.0);
			items.add(key);
		}
	}

	private void pickupItem() { // Method that handles item pickup events

		for (Sprite item : items) {
			if (player.getPlayerSprite().getBounds().intersects(item.getBounds())) {
				program.remove(item.getImg());
				collectedKey = true; // placeholder stage pass condition
				removeBarrier();
				stageCondition = true;
				for (Door d : doors) {
					d.isOpen = true;
					d.setDoorOpen();
				}
			}
		}
	}
	
	private void removeBarrier() {
		for (Sprite p : platforms) {
			if (p.getFileName().equals("sprites/T_Platform_Barrier.png")) {
				program.remove(p.getImg());
				platforms.remove(p);
				break;
			}
		}
	}

	private void spikeCollision() { // Method that handles item pickup events

		for (Sprite spikes : spikes) {
			if (player.getPlayerSprite().getBounds().intersects(spikes.getBounds())) {
				hideContents();
				showContents();
				break;
			}
		}
	}

	private void pressButton() { // Method that handles button pressing; NOTE: buttons are disabled in DevMode!

		for (Button button : buttons) {
			if (player.getPlayerSprite().getBounds().intersects(button.getBounds())) {
				buttonLogic(button);
			}
		}
	}
	

	// 60 calls per second
	@Override
	public void actionPerformed(ActionEvent e) {
		
		scroll();
		player.handlePlayerLogic();
		
		
		for(MovablePlatforms mv: movablePlatforms) {
			mv.movePlatformVert();
			mv.movePlatformHor();
		}

		
		if (!isDevMode) {
			pickupItem();
			player.shootUpdate();
			spikeCollision();
			
			for (Enemy enemy : enemies) {
				enemy.updateEnemy();
				if (player.getPlayerImg().getBounds().intersects(enemy.getBounds())) {
					hideContents();
					showContents();
					break;
				}
				
				if (player.getBasicShot().getBounds().intersects(enemy.getBounds())) {
					enemy.dead();
					program.remove(enemy.getImg());
					program.remove(enemy.getSightRec());
					
					//program.remove(player.getBasicShot());
					enemy.canPlaySound = false;
					enemies.remove(enemy);
					break;
				}
				
			}
			
			if (stageNum == 1 && puzzle1.isSolved() == true) {
				for(Door door : doors) {
					door.isOpen = true;
					door.setDoorOpen();
					if (puzzle1.isSolved() == true && player.getPlayerSprite().getBounds().intersects(door.getBounds())) {
						program.setStageOneComplete();
						setStageNum(stageNum+1);
						hideContents();
						showContents();
						puzzle1.setNotSolved();
						
					}
				}
			}
			if (stageNum == 2 && puzzle2.isSolved() == true) {
				
				for(Door door : doors) {
					door.isOpen = true;
					door.setDoorOpen();
					if (puzzle2.isSolved() == true && player.getPlayerSprite().getBounds().intersects(door.getBounds())) {
						program.setStageTwoComplete();
						setStageNum(stageNum+1);
						hideContents();
						showContents();
						puzzle2.setNotSolved();
					}
				}
			}
			if (stageNum == 3 && puzzle3.isSolved() == true) {
				
				for(Door door : doors) {
					door.isOpen = true;
					door.setDoorOpen();
					if (puzzle3.isSolved() == true && player.getPlayerSprite().getBounds().intersects(door.getBounds())) {
						program.setStageThreeComplete();
						setStageNum(stageNum+1);
						hideContents();
						showContents();
						puzzle3.setNotSolved();
					}
				}
			}
			
			if (stageNum == 4 && puzzle4.isSolved() == true) {
				
				for(Door door : doors) {
					door.isOpen = true;
					door.setDoorOpen();
					if (puzzle4.isSolved() == true && player.getPlayerSprite().getBounds().intersects(door.getBounds())) {
						program.setStageFourComplete();
						setStageNum(stageNum+1);
						hideContents();
						showContents();
						puzzle4.setNotSolved();
					}
				}
			}
			
			if (stageNum == 5) {
				for(Door door : doors) {
					if (door.isOpen && player.getPlayerSprite().getBounds().intersects(door.getBounds())) {
						program.setStageFiveComplete();
						setStageNum(stageNum+1);
						hideContents();
						showContents();
						break;
					}
				}
			}
			
			if (stageNum == 6) {
				for(Door door : doors) {
					if (door.isOpen && player.getPlayerSprite().getBounds().intersects(door.getBounds())) {
						program.setStageSixComplete();
						//setStageNum(stageNum+1);
						hideContents();
						program.switchToCredits();
						break;
					}
				}
			}
			
			// Stage 5 diamonds
			if (player.getPlayerSprite().getBounds().intersects(diamond1.getBounds())) {
				program.remove(diamond1.getImg());
				stageDCounter = stageDCounter + 1;

			}

			if (player.getPlayerSprite().getBounds().intersects(diamond2.getBounds())) {
				program.remove(diamond2.getImg());
				stageDCounter = stageDCounter + 1;
			}
			
			

//			if (stageDCounter >= 2) {
//				doorStage5.setImage("T_DoorOpen.png");
//			}
			
			

//			if (player.getPlayerSprite().getBounds().intersects(doorStage5.getBounds()) && stageDCounter >= 2) {
//				unloadStageFive();
//				// stageNum = 2; showContents();
//				program.switchToStageSelect();
//			}
//			
//			if (player.getPlayerSprite().getBounds().intersects(newDoor.getBounds()) && puzzle1.isSolved() == true && !isDevMode) {
//				program.switchToStageSelect();
//			}
		}
		

		// Death logic test
//		if (player.getPlayerSprite().getBounds().intersects(spikesLong.getBounds())
//				|| player.getPlayerSprite().getBounds().intersects(spikesLong2.getBounds())) {
//			player.getPlayerSprite().setLocation(DEFAULT_PlAYER_X_COORD - viewX, DEFAULT_PlAYER_Y_COORD - viewY);
//			player.getPlayerImg().setLocation(DEFAULT_PlAYER_X_COORD - viewX, DEFAULT_PlAYER_Y_COORD - viewY);
//			player.getPlayerSprite().getImg().setLocation(DEFAULT_PlAYER_X_COORD - viewX,
//					DEFAULT_PlAYER_Y_COORD - viewY);
//			// reset();
//
//		}
	}

	public void loadStageFiveAssets() {
		program.add(spikesLong.getImg());
		program.add(spikesLong2.getImg());
		program.add(diamond1.getImg());
		program.add(diamond2.getImg());
		initLabel(diamondLabel);
		player.getPlayerImg().sendToFront();

	}

	private void unloadStageFive() {
		program.remove(spikesLong.getImg());
		program.remove(spikesLong2.getImg());
		program.remove(diamond1.getImg());
		program.remove(diamond2.getImg());
		program.remove(diamondLabel);
	}

	public void mousePressed(MouseEvent e) {

		obj = program.getElementAt(e.getX(), e.getY());
		lastX = e.getX();
		lastY = e.getY();
		
		if (isDevMode) {
			System.out.println("X: " + (viewX + e.getX()) + " Y: " + (viewY + e.getY()));
			System.out.println("\nViewX: " + viewX + " viewY: " + viewY);
			
			System.out.println("Obj X: " + (obj.getX()) + " Y: " + (obj.getY()));
			System.out.println("OG View + Obj X: " + (obj.getX() + viewX) + " Y: " + (obj.getY() + viewY));
			System.out.println("OG View - Obj X: " + (obj.getX() - viewX) + " Y: " + (obj.getY() - viewY));
			System.out.println("Mouse X: " + (e.getX() - viewX) + " Y: " + (e.getY() - viewY));
		}

		if (!isDevMode && puzzleElems != null) {
			for (Sprite s : puzzleElems) {
				if (obj == s.getImg()) {
					clickPuzzleObjectLogic(s);
				}
			}
		}

		if (e.getButton() == MouseEvent.BUTTON3 && isDevMode) {
			program.remove(obj);
			
			for (Sprite platform : platforms) {
				if (platform.getImg() == obj) {
					platforms.remove(platform);
					break;
				}
			}
			
			for (MovablePlatforms platform : movablePlatforms) {
				if (platform.getImg() == obj) {
					movablePlatforms.remove(platform);
					break;
				}
			}
			
			for (Sprite button : buttons) {
				if (button.getImg() == obj) {
					buttons.remove(button);
					break;
				}
			}
			
			for (Sprite enemy : enemies) {
				if (enemy.getImg() == obj) {
					enemies.remove(enemy);
					break;
				}
			}
			
			for (Sprite spike : spikes) {
				if (spike.getImg() == obj) {
					spikes.remove(spike);
					break;
				}
			}
		}
		
		else if (e.getButton() == MouseEvent.BUTTON1 && !isDevMode) {
			player.shoot();
			sfxPlayer.playShootSound();
		}
		
		if(obj == backButton.getImg()) {
			sfxPlayer.playClickSound();
			program.switchToStageSelect();
			musicPlayer.playMenuTrack();
		}
	}

	public void mouseDragged(MouseEvent e) {

		if (obj != null && isDevMode) {
			for (Sprite platform : platforms) {
				if (platform.getImg() == obj) {
					xCoord = e.getX() - lastX;
					yCoord = e.getY() - lastY;

					platform.move(xCoord, yCoord); // Pinpoint Accurate
					lastX = e.getX();
					lastY = e.getY();
				}
			}
			
			for (Sprite button : buttons) {
				if (button.getImg() == obj) {
					xCoord = e.getX() - lastX;
					yCoord = e.getY() - lastY;

					button.move(xCoord, yCoord); // Pinpoint Accurate
					lastX = e.getX();
					lastY = e.getY();
				}
			}
			
			for (Sprite door : doors) {
				if (door.getImg() == obj) {
					xCoord = e.getX() - lastX;
					yCoord = e.getY() - lastY;

					door.move(xCoord, yCoord); // Pinpoint Accurate
					lastX = e.getX();
					lastY = e.getY();
				}
			}
			
			for (Sprite enemy : enemies) {
				if (enemy.getImg() == obj) {
					xCoord = e.getX() - lastX;
					yCoord = e.getY() - lastY;

					enemy.move(xCoord, yCoord); // Pinpoint Accurate
					lastX = e.getX();
					lastY = e.getY();
				}
			}
			
			for (Sprite spike : spikes) {
				if (spike.getImg() == obj) {
					xCoord = e.getX() - lastX;
					yCoord = e.getY() - lastY;

					spike.move(xCoord, yCoord); // Pinpoint Accurate
					lastX = e.getX();
					lastY = e.getY();
				}
			}
			
			for (Sprite mvp : movablePlatforms) {
				if (mvp.getImg() == obj) {
					xCoord = e.getX() - lastX;
					yCoord = e.getY() - lastY;

					mvp.move(xCoord, yCoord); // Pinpoint Accurate
					lastX = e.getX();
					lastY = e.getY();
				}
			}
		}
	}

	// KeyEvents
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') player.setMoveLeft(true);
		
		if (jumpKeyPressCounter == 0 && (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == KeyEvent.VK_SPACE)) {
			jumpKeyPressCounter++;
			sfxPlayer.playJumpSound();
			player.setIsJump(true);
		}
		
		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') player.setMoveRight(true);
		if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
			player.shoot();
			sfxPlayer.playShootSound();
		}
		
		if (e.getKeyChar() == 'f' || e.getKeyChar() == 'F') {
			pressButton();
		}
		
		if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
			for(MovablePlatforms mv: movablePlatforms) {
				if (mv.getLetter() == 'P') {
					if (mv.getIsMovingVert()) {
						mv.setCanMoveVert(true);
					}
					
					else {
						mv.setCanMoveHor(true);
					}
				}
			}
		}
		
		if (e.getKeyChar() == 'g' || e.getKeyChar() == 'G') {
			for(MovablePlatforms mv: movablePlatforms) {
				if (mv.getLetter() == 'G') {
					if (mv.getIsMovingVert()) {
						mv.setCanMoveVert(true);
					}
					
					else {
						mv.setCanMoveHor(true);
					}
				}
			}
		}

		// debug keys to test/swap between stage JSON files
		if (e.getKeyCode() == KeyEvent.VK_1) {
			stageNum = 1;
			showContents();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_2) {
			stageNum = 2;
			showContents();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_3) {
			stageNum = 3;
			showContents();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_4) {
			stageNum = 4;
			showContents();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_5) {
			stageNum = 5;
			showContents();
			//loadStageFiveAssets();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') player.setMoveLeft(false);
		if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.setIsJump(false);
		    jumpKeyPressCounter = 0;
	    }
		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') player.setMoveRight(false);
	}
	
	public String getBackground() {
		return backgroundSprite.getFileName();
	}
	
	public Sprite getBackgroundSprite() {
		return backgroundSprite;
	}

	public double getRightMargin() {
		return RIGHT_MARGIN;
	}

	public double getLeftMargin() {
		return LEFT_MARGIN;
	}
	
	public MusicPlayer getMusicPlayer() {
		return musicPlayer;
	}

	public double getVertMargin() {
		return VERTICAL_MARGIN;
	}

	public double getViewX() {
		return viewX;
	}

	public double getViewY() {
		return viewY;
	}

	public Player getPlayer() {
		return player;
	}

	public StageCreator getStageCreator() {
		return stageCreator;
	}

	public GObject getCurrentSelectedObject() {
		return obj;
	}

	public ArrayList<Sprite> getPlatforms() {
		return platforms;
	}

	public ArrayList<Button> getButtons() {
		return buttons;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Spike> getSpikes() {
		return spikes;
	}
	
	public ArrayList<Door> getDoors() {
		return doors;
	}
	
	public ArrayList<MovablePlatforms> getMovablePlatforms() {
		return movablePlatforms;
	}

	public void setStageNum(int stageNum) {
		this.stageNum = stageNum;
	}

	public void setPuzzleNum(int puzzleNum) {
		this.puzzleNum = puzzleNum;
	}
	
	public void setDevMode(Boolean devMode) {
		this.isDevMode = devMode;
	}
	
	public void setBackground(String backgroundImg) {
		backgroundSprite.setImage(backgroundImg);
	}

	public void updatePuzzleElements() { // Reset the currently loaded puzzle, and puts puzzle sprites into puzzleElems
		if (puzzleElems != null) {
			puzzleElems.clear();
		}

		switch (puzzleNum) {
		case 1:
			puzzle1 = new Puzzle1();
			puzzleElems = puzzle1.getSprites();
			break;
		case 2:
			puzzle2 = new Puzzle2();
			puzzleElems = puzzle2.getSprites();
			break;
		case 3:
			puzzle3 = new Puzzle3();
			puzzleElems = puzzle3.getSprites();
			break;
		case 4:
			puzzle4 = new Puzzle4();
			puzzleElems = puzzle4.getSprites();
			break;
		}
	}

	public void buttonLogic(Button b) { // Given a specific button, executes the correct puzzle functions. This part is
		// written manually.
		switch (puzzleNum) {
		case 1:
			if (!puzzle1.isSolved()) {
				switch (b.getName()) {
				case "button0":
					puzzle1.pressButtonL();
					break;
				case "button1":
					puzzle1.pressButton1();
					break;
				case "button2":
					puzzle1.pressButton2();
					break;
				}
			}
			break;
		case 2:
			if (!puzzle2.isSolved()) {
				switch (b.getName()) {
				case "button0":
					puzzle2.pressButtonX();
					break;
				case "button1":
					puzzle2.pressButton1();
					break;
				case "button2":
					puzzle2.pressButton2();
					break;
				}
			}
			break;
		case 3:
			if (!puzzle3.isSolved()) {
				switch (b.getName()) {
				case "button0":
					puzzle3.pressButtonL();
					break;
				case "button1":
					puzzle3.pressButton1();
					break;
				case "button2":
					puzzle3.pressButton2();
					break;
				case "button3":
					puzzle3.pressButton3();
					break;
				}
			}
			break;
		case 4:
			switch (b.getName()) {
			case "button0":
				puzzle4.pressButton("Red");
				break;
			case "button1":
				puzzle4.pressButton("Blue");
				break;
			case "button2":
				puzzle4.pressButton("Yellow");
				break;
			case "button3":
				puzzle4.pressButton("Green");
				break;
			}
			break;
		}
	}

	public void clickPuzzleObjectLogic(Sprite s) { // Given a specific puzzle object,
		if (s.getFileName() == "puzzle_assets/T_Reset_Button.png") {
			switch (puzzleNum) {
			case 1:
				if (!puzzle1.isSolved()) {
					puzzle1.resetPuzzle();
				}
				break;
			case 2:
				if (!puzzle2.isSolved()) {
					puzzle2.resetPuzzle();
				}
				break;
			case 3:
				if (!puzzle3.isSolved()) {
					puzzle3.resetPuzzle();
				}
				break;
			case 4:
				if (!puzzle4.isSolved()) {
					puzzle4.resetPuzzle();
				}
				break;
			}
		}
	}


	@Override
	public void showContents() {
		reset();

		program.add(backgroundSprite.getImg());
		///program.add(newDoor.getImg());
		// program.add(ceiling.getImg());
		for (Sprite platform : platforms) program.add(platform.getImg());
		for (MovablePlatforms mp : movablePlatforms) program.add(mp.getImg());
		for (Sprite item : items) program.add(item.getImg());
		for (Sprite door : doors) program.add(door.getImg());
		for (Enemy enemy : enemies) program.add(enemy.getImg());
		for (Sprite spike : spikes) program.add(spike.getImg());
		for (Sprite button : buttons) program.add(button.getImg());
		

		if (puzzleElems != null) {
			for (Sprite e : puzzleElems) {
				program.add(e.getImg());
			}
		}
		
		program.add(backButton.getImg());
		program.add(player.getPlayerImg());
		paneTimer.start();
		
	}

	@Override
	public void hideContents() {
		paneTimer.stop();
		program.removeAll();
	}
	
	public void remove(GImage img) {
		program.remove(img);
	}
	
	public void add(GImage img) {
		program.add(img);
	}

	@SuppressWarnings("unused")
	private void print(String str) {
		System.out.println(str);
	}
}
