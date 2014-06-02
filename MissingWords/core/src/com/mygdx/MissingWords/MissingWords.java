package com.mygdx.MissingWords;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.Screens.*;

public class MissingWords extends Game {
	
	public BaseScreen MenuScreen;
	private SpriteBatch myBatch;
	private AssetManager myManager;
	
	@Override
	public void create() {
		myBatch = new SpriteBatch();
		myManager = new AssetManager();
		MenuScreen = new MenuScreen(this);
		
		myManager.load("fondo.png", Texture.class);
		myManager.finishLoading();
		
		setScreen(MenuScreen);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		myBatch.dispose();
		myManager.dispose();
	}
	
	public SpriteBatch getSB() {
		return myBatch;
	}
	
	public AssetManager getAM() {
		return myManager;
	}

}

