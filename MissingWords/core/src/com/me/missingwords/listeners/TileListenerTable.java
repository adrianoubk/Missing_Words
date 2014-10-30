package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Tile;

/**
 * 
 * Listener que envia la ficha al submitBox para comenzar a formar palabras.
 *
 */

public class TileListenerTable extends AbstractListener {
	private Tile original, copy;
	
	public TileListenerTable(Tile original, Tile copy, MissingWords missingWords) {
		super(missingWords);
		this.original = original;
		this.copy = copy;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getTap().play(missingWords.getSoundFX().getVolume());
		
		original.setVisible(false); // Oculta la ficha original
		copy.setSmallSize(); // Cambia el tamaño de la ficha copia al enviarla al submitBox
		missingWords.getGameScreen().getSubmitBox().addActor(copy); // Añade la ficha
		missingWords.getGameScreen().getSubmitBox().increaseNumActors(); // ++numActors;
		
		missingWords.getGameScreen().getWordScore().increaseScore(original.getPoints());
	}
}
