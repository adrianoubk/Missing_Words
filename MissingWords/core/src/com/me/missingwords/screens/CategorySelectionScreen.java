package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.actors.Font;
import com.me.missingwords.listeners.OptionListener;

/**
 *  
 * Clase CategorySelectionScreen
 * 
 * Esta clase se encarga de mostrar la pantalla de selección de categoría de juego.
 *
 */

public class CategorySelectionScreen extends BaseScreen {
	
	private Background background;
	private Font titleFont;
	private TextButton categoryButton;
	private TextButtonStyle tStyle;
	private TextureRegionDrawable up, down;
	private BitmapFont font;

	public CategorySelectionScreen(MissingWords missingWords) {
		super(missingWords);
	}
	
	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		stage.act();
		stage.draw();
	}
	
	@Override
	public void show() {
		super.show();
		
		background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
		stage.addActor(background);
		
		titleFont = new Font("Categories");
		stage.addActor(titleFont);
		
		up = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button04.png", Texture.class)));
		down = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button05.png", Texture.class)));
		
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		
		tStyle = new TextButtonStyle(up, down, null, font);
		
		categoryButton = new TextButton("Days", tStyle);
		categoryButton.setPosition(245, 200);
		categoryButton.addListener(new OptionListener("days", missingWords));
		stage.addActor(categoryButton);
	}

}
