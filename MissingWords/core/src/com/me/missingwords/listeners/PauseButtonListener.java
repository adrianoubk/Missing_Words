package com.me.missingwords.listeners;

/**
 * 
 * Listener que pausa el juego.
 * 
 */

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

public class PauseButtonListener extends ClickListener {
	private MissingWords missingWords;
	
	public PauseButtonListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Muestra el dialogo con el bot�n de resume */
		missingWords.getGameScreen().getPauseDialog().show(missingWords.getGameScreen().getStage());
		
		/* Oculta las fichas para evitar trampas */
		missingWords.getGameScreen().getTileBox().getTileTable().setVisible(false);
		
		/* Para el tiempo */
		missingWords.getGameScreen().getTimeBar().stop();
	}
}