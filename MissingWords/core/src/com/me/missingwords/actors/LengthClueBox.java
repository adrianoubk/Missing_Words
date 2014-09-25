package com.me.missingwords.actors;

import com.badlogic.gdx.scenes.scene2d.Touchable;

public class LengthClueBox extends SubmitBox {
	
	public LengthClueBox() {
		super();
		
		setTouchable(Touchable.disabled);
	}
	
	public void createLength(int wordLength) {
		
		Tile fakeTile = new Tile("none", 0);
		
		for (int i = 0; i < wordLength; ++i) {
			Tile t = new Tile(fakeTile);
			t.setSmallSize();
			addActor(t);
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		update();
	}
}
