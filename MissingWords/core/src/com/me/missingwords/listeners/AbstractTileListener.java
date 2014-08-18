package com.me.missingwords.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.missingwords.actors.SubmitBox;
import com.me.missingwords.actors.Tile;

public abstract class AbstractTileListener extends ClickListener {
	protected SubmitBox submitBox;
	protected Tile original;
	protected Tile copy;
	
	public AbstractTileListener(SubmitBox submitBox, Tile original, Tile copy)  {
		this.submitBox = submitBox;
		this.original = original;
		this.copy = copy;
	}
	
	@Override
	public abstract void clicked(InputEvent event, float x, float y);
}
