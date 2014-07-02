package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.me.missingwords.MissingWords;
import com.me.missingwords.actors.Background;
import com.me.missingwords.actors.Tile;
import com.me.missingwords.actors.TileBox;
import com.me.missingwords.actors.TimeBar;
import com.me.missingwords.actors.Turn;

public class GameScreen extends BaseScreen {
	
	private Image imageCuadro;
	private float cont = 60, timeCounter;
	private float counter = 1;
	private HorizontalGroup submitGroup;
	private TileBox tileBox;
	private Tile a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p;
	private Container<TileBox> container;
	private Turn turn;
	private TimeBar barPrueba;
	private Background background;
	
	public GameScreen(MissingWords missingwords) {
		super(missingwords);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
		
		TileBox.drawDebug(stage);
		
		timeCounter += delta;
		if(timeCounter >= 1.0f && cont > 0){
			barPrueba.setProgress(counter);
			counter -= 1f / 60f;
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
		
		imageCuadro = new Image(MissingWords.myManager.get("cuadroFichaNuevo.png", Texture.class));
		imageCuadro.setPosition(255, 93);
		stage.addActor(imageCuadro);
		
		a = new Tile(MissingWords.myManager.get("q.png", Texture.class), "a", 3);
		b = new Tile(MissingWords.myManager.get("a.png", Texture.class), "a", 3);
		c = new Tile(MissingWords.myManager.get("a.png", Texture.class), "a", 3);
		d = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		e = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		f = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		g = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		h = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		i = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		j = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		k = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		l = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		m = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		n = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		o = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
		p = new Tile(MissingWords.myManager.get("a.png", Texture.class), "b", 3);
	
		a.setSize(71, 71);
		b.setSize(71, 71);
		c.setSize(71, 71);
		d.setSize(71, 71);
		e.setSize(71, 71);
		f.setSize(71, 71);
		g.setSize(71, 71);
		h.setSize(71, 71);
		i.setSize(71, 71);
		j.setSize(71, 71);
		k.setSize(71, 71);
		l.setSize(71, 71);
		m.setSize(71, 71);
		n.setSize(71, 71);
		o.setSize(71, 71);
		p.setSize(71, 71);
		
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
		
		tileBox = new TileBox();	
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
		
		tileBox.setSize(288, 288);
		//tileBox.debugTable();
		tileBox.top();
		
		container = new Container<TileBox>(tileBox);
		container.setBounds(254, 92, 288, 288);
		container.bottom();
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
