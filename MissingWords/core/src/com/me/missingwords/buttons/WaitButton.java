package com.me.missingwords.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;

public class WaitButton extends TextButton {
	
	public WaitButton() {
		super("Wait", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("rollButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("rollButtonDown.png", Texture.class))), 
				null, 
				new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false)));
		
		setPosition(180, 2);
	}
	
	public void show() {
		setVisible(true);
		setTouchable(Touchable.enabled);
	}
	
	public void hide() {
		setVisible(false);
		setTouchable(Touchable.disabled);
	}
}
