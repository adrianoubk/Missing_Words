package com.me.missingwords.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.me.missingwords.MissingWords;

/**
 * 
 * Clase BaseScreen
 * 
 * La clase BaseScreen es una clase abstracta que recoge la información en común que van a 
 * tener todas las pantallas descendientes.
 *
 */

public abstract class BaseScreen implements Screen {
	
	protected MissingWords missingWords;
	protected SpriteBatch myBatch;
	
	/* Escenario del juego. El escenario es donde se desarrollará todo. Tiene una estructura de
	 * árbol donde se van añadiendo sus hijos, por lo que el orden es importante para saber 
	 * cuales se mostrarán antes y cuales después, es decir, para que no se solapen.
	 * Todos los objetos que heredan de Actor implementan dos métodos: draw() y act(). 
	 * Lo que es lo mismo: como se dibujaran y como actuarán en el escenario. Luego el escenario
	 * llama a sus métodos stage.act() y stage.draw() que llamarán recursivamente a todos los
	 * métodos act() y draw() de sus hijos para añadirlos al escenario. 
	 */
	protected Stage stage;
	
	/* Un escenario necesita un Viewport. Un viewport es lo que vemos en la pantalla, ya que si
	 * dibujamos fuera de los límites de la pantalla, no significa que no se esté
	 * dibujando, sino que no lo vemos porque está fuera del Viewport.
	 */
	
	private ScalingViewport viewport;
	
	/* Conectamos el juego con la pantalla base */
	
	public BaseScreen(MissingWords missingWords) {
		this.missingWords = missingWords;
		this.myBatch = missingWords.getSB();
		
		/* Con Scaling.stretch ajustamos la aplicación a la pantalla, estrechando
		 * si es necesario.
		 */
		
		viewport = new ScalingViewport(Scaling.stretch, 
				MissingWords.VIEWPORT_WIDTH , MissingWords.VIEWPORT_HEIGHT);
		stage = new Stage(viewport, myBatch);
	}
	
	public Stage getStage() {
		return stage;
	}
	
	/* El método render() es el método encargado de dibujar los elementos en la pantalla.
	 * Delta es el número de segundos desde que se ejectuó render() anteriormente.
	 */

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	/* El método resize() es el método que se llama cuando hay que ajustar la pantalla al 
	 * dispositivo, es decir, cuando hay que cambiar la resolución.
	 */

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true); // Actualizamos el viewport del stage
	}
	
	/* El método show() es igual que el método create() pero para una pantalla. Crea los objetos
	 * necesarios para trabajar con ellos.
	 */
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage); // Indicamos que la entrada la procesará el stage 
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

	/* El método dispose() sirve para liberar memoria, al terminar la aplicación */
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
