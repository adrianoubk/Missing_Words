package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.actors.SubmitBox;
import com.me.missingwords.actors.Tile;

public class TileListenerTable extends AbstractTileListener {	
	public TileListenerTable(SubmitBox submitBox, Tile original, Tile copy)  {
		super(submitBox, original, copy);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		original.setVisible(false);
		copy.setSmallSize();
		submitBox.addActor(copy);
		submitBox.increaseNumActors();
	}
}
