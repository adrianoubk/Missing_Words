package com.me.missingwords;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.missingwords.screens.*;

/* 
 * La clase Game permite a nuestro juego dividirse en distintas pantallas como la pantalla de 
 * juego, menu, carga, etc. Game implementa ApplicationListener, una interfaz que se encarga de 
 * recibir los eventos con los que entra en contacto nuestra aplicacion. Estos son cuando la 
 * aplicacion es creada, pausada, resumida (despues de un estado de pausa),
 * renderizada (dibujando/refrescando la pantalla) o destruida.
 */

public class MissingWords extends Game {
	
	public BaseScreen GameScreen;
	
	/* La clase SpriteBatch nos permite dibujar las texturas de nuestro juego. Agrupa 
	 * sprites(imagenes) para enviarlas al procesador grafico y asi dibujarlas a la vez.
	 * Puesto que dibujarlas de una en una es mas costoso. Se recomiendo tener un unico
	 * SpriteBatch para el juego.
	 */
	private SpriteBatch myBatch; 
	
	/* Gestor de recursos del juego */
	public static AssetManager myManager; 
	
	@Override
	public void create() {
		myBatch = new SpriteBatch();
		myManager = new AssetManager();
		GameScreen = new GameScreen(this);
	
		myManager.load("barLoading.png", Texture.class);
		myManager.load("barBackground.png", Texture.class);
		myManager.load("background.png", Texture.class);
		myManager.load("cuadroFichaNuevo.png", Texture.class);
		myManager.load("a.png", Texture.class);
		myManager.load("w.png", Texture.class);
		myManager.load("q.png", Texture.class);
		myManager.load("TurnBar.png", Texture.class);
		
		myManager.finishLoading();
		
		setScreen(GameScreen);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		myBatch.dispose();
		myManager.dispose();
	}
	
	
	/* -------------- Getters -------------- */
	
	public SpriteBatch getSB() {
		return myBatch;
	}
}
