package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

/**
 * 
 * Clase TimeBar
 * 
 * La clase TimeBar representa la barra del tiempo que tienes disponible para formar una 
 * palabra. Está formada por dos ninepatch. Un ninepatch es una imagen que tiene areas
 * modificables definidas. Es decir, el constructor del ninepatch recibe 4 parámetros además
 * de la textura que se va a utilizar como imagen. Estos son: left, right, top, bottom. Estos
 * son los valores que indican los píxeles que no modificará en esas direcciones.
 *
 */

public class TimeBar extends Actor {
	
	private final int BACKGROUND_X = 255;
	private final int BACKGROUND_Y = 402;
	private final int BACKGROUND_WIDTH = 290;
	private final int BACKGROUND_HEIGHT = 20;
	private final int LOADING_X = 257;
	private final float LOADING_Y = 403.5f;
	private final int LOADING_WIDTH = 286;
	private final int LOADING_HEIGHT = 17;
	private final double FRAME_TIME_ROUNDED = 0.017; // frame time = 1 / 60 = 0.0166666667 ~ 0.017
	
	private NinePatch backgroundBar; // Ninepatch fondo
	private NinePatch loadingBar; // Ninepatch barra
	private TextureRegion textureBackground; // Textura del ninepatch fondo
	private TextureRegion textureLoading; // Textura del ninepatch barra
	private float progress = 1; // Variable que controla el progreso de la barra
	private float seconds = 60; // Segundos disponibles para jugar
	private float timeCounter = 1; // Contador de FPS
	private float secondsCounter = 1; // Contador de segundos
	
	private boolean activated;
	
	private MissingWords missingWords;
	
	public TimeBar(MissingWords missingWords) {
		
		this.missingWords = missingWords;
		
		textureBackground = new TextureRegion(MissingWords.myManager.get("barBackground.png", Texture.class));
		backgroundBar = new NinePatch(textureBackground, 6, 6, 5, 5);
		
		textureLoading = new TextureRegion(MissingWords.myManager.get("barLoading.png", Texture.class));
		loadingBar = new NinePatch(textureLoading, 5, 5, 4, 4);
		
		activated = false;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		backgroundBar.draw(batch, BACKGROUND_X, BACKGROUND_Y, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		
		if (progress > FRAME_TIME_ROUNDED) 
			loadingBar.draw(batch, LOADING_X, LOADING_Y, LOADING_WIDTH * progress, LOADING_HEIGHT);
	}

	/* El método act() define el comportamiento del actor dentro del escenario */
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if (activated) {
			timeCounter += delta; // Acumulamos el tiempo sumando los fps
			if(timeCounter >= 1 && seconds >= 0){ // Si suman 1 segundo y son mayores que 0
				setProgress(secondsCounter); // modificamos el progreso de la barra
				secondsCounter -= (float) 1 / 60; // restamos el valor del frame (pasos en la barra)
				timeCounter = 0; // establecemos el contador a 0
				--seconds; // quitamos 1 segundo
				}
			
			if (seconds == 0) {
				System.out.println("Time out!");
				
				missingWords.getGameScreen().getHuman().setMyTurn(false);
				
				if (!missingWords.isSinglePlayer()) {
					missingWords.getGameScreen().getNpc().setTurnFinished(true); 
					missingWords.getGameScreen().getNpc().setMyTurn(true);
				}
			}
		}
	}
	
	public void reset() {
		secondsCounter = 1;
		timeCounter = 1;
		setProgress(1);
		seconds = 60;
		
		stop();
	}
	
	public void start() {
		activated = true;
	}
	
	public void stop() {
		activated = false;
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public void setProgress(float progress) {
		this.progress = progress;
	}
}
