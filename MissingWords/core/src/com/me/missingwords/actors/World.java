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
	
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer renderer;
	private ArrayList<Vector2> arraySquares;
	private StaticTiledMapTile playerTile, npcTile;
	private StaticTiledMapTile transparentTile;
	private StaticTiledMapTile holeTile;
	private StaticTiledMapTile bothPlayers;
	private int playerPosition = 0;
	private int npcPosition = 31;
	private MissingWords missingWords;
	
	public World(MissingWords missingWords) {
		this.missingWords = missingWords;
		
		tileMap = new TmxMapLoader().load("prueba2.tmx");
		renderer = new OrthogonalTiledMapRenderer(tileMap, missingWords.getSB());
		renderer.setView((OrthographicCamera) missingWords.getGameScreen().getStage().getViewport().getCamera());
		
		playerTile = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("player.png", Texture.class))));
		npcTile = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("npc.png", Texture.class))));
		transparentTile = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("transparentTile.png", Texture.class))));		
		holeTile = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("holeGrass.png", Texture.class))));
		bothPlayers = new StaticTiledMapTile(new TextureRegion((MissingWords.myManager.get("bothPlayers.png", Texture.class))));
		
		TiledMapTileLayer pathLayer = (TiledMapTileLayer) tileMap.getLayers().get("Path");
		
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
		
		arraySquares = new ArrayList<>(32);
		
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
		
		TiledMapTileLayer tokenLayer = (TiledMapTileLayer) tileMap.getLayers().get("Tokens");
		Cell cell = new Cell();
		
		cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);
		
		cell.setTile(playerTile);
		
		if (!missingWords.isSinglePlayer()) {  // Si es singleplayer, no colocamos al npc		
			cell = tokenLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);
			cell.setTile(npcTile);
		}
	}
	
	public void movePlayer(int steps, boolean player) {
		
		TiledMapTileLayer tokenLayer = (TiledMapTileLayer) tileMap.getLayers().get("Tokens");
		
		Cell cell = new Cell();
		
		if (player) {
			cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);
			
			if (cell.getTile().equals(bothPlayers))
				cell.setTile(npcTile);
			else
				cell.setTile(transparentTile);
		
			playerPosition += steps;
			
			if (playerPosition > 31)
				playerPosition = 31;
			
			if (playerPosition == npcPosition) {
				cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);		
				cell.setTile(bothPlayers);
			}
			else {
				cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);		
				cell.setTile(playerTile);
			
			
				TiledMapTileLayer pathLayer = (TiledMapTileLayer) tileMap.getLayers().get("Path");
			
				cell = pathLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);
			
				if (cell.getTile().equals(holeTile)) 
					respawnPlayer(playerPosition, true);
			}
		}
		
		else {
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
			
				TiledMapTileLayer pathLayer = (TiledMapTileLayer) tileMap.getLayers().get("Path");
			
				cell = pathLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);
			
				if (cell.getTile().equals(holeTile)) 
					respawnPlayer(npcPosition, false);
			}
		}
		
		checkVictory();
	}
	
	public void respawnPlayer(int oldPosition, boolean player) {
		
		TiledMapTileLayer tokenLayer = (TiledMapTileLayer) tileMap.getLayers().get("Tokens");
		Cell cell = new Cell();
		
		if (player) {		
			cell = tokenLayer.getCell((int) arraySquares.get(oldPosition).x, (int) arraySquares.get(oldPosition).y);
			cell.setTile(transparentTile);
		
			playerPosition = 0;
		
			cell = tokenLayer.getCell((int) arraySquares.get(playerPosition).x, (int) arraySquares.get(playerPosition).y);
			cell.setTile(playerTile);
		
		}
		
		else {
			cell = tokenLayer.getCell((int) arraySquares.get(oldPosition).x, (int) arraySquares.get(oldPosition).y);
			
			cell.setTile(transparentTile);
			
			npcPosition = 31;
			
			cell = tokenLayer.getCell((int) arraySquares.get(npcPosition).x, (int) arraySquares.get(npcPosition).y);
			
			cell.setTile(npcTile);
		}
	}
	
	public void checkVictory() {
		if (playerPosition == 31) {
			missingWords.getGameScreen().setWinner("Player");
			missingWords.setVictory(true);
		}
		
		if (npcPosition == 0) {
			missingWords.getGameScreen().setWinner("NPC");
			missingWords.setVictory(true);
		}
		
		if (missingWords.victory()) {
			missingWords.setVictory(false);			
			missingWords.setScreen(missingWords.VictoryScreen);
		}	
		else if (!missingWords.victory() && missingWords.getMiniGameScreen().getRollsLeft().getRolls() == -1) {
				missingWords.getMiniGameScreen().getWaitButton().hide();
				missingWords.getMiniGameScreen().getMoveButton().hide();
				missingWords.getMiniGameScreen().getContinueButton().setVisible(true);
				missingWords.getMiniGameScreen().getContinueButton().setTouchable(Touchable.enabled);
		}
	}

	public OrthogonalTiledMapRenderer getRenderer() {
		return renderer;
	}
	
	public void dispose() {
		renderer.dispose();
		tileMap.dispose();
	}
}
