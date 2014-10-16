package com.me.missingwords.actors;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

public class Dice extends Actor {
	
	private TextureRegion diceTexture;
	private Random r;
	public boolean drawDice;
	private int result;
	
	public Dice() {
		r = new Random();
		diceTexture = new TextureRegion();
		drawDice = false;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (drawDice)
			batch.draw(diceTexture, 490, 55);
	}
	
	public void roll() {
		result = r.nextInt(6 - 1 + 1) + 1;
		
		switch (result) {
			case 1: diceTexture.setRegion(MissingWords.myManager.get("1.png", Texture.class)); break;
			case 2: diceTexture.setRegion(MissingWords.myManager.get("2.png", Texture.class)); break; 
			case 3: diceTexture.setRegion(MissingWords.myManager.get("3.png", Texture.class)); break;
			case 4: diceTexture.setRegion(MissingWords.myManager.get("4.png", Texture.class)); break;
			case 5: diceTexture.setRegion(MissingWords.myManager.get("5.png", Texture.class)); break;
			case 6: diceTexture.setRegion(MissingWords.myManager.get("6.png", Texture.class)); break;
		}
		
		drawDice = true;
	}

	public int getResult() {
		return result;
	}
}
