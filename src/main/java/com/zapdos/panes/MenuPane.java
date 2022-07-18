package com.zapdos.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.zapdos.audio.SFXPlayer;
import com.zapdos.main.MainApplication;
import com.zapdos.main.Sprite;
import com.zapdos.starter.GraphicsPane;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane implements ActionListener {
	private MainApplication program;
	private static double SCALE = MainApplication.SCALE;
	private static final double DEFAULT_X = 0;
	private static final double DEFAULT_Y = 0;
	private static final double PROJECTZAPDOS_X = 40;
	private static final double PROJECTZAPDOS_Y = 370;
//	private static final int GCIRCLE_X = 90;
//	private static final int GCIRCLE_Y = 110;
	private static final double BCIRCLE_X = 195;
	private static final double BCIRCLE_Y = 190;
	private static final double MENU_PLAY_X = 775 + 243;
	private static final double MENU_PLAY_Y = 200;
	private static final double MENU_CREATE_X = 750 + 243;
	private static final double MENU_CREATE_Y = 320;
	private static final double MENU_GUIDE_X = 725 + 243;
	private static final double MENU_GUIDE_Y = 440;
	private static final double MENU_QUIT_X = 943;
	private static final double MENU_QUIT_Y = 560;
	
	private static final int MS = 17;
	private Timer paneTimer = new Timer(MS, this);
	private SFXPlayer sfxPlayer;
	
	private Sprite menuBackgroundImg, menuOverlay, menuLogo, menuBcircle, menuPlayButtonImg, menuCreateButtonImg, menuGuideButtonImg, menuQuitButtonImg;
	private GObject obj;

	
	public MenuPane(MainApplication app) {
		super();
		program = app;
		sfxPlayer = app.getSFXPlayer();
		menuBackgroundImg = new Sprite("T_SS_BG.gif", DEFAULT_X, DEFAULT_Y, SCALE);
		menuOverlay = new Sprite("T_BGOverlay2.png", DEFAULT_X, DEFAULT_Y);
		menuLogo = new Sprite("T_Menu_ProjectZapdos.png", PROJECTZAPDOS_X, PROJECTZAPDOS_Y, SCALE);
		menuBcircle = new Sprite("T_BlueCircle.gif", BCIRCLE_X, BCIRCLE_Y, 1.5*SCALE);
		menuPlayButtonImg = new Sprite("T_Menu_Play.png", MENU_PLAY_X, MENU_PLAY_Y, SCALE);
		menuCreateButtonImg = new Sprite("T_Menu_Create.png", MENU_CREATE_X, MENU_CREATE_Y, SCALE);
		menuGuideButtonImg = new Sprite("T_Menu_Guide.png", MENU_GUIDE_X, MENU_GUIDE_Y, SCALE);
		menuQuitButtonImg = new Sprite("T_Menu_Quit.png", MENU_QUIT_X, MENU_QUIT_Y, SCALE);
		paneTimer.start();
	}
	

	@Override
	public void showContents() {
		menuBackgroundImg.setSize(1920.0, 1080.0);
	    program.add(menuBackgroundImg.getImg());
	    program.add(menuOverlay.getImg());
		//program.add(menuGcircle);
		program.add(menuBcircle.getImg());
		program.add(menuLogo.getImg());
		program.add(menuPlayButtonImg.getImg());
		program.add(menuCreateButtonImg.getImg());
		program.add(menuGuideButtonImg.getImg());
		program.add(menuQuitButtonImg.getImg());
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		obj = program.getElementAt(e.getX(), e.getY());
		
		if(obj == menuPlayButtonImg.getImg()) {
			sfxPlayer.playClickSound();
			program.switchToStageSelect();
		}
		
		else if (obj == menuCreateButtonImg.getImg()) {
			program.setStage(0);
			program.setDevmode(true);
			sfxPlayer.playClickSound();
			program.switchToBaseStage();
		}
		
		else if(obj == menuGuideButtonImg.getImg()) {
			sfxPlayer.playClickSound();
			program.switchToGuide();
		}
		
		else if(obj == menuQuitButtonImg.getImg()) {
			 sfxPlayer.playClickSound();
			 System.exit(0);
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj != null) {
			MainApplication.buttonHover(menuPlayButtonImg.getImg(), obj, "T_Menu_Play_Hover.png", "T_Menu_Play.png");
			MainApplication.buttonHover(menuCreateButtonImg.getImg(), obj, "T_Menu_Create_Hover.png", "T_Menu_Create.png");
			MainApplication.buttonHover(menuGuideButtonImg.getImg(), obj, "T_Menu_Guide_Hover.png", "T_Menu_Guide.png");
			MainApplication.buttonHover(menuQuitButtonImg.getImg(), obj, "T_Menu_Quit_Hover.png", "T_Menu_Quit.png");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//menuGcircle.rotate(4);
		//menuBcircle.rotate(-4);
	}
}
