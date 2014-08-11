package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

public class Tile extends Actor {
	private String letter;
	private int points;
	private TextureRegion tileTexture;
	
	public Tile(String letter, int points) {
		this.letter = letter;
		this.points = points;
		tileTexture = new TextureRegion(MissingWords.myManager.get(buildPath(letter), Texture.class));
		setDefaultSize();
	}
	
	public Tile(Tile t) { // Constructor de copia
		this(t.letter, t.points);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(tileTexture, getX(), getY(), getWidth(), getHeight());
	}
	
	private String buildPath(String letter) {
		String path = new String();
		
		path = letter + ".png";
		
		return path;
	}

	public void setDefaultSize() {
		setSize(71, 71);
	}
	
	public void setSmallSize()
	{
		setSize(50, 50);
	}
	
	public String letter() {
		return letter;
	}
	
	public int points() {
		return points;
	}
	/*
	private void addListeners() {
		this.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("hola amigos");		
				a.setVisible(false);
				submitGroup.addActor(a2);
			}
		});
		
	}*/
}

