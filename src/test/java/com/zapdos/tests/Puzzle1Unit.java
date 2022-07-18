package com.zapdos.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.zapdos.puzzles.*;

class Puzzle1Unit {

	@Test
	void Puzzle1_solvable() {
		Puzzle1 test = new Puzzle1();
		test.pressButton1();
		test.pressButtonL();
		test.pressButtonL();
		test.pressButtonL();
		test.pressButton1();
		test.pressButton2();
		assertTrue(test.isSolved());
	}
	
	@Test
	void Puzzle1_reset() {
		Puzzle1 test = new Puzzle1();
		test.pressButton1();
		test.pressButtonL();
		
		test.resetPuzzle();
		
		test.pressButton1();
		test.pressButtonL();
		test.pressButtonL();
		test.pressButtonL();
		test.pressButton1();
		test.pressButton2();
		assertTrue(test.isSolved());
	}
}
