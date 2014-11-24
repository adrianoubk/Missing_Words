package com.me.missingwords.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import com.me.missingwords.MissingWords.Category;
import com.me.missingwords.MissingWords.Language;

/**
 * 
 * Guarda el conjunto de palabras que se pueden jugar. Está formado por un map de strings y 
 * categories.
 *
 */

public class Vocabulary {
	
	private HashMap<String, Category> vocabulary;
	
	public Vocabulary(Language language, Category category) throws IOException {
		vocabulary = new HashMap<String, Category>();	
		FileHandle file = null;
		
		/* Cargamos el fichero de idioma */
		switch (language) {
			case german: file = Gdx.files.external("MissingWordsData/GameData/vocabulary-german.txt"); break;
			case english: file = Gdx.files.external("MissingWordsData/GameData/vocabulary-english.txt"); break;
		}
		
		BufferedReader br = new BufferedReader(file.reader());
		String line;
		String[] word;
		
		while((line = br.readLine()) != null) {	
			word = line.split(",");
			
			if (category == Category.ALL)
				vocabulary.put(word[0], category);
			else 
				if (word[1].equals(category.toString())) {
					vocabulary.put(word[0], category);
				}
		}
			
		/*for (Entry<String, Category> e: vocabulary.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
		}*/
		
		br.close();
	}
	
	/* randomKey(): obtiene una palabra al azar del map */
	public String randomKey() {
		Object[] keys;
		Object randomKey;
		
		keys = vocabulary.keySet().toArray();
		randomKey = keys[new Random().nextInt(keys.length)];
		
		return (String) randomKey;
	}
	
	/* -------------- Getters and Setters -------------- */
	
	public Map<String, Category> getVocabulary() {
		return vocabulary;
	}
}
