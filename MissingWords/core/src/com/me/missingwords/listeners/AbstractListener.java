package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener que mantiene en com�n la clase del juego con los dem�s listeners. Clase Abstracta.
 *
 */

public abstract class AbstractListener extends ClickListener {
	protected MissingWords missingWords;
	
	public AbstractListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	/* clicked(): m�todo abstracto que se definir� en cada listener */
	@Override
	public abstract void clicked(InputEvent event, float x, float y);
}
