package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.me.missingwords.actors.SubmitBox;
import com.me.missingwords.actors.Tile;

public class TileListenerSubmit extends AbstractTileListener {
	
	public TileListenerSubmit(SubmitBox submitBox, Tile original, Tile copy)  {
		super(submitBox, original, copy);
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		original.setVisible(true);
		submitBox.removeActor(copy);
		submitBox.decreaseNumActors();
	}
}
