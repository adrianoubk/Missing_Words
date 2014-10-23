package com.me.missingwords.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;

/**
 * 
 * Botón para pausar el juego.
 *
 */

public class PauseButton extends ImageButton {
	
	public PauseButton() {
		super(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("pauseButtonUp.png", Texture.class))),
			new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("pauseButtonDown.png", Texture.class))));
		
		setPosition(20, 425);
	}
}

