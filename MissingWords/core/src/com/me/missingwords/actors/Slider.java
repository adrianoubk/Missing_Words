package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.me.missingwords.MissingWords;

/** 
 * 
 * Clase Slider
 * 
 * La clase Slider representa la barra donde descansan las letras al ser seleccionadas para
 * formar una nueva palabra.
 *
 */

public class Slider extends Actor {
	
	private final int TILE_WIDTH = 50; // Ancho de la ficha
	private final int SLIDER_HEIGHT = 35; // Altura a la que se dibuja el slider
	private TextureRegion sliderTexture; // Textura del slider
	
	public Slider (Texture texture) {
		sliderTexture = new TextureRegion(texture);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(sliderTexture, 
				(MissingWords.VIEWPORT_WIDTH - sliderTexture.getRegionWidth()) / 2, 
				SLIDER_HEIGHT, sliderTexture.getRegionWidth(), sliderTexture.getRegionHeight());
	}
	
	/* El método update() alarga el slider para adaptarse al ancho de las fichas 
	 * a medida que se van añadiendo o borrando letras. */
	
	public void update(int value) {
		sliderTexture.setRegionWidth(value * TILE_WIDTH);
	}
	
}
