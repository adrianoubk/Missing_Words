package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.buttons.BackButton;
import com.me.missingwords.listeners.BackButtonListener;

/**
 * 
 * Muestra las instrucciones del juego.
 *
 */

public class InstructionsScreen extends BaseScreen {
	private Background background;
	private Label title, instructions, translationLabel, letterLabel, lengthLabel;
	private BackButton backButton;
	private Image translation, letter, length;
	
	public InstructionsScreen(MissingWords missingWords) {
		super(missingWords);
		
		/* Creamos el fondo */
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		/* Creamos una etiqueta con el titulo */
		title = new Label("Instructions", 
			new LabelStyle(new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false), Color.ORANGE));
		title.setPosition((MissingWords.VIEWPORT_WIDTH - title.getMinWidth()) / 2, 410);
		stage.addActor(title);
		
		/* Creamos el botón de vuelta atrás */
		backButton = new BackButton();
		backButton.addListener(new BackButtonListener(missingWords));
		stage.addActor(backButton);
		
		/* Creamos los elementos con las instrucciones */	
		instructions = new Label("- Goal: Win the mini-game by making words. Each\n"
				+ "word you form you get rolls to move your character.\n"
				+ "Be careful with the holes! Don't forget that you have\n"
				+ "to use at least one of your rolls before use \"wait\".\n"
				+ "- Clues: You can only use 2 clues per turn. Min. 1 roll.\n", 
				new LabelStyle(new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false), Color.BLACK));
		instructions.setPosition(10, 225);
		stage.addActor(instructions);
		
		translation = new Image(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("translationButtonUp.png", Texture.class))));
		translation.setPosition(10, 170);
		stage.addActor(translation);
		
		letter = new Image(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("letterButtonUp.png", Texture.class))));
		letter.setPosition(10, 120);
		stage.addActor(letter);
		
		length = new Image(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("lengthButtonUp.png", Texture.class))));
		length.setPosition(10, 70);
		stage.addActor(length);
		
		translationLabel = new Label("-> translation of one of the words (-2 rolls).", 
				new LabelStyle(new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false), Color.BLACK));
		translationLabel.setPosition(70, 180);
		stage.addActor(translationLabel);
		
		letterLabel = new Label("-> first letter of one of the words (-1 roll).", 
				new LabelStyle(new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false), Color.BLACK));
		letterLabel.setPosition(70, 131);
		stage.addActor(letterLabel);
		
		lengthLabel = new Label("-> length of one of the words (-1 roll).", 
				new LabelStyle(new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false), Color.BLACK));
		lengthLabel.setPosition(70, 82);
		stage.addActor(lengthLabel);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		stage.act();
		stage.draw();
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
