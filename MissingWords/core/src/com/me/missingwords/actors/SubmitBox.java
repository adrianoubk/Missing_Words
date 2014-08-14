package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.missingwords.MissingWords;

public class SubmitBox extends HorizontalGroup {
	private final int SUBMITBOX_HEIGHT = 55;
	private int numActors;
	
	public SubmitBox() {
		super();
		align(Align.bottom);
		numActors = 0;
	}
	
	public void update() {
		setPosition((MissingWords.VIEWPORT_WIDTH - getMinWidth()) / 2, SUBMITBOX_HEIGHT);
	}
	
	public void increaseNumActors() {
		++numActors;
	}
	
	public void decreaseNumActors() {
		--numActors;
	}

	public int getNumActors() {
		return numActors;
	}
}
