package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Muestra la puntuación de la palabra que estamos formando.
 *
 */

public class WordScore extends Label {
	private int score;
	private int penalties;
	private MissingWords missingWords;
	
	public WordScore(MissingWords missingWords) {
		super("Score: 0", 
			new LabelStyle(
				new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false), Color.BLACK));
		
		this.missingWords = missingWords;
		
		score = 0;
		penalties = 0;
		
		setPosition(615, 235); // 615
	}
	
	private void updateLabel() {
		if (penalties > 0) {
			if (missingWords.selectedLanguage == Language.english)
				setText("Score: " + score + "\n(" + "-" + penalties + " rolls)");
			else
				setText("Punkt: " + score + "\n(" + "-" + penalties + " würfeln)");
		}
		
		if (penalties == 0) {
			if (missingWords.selectedLanguage == Language.english)
				setText("Score: " + score);
			else
				setText("Punkt: " + score);
		}
	}
	
	public void decreaseScore(int tileScore) {
		score -= tileScore;
		updateLabel();
	}
	
	public void increaseScore(int tileScore) {
		score += tileScore;
		updateLabel();
	}
	
	public void increasePenalties(int penalties) {
		this.penalties += penalties;
		updateLabel();
	}
	
	/* -------------- Getters and Setters -------------- */

	public void setScore(int score) {
		this.score = score;
		updateLabel();
	}
	
	public void setPenalties(int penalties) {
		this.penalties = penalties;
		updateLabel();
	}
}
