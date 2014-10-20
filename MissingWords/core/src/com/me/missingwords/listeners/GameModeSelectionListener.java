package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener que permite seleccionar el modo de juego.
 *
 */

public class GameModeSelectionListener extends AbstractSelectionListener {

	public GameModeSelectionListener(String option, MissingWords missingWords) {
		super(option, missingWords);
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		switch (option) {
		case "singleplayer": missingWords.setSinglePlayer(true); break;
		case "playervscpu": missingWords.setSinglePlayer(false); break;
		}
		
		missingWords.setScreen(missingWords.CategorySelectionScreen);
	}

}
