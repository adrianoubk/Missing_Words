package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Category;

public class CategoryStatsListener extends AbstractListener {
	private String option;
	
	public CategoryStatsListener(MissingWords missingWords, String option) {
		super(missingWords);
		this.option = option;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		switch(option) {
		case "days": missingWords.selectedCategory = Category.days; break;
		case "months": missingWords.selectedCategory = Category.months; break;
		case "colours": missingWords.selectedCategory = Category.colours; break;
		case "wquestions": missingWords.selectedCategory = Category.wquestions; break;
		case "size": missingWords.selectedCategory = Category.size; break;
		case "classroom": missingWords.selectedCategory = Category.classroom; break;
		case "bodyparts": missingWords.selectedCategory = Category.bodyparts; break;
		case "feelings": missingWords.selectedCategory = Category.feelings; break;
		case "university": missingWords.selectedCategory = Category.university; break;
		case "city": missingWords.selectedCategory = Category.city; break;
		case "freetime": missingWords.selectedCategory = Category.freetime; break;
		}
		
		missingWords.setScreen(missingWords.CategoryStatsScreen); 
	}
	
}
