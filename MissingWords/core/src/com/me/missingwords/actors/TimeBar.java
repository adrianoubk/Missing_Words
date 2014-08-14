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
	private float seconds = 60;
	private float timeCounter = 1;
	private float counter = 1;
	
	public TimeBar() {
		textureBackground = new TextureRegion(MissingWords.myManager.get("barBackground.png", Texture.class));
		backgroundBar = new NinePatch(textureBackground, 6, 6, 5, 5);
		
		textureLoading = new TextureRegion(MissingWords.myManager.get("barLoading.png", Texture.class));
		loadingBar = new NinePatch(textureLoading, 5, 5, 4, 4);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		backgroundBar.draw(batch, 255, 402, 290, 20); // y = 383
		
		if (progress > 0.017)
			loadingBar.draw(batch, 257, 403.5f, 286 * progress, 17); // y = 384.5f
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		timeCounter += delta;
		if(timeCounter >= 1 && seconds >= 0){
			setProgress(counter);
			counter -= (float) 1 / 60;
			timeCounter = 0;
			--seconds;
			}	
	}
}
