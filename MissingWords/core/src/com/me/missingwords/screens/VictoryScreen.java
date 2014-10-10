package com.me.missingwords.screens;

import java.util.ArrayList;

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

public class VictoryScreen extends BaseScreen {
	
	private Table table, table2, winner1, statistics;
	private BitmapFont font, fontList, fontButton;

	public VictoryScreen(MissingWords missingWords) {
		super(missingWords);
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
		
		font = new BitmapFont(Gdx.files.internal("title.fnt"), Gdx.files.internal("title.png"), false);
		
		fontList = new BitmapFont(Gdx.files.internal("listFont.fnt"), Gdx.files.internal("listFont.png"), false);
		
		fontButton = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		
		ArrayList<String> prueba2 = new ArrayList<String>();
		prueba2.add("hola");
		prueba2.add("amigos");
		
		String[] stringArray =  missingWords.getGameScreen().getPlayedWords().toArray(new String[missingWords.getGameScreen().getPlayedWords().size()]);
		
		Background background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
		stage.addActor(background);
	
		table = new Table();
		table.setFillParent(true);
		
		List<String> listPrueba = new List<String>(new ListStyle(
				fontList, 
				fontList.getColor(), 
				fontList.getColor(), 
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("selection.png", Texture.class)))));
			
		
		listPrueba.setItems(stringArray);
		
		ScrollPane scroll = new ScrollPane(listPrueba, new ScrollPaneStyle(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("bg_grasslands.png", Texture.class))), 
				null, 
				null, 
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("verticalScroll.png", Texture.class))), 
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("squareBlue.png", Texture.class)))));
		
		scroll.setScrollbarsOnTop(true);
		
		Label l1 = new Label("Played\nWords", new LabelStyle(font, font.getColor()));
		
		table2 = new Table();
		
		table2.add(l1).expand();
		table2.add(scroll).fill().expand();
		
		statistics = new Table();
		
		Label totalTurns = new Label("Total Turns: " + missingWords.getGameScreen().getTurn().getNumTurn(), new LabelStyle(font, font.getColor()));
		Label totalWords = new Label("Total Words: " + missingWords.getGameScreen().getTotalWords(), new LabelStyle(font, font.getColor()));
		
		TextButton exitButton = new TextButton("exit", new TextButtonStyle(
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("upButton.png", Texture.class))), 
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("blue_button05.png", Texture.class))), 
				null, 
				fontButton));
		
		exitButton.addListener(new ExitGameListener(missingWords));
		
		TextButton playButton = new TextButton("Play Again?", new TextButtonStyle(
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("upButton.png", Texture.class))), 
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("blue_button05.png", Texture.class))), 
				null, 
				fontButton));
		
		playButton.addListener(new PlayAgainListener(missingWords));
		
		statistics.add(totalTurns).expand();
		statistics.row();
		statistics.add(totalWords).expand();
		
		
		winner1 = new Table();
		
		Label lWinner = new Label(missingWords.getGameScreen().getWinner() + " wins!", new LabelStyle(font, font.getColor()));
		
		winner1.add(lWinner).expand();
		winner1.add(playButton).expand().padTop(50);
		winner1.row();
		winner1.add(statistics);
		winner1.add(exitButton).expand().padBottom(20);
		
		SplitPane split = new SplitPane(winner1, table2, true, new SplitPaneStyle(
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("split.png", Texture.class)))));
		
		table.add(split).fill().expand();
		
		stage.addActor(table);
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
