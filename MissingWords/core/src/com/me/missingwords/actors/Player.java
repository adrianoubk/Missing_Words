package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.me.missingwords.GameData;
import com.me.missingwords.screens.GameScreen;

public abstract class Player extends Actor implements GameData {
	protected String name;
	protected boolean isMyTurn;
	protected GameScreen game;
	
	public Player(String name) {
		this.name = name;
		isMyTurn = false;
	}

	public boolean isMyTurn() {
		return isMyTurn;
	}

	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}
}
