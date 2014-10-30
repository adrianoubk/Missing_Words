package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener que mantiene en común la clase del juego con los demás listeners. Clase Abstracta.
 *
 */

public abstract class AbstractListener extends ClickListener {
	protected MissingWords missingWords;
	
	public AbstractListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	/* clicked(): método abstracto que se definirá en cada listener */
	@Override
	public abstract void clicked(InputEvent event, float x, float y);
}
