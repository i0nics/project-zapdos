package com.zapdos.puzzles;


import java.util.Collections;
import com.zapdos.main.Sprite;

public class Puzzle2 extends Puzzle {
	private Sprite light1, light2, light3, light4, light5, signX, sign1, sign2, reset;
	
	private static final String IMG_FOLDER = "puzzle_assets/";
	private static final String PUZZLE_NUM = "T_Puzzle2_";
	
	private boolean [] light_array = new boolean[]{false, false, false, false, false};
	
	public Puzzle2() {
		light1 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 6325.0, -752.0, 1.0);
		light2 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 6395.0, -752.0, 1.0);
		light3 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 6465.0, -752.0, 1.0);
		light4 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 6535.0, -752.0, 1.0);
		light5 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 6605.0, -752.0, 1.0);
		signX = new Sprite(IMG_FOLDER + PUZZLE_NUM + "SignX.png", 6359.0, -317.0, 0.5);
		sign1 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign1.png", 6503.0, -368.0, 0.5);
		sign2 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign2.png", 6640.0, -405.0, 0.5);
		reset = new Sprite(IMG_FOLDER + "T_Reset_Button.png", 6723.0, -477.0, 1.0);
		Collections.addAll(sprites, light1, light2, light3, light4, light5, signX, sign1, sign2, reset);
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

	
	public void pressButtonX() {
		// Turn off lights 4 and 5
		light_array[3] = false;
		light_array[4] = false;
		updateAllLights();
		checkVictory();
	}
	
	public void pressButton1() {
		// Invert lights 1, 3 & 4
		light_array[0] = !light_array[0];
		light_array[2] = !light_array[2];
		light_array[3] = !light_array[3];
		updateAllLights();
		checkVictory();
	}
	
	public void pressButton2() {
		// Invert lghts 2, 4 & 5
		light_array[1] = !light_array[1];
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
