package com.zapdos.puzzles;


import java.util.ArrayList;
import java.util.Collections;

import com.zapdos.environmentobjects.Button;
import com.zapdos.main.Sprite;

public class Puzzle4 extends Puzzle {
	private Sprite yellow, green, blue, red, lamp, signY, signG, signB, signR, reset;
	
	private static final String IMG_FOLDER = "puzzle_assets/";
	private static final String PUZZLE_NUM = "T_Puzzle4_";
	private boolean redPressed = false;
	private boolean bluePressed = false;
	private boolean yellowPressed = false;
	private boolean greenPressed = false;
	
	private String [] pressed = new String[]{"", "", "", ""};
	private int buttonCount = 0;
	
	ArrayList<Button> buttons;
	
	public Puzzle4() {
		yellow = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Yellow.png", 2106.0, -1929.0, 1.0);
		green = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Green.png", 1602.0, -512.0, 1.0);
		blue = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Blue.png", 2590.0, -1541.0, 1.0);
		red = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Red.png", 1595.0, 261.0, 1.0);
		lamp = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Lamp_Off.png", 1380.0, -1889.0, 1.0);
		signR = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign_Red.png", 1514.0, -2024.0, 0.5);
		signB = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign_Blue.png", 1669.0, -2024.0, 0.5);
		signY = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign_Yellow.png", 1831.0, -2024.0, 0.5);
		signG = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign_Green.png", 1994.0, -2024.0, 0.5);
		reset = new Sprite(IMG_FOLDER + "T_Reset_Button.png", 1440.0, -1995.0, 1.0);
		Collections.addAll(sprites, yellow, green, blue, red, lamp, signY, signG, signB, signR, reset);
	}

	
	private void checkVictory() {
		// Checks if the puzzle has been solved.
		//PUZZLE SOLUTION: Yellow, Green, Blue, Red
		if (pressed[0] == "Yellow" && pressed[1] == "Green" && pressed[2] == "Blue" && pressed[3] == "Red") {
			// If the code has reached this point, the puzzle has been solved correctly.
			onVictory();
		} else {
			lamp.setImage(IMG_FOLDER + PUZZLE_NUM + "Lamp_Wrong.png");
		}
	}
	
	@Override
	protected void onVictory() {
		solved = true;
		lamp.setImage(IMG_FOLDER + PUZZLE_NUM + "Lamp_On.png");
	}

	
	public void pressButton(String color) {
		switch (color) {
		case"Red":
			if (redPressed) {
				return;
			}
			redPressed = true;
			break;
		case"Blue":
			if (bluePressed) {
				return;
			}
			bluePressed = true;
			break;
		case"Yellow":
			if (yellowPressed) {
				return;
			}
			yellowPressed = true;
			break;
		case"Green":
			if (greenPressed) {
				return;
			}
			greenPressed = true;
			break;
		}
		pressed[buttonCount] = color;
		buttonCount++;
		if (buttonCount == 4) {
		checkVictory();
		}
	}
	
	public void resetPuzzle() {
		for (String s:pressed) {
			s = "";
		}
		buttonCount = 0;
		redPressed = bluePressed = yellowPressed = greenPressed = false;
		lamp.setImage(IMG_FOLDER + PUZZLE_NUM + "Lamp_Off.png");
	}
	
	public void setButtons(ArrayList<Button> bs) {
		buttons = bs;
	}
	
	
}
