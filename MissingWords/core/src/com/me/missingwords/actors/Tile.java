package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.me.missingwords.MissingWords;

/**
 * 
 * Clase Tile
 * 
 * La clase Tile representa las fichas de las letras con las que se forman las palabras.
 *
 */

public class Tile extends Actor {
	
	private final int DEFAULT_WIDTH_HEIGHT = 71; // Ancho y alto de la ficha por defecto
	private final int SMALL_WIDTH_HEIGHT = 50; // Ancho y alto de la ficha cuando se reduce
	private String letter; // letra asociada a la ficha
	private int points; // puntuación de la letra
	private TextureRegion tileTexture; // textura de la ficha
	
	public Tile(String letter, int points) {
		this.letter = letter;
		this.points = points;
		tileTexture = new TextureRegion(MissingWords.myManager.get(buildPath(letter), Texture.class));
		setDefaultSize();
	}
	
	/* 
	 * Constructor de copia 
	 */
	
	public Tile(Tile t) {
		this(t.letter, t.points);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(tileTexture, getX(), getY(), getWidth(), getHeight());
	}
	
	/* 
	 * El método buildPath() crea la ruta del archivo ".png" donde se encuentra la textura de 
	 * la ficha debido a que estas se crean aleatoriamente y no sabemos cual es.
	 */
	
	private String buildPath(String letter) {
		String path = new String();
		
		path = letter + ".png";
		
		return path;
	}

	public void setDefaultSize() {
		setSize(DEFAULT_WIDTH_HEIGHT, DEFAULT_WIDTH_HEIGHT);
	}
	
	public void setSmallSize()
	{
		setSize(SMALL_WIDTH_HEIGHT, SMALL_WIDTH_HEIGHT);
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public String getLetter() {
		return letter;
	}
	
	public int getPoints() {
		return points;
	}
}

