package com.me.missingwords.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.*;
import com.me.missingwords.utils.Scores;
import com.me.missingwords.utils.Vocabulary;

public class GameScreen extends BaseScreen {
	
	public final int MAX_TILES = 16;

	private ArrayList<Tile> tOriginal, tCopy;
	private Turn turn;
	private TimeBar barPrueba;
	private Background background;
	private Vocabulary vocab;
	private Scores scores;
	private Slider slider;
	private SubmitBox submitBox;
	private TileBox tileBox;
	private ImageButton button;
	private TextureRegionDrawable buttonUp, buttonDown;
	
	public GameScreen(MissingWords missingwords) {
		super(missingwords);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		submitBox.update();
		slider.update(submitBox.getNumActors());
		
		stage.act();
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {	
		Gdx.input.setInputProcessor(stage);

		try {
			vocab = new Vocabulary();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			scores = new Scores();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		createTiles();
		
		Collections.shuffle(tOriginal);

		tCopy = new ArrayList<Tile>(); // Copia del arrayList
		for (int i = 0; i < MAX_TILES; ++i) {
			tCopy.add(new Tile(tOriginal.get(i)));;
		}
		
		background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
		stage.addActor(background);
		
		addTiles();
		addListeners();
		
		submitBox = new SubmitBox();
		stage.addActor(submitBox);
		
		slider = new Slider(MissingWords.myManager.get("grey_sliderHorizontal.png", Texture.class));
		stage.addActor(slider);
		
		turn = new Turn(1);
		stage.addActor(turn);
		
		barPrueba = new TimeBar();
		stage.addActor(barPrueba);
		
		buttonUp = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class)));
		buttonDown = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button14.png", Texture.class)));
		
		button = new ImageButton(buttonUp, buttonDown);
		button.setPosition((MissingWords.VIEWPORT_WIDTH - button.getMinWidth()) / 2, 5);
		
		stage.addActor(button);
	}
	
	private void addTiles() {
		int i = 0;
		
		tileBox = new TileBox(new Table());
		
		Iterator<Tile> iterator = tOriginal.iterator();
		
		while (iterator.hasNext()) {
			if (i == 4 || i == 8 || i == 12) {
				tileBox.getTileTable().row();
			}
					
			tileBox.getTileTable().add(iterator.next());
			++i;
		}
		
		stage.addActor(tileBox);
	}

	private void createTiles() {
		String randomWord;
		String[] arrayWord;
		ArrayList<String> adaptedWord = new ArrayList<String>();
		String randomLetter;
		
		tOriginal = new ArrayList<Tile>(MAX_TILES);
		
		randomWord = vocab.randomKey();
		arrayWord = randomWord.split("(?!^)");
		
		for (int i = 0; i < arrayWord.length; ++i) {
			System.out.print(arrayWord[i]);
		}
		
		adaptedWord = adaptWord(arrayWord);
		
		System.out.print("\n");
		
		/* Creaci�n de fichas */
		
		for (int i = 0; i < adaptedWord.size(); ++i) {
			System.out.println(adaptedWord.get(i));
			tOriginal.add(new Tile(adaptedWord.get(i), scores.getScores().get(adaptedWord.get(i))));
		}
		
		for (int i = adaptedWord.size(); i < MAX_TILES; ++i) {
			randomLetter = scores.randomKey();
			tOriginal.add(new Tile(randomLetter, scores.getScores().get(randomLetter)));
		}
	}
	
	private ArrayList<String> adaptWord(String[] arrayWord) {
		int i = 0;
		ArrayList<String> newArray = new ArrayList<String>();
		
		while (i < arrayWord.length) {
			if ((arrayWord[i].equals("a") || arrayWord[i].equals("o") || arrayWord[i].equals("u")) 
					&& (i + 1) < arrayWord.length && arrayWord[i + 1].equals("e")) {
					switch (arrayWord[i]) {
						case "a": newArray.add("ae"); break;
						case "o": newArray.add("oe"); break;
						case "u": newArray.add("ue"); break;
					}
					
					i += 2;
			}
			else {
				newArray.add(arrayWord[i]);
				++i;
			}
		}
		
		return newArray;
	}
	
	private void addListeners() {
		for (int i = 0; i < MAX_TILES; ++i)  {
			final int index = i;
			tOriginal.get(i).addListener(new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					tOriginal.get(index).setVisible(false);
					tCopy.get(index).setSmallSize();
					submitBox.addActor(tCopy.get(index));
					submitBox.increaseNumActors();
					
					System.out.println("index: " + index);
			
				}
			});
			
			tCopy.get(i).addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					tOriginal.get(index).setVisible(true);
					submitBox.removeActor(tCopy.get(index));
					submitBox.decreaseNumActors();
				}
			});
		}
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

}
