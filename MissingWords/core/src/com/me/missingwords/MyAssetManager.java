package com.me.missingwords;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * 
 * Gestor de recursos de la aplicación.
 *
 */

public class MyAssetManager extends AssetManager {
	public MyAssetManager() {
		super();
	}
	
	public void loadAssets() {
		/* 
		 * Con la función load() añadimos los recursos a la cola de carga, pero aún no se
		 * han cargado. 
		 */
		load("loadingBackground.png", Texture.class);
		load("tiles.atlas", TextureAtlas.class);
		load("background.png", Texture.class);
		load("barLoading.png", Texture.class);
		load("barBackground.png", Texture.class);
		load("tileBox.png", Texture.class);
		load("upButtonLarge.png", Texture.class);
		load("grey_sliderHorizontal.png", Texture.class);
		load("submitButtonUp.png", Texture.class);
		load("submitButtonDown.png", Texture.class);
		load("letterButtonUp.png", Texture.class);
		load("letterButtonDown.png", Texture.class);
		load("Germany-flag.png", Texture.class);
		load("United-kingdom-flag.png", Texture.class);
		load("downButton.png", Texture.class);
		load("translationButtonDown.png", Texture.class);
		load("translationButtonUp.png", Texture.class);
		load("lengthButtonDown.png", Texture.class);
		load("lengthButtonUp.png", Texture.class);
		load("letterButton_Used.png", Texture.class);
		load("lengthButton_Used.png", Texture.class);
		load("translationButton_Used.png", Texture.class);
		load("none.png", Texture.class);
		load("holeGrass.png", Texture.class);
		load("rollButtonDown.png", Texture.class);
		load("rollButtonUp.png", Texture.class);
		load("1.png", Texture.class);
		load("2.png", Texture.class);
		load("3.png", Texture.class);
		load("4.png", Texture.class);
		load("5.png", Texture.class);
		load("6.png", Texture.class);
		load("player.png", Texture.class);
		load("transparentTile.png", Texture.class);
		load("npc.png", Texture.class);
		load("split.png", Texture.class);
		load("squareBlue.png", Texture.class);
		load("verticalScroll.png", Texture.class);
		load("selection.png", Texture.class);
		load("upButton.png", Texture.class);
		load("continueButtonUp.png", Texture.class);
		load("continueButtonDown.png", Texture.class);
		load("bothPlayers.png", Texture.class);
		load("backButtonUp.png", Texture.class);
		load("backButtonDown.png", Texture.class);
		load("pauseButtonUp.png", Texture.class);
		load("pauseButtonDown.png", Texture.class);
		load("backgroundDialog.png", Texture.class);
		load("sounds/roll.mp3", Sound.class);
		load("sounds/tap.ogg", Sound.class);
		load("sounds/menu.wav", Sound.class);
		load("sounds/positive.wav", Sound.class);
		load("sounds/negative.wav", Sound.class);
		load("sounds/clue.mp3", Sound.class);
		load("sounds/hole.wav", Sound.class);
		load("sounds/win.wav", Sound.class);
		load("checkboxOn.png", Texture.class);
		load("checkboxOff.png", Texture.class);
		load("germanyFlagSelected.png", Texture.class);
		load("ukFlagSelected.png", Texture.class);
		load("sounds/timeout.mp3", Sound.class);
		load("blue_button13.png", Texture.class);
	}
}
