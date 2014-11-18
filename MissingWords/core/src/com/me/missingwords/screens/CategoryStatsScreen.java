package com.me.missingwords.screens;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane.SplitPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.buttons.BackButton;
import com.me.missingwords.listeners.BackButtonListener;

/**
 * 
 * Muestra la pantalla de las estadísticas por categoría.
 *
 */

public class CategoryStatsScreen extends BaseScreen {
	private Background background;
	private Table stageTable, left, right;
	private BitmapFont font, fontList;
	private Label title, progress;
	private List<String> list;
	private BackButton backButton;
	private int wordsDone;

	public CategoryStatsScreen(MissingWords missingWords) {
		super(missingWords);
		
		font = new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false);	
		fontList = new BitmapFont(Gdx.files.internal("fonts/listFont.fnt"), Gdx.files.internal("fonts/listFont.png"), false);
		
		/* Creamos el fondo de pantalla */
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		/* Creamos el botón de vuelta atrás */
		backButton = new BackButton();
		backButton.addListener(new BackButtonListener(missingWords));
		
		/* Creamos la tabla que ocupará todo el stage (pantalla) */
		stageTable = new Table();
		stageTable.setFillParent(true);
		
		/* Creamos la etiqueta de la categoría */
		title = new Label(null, new LabelStyle(font, font.getColor()));
		
		/* Creamos el progreso de la categoría */
		progress = new Label(null, new LabelStyle(font, font.getColor()));
		
		/* Creamos la lista de palabras de la categoría */
		list = new List<String>(new ListStyle(
				fontList, 
				fontList.getColor(), 
				fontList.getColor(), 
				new TextureRegionDrawable(
					new TextureRegion(MissingWords.myManager.get("selection.png", Texture.class)))));
		
		/* Creamos el scroll para la lista de palabras */
		ScrollPane scroll = new ScrollPane(list, new ScrollPaneStyle(new TextureRegionDrawable(
				new TextureRegion(MissingWords.myManager.get("background.png", Texture.class))), 
					null, 
					null, 
					new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("verticalScroll.png", Texture.class))), 
					new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("squareBlue.png", Texture.class)))));
		
		/* Creamos la tabla izquierda del SplitPane */
		left = new Table();
		left.add(title).expand();
		left.row();
		left.add(progress).expand();
		left.row();
		left.add(backButton).align(Align.left).pad(20);
		
		/* Creamos la tabla derecha del SplitPane */
		right = new Table();
		right.add(scroll).fill().expand();
		
		/* Creamos el SplitPane */
		SplitPane split = new SplitPane(left, right, false, new SplitPaneStyle(
				new TextureRegionDrawable(
						new TextureRegion(MissingWords.myManager.get("split.png", Texture.class)))));
		
		stageTable.add(split).fill().expand(); // Añadimos el SplitPane a la stageTable
		
		stage.addActor(stageTable); // Añadimos la stageTable al stage
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
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
		
		wordsDone = 0; 
		
		ArrayList<String> words = new ArrayList<>();
		
		/* Leemos la categoría seleccionada del fichero */
		missingWords.getCategoryData().read(missingWords.selectedCategory.toString(), 
											missingWords.selectedLanguage.toString());
		
		/* Añadimos DONE a las palabras que hayamos acertado de cada categoría */
		for (Entry<String, Boolean> e: missingWords.getCategoryData().getCategoryWords().entrySet()) 
			if (e.getValue().equals(true)) {
				words.add(e.getKey() + " - " + "DONE");
				++wordsDone;
			}
			else
				words.add(e.getKey());
		
		/* Enviamos las palabras a la lista */
		String[] wordArray = words.toArray(new String[words.size()]);
		list.setItems(wordArray);
		
		/* Establecemos el título de la categoría */
		title.setText(missingWords.selectedCategory.toString());
		
		/* Calculamos el progreso */
		float totalProgress;
		totalProgress = ((float)wordsDone / (float)words.size()) * 100;
		DecimalFormat decimal = new DecimalFormat("0.00");
		String percentageProgress = String.valueOf(decimal.format(totalProgress));
		
		progress.setText("Progress:\n  " + percentageProgress + "%");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
