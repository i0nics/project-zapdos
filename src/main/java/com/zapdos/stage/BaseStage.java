//package com.zapdos.stage;
//
//import java.awt.AWTException;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Rectangle;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//
//import com.zapdos.main.Documentation;
//import com.zapdos.enemies.Enemy;
//import com.zapdos.enemies.Enemy_Alpha;
//import com.zapdos.enemies.Enemy_Beta;
//import com.zapdos.enemies.Enemy_Reverbot;
//import com.zapdos.main.Enviro_Door;
//import com.zapdos.main.Enviro_Platform;
//import com.zapdos.main.Enviro_Spikes;
//import com.zapdos.starter.GraphicsPane;
//import com.zapdos.main.Inventory;
//import com.zapdos.main.Item;
//import com.zapdos.main.MainApplication;
//
//import java.awt.event.*;
//
//import acm.graphics.GImage;
//import acm.graphics.GLabel;
//import acm.graphics.GObject;
//import acm.graphics.GPoint;
//import acm.graphics.GRect;
//import acm.program.GraphicsProgram;
//import acm.program.Program;
//import acm.program.ProgramMenuBar;
//import acm.graphics.GLine;
//
//public class BaseStage extends GraphicsPane implements ActionListener {
//	
//	private MainApplication program; 
//	//private Documentation doc = new Documentation();
//	private int lastX;
//	private int lastY;
//	double xVal; 
//	double yVal;
//	private double RIGHT_SCROLL_SPEED = 1.5;
//	private double LEFT_SCROLL_SPEED = -1.5;
//	private double JUMP_SPEED = -2.5;
//	private double FALL_SPEED = 2.5;
//
//	public GLine gridX = new GLine (0,0,1600,0);
//	public GLine gridY = new GLine (0,0,0,900);
//	public String x = "X";
//	public String y = "Y";
//	public GLabel elemXCoord = new GLabel (x, 500, 100);
//	public GLabel elemYCoord = new GLabel (y, 800, 100); 
//	public GLabel addPlatform = new GLabel ("ADDPLATFORM", 1050, 100);
//	public GImage ambOne = new GImage("T_ambOne.png", 1000, 0); 
//	public GImage ambTwo = new GImage("T_ambTwo.png", 1900, 0);
//	public GImage ambThree = new GImage("T_ambThree.png", 600, 0);
//	
//	Enviro_Door newDoor = new Enviro_Door("T_DoorClosed.png", 7000, 334, false);	// Door for this stage
//	public GObject currentObject;													// Stores which object is currently in focus
//	private Inventory inventory = new Inventory();									// Testing Inventory System
//
//	public GImage bg = new GImage("T_bg.png", 0, 0);
//	public GImage floor = new GImage("T_Floor.png", 0, 646);
//	public GRect Player = new GRect(750, 500, 100, 100);
//	public GRect mapBounds = new GRect (0,0,12860, 900);
//	Timer paneTimer = new Timer(1, this);
//	public Boolean movingLeft = false;
//	public Boolean movingRight = false;
//	public Boolean jumping = false;
//	public Boolean falling = false;
//	public Boolean Idle = true;;
//	
//	/**
//	 * Generate the Data Structures that handle all scene and gui objects
//	 * 
//	 */
//	
//	private ArrayList<GObject> bgElements = new ArrayList<GObject>();
//	private ArrayList<GObject> devLabels = new ArrayList<GObject>();
//	private ArrayList<GObject> platforms = new ArrayList<GObject>();
//	private ArrayList<GObject> spikes = new ArrayList<GObject>();
//	private ArrayList<GObject> items = new ArrayList<GObject>();
//	private ArrayList<GObject> enemies = new ArrayList<GObject>();
//	private ArrayList<GObject> doors = new ArrayList<GObject>();
//
//	
//	/**
//	 * Initialize all JMenu objects
//	 * 
//	 */
//	
//	JMenuBar menuBar = new JMenuBar();
//	JMenu file = new JMenu("File");
//	JMenu mNew = new JMenu("New");
//	JMenu edit = new JMenu("Edit");
//	JMenu loadPane = new JMenu("Load Pane");
//	JMenu addObject = new JMenu("Add Object");
//	JMenu Rotate = new JMenu("Rotate Object");
//	JMenu Options = new JMenu("Options");
//	
//	JMenuItem fNewPane = new JMenuItem("Graphics Pane");
//	JMenuItem fSave = new JMenuItem("Save");
//	JMenuItem fImport = new JMenuItem("Import");
//	JMenuItem fExport = new JMenuItem("Export");
//	JMenuItem fPrint = new JMenuItem("Print");
//	JMenuItem Exit = new JMenuItem("Exit");
//	
//	JMenuItem eCopy = new JMenuItem("Copy");
//	JMenuItem ePaste = new JMenuItem("Paste");
//	JMenuItem eCut = new JMenuItem("Cut");
//	JMenuItem eUndo = new JMenuItem("Undo");
//	JMenuItem eRedo = new JMenuItem("Redo");
//	
//	JMenuItem loadMainMenu = new JMenuItem("Main Menu");
//	JMenuItem loadStageSelect = new JMenuItem("Stage Select Menu");
//	JMenuItem loadBaseStage = new JMenuItem("Base Stage");
//	JMenuItem loadBaseStage2 = new JMenuItem("Base Stage 2");
//	JMenuItem loadPuzzle1 = new JMenuItem("Puzzle 1");
//
//	JMenuItem addPlat = new JMenuItem("Platform");
//	JMenuItem addSpike = new JMenuItem("Spikes");
//	JMenuItem addHealth = new JMenuItem("Health");
//	JMenuItem addEnergy = new JMenuItem("Energy");
//	JMenuItem addKey = new JMenuItem("Key");
//	JMenuItem addDoor = new JMenuItem("Door");
//
//	
//	JMenu tools = new JMenu("Tools");
//	JMenuItem docs = new JMenuItem("Docs");
//	
//	JMenu addEnemy = new JMenu("Enemy");
//	JMenuItem addReverbot = new JMenuItem("Reverbot");
//	JMenuItem addAlpha = new JMenuItem("Alpha");
//	JMenuItem addBeta = new JMenuItem("Beta");
//	JMenuItem addGoomba = new JMenuItem("Goomba");
//	
//	JMenuItem Rotate90 = new JMenuItem("Rotate 90 Degrees");
//	JMenuItem Rotate90n = new JMenuItem("Rotate -90 Degrees");
//	JMenuItem flipVertical = new JMenuItem("Flip Vertical");
//	JMenuItem flipHorizontal = new JMenuItem("Flip Horizontal");
//	
//	JMenuItem Resolution = new JMenuItem("Resolution");
//	
//	/**
//	 * Global variables that handle stage conditions 
//	 * 
//	 */
//
//	public Boolean stageCondition = false;								// Condition that'll allow the player to progress to the next stage
//	public Boolean collectedKey = false;								// This could be the stage condition or an additional condition
//
//
//	
//	public BaseStage(MainApplication app) {
//		super();
//		program = app;
//		paneTimer.start();
//		
//		
//		initLabel(elemXCoord);
//		initLabel(elemYCoord);
//		initLabel(addPlatform);
//		Collections.addAll(bgElements, bg, ambOne, ambTwo, ambThree, floor, elemXCoord, elemYCoord);
//		//Collections.addAll(doors, newDoor);
//
//		Player.setFillColor(Color.red);
//		Player.setFilled(true);
//		program.add(gridX);
//		showContents();
//		
//	 /**
//	 * Create the menu bar and add all JMenu Items
//	 * 
//	 * TODO: JMenu.adds should be stored within some data structure to streamline 
//	 * method actions to act upon the structure. 
//	 * 
//	 */
//	
//		menuBar.add(file);
//		menuBar.add(edit);
//		menuBar.add(loadPane);
//		menuBar.add(addObject);
//		menuBar.add(Rotate);
//		menuBar.add(Options);
//		menuBar.add(tools);
//		
//		file.add(mNew);
//		mNew.add(fNewPane);
//		
//		menuBar.setSize(1600, 35);
//		menuBar.setBackground(Color.WHITE);
//		file.setPreferredSize(new Dimension(65, file.getPreferredSize().height));
//		loadPane.setPreferredSize(new Dimension(loadPane.getPreferredSize().width + 35, loadPane.getPreferredSize().height));
//		edit.setPreferredSize(new Dimension(edit.getPreferredSize().width + 35, edit.getPreferredSize().height));
//		addObject.setPreferredSize(new Dimension(addObject.getPreferredSize().width + 35, addObject.getPreferredSize().height));
//		Rotate.setPreferredSize(new Dimension(Rotate.getPreferredSize().width + 35, Rotate.getPreferredSize().height));
//		Options.setPreferredSize(new Dimension(Options.getPreferredSize().width + 35, Options.getPreferredSize().height));
//		tools.setPreferredSize(new Dimension(tools.getPreferredSize().width + 35, Options.getPreferredSize().height));
//		
//		file.add(fSave);								
//		file.add(fImport);
//		file.add(fExport);
//		file.add(fPrint);
//		file.add(Exit);
//		
//		edit.add(eCopy);
//		edit.add(ePaste);
//		edit.add(eCut);
//		edit.add(eUndo);
//		edit.add(eRedo);
//		
//		loadPane.add(loadMainMenu);
//		loadPane.add(loadStageSelect);
//		loadPane.add(loadBaseStage);
//		loadPane.add(loadBaseStage2);
//		loadPane.add(loadPuzzle1);
//		
//		addObject.add(addPlat);
//		addObject.add(addSpike);
//		addObject.add(addHealth);
//		addObject.add(addEnergy);
//		addObject.add(addKey);
//		addObject.add(addEnemy);
//		addObject.add(addDoor);
//		
//		addEnemy.add(addReverbot);
//		addEnemy.add(addAlpha);
//		addEnemy.add(addBeta);
//		addEnemy.add(addGoomba);
//		
//		Rotate.add(Rotate90);
//		Rotate.add(Rotate90n);
//		Rotate.add(flipVertical);
//		Rotate.add(flipHorizontal);
//		
//		tools.add(docs);
//		
//		fExport.addActionListener(new clickExport());
//		eCut.addActionListener(new clickCut());
//		ePaste.addActionListener(new clickPaste());
//		loadMainMenu.addActionListener(new clickLoadMainMenu());
//		//loadStageSelect.addActionListener(new clickLoadStageSelect());
//		loadBaseStage.addActionListener(new clickLoadBaseStage());
//		loadBaseStage2.addActionListener(new clickLoadBaseStage2());
//		loadPuzzle1.addActionListener(new clickLoadPuzzle1());
//
//		addPlat.addActionListener(new clickAddPlatform());					// click add Platform action
//		addSpike.addActionListener(new clickAddSpike());                    // click add Spike action
//		addHealth.addActionListener(new clickAddHealth());          		// click add Item action
//		addKey.addActionListener(new clickAddKey());          				// click add key action
//		addEnemy.addActionListener(new clickAddEnemy());          			// click add key action
//		addReverbot.addActionListener(new clickAddReverbot());              // click add reverbot
//		addAlpha.addActionListener(new clickAddAlpha());             		// click add alpha
//		addBeta.addActionListener(new clickAddBeta());              		// click add beta
//		addDoor.addActionListener(new clickAddDoor());                      // click add door
//		docs.addActionListener(new clickDoc());
//		Rotate90.addActionListener(new clickRotate90());                    // click rotate90
//		Rotate90n.addActionListener(new clickRotate90N());                  // click rotate90n
//		flipHorizontal.addActionListener(new clickFlipHorizontal());        // click flip horizontal
//		flipVertical.addActionListener(new clickFlipVertical());        	// click flip vertical
//
//		
//		addGoomba.addActionListener(new clickAddGoomba());
//		program.getGCanvas().add(menuBar);									// adds the menu bar
//		program.setSize(1600, 800);											// hack method to fix menu border initialization
//		program.setSize(1600, 900);
//		program.getGCanvas().setName("something");
//		
//		
//		
//		
//	}
//	
//	/**
//	 * createDefStage() Instantiates a default level with platforms, ai and spikes.
//	 * Instantiated class objects are added to 'objects' data structure 
//	 * 
//	 */
//	
//	private void createDefStage() {
//		Enviro_Platform newPlatform1 = new Enviro_Platform("T_Platform.png", 942, 316); 
//		Enviro_Platform newPlatform2 = new Enviro_Platform("T_Platform.png", 1810, 316); 
//		Enviro_Platform newPlatform3 = new Enviro_Platform("T_Platform.png", 3212, 316); 
//		Enviro_Platform newPlatform4 = new Enviro_Platform("T_Platform.png", 4081, 316); 
//		Enviro_Platform newPlatform5 = new Enviro_Platform("T_Platform.png", 4981, 316); 
//		Enviro_Platform newPlatform6 = new Enviro_Platform("T_Platform.png", 5236, 316); 
//		Enviro_Platform newPlatform7 = new Enviro_Platform("T_Platform.png", 5491, 316); 
//		
//		Item key = new Item("T_Key.png", 4250, 163); 
//		items.add(key);
//		
//		Enviro_Spikes newSpike1 = new Enviro_Spikes("T_Spikes.png", 2602, 541); 
//		Enviro_Spikes newSpike2 = new Enviro_Spikes("T_Spikes.png", 3668, 541); 
//
//		ArrayList<GObject> objects = new ArrayList<GObject>();
//		Collections.addAll(objects, newSpike1, newSpike2, newPlatform1, newPlatform2, newPlatform3, newPlatform4,
//				newPlatform5, newPlatform6, newPlatform7, key);
//		spikes.add(newSpike1);
//		spikes.add(newSpike2);
//
//		platforms.add(newPlatform1);
//		platforms.add(newPlatform2);
//		platforms.add(newPlatform3);
//		platforms.add(newPlatform4);
//		platforms.add(newPlatform5);
//		platforms.add(newPlatform6);
//		platforms.add(newPlatform7);
//		
//		for (GObject f:objects) {
//			program.add(f);
//		}
//		newDoor.setVisible(true);
//		program.add(newDoor);
//	}
//	
//	/**
//	 * Main loop
//	 * 
//	 */
//	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (movingLeft == true) {
//			bg.move(0.5, 0);
//			floor.move(RIGHT_SCROLL_SPEED, 0);
//			moveSceneObjectsRight();
//		}
//		if (movingRight == true) {
//			bg.move(-0.5, 0);
//			floor.move(LEFT_SCROLL_SPEED, 0);
//			moveSceneObjectsLeft();
//		}
//		if (jumping == true) {
//			Player.move(0, JUMP_SPEED);
//		}
//		if (falling == true) {
//			Player.move(0, FALL_SPEED);
//		}
//		
//		if (Player.getBounds().intersects(floor.getBounds())) {
//			falling = false;
//		}
//		for (GObject x:platforms) {
//			if (Player.getBounds().intersects(x.getBounds())) {
//				falling = false;
//			}
//		}
//		
//		for (GObject x:spikes) {														// Check if the player collided with any spikes and call death method
//			if (Player.getBounds().intersects(x.getBounds())) {
//				killPlayer();
//			}
//		}
//		if (Player.getBounds().intersects(newDoor.getBounds()) && stageCondition == true) {
//			program.switchToMenu();
//		}
//		
//		program.ambientAnimations(ambOne, ambTwo, ambThree);
//		pickupItem();
//		for (GObject f:enemies) {
//			((Enemy) f).updateEnemy();
//			
//			for (GObject x:platforms) {
//				if (f.getBounds().intersects(x.getBounds()) || f.getBounds().intersects(floor.getBounds())) {
//					((Enemy) f).isFalling = false;
//				}
//				if (f.getBounds().intersects(x.getBounds()) == false || f.getBounds().intersects(floor.getBounds()) == false) {
//					//((Enemy) f).isFalling = true;
//				}
//			}
//			
//		}
//	}
//	
//	/**
//	 * Movement methods that handle interpolation of scene objects at right/left scroll speeds
//	 * 
//	 */
//	
//	private void moveSceneObjectsRight() {									// Method that handles moving all scene objects to the Right
//		for (GObject f:platforms) {
//			f.move(RIGHT_SCROLL_SPEED, 0);;
//		}
//		for (GObject f:items) {
//			f.move(RIGHT_SCROLL_SPEED, 0);;
//		}
//		for (GObject f:enemies) {
//			f.move(RIGHT_SCROLL_SPEED, 0);;
//		}
//		for (GObject f:spikes) {
//			f.move(RIGHT_SCROLL_SPEED, 0);;
//		}
//		for (GObject f:doors) {
//			f.move(RIGHT_SCROLL_SPEED, 0);;
//		}
//		newDoor.move(RIGHT_SCROLL_SPEED, 0);;
//	}
//	
//	private void moveSceneObjectsLeft() {									// Method that handles moving all scene objects to the Left
//		for (GObject f:platforms) {
//			f.move(LEFT_SCROLL_SPEED, 0);;
//		}
//		for (GObject f:items) {
//			f.move(LEFT_SCROLL_SPEED, 0);;
//		}
//		for (GObject f:enemies) {
//			f.move(LEFT_SCROLL_SPEED, 0);;
//		}
//		for (GObject f:spikes) {
//			f.move(LEFT_SCROLL_SPEED, 0);;
//		}
//		for (GObject f:doors) {
//			f.move(LEFT_SCROLL_SPEED, 0);;
//		}
//		newDoor.move(LEFT_SCROLL_SPEED, 0);;
//	}
//	
//	private void killPlayer() {
//		Player.setLocation(750, 500);
//	}
//	
//	/**
//	 * Methods exposed to the toolkit that allow game objects to be instantiated during run time and tested in 
//	 * real time. 
//	 * 
//	 */
//	
//	public void drawGrid() {
//	}
//	
//	private void addPlatform() {											// Method that handles adding platforms
//		Enviro_Platform newPlatform = new Enviro_Platform("T_Platform.png", 800, 450); 
//		platforms.add(newPlatform);
//		program.add(newPlatform);
//	}
//	
//	private void addSpike() {												// Method that handles adding platforms
//		Enviro_Spikes newSpike = new Enviro_Spikes("T_Spikes.png", 800, 450); 
//		spikes.add(newSpike);
//		program.add(newSpike);
//	}
//	
//	private void addHealth() {												// Method that handles adding health items
//		Item energy = new Item("T_Energy.png", 800, 450); 
//		items.add(energy);
//		program.add(energy);
//	}
//	
//	private void addKey() {													// Method that handles adding key items
//		Item key = new Item("T_Key.png", 800, 450); 
//		items.add(key);
//		program.add(key);
//	}
//	
//	private void addEnemy() {												// Method that handles adding health items
//		Enemy enemy = new Enemy("T_Goomba.gif", 800, 450); 
//		enemies.add(enemy);
//		program.add(enemy);
//	}
//	
//	private void addDoor() {												// Method that handles adding Doors
//		Enviro_Door door = new Enviro_Door("T_DoorClosed.png", 800, 450, false); 
//		doors.add(door);
//		program.add(door);
//	}
//	
//	private void addReverbot(int x, int y) {								// Method that handles adding Reaverbot Enemies
//		GRect tempRec = new GRect(750, 500, 750, 750);
//		//Enemy_Reverbot enemy = new Enemy_Reverbot("T_Goomba.gif", 800, 450, Player, tempRec); 
//		Enemy_Reverbot enemy = new Enemy_Reverbot("T_Goomba.gif", x, y, Player, tempRec); 
//		tempRec.setLineWidth(5);
//		tempRec.setColor(Color.ORANGE);
//		tempRec.setFillColor(Color.RED);
//		tempRec.setFilled(false);
//		tempRec.setVisible(true);
//
//		enemies.add(enemy);
//		program.add(enemy);
//		program.add(tempRec);
//	}
//	
//	private void addAlpha() {												// Method that handles adding Reaverbot Enemies
//		Enemy_Alpha enemy = new Enemy_Alpha("T_Alpha.gif", 800, 450); 
//		enemies.add(enemy);
//		program.add(enemy);
//	}
//	
//	private void addBeta() {												// Method that handles adding Reverbot Enemies
//		Enemy_Beta enemy = new Enemy_Beta("T_Beta.gif", 800, 450); 
//		enemies.add(enemy);
//		program.add(enemy);
//	}
//	
//	private void addGoomba() {												// Method that handles adding Reverbot Enemies
//		Enemy enemy = new Enemy("T_Goomba.gif", 800, 450); 
//		enemies.add(enemy);
//		program.add(enemy);
//	}
//	
//	private void pickupItem() {												// Method that handles item pickup events
//		
//		for (GObject f:items) {
//			if (Player.getBounds().intersects(f.getBounds())) {
//				program.remove(f);
//				collectedKey = true;										// placeholder stage pass condition
//				stageCondition = true;
//			}
//		}
//	}
//	
//	private void initLabel(GLabel label) {									// Method that handles initializing GLabels
//		label.setFont(Font.SANS_SERIF);
//		label.setColor(Color.red);
//		label.scale(5);
//		label.setVisible(true);
//	}
//	
//	private void cutObject() {
//		program.remove(this.currentObject);
//		platforms.remove(currentObject);
//		enemies.remove(currentObject);
//		items.remove(currentObject);
//	}
//	
//	
//	private void pasteObject() {
//		program.add(this.currentObject);
//
//	}
//	
//	private void rotate90() {
//		currentObject.rotate(-90);
//	}
//	
//	private void rotate90N() {
//		currentObject.rotate(90);
//	}
//	
//	private void flipHorizontal() {
//		currentObject.scale(-1, 1);
//	}
//	
//	private void flipVertical() {
//		currentObject.scale(1, -1);
//	}
//	
//	private void exportScene(Component[] components) throws IOException, AWTException {
//		Rectangle screenRect = new Rectangle(program.getGCanvas().getBounds());
//		//Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//		BufferedImage capture = new Robot().createScreenCapture(screenRect);
//		ImageIO.write(capture, "bmp", new File("screenshot.png"));
//	}
//	
//	
//	/**
//	 * Methods that handle Input events | Keyboard / Mouse events
//	 * 
//	 */
//
//	public void mousePressed(MouseEvent e) {
//		
//		GObject obj = program.getElementAt(e.getX(), e.getY());
//		currentObject = obj;
//		lastX = e.getX();
//		lastY = e.getY();
//		
//		xVal = obj.getLocation().getX() - mapBounds.getLocation().getX();
//		yVal = obj.getLocation().getY() - mapBounds.getLocation().getY();
//		
//		System.out.println(xVal);
//		System.out.println(yVal);
//
//
//		
//		if(e.getButton() == MouseEvent.BUTTON3) {
//			program.remove(obj);
//			platforms.remove(obj);
//			enemies.remove(obj);
//			items.remove(obj);
//			
//		}
//	}
//	
//						
//	
//	public void mouseDragged(MouseEvent e) {
//		GObject obj = program.getElementAt(e.getX(), e.getY());
//			if(platforms.contains(obj) || items.contains(obj) || enemies.contains(obj) || spikes.contains(obj) || doors.contains(obj)) {
//				//obj.setLocation(e.getX() - 20, e.getY() - 7); //Not Pinpoint Accurate
//				obj.move(e.getX()-lastX, e.getY()-lastY);		//Pinpoint Accurate 
//				lastX = e.getX();
//				lastY = e.getY();
//				x = Integer.toString((int) obj.getX());
//				y = Integer.toString((int) obj.getY());
//				elemXCoord.setLabel(x);
//				elemYCoord.setLabel(y);
//			}
//	}
//	
//	public void keyPressed(KeyEvent e) {
//		switch (e.getKeyCode()) {
//		
//		case KeyEvent.VK_A:
//			movingLeft = true;
//			movingRight = false;
//			break;
//		case KeyEvent.VK_D:
//			movingRight = true;
//			movingLeft = false;
//			break;	
//		case KeyEvent.VK_SPACE:
//			jumping = true;
//			falling = false;
//			break;
//		case KeyEvent.VK_ESCAPE:
//			System.exit(0);
//			
//		case KeyEvent.VK_1:
//			program.getGCanvas().add(menuBar);
//			
//		case KeyEvent.VK_2:
//			program.getGCanvas().remove(menuBar);
//		}		
//			
//	}
//		
//	public void keyReleased(KeyEvent e) {
//		switch (e.getKeyCode()) {
//			
//		case KeyEvent.VK_A:
//			movingLeft = false;
//			break;
//		case KeyEvent.VK_D:
//			movingRight = false;
//			break;	
//		case KeyEvent.VK_SPACE:
//			jumping = false;
//			falling = true;
//			break;		
//			
//		}
//	}
//	
//	
//	/**
//	 * Toolkit Menu Bar Classes that can be attached to JMenu Items
//	 * 
//	 */
//	
//	public class clickExport implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			try {
//				exportScene(program.getGCanvas().getComponents());
//			} catch (IOException | AWTException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
//	
//	public class clickAddDoor implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addDoor();
//		}
//	}
//	
//	public class clickAddReverbot implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addReverbot(800, 550);
//		}
//	}
//	
//	public class clickAddAlpha implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addAlpha();
//		}
//	}
//	
//	public class clickAddBeta implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addBeta();
//		}
//	}
//	
//	public class clickAddGoomba implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addGoomba();
//		}
//	}
//	
//	public class clickCut implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			cutObject();
//		}
//	}
//	
//	public class clickPaste implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			pasteObject();
//		}
//	}
//	
//	public class clickRotate90 implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			rotate90();
//		}
//	}
//	
//	public class clickRotate90N implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			rotate90N();
//		}
//	}
//	
//	public class clickFlipHorizontal implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			flipHorizontal();
//		}
//	}
//	
//	public class clickFlipVertical implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			flipVertical();
//		}
//	}
//	
//	public class clickLoadMainMenu implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			program.switchToMenu();;
//		}
//	}
//	
//	public class clickLoadBaseStage implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			program.switchToBaseStage();;
//		}
//	}
//	
//	public class clickLoadBaseStage2 implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			program.switchToBaseStage2();;
//		}
//	}
//	
//	public class clickLoadPuzzle1 implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			program.switchToPuzzle1();
//		}
//	}
//	
//	private class clickAddPlatform implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addPlatform();
//		}
//	}
//	
//	private class clickAddSpike implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addSpike();
//		}
//	}
//	
//	private class clickAddHealth implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addHealth();
//		}
//	}
//	
//	private class clickAddKey implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addKey();
//		}
//	}
//
//	private class clickAddEnemy implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			addEnemy();
//		}	
//		
//	}
//	
//	public class clickDoc implements ActionListener{
//		public void actionPerformed (ActionEvent e) {
//			Documentation doc = new Documentation();
//		}
//	}
//	
//	/**
//	 * Scene render methods that handle Graphics Application object add(s)
//	 * 
//	 */
//	
//	public void showContents() {
//		
//		for (GObject e:bgElements) {
//			program.add(e);
//		}
//	
//		for (GObject e:devLabels) {
//			program.add(e);
//		}
//		
//		createDefStage();
//		addReverbot(1102, 515);
//		addReverbot(4225, 163);
//		addReverbot(5000, 515);
//		addReverbot(5300, 515);
//		addReverbot(5600, 515);
//		
//		program.add(Player);
//		program.add(mapBounds);
//	}
//
//	
//	public void hideContents() {
//	
//	}
//}
