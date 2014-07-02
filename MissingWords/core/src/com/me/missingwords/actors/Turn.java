package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.MissingWords;

public class Turn extends Actor {
	
	private BitmapFont font;
	private int numTurn;
	private TextureRegion turnTexture;
	
	public Turn(int numTurn) {
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		turnTexture = new TextureRegion(MissingWords.myManager.get("TurnBar.png", Texture.class));
		this.numTurn = numTurn;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(turnTexture, (800 - turnTexture.getRegionWidth()) / 2, 405);
		font.setColor(Color.MAROON);
		font.draw(batch, "Turn " + numTurn, calculatePosition().x , calculatePosition().y);
	}

	private Vector2 calculatePosition() {
		Vector2 pos = new Vector2();
		
		pos.x = ((800 - turnTexture.getRegionWidth()) / 2) + 
				((turnTexture.getRegionWidth() - font.getBounds("Turn " + numTurn).width) / 2);
		pos.y = 405 + ((8 + turnTexture.getRegionHeight() + 
				font.getBounds("Turn " + numTurn).height) / 2);
		
		return pos;
	}

}