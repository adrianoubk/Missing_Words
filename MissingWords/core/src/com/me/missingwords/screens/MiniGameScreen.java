package com.me.missingwords.screens;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Dice;
import com.me.missingwords.actors.RollsLeft;
import com.me.missingwords.actors.World;
import com.me.missingwords.buttons.ContinueButton;
import com.me.missingwords.buttons.MoveButton;
import com.me.missingwords.buttons.RollDiceButton;
import com.me.missingwords.buttons.WaitButton;
import com.me.missingwords.listeners.ContinueMiniListener;
import com.me.missingwords.listeners.MoveListener;
import com.me.missingwords.listeners.RollListener;
import com.me.missingwords.listeners.WaitListener;

public class MiniGameScreen extends BaseScreen {
	
	private World world;
	private RollDiceButton rollButton;
	private MoveButton moveButton;
	private WaitButton waitButton;
	private ContinueButton continueButton;
	private Dice dice;
	private int playCount;
	private Timer t;
	private RollsLeft rollsLeft;

	public MiniGameScreen(MissingWords missingWords) {
		super(missingWords);
		
		playCount = 0;
		
		t = new Timer();
		
		world = new World(missingWords);
		
		dice = new Dice();
		stage.addActor(dice);
		
		rollButton = new RollDiceButton();
		rollButton.addListener(new RollListener(missingWords));
		stage.addActor(rollButton);
		
		moveButton = new MoveButton();
		moveButton.addListener(new MoveListener(missingWords));
		stage.addActor(moveButton);
		
		waitButton = new WaitButton();
		waitButton.addListener(new WaitListener(missingWords));
		waitButton.setVisible(false);
		stage.addActor(waitButton);
		
		continueButton = new ContinueButton();
		continueButton.addListener(new ContinueMiniListener(missingWords));
		continueButton.setVisible(false);
		stage.addActor(continueButton);
		
		rollsLeft = new RollsLeft();
		stage.addActor(rollsLeft);
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
		
		dice.drawDice = false;
		moveButton.setMoved(false);
		
		waitButton.hide();
		continueButton.hide();
		
		if (missingWords.isSinglePlayer()) { // SINGLEPLAYER
			rollButton.show();
			moveButton.setVisible(true);
			moveButton.setTouchable(Touchable.disabled);
			rollsLeft.setRolls(missingWords.getGameScreen().getHuman().getRolls());
			
		}
		else { // PLAYER VS CPU
			if ((playCount % 2) == 0) { // Jugada par -> turno jugador
				rollButton.show();
				moveButton.setVisible(true);
				moveButton.setTouchable(Touchable.disabled);
				rollsLeft.setRolls(missingWords.getGameScreen().getHuman().getRolls());
			}
			else { // Jugada impar -> turno npc
				rollButton.setTouchable(Touchable.disabled);
				moveButton.setTouchable(Touchable.disabled);
				rollsLeft.setRolls(missingWords.getGameScreen().getNpc().getRolls());
				
					t.scheduleTask(new Task() {
						@Override
						public void run() {
							dice.roll();
							rollsLeft.decreaseRolls();
							
							if (rollsLeft.getRolls() == 0)
								rollsLeft.setRolls(-1);
							
							world.movePlayer(dice.getResult(), false);
						}
					}, 1, 2, rollsLeft.getRolls() - 1);
			}
		}
		
	}

	public RollDiceButton getRollButton() {
		return rollButton;
	}

	public MoveButton getMoveButton() {
		return moveButton;
	}

	public WaitButton getWaitButton() {
		return waitButton;
	}

	public ContinueButton getContinueButton() {
		return continueButton;
	}

	public World getWorld() {
		return world;
	}

	public Dice getDice() {
		return dice;
	}

	public RollsLeft getRollsLeft() {
		return rollsLeft;
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
