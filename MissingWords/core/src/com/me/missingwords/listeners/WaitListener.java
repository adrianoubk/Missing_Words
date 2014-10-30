package com.me.missingwords.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener que permite al jugador esperar al siguiente turno al pulsar el botón wait.
 *
 */

public class WaitListener extends AbstractListener {
	
	public WaitListener(MissingWords missingWords) {
		super(missingWords);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		/* Si ha movido al menos una vez */
		if (missingWords.getMiniGameScreen().getMoveButton().hasMoved()) {
			missingWords.getMiniGameScreen().getWaitButton().hide();
			missingWords.getMiniGameScreen().getMoveButton().hide();
			
			missingWords.getMiniGameScreen().getContinueButton().show(); // Muestra el botón
		}
		else { // Si no, se le indica con una etiqueta
			Label warning = new Label("You must move at least once",  
					new LabelStyle(
							new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false),
					Color.BLACK));
			
			warning.setPosition((MissingWords.VIEWPORT_WIDTH - warning.getMinWidth()) / 2, 200);
			warning.addAction(Actions.fadeOut(3));
			missingWords.getMiniGameScreen().getStage().addActor(warning);
		}
	}
}
