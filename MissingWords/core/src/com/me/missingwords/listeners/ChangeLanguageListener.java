package com.me.missingwords.listeners;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Listener que cambia el idioma en los ajustes.
 *
 */

public class ChangeLanguageListener extends AbstractListener {
	private String language;
	
	public ChangeLanguageListener(String language, MissingWords missingWords) {
		super(missingWords);
		this.language = language;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		/* Reproducimos el efecto de sonido si está activo */
		missingWords.getSoundFX().getButton().play(missingWords.getSoundFX().getVolume());
		
		switch(language) {
		case "german": 
			missingWords.getSettingsScreen().getGermany().getStyle().imageUp = 
				new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("germanyFlagSelected.png", Texture.class))); 
			missingWords.getSettingsScreen().getUk().getStyle().imageUp = 
				new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("United-kingdom-flag.png", Texture.class)));
			missingWords.selectedLanguage = Language.german;
			break;
		case "english":
			missingWords.getSettingsScreen().getUk().getStyle().imageUp = 
				new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("ukFlagSelected.png", Texture.class))); 
			missingWords.getSettingsScreen().getGermany().getStyle().imageUp = 
					new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("Germany-flag.png", Texture.class)));
			missingWords.selectedLanguage = Language.english;
			break;
		}
	}
}
