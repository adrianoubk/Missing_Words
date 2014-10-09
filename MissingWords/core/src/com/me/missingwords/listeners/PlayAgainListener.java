package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

public class PlayAgainListener extends ClickListener {
	
	private MissingWords missingWords;
	
	public PlayAgainListener(MissingWords misssingWords) {
		this.missingWords = misssingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		missingWords.VictoryScreen.dispose();
		missingWords.setScreen(missingWords.GameScreen);
	}
}
