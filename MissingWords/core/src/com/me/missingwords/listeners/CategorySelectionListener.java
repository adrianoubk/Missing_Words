package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Category;

/**
 * 
 * Listener que permite seleccionar una categoría del juego.
 *
 */

public class CategorySelectionListener extends AbstractListener {
	private String option;

	public CategorySelectionListener(String option, MissingWords missingWords) {
		super(missingWords);
		this.option = option;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		switch (option) {
		case "days": missingWords.selectedCategory = Category.days; break;
		case "months": missingWords.selectedCategory = Category.months; break;
		case "wquestions": missingWords.selectedCategory = Category.wquestions; break;
		case "colours": missingWords.selectedCategory = Category.colours; break;
		case "size": missingWords.selectedCategory = Category.size; break;
		case "classroom": missingWords.selectedCategory = Category.classroom; break;
		case "bodyparts": missingWords.selectedCategory = Category.bodyparts; break;
		case "feelings": missingWords.selectedCategory = Category.feelings; break;
		case "university": missingWords.selectedCategory = Category.university; break;
		case "city": missingWords.selectedCategory = Category.city; break;
		case "freetime": missingWords.selectedCategory = Category.freetime; break;
		case "all": missingWords.selectedCategory = Category.ALL; break;
		}
		
		missingWords.createUtils();
		missingWords.createGameScreens();
		missingWords.setScreen(missingWords.GameScreen);
		/*missingWords.getCategoryData().read(missingWords.selectedCategory.toString(), 
											missingWords.selectedLanguage.toString());*/
	}
}
