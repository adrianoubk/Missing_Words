package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.me.missingwords.MissingWords;

/**
 * 
 * Muestra la pantalla de carga, mientras se cargan los recursos.
 *
 */

public class LoadingScreen extends BaseScreen {
	private BitmapFont font;

	public LoadingScreen(MissingWords missingWords) {
		super(missingWords);
		
		font = new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		if (MissingWords.myManager.update()) {
			missingWords.LanguageSelectionScreen = new LanguageSelectionScreen(missingWords);
			missingWords.setScreen(missingWords.LanguageSelectionScreen);
		}
		
		if (MissingWords.myManager.isLoaded("background.png", Texture.class)) {
			int width = Gdx.graphics.getWidth();
			int height = Gdx.graphics.getHeight();
			missingWords.getSB().begin();
			missingWords.getSB().draw(MissingWords.myManager.get("background.png", Texture.class), 0, 0, width, height);
			font.draw(missingWords.getSB(), "Loading...", 200, 100);
			missingWords.getSB().end();
		}	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}
