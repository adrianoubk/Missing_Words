package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Category;

public class OptionListener extends ClickListener {
	
	private String option;
	private MissingWords missingwords;
	
	public OptionListener(String option, MissingWords missingwords) {
		this.option = option;
		this.missingwords = missingwords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		switch (option) {
		case "newgame": missingwords.setScreen(missingwords.CategorySelectionScreen); break;
		case "daysmonths": missingwords.selectedCategory = Category.days_months; 
						   missingwords.setScreen(missingwords.GameScreen); break;
		}
	}
}
