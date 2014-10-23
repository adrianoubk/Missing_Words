package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.buttons.BackButton;
import com.me.missingwords.listeners.BackButtonListener;

/**
 * 
 * Muestra las pantalla de estadísticas del jugador.
 *
 */

public class StatsScreen extends BaseScreen {
	private VerticalGroup statsBox;
	private Label stats, hits, largestWord, bestWord, gamesWon, gamesLost, cluesUsed;
	private Background background;
	private BitmapFont fontPlayer, fontStats;
	private BackButton backButton;
	
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
		
		/* Creamos las etiquetas con las estadísticas */
		hits = new Label("% hits: " + missingWords.getStatsData().getPercentageHits() + " %", new LabelStyle(fontStats, fontStats.getColor()));
		gamesWon = new Label("Games won: " + missingWords.getStatsData().getGamesWon(), new LabelStyle(fontStats, fontStats.getColor()));
		gamesLost = new Label("Games lost: " + missingWords.getStatsData().getGamesLost(), new LabelStyle(fontStats, fontStats.getColor()));
		largestWord = new Label("Largest word: "  + missingWords.getStatsData().getLargestWord(), new LabelStyle(fontStats, fontStats.getColor()));
		bestWord = new Label("Best word: " + missingWords.getStatsData().getBestWord(), new LabelStyle(fontStats, fontStats.getColor()));	
		cluesUsed = new Label("Clues used: " + missingWords.getStatsData().getCluesUsed(), new LabelStyle(fontStats, fontStats.getColor()));
		
		/* Creamos el grupo que va a contener las etiquetas de las estadísticas */
		statsBox = new VerticalGroup();
		statsBox.setPosition(50, 380);
		statsBox.align(Align.left);
		
		/* Añadimos las etiquetas al grupo */
		statsBox.addActor(hits);
		statsBox.addActor(gamesWon);
		statsBox.addActor(gamesLost);
		statsBox.addActor(largestWord);
		statsBox.addActor(bestWord);
		statsBox.addActor(cluesUsed);
		
		stage.addActor(statsBox); // Añadimos el grupo al stage
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
