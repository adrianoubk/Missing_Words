package com.me.missingwords;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.missingwords.screens.*;
import com.me.missingwords.utils.Dictionary;
import com.me.missingwords.utils.Scores;
import com.me.missingwords.utils.Vocabulary;

/**
 * 
 * Clase MissingWords (Clase principal del juego)
 * 
 * La clase Game permite a nuestro juego dividirse en distintas pantallas como la pantalla de 
 * juego, menu, carga, etc. Game implementa ApplicationListener, una interfaz que se encarga de 
 * recibir los eventos con los que entra en contacto nuestra aplicacion. Estos son cuando la 
 * aplicacion es creada, pausada, resumida (despues de un estado de pausa),
 * renderizada (dibujando/refrescando la pantalla) o destruida.
 * 
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
	
	private boolean victory;
	
	private boolean singlePlayer;
	
	private Vocabulary vocabulary;
	
	private Dictionary dictionary;
	
	private Scores scores;
	
	/** En el método create() creamos los objetos necesarios para construir la aplicación */
	
	@Override
	public void create() {
		
		/* Creamos el SpriteBatch y el Gestor de recursos */
		
		myBatch = new SpriteBatch();
		myManager = new AssetManager();
		
		/* Con la función load() añadimos los recursos a la cola de carga, pero aún no
		 * se cargan hasta que no se llame a finishLoading(). 
		 */
		
		myManager.load("barLoading.png", Texture.class);
		myManager.load("barBackground.png", Texture.class);
		myManager.load("cuadroFichaNuevo.png", Texture.class);
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
		myManager.load("TurnBar.png", Texture.class);
		myManager.load("bg_grasslands.png", Texture.class);
		myManager.load("blue_button04.png", Texture.class);
		myManager.load("grey_sliderHorizontal.png", Texture.class);
		myManager.load("submitButtonUp.png", Texture.class);
		myManager.load("submitButtonDown.png", Texture.class);
		myManager.load("letterButtonUp.png", Texture.class);
		myManager.load("letterButtonDown.png", Texture.class);
		myManager.load("Germany-flag.png", Texture.class);
		myManager.load("United-kingdom-flag.png", Texture.class);
		myManager.load("blue_button05.png", Texture.class);
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
		
		myManager.finishLoading(); // Cargamos los recursos para usarlos
		
		/* Creamos las pantallas del juego */
		
		LanguageSelectionScreen = new LanguageSelectionScreen(this);
		
		victory = false;
		
		setScreen(LanguageSelectionScreen); // Establecemos la pantalla al inicio de la app
	}
	
	/** En el método dispose() liberamos la memoria de los recursos que hemos usado */
	
	@Override
	public void dispose() {
		super.dispose();
		myBatch.dispose();
		myManager.dispose();
	}
	
	public void createScreens() {
		CategorySelectionScreen = new CategorySelectionScreen(this);
		MenuScreen = new MenuScreen(this);
	
	}
	
	public void createScreens2() {
		GameScreen = new GameScreen(this);
		MiniGameScreen = new MiniGameScreen(this);
		VictoryScreen = new VictoryScreen(this);
	}
	
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
	}
	
	
	/* -------------- Getters -------------- */
	
	public SpriteBatch getSB() {
		return myBatch;
	}

	public GameScreen getGameScreen() {
		return GameScreen;
	}

	public MiniGameScreen getMiniGameScreen() {
		return MiniGameScreen;
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

	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public void setSinglePlayer(boolean singlePlayer) {
		this.singlePlayer = singlePlayer;
	}
}

