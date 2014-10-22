package com.me.missingwords.listeners;

/**
 * 
 * Listener que permite jugar otra vez al juego al pulsar el botón play again.
 * 
 */

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

public class PlayAgainListener extends ClickListener {
	private MissingWords missingWords;
	
	public PlayAgainListener(MissingWords misssingWords) {
		this.missingWords = misssingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Calculamos el porcentaje de aciertos */
		missingWords.getStatsData().calculatePercentageHits();
		
		/* Guardamos los datos de las estadísticas */
		missingWords.getStatsData().saveData();
		
		/* Actualizamos los valores de las estadísticas */
		missingWords.getStatsScreen().updateLabels();
		
		/* Liberamos recursos y eliminamos las pantallas de juego */
		missingWords.GameScreen.dispose();
		missingWords.MiniGameScreen.dispose();
		missingWords.VictoryScreen.dispose();
		
		/* Creamos las pantallas para un juego nuevo */
		missingWords.createGameScreens();
		
		missingWords.setScreen(missingWords.GameScreen); // Cambiamos a la pantalla de juego
	}
}
