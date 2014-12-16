package com.me.missingwords.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;
import com.me.missingwords.actors.Tile;

/**
 * 
 * Listener que se encarga de confirmar una palabra al pulsar el botón.
 *
 */

public class InputButtonListener extends ClickListener {
	private MissingWords missingWords;
	
	public InputButtonListener(MissingWords missingWords) {	
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		LabelStyle lStyle = new LabelStyle(font, Color.BLACK);
		
		/* Obtenemos todas las fichas que estén en el submitBox para su comprobación */
		SnapshotArray<Actor> array = missingWords.getGameScreen().getSubmitBox().getChildren();
		
		/* 
		 * Creamos un objeto de tipo StringBuilder que nos sirve para construir un String con
		 * la palabra que forman las fichas.
		 */
		StringBuilder word = new StringBuilder();
		
		int score = 0;
		
		for(int i = 0; i < array.size; ++i) {
			Tile t = (Tile) array.get(i);
			
			if (t.getLetter().equals("ae"))
				if (i == 0)
					word.append("ä".toUpperCase());
				else
					word.append("ä");
			else if (t.getLetter().equals("oe"))
				if (i == 0)
					word.append("ö".toUpperCase());
				else
					word.append("ö");
			else if (t.getLetter().equals("ue"))
				if (i == 0)
					word.append("ü".toUpperCase());
				else
					word.append("ü");
			else
				word.append(t.getLetter());
			score += t.getPoints();
		}
		
		/* Si la palabra está en el vocbulario */
		if (missingWords.getVocabulary().getVocabulary().containsKey(word.toString())) {
			/* Reproducimos el efecto de sonido si está activo */
			missingWords.getSoundFX().getPositiveSound().play(missingWords.getSoundFX().getVolume());
			
			/* Calculamos el número de tiradas en base a los puntos del jugador */
			missingWords.getGameScreen().getHuman().calculateRolls(score);
			
			/* Añadimos la palabra a la lista de palabras jugadas */
			missingWords.getGameScreen().getHuman().addPlayedWord(word.toString());
			
			/* Creamos una etiqueta y mostramos el mensaje */
			Label nice = new Label("Nice!", lStyle);
			if (missingWords.selectedLanguage == Language.german)
				nice.setText("Gut!");
			nice.setPosition(0, 0);
			nice.addAction(Actions.fadeOut(1));
			missingWords.getGameScreen().getStage().addActor(nice);
			
			missingWords.getGameScreen().increaseTotalWords(); // TotalWords + 1
			
			/* Añadimos los datos a las estadísticas */
			missingWords.getStatsData().increaseMaxWords();
			missingWords.getStatsData().increaseCorrectWords();
			
			/* Añadimos la palabra a las estadísticas de su categoría */
			missingWords.getCategoryData().addWord(word.toString());
			
			/* Comprobamos si hemos formado una palabra más larga y la añadimos si es así */
			if (missingWords.getStatsData().getLargestWord().length() < word.length())
				missingWords.getStatsData().setLargestWord(word.toString());
			
			/* Comprobamos si hemos formado una palabra con más puntos y la añadimos si es así */
				missingWords.getStatsData().setBestWord(word.toString(), score);
			
			/* El jugador confirma la palabra y juega al minijuego */
			missingWords.getGameScreen().getHuman().playTurn();
			
		}
		else { // Si no está, se muestra con una etiqueta
			/* Reproducimos el efecto de sonido si está activo */
			missingWords.getSoundFX().getNegativeSound().play(missingWords.getSoundFX().getVolume());
			
			Label notfound = new Label("Not found!", lStyle);
			notfound.setPosition(630, 0);
			if (missingWords.selectedLanguage == Language.german) {
				notfound.setText("Nicht gefunden");
				notfound.setPosition(550, 0);
			}
			notfound.addAction(Actions.fadeOut(1));
			missingWords.getGameScreen().getStage().addActor(notfound);
		}
	}
}
