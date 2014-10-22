package com.me.missingwords.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;

/**
 * 
 * Botón para moverse por el menú
 *
 */

public class BackButton extends ImageButton {

	public BackButton() {
		super(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("backButtonUp.png", Texture.class))),
			new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("backButtonDown.png", Texture.class))));
		
		setPosition(20, 20);
	}
}
