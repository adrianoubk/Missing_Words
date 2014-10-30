package com.me.missingwords.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;

/** 
 * 
 * Listener que nos permite acceder a la selección de idioma y a las opciones del menú
 * principal.
 *
 */

public class MenuListener extends AbstractListener {
	private String option;

	public MenuListener(MissingWords missingWords, String option) {
		super(missingWords);
		this.option = option;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		switch (option) {
		/* SELECCIÓN DE IDIOMA */
		case "english":
			missingWords.selectedLanguage = Language.english;
			missingWords.createMenuScreens(); // Creamos las pantallas de menús
			missingWords.setScreen(missingWords.MenuScreen); // Siguiente pantalla -> Menú
			break;
		case "german":
			missingWords.selectedLanguage = Language.german;
			missingWords.createMenuScreens(); 
			missingWords.setScreen(missingWords.MenuScreen); 
			break;
		/* MENÚ PRINCIPAL */
		case "playervscpu": 
			missingWords.setSinglePlayer(false);
			missingWords.setScreen(missingWords.CategorySelectionScreen);
			break;
		case "singleplayer": 
			missingWords.setSinglePlayer(true); 
			missingWords.setScreen(missingWords.CategorySelectionScreen);
			break;
		case "stats":
			missingWords.setScreen(missingWords.StatsScreen); 
			break;
		case "settings":
			missingWords.setScreen(missingWords.SettingsScreen); 
			break;
		case "instructions":
			missingWords.setScreen(missingWords.InstructionsScreen); 
			break;
		case "exit":
			missingWords.dispose();
			Gdx.app.exit();
			break;
		}
	}

}
