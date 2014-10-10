package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.actors.SubmitBox;
import com.me.missingwords.actors.Tile;

/**
 * 
 * Clase AbstractTileListener
 * 
 * Clase abstracta que se encarga de ejecutar los eventos de click producidos en las fichas. 
 * Estos son:
 * 1. Tocar la ficha para enviarla al submitbox (TileListenerTable).
 * 2. Tocar la ficha para quitarla del submitbox y llevarla al tilebox (TileListenerSubmit).
 *
 */

public abstract class AbstractTileListener extends ClickListener {
	
	protected SubmitBox submitBox;
	protected Tile original, copy;
	
	public AbstractTileListener(SubmitBox submitBox, Tile original, Tile copy)  {
		this.submitBox = submitBox;
		this.original = original;
		this.copy = copy;
	}
	
	/* clicked() será el método abstracto que definiremos en los listeners que hereden de la
	 * clase abstracta 
	 */
	
	@Override
	public abstract void clicked(InputEvent event, float x, float y);
}
