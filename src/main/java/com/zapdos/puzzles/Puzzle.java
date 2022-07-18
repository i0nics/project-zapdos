package com.zapdos.puzzles;


import java.util.ArrayList;
import com.zapdos.main.Sprite;

public class Puzzle {
	protected boolean solved = false;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	
	protected void onVictory() {
		solved = true;
	}
	
	public void setNotSolved() {
		solved = false;
	}
	
	public boolean isSolved() {
		return solved;
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	
}
