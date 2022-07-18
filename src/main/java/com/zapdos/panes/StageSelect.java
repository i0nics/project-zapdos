package com.zapdos.panes;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.zapdos.audio.SFXPlayer;
import com.zapdos.main.AudioPlayer;
import com.zapdos.main.MainApplication;
import com.zapdos.main.Sprite;
import com.zapdos.starter.GraphicsPane;
import acm.graphics.GObject;

public class StageSelect extends GraphicsPane {

	private static final double DEFAULT_BUTTON_SIZE = 200.0;
	private static final double SCALE = MainApplication.SCALE;
	private static final double DEFAULT_X_COORD = 0.0;
	private static final double DEFAULT_Y_COORD = 0.0;
	private static final double DEFAULT_IMG_X = 250.0;
	private static final double DEFAULT_IMG_Y = 200.0;
	private static final double NEXT_LEVEL_BUTTON_X = 300.0;
	private static final double NEXT_STAGE_LEVELSET_Y = 300.0;
	private static final double BACK_BUTTON_X = 20.0;
	private static final double BACK_BUTTON_Y = 50.0;
	private static final int MAX_LEVELS_IN_ROW = 4;
	private static final int MAX_LEVEL = 7;
	
	private static double imgX = DEFAULT_IMG_X;
	private static double imgY = DEFAULT_IMG_Y;
	
	private MainApplication program;
	private Sprite background;
	private Sprite background2;
	private Sprite background3;
	private Sprite backButton;
	private int stageNum;
	private ArrayList<Sprite> stages = new  ArrayList<Sprite>();
	private SFXPlayer sfxPlayer;
	
	public boolean stageOneComplete = true;
	public boolean stageTwoComplete = false;
	public boolean stageThreeComplete = false;
	public boolean stageFourComplete = false;
	public boolean stageFiveComplete = false;
	public boolean stageSixComplete = false;


	
	public StageSelect(MainApplication app) {
		super();
		program = app;
		sfxPlayer = app.getSFXPlayer();
		background = new Sprite("T_SS_BG.gif", DEFAULT_X_COORD, DEFAULT_Y_COORD, SCALE);
		background2 = new Sprite("T_DarkBG_TP.png", DEFAULT_X_COORD, DEFAULT_Y_COORD, SCALE);
		background3 = new Sprite("T_BGOverlay2.png", DEFAULT_X_COORD, DEFAULT_Y_COORD, SCALE);
		backButton = new Sprite("T_Menu_Back_Button.png", BACK_BUTTON_X, BACK_BUTTON_Y, SCALE/2.5);
		background.setSize(1600.0, 900.0);

		// Create 8 level buttons and add it to the stages ArrayList
		for (int stage_id = 1; stage_id <= MAX_LEVEL; stage_id++) {
			Sprite newStage = new Sprite("T_Stage_" + stage_id + ".png", imgX, imgY, SCALE);
			newStage.setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
			stages.add(newStage);
			
			// Layout level buttons in next row after four level buttons in a row have been placed
			if (stage_id % MAX_LEVELS_IN_ROW == 0) {
				imgY += NEXT_STAGE_LEVELSET_Y;
				imgX = DEFAULT_IMG_X;
				continue;
			}
			
			// Increase x value for position of next row button
			imgX += NEXT_LEVEL_BUTTON_X;
		}
		
		
		updateSprites();
	}
	
	public void updateSprites() {
		if (stageOneComplete == false) {
			stages.get(0).setImage("T_Stage_1_Locked.png");
			stages.get(0).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageTwoComplete == false) {
			stages.get(1).setImage("T_Stage_2_Locked.png");
			stages.get(1).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageThreeComplete == false) {
			stages.get(2).setImage("T_Stage_3_Locked.png");
			stages.get(2).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageFourComplete == false) {
			stages.get(3).setImage("T_Stage_4_Locked.png");
			stages.get(3).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageFiveComplete == false) {
			stages.get(4).setImage("T_Stage_5_Locked.png");
			stages.get(4).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageSixComplete == false) {
			stages.get(5).setImage("T_Stage_6_Locked.png");
			stages.get(5).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}

		
		// Unlocked Sprites: 
		
		if (stageOneComplete == true) {
			stages.get(0).setImage("T_Stage_1.png");
			stages.get(0).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageTwoComplete == true) {
			stages.get(1).setImage("T_Stage_2.png");
			stages.get(1).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageThreeComplete == true) {
			stages.get(2).setImage("T_Stage_3.png");
			stages.get(2).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageFourComplete == true) {
			stages.get(3).setImage("T_Stage_4.png");
			stages.get(3).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageFiveComplete == true) {
			stages.get(4).setImage("T_Stage_5.png");
			stages.get(4).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}
		if (stageSixComplete == true) {
			stages.get(5).setImage("T_Stage_6.png");
			stages.get(5).setSize(DEFAULT_BUTTON_SIZE, DEFAULT_BUTTON_SIZE);
		}

	}

	public void unlockAllStages() {
		stageOneComplete = true;
		stageTwoComplete = true;
		stageThreeComplete = true;
		stageFourComplete = true;
		stageFiveComplete = true;
		stageSixComplete = true;
	}
	
	@Override
	public void showContents() {
		program.add(background.getImg());
		program.add(background2.getImg());
		program.add(background3.getImg());
		program.add(backButton.getImg());
		
		for (Sprite stage: stages) {
			program.add(stage.getImg());
		}

	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if(obj == backButton.getImg()) {
			sfxPlayer.playClickSound();
			program.switchToMenu();
		}
		
		
		for (Sprite stage: stages) {

			if(obj == stage.getImg()) {
				// Get stage number from stage button image file name
				if (!stage.getFileName().contains("Locked")) {
					stageNum = Integer.valueOf(stage.getFileName().replaceAll(".png", "").replaceAll("sprites/T_Stage_", ""));
					program.setStage(stageNum);
					program.setDevmode(false);
					sfxPlayer.playClickSound();
					program.switchToBaseStage();
				}
			}
		}
		 
	}
}
