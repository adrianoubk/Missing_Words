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
			word.append(t.getLetter());
			score += t.getPoints();
		}
		
		/* Calculamos el número de tiradas en base a los puntos del jugador */
		missingWords.getGameScreen().getHuman().calculateRolls(score);
		
		/* Si la palabra está en el vocbulario */
		if (missingWords.getVocabulary().getVocabulary().containsKey(word.toString())) {
			/* Añadimos la palabra a la lista de palabras jugadas */
			missingWords.getGameScreen().getHuman().addPlayedWord(word.toString());
			
			/* Creamos una etiqueta y mostramos el mensaje */
			Label yes = new Label("Nice!", lStyle);
			yes.setPosition(0, 0);
			yes.addAction(Actions.fadeOut(1.5f));
			missingWords.getGameScreen().getStage().addActor(yes);
			
			missingWords.getGameScreen().increaseTotalWords(); // TotalWords + 1
			
			/* El jugador confirma la palabra y juega al minijuego */
			missingWords.getGameScreen().getHuman().playTurn();
			
		}
		else { // Si no está, se muestra con una etiqueta
			Label no = new Label("Not found!", lStyle);
			no.setPosition(550, 0);
			no.addAction(Actions.fadeOut(1.5f));
			missingWords.getGameScreen().getStage().addActor(no);
		}
	}
}
