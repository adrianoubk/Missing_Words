package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Muestra las tiradas que tiene el jugador.
 *
 */

public class RollsLeft extends Label {
	private final int POSITION_X = 180;
	private final int POSITION_Y = 90;
	
	private int rolls;
	private MissingWords missingWords;

	public RollsLeft(MissingWords missingWords) {
		super(null, 
				new LabelStyle(
				new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false), 
				Color.WHITE));
		
		this.missingWords = missingWords;
		
		setPosition(POSITION_X, POSITION_Y);
		
		if (missingWords.selectedLanguage == Language.english)
			setText("Rolls Left: " + rolls);
		else
			setText("Verbleibende\nWürfel: " +  rolls);
	}
	
	/* update(): actualiza la etiqueta con las tiradas restantes */
	private void update() {
		if (missingWords.selectedLanguage == Language.english)
			setText("Rolls Left: " + rolls);
		else
			setText("Verbleibende\nWürfel: " +  rolls);
	}
	
	public void decreaseRolls() {
		--rolls;
		update();
	}

	/* -------------- Getters and Setters -------------- */
	
	public int getRolls() {
		return rolls;
	}

	public void setRolls(int rolls) {
		this.rolls = rolls;
		
		if (rolls != -1) // Si es -1, no se actualiza, es la condición de parada
			update();
	}
}
