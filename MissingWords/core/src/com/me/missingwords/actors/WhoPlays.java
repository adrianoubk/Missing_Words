package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Pool;

import com.me.missingwords.GameData;
import com.me.missingwords.MissingWords;
import com.me.missingwords.screens.GameScreen;

public class WhoPlays extends Label implements GameData {
	
	private static BitmapFont font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
	private SequenceAction action;
	private String name;
	private Pool<SequenceAction> pool;
	
	private GameScreen game;
	
	public WhoPlays(String name) {
		super(name, new LabelStyle(font, Color.BLACK));
		this.name = name;
		
		setTouchable(Touchable.disabled);
		setColor(1, 1, 1, 0);
		
		pool = new Pool<SequenceAction>() {
		    protected SequenceAction newObject () {
		        return new SequenceAction(Actions.delay(0.5f),
		        		Actions.fadeIn(2), Actions.fadeOut(2),
		        		Actions.run(new Runnable() {		
							@Override
							public void run() {
								
								game.newTiles();
								
								if (game.getNpc().isMyTurn()) {
									game.getNpc().createWord(
											game.getSubmitBox(), game.getOriginalTiles(), 
											game.getCopyTiles(), game.getAdaptedWordNPC());
									
								}
								
								game.getTimeBar().start();							
								System.out.println("Action completed");
								removeAction(action);
							}
						}));
		    }
		};
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		setPosition(((MissingWords.VIEWPORT_WIDTH - game.getTileBox().getTileTable().getWidth()) / 2) + 
				((game.getTileBox().getTileTable().getWidth() - font.getBounds(name).width) / 2), 250);
		
	}
	
	public void performAction() {
		action = pool.obtain();
		action.setPool(pool);
		addAction(action);
	}

	public void setName(String name) {
		setText(name);
		this.name = name;
	}

	@Override
	public void getGameData(MissingWords missingWords) {
		game = (GameScreen) missingWords.getGameScreen();
		
	}
}
