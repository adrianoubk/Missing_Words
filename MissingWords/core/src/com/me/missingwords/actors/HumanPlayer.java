package com.me.missingwords.actors;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.missingwords.MissingWords;

/**
 * 
 * Representa al jugador humano.
 *
 */

public class HumanPlayer extends Player {
	private ArrayList<String> playedWords;
	
	public HumanPlayer(String name, MissingWords missingWords) {
		super(name, missingWords);
		
		/* Creamos el array con las palabras jugadas por el jugador */
		playedWords = new ArrayList<>();
	}
		
	/* 
	 * playTurn(): método con el que el jugador juega su turno. Confirma la palabra creada
	 * y juega el minijuego.
	 */
	public void playTurn() {
		playMinigame(); // Juega el minijuego
		
		/* Restablecemos el score */
		missingWords.getGameScreen().getWordScore().setScore(0);
		
		setMyTurn(false); // Acaba su turno
		
		/* Si la cpu falla. ?????? 
		int result = new Random().nextInt(10 - 1 + 1) + 1; // max = 10 y min = 1
		
		if (result <= 9) {
			Label confused = new Label("NPC is confused and don't remember any word. So it's your turn again :)", new LabelStyle(new BitmapFont(), Color.BLACK));
			confused.setPosition((MissingWords.VIEWPORT_WIDTH - confused.getMinWidth()) / 2, 50);
			confused.addAction(Actions.fadeOut(5));
			missingWords.getGameScreen().getStage().addActor(confused);
			missingWords.getGameScreen().getNpc().setMyTurn(false);
		}*/
		
		/* Activamos el turno de la máquina. Solo para el modo PLAYER VS CPU */	
		if (!missingWords.isSinglePlayer()) {
		missingWords.getGameScreen().getNpc().setTurnFinished(true); 
		missingWords.getGameScreen().getNpc().setMyTurn(true);
		}	
	}
	
	/* touchScreen(): controla si el jugador puede interactuar con la pantalla */
	public void touchScreen(Touchable touchable) {
		missingWords.getGameScreen().getTileBox().setTouchable(touchable);
		missingWords.getGameScreen().getSubmitBox().setTouchable(touchable);
		missingWords.getGameScreen().getLetterClue().setTouchable(touchable);
		missingWords.getGameScreen().getLengthClue().setTouchable(touchable);
		missingWords.getGameScreen().getTranslationClue().setTouchable(touchable);
		missingWords.getGameScreen().getSubmit().setTouchable(touchable);
	}
	
	/* addPlayedWord(): añade una palabra a la lista de palabras jugadas */
	public void addPlayedWord(String word) {
		if (!playedWords.contains(word))
			playedWords.add(word);
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public ArrayList<String> getPlayedWords() {
		return playedWords;
	}
}
