package com.me.missingwords.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.SnapshotArray;
import com.me.missingwords.actors.Tile;
import com.me.missingwords.actors.SubmitBox;

public class Button extends ImageButton {

	public Button(Drawable imageUp, Drawable imageDown) {
		super(imageUp, imageDown);
	}
		
}
