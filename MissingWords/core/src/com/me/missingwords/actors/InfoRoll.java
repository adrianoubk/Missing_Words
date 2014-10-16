package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.me.missingwords.MissingWords;

public class InfoRoll extends VerticalGroup {
	
	private Label roll1, roll2, roll3, title;
	private MissingWords missingWords;
	private BitmapFont font;
	
	public InfoRoll(MissingWords game) {
		super();
		missingWords = game;
		
		font = new BitmapFont(Gdx.files.internal("fonts/info.fnt"), Gdx.files.internal("fonts/info.png"), false); 
		//font = new BitmapFont();
		
		title = new Label("   Points = roll(s)", new LabelStyle(font, Color.BLACK));
		roll1 = new Label(" 1 to " + missingWords.getMin() + " = 1 roll" , new LabelStyle(font, Color.BLACK));
		roll2 = new Label((missingWords.getMin() + 1)  + " to " + (missingWords.getMax() - 1) + " = 2 rolls", new LabelStyle(font, Color.BLACK));
		roll3 = new Label("        " + missingWords.getMax() + "+ = 3 rolls" , new LabelStyle(font, Color.BLACK));
		
		addActor(title);
		addActor(roll1);
		addActor(roll2);
		addActor(roll3);
		
		setPosition(120, 400);
	}
}
