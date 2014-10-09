package com.me.missingwords.screens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.MissingWords.Language;
import com.me.missingwords.actors.*;
import com.me.missingwords.buttons.ClueButton;
import com.me.missingwords.buttons.SubmitButton;
import com.me.missingwords.listeners.*;
import com.me.missingwords.utils.*;


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
	private ArrayList<Tile> adaptedWordNPC;
	private NPCPlayer npc;
	private HumanPlayer human;
	private TurnControl turnControl;
	private ClueButton letterClue, translationClue, lengthClue;
	private SubmitButton submit;
	private LengthClueBox lengthBox;
	private Dictionary dic;
	private ArrayList<String> playedWords;
	private int totalWords;
	private String winner;

	public GameScreen(MissingWords missingWords) {
		super(missingWords);
	}

	@Override
	public void render(float delta) {
		
		super.render(delta);
		
		/*if (end) { // Comprueba el final del juego
			System.out.println("FIN DEL JUEGO");
			missingWords.setGameRunning(false);
			end = false;
			turn.setNumTurn(0); 
			tileBox.clean();
			timeBar.reset();
			missingWords.setScreen(missingWords.MenuScreen); 
		}
		else */
		
		if (missingWords.isSinglePlayer()) { // Modo SINGLEPLAYER
			if (!human.isMyTurn()) { // Si ha terminado el turno del jugador
				human.setMyTurn(true);
				newTurn();
			}
		}
		else { // Modo PLAYER VS CPU
			if  (!human.isMyTurn()  && !npc.isMyTurn()) { // Si han terminado sus turnos
				human.setMyTurn(true);
				newTurn();
			}
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
		
		if (!missingWords.isGameRunning()) {
		
			System.out.println("Juego nuevo");
			
			totalWords = 0;
			
			playedWords = new ArrayList<>();
			
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
			
			try {
				dic = new Dictionary(missingWords.selectedLanguage);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			background = new Background(MissingWords.myManager.get("bg_grasslands.png", Texture.class));
			stage.addActor(background);
			
			lengthBox = new LengthClueBox();
			stage.addActor(lengthBox);
			
			submitBox = new SubmitBox();
			stage.addActor(submitBox);
			
			human = new HumanPlayer("Adri", missingWords);
			stage.addActor(human);
			
			if (!missingWords.isSinglePlayer()) { // Si no es singleplayer, la cpu juega
				npc = new NPCPlayer("NPC", missingWords);
				stage.addActor(npc);
			}
			
			slider = new Slider(MissingWords.myManager.get("grey_sliderHorizontal.png", Texture.class));
			slider.getGameData(missingWords);
			stage.addActor(slider);
			
			turn = new Turn(0);
			stage.addActor(turn);
			
			timeBar = new TimeBar(missingWords);
			stage.addActor(timeBar);
			
			createButtons();
			
			tileBox = new TileBox(new Table());
			stage.addActor(tileBox);
			
			turnControl = new TurnControl("none", missingWords);
			stage.addActor(turnControl);
		}
	}
	
	private void createButtons() {
		
		translationClue = new ClueButton(
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("translationButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("translationButtonDown.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("translationButton_Used.png", Texture.class))));
						
		translationClue.setPosition(272, 5);
		translationClue.addListener(new TranslationClueListener(this, translationClue));
		stage.addActor(translationClue);
		
		letterClue = new ClueButton(
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("letterButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("letterButtonDown.png", Texture.class))),
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("letterButton_Used.png", Texture.class))));
		
		letterClue.setPosition(341, 5);
		letterClue.addListener(new LetterClueListener(this, letterClue));
		stage.addActor(letterClue);
		
		lengthClue = new ClueButton(
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("lengthButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("lengthButtonDown.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("lengthButton_Used.png", Texture.class))));
		
		lengthClue.setPosition(410, 5);
		lengthClue.addListener(new LengthClueListener(this, lengthClue));
		stage.addActor(lengthClue);
		
		submit = new SubmitButton();
		submit.addListener(new InputButtonListener(this));
		stage.addActor(submit);
	}
	

	private void newTurn() {
		
		turn.nextTurn(); // Turno nuevo
		
		//if (turn.getNumTurn() == 2) // Si es el turno 3, acaba el juego
			//end = true;
		//else { // Si no, comienza un nuevo turno
			turnControl.prepareTurn(); // Prepara el turno para el jugador
			turnControl.initialiseTurn(); // Inicia el turno
		//}
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
	
	public void addPlayedWord(String word) {
		if (!playedWords.contains(word))
			playedWords.add(word);
	}
	
	public void increaseTotalWords() {
		++totalWords;
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

	public TurnControl getTurnControl() {
		return turnControl;
	}

	public ClueButton getLetterClue() {
		return letterClue;
	}

	public ClueButton getTranslationClue() {
		return translationClue;
	}

	public ClueButton getLengthClue() {
		return lengthClue;
	}

	public SubmitButton getSubmit() {
		return submit;
	}

	public LengthClueBox getLengthBox() {
		return lengthBox;
	}

	public Dictionary getDic() {
		return dic;
	}

	public int getTotalWords() {
		return totalWords;
	}

	public void setTotalWords(int totalWords) {
		this.totalWords = totalWords;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public Turn getTurn() {
		return turn;
	}

	public ArrayList<String> getPlayedWords() {
		return playedWords;
	}

	@Override
	public void hide() {
		System.out.println("Ocultando");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		System.out.println("Resumiendo");
	}

	@Override
	public void dispose() {
		playedWords.clear();
		stage.dispose();
	}
}
