package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Category;

public class OptionListener extends ClickListener {
	
	private String option;
	private MissingWords missingWords;
	
	public OptionListener(String option, MissingWords missingWords) {
		this.option = option;
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		switch (option) {
		case "newgame": missingWords.setScreen(missingWords.CategorySelectionScreen); break;
		case "days": missingWords.selectedCategory = Category.days; 
						   missingWords.setScreen(missingWords.GameScreen); break;
		}
	}
}
