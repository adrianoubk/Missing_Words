package com.me.missingwords.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.me.missingwords.MissingWords;

public abstract class BaseScreen implements Screen {
	
	protected MissingWords missingwords;
	protected SpriteBatch myBatch;
	protected Stage stage;
	private ScalingViewport viewport;
	
	
	/* Conectamos el juego con la pantalla base */
	
	public BaseScreen(MissingWords missingwords) {
		this.missingwords = missingwords;
		this.myBatch = missingwords.getSB();
		viewport = new ScalingViewport(Scaling.stretch, 800, 480);
		stage = new Stage(viewport, myBatch);
	}
	

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
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
