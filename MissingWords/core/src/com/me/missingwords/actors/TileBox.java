package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.me.missingwords.MissingWords;

/**
 * 
 * Clase TileBox
 * 
 * La clase TileBox representa el panel donde se colocan las posibles fichas para formar
 * palabras. Su estructura es un contenedor que almacena una tabla para poder gestionar la fichas
 * fácilmente.
 *
 */

public class TileBox extends Container<Table> {
	
	private final int POSITION_X = 254;
	private final int POSITION_Y = 110;
	private final int WIDTH_HEIGHT = 288;
	private Table tileTable; // Tabla donde se colocan las fichas
	private TextureRegionDrawable tDrawable; // Fondo del panel
	
	public TileBox(Table table) {
		super(table);
		tileTable = table;
		tileTable.setFillParent(true); // El tamaño de la tabla es el tamaño del contenedor(padre)
		tileTable.top().left(); // Posicionar a tabla arriba y a la izquierda
		tileTable.padTop(2); // Relleno de la tabla por encima
		
		tDrawable = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("cuadroFichaNuevo.png", Texture.class)));
		setBounds(POSITION_X, POSITION_Y, WIDTH_HEIGHT, WIDTH_HEIGHT); 
		bottom(); // ajustar tilebox a la base
		setBackground(tDrawable);
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public Table getTileTable() {
		return tileTable;
	}
}
