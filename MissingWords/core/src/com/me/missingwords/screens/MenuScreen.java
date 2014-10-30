package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.listeners.MenuListener;

/** 
 * 
 * Muestra el menú principal del juego.
 *
 */

public class MenuScreen extends BaseScreen {
	private Background background;
	private Label title;
	private TextButton playerVsCpu, singlePlayer, stats, settings, instructions, exit;
	private TextButtonStyle tStyle;
	private TextureRegionDrawable up, down;
	private BitmapFont font;
	private VerticalGroup buttonsBox;

	public MenuScreen(MissingWords missingWords) {
		super(missingWords);
		
		/* Creamos el fondo */
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		/* Creamos una etiqueta con el titulo */
		title = new Label("Missing Words", 
			new LabelStyle(new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false), Color.ORANGE));
		title.setPosition((MissingWords.VIEWPORT_WIDTH - title.getMinWidth()) / 2, 400);
		stage.addActor(title);
		
		/* Creamos los botones */
		up = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("upButtonLarge.png", Texture.class)));
		down = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("downButton.png", Texture.class)));
		
		font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		
		tStyle = new TextButtonStyle(up, down, null, font);
		
		playerVsCpu = new TextButton("Player VS CPU", tStyle);
		playerVsCpu.addListener(new MenuListener(missingWords, "playervscpu"));
		
		singlePlayer = new TextButton("SinglePlayer", tStyle);
		singlePlayer.addListener(new MenuListener(missingWords, "singleplayer"));
		
		stats = new TextButton("Stats", tStyle);
		stats.addListener(new MenuListener(missingWords, "stats"));
		
		settings = new TextButton("Settings", tStyle);
		settings.addListener(new MenuListener(missingWords, "settings"));
		
		instructions = new TextButton("Instructions", tStyle);
		instructions.addListener(new MenuListener(missingWords, "instructions"));
		
		exit = new TextButton("Exit", tStyle);
		exit.addListener(new MenuListener(missingWords, "exit"));
		
		/* Creamos un grupo para añadir los botones */
		buttonsBox = new VerticalGroup();
		buttonsBox.space(10); // Espacio entre botones
		
		/* Añadimos los botones */
		buttonsBox.addActor(playerVsCpu);
		buttonsBox.addActor(singlePlayer);
		buttonsBox.addActor(stats);
		buttonsBox.addActor(settings);
		buttonsBox.addActor(instructions);
		buttonsBox.addActor(exit);
		
		/* Posicionamos el grupo */
		buttonsBox.setPosition((MissingWords.VIEWPORT_WIDTH - buttonsBox.getMaxWidth()) / 2, 375);
		
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
