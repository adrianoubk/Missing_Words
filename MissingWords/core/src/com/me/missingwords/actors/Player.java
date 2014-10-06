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
	protected MissingWords missingWords;
	
	public Player(String name, MissingWords missingWords) {
		
		this.name = name;
		this.missingWords = missingWords;
		isMyTurn = false;
	}

	public boolean isMyTurn() {
		return isMyTurn;
	}

	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}
	
	public void playMinigame() {
		missingWords.setScreen(missingWords.MiniGameScreen);
	}
}
