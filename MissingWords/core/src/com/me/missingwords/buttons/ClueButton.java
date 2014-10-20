package com.me.missingwords.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * 
 * Representa un botón de pista
 *
 */

public class ClueButton extends ImageButton {
	
	private ImageButtonStyle enableStyle, disableStyle;

	public ClueButton(Drawable imageUp, Drawable imageDown, Drawable disable) {
		super(imageUp, imageDown);
		
		enableStyle = new ImageButtonStyle(getStyle());
		disableStyle = new ImageButtonStyle();
		disableStyle.imageUp = disable;
	}
	
	/* enableStyle(): establece un estilo en el que se permite usar el botón */
	public void enableStyle() {
		setStyle(enableStyle);
	}
	
	/* disableStyle(): establece un estilo en el que no se permite usar el botón */
	public void disableStyle() {
		setStyle(disableStyle);
	}
}
