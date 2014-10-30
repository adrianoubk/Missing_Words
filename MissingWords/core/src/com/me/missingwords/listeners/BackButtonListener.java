package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener del botón back. Nos devuelve a la pantalla anterior.
 *
 */

public class BackButtonListener extends AbstractListener {
	
	public BackButtonListener(MissingWords missingWords) {
		super(missingWords);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		missingWords.setScreen(missingWords.MenuScreen);
	}
}
