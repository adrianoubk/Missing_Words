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
import com.me.missingwords.screens.GameScreen;

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
	
	private GameScreen game;
	
	public TurnControl(String name, GameScreen gameScreen) {
		super(name, new LabelStyle(font, Color.BLACK));
		this.player = name;
		this.game = gameScreen;
		
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
								
								game.getTileBox().getTileTable().setVisible(true);
								
								if (game.getHuman().isMyTurn())
									game.getHuman().touchScreen(Touchable.enabled);
								
								if (game.getNpc().isMyTurn()) { // Si es el turno de la máquina
									game.getNpc().createWord( // Crea una palabra
											game.getSubmitBox(), game.getOriginalTiles(), 
											game.getCopyTiles(), game.getAdaptedWordNPC());
									
								}
					
								game.getTimeBar().start(); // Activamos el tiempo
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
		setPosition(((MissingWords.VIEWPORT_WIDTH - game.getTileBox().getTileTable().getWidth()) / 2) + 
				((game.getTileBox().getTileTable().getWidth() - font.getBounds(player).width) / 2), 250);
		
	}
	
	/*
	 * prepareTurn(): Prepara el turno para el jugador que va a jugar
	 */
	
	public void prepareTurn() {
		
		/* Limpiamos las fichas */		
		game.getSubmitBox().clean(); 
		game.getTileBox().clean();
		
		/* Restablecemos las pistas */
		game.getLetterClue().enableStyle();
		
		game.getLengthClue().enableStyle();
		game.getLengthBox().clearChildren();
		game.getLengthBox().remove();
		game.getTranslationClue().enableStyle();
		
		
		/* Restablecemos el tiempo */
		game.getTimeBar().reset();
		
		/* Establecemos el jugador que va a jugar */
		if (game.getHuman().isMyTurn()) {
			setPlayer("Your Turn"); 
		}
		else {
			game.getNpc().setMyTurn(true);
			setPlayer("Npc's Turn");
		}
		
		/* Prohibimos al jugador que toque la pantalla */
		game.getHuman().touchScreen(Touchable.disabled); 
		
		/* Creamos las fichas y las ocultamos hasta que empiece el turno */
		game.newTiles(); 
		game.getTileBox().getTileTable().setVisible(false);
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
