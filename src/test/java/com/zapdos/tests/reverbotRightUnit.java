package com.zapdos.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.zapdos.enemies.Enemy;

class reverbotRightUnit {

	@Test
	void test() {

		Enemy test = new Enemy("T_Goomba.gif", 0, 0);
		test.reverbotRight();
		assertEquals("T_ReverbotMoveRight.gif", test.getFileName());
	}

}
