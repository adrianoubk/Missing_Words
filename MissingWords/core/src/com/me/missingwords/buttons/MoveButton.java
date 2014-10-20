package com.me.missingwords.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;

/**
 * 
 * Botón de movimiento del minijuego. Permite mover una ficha de jugador.
 *
 */

public class MoveButton extends MiniGameButton {
	private final int POSITION_X = 400;
	private final int POSITION_Y = 2;
	
	/* 
	 * Booleano que comprueba si el jugador se ha movido. Ya que tiene que mover al menos una
	 * vez de sus tiradas
	 */
	private boolean hasMoved;

	public MoveButton() {
		super("Move", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("rollButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("rollButtonDown.png", Texture.class))), 
				null, 
				new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false)));
		
		setPosition(POSITION_X, POSITION_Y);
		
		hasMoved = false;
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public void setMoved(boolean moved) {
		hasMoved = moved;
	}
}
