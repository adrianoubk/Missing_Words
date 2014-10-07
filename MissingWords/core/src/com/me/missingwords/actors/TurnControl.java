package com.me.missingwords.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Pool;
import com.me.missingwords.MissingWords;

/**
 * 
 * Clase que se encarga de iniciar el turno para el jugador que juegue.
 *
 */

public class TurnControl extends Label {
	
	private static BitmapFont font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
	private SequenceAction action; 
	private String player;
	
	/* El objeto pool nos permite reutilizar una acción sin tener que volver a crearla
	 * cuando es eliminada 
	 */
	private Pool<SequenceAction> pool; 
	
	private MissingWords missingWords;
	
	public TurnControl(String name, MissingWords missingWordsGame) {
		super(name, new LabelStyle(font, Color.BLACK));
		this.player = name;
		this.missingWords = missingWordsGame;
		
		setTouchable(Touchable.disabled); // No se puede tocar
		setColor(1, 1, 1, 0); // Establecemos el color de la fuente
		
		/* Creamos la acción secuencial 
		 * 
		 * 1: Retraso de 0.5 sg
		 * 2: Aparecer con una duración de 2 sg
		 * 3: Desaparecer con una duración de 2 sg
		 * 4: Ejecutar un trozo de código -> Iniciar el turno
		 */
		
		pool = new Pool<SequenceAction>() {
		    protected SequenceAction newObject () {
		        return new SequenceAction(Actions.delay(0.5f),
		        		Actions.fadeIn(2), Actions.fadeOut(2),
		        		Actions.run(new Runnable() {		
							@Override
							public void run() {
								
								missingWords.getGameScreen().getTileBox().getTileTable().setVisible(true);
								
								if (missingWords.getGameScreen().getHuman().isMyTurn())
									missingWords.getGameScreen().getHuman().touchScreen(Touchable.enabled);
								
								if (!missingWords.isSinglePlayer())
									if (missingWords.getGameScreen().getNpc().isMyTurn()) { // Si es el turno de la máquina
										missingWords.getGameScreen().getNpc().createWord( // Crea una palabra
											missingWords.getGameScreen().getSubmitBox(), 
											missingWords.getGameScreen().getOriginalTiles(), 
											missingWords.getGameScreen().getCopyTiles(), 
											missingWords.getGameScreen().getAdaptedWordNPC());
									
								}
					
								missingWords.getGameScreen().getTimeBar().start(); // Activamos el tiempo
								System.out.println("Action completed");
								removeAction(action); // Enviamos la accion al "pool"
							}
						}));
		    }
		};
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		setPosition(((MissingWords.VIEWPORT_WIDTH - missingWords.getGameScreen().getTileBox().getTileTable().getWidth()) / 2) + 
				((missingWords.getGameScreen().getTileBox().getTileTable().getWidth() - font.getBounds(player).width) / 2), 250);
		
	}
	
	/*
	 * prepareTurn(): Prepara el turno para el jugador que va a jugar
	 */
	
	public void prepareTurn() {
		
		/* Limpiamos las fichas */		
		missingWords.getGameScreen().getSubmitBox().clean(); 
		missingWords.getGameScreen().getTileBox().clean();
		
		/* Restablecemos las pistas */
		missingWords.getGameScreen().getLetterClue().enableStyle();	
		missingWords.getGameScreen().getLengthClue().enableStyle();
		missingWords.getGameScreen().getLengthBox().clearChildren();
		missingWords.getGameScreen().getLengthBox().remove();
		missingWords.getGameScreen().getTranslationClue().enableStyle();
		
		/* Restablecemos el tiempo */
		missingWords.getGameScreen().getTimeBar().reset();
		
		/* 
		 * Establecemos el jugador que va a jugar. Si solo juega un jugador, no entrará
		 * en la parte correspondiente de la cpu 
		 */
		if (missingWords.getGameScreen().getHuman().isMyTurn()) {
			setPlayer("Your Turn"); 
		}
		else {
			missingWords.getGameScreen().getNpc().setMyTurn(true);
			setPlayer("Npc's Turn");
		}
		
		/* Prohibimos al jugador que toque la pantalla */
		missingWords.getGameScreen().getHuman().touchScreen(Touchable.disabled); 
		
		/* Creamos las fichas y las ocultamos hasta que empiece el turno */
		missingWords.getGameScreen().newTiles(); 
		missingWords.getGameScreen().getTileBox().getTileTable().setVisible(false);
	}
	
	/*
	 * initialiseTurn(): Inicializa el turno para el jugador
	 */
	
	public void initialiseTurn() {
		action = pool.obtain(); // Obtiene la accion del pool
		action.setPool(pool); // establece el pool donde va a ir cuando sea eliminada
		addAction(action); // Añade y ejecuta la acción
	}
	
	public void setPlayer(String player) {
		setText(player);
		this.player = player;
	}
}
