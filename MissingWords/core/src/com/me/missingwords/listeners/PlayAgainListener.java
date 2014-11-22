package com.me.missingwords.listeners;

/**
 * 
 * Listener que permite jugar otra vez al juego al pulsar el bot�n play again.
 * 
 */

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.MissingWords;

public class PlayAgainListener extends AbstractListener {
	
	public PlayAgainListener(MissingWords misssingWords) {
		super(misssingWords);
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
		
		/* Liberamos recursos y eliminamos las pantallas de juego */
		missingWords.GameScreen.dispose();
		missingWords.MiniGameScreen.dispose();
		missingWords.VictoryScreen.dispose();
		
		/* Creamos las pantallas para un juego nuevo */
		missingWords.createGameScreens();
		
		missingWords.setScreen(missingWords.GameScreen); // Cambiamos a la pantalla de juego
	}
}
