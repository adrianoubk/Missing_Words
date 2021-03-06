package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener que permite mover al jugador al pulsar el bot�n mover.
 *
 */

public class MoveListener extends AbstractListener {
	
	public MoveListener(MissingWords missingWords) {
		super(missingWords);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si est� activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		/* Activamos que el jugador ha movido al menos una vez */
		missingWords.getMiniGameScreen().getMoveButton().setMoved(true);
		
		/* Evitamos que se pueda tocar el bot�n por segunda vez */
		missingWords.getMiniGameScreen().getMoveButton().setTouchable(Touchable.disabled);
		
		/* Si no quedan tiradas, establece la condici�n de parada */
		if (missingWords.getMiniGameScreen().getRollsLeft().getRolls() == 0)
			missingWords.getMiniGameScreen().getRollsLeft().setRolls(-1);
		
		/* Finalmente, movemos el jugador */
		missingWords.getMiniGameScreen().getWorld().movePlayer(
				missingWords.getMiniGameScreen().getDice().getResult(), true);
	}
}
