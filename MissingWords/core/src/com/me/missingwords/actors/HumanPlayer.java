package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.screens.GameScreen;

public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name);
	}
	
	public void playTurn() {
		
		setMyTurn(false);
		
		game.getSubmitBox().clean();
		game.getTileBox().clean();
		touchScreen(Touchable.disabled);
		game.getNpc().setMyTurn(true);
	}
	
	public void touchScreen(Touchable touchable) {
		game.getTileBox().setTouchable(touchable);
		game.getSubmitBox().setTouchable(touchable);
		game.getButton().setTouchable(touchable);
	}

	@Override
	public void getGameData(MissingWords missingWords) {
		game = (GameScreen) missingWords.getGameScreen();
	}
}
