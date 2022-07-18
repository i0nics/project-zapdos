package com.zapdos.main;

import com.zapdos.audio.MusicPlayer;
import com.zapdos.audio.SFXPlayer;
import com.zapdos.panes.MenuPane;
import com.zapdos.panes.StageSelect;
import com.zapdos.panes.Credits;
import com.zapdos.panes.GuidePane;
import com.zapdos.stage.*;
import com.zapdos.starter.GraphicsApplication;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 1600;
	public static final int WINDOW_HEIGHT = 800;
	public static final Double SCALE = 1.0;
		
	private static MenuPane menu;
	private static Credits credits;
	public static BaseStage2 bStage;
	private static MenuBar menuBar;
	private static StageSelect stageSelect;
	private static GuidePane tips;
	private MusicPlayer musicPlayer;
	private SFXPlayer sfxPlayer;
	
	
	public MainApplication() {
		
	}
	
	// initialize variables and objects
	public void init() {
		musicPlayer = new MusicPlayer();
		sfxPlayer = new SFXPlayer();
		bStage = new BaseStage2(this, false); 
		menuBar = new MenuBar(this, bStage);
		stageSelect = new StageSelect(this);
		menu = new MenuPane(this);
		tips = new GuidePane(this);
		credits = new Credits(this);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

		
	public void run() {
		System.out.println("Initializing Game!");
		setupInteractions();
		musicPlayer.playMenuTrack();
		switchToMenu();
	}
	
	public void setStage(int stageNum) {
		bStage.setStageNum(stageNum);
	}
	
	public void unlockAllStages() {
		stageSelect.unlockAllStages();
		stageSelect.updateSprites();
	}
	
	public void setStageOneComplete() {
		stageSelect.stageTwoComplete = true;
		stageSelect.updateSprites();
	}
	public void setStageTwoComplete() {
		stageSelect.stageThreeComplete = true;
		stageSelect.updateSprites();
	}
	public void setStageThreeComplete() {
		stageSelect.stageFourComplete = true;
		stageSelect.updateSprites();
	}
	public void setStageFourComplete() {
		stageSelect.stageFiveComplete = true;
		stageSelect.updateSprites();
	}
	public void setStageFiveComplete() {
		stageSelect.stageSixComplete = true;
		stageSelect.updateSprites();
	}
	public void setStageSixComplete() {
		stageSelect.stageSixComplete = true;
		stageSelect.updateSprites();
	}
	
	public void setDevmode(boolean isDevMode) {
		menuBar.setDevMode(isDevMode);
		bStage.setDevMode(isDevMode);
	}
	
	public MusicPlayer getMusicPlayer() {
		return musicPlayer;
	}
	
	public SFXPlayer getSFXPlayer() {
		return sfxPlayer;
	}
	
	public void switchToBaseStage() {
		switchToScreen(bStage);
	}
	
	public void switchToMenu() {
		setDevmode(false);
		switchToScreen(menu);
	}
	
	public void switchToCredits() {
		setDevmode(false);
		switchToScreen(credits);
	}

	public void switchToStageSelect() {
		setDevmode(false);
		switchToScreen(stageSelect);
	}
	
	public void switchToGuide() {
		setDevmode(false);
		switchToScreen(tips);
	}
	
	public static void buttonHover(GImage button, GObject obj, String newButton, String defButton) {
		if(obj == button) {
			button.setImage("sprites/" + newButton);
		}
		else {
			button.setImage("sprites/" + defButton);			
		} 
	}
	
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}
