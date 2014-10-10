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
 * Clase InputButtonListener
 * 
 * Este listener se encarga de controlar el evento cuando se hace click en el botón de enviar
 * palabra para comprobación.
 *
 */

public class InputButtonListener extends ClickListener {
	
	private MissingWords missingWords;
	
	public InputButtonListener(MissingWords missingWords) {
		
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Obtenemos todas las fichas que estén en el submitBox para su comprobación */
		SnapshotArray<Actor> array = missingWords.getGameScreen().getSubmitBox().getChildren();
		
		/* Creamos un objeto de tipo StringBuilder que nos sirve para construir un String con
		 * la palabra que forman las fichas.
		 */
		StringBuilder word = new StringBuilder();
		
		for(int i = 0; i < array.size; ++i) {
			Tile t = (Tile) array.get(i);
			word.append(t.getLetter());
		}
		
		BitmapFont font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		LabelStyle lStyle = new LabelStyle(font, Color.BLACK);
		
		if (missingWords.getVocabulary().getVocabulary().containsKey(word.toString())) {
			
			missingWords.getGameScreen().addPlayedWord(word.toString());
			
			Label l = new Label("Nice!", lStyle);
			l.setPosition(0, 0);
			l.addAction(Actions.fadeOut(1.5f));
			missingWords.getGameScreen().getStage().addActor(l);
			
			missingWords.getGameScreen().increaseTotalWords();
			
			missingWords.getGameScreen().getHuman().playTurn(); // El jugador forma una palabra y termina su turno
			
		}
		else {
			Label l2 = new Label("Not found!", lStyle);
			l2.setPosition(550, 0);
			l2.addAction(Actions.fadeOut(1.5f));
			missingWords.getGameScreen().getStage().addActor(l2);
		}
	}
}
