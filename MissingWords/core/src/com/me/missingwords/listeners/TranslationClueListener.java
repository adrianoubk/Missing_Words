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

public class TranslationClueListener extends ClickListener {
	
	private MissingWords missingWords;
	private ClueButton button;
	
	public TranslationClueListener(MissingWords missingWords, ClueButton button) {
		this.missingWords = missingWords;
		this.button = button;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		
		if (missingWords.getGameScreen().getSubmitBox().hasChildren()) {
			missingWords.getGameScreen().getSubmitBox().clearChildren();
			missingWords.getGameScreen().getSubmitBox().setNumActors(0);
			
			for (int i = 0; i < missingWords.getGameScreen().getOriginalTiles().size(); ++i) {
				missingWords.getGameScreen().getOriginalTiles().get(i).setVisible(true);
			}
		}
		
		StringBuilder word = new StringBuilder();
		
		for(int i = 0; i < missingWords.getGameScreen().getAdaptedWordNPC().size(); ++i) {
			Tile t = (Tile) missingWords.getGameScreen().getAdaptedWordNPC().get(i);
			word.append(t.getLetter());
		}
		
		BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		LabelStyle lStyle = new LabelStyle(font, Color.BLACK);
		
		Label l = new Label(missingWords.getDictionary().getDictionary().get(word.toString()), lStyle);
		l.setTouchable(Touchable.disabled);
		l.setPosition((MissingWords.VIEWPORT_WIDTH - l.getMinWidth()) / 2, 60);
		l.addAction(Actions.fadeOut(5));
		missingWords.getGameScreen().getStage().addActor(l);
		
		button.disableStyle();
		button.setTouchable(Touchable.disabled);
	}
}
