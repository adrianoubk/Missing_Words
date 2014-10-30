package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener del botón continuar del minijuego. Cambia a la pantalla principal de juego.
 *
 */

public class ContinueMiniListener extends AbstractListener {
	
	public ContinueMiniListener(MissingWords missingWords) {
		super(missingWords);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		missingWords.setScreen(missingWords.GameScreen);
	}
}
