package com.me.missingwords.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

public class WaitListener extends ClickListener {
	
	private MissingWords missingWords;
	
	public WaitListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (missingWords.getMiniGameScreen().getMoveButton().hasMoved()) {
			missingWords.getMiniGameScreen().getWaitButton().hide();
			missingWords.getMiniGameScreen().getMoveButton().hide();
			
			missingWords.getMiniGameScreen().getContinueButton().show();
		}
		else {
			Label warning = new Label("You must move at least once",  
					new LabelStyle(
							new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false),
					Color.BLACK));
			
			warning.setPosition(50, 50);
			warning.addAction(Actions.fadeOut(3));
			missingWords.getMiniGameScreen().getStage().addActor(warning);
		}
	}
}
