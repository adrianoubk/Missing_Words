package com.me.missingwords;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.missingwords.data.StatsData;
import com.me.missingwords.screens.*;
import com.me.missingwords.utils.Dictionary;
import com.me.missingwords.utils.Scores;
import com.me.missingwords.utils.Vocabulary;

/**
 * 
 * Clase principal del juego, con ella conectamos las pantallas.
 * 
 */

/*  La clase Game permite a nuestro juego dividirse en distintas pantallas como la pantalla de 
 * juego, menu, carga, etc. Game implementa ApplicationListener, una interfaz que se encarga de 
 * recibir los eventos con los que entra en contacto nuestra aplicacion. Estos son cuando la 
 * aplicacion es creada, pausada, resumida (despues de un estado de pausa),
 * renderizada (dibujando/refrescando la pantalla) o destruida.
 */
public class MissingWords extends Game {
	
	/* Ancho y alto del viewport del juego, que se ajusta a la pantalla */
	
	public static final int VIEWPORT_WIDTH = 800;
	public static final int VIEWPORT_HEIGHT = 480;
	
	/* Pantallas del juego */
	
	public GameScreen GameScreen;
	public BaseScreen LanguageSelectionScreen;
	public BaseScreen MenuScreen;
	public BaseScreen CategorySelectionScreen;
	public MiniGameScreen MiniGameScreen;
	public VictoryScreen VictoryScreen;
	public LoadingScreen LoadingScreen;
	public StatsScreen StatsScreen;
	
	/* La clase SpriteBatch nos permite dibujar las texturas de nuestro juego. Agrupa 
	 * sprites(imagenes) para enviarlas al procesador grafico y asi dibujarlas a la vez.
	 * Puesto que dibujarlas de una en una es mas costoso. Se recomiendo tener un único
	 * SpriteBatch para el juego.
	 */
	private SpriteBatch myBatch; 
	
	/* Gestor de recursos del juego */
	public static AssetManager myManager;
	
	/* Categorías del juego */
	public enum Category {days, months};
	
	/* Idiomas que soporta el juego */
	public enum Language {english, german};
	
	/* Idioma con el que se jugará */
	public Language selectedLanguage; 
	
	/* Categoría con la que se jugará */
	public Category selectedCategory;
	
	/* Booleano que indica si se ha llegado a la victoria */
	private boolean victory;
	
	/* Booleano que indica si se está jugando en el modo SINGLEPLAYER */
	private boolean singlePlayer;
	
	/* Vocabulario que se usa en el juego */
	private Vocabulary vocabulary;
	
	/* Diccionario del juego. Útil para las pista de traducción */
	private Dictionary dictionary;
	
	/* Puntuaciones que tienen las letras del juego */
	private Scores scores;
	
	/* Umbrales máximo y mínimo que marcan la puntuación a conseguir para obtener más tiradas */
	private int max, min;
	
	/* Datos de las estadísticas */
	private StatsData statsData;
	
