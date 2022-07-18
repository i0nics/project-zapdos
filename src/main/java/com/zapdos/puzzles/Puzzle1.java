package com.zapdos.puzzles;


import java.util.Collections;
import com.zapdos.main.Sprite;

public class Puzzle1 extends Puzzle {
	private Sprite light1, light2, light3, light4, light5, signL, sign1, sign2, reset;
	
	private static final String IMG_FOLDER = "puzzle_assets/";
	private static final String PUZZLE_NUM = "T_Puzzle1_";
	
	private boolean [] light_array = new boolean[]{false, false, false, false, false};
	
	public Puzzle1() {
		light1 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 6856.0, 56.0, 1.0);
		light2 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 6931.0, 56.0, 1.0);
		light3 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 7006.0, 56.0, 1.0);
		light4 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 7081.0, 56.0, 1.0);
		light5 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Light_Off.png", 7156.0, 56.0, 1.0);
		signL = new Sprite(IMG_FOLDER + PUZZLE_NUM + "SignL.png", 6070.0, 337.0, 0.5);
		sign1 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign1.png", 6333.0, 337.0, 0.5);
		sign2 = new Sprite(IMG_FOLDER + PUZZLE_NUM + "Sign2.png", 6625.0, 337.0, 0.5);
		reset = new Sprite(IMG_FOLDER + "T_Reset_Button.png", 6714.0, 278.0, 1.0);
		Collections.addAll(sprites, light1, light2, light3, light4, light5, signL, sign1, sign2, reset);
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
		// Invert lights 3 & 4
		light_array[2] = !light_array[2];
		light_array[3] = !light_array[3];
		updateAllLights();
		checkVictory();
	}
	
	public void pressButton2() {
		// Invert lghts 2 & 5
		light_array[1] = !light_array[1];
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
