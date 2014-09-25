package com.me.missingwords.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ClueButton extends ImageButton {
	
	private ImageButtonStyle enableStyle, disableStyle;

	public ClueButton(Drawable imageUp, Drawable imageDown, Drawable disable) {
		super(imageUp, imageDown);
		
		enableStyle = new ImageButtonStyle(getStyle());
		disableStyle = new ImageButtonStyle();
		disableStyle.imageUp = disable;
	}
	
	public void enableStyle() {
		setStyle(enableStyle);
	}
	
	public void disableStyle() {
		setStyle(disableStyle);
	}
}
