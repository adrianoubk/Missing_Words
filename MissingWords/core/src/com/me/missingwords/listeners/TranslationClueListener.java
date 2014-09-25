package com.me.missingwords.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Tile;
import com.me.missingwords.buttons.ClueButton;
import com.me.missingwords.screens.BaseScreen;
import com.me.missingwords.screens.GameScreen;

public class TranslationClueListener extends ClickListener {
	
	private GameScreen game;
	private ClueButton button;
	
	public TranslationClueListener(BaseScreen game, ClueButton button) {
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
		
		StringBuilder word = new StringBuilder();
		
		for(int i = 0; i < game.getAdaptedWordNPC().size(); ++i) {
			Tile t = (Tile) game.getAdaptedWordNPC().get(i);
			word.append(t.getLetter());
		}
		
		BitmapFont font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		LabelStyle lStyle = new LabelStyle(font, Color.BLACK);
		
		Label l = new Label(game.getDic().getDictionary().get(word.toString()), lStyle);
		l.setTouchable(Touchable.disabled);
		l.setPosition((MissingWords.VIEWPORT_WIDTH - l.getMinWidth()) / 2, 60);
		l.addAction(Actions.fadeOut(5));
		game.getStage().addActor(l);
		
		button.disableStyle();
		button.setTouchable(Touchable.disabled);
	}
}
