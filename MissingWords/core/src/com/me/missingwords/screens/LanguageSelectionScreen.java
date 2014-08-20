package com.me.missingwords.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.listeners.LanguageListener;

/** 
 * 
 * Clase LanguageSelectionScreen
 * 
 * Esta clase se encarga de mostrar la pantalla de selección de idioma del juego.
 *
 */

public class LanguageSelectionScreen extends BaseScreen {
	
	private Background background;
	private ImageButton buttonGerman, buttonEnglish;
	
	public LanguageSelectionScreen(MissingWords missingwords) {
		super(missingwords);
	}
	
	@Override
	public void render(float delta) {
		stage.act();
		stage.draw();
	}
	
	@Override
	public void show() {
		
		super.show();
		
		background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
		stage.addActor(background);
		
		buttonGerman = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("Germany-flag.png", Texture.class))));
		
		buttonGerman.setPosition(200, 200);
		buttonGerman.addListener(new LanguageListener("german", missingwords));
		stage.addActor(buttonGerman);
		
		buttonEnglish = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("United-kingdom-flag.png", Texture.class))));
		
		buttonEnglish.setPosition(400, 200);
		buttonEnglish.addListener(new LanguageListener("english", missingwords));
		stage.addActor(buttonEnglish);
	}
}
