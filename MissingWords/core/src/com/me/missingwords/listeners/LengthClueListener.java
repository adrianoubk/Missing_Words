package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;
import com.me.missingwords.buttons.ClueButton;

/**
 * 
 * Listener que procesa el evento al pulsar el bot�n "pista longitud".
 *
 */

public class LengthClueListener extends ClickListener {
	private MissingWords missingWords;
	private ClueButton button;
	
	public LengthClueListener(MissingWords missingWords, ClueButton button) {
		this.missingWords = missingWords;
		this.button = button;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Limpiamos el submitBox antes de generar las fichas de longitud */
		if (missingWords.getGameScreen().getSubmitBox().hasChildren()) {
			missingWords.getGameScreen().getSubmitBox().clearChildren();
			missingWords.getGameScreen().getSubmitBox().setNumActors(0);
			
			for (int i = 0; i < missingWords.getGameScreen().getOriginalTiles().size(); ++i) {
				missingWords.getGameScreen().getOriginalTiles().get(i).setVisible(true);
			}
		}
		
		/* Creamos las fichas de longitud */
		missingWords.getGameScreen().getLengthBox().createLength(missingWords.getGameScreen().getAdaptedWordNPC().size());
		
		/* Desactivamos el bot�n de "pista longitud" */
		button.disableStyle();
		button.setTouchable(Touchable.disabled);
		
		/* A�adimos las fichas al stage */
		missingWords.getGameScreen().getStage().addActor(missingWords.getGameScreen().getLengthBox());
		missingWords.getGameScreen().getStage().addActor(missingWords.getGameScreen().getSubmitBox());
	}
}
