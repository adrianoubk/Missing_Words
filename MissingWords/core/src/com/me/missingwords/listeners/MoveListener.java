package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

public class MoveListener extends ClickListener {
	private MissingWords missingWords;
	
	public MoveListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		
		missingWords.getMiniGameScreen().getMoveButton().setMoved(true);
		
		missingWords.getMiniGameScreen().getMoveButton().setTouchable(Touchable.disabled);
		
		if (missingWords.getMiniGameScreen().getRollsLeft().getRolls() == 0)
			missingWords.getMiniGameScreen().getRollsLeft().setRolls(-1);
		
		missingWords.getMiniGameScreen().getWorld().movePlayer(
				missingWords.getMiniGameScreen().getDice().getResult(), true);
	}
}
