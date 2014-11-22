package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.buttons.BackButton;
import com.me.missingwords.listeners.BackButtonListener;
import com.me.missingwords.listeners.CategorySelectionListener;
import com.me.missingwords.utils.LanguageMenusStrings;

/**
 *  
 * Se encarga de mostrar la pantalla de selección de categoría de juego.
 *
 */

public class CategorySelectionScreen extends BaseScreen implements LanguageMenusStrings {
	private Background background;
	private Label title;
	private TextButton days, months, wquestions, colours, size, classroom, bodyparts, feelings, 
					   university, city, freetime;
	private TextButtonStyle tStyle;
	private TextureRegionDrawable up, down;
	private BitmapFont font;
	private BackButton backButton;
	private VerticalGroup buttonsBoxLeft, buttonsBoxRight;

	public CategorySelectionScreen(MissingWords missingWords) {
		super(missingWords);
		
		/* Creamos el fondo de pantalla */
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		/* Creamos el botón de vuelta */
		backButton = new BackButton();
		backButton.addListener(new BackButtonListener(missingWords));
		stage.addActor(backButton);
		
		/* Creamos el título */
		title = new Label("Category selection", 
				new LabelStyle(new BitmapFont(Gdx.files.internal("fonts/title.fnt"), Gdx.files.internal("fonts/title.png"), false), Color.ORANGE));
			title.setPosition((MissingWords.VIEWPORT_WIDTH - title.getMinWidth()) / 2, 400);
			stage.addActor(title);
		
		/* Creamos los botones de categorías */	
		up = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("upButtonLarge.png", Texture.class)));
		down = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("downButton.png", Texture.class)));
		
		font = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"), Gdx.files.internal("fonts/myfont.png"), false);
		
		tStyle = new TextButtonStyle(up, down, null, font);
		
		days = new TextButton("Days", tStyle);
		days.addListener(new CategorySelectionListener("days", missingWords));
		
		months = new TextButton("Months", tStyle);
		months.addListener(new CategorySelectionListener("months", missingWords));
		
		wquestions = new TextButton("W-Questions", tStyle);
		wquestions.addListener(new CategorySelectionListener("wquestions", missingWords));
		
		colours = new TextButton("Colours", tStyle);
		colours.addListener(new CategorySelectionListener("colours", missingWords));
		
		size = new TextButton("Size", tStyle);
		size.addListener(new CategorySelectionListener("size", missingWords));
		
		classroom = new TextButton("Classroom", tStyle);
		classroom.addListener(new CategorySelectionListener("classroom", missingWords));
		
		bodyparts = new TextButton("Body-parts", tStyle);
		bodyparts.addListener(new CategorySelectionListener("bodyparts", missingWords));
		
		feelings = new TextButton("Feelings", tStyle);
		feelings.addListener(new CategorySelectionListener("feelings", missingWords));
		
		university = new TextButton("University", tStyle);
		university.addListener(new CategorySelectionListener("university", missingWords));
		
		city = new TextButton("City", tStyle);
		city.addListener(new CategorySelectionListener("city", missingWords));
		
		freetime = new TextButton("Free time", tStyle);
		freetime.addListener(new CategorySelectionListener("freetime", missingWords));
		
		/* Creamos los contenedores de botones */
		buttonsBoxLeft = new VerticalGroup();
		buttonsBoxLeft.space(10);
		buttonsBoxLeft.setPosition(200, 400);
		
		/* Añadimos los botones */
		buttonsBoxLeft.addActor(days);
		buttonsBoxLeft.addActor(months);
		buttonsBoxLeft.addActor(wquestions);
		buttonsBoxLeft.addActor(colours);
		buttonsBoxLeft.addActor(size);

		stage.addActor(buttonsBoxLeft); // Añadimos el grupo al stage
		
		buttonsBoxRight = new VerticalGroup();
		buttonsBoxRight.space(10);
		buttonsBoxRight.setPosition(600, 400);
		
		buttonsBoxRight.addActor(classroom);
		buttonsBoxRight.addActor(bodyparts);
		buttonsBoxRight.addActor(feelings);
		buttonsBoxRight.addActor(university);
		buttonsBoxRight.addActor(city);
		buttonsBoxRight.addActor(freetime);
		
		stage.addActor(buttonsBoxRight);
	}
	
	@Override
	public void updateLanguageStrings() {
		switch (missingWords.selectedLanguage.toString()) {
		case "german":
			title.setText(categorySelection_de);
			days.setText(days_de);
			months.setText(months_de);
			wquestions.setText(wQuestions_de);
			colours.setText(colours_de);
			size.setText(size_de);
			classroom.setText(classroom_de);
			bodyparts.setText(bodyParts_de);
			feelings.setText(feelings_de);
			university.setText(university_de);
			city.setText(city_de);
			freetime.setText(freetime_de);
			break;
		case "english":
			title.setText(categorySelection_en);
			days.setText(days_en);
			months.setText(months_en);
			wquestions.setText(wQuestions_en);
			colours.setText(colours_en);
			size.setText(size_en);
			classroom.setText(classroom_en);
			bodyparts.setText(bodyParts_en);
			feelings.setText(feelings_en);
			university.setText(university_en);
			city.setText(city_en);
			freetime.setText(freetime_en);
		}
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		title.setPosition((MissingWords.VIEWPORT_WIDTH - title.getMinWidth()) / 2, 400);
		
		stage.act();
		stage.draw();
	}
	
	@Override
	public void show() {
		super.show();
		
		updateLanguageStrings();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		font.dispose();
		stage.dispose();
	}
}
