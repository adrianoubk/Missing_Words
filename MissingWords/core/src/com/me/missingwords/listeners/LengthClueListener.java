package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.buttons.ClueButton;

/**
 * 
 * Listener que procesa el evento al pulsar el botón "pista longitud".
 *
 */

public class LengthClueListener extends AbstractListener {
	private ClueButton button;
	
	public LengthClueListener(MissingWords missingWords, ClueButton button) {
		super(missingWords);
		this.button = button;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		missingWords.getGameScreen().getHuman().increaseCluesUsed();
		
		if (missingWords.getGameScreen().getHuman().getCluesUsed() <= 2) {
			/* Reproducimos el efecto de sonido si está activo */
			missingWords.getSoundFX().getClue().play(missingWords.getSoundFX().getVolume());
			
			/* Limpiamos el submitBox antes de generar las fichas de longitud */
			if (missingWords.getGameScreen().getSubmitBox().hasChildren()) {
				missingWords.getGameScreen().getSubmitBox().clearChildren();
				missingWords.getGameScreen().getSubmitBox().setNumActors(0);
				
				for (int i = 0; i < missingWords.getGameScreen().getOriginalTiles().size(); ++i) {
					missingWords.getGameScreen().getOriginalTiles().get(i).setVisible(true);
				}
			}
			
			/* Restablecemos el score */
			missingWords.getGameScreen().getWordScore().setScore(0);
			
			/* Creamos las fichas de longitud */
			missingWords.getGameScreen().getLengthBox().createLength(missingWords.getGameScreen().getAdaptedWordNPC().size());
			
			/* Desactivamos el botón de "pista longitud" */
			button.disableStyle();
			button.setTouchable(Touchable.disabled);
			
			/* Añadimos las fichas al stage */
			missingWords.getGameScreen().getStage().addActor(missingWords.getGameScreen().getLengthBox());
			missingWords.getGameScreen().getStage().addActor(missingWords.getGameScreen().getSubmitBox());
			
			/* Incrementamos el contador de pistas usadas */
			missingWords.getStatsData().increaseCluesUsed();
			
			/* Restamos las tiradas por usar pista (-2) */
			missingWords.getGameScreen().getHuman().decreaseRolls(1);
			
			/* Actualizamos las penalizaciones */
			missingWords.getGameScreen().getWordScore().increasePenalties(1);
			
			if (missingWords.getGameScreen().getHuman().getCluesUsed() == 2) {
				if (!button.isTouchable() && !missingWords.getGameScreen().getLetterClue().isTouchable()) {
					missingWords.getGameScreen().getTranslationClue().disableStyle();
					missingWords.getGameScreen().getTranslationClue().setTouchable(Touchable.disabled);
				}
				else if (!button.isTouchable() && !missingWords.getGameScreen().getTranslationClue().isTouchable()) {
					missingWords.getGameScreen().getLetterClue().disableStyle();
					missingWords.getGameScreen().getLetterClue().setTouchable(Touchable.disabled);
				}
			}
		}
	}
}
