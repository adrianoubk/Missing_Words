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
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.actors.Tile;
import com.me.missingwords.actors.TimeBar;
import com.me.missingwords.actors.Turn;
import com.me.missingwords.utils.Scores;
import com.me.missingwords.utils.Vocabulary;

public class GameScreen extends BaseScreen {
	
	public static final int MAX_TILES = 16;
	
	private float cont = 60, timeCounter = 1;
	private float counter = 1;
	private HorizontalGroup submitGroup;
	private Table tileBox;
	private ArrayList<Tile> tOriginal, tCopy;
	private Container<Table> container;
	private Turn turn;
	private TimeBar barPrueba;
	private Background background;
	private TextureRegionDrawable tDrawable;
	private Vocabulary vocab;
	private Scores scores;
	//private int listener;
	
	public GameScreen(MissingWords missingwords) {
		super(missingwords);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		submitGroup.setPosition((800 - submitGroup.getMinWidth()) / 2, 0);
		stage.act();
		stage.draw();
		//Table.drawDebug(stage);
		
		timeCounter += delta;
		if(timeCounter >= 1 && cont >= 0){
			barPrueba.setProgress(counter);
			counter -= (float) 1 / 60;
			timeCounter = 0;
			--cont;
			}
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
		
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		addTiles();
		addListeners();
		
		
		/*a = new Tile("a", 3);
		b = new Tile("a", 3);
		c = new Tile("a", 3);
		d = new Tile("a", 3);*/
		
		submitGroup = new HorizontalGroup();

		/*submitGroup.addActor(a);
		submitGroup.addActor(b);
		submitGroup.addActor(c);
		submitGroup.addActor(d);*/
		//submitGroup.setPosition((800 - submitGroup.getMinWidth()) / 2, 0);
		submitGroup.align(Align.bottom);
		stage.addActor(submitGroup);
		
		turn = new Turn(1);
		stage.addActor(turn);
		
		/*tileBox = new Table();	
		tileBox.setFillParent(true);
		tileBox.add(a);
		tileBox.add(b);
		tileBox.add(c);
		tileBox.add(d);
		tileBox.row();
		tileBox.add(e);
		tileBox.add(f);
		tileBox.add(g);
		tileBox.add(h);
		tileBox.row();
		tileBox.add(i);
		tileBox.add(j);
		tileBox.add(k);
		tileBox.add(l);
		tileBox.row();
		tileBox.add(m);
		tileBox.add(n);
		tileBox.add(o);
		tileBox.add(p);
		
		tileBox.top().left();
		tileBox.padTop(2);
		
		tDrawable = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("cuadroFichaNuevo.png", Texture.class)));
		container = new Container<Table>(tileBox);
		container.setBounds(254, 92, 288, 288);
		container.bottom();
		container.setBackground(tDrawable);
		stage.addActor(container);*/
		
		barPrueba = new TimeBar();
		stage.addActor(barPrueba);
		
		/*a2 = new Tile(a);
		//System.out.println(submitGroup.getZIndex());*/
		
		/*tOriginal.get(0).addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("hola amigos");		
				tOriginal.get(0).setVisible(false);
				submitGroup.addActor(tCopy.get(0));
			}
		});
		
		tCopy.get(0).addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("hola amigos2");
				tOriginal.get(0).setVisible(true);
				submitGroup.removeActor(tCopy.get(0));
			}
		});*/	
	}
	
	private void addTiles() {
		int i = 0;
		
		tileBox = new Table();
		
		Iterator<Tile> iterator = tOriginal.iterator();
		
		while (iterator.hasNext()) {
			if (i == 4 || i == 8 || i == 12) {
				tileBox.row();
			}
					
			tileBox.add(iterator.next());
			++i;
		}
		
		tileBox.setFillParent(true);
		tileBox.top().left();
		tileBox.padTop(2);
		
		tDrawable = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("cuadroFichaNuevo.png", Texture.class)));
		container = new Container<Table>(tileBox);
		container.setBounds(254, 92, 288, 288);
		container.bottom();
		container.setBackground(tDrawable);
		stage.addActor(container);
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
					submitGroup.addActor(tCopy.get(index));
					System.out.println("index: " + index);
			
				}
			});
			
			tCopy.get(i).addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					tOriginal.get(index).setVisible(true);
					submitGroup.removeActor(tCopy.get(index));
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
