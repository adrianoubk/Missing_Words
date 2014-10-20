package com.me.missingwords.actors;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.me.missingwords.MissingWords;

public class World {
	private final int SQUARES = 32; // Casillas del mapa
	
	private TiledMap tileMap; // Mapa de losetas
	private OrthogonalTiledMapRenderer renderer; // Renderer del mapa de losetas
	private ArrayList<Vector2> arraySquares; // array con las casillas del juego
	
	/* Losetas para el mapa */
	private StaticTiledMapTile playerTile, npcTile, transparentTile, holeTile, bothPlayers;
	
	/* Posiciones iniciales de los jugadores */
	private int playerPosition = 0;
	private int npcPosition = 31;
	
	/* Capas del mapa */
	private TiledMapTileLayer pathLayer, tokenLayer;
	
	private MissingWords missingWords;
	
	public World(MissingWords missingWords) {
		this.missingWords = missingWords;
		
		/* Cargamos el mapa */
		tileMap = new TmxMapLoader().load("minigame.tmx");
		
		/* Creamos el MapRenderer y lo asociamos a nuestra camara del stage */
		renderer = new OrthogonalTiledMapRenderer(tileMap, missingWords.getSB());
		renderer.setView((OrthographicCamera) missingWords.getGameScreen().getStage().getViewport().getCamera());
		
		/* Creamos las losetas */
		playerTile = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("player.png", Texture.class))));
		npcTile = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("npc.png", Texture.class))));
		transparentTile = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("transparentTile.png", Texture.class))));		
		holeTile = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("holeGrass.png", Texture.class))));
		bothPlayers = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("bothPlayers.png", Texture.class))));
		
		/* Obtenemos las capas del mapa */
		pathLayer = (TiledMapTileLayer) tileMap.getLayers().get("Path");
		tokenLayer = (TiledMapTileLayer) tileMap.getLayers().get("Tokens");
		
		generateHoles();
		
		createSquares();
		
		placePlayers();
	}
	
	/* generateHoles(): genera los agujeros aleatoriamente en el mapa */
	private void generateHoles() {
		Random r = new Random();
		
		for (int x = 0; x < pathLayer.getWidth(); ++x)
			for (int y = 0; y < pathLayer.getHeight(); ++y) {
				Cell cell = pathLayer.getCell(x, y);
				if (cell == null)
					continue;		
				else if (cell.getTile().getProperties().containsKey("path")) {
					int num = r.nextInt(10 - 1 + 1) + 1;
					if (num >= 9)
						cell.setTile(holeTile);
				}
			}
	}
	
	/* createSquares(): crea las casillas del mapa */
	private void createSquares() {
		arraySquares = new ArrayList<Vector2>(SQUARES);
		
		arraySquares.add(0, new Vector2(1, 6));
		arraySquares.add(1, new Vector2(2, 6));
		arraySquares.add(2, new Vector2(3, 6));
		arraySquares.add(3, new Vector2(4, 6));
		arraySquares.add(4, new Vector2(5, 6));
		arraySquares.add(5, new Vector2(6, 6));
		arraySquares.add(6, new Vector2(7, 6));
		arraySquares.add(7, new Vector2(8, 6));
		arraySquares.add(8, new Vector2(9, 6));
		arraySquares.add(9, new Vector2(10, 6));
		arraySquares.add(10, new Vector2(10, 5));
		arraySquares.add(11, new Vector2(10, 4));
		arraySquares.add(12, new Vector2(9, 4));
		arraySquares.add(13, new Vector2(8, 4));
		arraySquares.add(14, new Vector2(7, 4));
		arraySquares.add(15, new Vector2(6, 4));
		arraySquares.add(16, new Vector2(5, 4));
		arraySquares.add(17, new Vector2(4, 4));
		arraySquares.add(18, new Vector2(3, 4));
		arraySquares.add(19, new Vector2(2, 4));
		arraySquares.add(20, new Vector2(1, 4));
		arraySquares.add(21, new Vector2(1, 3));
		arraySquares.add(22, new Vector2(1, 2));
		arraySquares.add(23, new Vector2(2, 2));
		arraySquares.add(24, new Vector2(3, 2));
		arraySquares.add(25, new Vector2(4, 2));
		arraySquares.add(26, new Vector2(5, 2));
		arraySquares.add(27, new Vector2(6, 2));
		arraySquares.add(28, new Vector2(7, 2));
		arraySquares.add(29, new Vector2(8, 2));
		arraySquares.add(30, new Vector2(9, 2));
		arraySquares.add(31, new Vector2(10, 2));
	}
	
	/* placePlayers(): posiciona a los jugadores al principio del juego */
	private void placePlayers() {
		Cell cell = new Cell();
		
		cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);
		
		cell.setTile(playerTile);
		
		if (!missingWords.isSinglePlayer()) {  // Si no es modo SINGLEPLAYER, no colocamos al npc		
			cell = tokenLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);
			cell.setTile(npcTile);
		}
	}
	
	/* movePlayer(): mueve el jugador por el tablero */
	public void movePlayer(int steps, boolean player) {
		Cell cell = new Cell();
		
		if (player) { // mueve el jugador
			/* Obtenemos la casilla del jugador de la capa Tokens */
			cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);
			
			/* Antes de mover, comprobamos si estabamos en la misma casilla que el adversario */
			if (cell.getTile().equals(bothPlayers))
				cell.setTile(npcTile); // Al movernos dejamos solo la loseta del adversario
			else
				cell.setTile(transparentTile); // Si no, dejamos la loseta transparente
		
			playerPosition += steps; // movemos al jugador
			
			if (playerPosition > 31) // Si nos pasamos de nuestra meta, lo colocamos en la meta
				playerPosition = 31;
			
			/* Comprobamos si a la casilla que hemos llegado es la misma que el adversario */
			if (playerPosition == npcPosition) {
				cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);		
				cell.setTile(bothPlayers); // Si es, colocamos la loseta de los 2 jugadores
			}
			else { // Si no, colocamos a nuestro jugador en la casilla correspondiente
				cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);		
				cell.setTile(playerTile);
			
				cell = pathLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);
				
				/* Comprobamos si hemos caído en un agujero */
				if (cell.getTile().equals(holeTile)) 
					respawnPlayer(playerPosition, true); // Volvemos al jugador al inicio
			}
		}
		
		else { // Idem pero para el NPC
			cell = tokenLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);
			
			if (cell.getTile().equals(bothPlayers))
				cell.setTile(playerTile);
			else
				cell.setTile(transparentTile);
			
			npcPosition -= steps;
			
			if (npcPosition < 0)
				npcPosition = 0;
			
			if (playerPosition == npcPosition) {
				cell = tokenLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);		
				cell.setTile(bothPlayers);
			}
			else {
				cell = tokenLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);			
				cell.setTile(npcTile);
			
				cell = pathLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);
			
				if (cell.getTile().equals(holeTile)) 
					respawnPlayer(npcPosition, false);
			}
		}
		
		checkVictory(); // Comprobamos tras habernos movido, si hemos llegado a nuestro objetivo
	}
	
	/* respawnPlayer(): lleva al jugador a la casilla de inicio al caer en un agujero */
	public void respawnPlayer(int oldPosition, boolean player) {
		Cell cell = new Cell();
		
		if (player) { // Si es el jugador		
			cell = tokenLayer.getCell((int) arraySquares.get(oldPosition).x, (int) arraySquares.get(oldPosition).y);
			cell.setTile(transparentTile);
		
			playerPosition = 0;
		
			cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);
			cell.setTile(playerTile);
		
		}
		
		else { // Si es el NPC
			cell = tokenLayer.getCell((int) arraySquares.get(oldPosition).x, (int) arraySquares.get(oldPosition).y);
			
			cell.setTile(transparentTile);
			
			npcPosition = 31;
			
			cell = tokenLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);
			
			cell.setTile(npcTile);
		}
	}
	
	/* checkVictory(): Comprueba si alguno de los jugadores ha alcanzado su objetivo */
	public void checkVictory() {
		if (playerPosition == 31) { // Si gana el jugador
			missingWords.getGameScreen().setWinner("Player");
			missingWords.setVictory(true);
		}
		
		if (npcPosition == 0) { // Si gana el NPC
			missingWords.getGameScreen().setWinner("NPC");
			missingWords.setVictory(true);
		}
		
		if (missingWords.victory()) { // Nos muestra la pantalla de victoria
			missingWords.setVictory(false);			
			missingWords.setScreen(missingWords.VictoryScreen);
		} /* Si no se ha llegado a la victoria y no le quedan tiradas */	
		else if (!missingWords.victory() && missingWords.getMiniGameScreen().getRollsLeft().getRolls() == -1) {
				missingWords.getMiniGameScreen().getWaitButton().hide();
				missingWords.getMiniGameScreen().getMoveButton().hide();
				missingWords.getMiniGameScreen().getContinueButton().setVisible(true);
				missingWords.getMiniGameScreen().getContinueButton().setTouchable(Touchable.enabled);
		}
	}
	
	/* dispose(): libera los recursos utilizados */
	public void dispose() {
		renderer.dispose();
		tileMap.dispose();
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public OrthogonalTiledMapRenderer getRenderer() {
		return renderer;
	}
}
