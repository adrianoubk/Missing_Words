package com.me.missingwords.actors;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

/**
 * 
 * Representa el dado del minijuego (1d6).
 *
 */

public class Dice extends Actor {
	private final int POSITION_X = 490;
	private final int POSITION_Y = 55;
	
	private TextureRegion diceTexture; // Textura del dado
	private Random r; // Variable que obtendrá el número aleatorio entre 1 y 6
	public boolean drawDice; // Booleano que indica si se dibuja el dado o no
	private int result; // Resultado obtenido en una tirada
	
	public Dice() {
		r = new Random();
		diceTexture = new TextureRegion();
		drawDice = false;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (drawDice)
			batch.draw(diceTexture, POSITION_X, POSITION_Y);
	}
	
	/* roll(): realiza una tirada */
	public void roll() {
		result = r.nextInt(6 - 1 + 1) + 1; // 1d6 -> max = 6 y min = 1
		
		switch (result) {
			case 1: diceTexture.setRegion(MissingWords.myManager.get("1.png", Texture.class)); break;
			case 2: diceTexture.setRegion(MissingWords.myManager.get("2.png", Texture.class)); break; 
			case 3: diceTexture.setRegion(MissingWords.myManager.get("3.png", Texture.class)); break;
			case 4: diceTexture.setRegion(MissingWords.myManager.get("4.png", Texture.class)); break;
			case 5: diceTexture.setRegion(MissingWords.myManager.get("5.png", Texture.class)); break;
			case 6: diceTexture.setRegion(MissingWords.myManager.get("6.png", Texture.class)); break;
		}
		
		drawDice = true; // Al tirar, indicamos al dado que se dibuje
	}
	
	/* -------------- Getters and Setters -------------- */

	public int getResult() {
		return result;
	}
}
