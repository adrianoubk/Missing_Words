package com.me.missingwords.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;

public class SubmitButton extends ImageButton {

	private final int POSITION_X = 479;
	private final int POSITION_Y = 5;
	
	public SubmitButton() {
		super(
		new TextureRegionDrawable(new TextureRegion(
				MissingWords.myManager.get("submitButtonUp.png", Texture.class))),
		new TextureRegionDrawable(new TextureRegion(
				MissingWords.myManager.get("submitButtonDown.png", Texture.class))));
		
		setPosition(POSITION_X, POSITION_Y);
	}

}
