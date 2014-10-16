package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

public class ExitGameListener extends ClickListener {
	
	private MissingWords missingWords;
	
	public ExitGameListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		missingWords.GameScreen.dispose();
		missingWords.MiniGameScreen.dispose();
		missingWords.VictoryScreen.dispose();
		
		/* Desactivamos el SINGLEPLAYER, si es aplicable */
		if (missingWords.isSinglePlayer())
			missingWords.setSinglePlayer(false);
		
		missingWords.setScreen(missingWords.MenuScreen);
	}
}
