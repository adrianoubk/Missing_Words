package com.me.missingwords.sound;

import com.badlogic.gdx.audio.Sound;
import com.me.missingwords.MissingWords;

/**
 * 
 * Gestor de sonido.
 *
 */

public class SoundFX {
	private Sound button, clue, positiveSound, negativeSound, tap, roll, win, hole, timeout;
	private int volume;
	
	public SoundFX() {
		button = MissingWords.myManager.get("sounds/menu.wav", Sound.class);
		clue = MissingWords.myManager.get("sounds/clue.mp3", Sound.class);
		positiveSound = MissingWords.myManager.get("sounds/positive.wav", Sound.class);
		negativeSound = MissingWords.myManager.get("sounds/negative.wav", Sound.class);
		tap = MissingWords.myManager.get("sounds/tap.ogg", Sound.class);
		roll = MissingWords.myManager.get("sounds/roll.mp3", Sound.class);
		win = MissingWords.myManager.get("sounds/win.wav", Sound.class);
		hole = MissingWords.myManager.get("sounds/hole.wav", Sound.class);
		timeout = MissingWords.myManager.get("sounds/timeout.mp3", Sound.class);
		
		volume = 1;
	}
	
	/* -------------- Getters and Setters -------------- */

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public Sound getButton() {
		return button;
	}

	public Sound getClue() {
		return clue;
	}

	public Sound getPositiveSound() {
		return positiveSound;
	}

	public Sound getNegativeSound() {
		return negativeSound;
	}

	public Sound getTap() {
		return tap;
	}

	public Sound getRoll() {
		return roll;
	}

	public Sound getWin() {
		return win;
	}

	public Sound getHole() {
		return hole;
	}

	public Sound getTimeout() {
		return timeout;
	}
}
