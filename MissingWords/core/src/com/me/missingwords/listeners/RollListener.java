package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

public class RollListener extends ClickListener {
	
	private MissingWords missingWords;
	
	public RollListener(MissingWords missingWords)  {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		
		missingWords.getMiniGameScreen().getDice().roll();
		
		missingWords.getMiniGameScreen().getRollsLeft().decreaseRolls();
		
		if (missingWords.getMiniGameScreen().getRollsLeft().getRolls() == 0) { // Sin tiradas
			missingWords.getMiniGameScreen().getRollButton().hide();
			missingWords.getMiniGameScreen().getWaitButton().show();
		}
		
		missingWords.getMiniGameScreen().getMoveButton().setTouchable(Touchable.enabled);
	}
}
