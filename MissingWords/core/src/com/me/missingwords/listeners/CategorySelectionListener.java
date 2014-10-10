package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Category;

public class CategorySelectionListener extends AbstractSelectionListener {

	public CategorySelectionListener(String option, MissingWords missingWords) {
		super(option, missingWords);
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		switch (option) {
		case "days": missingWords.selectedCategory = Category.days;
					 missingWords.createUtils();
					 missingWords.createScreens2();
					 missingWords.setScreen(missingWords.GameScreen);
					 break;
		}
	}

}
