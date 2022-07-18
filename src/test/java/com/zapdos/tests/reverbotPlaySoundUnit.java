package com.zapdos.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.zapdos.enemies.Enemy;

import acm.graphics.GRect;

class reverbotPlaySoundUnit {

	@Test
	void test() {
		Enemy test = new Enemy("T_Goomba.gif", 0, 0);
		GRect Player = new GRect(750, 500, 100, 100);
		test.updateEnemy();
		assertEquals(true, test.canPlaySound);	}

}
