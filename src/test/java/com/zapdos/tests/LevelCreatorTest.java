package com.zapdos.tests;


import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;


import com.zapdos.stage.BaseStage2;

class LevelCreatorTest {
	private static BaseStage2 bStage2;
	private static int stageLen;
	private static final double PLATFORM_X_COORD = 100.0;
	private static final double PLATFORM_Y_COORD = 200.0;
	private static final double PLATFORM_VERT_X_COORD = 500.0;
	private static final double PLATFORM_VERT_Y_COORD = 500.0;
	private static final double REVERBOT_X_COORD = 110.0;
	private static final double REVERBOT_Y_COORD = 120.0;
	private static final int NUM_REVERBOTS = 2;
	private static final boolean IS_JUNIT_TEST_MODE = true;

	@BeforeClass
	public static void beforeClass() {
		// Add dummy env objects
		bStage2 = new BaseStage2(null, IS_JUNIT_TEST_MODE);
//		bStage2.addPlatform(PLATFORM_X_COORD, PLATFORM_Y_COORD);
//		bStage2.addPlatformVert(PLATFORM_VERT_X_COORD, PLATFORM_VERT_Y_COORD);
//		bStage2.addReverbot(REVERBOT_X_COORD, REVERBOT_Y_COORD);
//		bStage2.addReverbot(REVERBOT_X_COORD + 10, REVERBOT_Y_COORD + 20);

		// Save current stage to JSON
		bStage2.getStageCreator().saveStage();
		stageLen = new File("src/main/resources/stages/").list().length - 1;
		bStage2.setStageNum(stageLen);

		// Load new stage file
		bStage2.reset();
	}

	@Test
	public void testNumReverBots() {
		// Setup
		beforeClass();
		
		// Check if number of rever bots added match
		assertEquals(bStage2.getEnemies().size(), NUM_REVERBOTS);
	}

	@Test
	public void testPlatformCoords() {
		// Check if coordinates of added platform match
		assertEquals(bStage2.getPlatforms().get(0).getX(), PLATFORM_X_COORD);
		assertEquals(bStage2.getPlatforms().get(0).getY(), PLATFORM_Y_COORD);
	}

	@Test
	public void testPlatformVertCoords() {
		// Check if coordinates of added vertical platform match
		assertEquals(bStage2.getPlatforms().get(1).getX(), PLATFORM_VERT_X_COORD);
		assertEquals(bStage2.getPlatforms().get(1).getY(), PLATFORM_VERT_Y_COORD);
	}

	@Test
	public void testReverBotCoords() {
		// Check if coordinates of added enemy match
		assertEquals(bStage2.getEnemies().get(0).getX(), REVERBOT_X_COORD);
		assertEquals(bStage2.getEnemies().get(0).getY(), REVERBOT_Y_COORD);
		assertNotEquals(bStage2.getEnemies().get(0).getY(), REVERBOT_Y_COORD + 10);

		// Remove test stage JSON file
		AfterClass();
	}

	// Remove test stage file
	@org.junit.AfterClass
	public static void AfterClass() {

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
