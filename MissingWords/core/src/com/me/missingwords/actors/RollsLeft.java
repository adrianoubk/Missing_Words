package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * 
 * Muestra las tiradas que tiene el jugador.
 *
 */

public class RollsLeft extends Label {
	private final int POSITION_X = 180;
	private final int POSITION_Y = 85;
	
	private int rolls;

	public RollsLeft() {
		super(null, 
				new LabelStyle(
				new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false), 
				Color.WHITE));
		
		setText("Rolls Left: " + rolls);
		
		setPosition(POSITION_X, POSITION_Y);
	}
	
	/* update(): actualiza la etiqueta con las tiradas restantes */
	private void update() {
		setText("Rolls Left: " + rolls);
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
