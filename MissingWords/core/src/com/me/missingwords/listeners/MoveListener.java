package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.MissingWords;

/**
 * 
 * Listener que permite mover al jugador al pulsar el botón mover.
 *
 */

public class MoveListener extends ClickListener {
	private MissingWords missingWords;
	
	public MoveListener(MissingWords missingWords) {
		this.missingWords = missingWords;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Activamos que el jugador ha movido al menos una vez */
		missingWords.getMiniGameScreen().getMoveButton().setMoved(true);
		
		/* Evitamos que se pueda tocar el botón por segunda vez */
		missingWords.getMiniGameScreen().getMoveButton().setTouchable(Touchable.disabled);
		
		/* Si no quedan tiradas, establece la condición de parada */
		if (missingWords.getMiniGameScreen().getRollsLeft().getRolls() == 0)
			missingWords.getMiniGameScreen().getRollsLeft().setRolls(-1);
		
		/* Finalmente, movemos el jugador */
		missingWords.getMiniGameScreen().getWorld().movePlayer(
				missingWords.getMiniGameScreen().getDice().getResult(), true);
	}
}
