package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener de selección de menus. Clase abstracta. Listeners que heredan:
 * 
 * 1. GameModeSelectionListener
 * 2. CategorySelectionListener
 *
 */

public abstract class AbstractSelectionListener extends ClickListener {	
	protected String option;
	protected MissingWords missingWords;
	
	public AbstractSelectionListener(String option, MissingWords missingWords) {
		this.option = option;
		this.missingWords = missingWords;
	}
	
	/* 
	 * clicked(): será el método abstracto que definiremos en los listeners que hereden de la
	 * clase abstracta 
	 */
	@Override
	public abstract void clicked(InputEvent event, float x, float y);
}
