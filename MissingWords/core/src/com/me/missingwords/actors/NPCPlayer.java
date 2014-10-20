package com.me.missingwords.actors;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.me.missingwords.MissingWords;

/**
 *
 * Representa al jugador controlado por el dispositivo (IA): NPC 
 * 
 */

public class NPCPlayer extends Player {
	private int wordCounter; // Contador usado para recorrer la palabra
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
		
		/* Entrando al turno del NPC */
		if (isTurnFinished()) { // ¿Ha terminad su turno?
			if (isMyTurn()) { // Si no ha terminado y es su turno
				/* Evitamos que entre hasta que no acabe su turno actual */
				isTurnFinished = false; 
				
				/* Juega la máquina */
				playTurn(missingWords.getGameScreen().getSubmitBox(), 
						missingWords.getGameScreen().getOriginalTiles(), 
						missingWords.getGameScreen().getCopyTiles(),
						missingWords.getGameScreen().getAdaptedWordNPC());
			}
		}
	}
	
	/* playTurn(): la máquina juega su turno: confirma la palabra y juega al minijuego */
	void playTurn(SubmitBox submitBox, ArrayList<Tile> original, ArrayList<Tile> copy,
			ArrayList<Tile> word) {
		missingWords.getGameScreen().getTurnControl().prepareTurn(); // Prepara el turno
		missingWords.getGameScreen().getTurnControl().initialiseTurn(); // Inicializa el turno
	}
	
	/* createWord(): la máquina crea una palabra */
	public void createWord(final SubmitBox submitBox, final ArrayList<Tile> original,
			final ArrayList<Tile> copy, final ArrayList<Tile> word) {
		int repeatCount = word.size(); // Contador de letras de la palabra
		
		/* 
		 * Creamos una tarea con el temporizador. Va escogiendo las letras para formar
		 * una palabra.
		 */
		h.scheduleTask(new Task() {
			
			@Override
			public void run() {
				int index = original.indexOf(word.get(wordCounter)); // Primera letra  
				
				original.get(index).setVisible(false); // Oculta la ficha original
				copy.get(index).setSmallSize(); // Cambia el tamaño de la ficha copia al enviarla al submitBox
				submitBox.addActor(copy.get(index)); // Añade la ficha al submitBox
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
	 
	/* submitWord(): la máquina confirma una palabra */
	private void submitWord(final SubmitBox submitBox) {
		
		/* 
		 * Creamos una tarea con el temporizador. Confirma la palabra y asigna las tiradas
		 * correspondientes en base a los puntos. 
		 */
		h.scheduleTask(new Task() {
			
			@Override
			public void run() {
				SnapshotArray<Actor> array = new SnapshotArray<>();
				int score = 0;
				
				array = submitBox.getChildren(); // Obtenemos la palabra en un array de Tiles
				
				/* 
				 * Con la clase StringBuilder creamos un array de strings a partir del array de
				 * Tiles
				 */
				StringBuilder word = new StringBuilder();
				
				for (int i = 0; i < array.size; ++i) {
					Tile t = (Tile) array.get(i);
					word.append(t.getLetter()); // añadimos la letra al array de strings
					score += t.getPoints(); // sumamos los puntos de la Tile
				}
				
				/* Añadimos la palabra a la lista de palabras jugadas */
				//missingWords.getGameScreen().addPlayedWord(word.toString());
				
				/* Incrementamos el número de palabras formadas */
				missingWords.getGameScreen().increaseTotalWords();
				
				/* Asignamos las tiradas correspondientes en base a la puntuación */
				calculateRolls(score);
				
				/* La máquina juega el minijuego */
				playMinigame();
				
				/* Finaliza el turno de la máquina */
				setMyTurn(false); 
			}
		}, 1);
		
		/* Fin turno NPC */
	}

	/* -------------- Getters and Setters -------------- */
	
	public boolean isTurnFinished() {
		return isTurnFinished;
	}

	public void setTurnFinished(boolean isTurnFinished) {
		this.isTurnFinished = isTurnFinished;
	}
}
