package com.mygdx.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.MissingWords.MissingWords;

public abstract class BaseScreen implements Screen {
	
	protected MissingWords missingwords;
	protected SpriteBatch myBatch;
	
	
	/* Conectamos el juego con la pantalla base */
	
	public BaseScreen(MissingWords missingwords) {
		this.missingwords = missingwords;
		this.myBatch = missingwords.getSB();
	}
	

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
