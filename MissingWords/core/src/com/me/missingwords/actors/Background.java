package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

public class Background extends Actor {
	private TextureRegion textureBackground;
	
	public Background(Texture texture) {
		textureBackground = new TextureRegion(texture);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textureBackground, 0, 0, MissingWords.VIEWPORT_WIDTH, MissingWords.VIEWPORT_HEIGHT);
	}
}
