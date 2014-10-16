package com.me.missingwords.actors;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.me.missingwords.MissingWords;

/**
 *
 * Clase que representa al jugador controlado por la m�quina (IA).
 * 
 */

public class NPCPlayer extends Player {
	
	private int wordCounter; // Contador que recorre la palabra
	private Timer h; // Temporizador
	private boolean isTurnFinished; // Variable que indica si el turno ha terminado o no

	public NPCPlayer(String name, MissingWords missingWords) {
		super(name, missingWords);
		this.missingWords = missingWords;
		wordCounter = 0;
		h = new Timer();
		isTurnFinished = false;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (isTurnFinished()) { // �Ha terminad su turno?
			if (isMyTurn()) { // Si no ha terminado y es su turno
				System.out.println("Entrando al turno NPC....");
				isTurnFinished = false; // Restringimos que haga m�s de un turno seguido
				
				/* Juega la m�quina */
				playTurn(missingWords.getGameScreen().getSubmitBox(), 
						missingWords.getGameScreen().getOriginalTiles(), 
						missingWords.getGameScreen().getCopyTiles(),
						missingWords.getGameScreen().getAdaptedWordNPC());
			}
		}
	}
	
	/*
	 * playTurn(): M�todo con el que la m�quina juega su turno y confirma la palabra 
	 */
	
	void playTurn(SubmitBox submitBox, ArrayList<Tile> original, ArrayList<Tile> copy,
			ArrayList<Tile> word) {
		missingWords.getGameScreen().getTurnControl().prepareTurn(); // Prepara el turno
		missingWords.getGameScreen().getTurnControl().initialiseTurn(); // Inicializa el turno
	}
	
	/*
	 * createWord(): M�todo con el que la m�quina crea una palabra
	 */
	
	public void createWord(final SubmitBox submitBox, final ArrayList<Tile> original,
			final ArrayList<Tile> copy, final ArrayList<Tile> word) {
		
		int repeatCount = word.size(); // Contador de letras
		
		h.scheduleTask(new Task() { // Nueva tarea
			
			@Override
			public void run() {
				int index = original.indexOf(word.get(wordCounter)); // Primera letra  
				
				original.get(index).setVisible(false); // Oculta la ficha original
				copy.get(index).setSmallSize(); // Cambia el tama�o de la ficha copia al enviarla al submitBox
				submitBox.addActor(copy.get(index)); // A�ade la ficha al submitBox
				submitBox.increaseNumActors(); 
				
				if (wordCounter < word.size()) { // Si quedan letras por procesar
					++wordCounter;
				}
				
				if (wordCounter == word.size()) { // No quedan mas letras que procesar
					wordCounter = 0; // Restablecemos el contador
					submitWord(submitBox); // Enviamos palabra
				}
			}
		}, 2, 1, repeatCount - 1);
	}
	
	/* 
	 * submitWord(): M�todo con el que la m�quina confirma una palabra
	 */
	
	private void submitWord(final SubmitBox submitBox) {
		h.scheduleTask(new Task() {
			
			@Override
			public void run() {
				SnapshotArray<Actor> array = new SnapshotArray<>();
				int score = 0;
				
				array = submitBox.getChildren();
				
				StringBuilder word = new StringBuilder();
				
				for(int i = 0; i < array.size; ++i) {
					Tile t = (Tile) array.get(i);
					word.append(t.getLetter());
					score += t.getPoints();
				}
				
				missingWords.getGameScreen().addPlayedWord(word.toString());
				
				missingWords.getGameScreen().increaseTotalWords();
				
				calculateRolls(score);
				
				playMinigame();
				
				setMyTurn(false);
			}
		}, 1);
		
		System.out.println("Fin turno NPC");
	}

	public boolean isTurnFinished() {
		return isTurnFinished;
	}

	public void setTurnFinished(boolean isTurnFinished) {
		this.isTurnFinished = isTurnFinished;
	}
}
