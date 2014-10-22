package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener del botón stats. Nos abre la pantalla de estadísticas.
 *
 */

public class StatsListener extends ClickListener {
	private MissingWords missingWords;
	
	public StatsListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		missingWords.setScreen(missingWords.StatsScreen);
	}
}
