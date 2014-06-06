package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.MissingWords.MissingWords;

public class MenuScreen extends BaseScreen {
	
	private Stage stage;
	private ScalingViewport viewport;
	private Image imageFondo, imagePrueba;
	
	public MenuScreen(MissingWords missingwords) {
		super(missingwords);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		viewport = new ScalingViewport(Scaling.stretch, 800, 480);
		stage = new Stage(viewport, missingwords.getSB());
		
		imageFondo = new Image(missingwords.getAM().get("background2.png", Texture.class));
		stage.addActor(imageFondo);
		
		imagePrueba = new Image(missingwords.getAM().get("prueba.png", Texture.class));
		imagePrueba.setPosition(0, 0);
		stage.addActor(imagePrueba);
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
