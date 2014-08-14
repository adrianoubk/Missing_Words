package com.me.missingwords.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;

public class TileBox extends Container<Table> {
	Table tileTable;
	TextureRegionDrawable tDrawable;
	
	public TileBox(Table table) {
		super(table);
		tileTable = table;
		tileTable.setFillParent(true);
		tileTable.top().left();
		tileTable.padTop(2);
		
		tDrawable = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("cuadroFichaNuevo.png", Texture.class)));
		setBounds(254, 110, 288, 288); 
		bottom();
		setBackground(tDrawable);
	}
	

	public Table getTileTable() {
		return tileTable;
	}
}
