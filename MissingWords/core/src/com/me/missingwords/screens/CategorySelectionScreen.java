package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.buttons.BackButton;
import com.me.missingwords.listeners.BackButtonListener;
import com.me.missingwords.listeners.CategorySelectionListener;

/**
 *  
 * Se encarga de mostrar la pantalla de selección de categoría de juego.
 *
 */

public class CategorySelectionScreen extends BaseScreen {
	private Background background;
	private Label title;
	private TextButton categoryButton;
	private TextButtonStyle tStyle;
	private TextureRegionDrawable up, down;
	private BitmapFont font;
	private BackButton backButton;
	private VerticalGroup buttonsBox;

	public CategorySelectionScreen(MissingWords missingWords) {
		super(missingWords);
		
		/* Creamos el fondo de pantalla */
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		/* Creamos el botón de vuelta */
		backButton = new BackButton();
		backButton.addListener(new BackButtonListener(missingWords));
		stage.addActor(backButton);
		
		/* Creamos el título */
		title = new Label("Categories", 
				new LabelStyle(new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false), Color.ORANGE));
			title.setPosition((MissingWords.VIEWPORT_WIDTH - title.getMinWidth()) / 2, 400);
			stage.addActor(title);
		
		/* Creamos los botones de categorías */	
		up = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("upButtonLarge.png", Texture.class)));
		down = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("downButton.png", Texture.class)));
		
		font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		
		tStyle = new TextButtonStyle(up, down, null, font);
		
		categoryButton = new TextButton("Days", tStyle);
		categoryButton.addListener(new CategorySelectionListener("days", missingWords));
		
		/* Creamos el contenedor de botones */
		buttonsBox = new VerticalGroup();
		
		/* Añadimos los botones */
		buttonsBox.addActor(categoryButton);

		/* Posicionamos el grupo */
		buttonsBox.setPosition((MissingWords.VIEWPORT_WIDTH - buttonsBox.getMaxWidth()) / 2, 350);
		
		stage.addActor(buttonsBox); // Añadimos el grupo al stage
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
		font.dispose();
		stage.dispose();
	}
}
