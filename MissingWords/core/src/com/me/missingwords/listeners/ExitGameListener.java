package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener del botón salida al final de una partida. Nos lleva al menú principal del juego.
 *
 */

public class ExitGameListener extends ClickListener {
	private MissingWords missingWords;
	
	public ExitGameListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Calculamos el porcentaje de aciertos */
		missingWords.getStatsData().calculatePercentageHits();
		
		/* Guardamos los datos de las estadísticas */
		missingWords.getStatsData().saveData();
		
		/* Actualizamos los valores de las estadísticas */
		missingWords.getStatsScreen().updateLabels();
		
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
