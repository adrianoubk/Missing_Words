package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.actors.Tile;
import com.me.missingwords.actors.TimeBar;
import com.me.missingwords.actors.Turn;

public class GameScreen extends BaseScreen {
	
	private float cont = 60, timeCounter = 1;
	private float counter = 1;
	private HorizontalGroup submitGroup;
	private Table tileBox;
	private Tile a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p;
	private Container<Table> container;
	private Turn turn;
	private TimeBar barPrueba;
	private Background background;
	private TextureRegionDrawable tDrawable;
	
	public GameScreen(MissingWords missingwords) {
		super(missingwords);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
		Table.drawDebug(stage);
		
		timeCounter += delta;
		if(timeCounter >= 1 && cont >= 0){
			barPrueba.setProgress(counter);
			counter -= (float) 1 / 60;
			timeCounter = 0;
			--cont;
			}
	}
		

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {
		background = new Background(MissingWords.myManager.get("background.png", Texture.class));
		stage.addActor(background);
		
		a = new Tile("a", 3);
		b = new Tile("a", 3);
		c = new Tile("a", 3);
		d = new Tile("a", 3);
		
		submitGroup = new HorizontalGroup();

		submitGroup.addActor(a);
		submitGroup.addActor(b);
		submitGroup.addActor(c);
		submitGroup.addActor(d);
	
		submitGroup.setPosition((800 - submitGroup.getMinWidth()) / 2, 0);
		submitGroup.align(Align.bottom);
		stage.addActor(submitGroup);
		
		turn = new Turn(1);
		stage.addActor(turn);
		
		tileBox = new Table();	
		tileBox.setFillParent(true);
		tileBox.add(a);
		tileBox.add(b);
		tileBox.add(c);
		tileBox.add(d);
		tileBox.row();
		tileBox.add(e);
		tileBox.add(f);
		tileBox.add(g);
		tileBox.add(h);
		tileBox.row();
		tileBox.add(i);
		tileBox.add(j);
		tileBox.add(k);
		tileBox.add(l);
		tileBox.row();
		tileBox.add(m);
		tileBox.add(n);
		tileBox.add(o);
		tileBox.add(p);
		
		tileBox.top().left();
		tileBox.padTop(2);
		
		tDrawable = new TextureRegionDrawable(new TextureRegion(MissingWords.myManager.get("cuadroFichaNuevo.png", Texture.class)));
		container = new Container<Table>(tileBox);
		container.setBounds(254, 92, 288, 288);
		container.bottom();
		container.setBackground(tDrawable);
		stage.addActor(container);
		
		barPrueba = new TimeBar();
		stage.addActor(barPrueba);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

}
