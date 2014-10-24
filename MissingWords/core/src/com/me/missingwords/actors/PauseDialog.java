package com.me.missingwords.actors;

/**
 * 
 * Representa al di�logo de pausa del juego.
 * 
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;

public class PauseDialog extends Dialog {
	private MissingWords missingWords;
	private WindowStyle style;
	
	public PauseDialog(MissingWords missingWords) {
		super("", new WindowStyle(
					new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false), 
					Color.WHITE, null));
		
		this.missingWords = missingWords;
		
		/* Creamos el estilo del di�logo */
		style = new WindowStyle(new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false), 
				Color.WHITE, null);
		
		/* Aplicamos un background al stage */
		style.stageBackground = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("backgroundDialog.png", Texture.class)));
		
		setStyle(style); // aplicamos el estilo
		
		/* Creamos un bot�n dentro del di�logo */
		button("resume", null, new TextButtonStyle(
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("upButton.png", Texture.class))),
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("downButton.png", Texture.class))), 
				null, 
				new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false)));
	}
	
	/* result(): M�todo que se ejecuta al pulsar el bot�n y cierra el di�logo */
	@Override
	protected void result(Object object) {
		super.result(object);
		
		/* Mostramos las fichas */
		missingWords.getGameScreen().getTileBox().getTileTable().setVisible(true);
		
		/* Reanudamos el tiempo */
		missingWords.getGameScreen().getTimeBar().start();
		
		/* Si no es singleplayer y es el turno de la npc, reanudamos su temporizador */
		if (!missingWords.isSinglePlayer())
			if (missingWords.getGameScreen().getNpc().isMyTurn())
				missingWords.getGameScreen().getNpc().getNpcTimer().start();
	}
}
