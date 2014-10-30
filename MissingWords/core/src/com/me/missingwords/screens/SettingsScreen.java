package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;
import com.me.missingwords.actors.Background;
import com.me.missingwords.buttons.BackButton;
import com.me.missingwords.listeners.BackButtonListener;
import com.me.missingwords.listeners.ChangeLanguageListener;

public class SettingsScreen extends BaseScreen {
	private Background background;
	private BitmapFont fontTitle, fontSettings;
	private Label settings, language;
	private BackButton backButton;
	private CheckBox sound;
	private CheckBoxStyle style;
	private ImageButton germany, uk;
	private Label about, about2;

	public ImageButton getGermany() {
		return germany;
	}

	public ImageButton getUk() {
		return uk;
	}

	public SettingsScreen(MissingWords missingWords) {
		super(missingWords);
		
		/* Creamos las fuentes */
		fontTitle = new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false);
		fontSettings = new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false);
		
		/* Creamos el fondo de pantalla */
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		/* Creamos el título */
		settings = new Label("Settings", new LabelStyle(fontTitle, fontTitle.getColor()));
		settings.setPosition((MissingWords.VIEWPORT_WIDTH - settings.getMinWidth()) / 2, 400);
		stage.addActor(settings);
		
		/* Creamos el botón de vuelta atrás */
		backButton = new BackButton();
		backButton.addListener(new BackButtonListener(missingWords));
		stage.addActor(backButton);
		
		/* Creamos el ajuste del sonido */
		style = new CheckBoxStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("checkboxOff.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("checkboxOn.png", Texture.class))), 
				fontSettings, fontSettings.getColor());
		
		sound = new CheckBox(" Sound On", style);
		sound.setPosition((MissingWords.VIEWPORT_WIDTH - sound.getMinWidth()) / 2, 350);
		sound.setChecked(true);
		stage.addActor(sound);
		
		/* Creamos el ajuste de idioma */
		language = new Label("Language", new LabelStyle(fontSettings, fontSettings.getColor()));
		language.setPosition((MissingWords.VIEWPORT_WIDTH - language.getMinWidth()) / 2, 275);
		stage.addActor(language);
		
		if (missingWords.selectedLanguage == Language.english) {
			uk = new ImageButton(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("ukFlagSelected.png", Texture.class))));
			germany = new ImageButton(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("Germany-flag.png", Texture.class))));
		}
		else {
			uk = new ImageButton(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("United-kingdom-flag.png", Texture.class))));
			germany = new ImageButton(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("germanyFlagSelected.png", Texture.class))));
		}
		
		germany.addListener(new ChangeLanguageListener("german", missingWords));
		germany.setPosition(200, 125);
		stage.addActor(germany);
		
		uk.addListener(new ChangeLanguageListener("english", missingWords));
		uk.setPosition(470, 125);
		stage.addActor(uk);
		
		/* Creamos la información */
		
		about = new Label("About", new LabelStyle(fontSettings, fontSettings.getColor()));
		about.setPosition((MissingWords.VIEWPORT_WIDTH - about.getMinWidth()) / 2, 50);
		stage.addActor(about);
		
		about2 = new Label("MissingWords v1.0. https://github.com/adrianoubk/Missing_Words\nAuthor: Adrian Perez. Contributors: Anke Berns, Manuel Palomo.", 
				 new LabelStyle(new BitmapFont(), Color.BLACK));
		about2.setPosition((MissingWords.VIEWPORT_WIDTH - about2.getMinWidth()) / 2, 5);
		stage.addActor(about2);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		if (!sound.isChecked()) {
			sound.setText(" Sound Off");
			missingWords.getSoundFX().setVolume(0);
		}
		else {
			sound.setText(" Sound On");
			missingWords.getSoundFX().setVolume(1);
		}
		
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
