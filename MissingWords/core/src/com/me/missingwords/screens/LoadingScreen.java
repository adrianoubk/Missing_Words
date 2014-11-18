package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.me.missingwords.MissingWords;

/**
 * 
 * Muestra la pantalla de carga, mientras se cargan los recursos.
 *
 */

public class LoadingScreen extends BaseScreen {

	public LoadingScreen(MissingWords missingWords) {
		super(missingWords);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		/* Si ha cargado todos los recursos del juego, pasamos a la siguiente pantalla */
		if (MissingWords.myManager.update()) {
			/* Creamos el gestor de sonido y la pantalla de selección de idioma */
			missingWords.createObjects();
			missingWords.LanguageSelectionScreen = new LanguageSelectionScreen(missingWords);
			missingWords.setScreen(missingWords.LanguageSelectionScreen);
		}
		
		/* Mientras los está cargando, mostramos el fondo de carga si se ha cargado su recurso */
		if (MissingWords.myManager.isLoaded("loadingBackground.png", Texture.class)) {
			int width = Gdx.graphics.getWidth();
			int height = Gdx.graphics.getHeight();
			missingWords.getSB().begin();
			missingWords.getSB().draw(MissingWords.myManager.get("loadingBackground.png", Texture.class), 0, 0, width, height);
			missingWords.getSB().end();
		}	
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
}
