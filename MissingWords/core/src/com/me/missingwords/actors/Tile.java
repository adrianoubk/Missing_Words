package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Tile extends Actor {
	private String letter;
	private int points;
	private Texture texture;
	
	
	public Tile(Texture tileTexture, String letter, int points) {
		this.letter = letter;
		this.points = points;	
		texture = tileTexture;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getWidth(), getHeight());
	}

	public String letter() {
		return letter;
	}
	
	public int points() {
		return points;
	}
}

