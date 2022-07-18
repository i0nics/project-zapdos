package com.zapdos.audio;

import com.zapdos.main.AudioPlayer;

public class MusicPlayer {
	private static final String MUSIC_FOLDER = "music";
	private static final String ASK_TRACK = "ask_yourself.mp3";
	private static final String BGTKY_TRACK = "bgtky.mp3";
	private static final String FGHOST_TRACK = "forest_ghost.mp3";
	private static final String PUNIV_TRACK = "parallel_universes.mp3";
	private static final String SAE_TRACK = "selling_an_experience.mp3";
	private static final String RETRO_TRACK = "Sound_LevelOne.mp3";
	private static final String V_TRACK = "v.mp3";
	private static final String MAINMENU_TRACK = "MainMenuTheme.mp3";
	private static boolean isMusicPlaying = false;
	private static boolean playMusic = true;
	private static String currentTrack;
	private static AudioPlayer audio;

	public MusicPlayer() {
		audio = AudioPlayer.getInstance();
	}
	
	public void playMenuTrack() {
		checkMusic();
		currentTrack = V_TRACK;
		if (playMusic) audio.playSound(MUSIC_FOLDER, V_TRACK, true);
	}
	
	public void playAskTrack() {
		checkMusic();
		currentTrack = ASK_TRACK;
		if (playMusic) audio.playSound(MUSIC_FOLDER, ASK_TRACK, true);
	}
	
	public void playBGTKYTrack() {
		checkMusic();
		currentTrack = BGTKY_TRACK;
		if (playMusic) audio.playSound(MUSIC_FOLDER, BGTKY_TRACK, true);
	}
	
	public void playForestGhostTrack() {
		checkMusic();
		currentTrack = FGHOST_TRACK;
		if (playMusic) audio.playSound(MUSIC_FOLDER, FGHOST_TRACK, true);
	}
	
	public void playParallelUniversesTrack() {
		checkMusic();
		currentTrack = PUNIV_TRACK;
		if (playMusic) audio.playSound(MUSIC_FOLDER, PUNIV_TRACK, true);
	}
	
	public void playSAETrack() {
		checkMusic();
		currentTrack = SAE_TRACK;
		if (playMusic) audio.playSound(MUSIC_FOLDER, SAE_TRACK, true);
	}
	
	public void playRetroTrack() {
		checkMusic();
		currentTrack = RETRO_TRACK;
		if (playMusic) audio.playSound(MUSIC_FOLDER, RETRO_TRACK, true);
	}

	public void playVTrack() {
		checkMusic();
		currentTrack = V_TRACK;
		if (playMusic) audio.playSound(MUSIC_FOLDER, V_TRACK, true);
	}

	public void playThisTrack(String track) {
		checkMusic();
		currentTrack = track;
		if (playMusic) audio.playSound(MUSIC_FOLDER, currentTrack, true);
	}

	public void play() {
		isMusicPlaying = true;
		audio.playSound(MUSIC_FOLDER, currentTrack, true);
	}

	public void stop() {
		isMusicPlaying = false;
		audio.stopSound(MUSIC_FOLDER, currentTrack);
	}

	public void pause() {
		isMusicPlaying = false;
		audio.pauseSound(MUSIC_FOLDER, currentTrack);
	}

	public boolean isMusicPlaying() {
		return isMusicPlaying;
	}

	public void toggleMusic() {
		playMusic = !playMusic;
		if (playMusic) play();
		else pause();
	}
	
	private void checkMusic() {
		if (isMusicPlaying) stop();
		isMusicPlaying = true;
	}
	
	public String getCurrentTrack() {
		return currentTrack;
	}
}
