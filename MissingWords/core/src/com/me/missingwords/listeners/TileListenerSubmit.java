package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.SubmitBox;
import com.me.missingwords.actors.Tile;

/**
 * 
 * Listener que se encarga de devolver la ficha al tilebox cuando no es necesaria para formar
 * la palabra.
 *
 */

public class TileListenerSubmit extends AbstractTileListener {
	
	public TileListenerSubmit(SubmitBox submitBox, Tile original, Tile copy, MissingWords missingWords) {
		super(submitBox, original, copy, missingWords);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		original.setVisible(true);  // Ponemos visible la ficha original
		submitBox.removeActor(copy); // Eliminamos la ficha copia del submitBox
		submitBox.decreaseNumActors(); // --NumActors;
		missingWords.getGameScreen().getWordScore().decreaseScore(original.getPoints());
	}
}
