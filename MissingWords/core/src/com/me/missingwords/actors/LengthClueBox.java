package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * 
 * Representa la longitud de la palabra con las Tiles(?) al usar la pista de longitud.
 *
 */

public class LengthClueBox extends SubmitBox {
	
	public LengthClueBox() {
		super();
		
		setTouchable(Touchable.disabled);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		update(); // con el método update() centramos las Tiles.
	}
	
	/* createLength(): crea las fichas que representan la longitud de la palabra y la muestra */
	public void createLength(int wordLength) {
		
		Tile fakeTile = new Tile("none", 0);
		
		for (int i = 0; i < wordLength; ++i) {
			Tile t = new Tile(fakeTile);
			t.setSmallSize(); // Usamos el tamaño pequeño
			addActor(t); // Las añadimos al grupo
		}
	}
}
