package com.zapdos.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;


import com.zapdos.stage.BaseStage2;

public class StageResetTest {
	private static final double PLATFORM_X_COORD = 100.0;
	private static final double PLATFORM_Y_COORD = 200.0;
	private static final double PLATFORM_VERT_X_COORD = 500.0;
	private static final double PLATFORM_VERT_Y_COORD = 500.0;
	private static final double REVERBOT_X_COORD = 110.0;
	private static final double REVERBOT_Y_COORD = 120.0;
	private static BaseStage2 bStage2;
	private static final boolean IS_JUNIT_TEST_MODE = true;
	private static int stageLen;
	private static double moveSpriteByX = 30.0;
	private static double moveSpriteByY = 20.0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bStage2 = new BaseStage2(null, IS_JUNIT_TEST_MODE);
//		bStage2.addPlatform(PLATFORM_X_COORD, PLATFORM_Y_COORD);
//		bStage2.addPlatformVert(PLATFORM_VERT_X_COORD, PLATFORM_VERT_Y_COORD);
//		bStage2.addReverbot(REVERBOT_X_COORD, REVERBOT_Y_COORD);
//		bStage2.addReverbot(REVERBOT_X_COORD + 10, REVERBOT_Y_COORD + 20);
		
		// Save current stage to JSON
		bStage2.getStageCreator().saveStage();
		stageLen = new File("src/main/resources/stages/").list().length - 1;
		bStage2.setStageNum(stageLen);
		
		// Move background image sprite to different location
	    bStage2.getBackgroundSprite().move(moveSpriteByX, moveSpriteByY);
	    
	    // Move environment sprites by moveSpriteByX
		bStage2.getPlatforms().get(0).move(moveSpriteByX, moveSpriteByY);
		bStage2.getPlatforms().get(1).move(moveSpriteByX, moveSpriteByY);
		bStage2.getEnemies().get(0).move(moveSpriteByX, moveSpriteByY);
		bStage2.getEnemies().get(1).move(moveSpriteByX, moveSpriteByY);
		
		bStage2.reset();
	}
	
	@Test
	public void testIfReverBotLocationsAreReset() {
		try {
			setUpBeforeClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(bStage2.getEnemies().get(0).getX() == REVERBOT_X_COORD);
		assertTrue(bStage2.getEnemies().get(0).getY() == REVERBOT_Y_COORD);
		assertTrue(bStage2.getEnemies().get(1).getX() == REVERBOT_X_COORD + 10);
		assertTrue(bStage2.getEnemies().get(1).getY() == REVERBOT_Y_COORD + 20);
	}

	@Test
	public void testIfBackgroundLocationIsReset() {
		assertTrue(bStage2.getBackgroundSprite().getX() == 0.0);
	}
	
	@Test
	public void testIfPlatformLocationIsReset() {
		assertTrue(bStage2.getPlatforms().get(0).getX() == PLATFORM_X_COORD);
		assertTrue(bStage2.getPlatforms().get(0).getY() == PLATFORM_Y_COORD);
	}
	
	@Test
	public void testIfPlatformVertLocationIsReset() {
		assertTrue(bStage2.getPlatforms().get(1).getX() == PLATFORM_VERT_X_COORD);
		assertTrue(bStage2.getPlatforms().get(1).getY() == PLATFORM_VERT_Y_COORD);
		
		// Delete test JSON file
		try {
			tearDownAfterClass();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try {
			Files.deleteIfExists(Paths.get("src/main/resources/stages/stage" + stageLen + ".json"));
		} catch (NoSuchFileException e) {
			System.out.println("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty.");
		} catch (IOException e) {
			System.out.println("Invalid permissions.");
		}

		System.out.println("Test JSON File Deletion successful.");
	}

}
