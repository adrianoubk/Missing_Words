package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

public class Slider extends Actor {
	private final int TILE_WIDTH = 50;
	private final int SLIDER_HEIGHT = 35;
	TextureRegion sliderTexture;
	
	public Slider (Texture texture) {
		sliderTexture = new TextureRegion(texture);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sliderTexture, 
				(MissingWords.VIEWPORT_WIDTH - sliderTexture.getRegionWidth()) / 2, 
				SLIDER_HEIGHT, sliderTexture.getRegionWidth(), sliderTexture.getRegionHeight());
	}
	
	public void update(int value) {
		sliderTexture.setRegionWidth(value * TILE_WIDTH);
	}
	
}
