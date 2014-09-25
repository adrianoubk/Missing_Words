package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.buttons.ClueButton;
import com.me.missingwords.screens.BaseScreen;
import com.me.missingwords.screens.GameScreen;

public class LetterClueListener extends ClickListener {
	
	private GameScreen game;
	private ClueButton button;
	
	public LetterClueListener(BaseScreen game, ClueButton button) {
		this.game = (GameScreen) game;
		this.button = button;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		
		if (game.getSubmitBox().hasChildren()) {
			game.getSubmitBox().clearChildren();
			game.getSubmitBox().setNumActors(0);
			
			for (int i = 0; i < game.getOriginalTiles().size(); ++i) {
				game.getOriginalTiles().get(i).setVisible(true);
			}
		}
		
		int index = game.getOriginalTiles().indexOf(game.getAdaptedWordNPC().get(0));
		game.getOriginalTiles().get(index).setVisible(false);
		game.getCopyTiles().get(index).setSmallSize(); 
		game.getSubmitBox().addActor(game.getCopyTiles().get(index)); 
		game.getSubmitBox().increaseNumActors();
		
		button.disableStyle();
		button.setTouchable(Touchable.disabled);
	}
}
