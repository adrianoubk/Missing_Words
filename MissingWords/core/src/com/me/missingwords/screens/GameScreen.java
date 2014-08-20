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
import com.me.missingwords.MissingWords.Language;
import com.me.missingwords.actors.*;
import com.me.missingwords.listeners.*;
import com.me.missingwords.utils.Scores;
import com.me.missingwords.utils.Vocabulary;

/** Clase GameScreen
 * 
 * Clase que muestra la pantalla de juego.
 *
 */

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
	private ImageButton button, button2;
	private TextureRegionDrawable buttonUp, buttonDown, buttonUp2, buttonDown2;

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
		
		super.show();
		
		try {
			vocab = new Vocabulary(missingwords.selectedLanguage, missingwords.selectedCategory);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			scores = new Scores(missingwords.selectedLanguage);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
		stage.addActor(background);
		
		createTiles();
		shuffleTiles();
		addTiles();
		
		submitBox = new SubmitBox();
		stage.addActor(submitBox);
		
		addListeners();
		
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
		
		button.addListener(new InputButtonListener(vocab, submitBox, stage));
		
		stage.addActor(button);
		
		buttonUp2 = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("green_button11.png", Texture.class)));
		buttonDown2 = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("green_button12.png", Texture.class)));
		
		button2 = new ImageButton(buttonUp2, buttonDown2);
		button2.setPosition(100, 200);
		
		button2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				submitBox.clear();
				submitBox.setNumActors(0);
				tileBox.clear();
				tOriginal.clear();
				tCopy.clear();
				createTiles();
				
				Collections.shuffle(tOriginal);

				tCopy = new ArrayList<Tile>(); // Copia del arrayList
				for (int i = 0; i < MAX_TILES; ++i) {
					tCopy.add(new Tile(tOriginal.get(i)));;
				}
				addTiles();
				addListeners();			
			}
		});
		
		stage.addActor(button2);
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
		
		if (missingwords.selectedLanguage.equals(Language.german)) {
			adaptedWord = adaptWord(arrayWord);
		}
		else {
			for (int i = 0; i < arrayWord.length; ++i)
				adaptedWord.add(arrayWord[i]);		
		}
		
		System.out.print("\n");
		
		/* Creación de fichas */
		
		for (int i = 0; i < adaptedWord.size(); ++i) {
			System.out.println(adaptedWord.get(i));
			tOriginal.add(new Tile(adaptedWord.get(i), scores.getScores().get(adaptedWord.get(i))));
		}
		
		for (int i = adaptedWord.size(); i < MAX_TILES; ++i) {
			randomLetter = scores.randomKey();
			tOriginal.add(new Tile(randomLetter, scores.getScores().get(randomLetter)));
		}
	}
	
	public void shuffleTiles() {
		Collections.shuffle(tOriginal);

		tCopy = new ArrayList<Tile>(); // Copia del arrayList
		for (int i = 0; i < MAX_TILES; ++i) {
			tCopy.add(new Tile(tOriginal.get(i)));;
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
		for (int i = 0; i < MAX_TILES; ++i) {
			tOriginal.get(i).addListener(new TileListenerTable(submitBox, tOriginal.get(i), tCopy.get(i)));
			tCopy.get(i).addListener(new TileListenerSubmit(submitBox, tOriginal.get(i), tCopy.get(i)));
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
