package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.buttons.ClueButton;

/**
 * 
 * Listener que procesa el evento al pulsar el botón "pista letra adicional".
 *
 */

public class LetterClueListener extends AbstractListener {
	private ClueButton button;
	
	public LetterClueListener(MissingWords missingWords, ClueButton button) {
		super(missingWords);
		this.button = button;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		missingWords.getGameScreen().getHuman().increaseCluesUsed();
		
		if (missingWords.getGameScreen().getHuman().getCluesUsed() <= 2) {
		
			/* Reproducimos el efecto de sonido si está activo */
			missingWords.getSoundFX().getClue().play(missingWords.getSoundFX().getVolume());
			
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
			
			/* Añadimos el score */
			missingWords.getGameScreen().getWordScore().setScore(missingWords.getGameScreen().getOriginalTiles().get(index).getPoints());
			
			/* Desactivamos el botón de "pista letra adicional" */
			button.disableStyle();
			button.setTouchable(Touchable.disabled);
			
			/* Incrementamos el contador de pistas usadas */
			missingWords.getStatsData().increaseCluesUsed();
			
			/* Restamos las tiradas por usar pista (-1) */
			missingWords.getGameScreen().getHuman().decreaseRolls(1);
			
			/* Actualizamos las penalizaciones */
			missingWords.getGameScreen().getWordScore().increasePenalties(1);
			
			if (missingWords.getGameScreen().getHuman().getCluesUsed() == 2) {
				if (!button.isTouchable() && !missingWords.getGameScreen().getLengthClue().isTouchable()) {
					missingWords.getGameScreen().getTranslationClue().disableStyle();
					missingWords.getGameScreen().getTranslationClue().setTouchable(Touchable.disabled);
				}
				else if (!button.isTouchable() && !missingWords.getGameScreen().getTranslationClue().isTouchable()) {
					missingWords.getGameScreen().getLengthClue().disableStyle();
					missingWords.getGameScreen().getLengthClue().setTouchable(Touchable.disabled);
				}
			}
		}
	}
}
