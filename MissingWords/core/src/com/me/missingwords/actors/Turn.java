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
 * Clase Turn
 * 
 * La clase Turn indica el turno que se está jugando en ese momento.
 *
 */

public class Turn extends Actor {
	
	private final int POSITION_Y = 425;
	private final int TEXTURE_PADDING = 8;
	private BitmapFont font; // Tipo de fuente para el turno
	private int numTurn; // Número del turno
	private TextureRegion turnTexture; // Textura del cuadro de turno
	
	public Turn(int numTurn) {
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		turnTexture = new TextureRegion(MissingWords.myManager.get("blue_button04.png", Texture.class));
		this.numTurn = numTurn;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(turnTexture, (800 - turnTexture.getRegionWidth()) / 2, 425);
		font.setColor(Color.MAROON);
		font.draw(batch, "Turn " + numTurn, calculatePosition().x , calculatePosition().y);
	}

	/* Método que calcula la posición de la fuente de turno */
	
	private Vector2 calculatePosition() {
		Vector2 pos = new Vector2();
		
		pos.x = ((MissingWords.VIEWPORT_WIDTH - turnTexture.getRegionWidth()) / 2) + 
				((turnTexture.getRegionWidth() - font.getBounds("Turn " + numTurn).width) / 2);
		pos.y = POSITION_Y + ((TEXTURE_PADDING + turnTexture.getRegionHeight() + 
				font.getBounds("Turn " + numTurn).height) / 2);
		
		return pos;
	}

}