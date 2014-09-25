package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.screens.GameScreen;

/**
 * 
 * Clase que representa al jugador humano.
 *
 */

public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name);
	}
	
	/* 
	 * Método con el que el jugador juega su turno y confirma la palabra 
	 */
	
	public void playTurn() {
		
		setMyTurn(false); // Acaba su turno
		
		/* Activamos el turno de la máquina */
		
		game.getNpc().setTurnFinished(true); 
		game.getNpc().setMyTurn(true); 
	}
	
	/* Método que controla si el jugador puede interactuar con la pantalla */
	
	public void touchScreen(Touchable touchable) {
		game.getTileBox().setTouchable(touchable);
		game.getSubmitBox().setTouchable(touchable);
		game.getLetterClue().setTouchable(touchable);
		game.getLengthClue().setTouchable(touchable);
		game.getTranslationClue().setTouchable(touchable);
		game.getSubmit().setTouchable(touchable);
	}

	@Override
	public void getGameData(MissingWords missingWords) {
		game = (GameScreen) missingWords.getGameScreen();
	}
}
