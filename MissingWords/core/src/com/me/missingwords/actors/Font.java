package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** 
 * 
 * Se usa para escribir texto en la pantalla.
 *
 */

public class Font extends Actor {
	private BitmapFont font;
	private String text;
	
	public Font(String text) {
		this.text = text;
		font = new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.draw(batch, text , 250, 400);
	}
}
