package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.missingwords.MissingWords;

/**
 * 
 * Clase que representa al jugador humano.
 *
 */

public class HumanPlayer extends Player {

	public HumanPlayer(String name, MissingWords missingWords) {
		super(name, missingWords);
	}
	
	/* 
	 * Método con el que el jugador juega su turno y confirma la palabra 
	 */
	
	public void playTurn() {
		
		playMinigame();
		
		setMyTurn(false); // Acaba su turno
		
		/* Activamos el turno de la máquina. Solo para el modo PLAYER VS CPU */
		
		if (!missingWords.isSinglePlayer()) {
			missingWords.getGameScreen().getNpc().setTurnFinished(true); 
			missingWords.getGameScreen().getNpc().setMyTurn(true);
		}
	}
	
	/* Método que controla si el jugador puede interactuar con la pantalla */
	
	public void touchScreen(Touchable touchable) {
		missingWords.getGameScreen().getTileBox().setTouchable(touchable);
		missingWords.getGameScreen().getSubmitBox().setTouchable(touchable);
		missingWords.getGameScreen().getLetterClue().setTouchable(touchable);
		missingWords.getGameScreen().getLengthClue().setTouchable(touchable);
		missingWords.getGameScreen().getTranslationClue().setTouchable(touchable);
		missingWords.getGameScreen().getSubmit().setTouchable(touchable);
	}
}
