package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * 
 * Muestra la puntuación de la palabra que estamos formando.
 *
 */

public class WordScore extends Label {
	private int score;
	
	public WordScore() {
		super("Score: 0", 
			new LabelStyle(
				new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false), Color.BLACK));
		
		score = 0;
		
		setPosition(615, 235);
	}
	
	private void updateLabel() {
		setText("Score: " + score);
	}
	
	public void decreaseScore(int tileScore) {
		score -= tileScore;
		updateLabel();
	}
	
	public void increaseScore(int tileScore) {
		score += tileScore;
		updateLabel();
	}
	
	/* -------------- Getters and Setters -------------- */

	public void setScore(int score) {
		this.score = score;
		updateLabel();
	}
}
