package com.me.missingwords.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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

	private ArrayList<Tile> originalTiles, copyTiles;
	private Turn turn;
	private TimeBar timeBar;
	private Background background;
	private Vocabulary vocab;
	private Scores scores;
	private Slider slider;
	private SubmitBox submitBox;
	private TileBox tileBox;
	private ImageButton button;
	private TextureRegionDrawable buttonUp, buttonDown;
	private ArrayList<Tile> adaptedWordNPC;
	private NPCPlayer npc;
	private HumanPlayer human;
	private WhoPlays who;
	private boolean end;

	public GameScreen(MissingWords missingWords) {
		super(missingWords);
	}

	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		if (end) {
			System.out.println("FIN DEL JUEGO");
			turn.setNumTurn(0);
			tileBox.clean();
			timeBar.reset();
			//end = false;
			missingWords.setScreen(missingWords.MenuScreen); 
		}
		else 
			if  (!human.isMyTurn()  && !npc.isMyTurn()) {
				newTurn();
			}
			
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
		
		end = false;
		
		System.out.println("Juego nuevo");
		
		try {
			vocab = new Vocabulary(missingWords.selectedLanguage, missingWords.selectedCategory);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			scores = new Scores(missingWords.selectedLanguage);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
		stage.addActor(background);
		
		submitBox = new SubmitBox();
		stage.addActor(submitBox);
		
		human = new HumanPlayer("Adri");
		human.getGameData(missingWords);
		stage.addActor(human);
		
		npc = new NPCPlayer("NPC");
		npc.getGameData(missingWords);
		stage.addActor(npc);
		
		slider = new Slider(MissingWords.myManager.get("grey_sliderHorizontal.png", Texture.class));
		slider.getGameData(missingWords);
		stage.addActor(slider);
		
		turn = new Turn(0);
		stage.addActor(turn);
		
		timeBar = new TimeBar();
		timeBar.getGameData(missingWords);
		stage.addActor(timeBar);
		
		buttonUp = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button13.png", Texture.class)));
		buttonDown = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("blue_button14.png", Texture.class)));
		
		button = new ImageButton(buttonUp, buttonDown);
		button.setPosition((MissingWords.VIEWPORT_WIDTH - button.getMinWidth()) / 2, 5);
		
		button.addListener(new InputButtonListener(missingWords));
		
		stage.addActor(button);
		
		tileBox = new TileBox(new Table());
		stage.addActor(tileBox);
		
		who = new WhoPlays("none");
		who.getGameData(missingWords);
		stage.addActor(who);
	}
	
	private void newTurn() {
		
		turn.nextTurn();
		
		if (turn.getNumTurn() == 3)
			end = true;
		else {
			tileBox.clean();
		
			timeBar.reset();
		
			who.setName("Your Turn");
			who.performAction();
		
			npc.setTurnFinished(true);

			human.setMyTurn(true);
			human.touchScreen(Touchable.enabled);
			npc.setMyTurn(false);
		}
	}
	
	public void newTiles() {
		createTiles();
		shuffleTiles();
		addTiles();
		addListeners();
	}
	
	private void createTiles() {
		String randomWord;
		String[] arrayWord;
		ArrayList<String> adaptedWord = new ArrayList<String>();
		String randomLetter;
		
		originalTiles = new ArrayList<Tile>(MAX_TILES);
		
		randomWord = vocab.randomKey();
		arrayWord = randomWord.split("(?!^)");
		
		for (int i = 0; i < arrayWord.length; ++i) {
			System.out.print(arrayWord[i]);
		}
		
		if (missingWords.selectedLanguage.equals(Language.german)) {
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
			originalTiles.add(new Tile(adaptedWord.get(i), scores.getScores().get(adaptedWord.get(i))));
		}
		
		adaptedWordNPC = new ArrayList<Tile>(originalTiles);
		
		for (int i = adaptedWord.size(); i < MAX_TILES; ++i) {
			randomLetter = scores.randomKey();
			originalTiles.add(new Tile(randomLetter, scores.getScores().get(randomLetter)));
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
	
	private void shuffleTiles() {
		Collections.shuffle(originalTiles);

		copyTiles = new ArrayList<Tile>(); // Copia del arrayList
		for (int i = 0; i < MAX_TILES; ++i) {
			copyTiles.add(new Tile(originalTiles.get(i)));;
		}
	}
	
	private void addTiles() {
		int i = 0;
		
		Iterator<Tile> iterator = originalTiles.iterator();
		
		while (iterator.hasNext()) {
			if (i == 4 || i == 8 || i == 12) {
				tileBox.getTileTable().row();
			}
					
			tileBox.getTileTable().add(iterator.next());
			++i;
		}
	}

	private void addListeners() {	
		for (int i = 0; i < MAX_TILES; ++i) {
			originalTiles.get(i).addListener(new TileListenerTable(submitBox, originalTiles.get(i), copyTiles.get(i)));
			copyTiles.get(i).addListener(new TileListenerSubmit(submitBox, originalTiles.get(i), copyTiles.get(i)));
		}
	}

	public HumanPlayer getHuman() {
		return human;
	}

	public SubmitBox getSubmitBox() {
		return submitBox;
	}

	public ArrayList<Tile> getOriginalTiles() {
		return originalTiles;
	}

	public ArrayList<Tile> getCopyTiles() {
		return copyTiles;
	}

	public ArrayList<Tile> getAdaptedWordNPC() {
		return adaptedWordNPC;
	}

	public Vocabulary getVocab() {
		return vocab;
	}

	public NPCPlayer getNpc() {
		return npc;
	}

	public TimeBar getTimeBar() {
		return timeBar;
	}

	public TileBox getTileBox() {
		return tileBox;
	}

	public ImageButton getButton() {
		return button;
	}

	public WhoPlays getWho() {
		return who;
	}

	@Override
	public void hide() {
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
		System.out.println("BORRANDO...");
		stage.dispose();
	}
}
