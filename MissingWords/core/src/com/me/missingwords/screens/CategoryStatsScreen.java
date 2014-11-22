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
import com.me.missingwords.MissingWords.Language;
import com.me.missingwords.actors.Background;
import com.me.missingwords.buttons.BackButton;
import com.me.missingwords.listeners.BackButtonListener;
import com.me.missingwords.utils.LanguageMenusStrings;

/**
 * 
 * Muestra la pantalla de las estadísticas por categoría.
 *
 */

public class CategoryStatsScreen extends BaseScreen implements LanguageMenusStrings {
	private Background background;
	private Table stageTable, left, right;
	private BitmapFont font, fontList;
	private Label title, progress;
	private List<String> list;
	private BackButton backButton;
	private int wordsDone;
	private String percentageProgress;

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
		left.add(backButton).align(Align.left).pad(10);
		
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
	public void updateLanguageStrings() {
		switch (missingWords.selectedLanguage.toString()) {
		case "german":
			progress.setText(progress_de + percentageProgress + "%");
			break;
		case "english":
			progress.setText(progress_en + percentageProgress + "%");
			break;
		}
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
		if (missingWords.selectedLanguage == Language.english)
			switch (missingWords.selectedCategory.toString()) {
				case "days": title.setText(days_en); break;
				case "months": title.setText(months_en); break;
				case "wquestions": title.setText(wQuestions_en); break;
				case "colours": title.setText(colours_en); break;
				case "size": title.setText(size_en); break;
				case "classroom": title.setText(classroom_en); break;
				case "bodyparts": title.setText(bodyParts_en); break;
				case "feelings": title.setText(feelings_en); break;
				case "university": title.setText(university_en); break;
				case "city": title.setText(city_en); break;
				case "freetime": title.setText(freetime_en); break;
			}
		else
			switch (missingWords.selectedCategory.toString()) {
			case "days": title.setText(days_de); break;
			case "months": title.setText(months_de); break;
			case "wquestions": title.setText(wQuestions_de); break;
			case "colours": title.setText(colours_de); break;
			case "size": title.setText(size_de); break;
			case "classroom": title.setText(classroom_de); break;
			case "bodyparts": title.setText(bodyParts_de); break;
			case "feelings": title.setText(feelings_de); break;
			case "university": title.setText(university_de); break;
			case "city": title.setText(city_de); break;
			case "freetime": title.setText(freetime_de); break;
			}
		
		/* Calculamos el progreso */
		float totalProgress;
		totalProgress = ((float)wordsDone / (float)words.size()) * 100;
		DecimalFormat decimal = new DecimalFormat("0.00");
		percentageProgress = String.valueOf(decimal.format(totalProgress));
		
		updateLanguageStrings();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}
