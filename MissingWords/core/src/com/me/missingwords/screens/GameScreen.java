package com.me.missingwords.screens;

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
import com.me.missingwords.buttons.PauseButton;
import com.me.missingwords.buttons.SubmitButton;
import com.me.missingwords.listeners.*;


/** 
 * 
 * Muestra la pantalla de juego.
 *
 */

public class GameScreen extends BaseScreen {
	public final int MAX_TILES = 16;

	private ArrayList<Tile> originalTiles, copyTiles;
	private Turn turn;
	private TimeBar timeBar;
	private Background background;
	private Slider slider;
	private SubmitBox submitBox;
	private TileBox tileBox;
	private ArrayList<Tile> adaptedWordNPC;
	private NPCPlayer npc;
	private HumanPlayer human;
	private TurnControl turnControl;
	private ClueButton letterClue, translationClue, lengthClue;
	private PauseButton pauseButton;
	private SubmitButton submit;
	private LengthClueBox lengthBox;
	private int totalWords;
	private String winner, randomWord;
	private InfoRoll info;
	private PauseDialog pauseDialog;
	private WordScore wordScore;

	public GameScreen(MissingWords missingWords) {
		super(missingWords);
		
		System.out.println("Juego nuevo");
		
		totalWords = 0; // Contadore de palabras totales, npc y player.
		
		/* Creamos el fondo de pantalla */
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		/* Creamos el bloque de la pista de longitud */
		lengthBox = new LengthClueBox();
		stage.addActor(lengthBox);
		
		/* Creamos el bloque de formación de palabras */
		submitBox = new SubmitBox();
		stage.addActor(submitBox);
		
		/* Creamos el jugador */
		human = new HumanPlayer("Player", missingWords);
		stage.addActor(human);
		
		/* Creamos el npc si no es SINGLEPLAYER */
		if (!missingWords.isSinglePlayer()) { 
			npc = new NPCPlayer("NPC", missingWords);
			stage.addActor(npc);
		}
		
		/* Creamos el slider donde descansan las letras */
		slider = new Slider(MissingWords.myManager.get("grey_sliderHorizontal.png", Texture.class), missingWords);
		stage.addActor(slider);
		
		/* Creamos la barra de turno */
		turn = new Turn(0, missingWords);
		stage.addActor(turn);
		
		/* Creamos la barra de tiempo */
		timeBar = new TimeBar(missingWords);
		stage.addActor(timeBar);
		
		/* Creamos los botones de pistas */
		createClueButtons();
		
		/* Creamos el botón de pausa */
		pauseButton = new PauseButton();
		pauseButton.addListener(new PauseButtonListener(missingWords));
		stage.addActor(pauseButton);
		
		/* Creamos el bloque que contendrá las letras */
		tileBox = new TileBox(new Table());
		stage.addActor(tileBox);
		
		/* Creamos el objeto que controla los turnos */
		turnControl = new TurnControl("none", missingWords);
		stage.addActor(turnControl);
		
		/* Creamos el bloque con la información de las tiradas */
		info = new InfoRoll(missingWords);
		stage.addActor(info);
		
		/* Creamos el dialogo de pausa */
		pauseDialog = new PauseDialog(missingWords);
		
		wordScore = new WordScore(missingWords);
		stage.addActor(wordScore);
	}

	@Override
	public void render(float delta) {	
		super.render(delta);
		
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
	}
	
	/* createClueButtons(): crea los botones de pistas y los añade al escenario */
	private void createClueButtons() {	
		translationClue = new ClueButton(
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("translationButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("translationButtonDown.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("translationButton_Used.png", Texture.class))));
						
		translationClue.setPosition(580, 300);
		translationClue.addListener(new TranslationClueListener(missingWords, translationClue));
		stage.addActor(translationClue);
		
