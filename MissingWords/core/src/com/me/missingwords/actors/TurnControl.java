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
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Se encarga de iniciar y controlar el turno para el jugador.
 *
 */

public class TurnControl extends Label {
	private BitmapFont font;
	private SequenceAction action; 
	private String player;
	/* El objeto pool nos permite reutilizar una acción sin tener que volver a crearla
	 * cuando es eliminada 
	 */
	private Pool<SequenceAction> pool; 	
	private MissingWords missingWords;
	
	public TurnControl(String name, MissingWords missingWordsGame) {
		super(name, new LabelStyle(new BitmapFont(
			Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false), Color.BLACK));
		this.player = name;
		this.missingWords = missingWordsGame;
		font = new BitmapFont(
				Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		
		setTouchable(Touchable.disabled); // No se puede tocar
		setColor(1, 1, 1, 0); // Establecemos el alpha a 0 para la acción
		
		/* 
		 * Creamos la acción secuencial 
		 * 
		 * 1: Retraso de 0.5 sg
		 * 2: Aparecer con una duración de 1 sg
		 * 3: Desaparecer con una duración de 1 sg
		 * 4: Ejecutar un trozo de código -> Iniciar el turno
		 */
		
		pool = new Pool<SequenceAction>() {
		    protected SequenceAction newObject () {
		        return new SequenceAction(Actions.delay(0.5f),
		        		Actions.fadeIn(1), Actions.fadeOut(1),
		        		Actions.run(new Runnable() {		
							@Override
							public void run() {		
								missingWords.getGameScreen().getTileBox().getTileTable().setVisible(true);
								
								/* Permitimos que toque la pantalla */
								if (missingWords.getGameScreen().getHuman().isMyTurn())
									missingWords.getGameScreen().getHuman().touchScreen(Touchable.enabled);
								
								/* El botón de pause siempre se puede tocar */
								missingWords.getGameScreen().getPauseButton().setTouchable(Touchable.enabled);
								
								if (!missingWords.isSinglePlayer())
									if (missingWords.getGameScreen().getNpc().isMyTurn()) { // Si es el turno de la máquina
										missingWords.getGameScreen().getNpc().createWord( // Crea una palabra
											missingWords.getGameScreen().getSubmitBox(), 
											missingWords.getGameScreen().getOriginalTiles(), 
											missingWords.getGameScreen().getCopyTiles(), 
											missingWords.getGameScreen().getAdaptedWordNPC());
									}
					
								missingWords.getGameScreen().getTimeBar().start(); // Activamos el tiempo
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
	
	/* prepareTurn(): prepara el turno para el jugador que va a jugar */
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
		 * Establecemos el jugador que va a jugar y restablecemos las tiradas. Si solo juega 
		 * un jugador, no entrará en la parte correspondiente de la cpu. En el caso del jugador 
		 * restablecemos las pistas que puede usar por turno
		 */
		if (missingWords.getGameScreen().getHuman().isMyTurn()) {
			if (missingWords.selectedLanguage == Language.english)
				setPlayer("Your Turn");
			else
				setPlayer("Du bist dran!");
			missingWords.getGameScreen().getHuman().setRolls(0);
			missingWords.getGameScreen().getHuman().setCluesUsed(0);
		}
		else {
			missingWords.getGameScreen().getNpc().setMyTurn(true);
			missingWords.getGameScreen().getNpc().setRolls(0);
			if (missingWords.selectedLanguage == Language.english)
				setPlayer("Npc's Turn");
			else
				setPlayer("Gegner ist dran!");
		}
		
		/* Restablecemos las penalizaciones */
		missingWords.getGameScreen().getWordScore().setPenalties(0);
		
		/* Prohibimos al jugador que toque la pantalla */
		missingWords.getGameScreen().getHuman().touchScreen(Touchable.disabled); 
		
		/* Desactivamos el botón de pausa */
		missingWords.getGameScreen().getPauseButton().setTouchable(Touchable.disabled);
		
		/* Creamos las fichas y las ocultamos hasta que empiece el turno */
		missingWords.getGameScreen().newTiles(); 
		missingWords.getGameScreen().getTileBox().getTileTable().setVisible(false);
	}
	
	/* initialiseTurn(): inicializa el turno para el jugador */
	public void initialiseTurn() {
		action = pool.obtain(); // Obtiene la accion del pool
		action.setPool(pool); // establece el pool donde va a ir cuando sea eliminada
		addAction(action); // Añade y ejecuta la acción
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public void setPlayer(String player) {
		setText(player);
		this.player = player;
	}

	public SequenceAction getAction() {
		return action;
	}
}
