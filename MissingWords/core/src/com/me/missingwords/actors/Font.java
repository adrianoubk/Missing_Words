package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** 
 * 
 * La clase Font se usa para escribir texto en la pantalla.
 *
 */

public class Font extends Actor {
	
	private BitmapFont font;
	private String text;
	
	public Font(String text) {
		font = new BitmapFont(Gdx.files.internal("title.fnt"), Gdx.files.internal("title.png"), false);
		this.text = text;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.draw(batch, text , 250, 400);
	}
}
