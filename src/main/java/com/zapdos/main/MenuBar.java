package com.zapdos.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.zapdos.audio.MusicPlayer;
import com.zapdos.audio.SFXPlayer;
import com.zapdos.enemies.Enemy_Reverbot;
import com.zapdos.environmentobjects.Button;
import com.zapdos.environmentobjects.Door;
import com.zapdos.environmentobjects.MovablePlatforms;
import com.zapdos.environmentobjects.Spike;
import com.zapdos.stage.BaseStage2;


@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private MainApplication program; 
	private BaseStage2 bStage;
	
	private static double SCALE = MainApplication.SCALE;
	private static final int WINDOW_WIDTH = MainApplication.WINDOW_WIDTH;
	private static final int MENUBAR_HEIGHT = 25;
	
	private static final double DEFAULT_X_COORD = 0.0;
	private static final double DEFAULT_BG_Y = -7600.0;
	private static final double DEFAULT_ENVOBJ_X_COORD = 100.0;
	private static final double DEFAULT_PLATFORM_Y_COORD = 200.0;
	
	private static final String BACKGROUND_CAULDRON = "T_background_cauldron.jpg";
	private static final String BACKGROUND_GRID = "T_background_grid.jpg";
	private static final String BACKGROUND_GUILD = "T_background_guild.jpg";
	private static final String BACKGROUND_ROBOHUB = "T_background_robohub.jpg";
	private static final String BACKGROUND_SPIRE = "T_background_spire.jpg";
	
	private int buttonCount = 0; // Keeps track of how many buttons have been added to the stage, so we can give
	// them unique IDs

	private static JMenuBar menuBar = new JMenuBar();

	// Exit
	private static JMenu menuZapdos = new JMenu("Project Zapdos");
	private static JMenuItem menuItemExit = new JMenuItem("Exit");

	// Switch between panes
	private static JMenu menuDeveloper = new JMenu("Developer");
	private static JMenu menuDropDownSwitchToScreen = new JMenu("Switch To Screen");
	private static JMenuItem loadMainMenu = new JMenuItem("Main Menu");
	private static JMenuItem loadStageSelect = new JMenuItem("Stage Select Menu");
	private static JMenuItem loadLevelCreator = new JMenuItem("Level Creator");
	private static JMenu loadLevels = new JMenu("Levels");
	private static JMenuItem loadLevel1 = new JMenuItem("Level 1");
	private static JMenuItem loadLevel2 = new JMenuItem("Level 2");
	private static JMenuItem loadLevel3 = new JMenuItem("Level 3");
	private static JMenuItem loadLevel4 = new JMenuItem("Level 4");
	private static JMenuItem loadLevel5 = new JMenuItem("Level 5");
	private static JMenuItem loadLevel6 = new JMenuItem("Level 6");
	private static JMenuItem loadLevel7 = new JMenuItem("Custom Level");
	private static JMenuItem unlockAllLevels = new JMenuItem("Unlock All Levels");
	private static JMenuItem loadGuide = new JMenuItem("Guide");

	// Toggle Music
	private static JMenu menuMusic = new JMenu("Music");
	private static JMenuItem menuItemToggleMusic = new JMenuItem("Toggle Music");
	private static JMenuItem menuItemToggleSFX = new JMenuItem("Toggle SFX");

	// Save or Reset Level
	private static JMenu menuLevelCreator = new JMenu("Level Creator");
	private static JMenuItem menuItemSaveLevel = new JMenuItem("Save Level");
	private static JMenuItem menuItemResetLevel = new JMenuItem("Reset Level");

	// Add Environment Objects
	private static JMenu menuAddObjects = new JMenu("Add/Modify Elements");

	
	
	private static JMenu menuDropDownAddBackground = new JMenu("Backgrounds");
	private static JMenuItem menuItemAddBackgroundCauldron = new JMenuItem("Cauldron");
	private static JMenuItem menuItemAddBackgroundGrid = new JMenuItem("Grid");
	private static JMenuItem menuItemAddBackgroundGuild = new JMenuItem("Guild");
	private static JMenuItem menuItemAddBackgroundRobohub = new JMenuItem("Robohub");
	private static JMenuItem menuItemAddBackgroundSpire = new JMenuItem("Spire");
	
	private static JMenuItem addButton = new JMenuItem("Button");
	
	private static JMenu addDoor = new JMenu("Doors");
	private static JMenuItem addItemOpenDoor = new JMenuItem("Open Door");
	private static JMenuItem addItemClosedDoor = new JMenuItem("Closed Door");
	
	private static JMenu menuDropDownPlatforms = new JMenu("Platforms");
	private static JMenuItem menuItemAddHorizontalPlatform = new JMenuItem("Horizontal Platform");
	private static JMenuItem menuItemAddVerticalPlatform = new JMenuItem("Long Horizontal Platform");
	private static JMenuItem menuItemAddLongHorizontalPlatform = new JMenuItem("Vertical Platform");
	
	private static JMenu menuDropDownMovingPlatforms = new JMenu("Moving Platforms");
	private static JMenu menuAddMovingHorizontalPlatformRight = new JMenu("Horizontal Platform Right");
	private static JMenu menuAddMovingHorizontalPlatformLeft = new JMenu("Horizontal Platform Left");
	private static JMenu menuAddMovingVerticalPlatformUp = new JMenu("Horizontal Platform Up");
	private static JMenu menuAddMovingVerticalPlatformDown = new JMenu("Horizontal Platform Down");
	private static JMenuItem menuAddMovingHPR_G = new JMenuItem("G");
	private static JMenuItem menuAddMovingHPL_G = new JMenuItem("G");
	private static JMenuItem menuAddMovingHPU_G = new JMenuItem("G");
	private static JMenuItem menuAddMovingHPD_G = new JMenuItem("G");
	private static JMenuItem menuAddMovingHPR_P = new JMenuItem("P");
	private static JMenuItem menuAddMovingHPL_P = new JMenuItem("P");
	private static JMenuItem menuAddMovingHPU_P = new JMenuItem("P");
	private static JMenuItem menuAddMovingHPD_P = new JMenuItem("P");

	private static JMenu menuDropDownAddEnemies = new JMenu("Enemies");
	private static JMenuItem menuItemAddReverbot = new JMenuItem("Reverbot");
	
	private static JMenu menuDropDownChangeMusic = new JMenu("Change Music");
	private static JMenuItem menuItemMusicAskYourself = new JMenuItem("Ask Yourself");
	private static JMenuItem menuItemMusicBGTKY = new JMenuItem("Been Good To Know Ya");
	private static JMenuItem menuItemMusicFGhost = new JMenuItem("Forest Ghost");
	private static JMenuItem menuItemMusicPUniv = new JMenuItem("Parallel Universes");
	private static JMenuItem menuItemMusicRetro = new JMenuItem("Retro");
	private static JMenuItem menuItemMusicSAE = new JMenuItem("Selling An Experience");
	private static JMenuItem menuItemMusicV = new JMenuItem("V");
	
	
	// Rotate or Flip Selected Objects
	private static JMenu menuRotate = new JMenu("Rotate Object");
	private static JMenuItem menuItemRotate90 = new JMenuItem("Rotate 90 Degrees");
	private static JMenuItem menuItemRotateNeg90 = new JMenuItem("Rotate -90 Degrees");
	private static JMenuItem menuItemFlipVertical = new JMenuItem("Flip Vertical");
	private static JMenuItem menuItemFlipHorizontal = new JMenuItem("Flip Horizontal");

	private static JMenu menuDropDownAddSpike = new JMenu("Spike");
	private static JMenuItem menuItemAddShortSpike = new JMenuItem("Short Spike");
	private static JMenuItem menuItemAddLongSpike = new JMenuItem("Long Spike");
	private static JMenuItem menuItemAddHealth = new JMenuItem("Health");
	private static JMenuItem menuItemAddDoor = new JMenuItem("Door");
	private static JMenuItem menuItemAddKey = new JMenuItem("Key");

	
	// Audio Players
	private MusicPlayer musicPlayer;
	private SFXPlayer sfxPlayer;

	public MenuBar(MainApplication app, BaseStage2 bStage) {
		this.program = app;
		musicPlayer = program.getMusicPlayer();
		sfxPlayer = program.getSFXPlayer();
		this.bStage = bStage;
		
		menuBar.setSize(WINDOW_WIDTH, MENUBAR_HEIGHT);
		menuBar.setBackground(Color.GRAY);
		menuBar.setOpaque(true);
		
		// Add objects to menu bar
		menuBar.add(menuZapdos);
		menuBar.add(menuDeveloper);
		menuBar.add(menuMusic);
		menuBar.add(menuLevelCreator);
		menuBar.add(menuAddObjects);
		menuBar.add(menuRotate);

		menuZapdos.add(menuItemExit);
		menuItemExit.addActionListener(new ClickExit());
        
		// Developer
		menuDeveloper.add(menuDropDownSwitchToScreen);
		menuDropDownSwitchToScreen.add(loadMainMenu);
		loadMainMenu.addActionListener(new ClickLoadMainMenu());
		
		menuDropDownSwitchToScreen.add(loadStageSelect);
		loadStageSelect.addActionListener(new ClickLoadStageSelect());
		
		menuDropDownSwitchToScreen.add(loadLevelCreator);
		loadLevelCreator.addActionListener(new ClickLoadLevelCreator());
		
		menuDropDownSwitchToScreen.add(loadLevels);
		loadLevels.add(loadLevel1);
		loadLevels.add(loadLevel2);
		loadLevels.add(loadLevel3);
		loadLevels.add(loadLevel4);
		loadLevels.add(loadLevel5);
		loadLevels.add(loadLevel6);
		loadLevels.add(loadLevel7);
		loadLevels.add(unlockAllLevels);
		
		loadLevel1.addActionListener(new ClickLoadLevel1());
		loadLevel2.addActionListener(new ClickLoadLevel2());
		loadLevel3.addActionListener(new ClickLoadLevel3());
		loadLevel4.addActionListener(new ClickLoadLevel4());
		loadLevel5.addActionListener(new ClickLoadLevel5());
		loadLevel6.addActionListener(new ClickLoadLevel6());
		loadLevel7.addActionListener(new ClickLoadLevel7());
		unlockAllLevels.addActionListener(new ClickUnlockAllLevels());
		
		menuDropDownSwitchToScreen.add(loadGuide);
		loadGuide.addActionListener(new ClickLoadControls());

		// Audio
		menuMusic.add(menuItemToggleMusic);
		menuItemToggleMusic.addActionListener(new ClickToggleMusic());	
		menuMusic.add(menuItemToggleSFX);
		menuItemToggleSFX.addActionListener(new ClickToggleSFX());	

		menuLevelCreator.add(menuItemSaveLevel);
		menuItemSaveLevel.addActionListener(new ClickSaveStage());
		menuLevelCreator.add(menuItemResetLevel);
		menuItemResetLevel.addActionListener(new ClickResetStage());

		/* Add/Modify Objects */
		// Backgrounds
		menuAddObjects.add(menuDropDownAddBackground);
		menuDropDownAddBackground.add(menuItemAddBackgroundCauldron);
		menuItemAddBackgroundCauldron.addActionListener(new ClickBackCauldron());
		menuDropDownAddBackground.add(menuItemAddBackgroundGrid);
		menuItemAddBackgroundGrid.addActionListener(new ClickBackGrid());
		menuDropDownAddBackground.add(menuItemAddBackgroundGuild);
		menuItemAddBackgroundGuild.addActionListener(new ClickBackGuild());
		menuDropDownAddBackground.add(menuItemAddBackgroundRobohub);
		menuItemAddBackgroundRobohub.addActionListener(new ClickBackRobohub());
		menuDropDownAddBackground.add(menuItemAddBackgroundSpire);
		menuItemAddBackgroundSpire.addActionListener(new ClickBackSpire());
				
		// Button
		menuAddObjects.add(addButton);
		addButton.addActionListener(new clickAddButton());
		
		// Doors
		menuAddObjects.add(addDoor);
		addDoor.add(addItemOpenDoor);
		addItemOpenDoor.addActionListener(new clickAddOpenDoor());
		addDoor.add(addItemClosedDoor);
		addItemClosedDoor.addActionListener(new clickAddClosedDoor());
		
		
		// Change music
		menuAddObjects.add(menuDropDownChangeMusic);
		
		menuDropDownChangeMusic.add(menuItemMusicAskYourself);
		menuItemMusicAskYourself.addActionListener(new ClickMusicAskYourself());
		
		menuDropDownChangeMusic.add(menuItemMusicBGTKY);
		menuItemMusicBGTKY.addActionListener(new ClickMusicBGTKY());
		
		menuDropDownChangeMusic.add(menuItemMusicFGhost);
		menuItemMusicFGhost.addActionListener(new ClickMusicFGhost());
		
		menuDropDownChangeMusic.add(menuItemMusicPUniv);
		menuItemMusicPUniv.addActionListener(new ClickMusicPUniv());
		
		menuDropDownChangeMusic.add(menuItemMusicSAE);
		menuItemMusicSAE.addActionListener(new ClickMusicSAE());
		
		menuDropDownChangeMusic.add(menuItemMusicRetro);
		menuItemMusicRetro.addActionListener(new ClickMusicRetro());
		
		menuDropDownChangeMusic.add(menuItemMusicV);
		menuItemMusicV.addActionListener(new ClickMusicV());
		
		// Enemies
	    menuAddObjects.add(menuDropDownAddEnemies);
		menuDropDownAddEnemies.add(menuItemAddReverbot);
		menuItemAddReverbot.addActionListener(new ClickAddReverbot());
		
		// Platforms
		menuAddObjects.add(menuDropDownPlatforms);
		menuDropDownPlatforms.add(menuItemAddHorizontalPlatform);
		menuItemAddHorizontalPlatform.addActionListener(new ClickAddHorizPlatform());
		menuDropDownPlatforms.add(menuItemAddLongHorizontalPlatform);
		menuItemAddLongHorizontalPlatform.addActionListener(new ClickAddHorizPlatformLong());
		menuDropDownPlatforms.add(menuItemAddVerticalPlatform);
		menuItemAddVerticalPlatform.addActionListener(new ClickAddVerticalPlatform());
		
		// Moving Platforms
		menuAddObjects.add(menuDropDownMovingPlatforms);
		menuDropDownMovingPlatforms.add(menuAddMovingHorizontalPlatformLeft);
		menuDropDownMovingPlatforms.add(menuAddMovingHorizontalPlatformRight);
		menuDropDownMovingPlatforms.add(menuAddMovingVerticalPlatformUp);
		menuDropDownMovingPlatforms.add(menuAddMovingVerticalPlatformDown);
		
		menuAddMovingHorizontalPlatformLeft.add(menuAddMovingHPL_G);
		menuAddMovingHorizontalPlatformLeft.add(menuAddMovingHPL_P);
		menuAddMovingHorizontalPlatformRight.add(menuAddMovingHPR_G);
		menuAddMovingHorizontalPlatformRight.add(menuAddMovingHPR_P);
		menuAddMovingVerticalPlatformUp.add(menuAddMovingHPU_G);
		menuAddMovingVerticalPlatformUp.add(menuAddMovingHPU_P);
		menuAddMovingVerticalPlatformDown.add(menuAddMovingHPD_G);
		menuAddMovingVerticalPlatformDown.add(menuAddMovingHPD_P);
		
		menuAddMovingHPL_G.addActionListener(new ClickAddMovingHPL_G());
		menuAddMovingHPL_P.addActionListener(new ClickAddMovingHPL_P());
		menuAddMovingHPR_G.addActionListener(new ClickAddMovingHPR_G());
		menuAddMovingHPR_P.addActionListener(new ClickAddMovingHPR_P());
		menuAddMovingHPU_G.addActionListener(new ClickAddMovingHPU_G());
		menuAddMovingHPU_P.addActionListener(new ClickAddMovingHPU_P());
		menuAddMovingHPD_G.addActionListener(new ClickAddMovingHPD_G());
		menuAddMovingHPD_P.addActionListener(new ClickAddMovingHPD_P());
		
		
		

		// Spikes
		menuAddObjects.add(menuDropDownAddSpike);
		menuDropDownAddSpike.add(menuItemAddShortSpike);
		menuItemAddShortSpike.addActionListener(new ClickAddShortSpike());
		menuDropDownAddSpike.add(menuItemAddLongSpike);
		menuItemAddLongSpike.addActionListener(new ClickAddLongSpike());
		
		
		//menuAddObjects.add(menuItemAddHealth);
		//menuItemAddHealth.addActionListener(new ClickAddHealth());
		
		// Add rotate/flip options
		menuRotate.add(menuItemRotate90);
		menuItemRotate90.addActionListener(new ClickRotate90());
		menuRotate.add(menuItemRotateNeg90);
		menuItemRotateNeg90.addActionListener(new ClickRotateNeg90());
		menuRotate.add(menuItemFlipVertical);
		menuItemFlipVertical.addActionListener(new ClickFlipVertical());
		menuRotate.add(menuItemFlipHorizontal);
		menuItemFlipHorizontal.addActionListener(new ClickFlipHorizontal());
		
//
//		menuZapdos.setPreferredSize(new Dimension(menuZapdos.getPreferredSize().width, menuZapdos.getPreferredSize().height));
//		menuLevelCreator.setPreferredSize(new Dimension(menuLevelCreator.getPreferredSize().width, menuLevelCreator.getPreferredSize().height));
//		menuAddObjects.setPreferredSize(new Dimension(menuAddObjects.getPreferredSize().width, menuAddObjects.getPreferredSize().height));
//		menuRotate.setPreferredSize(new Dimension(menuRotate.getPreferredSize().width, menuRotate.getPreferredSize().height));
//		menuMusic.setPreferredSize(new Dimension(menuMusic.getPreferredSize().width, menuMusic.getPreferredSize().height));
//		menuDeveloper.setPreferredSize(new Dimension(menuDeveloper.getPreferredSize().width, menuDeveloper.getPreferredSize().height));
//		
		setDevMode(false);

		program.getGCanvas().add(menuBar);
		program.getGCanvas().revalidate() ;

		menuBar.setVisible(true);
	}

	public void setDevMode(boolean isDevMode) {
		if (!isDevMode) {
			try {
				menuBar.remove(menuLevelCreator);
				menuBar.remove(menuAddObjects);
				menuBar.remove(menuRotate);
			} catch(NullPointerException n) {;}
		}

		else {
			menuBar.add(menuLevelCreator);
			menuBar.add(menuAddObjects);
			menuBar.add(menuRotate);
		}
		
		program.getGCanvas().revalidate();
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	// ===================================================== Menu Actions
	public class ClickExit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	
	public class ClickLoadMainMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.switchToMenu();
		}
	}
	
	public class ClickLoadControls implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.switchToGuide();
		}
	}
	
	public class ClickLoadStageSelect implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.switchToStageSelect();;
		}
	}

	
	public class ClickLoadLevelCreator implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(0);
			program.switchToBaseStage();
		}
	}
	
	public class ClickLoadLevel1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(1);
			program.switchToBaseStage();
		}
	}
	
	public class ClickLoadLevel2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(2);
			program.switchToBaseStage();
		}
	}
	
	public class ClickLoadLevel3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(3);
			program.switchToBaseStage();
		}
	}
	
	public class ClickLoadLevel4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(4);
			program.switchToBaseStage();
		}
	}
	
	public class ClickLoadLevel5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(5);
			program.switchToBaseStage();
		}
	}
	
	public class ClickLoadLevel6 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(6);
			program.switchToBaseStage();
		}
	}
	
	public class ClickLoadLevel7 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(7);
			program.switchToBaseStage();
		}
	}
	
	public class ClickLoadLevel8 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.setStage(8);
			program.switchToBaseStage();
		}
	}
	
	public class ClickUnlockAllLevels implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			program.setDevmode(true);
			program.unlockAllStages();
		}
	}
	
	public class ClickToggleMusic implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			musicPlayer.toggleMusic();
		}
	}
	
	public class ClickToggleSFX implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sfxPlayer.toggleSFX();
		}
	}
	
	private class ClickSaveStage implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.getStageCreator().saveStage();
		}
	}
	
	private class ClickResetStage implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.hideContents();
			bStage.showContents();
		}
	}
	
	private class ClickBackCauldron implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.setBackground(BACKGROUND_CAULDRON);
		}
	}
	
	private class ClickBackGrid implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.setBackground(BACKGROUND_GRID);
		}
	}
	
	private class ClickBackGuild implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.setBackground(BACKGROUND_GUILD);
		}
	}
	
	private class ClickBackRobohub implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.setBackground(BACKGROUND_ROBOHUB);
		}
	}
	
	private class ClickBackSpire implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.setBackground(BACKGROUND_SPIRE);
		}
	}
	
	// Creates a new button. Name of the button follows the format "buttonX" where x
	// starts at 0 and increments per button added.
	public class clickAddButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String buttonName = "button" + Integer.toString(buttonCount);
			Button newButton = new Button(DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, buttonName);
			bStage.getButtons().add(newButton);
			program.add(newButton.getImg());
			buttonCount++;
		}
	}
	
	public class clickAddOpenDoor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Door newDoor = new Door(DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, true);
			bStage.getDoors().add(newDoor);
			program.add(newDoor.getImg());
		}
	}
	
	public class clickAddClosedDoor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Door newDoor = new Door(DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, false);
			bStage.getDoors().add(newDoor);
			program.add(newDoor.getImg());
		}
	}
	
	private class ClickAddHorizPlatform implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Sprite newPlatform = new Sprite("T_Platform.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD);
			bStage.getPlatforms().add(newPlatform);
			program.add(newPlatform.getImg());
		}
	}
	
	private class ClickAddHorizPlatformLong implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Sprite newPlatform = new Sprite("T_PlatformVertical.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE);
			bStage.getPlatforms().add(newPlatform);
			program.add(newPlatform.getImg());
		}
	}

	private class ClickAddVerticalPlatform implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Sprite newPlatform = new Sprite("T_PlatformLong.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD);
			bStage.getPlatforms().add(newPlatform);
			program.add(newPlatform.getImg());
		}
	}
	
	
	// Moving Platforms
	private class ClickAddMovingHPL_G implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MovablePlatforms platform = new MovablePlatforms("T_Platform_MV_G.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, 'G', false, -1.0);
			bStage.getMovablePlatforms().add(platform);
			program.add(platform.getImg());
		}
	}
	
	private class ClickAddMovingHPL_P implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MovablePlatforms platform = new MovablePlatforms("T_Platform_MV_P.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, 'P', false, -1.0);
			bStage.getMovablePlatforms().add(platform);
			program.add(platform.getImg());
		}
	}
	
	private class ClickAddMovingHPR_G implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MovablePlatforms platform = new MovablePlatforms("T_Platform_MV_G.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, 'G', false, 1.0);
			bStage.getMovablePlatforms().add(platform);
			program.add(platform.getImg());
		}
	}
	
	private class ClickAddMovingHPR_P implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MovablePlatforms platform = new MovablePlatforms("T_Platform_MV_P.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, 'P', false, 1.0);
			bStage.getMovablePlatforms().add(platform);
			program.add(platform.getImg());
		}
	}
	
	private class ClickAddMovingHPU_G implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MovablePlatforms platform = new MovablePlatforms("T_Platform_MV_G.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, 'G', true, -1.0);
			bStage.getMovablePlatforms().add(platform);
			program.add(platform.getImg());
		}
	}
	
	private class ClickAddMovingHPU_P implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MovablePlatforms platform = new MovablePlatforms("T_Platform_MV_P.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, 'P', true, -1.0);
			bStage.getMovablePlatforms().add(platform);
			program.add(platform.getImg());
		}
	}
	
	private class ClickAddMovingHPD_G implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MovablePlatforms platform = new MovablePlatforms("T_Platform_MV_G.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, 'G', true, 1.0);
			bStage.getMovablePlatforms().add(platform);
			program.add(platform.getImg());
		}
	}
	
	private class ClickAddMovingHPD_P implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MovablePlatforms platform = new MovablePlatforms("T_Platform_MV_P.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE, 'P', true, 1.0);
			bStage.getMovablePlatforms().add(platform);
			program.add(platform.getImg());
		}
	}
	
	
	private class ClickAddShortSpike implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Spike newSpike = new Spike(bStage, "T_Spikes.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE);
			bStage.getSpikes().add(newSpike);
			program.add(newSpike.getImg());
		}
	}
	
	private class ClickAddLongSpike implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Spike newSpike = new Spike(bStage, "T_Spikes_Long.png", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, SCALE);
			bStage.getSpikes().add(newSpike);
			program.add(newSpike.getImg());
		}
	}
	
	private class ClickAddHealth implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		//Implement
		}
	}
	
	private class ClickAddReverbot implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			Enemy_Reverbot enemy = new Enemy_Reverbot("T_ReverbotMoveRight.gif", DEFAULT_ENVOBJ_X_COORD, DEFAULT_PLATFORM_Y_COORD, bStage.getPlayer());
			bStage.getEnemies().add(enemy);
			program.add(enemy.getImg());
		}
	}
	
	private class ClickMusicAskYourself implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			musicPlayer.playAskTrack();
		}
	}
	
	private class ClickMusicBGTKY implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			musicPlayer.playBGTKYTrack();
		}
	}
	
	private class ClickMusicFGhost implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			musicPlayer.playForestGhostTrack();
		}
	}
	
	private class ClickMusicPUniv implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			musicPlayer.playParallelUniversesTrack();
		}
	}
	
	private class ClickMusicRetro implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			musicPlayer.playRetroTrack();
		}
	}
	
	private class ClickMusicSAE implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			musicPlayer.playSAETrack();
		}
	}
	
	private class ClickMusicV implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			musicPlayer.playVTrack();
		}
	}
	
	public class ClickRotate90 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.getCurrentSelectedObject().rotate(-90);
		}
	}
	
	public class ClickRotateNeg90 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.getCurrentSelectedObject().rotate(90);
		}
	}
	
	public class ClickFlipHorizontal implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.getCurrentSelectedObject().scale(-1, 1);
		}
	}
	
	public class ClickFlipVertical implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bStage.getCurrentSelectedObject().scale(1, -1);
		}
	}
	
}
