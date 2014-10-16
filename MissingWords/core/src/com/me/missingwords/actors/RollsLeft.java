package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class RollsLeft extends Label {
	
	private int rolls;

	public RollsLeft() {
		super(null, 
				new LabelStyle(
				new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false), 
				Color.WHITE));
		
		setText("Rolls Left: " + rolls);
		
		setPosition(180, 85);
	}
	
	public void decreaseRolls() {
		--rolls;
		update();
	}
	
	private void update() {
		setText("Rolls Left: " + rolls);
	}

	public int getRolls() {
		return rolls;
	}

	public void setRolls(int rolls) {
		this.rolls = rolls;
		
		if (rolls != -1)
			update();
	}
}
