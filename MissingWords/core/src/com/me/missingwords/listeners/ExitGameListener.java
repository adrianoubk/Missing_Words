package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener del bot�n salida al final de una partida. Nos lleva al men� principal del juego.
 *
 */

public class ExitGameListener extends AbstractListener {
	
	public ExitGameListener(MissingWords missingWords) {
		super(missingWords);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si est� activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		/* Calculamos el porcentaje de aciertos */
		missingWords.getStatsData().calculatePercentageHits();
		
		/* Guardamos los datos de las estad�sticas */
		missingWords.getStatsData().saveData();
		
		/* Actualizamos los valores de las estad�sticas */
		missingWords.getStatsScreen().updateLanguageStrings();
		
		/* Guardamos las palabras jugadas para esta categor�a */
		missingWords.getCategoryData().write(missingWords.getCategoryData().getCategoryWords(), 
											 missingWords.selectedCategory.toString(), 
											 missingWords.selectedLanguage.toString());
		
		/* Liberamos los recursos de las pantallas  y eliminamos las mismas */
		missingWords.GameScreen.dispose();
		missingWords.MiniGameScreen.dispose();
		missingWords.VictoryScreen.dispose();
		
		/* Desactivamos el SINGLEPLAYER, si es aplicable */
		if (missingWords.isSinglePlayer())
			missingWords.setSinglePlayer(false);
		
		missingWords.setScreen(missingWords.MenuScreen);
	}
}
