package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.actors.Font;
import com.me.missingwords.listeners.GameModeSelectionListener;
import com.me.missingwords.listeners.StatsListener;

/** 
 * 
 * Muestra el menú principal del juego.
 *
 */

public class MenuScreen extends BaseScreen {
	private Background background;
	private Font titleFont;
	private TextButton playerVsCpu, singlePlayer, stats;
	private TextButtonStyle tStyle;
	private TextureRegionDrawable up, down;
	private BitmapFont font;

	public MenuScreen(MissingWords missingWords) {
		super(missingWords);
		
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		titleFont = new Font("Missing Words");
		stage.addActor(titleFont);
		
		up = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("upButtonLarge.png", Texture.class)));
		down = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("downButton.png", Texture.class)));
		
		font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		
		tStyle = new TextButtonStyle(up, down, null, font);
		
		playerVsCpu = new TextButton("Player VS CPU", tStyle);
		playerVsCpu.setPosition(265, 200);
		playerVsCpu.addListener(new GameModeSelectionListener("playervscpu", missingWords));
		stage.addActor(playerVsCpu);
		
		singlePlayer = new TextButton("SinglePlayer", tStyle);
		singlePlayer.setPosition(265, 100);
		singlePlayer.addListener(new GameModeSelectionListener("singleplayer", missingWords));
		stage.addActor(singlePlayer);
		
		stats = new TextButton("Stats", tStyle);
		stats.setPosition(265, 0);
		stats.addListener(new StatsListener(missingWords));
		stage.addActor(stats);
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
}
