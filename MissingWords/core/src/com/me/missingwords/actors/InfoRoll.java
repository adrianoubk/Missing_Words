package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.me.missingwords.MissingWords;

/**
 * 
 * Muestra las puntuaciones que hay que conseguir para obtener más o menos tiradas.
 *
 */

public class InfoRoll extends VerticalGroup {	
	private final int POSITION_X = 120;
	private final int POSITION_Y = 350;
	
	private Label roll1, roll2, roll3, title;
	private MissingWords missingWords;
	private BitmapFont font;
	
	public InfoRoll(MissingWords game) {
		super();
		//.align(Align.left);
		
		missingWords = game;
		
		font = new BitmapFont(Gdx.files.internal("fonts/info.fnt"), 
				Gdx.files.internal("fonts/info.png"), false); 
		
		/* Creamos las etiquetas con la información a mostrar */
		title = new Label("   Points = roll(s)", new LabelStyle(font, Color.BLACK));
		roll1 = new Label(" 1 to " + missingWords.getMin() + " = 1 roll" , new LabelStyle(font, Color.BLACK));
		roll2 = new Label((missingWords.getMin() + 1)  + " to " + (missingWords.getMax() - 1) + " = 2 rolls", new LabelStyle(font, Color.BLACK));
		roll3 = new Label("        " + missingWords.getMax() + "+ = 3 rolls" , new LabelStyle(font, Color.BLACK));
		
		addActor(title);
		addActor(roll1);
		addActor(roll2);
		addActor(roll3);
		
		setPosition(POSITION_X, POSITION_Y);
	}
}
