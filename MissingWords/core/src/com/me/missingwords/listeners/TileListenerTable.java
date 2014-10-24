package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.SubmitBox;
import com.me.missingwords.actors.Tile;

/**
 * 
 * Listener que envia la ficha al submitBox para comenzar a formar palabras.
 *
 */

public class TileListenerTable extends AbstractTileListener {
	
	public TileListenerTable(SubmitBox submitBox, Tile original, Tile copy, MissingWords missingWords) {
		super(submitBox, original, copy, missingWords);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		original.setVisible(false); // Oculta la ficha original
		copy.setSmallSize(); // Cambia el tamaño de la ficha copia al enviarla al submitBox
		submitBox.addActor(copy); // Añade la ficha al submitBox
		submitBox.increaseNumActors(); // ++numActors;
		missingWords.getGameScreen().getWordScore().increaseScore(original.getPoints());
	}
}
