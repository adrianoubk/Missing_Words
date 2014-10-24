package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import com.me.missingwords.MissingWords;

/**
 * 
 * Representa el espacio donde las letras son colocadas al ser seleccionadas para formar una 
 * nueva palabra.
 *
 */

public class SubmitBox extends HorizontalGroup {	
	private final int SUBMITBOX_HEIGHT = 40; // Altura del submitbox
	
	private int numActors; // Número de fichas que contiene el submitbox
	
	public SubmitBox() {
		super();
		align(Align.bottom); // alinear el submitbox a la base
		numActors = 0;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		update();
	}
	
	/* 
	 * update(): modifica la posición del objeto a medida que se introducen nuevas
	 * letras para centrarlo.
	 */ 
	public void update() {
		setPosition((MissingWords.VIEWPORT_WIDTH - getMinWidth()) / 2, SUBMITBOX_HEIGHT);
	}
	
	public void increaseNumActors() {
		++numActors;
	}
	
	public void decreaseNumActors() {
		--numActors;
	}
	
	/* clean(): elimina las fichas del grupo */
	public void clean() {
		clearChildren();
		setNumActors(0);
	}

	/* -------------- Getters and Setters -------------- */
	
	public int getNumActors() {
		return numActors;
	}
	
	public void setNumActors(int value) {
		numActors = value;
	}
}
