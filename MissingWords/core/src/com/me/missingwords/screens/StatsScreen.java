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
	private VerticalGroup stats;
	private Label player, hits, largestWord, bestWord, gamesWon, gamesLost, cluesUsed;
	private Background background;
	private BitmapFont fontPlayer, fontStats;
	private BackButton backButton;
	
	public StatsScreen(MissingWords missingWords) {
		super(missingWords);
		
		fontPlayer = new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false);
		fontStats = new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false);
		
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		backButton = new BackButton();
		backButton.addListener(new BackButtonListener(missingWords));
		stage.addActor(backButton);
		
		stats = new VerticalGroup();
		stats.setPosition(50, 380);
		stats.align(Align.left);
		stage.addActor(stats);
		
		player = new Label("Stats", new LabelStyle(fontPlayer, fontPlayer.getColor()));
		player.setPosition((MissingWords.VIEWPORT_WIDTH - player.getMinWidth()) / 2, 400);
		stage.addActor(player);
		
		hits = new Label("% hits: " + missingWords.getStatsData().getPercentageHits() + " %", new LabelStyle(fontStats, fontStats.getColor()));
		stats.addActor(hits);
		
		gamesWon = new Label("Games won: " + missingWords.getStatsData().getGamesWon(), new LabelStyle(fontStats, fontStats.getColor()));
		stats.addActor(gamesWon);
		
		gamesLost = new Label("Games lost: " + missingWords.getStatsData().getGamesLost(), new LabelStyle(fontStats, fontStats.getColor()));
		stats.addActor(gamesLost);
		
		largestWord = new Label("Largest word: "  + missingWords.getStatsData().getLargestWord(), new LabelStyle(fontStats, fontStats.getColor()));
		stats.addActor(largestWord);
		
		bestWord = new Label("Best word: " + missingWords.getStatsData().getBestWord(), new LabelStyle(fontStats, fontStats.getColor()));
		stats.addActor(bestWord);
		
		cluesUsed = new Label("Clues used: " + missingWords.getStatsData().getCluesUsed(), new LabelStyle(fontStats, fontStats.getColor()));
		stats.addActor(cluesUsed);
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
		// TODO Auto-generated method stub
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
