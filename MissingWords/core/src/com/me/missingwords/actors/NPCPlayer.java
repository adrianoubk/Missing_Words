package com.me.missingwords.actors;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.me.missingwords.MissingWords;
import com.me.missingwords.screens.GameScreen;

public class NPCPlayer extends Player {
	
	private int wordCounter;
	private Timer h;
	private boolean isTurnFinished;

	public NPCPlayer(String name) {
		super(name);
		wordCounter = 0;
		h = new Timer();
		isTurnFinished = false;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (isTurnFinished()) {
			if (isMyTurn()) {
				System.out.println("Entrando al turno NPC....");
				isTurnFinished = false;
				game.getTimeBar().resetTime();	
				game.newTiles();
				
				playTurn(game.getSubmitBox(), game.getOriginalTiles(), game.getCopyTiles(),
						game.getAdaptedWordNPC());
			}
		}
	}
	
	void playTurn(SubmitBox submitBox, ArrayList<Tile> original, ArrayList<Tile> copy,
			ArrayList<Tile> word) {
		createWord(submitBox, original, copy, word);
	}
	
	private void createWord(final SubmitBox submitBox, final ArrayList<Tile> original,
			final ArrayList<Tile> copy, final ArrayList<Tile> word) {
		
		int repeatCount = word.size();
		
		System.out.println("NPC Turn");
		
		h.scheduleTask(new Task() {
			
			@Override
			public void run() {
				int index = original.indexOf(word.get(wordCounter));  
				
				original.get(index).setVisible(false); // Oculta la ficha original
				copy.get(index).setSmallSize(); // Cambia el tamaño de la ficha copia al enviarla al submitBox
				submitBox.addActor(copy.get(index)); // Añade la ficha al submitBox
				submitBox.increaseNumActors();
				
				if (wordCounter < word.size()) {
					++wordCounter;
				}
				
				if (wordCounter == word.size()) {
					wordCounter = 0;
					submitWord(submitBox);
				}
			}
		}, 2, 1, repeatCount - 1);
	}
	
	private void submitWord(final SubmitBox submitBox) {
		h.scheduleTask(new Task() {
			
			@Override
			public void run() {
				SnapshotArray<Actor> word = new SnapshotArray<>();
				int score = 0;
				
				word = submitBox.getChildren();
				
				for (int i = 0; i < word.size; ++i) {
					Tile t = (Tile) word.get(i);
					score += t.getPoints();
				}
				
				System.out.println("Word Score: " + score);
				
				submitBox.clean();
				setMyTurn(false);
			}
		}, 2);
		
		System.out.println("Fin turno NPC");
	}

	public boolean isTurnFinished() {
		return isTurnFinished;
	}

	public void setTurnFinished(boolean isTurnFinished) {
		this.isTurnFinished = isTurnFinished;
	}

	@Override
	public void getGameData(MissingWords missingWords) {
		game = (GameScreen) missingWords.getGameScreen();	
	}
}
