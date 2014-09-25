package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.buttons.ClueButton;
import com.me.missingwords.screens.BaseScreen;
import com.me.missingwords.screens.GameScreen;

public class LengthClueListener extends ClickListener {

	private GameScreen game;
	private ClueButton button;
	
	public LengthClueListener(BaseScreen game, ClueButton button) {
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
		
		game.getLengthBox().createLength(game.getAdaptedWordNPC().size());
		button.disableStyle();
		button.setTouchable(Touchable.disabled);
		game.getStage().addActor(game.getLengthBox());
		game.getStage().addActor(game.getSubmitBox());
	}
}
