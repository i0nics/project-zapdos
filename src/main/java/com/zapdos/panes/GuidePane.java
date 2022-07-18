package com.zapdos.panes;

import java.awt.event.MouseEvent;
import com.zapdos.main.MainApplication;
import com.zapdos.main.Sprite;
import com.zapdos.starter.GraphicsPane;
import acm.graphics.GObject;

public class GuidePane extends GraphicsPane {

	private static double SCALE = MainApplication.SCALE;
	public static final int WINDOW_WIDTH = MainApplication.WINDOW_WIDTH;
	public static final int WINDOW_HEIGHT = MainApplication.WINDOW_HEIGHT;
	private static final double BACK_BUTTON_Y = 50.0;
	private static final double BACK_BUTTON_X = 20.0;

	private Sprite backButton;
	private MainApplication program;
	private Sprite tips;
	private Sprite tipsBG;
	private Sprite tipsBG2;
	private Sprite MMRUN;
	private Sprite PROJ;

	public GuidePane(MainApplication app) {
		super();
		program = app;
		tips = new Sprite("T_TipsNew.png", 0.0, 0.0, SCALE);
		tipsBG = new Sprite ("T_SS_BG.gif", 0.0, 0.0, SCALE);
		tipsBG2 = new Sprite ("T_BGOverlay2.png", 0.0, 0.0, SCALE);
		MMRUN = new Sprite ("player2/T_MM_RightRun.gif", 330.0, 230.0);
		PROJ = new Sprite ("player2/projectileR.gif", 620.0, 310.0);

		tipsBG.setSize(1920.0, 1080.0);
		tipsBG2.setSize(1920.0, 1080.0);
		tips.setSize(1600.0, 800.0);
		backButton = new Sprite("T_Menu_Back_Button.png", BACK_BUTTON_X, BACK_BUTTON_Y, SCALE/2.5);
	}

	@Override
	public void showContents() {
		program.add(tipsBG.getImg());
		program.add(tipsBG2.getImg());
		program.add(tips.getImg());
		program.add(MMRUN.getImg());
		program.add(PROJ.getImg());
		program.add(backButton.getImg());
		
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		System.out.println("x: " + e.getX() + " y: " + e.getY() );
		if (obj == backButton.getImg()) {
			program.getSFXPlayer().playClickSound();
			program.switchToMenu();
		}
	}
}