		letterClue = new ClueButton(
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("letterButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("letterButtonDown.png", Texture.class))),
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("letterButton_Used.png", Texture.class))));
		
		letterClue.setPosition(650, 300);
		letterClue.addListener(new LetterClueListener(missingWords, letterClue));
		stage.addActor(letterClue);
		
		lengthClue = new ClueButton(
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("lengthButtonUp.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("lengthButtonDown.png", Texture.class))), 
				new TextureRegionDrawable(new TextureRegion(
						MissingWords.myManager.get("lengthButton_Used.png", Texture.class))));
		
		lengthClue.setPosition(720, 300);
		lengthClue.addListener(new LengthClueListener(missingWords, lengthClue));
		stage.addActor(lengthClue);
		
		submit = new SubmitButton();
		submit.addListener(new InputButtonListener(missingWords));
		stage.addActor(submit);
	}
	
	/* newTurn(): crea un nuevo turno para jugar */
	private void newTurn() {
		turn.nextTurn(); // Turno nuevo	
		turnControl.prepareTurn(); // Prepara el turno para el jugador
		turnControl.initialiseTurn(); // Inicia el turno
	}
	
	/* newTiles(): crea nuevas fichas y las añade */
	public void newTiles() {
		createTiles();
		shuffleTiles();
		addTiles();
		addListeners();
	}
	
	/* createTiles(): crea los objetos de tipo Tile */
	private void createTiles() {
		String[] arrayWord;
		ArrayList<String> adaptedWord = new ArrayList<String>();
		String randomLetter;
		
		originalTiles = new ArrayList<Tile>(MAX_TILES); // Creo un array con el máximo de fichas
		
		randomWord = missingWords.getVocabulary().randomKey(); // palabra al azar del vocabulario
		arrayWord = randomWord.split("(?!^)");
		
		arrayWord[0] = arrayWord[0].toLowerCase(); // Primera letra a minúscula	
		
		/* Muestro la palabra, SOLO PRUEBAS */
		for (int i = 0; i < arrayWord.length; ++i) {
			System.out.print(arrayWord[i]);
		}
		
		/* Si el idioma seleccionado es el alemán, adapto la palabra (por las vocales) */
		if (missingWords.selectedLanguage.equals(Language.german)) {
			adaptedWord = adaptWord(arrayWord);
		}
		else { // Si no, la copio
			for (int i = 0; i < arrayWord.length; ++i)
				adaptedWord.add(arrayWord[i]);		
		}
		
		System.out.println("\n");
		
		System.out.print(adaptedWord);
		
		/* Creamos las fichas */
		for (int i = 0; i < adaptedWord.size(); ++i) {
			originalTiles.add(new Tile(
					adaptedWord.get(i), missingWords.getScores().getScores().get(adaptedWord.get(i))));
		}
		
		/* Transformo la palabra en un ArrayList para el NPC, por su fácil manejo */
		adaptedWordNPC = new ArrayList<Tile>(originalTiles); 
		
		/* Si quedan letras para completar la caja, las obtengo aleatoriamente y las añado */
		for (int i = adaptedWord.size(); i < MAX_TILES; ++i) {
			randomLetter = missingWords.getScores().randomKey();
			originalTiles.add(new Tile(randomLetter, missingWords.getScores().getScores().get(randomLetter)));
		}
	}
	
	/* adaptWord(): Adapta la palabra si el idioma es el alemán, debido a las vocales */
	private ArrayList<String> adaptWord(String[] arrayWord) {
		int i = 0;
		ArrayList<String> newArray = new ArrayList<String>();
		
		/* 
		 * Recorremos la palabra e intercambiamos las ocurrencias de ae, oe y ue por sus vocales
		 * con diéresis
		 */
		/*
		while (i < arrayWord.length) {
			if ((arrayWord[i].equals("a") || arrayWord[i].equals("o") || arrayWord[i].equals("u")) 
					&& (i + 1) < arrayWord.length && arrayWord[i + 1].equals("e")) {
					switch (arrayWord[i]) {
						case "a": newArray.add("ae"); break;
						case "o": newArray.add("oe"); break;
						case "u": newArray.add("ue"); break;
					}
					
					i += 2; // Si es una ocurrencia, saltamos 2 posiciones. La vocal y la 'e'
			}
			else {
				newArray.add(arrayWord[i]);
				++i;
			}
		}
		*/
		
		while (i < arrayWord.length) {
			if (arrayWord[i].equals("ä"))
				newArray.add("ae");
			else if (arrayWord[i].equals("ö"))
				newArray.add("oe");
			else if (arrayWord[i].equals("ü"))
				newArray.add("ue");
			else
				newArray.add(arrayWord[i]);
			
			++i;
		}
		
		
		/*while (i < arrayWord.length) {
			newArray.add(arrayWord[i]);
			++i;
		}*/
		
		return newArray;
	}
	
	/* shuffleTiles(): desordena el array de letras */
	private void shuffleTiles() {
		Collections.shuffle(originalTiles);
		
		/* 
		 * Realizamos una copia del array de tiles original, para usarlo a la hora de 
		 * seleccionar letras, no bajar las originales, perdiendo así su posición en la 
		 * tabla (dependencia de Table LibGDX).
		 */
		copyTiles = new ArrayList<Tile>();
		for (int i = 0; i < MAX_TILES; ++i) {
			copyTiles.add(new Tile(originalTiles.get(i)));;
		}
	}
	
	/* addTiles(): añadimos las tiles al bloque de letras */
	private void addTiles() {
		int i = 0;
		
		Iterator<Tile> iterator = originalTiles.iterator();
		
		while (iterator.hasNext()) { 
			if (i == 4 || i == 8 || i == 12) { // Solo 4 tiles por fila
				tileBox.getTileTable().row();
			}
					
			tileBox.getTileTable().add(iterator.next());
			++i;
		}
	}
	
	/* addListeners(): añade los listeners a las tiles para poder tocarlas */
	private void addListeners() {	
		for (int i = 0; i < MAX_TILES; ++i) {
			originalTiles.get(i).addListener(new TileListenerTable(originalTiles.get(i), copyTiles.get(i), missingWords));
			copyTiles.get(i).addListener(new TileListenerSubmit(originalTiles.get(i), copyTiles.get(i), missingWords));
		}
	}
	
	/* increaseTotalWords(): Incrementa el total de palabras jugadas */ 
	public void increaseTotalWords() {
		++totalWords;
	}
	
	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {		
		/* Muestra el dialogo con el botón de resume */
		missingWords.getGameScreen().getPauseDialog().show(missingWords.getGameScreen().getStage());
		
		/* Oculta las fichas para evitar trampas */
		missingWords.getGameScreen().getTileBox().getTileTable().setVisible(false);
		
		/* Para el tiempo */
		missingWords.getGameScreen().getTimeBar().stop();
		
		/* Si no es singleplayer y es el turno de la npc, reanudamos su temporizador */
		if (!missingWords.isSinglePlayer()) 
			if (missingWords.getGameScreen().getNpc().isMyTurn())
				missingWords.getGameScreen().getNpc().getNpcTimer().stop();
	}

	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
	
	/* -------------- Getters and Setters -------------- */

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

	public String getRandomWord() {
		return randomWord;
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

	public PauseButton getPauseButton() {
		return pauseButton;
	}

	public SubmitButton getSubmit() {
		return submit;
	}

	public LengthClueBox getLengthBox() {
		return lengthBox;
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

	public PauseDialog getPauseDialog() {
		return pauseDialog;
	}

	public WordScore getWordScore() {
		return wordScore;
	}
}