	/* create(): Creamos los objetos necesarios para construir la aplicación */
	@Override
	public void create() {
		/* Creamos el SpriteBatch y el Gestor de recursos */
		myBatch = new SpriteBatch();
		myManager = new AssetManager();
		
		/* Cargamos los datos de las estadísticas */
		statsData = new StatsData();
		
		/* 
		 * Con la función load() añadimos los recursos a la cola de carga, pero aún no se
		 * han cargado. 
		 */
		myManager.load("loadingBackground.png", Texture.class);
		myManager.load("background.png", Texture.class);
		myManager.load("barLoading.png", Texture.class);
		myManager.load("barBackground.png", Texture.class);
		myManager.load("tileBox.png", Texture.class);
		myManager.load("a.png", Texture.class);
		myManager.load("b.png", Texture.class);
		myManager.load("c.png", Texture.class);
		myManager.load("d.png", Texture.class);
		myManager.load("e.png", Texture.class);
		myManager.load("f.png", Texture.class);
		myManager.load("g.png", Texture.class);
		myManager.load("h.png", Texture.class);
		myManager.load("i.png", Texture.class);
		myManager.load("j.png", Texture.class);
		myManager.load("k.png", Texture.class);
		myManager.load("l.png", Texture.class);
		myManager.load("m.png", Texture.class);
		myManager.load("n.png", Texture.class);
		myManager.load("o.png", Texture.class);
		myManager.load("p.png", Texture.class);
		myManager.load("q.png", Texture.class);
		myManager.load("r.png", Texture.class);
		myManager.load("s.png", Texture.class);
		myManager.load("t.png", Texture.class);
		myManager.load("u.png", Texture.class);
		myManager.load("v.png", Texture.class);
		myManager.load("w.png", Texture.class);
		myManager.load("x.png", Texture.class);
		myManager.load("y.png", Texture.class);
		myManager.load("z.png", Texture.class);
		myManager.load("ae.png", Texture.class);
		myManager.load("oe.png", Texture.class);
		myManager.load("ue.png", Texture.class);
		myManager.load("upButtonLarge.png", Texture.class);
		myManager.load("grey_sliderHorizontal.png", Texture.class);
		myManager.load("submitButtonUp.png", Texture.class);
		myManager.load("submitButtonDown.png", Texture.class);
		myManager.load("letterButtonUp.png", Texture.class);
		myManager.load("letterButtonDown.png", Texture.class);
		myManager.load("Germany-flag.png", Texture.class);
		myManager.load("United-kingdom-flag.png", Texture.class);
		myManager.load("downButton.png", Texture.class);
		myManager.load("translationButtonDown.png", Texture.class);
		myManager.load("translationButtonUp.png", Texture.class);
		myManager.load("lengthButtonDown.png", Texture.class);
		myManager.load("lengthButtonUp.png", Texture.class);
		myManager.load("letterButton_Used.png", Texture.class);
		myManager.load("lengthButton_Used.png", Texture.class);
		myManager.load("translationButton_Used.png", Texture.class);
		myManager.load("none.png", Texture.class);
		myManager.load("holeGrass.png", Texture.class);
		myManager.load("rollButtonDown.png", Texture.class);
		myManager.load("rollButtonUp.png", Texture.class);
		myManager.load("1.png", Texture.class);
		myManager.load("2.png", Texture.class);
		myManager.load("3.png", Texture.class);
		myManager.load("4.png", Texture.class);
		myManager.load("5.png", Texture.class);
		myManager.load("6.png", Texture.class);
		myManager.load("player.png", Texture.class);
		myManager.load("transparentTile.png", Texture.class);
		myManager.load("npc.png", Texture.class);
		myManager.load("split.png", Texture.class);
		myManager.load("squareBlue.png", Texture.class);
		myManager.load("verticalScroll.png", Texture.class);
		myManager.load("selection.png", Texture.class);
		myManager.load("upButton.png", Texture.class);
		myManager.load("continueButtonUp.png", Texture.class);
		myManager.load("continueButtonDown.png", Texture.class);
		myManager.load("bothPlayers.png", Texture.class);
		myManager.load("backButtonUp.png", Texture.class);
		myManager.load("backButtonDown.png", Texture.class);
		myManager.load("pauseButtonUp.png", Texture.class);
		myManager.load("pauseButtonDown.png", Texture.class);
		myManager.load("backgroundDialog.png", Texture.class);
		
		victory = false; 
		
		LoadingScreen = new LoadingScreen(this); // Creamos la pantalla de carga
		
		setScreen(LoadingScreen); // Establecemos la pantalla de carga para arrancar la app
	}
	
	/* dispose(): liberamos la memoria de los recursos que hemos usado */	
	@Override
	public void dispose() {
		super.dispose();
		myBatch.dispose();
		myManager.dispose();
	}
	
	/* createMenuScreens(): crea las pantallas de los menús de selección */
	public void createMenuScreens() {
		CategorySelectionScreen = new CategorySelectionScreen(this);
		MenuScreen = new MenuScreen(this);
		StatsScreen = new StatsScreen(this);
	}
	
	/* createGameScreens(): crea las pantallas de juego */
	public void createGameScreens() {
		GameScreen = new GameScreen(this);
		MiniGameScreen = new MiniGameScreen(this);
		VictoryScreen = new VictoryScreen(this);
	}
	
	/* createUtils(): inicializa el vocabulario, diccionario y puntuación */
	public void createUtils() {
		try {
			vocabulary = new Vocabulary(selectedLanguage, selectedCategory);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			scores = new Scores(selectedLanguage);
		} catch (IOException e1) {
		e1.printStackTrace();
		}
		
		try {
			dictionary = new Dictionary(selectedLanguage);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			setLimits(selectedLanguage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * setLimits(): esstablece los umbrales para las tiradas, los obtiene desde un fichero 
	 * por si se desean cambiar
	 */
	private void setLimits(Language language) throws IOException {	
		FileHandle file = null;
		
		switch(language) { // Leemos de un fichero o otro según el idioma
			case german: file = Gdx.files.internal("utils/score-limits-german.txt"); break;
			case english: file = Gdx.files.internal("utils/score-limits-english.txt"); break;
		}
		
		BufferedReader br = new BufferedReader(file.reader());
		String line;
		String[] limits;
		while ((line = br.readLine()) != null) { // leemos linea hasta que sea null
			limits = line.split(" ");
			
			if (limits[0].equals("max")) // establecemos el máximo
				max = Integer.parseInt(limits[1]);
			
			if (limits[0].equals("min")) // establecemos el mínimo
				min = Integer.parseInt(limits[1]);
		}
		
		br.close(); // cerramos el buffer
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public SpriteBatch getSB() {
		return myBatch;
	}

	public GameScreen getGameScreen() {
		return GameScreen;
	}

	public MiniGameScreen getMiniGameScreen() {
		return MiniGameScreen;
	}

	public StatsScreen getStatsScreen() {
		return StatsScreen;
	}

	public Vocabulary getVocabulary() {
		return vocabulary;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public Scores getScores() {
		return scores;
	}

	public boolean victory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public void setSinglePlayer(boolean singlePlayer) {
		this.singlePlayer = singlePlayer;
	}

	public StatsData getStatsData() {
		return statsData;
	}
}

