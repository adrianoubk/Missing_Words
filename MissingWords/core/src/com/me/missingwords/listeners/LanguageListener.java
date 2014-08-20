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
	private MissingWords missingwords;
	
	public LanguageListener(String language, MissingWords missingwords) {
		this.language = language;
		this.missingwords = missingwords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		switch(language) {
		case "german": { missingwords.selectedLanguage = Language.german; break; }
		case "english": { missingwords.selectedLanguage = Language.english; break; }
		}
		
		missingwords.setScreen(missingwords.MenuScreen); // Siguiente pantalla -> Menú
	}
}
