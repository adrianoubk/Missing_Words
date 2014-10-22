package com.me.missingwords.data;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * 
 * Almacena las estadísticas del jugador.
 *
 */

public class StatsData {
	private Preferences prefs;
	private int maxWords, correctWords, gamesWon, gamesLost, cluesUsed;
	private int scoreBestWord;
	private float hits;
	private String percentageHits;
	private String largestWord, bestWord;
	
	public StatsData() {
		prefs = Gdx.app.getPreferences("stats"); // Creamos el archivo para guardar las stats
		
		/* Iniciazlizamos las stats, si no se encuentran, toman el segundo parametro */
		maxWords = prefs.getInteger("maxWords", 0);
		correctWords = prefs.getInteger("correctWords", 0);
		percentageHits = prefs.getString("percentageHits", "N/A");
		gamesWon = prefs.getInteger("gamesWon", 0);
		gamesLost = prefs.getInteger("gamesLost", 0);
		cluesUsed = prefs.getInteger("cluesUsed", 0);
		largestWord = prefs.getString("largestWord", "N/A");
		bestWord = prefs.getString("bestWord", "N/A");
	}
	
	/* saveData(): guarda la información en el fichero de preferencias stats */
	public void saveData() {
		prefs.putString("percentageHits", percentageHits);
		prefs.putInteger("maxWords", maxWords);
		prefs.putInteger("correctWords", correctWords);
		prefs.putInteger("gamesWon", gamesWon);
		prefs.putInteger("gamesLost", gamesLost);
		prefs.putInteger("cluesUsed", cluesUsed);
		prefs.putString("largestWord", largestWord);
		prefs.putString("bestWord", bestWord);
		
		prefs.flush();
	}
	
	/* calculatePercentageHits(): calcula el porcentaje de aciertos */
	public void calculatePercentageHits() {
		hits = ((float)correctWords / (float)maxWords) * 100;
		DecimalFormat decimal = new DecimalFormat("0.00");
		percentageHits = String.valueOf(decimal.format(hits));
	}
	
	public void increaseMaxWords() {
		++maxWords;
	}
	
	public void increaseCorrectWords() {
		++correctWords;
	}
	
	public void increaseGamesWon() {
		++gamesWon;
	}
	
	public void increaseGamesLost() {
		++gamesLost;
	}
	
	public void increaseCluesUsed() {
		++cluesUsed;
	}
	
	/* -------------- Getters and Setters -------------- */

	public int getMaxWords() {
		return maxWords;
	}

	public int getCorrectWords() {
		return correctWords;
	}

	public String getPercentageHits() {
		return percentageHits;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public int getGamesLost() {
		return gamesLost;
	}

	public int getCluesUsed() {
		return cluesUsed;
	}

	public String getLargestWord() {
		return largestWord;
	}

	public void setLargestWord(String largestWord) {
		this.largestWord = largestWord;
	}

	public String getBestWord() {
		return bestWord;
	}

	public void setBestWord(String bestWord, int score) {
		if (score > scoreBestWord) {
			this.bestWord = bestWord;
			scoreBestWord = score;
		}
		
	}
}
