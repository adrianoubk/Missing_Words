package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.buttons.BackButton;
import com.me.missingwords.listeners.BackButtonListener;
import com.me.missingwords.listeners.CategoryStatsListener;

/**
 * 
 * Muestra las pantalla de estadísticas del jugador.
 *
 */

public class StatsScreen extends BaseScreen {
	private VerticalGroup statsBox, categoriesLeft, categoriesRight;
	private Label stats, hits, largestWord, bestWord, gamesWon, gamesLost, cluesUsed,
				  general, categories;
	private Background background;
	private BitmapFont fontPlayer, fontStats;
	private BackButton backButton;
	private TextButton days, months, wquestions, colours, size, classroom, body_parts, feelings,
	                   university, city, free_time;
	
	public StatsScreen(MissingWords missingWords) {
		super(missingWords);
		
		fontPlayer = new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false);
		fontStats = new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false);
		
		/* Creamos el fondo de pantalla */
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		/* Creamos el botón de vuelta atrás */
		backButton = new BackButton();
		backButton.addListener(new BackButtonListener(missingWords));
		stage.addActor(backButton);
		
		/* Creamos el título */
		stats = new Label("Stats", new LabelStyle(fontPlayer, fontPlayer.getColor()));
		stats.setPosition((MissingWords.VIEWPORT_WIDTH - stats.getMinWidth()) / 2, 400);
		stage.addActor(stats);
		
		/* Creamos las etiquetas con las estadísticas generales */
		general =  new Label("- General:", new LabelStyle(fontStats, fontStats.getColor()));
		hits = new Label("% hits: " + missingWords.getStatsData().getPercentageHits() + " %", new LabelStyle(fontStats, fontStats.getColor()));
		gamesWon = new Label("Games won: " + missingWords.getStatsData().getGamesWon(), new LabelStyle(fontStats, fontStats.getColor()));
		gamesLost = new Label("Games lost: " + missingWords.getStatsData().getGamesLost(), new LabelStyle(fontStats, fontStats.getColor()));
		largestWord = new Label("Largest word: "  + missingWords.getStatsData().getLargestWord(), new LabelStyle(fontStats, fontStats.getColor()));
		bestWord = new Label("Best word: " + missingWords.getStatsData().getBestWord(), new LabelStyle(fontStats, fontStats.getColor()));	
		cluesUsed = new Label("Clues used: " + missingWords.getStatsData().getCluesUsed(), new LabelStyle(fontStats, fontStats.getColor()));
		
		/* Creamos el grupo que va a contener las etiquetas de las estadísticas generales */
		statsBox = new VerticalGroup();
		statsBox.setPosition(10, 380);
		statsBox.align(Align.left);
		
		/* Añadimos las etiquetas al grupo */
		statsBox.addActor(general);
		statsBox.addActor(hits);
		statsBox.addActor(gamesWon);
		statsBox.addActor(gamesLost);
		statsBox.addActor(largestWord);
		statsBox.addActor(bestWord);
		statsBox.addActor(cluesUsed);
		
		stage.addActor(statsBox); // Añadimos el grupo al stage
		
		/* Creamos la etiqueta de categorías para las estadísticas de categorías */
		categories = new Label("- Categories:", new LabelStyle(fontStats, fontStats.getColor()));
		
		/* Creamos los botones de estadísticas de categorías */
		days = new TextButton("Days", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		days.addListener(new CategoryStatsListener(missingWords, "days"));
		
		months = new TextButton("Months", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		months.addListener(new CategoryStatsListener(missingWords, "months"));
		
		wquestions = new TextButton("W-Questions", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		wquestions.addListener(new CategoryStatsListener(missingWords, "wquestions"));
		
		colours = new TextButton("Colours", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		colours.addListener(new CategoryStatsListener(missingWords, "colours"));
		
		size = new TextButton("Size", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		size.addListener(new CategoryStatsListener(missingWords, "size"));
		
		classroom = new TextButton("Classroom", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		classroom.addListener(new CategoryStatsListener(missingWords, "classroom"));
		
		body_parts = new TextButton("Body-parts", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		body_parts.addListener(new CategoryStatsListener(missingWords, "bodyparts"));
		
		feelings = new TextButton("Feelings", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		feelings.addListener(new CategoryStatsListener(missingWords, "feelings"));
		
		university = new TextButton("University", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		university.addListener(new CategoryStatsListener(missingWords, "university"));
		
		city = new TextButton("City", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		city.addListener(new CategoryStatsListener(missingWords, "city"));
		
		/* El botón free_time va por separado, por darle una mejor apariencia */
		free_time = new TextButton("Free Time", 
				new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class))), 
				null, 
				null, 
				fontStats));
		
		free_time.addListener(new CategoryStatsListener(missingWords, "freetime"));
		free_time.setPosition(500, 30);
		
		stage.addActor(free_time);
		
		/* Creamos os grupos que van a contener las etiquetas de las estadísticas de categorías */
		
		categoriesLeft = new VerticalGroup();
		categoriesLeft.setPosition(400, 380);
		categoriesLeft.align(Align.left);
		categoriesLeft.space(2);
		
		categoriesLeft.addActor(categories);
		categoriesLeft.addActor(days);
		categoriesLeft.addActor(months);
		categoriesLeft.addActor(wquestions);
		categoriesLeft.addActor(colours);
		categoriesLeft.addActor(size);
		
		stage.addActor(categoriesLeft);
		
		categoriesRight = new VerticalGroup();
		categoriesRight.setPosition(600, 337);
		categoriesRight.align(Align.left);
		categoriesRight.space(2);
		
		categoriesRight.addActor(classroom);
		categoriesRight.addActor(body_parts);
		categoriesRight.addActor(feelings);
		categoriesRight.addActor(university);
		categoriesRight.addActor(city);
		
		stage.addActor(categoriesRight);
	}
	
	/* updateLabels(): actualiza los valores de las stats */
	public void updateLabels() {
		hits.setText("% hits: " + missingWords.getStatsData().getPercentageHits() + " %");
		gamesWon.setText("Games won: " + missingWords.getStatsData().getGamesWon());
		gamesLost.setText("Games lost: " + missingWords.getStatsData().getGamesLost());
		largestWord.setText("Largest word: "  + missingWords.getStatsData().getLargestWord());
		bestWord.setText("Best word: " + missingWords.getStatsData().getBestWord());
		cluesUsed.setText("Clues used: " + missingWords.getStatsData().getCluesUsed());
	}

	@Override
	public void render(float delta) {
		super.render(delta);

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
		super.dispose();
		fontPlayer.dispose();
		fontStats.dispose();
		stage.dispose();
	}
}
