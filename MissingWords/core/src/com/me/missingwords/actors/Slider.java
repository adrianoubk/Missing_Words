package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

/** 
 * 
 * Representa la barra donde descansan las letras al ser seleccionadas para formar una nueva 
 * palabra.
 *
 */

public class Slider extends Actor {
	private final int TILE_WIDTH = 50; // Ancho de la ficha
	private final int SLIDER_HEIGHT = 25; // Altura a la que se dibuja el slider
	private TextureRegion sliderTexture; // Textura del slider
	
	private MissingWords missingWords;
	
	public Slider (Texture texture, MissingWords missingWords) {
		sliderTexture = new TextureRegion(texture);
		
		this.missingWords = missingWords;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sliderTexture, 
				(MissingWords.VIEWPORT_WIDTH - sliderTexture.getRegionWidth()) / 2, 
				SLIDER_HEIGHT, sliderTexture.getRegionWidth(), sliderTexture.getRegionHeight());
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if (missingWords.getGameScreen() == null)
			System.out.println("game es null");
		
		sliderTexture.setRegionWidth(
				missingWords.getGameScreen().getSubmitBox().getNumActors() * TILE_WIDTH);
	}
}
