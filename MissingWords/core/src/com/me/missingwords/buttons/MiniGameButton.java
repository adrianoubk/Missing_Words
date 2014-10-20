package com.me.missingwords.buttons;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * 
 * Representa un botón del minijuego. Clase abstracta.
 *
 */

public abstract class MiniGameButton extends TextButton {
	
	public MiniGameButton(String text, TextButtonStyle style) {
		super(text, style);
	}
	
	/* show(): muestra el botón y activa su uso */
	public void show() {
		setVisible(true);
		setTouchable(Touchable.enabled);
	}
	
	/* hide(): oculta el botón y cancela su uso */
	public void hide() {
		setVisible(false);
		setTouchable(Touchable.disabled);
	}
}
