package com.zapdos.audio;

import com.zapdos.main.AudioPlayer;
import com.zapdos.main.MainApplication;

import acm.program.Program;


public class SFXPlayer {
	private AudioPlayer audio;
	private static boolean isSFXOn = true;
	private static final String SFX_FOLDER = "sfx";
	private static final String CLICK_SFX = "click.mp3";
	private static final String JUMP_SFX = "SFX_MM_Jump.mp3";
	private static final String LAND_SFX = "SFX_MM_Land.mp3";
	private static final String SHOOT_SFX = "SFX_MM_Shoot.mp3";
	
	public  SFXPlayer() {
		audio =  AudioPlayer.getInstance();
	}
	
	public void toggleSFX() {
		isSFXOn = !isSFXOn;
	}

	public boolean getIsSFXOn() {
		return isSFXOn;
	}
	
	public void playClickSound() {
		if (isSFXOn) audio.playSound(SFX_FOLDER, CLICK_SFX);
	}
	
	public void playJumpSound() {
		if (isSFXOn) audio.playSound(SFX_FOLDER, JUMP_SFX);
	}
	
	public void playShootSound() {
		if (isSFXOn) audio.playSound(SFX_FOLDER, SHOOT_SFX);
	}
	
	public void playLandSound() {
		if (isSFXOn) audio.playSound(SFX_FOLDER, LAND_SFX);
	}

}
