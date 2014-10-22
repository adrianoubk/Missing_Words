package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener del botón back. Nos devuelve a la pantalla anterior.
 *
 */

public class BackButtonListener extends ClickListener {
	private MissingWords missingWords;
	
	public BackButtonListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (missingWords.getScreen().equals(missingWords.StatsScreen))
			missingWords.setScreen(missingWords.MenuScreen);
		
		if (missingWords.getScreen().equals(missingWords.CategorySelectionScreen)) {
			missingWords.setSinglePlayer(false);
			missingWords.setScreen(missingWords.MenuScreen);
		}
	}
}
