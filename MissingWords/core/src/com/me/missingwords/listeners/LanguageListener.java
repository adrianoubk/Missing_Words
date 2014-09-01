package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;

/** 
 * 
 * Clase LanguageListener
 * 
 * Este listener se encarga de guardar el idioma escogido por el jugador en la pantalla
 * de selección de idioma.
 *
 */

public class LanguageListener extends ClickListener {
	
	private String language;
	private MissingWords missingWords;
	
	public LanguageListener(String language, MissingWords missingWords) {
		this.language = language;
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		switch(language) {
		case "german": { missingWords.selectedLanguage = Language.german; break; }
		case "english": { missingWords.selectedLanguage = Language.english; break; }
		}
		
		missingWords.setScreen(missingWords.MenuScreen); // Siguiente pantalla -> Menú
	}
}
