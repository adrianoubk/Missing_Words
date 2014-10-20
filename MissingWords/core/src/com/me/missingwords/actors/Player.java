package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

/**
 * 
 * Representa a un jugador (Abstracta).
 *
 */

public abstract class Player extends Actor {
	protected String name; // Nombre del jugador
	protected boolean isMyTurn; // Booleano que indica si es su turno o no
	protected int rolls; // Tiradas disponibles del jugador
	protected MissingWords missingWords;
	
	public Player(String name, MissingWords missingWords) {
		this.name = name;
		this.missingWords = missingWords;
		isMyTurn = false;
		rolls = 0;
	}

	/* calculateRolls(): calcula el numero de tiradas en base a los puntos */
	public void calculateRolls(int points) {
		if (points <= missingWords.getMin())
			rolls = 1;
		
		if (points > missingWords.getMin() && points < missingWords.getMax())
			rolls = 2;
		
		if (points >= missingWords.getMax())
			rolls = 3;
	}
	
	/* playMinigame(): Cambia a la pantalla del minijuego para jugarlo */
	public void playMinigame() {
		missingWords.setScreen(missingWords.MiniGameScreen);
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public boolean isMyTurn() {
		return isMyTurn;
	}

	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}
	
	public int getRolls() {
		return rolls;
	}
}
