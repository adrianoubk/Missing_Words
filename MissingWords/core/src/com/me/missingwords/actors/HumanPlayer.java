package com.me.missingwords.actors;

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
		game.getNpc().setMyTurn(true);
	}

	@Override
	public void getGameData(MissingWords missingWords) {
		game = (GameScreen) missingWords.getGameScreen();
	}
}
