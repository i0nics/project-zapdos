package com.zapdos.puzzles;


import java.util.Collections;
import com.zapdos.main.Sprite;

public class Puzzle3 extends Puzzle {
	private Sprite light1, light2, light3, light4, light5, signL, sign1, sign2, sign3, reset;
	
	private static final String IMG_FOLDER = "puzzle_assets/";
	private static final String PUZZLE_NUM = "T_Puzzle3_";
	
	private boolean [] light_array = new boolean[]{false, false, false, false, false};
	
	public Puzzle3() {
		light1 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 2787.0, -936.0, 1.0);
		light2 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 2857.0, -936.0, 1.0);
		light3 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 2927.0, -936.0, 1.0);
		light4 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 2997.0, -936.0, 1.0);
		light5 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 3067.0, -936.0, 1.0);
		signL = new Sprite(IMG_FOLDER + PUZZLE_NUM + "SignL.png", 2761.0, -627.0, 0.5);
		sign1 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign1.png", 2834.0, -683.0, 0.5);
		sign2 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign2.png", 3047.0, -683.0, 0.5);
		sign3 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign3.png", 3122.0, -627.0, 0.5);
		reset = new Sprite(IMG_FOLDER + "T_Reset_Button.png", 2936.0, -633.0, 1.0);
		Collections.addAll(sprites, light1, light2, light3, light4, light5, signL, sign1, sign2, sign3, reset);
	}

	
	private void checkVictory() {
		// Checks if the puzzle has been solved.
		for (int i = 0; i < 5; i++) {
			if (!light_array[i]) {
				return;
			}
		}
		// If the code has reached this point, the puzzle has been solved correctly.
		onVictory();
	}

	
	public void pressButtonL() {
		// Shift all lights left by 1
		light_array[0] = light_array[1];
		light_array[1] = light_array[2];
		light_array[2] = light_array[3];
		light_array[3] = light_array[4];
		light_array[4] = false;
		updateAllLights();
		checkVictory();
	}
	
	public void pressButton1() {
		// Invert light 3
		light_array[2] = !light_array[2];
		updateAllLights();
		checkVictory();
	}
	
	public void pressButton2() {
		// Invert lghts 1 & 4, and turns off all other lights.
		light_array[0] = !light_array[0];
		light_array[3] = !light_array[3];

		light_array[1] = false;
		light_array[2] = false;
		light_array[4] = false;
		updateAllLights();
		checkVictory();
	}
	
	public void pressButton3() {
		// Invert lghts 1, 4 & 5
		light_array[0] = !light_array[0];
		light_array[3] = !light_array[3];
		light_array[4] = !light_array[4];
		updateAllLights();
		checkVictory();
	}
	
	public void resetPuzzle() {
		for(int i = 0; i < 5; i++) {
			light_array[i] = false;
		}
		updateAllLights();
	}
	
	private void updateAllLights() {
		String toAdd;
		for (int i = 0; i < 5; i++) {
			toAdd = IMG_FOLDER + PUZZLE_NUM + "Light_" + (light_array[i] ? "On.png" : "Off.png");
			sprites.get(i).setImage(toAdd);
			//System.out.println("Updated light " + i);
		}
	}
	
}
