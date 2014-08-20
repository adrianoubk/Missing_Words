package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.me.missingwords.actors.SubmitBox;
import com.me.missingwords.actors.Tile;

/**
 * 
 * Clase TileListenerTable
 * 
 * Este listener envia la ficha al submitBox para comenzar a formar palabras.
 *
 */

public class TileListenerTable extends AbstractTileListener {
	
	public TileListenerTable(SubmitBox submitBox, Tile original, Tile copy)  {
		super(submitBox, original, copy);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		original.setVisible(false); // Oculta la ficha original
		copy.setSmallSize(); // Cambia el tamaño de la ficha copia al enviarla al submitBox
		submitBox.addActor(copy); // Añade la ficha al submitBox
		submitBox.increaseNumActors(); // ++numActors;
	}
}
