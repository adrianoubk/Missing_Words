package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

public abstract class AbstractSelectionListener extends ClickListener {
	
	protected String option;
	protected MissingWords missingWords;
	
	public AbstractSelectionListener(String option, MissingWords missingWords) {
		this.option = option;
		this.missingWords = missingWords;
	}
	
	@Override
	public abstract void clicked(InputEvent event, float x, float y);
}
