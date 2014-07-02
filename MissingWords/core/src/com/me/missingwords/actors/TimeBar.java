package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

public class TimeBar extends Actor {
	
	private NinePatch backgroundBar;
	private NinePatch loadingBar;
	private TextureRegion textureBackground;
	private TextureRegion textureLoading;
	private float progress = 1;
	
	public TimeBar() {
		textureBackground = new TextureRegion(MissingWords.myManager.get("barBackground.png", Texture.class));
		backgroundBar = new NinePatch(textureBackground, 6, 6, 5, 5);
		
		textureLoading = new TextureRegion(MissingWords.myManager.get("barLoading.png", Texture.class));
		loadingBar = new NinePatch(textureLoading, 5, 5, 4, 4);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		backgroundBar.draw(batch, 255, 384, 290, 20);
		
		if (progress > 0.017)
			loadingBar.draw(batch, 257, 385.5f, 286 * progress, 17);
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}
	
	
	
	

}
