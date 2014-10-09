package com.me.missingwords.screens;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Dice;
import com.me.missingwords.actors.World;
import com.me.missingwords.buttons.RollDiceButton;
import com.me.missingwords.listeners.RollListener;

public class MiniGameScreen extends BaseScreen {
	
	private World world;
	private RollDiceButton rollButton;
	private Dice dice;
	private int playCount;
	private Timer t;

	public MiniGameScreen(MissingWords missingWords) {
		super(missingWords);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		world.getRenderer().render();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {
		
		super.show();
		
		if (!missingWords.isGameRunning()) {
			missingWords.setGameRunning(true);
			
			playCount = 0;
			
			t = new Timer();
			
			world = new World(missingWords);
			
			dice = new Dice();
			stage.addActor(dice);
			
			rollButton = new RollDiceButton();
			rollButton.addListener(new RollListener(missingWords));
			stage.addActor(rollButton);
		}
		
		if (missingWords.isSinglePlayer()) // SINGLEPLAYER
			rollButton.setTouchable(Touchable.enabled); // Activo dado siempre
		else { // PLAYER VS CPU
			if ((playCount % 2) == 0) // Jugada par -> turno jugador
				rollButton.setTouchable(Touchable.enabled);
			else { // Jugada impar -> turno npc
				rollButton.setTouchable(Touchable.disabled);
		
					t.scheduleTask(new Task() {
						@Override
						public void run() {
								increasePlayCount();
								int play = dice.roll();
								world.movePlayer(play, false);
						}
					}, 2);
			}
		}
		
	}

	public RollDiceButton getRollButton() {
		return rollButton;
	}

	public World getWorld() {
		return world;
	}

	public Dice getDice() {
		return dice;
	}

	public void increasePlayCount() {
		++playCount;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		world.dispose();
		stage.dispose();
	}
}
