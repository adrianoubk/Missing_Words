package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

/**
 * 
 * Clase abstracta que representa un jugador.
 *
 */

public abstract class Player extends Actor {
	
	protected String name;
	protected boolean isMyTurn;
	protected int rolls;
	protected MissingWords missingWords;
	
	public Player(String name, MissingWords missingWords) {
		
		this.name = name;
		this.missingWords = missingWords;
		isMyTurn = false;
		rolls = 0;
	}

	public boolean isMyTurn() {
		return isMyTurn;
	}

	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}
	
	public int getRolls() {
		return rolls;
	}

	public void calculateRolls(int points) {
		if (points <= missingWords.getMin())
			rolls = 1;
		
		if (points > missingWords.getMin() && points < missingWords.getMax())
			rolls = 2;
		
		if (points >= missingWords.getMax())
			rolls = 3;
	}
	
	public void playMinigame() {
		missingWords.setScreen(missingWords.MiniGameScreen);
	}
}
