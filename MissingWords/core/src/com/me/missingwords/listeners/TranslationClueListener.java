package com.me.missingwords.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.me.missingwords.MissingWords;
import com.me.missingwords.buttons.ClueButton;

/**
 * 
 * Listener que procesa el evento al pulsar el botón "pista traducción".
 *
 */

public class TranslationClueListener extends AbstractListener {
	private ClueButton button;
	
	public TranslationClueListener(MissingWords missingWords, ClueButton button) {
		super(missingWords);
		this.button = button;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		missingWords.getGameScreen().getHuman().increaseCluesUsed();
		
		if (missingWords.getGameScreen().getHuman().getCluesUsed() <= 2) {	
			/* Reproducimos el efecto de sonido si está activo */
			missingWords.getSoundFX().getClue().play(missingWords.getSoundFX().getVolume());
			
			/* Limpiamos el submitBox antes de dar la traducción */
			if (missingWords.getGameScreen().getSubmitBox().hasChildren()) {
				missingWords.getGameScreen().getSubmitBox().clearChildren();
				missingWords.getGameScreen().getSubmitBox().setNumActors(0);
				
				for (int i = 0; i < missingWords.getGameScreen().getOriginalTiles().size(); ++i) {
					missingWords.getGameScreen().getOriginalTiles().get(i).setVisible(true);
				}
			}
			
			/* Restablecemos el score */
			missingWords.getGameScreen().getWordScore().setScore(0);
			
			/* Creamos una etiqueta que indica la traducción */
			BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
			LabelStyle lStyle = new LabelStyle(font, Color.BLACK);
			
			Label l = new Label(missingWords.getDictionary().getDictionary().get(missingWords.getGameScreen().getRandomWord()), lStyle);
			l.setTouchable(Touchable.disabled);
			l.setPosition((MissingWords.VIEWPORT_WIDTH - l.getMinWidth()) / 2, 40);
			l.addAction(Actions.fadeOut(3));
			missingWords.getGameScreen().getStage().addActor(l);
			
			/* Desactivamos el botón de "pista traducción" */
			button.disableStyle();
			button.setTouchable(Touchable.disabled);
			
			/* Incrementamos el contador de pistas usadas */
			missingWords.getStatsData().increaseCluesUsed();
			
			/* Restamos las tiradas por usar pista (-2) */
			missingWords.getGameScreen().getHuman().decreaseRolls(2);
			
			/* Actualizamos las penalizaciones */
			missingWords.getGameScreen().getWordScore().increasePenalties(2);
			
			if (missingWords.getGameScreen().getHuman().getCluesUsed() == 2) {
				if (!button.isTouchable() && !missingWords.getGameScreen().getLetterClue().isTouchable()) {
					missingWords.getGameScreen().getLengthClue().disableStyle();
					missingWords.getGameScreen().getLengthClue().setTouchable(Touchable.disabled);
				}
				else if (!button.isTouchable() && !missingWords.getGameScreen().getLengthClue().isTouchable()) {
					missingWords.getGameScreen().getLetterClue().disableStyle();
					missingWords.getGameScreen().getLetterClue().setTouchable(Touchable.disabled);
				}
			}
		}
	}
}
