package com.me.missingwords.listeners;

/**
 * 
 * Listener que pausa el juego.
 * 
 */

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;

public class PauseButtonListener extends AbstractListener {
	
	public PauseButtonListener(MissingWords missingWords) {
		super(missingWords);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		/* Muestra el dialogo con el botón de resume */
		missingWords.getGameScreen().getPauseDialog().show(missingWords.getGameScreen().getStage());
		
		/* Oculta las fichas para evitar trampas */
		missingWords.getGameScreen().getTileBox().getTileTable().setVisible(false);
		
		/* Para el tiempo */
		missingWords.getGameScreen().getTimeBar().stop();
		
		/* Si no es singleplayer y es el turno de la npc, reanudamos su temporizador */
		if (!missingWords.isSinglePlayer()) 
			if (missingWords.getGameScreen().getNpc().isMyTurn())
				missingWords.getGameScreen().getNpc().getNpcTimer().stop();
	}
}
