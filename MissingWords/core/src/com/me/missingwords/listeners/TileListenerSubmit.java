package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Tile;

/**
 * 
 * Listener que se encarga de devolver la ficha al tilebox cuando no es necesaria para formar
 * la palabra.
 *
 */

public class TileListenerSubmit extends AbstractListener {
	private Tile original, copy;
	
	public TileListenerSubmit(Tile original, Tile copy, MissingWords missingWords) {
		super(missingWords);
		this.original = original;
		this.copy = copy;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getTap().play(missingWords.getSoundFX().getVolume());
		
		original.setVisible(true);  // Ponemos visible la ficha original
		missingWords.getGameScreen().getSubmitBox().removeActor(copy); // Eliminamos la copia
		missingWords.getGameScreen().getSubmitBox().decreaseNumActors(); // --NumActors;
		
		missingWords.getGameScreen().getWordScore().decreaseScore(original.getPoints());
	}
}
