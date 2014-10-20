package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

/**
 *
 * Indica el turno que se est� jugando en ese momento.
 *
 */

public class Turn extends Actor {
	private final int POSITION_Y = 425;
	private final int TEXTURE_PADDING = 8;
	
	private BitmapFont font; // Fuente para el turno
	private int numTurn; // N�mero del turno
	private TextureRegion turnTexture;// Textura del cuadro de turno
	
	public Turn(int numTurn) {
		this.numTurn = numTurn;
		
		font = new BitmapFont(
			Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		turnTexture = new TextureRegion(
				MissingWords.myManager.get("upButtonLarge.png", Texture.class));
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(turnTexture, (MissingWords.VIEWPORT_WIDTH - turnTexture.getRegionWidth()) / 2, POSITION_Y);
		font.setColor(Color.MAROON);
		font.draw(batch, "Turn " + numTurn, calculatePosition().x , calculatePosition().y);
	}

	/* calculatePosition(): calcula la posici�n de la fuente del turno */
	private Vector2 calculatePosition() {
		Vector2 pos = new Vector2();
		
		pos.x = ((MissingWords.VIEWPORT_WIDTH - turnTexture.getRegionWidth()) / 2) + 
				((turnTexture.getRegionWidth() - font.getBounds("Turn " + numTurn).width) / 2);
		pos.y = POSITION_Y + ((TEXTURE_PADDING + turnTexture.getRegionHeight() + 
				font.getBounds("Turn " + numTurn).height) / 2);
		
		return pos;
	}
	
	public void nextTurn() {
		numTurn += 1;
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public int getNumTurn() {
		return numTurn;
	}
	public void setNumTurn(int numTurn) {
		this.numTurn = numTurn;
	}
}