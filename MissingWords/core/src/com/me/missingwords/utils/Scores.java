package com.me.missingwords.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Clase Scores 
 * 
 * Clase que guarda las letras del idioma seleccionado junto con sus puntuaciones. Está formada 
 * por un map de strings y enteros.
 *
 */

public class Scores {
	
	private HashMap<String, Integer> scores;
	
	public Scores(Language language) throws IOException {
		scores = new HashMap<String, Integer>();
		
		FileHandle file = null;
		
		/* Cargamos el fichero de idioma */
		
		switch (language) {
		case german: file = Gdx.files.internal("utils/scores-german.txt"); break;
		case english: file = Gdx.files.internal("utils/scores-english.txt"); break;
		}
		
		/* Buffer donde volcamos el contenido del fichero para leerlo */
		
		BufferedReader br = new BufferedReader(file.reader());
		String line;
		String[] score;
		while ((line = br.readLine()) != null) { // leemos linea hasta que sea null
			score = line.split(" "); // separamos la linea por espacio
			
			/* Introducimos la puntuación en el map. Con parseInt tranformamos el número(String)
			 * a entero(Integer).
			 */
			
			scores.put(score[0], Integer.parseInt(score[1]));
		}
		
		/*for (Entry<String, Integer> e: scores.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}*/
		
		br.close();
	}
	
	/* El método randomKey() obtiene una letra al azar del map */
	
	public String randomKey() {
		Object[] keys;
		Object randomKey;
		
		keys = scores.keySet().toArray(); // Obtenemos un cojunto de claves
		randomKey = keys[new Random().nextInt(keys.length)]; // Obtenemos una clave al azar
		
		return (String) randomKey; // Casting de Object a String
	}

	/* -------------- Getters and Setters -------------- */
	
	public Map<String, Integer> getScores() {
		return scores;
	}
}
