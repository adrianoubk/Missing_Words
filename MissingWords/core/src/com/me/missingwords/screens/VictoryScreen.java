package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List.*;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.*;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.listeners.ExitGameListener;
import com.me.missingwords.listeners.PlayAgainListener;

/**
 * 
 * Muestra las estadísticas y el ganador de la partida.
 *
 */

public class VictoryScreen extends BaseScreen {
	private Table stageTable, tableDown, tableUp, statistics;
	private BitmapFont font, fontList, fontButton;
	private List<String> wordList;
	private String[] wordArray;
	private ScrollPane scroll;
	private Label playedWords, totalTurns, totalWords, winner;
	private TextButton exitButton, playAgainButton;
	private SplitPane split;

	public VictoryScreen(MissingWords missingWords) {
		super(missingWords);
		
		font = new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false);
		
		fontList = new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false);
		
		fontButton = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		
		/* Establecemos el fondo */
		Background background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
	
		
		/* 
		 * Creamos la tabla que tendrá el tamaño del stage (pantalla) que contendrá un split
		 * pane  
		 */
		stageTable = new Table();
		stageTable.setFillParent(true);
		
		/* --------- ELEMENTOS SPLIT PANE: DOWN --------- */
		
		/* Creamos la lista de palabras */
		wordList = new List<String>(new ListStyle(
			fontList, 
			fontList.getColor(), 
			fontList.getColor(), 
			new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("selection.png", Texture.class)))));
		
		/* Creamos el scrollpane */
		scroll = new ScrollPane(wordList, new ScrollPaneStyle(new TextureRegionDrawable(
			new TextureRegion(MissingWords.myManager.get("background.png", Texture.class))), 
				null, 
				null, 
				new TextureRegionDrawable(
					new TextureRegion(MissingWords.myManager.get("verticalScroll.png", Texture.class))), 
				new TextureRegionDrawable(
					new TextureRegion(MissingWords.myManager.get("squareBlue.png", Texture.class)))));
		
		scroll.setScrollbarsOnTop(true);
		
		/* Creamos la etiqueta con las palabras jugadas */
		playedWords = new Label(null, new LabelStyle(font, font.getColor()));
		
		/* Creamos la tabla para la parte de abajo del SplitPane */
		tableDown = new Table();
		
		tableDown.add(playedWords).expand(); // añadimos la etiqueta
		tableDown.add(scroll).fill().expand(); // añadimos el scroll
		
		/* --------- FIN ELEMENTOS DOWN --------- */
		
		/* --------- ELEMENTOS SPLIT PANE: UP --------- */
		
		/* Creamos la tabla con las estadísticas */
		statistics = new Table();
		
		/* Creamos las etiquetas totalTurns y totalWords */
		totalTurns = new Label(null, new LabelStyle(font, font.getColor()));
		
		totalWords = new Label(null, new LabelStyle(font, font.getColor()));
		
		/* Añadimos las etiquetas a la tabla */
		statistics.add(totalTurns).expand();
		statistics.row();
		statistics.add(totalWords).expand();
		
		/* Creamos el botón de salida */
		exitButton = new TextButton("exit", new TextButtonStyle(
			new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("upButton.png", Texture.class))), 
			new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("downButton.png", Texture.class))), 
			null, 
			fontButton));
		
		exitButton.addListener(new ExitGameListener(missingWords));
		
		/* Creamos el botón de jugar de nuevo */
		playAgainButton = new TextButton("Play Again?", new TextButtonStyle(
			new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("upButton.png", Texture.class))), 
			new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("downButton.png", Texture.class))), 
			null, 
			fontButton));
		
		playAgainButton.addListener(new PlayAgainListener(missingWords));
		
		/* Creamos la tabla para la parte de abajo del SplitPane */
		tableUp = new Table();
		
		/* Creamos la etiqueta winner */
		winner = new Label(null, new LabelStyle(font, font.getColor()));
		
		/* Añadimos los elementos a la tabla */
		tableUp.add(winner).expand();
		tableUp.add(playAgainButton).expand().padTop(50);
		tableUp.row();
		tableUp.add(statistics);
		tableUp.add(exitButton).expand().padBottom(20);
		
		/* --------- FIN ELEMENTOS UP --------- */
		
		/* Creamos el SplitPane y añadimos las tablas con todos los elementos creados antes */
		split = new SplitPane(tableUp, tableDown, true, new SplitPaneStyle(
					new TextureRegionDrawable(
							new TextureRegion(MissingWords.myManager.get("split.png", Texture.class)))));
		
		/* Añadimos el SplitPane a la tabla y lo expandimos */
		stageTable.add(split).fill().expand();
		
		stage.addActor(stageTable); // Añadimos finalmente, la tabla al stage
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
		super.show();
		
		/* Creamos el array de strings con las palabras que ha jugado el jugador */
		wordArray =  missingWords.getGameScreen().getHuman().getPlayedWords().toArray(
				new String[missingWords.getGameScreen().getHuman().getPlayedWords().size()]);
		
		playedWords.setText("Played\nWords:\n" + 
		" " + missingWords.getGameScreen().getHuman().getPlayedWords().size() + " of " + 
		missingWords.getVocabulary().getVocabulary().size());
		
		wordList.setItems(wordArray); // insertamos el array de strings
		
		/* Asignamos las cadenas a las etiquetas una vez que se conocen los datos */
		totalTurns.setText("Total Turns: " + missingWords.getGameScreen().getTurn().getNumTurn());
		
		totalWords.setText("Total Words: " + missingWords.getGameScreen().getTotalWords());
		
		winner.setText(missingWords.getGameScreen().getWinner() + " wins!");
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
		font.dispose();
		fontList.dispose();
		fontButton.dispose();
	}
}
