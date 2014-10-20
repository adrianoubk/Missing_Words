package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;
import com.me.missingwords.buttons.ClueButton;

/**
 * 
 * Listener que procesa el evento al pulsar el botón "pista letra adicional".
 *
 */

public class LetterClueListener extends ClickListener {
	private MissingWords missingWords;
	private ClueButton button;
	
	public LetterClueListener(MissingWords missingWords, ClueButton button) {
		this.missingWords = missingWords;
		this.button = button;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Limpiamos el submitBox antes de dar la ficha adicional */
		if (missingWords.getGameScreen().getSubmitBox().hasChildren()) {
			missingWords.getGameScreen().getSubmitBox().clearChildren();
			missingWords.getGameScreen().getSubmitBox().setNumActors(0);
			
			for (int i = 0; i < missingWords.getGameScreen().getOriginalTiles().size(); ++i) {
				missingWords.getGameScreen().getOriginalTiles().get(i).setVisible(true);
			}
		}
		
		/* Añadimos la letra adicional */
		int index = missingWords.getGameScreen().getOriginalTiles().indexOf(missingWords.getGameScreen().getAdaptedWordNPC().get(0));
		missingWords.getGameScreen().getOriginalTiles().get(index).setVisible(false);
		missingWords.getGameScreen().getCopyTiles().get(index).setSmallSize(); 
		missingWords.getGameScreen().getSubmitBox().addActor(missingWords.getGameScreen().getCopyTiles().get(index)); 
		missingWords.getGameScreen().getSubmitBox().increaseNumActors();
		
		/* Desactivamos el botón de "pista letra adicional" */
		button.disableStyle();
		button.setTouchable(Touchable.disabled);
	}
}
