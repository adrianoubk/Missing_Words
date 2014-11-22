package com.me.missingwords.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Botón para esperar al siguiente turno.
 *
 */

public class WaitButton extends MiniGameButton {
	private final int POSITION_X = 180;
	private final int POSITION_Y = 2;
	
	public WaitButton(MissingWords missingWords) {
		super("Wait", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("rollButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("rollButtonDown.png", Texture.class))), 
				null, 
				new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false)));
		
		if (missingWords.selectedLanguage == Language.english)
			setText("Wait");
		else
			setText("Warte!");
		
		setPosition(POSITION_X, POSITION_Y);
	}
}
