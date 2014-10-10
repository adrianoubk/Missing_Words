package com.me.missingwords.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
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
	private HorizontalGroup languageBox;
	
	public LanguageSelectionScreen(MissingWords missingWords) {
		super(missingWords);
		
		background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
		stage.addActor(background);
		
		buttonGerman = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("Germany-flag.png", Texture.class))));
		
		buttonGerman.addListener(new LanguageListener("german", missingWords));
		
		buttonEnglish = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("United-kingdom-flag.png", Texture.class))));
		
		buttonEnglish.addListener(new LanguageListener("english", missingWords));
		
		languageBox = new HorizontalGroup();
		languageBox.space(100);
		
		languageBox.addActor(buttonGerman);
		languageBox.addActor(buttonEnglish);
		
		languageBox.setPosition((MissingWords.VIEWPORT_WIDTH - languageBox.getMinWidth()) / 2, 
				(MissingWords.VIEWPORT_HEIGHT - languageBox.getMaxHeight()) / 2);
		
		stage.addActor(languageBox);
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
		/*
		background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
		stage.addActor(background);
		
		buttonGerman = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("Germany-flag.png", Texture.class))));
		
		buttonGerman.addListener(new LanguageListener("german", missingWords));
		
		buttonEnglish = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("United-kingdom-flag.png", Texture.class))));
		
		buttonEnglish.addListener(new LanguageListener("english", missingWords));
		
		languageBox = new HorizontalGroup();
		languageBox.space(100);
		
		languageBox.addActor(buttonGerman);
		languageBox.addActor(buttonEnglish);
		
		languageBox.setPosition((MissingWords.VIEWPORT_WIDTH - languageBox.getMinWidth()) / 2, 
				(MissingWords.VIEWPORT_HEIGHT - languageBox.getMaxHeight()) / 2);
		
		stage.addActor(languageBox);
		*/
	}
}
