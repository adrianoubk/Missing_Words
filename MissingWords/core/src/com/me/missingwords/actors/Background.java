package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.me.missingwords.MissingWords;

/** 
 * 
 * La clase Background representa el fondo de pantalla que estemos usando para cada
 * pantalla. 
 *
 */

public class Background extends Actor {
	
	private TextureRegion textureBackground; // Variable que guarda la textura del fondo 
	
	public Background(Texture texture) {
		textureBackground = new TextureRegion(texture);
	}

	/* El método draw() indica al fondo como dibujarse en la pantalla. Se dibujará en la
	 * posición x = 0 e y = 0 (esquina inferior izquierda) y se extenderá por toda la pantalla. 
	 */
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textureBackground, 0, 0, MissingWords.VIEWPORT_WIDTH, MissingWords.VIEWPORT_HEIGHT);
	}
}
